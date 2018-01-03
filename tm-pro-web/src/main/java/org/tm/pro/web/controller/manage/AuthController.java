package org.tm.pro.web.controller.manage;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tm.pro.entity.LoginLog;
import org.tm.pro.service.LoginLogService;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmMd5Util;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.anno.UserAgentRecord;
import org.tm.pro.web.controller.base.BaseController;

import com.google.code.kaptcha.Constants;

@Controller
@RequestMapping("auth")
public class AuthController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	LoginLogService loginLogService;

	public AuthController() {
	}

	@UserAgentRecord
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) {

		String referer = request.getParameter("referer");
		if (TmStringUtil.isBlank(referer)) {
			referer = "/index.do";
		}

		String[] rm = referer.split("[?]");
		if (rm.length > 1) {
			referer = "/operation_timeout.do";
		} else {
			referer = rm[0];
		}

		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return "redirect:" + referer;
		}

		Session session = subject.getSession(true);

		String verifyCode = request.getParameter("vc");
		if (TmStringUtil.isBlank(verifyCode)) {
			session.setAttribute("login_fail_msg", "请输入图片验证码");
			return "redirect:/login.do";
		}

		Object KAPTCHA_verifyCode = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (KAPTCHA_verifyCode == null) {
			session.setAttribute("login_fail_msg", "无效的图片验证码");
			return "redirect:/login.do";
		}
		
		if(!verifyCode.equals(KAPTCHA_verifyCode)) {
			session.setAttribute("login_fail_msg", "图片验证码输入错误");
			return "redirect:/login.do";
		}

		String username = request.getParameter("un");
		if (TmStringUtil.isBlank(username)) {
			session.setAttribute("login_fail_msg", "请输入登录名称");
			return "redirect:/login.do";
		}

		String password = request.getParameter("pw");
		if (TmStringUtil.isBlank(password)) {
			session.setAttribute("login_fail_msg", "请输入登录密码");
			return "redirect:/login.do";
		}

		UsernamePasswordToken token = new UsernamePasswordToken(username, TmMd5Util.md5(password, username));
		token.setRememberMe(false);

		String msg = "";
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误.";
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多，请" + e.getMessage() + "重试";
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定.";
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用.";
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期.";
		} catch (UnknownAccountException e) {
			msg = "帐号不存在.";
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权.";
		}
		if (TmStringUtil.isBlank(msg)) {
			msg = "登录成功";
		}
		// 设置登录日志信息
		setLoginLog(request, username, password, msg);

		if (subject.isAuthenticated()) {
			return "redirect:" + referer;
		} else {
			session.setAttribute("login_fail_msg", msg);
			return "redirect:/login.do";
		}
	}

	private void setLoginLog(HttpServletRequest request, String username, String password, String msg) {
		LoginLog loginLog = new LoginLog();
		loginLog.setLoginName(username);
		loginLog.setLoginPass(password);
		loginLog.setOperatingSystem(request.getAttribute("operatingSystem").toString());
		loginLog.setBrowser(request.getAttribute("browser").toString());
		loginLog.setBrowserVersion(request.getAttribute("browserVersion").toString());
		loginLog.setLoginResult(msg);
		loginLog.setLoginTime(new Date());
		loginLogService.insert(loginLog);
	}
}
