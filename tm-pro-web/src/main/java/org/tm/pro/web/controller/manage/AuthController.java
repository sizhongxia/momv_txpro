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
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.entity.LoginLog;
import org.tm.pro.service.LoginLogService;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.anno.UserAgentRecord;
import org.tm.pro.web.controller.base.BaseController;

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
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/login.do");

		String username = request.getParameter("un");
		if (TmStringUtil.isBlank(username)) {
			mav.addObject("msg", "请输入登录名称");
			return mav;
		}

		String password = request.getParameter("pw");
		if (TmStringUtil.isBlank(password)) {
			mav.addObject("msg", "请输入登录密码");
			return mav;
		}
		// Console.log("username: {}, password: {}", username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(false);

		Subject subject = SecurityUtils.getSubject();

		String msg = "";
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误.";
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多";
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
			mav.setViewName("redirect:/manage/index.do");
		} else {
			mav.addObject("msg", msg);
		}

		return mav;
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
