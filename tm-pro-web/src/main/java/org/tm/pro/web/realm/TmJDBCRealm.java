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
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.tm.pro.entity.Role;
import org.tm.pro.entity.SystemInfo;
import org.tm.pro.entity.User;
import org.tm.pro.service.UserRoleService;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmNumberUtil;
import org.tm.pro.web.cache.SystemInfoCacheUtil;

import com.tm.pro.redis.util.RedisUtil;

public class TmJDBCRealm extends AuthorizingRealm {

	private static String LoginFailCountKey = "tm:login:fail:count:";

	@Autowired
	UserService userService;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	RedisUtil redisUtil;

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

		Set<Role> roles = userRoleService.getUserRoles(userId);
		Set<String> roleCodes = new HashSet<String>();
		if (roles != null) {
			for (Role role : roles) {
				roleCodes.add(role.getRoleCode());
			}
		}
		authorizationInfo.setRoles(roleCodes);

		Set<String> permissions = userRoleService.getUserAuthorizations(userId);
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

		String password = String.valueOf(upToken.getPassword());
		// 系统配置信息
		SystemInfo systemInfo = SystemInfoCacheUtil.systemInfo;
		String loginFailLimit = systemInfo.getLoginFailLimit();
		// 登录限制
		if ("Y".equals(loginFailLimit)) {

			String loginFailCountKey = LoginFailCountKey + loginName;
			String failCountStr = redisUtil.get(loginFailCountKey);

			int failCount = TmNumberUtil.toInt(failCountStr, 0);
			int loginFailCount = systemInfo.getLoginFailCount();
			if (failCount >= loginFailCount) {
				// 账号登陆失败次数超出范围
				long ttl = redisUtil.ttl(loginFailCountKey);
				String time = "";
				int h = (int) (ttl / 3600);
				if (h == 0) {
					int m = (int) (ttl / 60);
					if (m == 0) {
						time = ttl + "秒后";
					} else {
						time = m + "分钟后";
					}
				} else {
					time = h + "小时后";
				}
				throw new ExcessiveAttemptsException(time);
			}
			if (!user.getLoginPass().equals(password)) {
				int loginFailExpired = systemInfo.getLoginFailExpired();
				failCount++;
				String fc = failCount + "";
				redisUtil.setex(loginFailCountKey, loginFailExpired, fc);
				// 登陆密码错误
				throw new IncorrectCredentialsException();
			}
			// 删除Redis登陆失败次数Key
			redisUtil.del(loginFailCountKey);
		} else {
			if (!user.getLoginPass().equals(password)) {
				// 登陆密码错误
				throw new IncorrectCredentialsException();
			}
		}

		user.setLastLoginTime(System.currentTimeMillis());
		userService.updateUser(user);

		return new SimpleAuthenticationInfo(user, password.toCharArray(), getName());
	}

}
