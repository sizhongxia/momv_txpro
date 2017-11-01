package org.tm.pro.web.realm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.tm.pro.entity.User;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmCollectionUtil;
import org.tm.pro.utils.TmNumberUtil;
import org.tm.pro.web.cache.SystemBaseInfoCacheUtil;
import org.tm.pro.web.redis.TmRedisKeys;

import com.tm.pro.redis.util.RedisUtil;

public class TmJDBCRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;

	/***
	 * 权限验证判断
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 登陆成功，记录Session
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		Integer userId = user.getId();
		// Console.log("正在设置用户ID:{} 的权限信息.", userId);
		Set<String> roles = userService.getUserRoles(userId);
		if (roles == null) {
			roles = new HashSet<String>();
		}
		// Console.log("用户角色信息：{}", roles);
		authorizationInfo.setRoles(roles);

		Set<String> permissions = userService.getUserAuthorizations(userId);
		if (permissions == null) {
			permissions = new HashSet<String>();
		}
		// Console.log("用户权限字信息：{}", permissions);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	/**
	 * 登陆检查判断
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// token中储存着输入的用户名和密码
		UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
		String loginName = upToken.getUsername();

		User user = userService.getByLoginName(loginName);
		if (user == null) {
			// 未知账号
			throw new UnknownAccountException();
		}

		if (!"N".equals(user.getLockedState())) {
			// 账号锁定
			throw new LockedAccountException();
		}

		if (!"N".equals(user.getDisabledState())) {
			// 账号禁用
			throw new DisabledAccountException();
		}
		long expiredTime = user.getExpiredTime();
		if (expiredTime != 0) {
			long nowTime = new Date().getTime();
			if (expiredTime < nowTime) {
				// 帐号已过期
				throw new ExpiredCredentialsException();
			}
		}

		RedisUtil redis = RedisUtil.getInstance();

		String loginFailCountKey = TmRedisKeys.LoginFailCountKey + loginName;
		String failCountStr = redis.get(loginFailCountKey);

		String loginFailCountStr = SystemBaseInfoCacheUtil.systemInfo.get("login_fail_count");

		int failCount = TmNumberUtil.toInt(failCountStr, 0);
		int loginFailCount = TmNumberUtil.toInt(loginFailCountStr, 0);
		if (failCount >= loginFailCount) {
			// 账号登陆失败次数超出范围
			throw new ExcessiveAttemptsException();
		}
		String password = String.valueOf(upToken.getPassword());
		if (!user.getLoginPass().equals(password)) {
			String loginFailExpiredStr = SystemBaseInfoCacheUtil.systemInfo.get("login_fail_expired");
			int loginFailExpired = TmNumberUtil.toInt(loginFailExpiredStr, 0);
			failCount++;
			String fc = failCount + "";
			redis.setex(loginFailCountKey, loginFailExpired, fc);
			// 登陆密码错误
			throw new IncorrectCredentialsException();
		}
		// 删除Redis登陆失败次数Key
		redis.del(loginFailCountKey);

		Set<String> authorizations = userService.getUserAuthorizations(user.getId());
		if (TmCollectionUtil.isEmpty(authorizations)) {
			// 未授权
			throw new UnauthorizedException();
		}

		user.setLastLoginTime(System.currentTimeMillis());
		userService.update(user);

		return new SimpleAuthenticationInfo(user, password.toCharArray(), getName());
	}

}
