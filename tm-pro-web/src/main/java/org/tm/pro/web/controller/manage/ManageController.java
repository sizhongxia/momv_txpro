package org.tm.pro.web.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.web.controller.base.BaseController;

import com.google.code.kaptcha.Constants;

@Controller
@RequestMapping("manage")
public class ManageController extends BaseController {

	@RequiresAuthentication
	@RequestMapping(value = "/index")
	@RequiresPermissions("system_menu_manage_index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("manage/index");
		mv.addObject("userId", request.getAttribute("userId"));

		// 菜单信息
		mv.addObject("currentMenu", "manage_index");
		return mv;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/users")
	@RequiresPermissions("system_menu_manage_users")
	public ModelAndView users(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("manage/users");
		mv.addObject("userId", request.getAttribute("userId"));

		Subject subject = SecurityUtils.getSubject();
		mv.addObject("vcode", subject.getSession(true).getAttribute(Constants.KAPTCHA_SESSION_KEY));
		// 菜单信息
		mv.addObject("currentMenu", "manage_users");
		return mv;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/notices")
	@RequiresPermissions("system_menu_manage_notices")
	public ModelAndView notices(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("manage/notices");
		mv.addObject("userId", request.getAttribute("userId"));

		// 菜单信息
		mv.addObject("currentMenu", "manage_notices");
		return mv;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/system")
	@RequiresPermissions("system_menu_manage_system")
	public ModelAndView system(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("manage/system");
		mv.addObject("userId", request.getAttribute("userId"));
		// 菜单信息
		mv.addObject("currentMenu", "manage_system");
		return mv;
	}

	@RequiresAuthentication
	@RequiresRoles("admin")
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("manage/index");
		mv.addObject("userId", "save");
		return mv;
	}

}
