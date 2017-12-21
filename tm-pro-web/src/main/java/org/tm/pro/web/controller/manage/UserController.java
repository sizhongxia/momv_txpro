package org.tm.pro.web.controller.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.entity.Organization;
import org.tm.pro.entity.Role;
import org.tm.pro.entity.User;
import org.tm.pro.entity.UserRole;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.service.OrganizationService;
import org.tm.pro.service.RoleService;
import org.tm.pro.service.UserRoleService;
import org.tm.pro.service.UserService;
import org.tm.pro.utils.TmDateUtil;
import org.tm.pro.utils.TmMd5Util;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	RoleService roleService;

	@RequiresAuthentication
	@RequestMapping(value = "/index")
	@RequiresPermissions("auth_user_manager")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("user/index");

		List<Organization> organizations = organizationService.selectList(new EntityWrapper<>());
		mav.addObject("organizations", organizations);

		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/list")
	public ApiResultMap list(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();

		Wrapper<User> wrapper = new EntityWrapper<>();
		String loginName = getParameter(request, "loginName", "");
		if (TmStringUtil.isNotBlank(loginName)) {
			wrapper.like("login_name", loginName);
		}
		String userName = getParameter(request, "userName", "");
		if (TmStringUtil.isNotBlank(userName)) {
			wrapper.like("username", userName);
		}
		String phone = getParameter(request, "phone", "");
		if (TmStringUtil.isNotBlank(phone)) {
			wrapper.like("phone", phone);
		}
		Integer organizationId = getParameter(request, "organizationId", 0);
		if (organizationId > 0) {
			wrapper.eq("organization_id", organizationId);
		}

		Integer page = getParameter(request, "page", 1);
		Integer size = getParameter(request, "size", 10);

		PageHelper.startPage(page, size);
		List<User> users = userService.selectList(wrapper);

		arm.setTotalPage(PageHelper.getPagination().getPages());
		arm.setList(users);
		return arm;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("user/add");

		List<Organization> organizations = organizationService.selectList(new EntityWrapper<>());
		mav.addObject("organizations", organizations);

		return mav;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/auth")
	public ModelAndView auth(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView("user/auth");

		User user = userService.selectById(id);
		if (user == null) {
			// 无权访问
			mav.setViewName("redirect:/un_authorized.do");
			return mav;
		}

		Organization organization = organizationService.selectById(user.getOrganizationId());
		mav.addObject("organization", organization);

		Role entity = new Role();
		entity.setOrganizationId(organization.getId());
		entity.setUsingState("Y");
		Wrapper<Role> wrapper = new EntityWrapper<Role>(entity);
		List<Role> roles = roleService.selectList(wrapper);
		mav.addObject("roles", roles);

		mav.addObject("userId", id);

		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/userRoles")
	public ApiResultMap userRoles(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ApiResultMap arm = new ApiResultMap();
		Set<Role> userRoles = userRoleService.getUserRoles(id);
		List<Role> userRoleList = new ArrayList<Role>();
		if (userRoles != null && !userRoles.isEmpty()) {
			for (Role r : userRoles) {
				userRoleList.add(r);
			}
		}
		arm.setList(userRoleList);
		return arm;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/detail")
	public ModelAndView detail(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {

		ModelAndView mav = new ModelAndView("user/detail");

		User user = userService.selectById(id);
		if (user == null) {
			// 无权访问
			mav.setViewName("redirect:/un_authorized.do");
			return mav;
		}

		mav.addObject("user", user);
		// 账号过期时间
		long expiredTime = user.getExpiredTime();
		if (expiredTime > 0) {
			mav.addObject("expiredTime", TmDateUtil.format(expiredTime, "yyyy-MM-dd HH:mm:ss"));
		} else {
			// 永不过期
			mav.addObject("expiredTime", "永不过期");
		}
		long lastLoginTime = user.getLastLoginTime();
		if (lastLoginTime > 0) {
			mav.addObject("lastLoginTime", TmDateUtil.format(lastLoginTime, "yyyy-MM-dd HH:mm:ss"));
		} else {
			// 永不过期
			mav.addObject("lastLoginTime", "未登录");
		}
		mav.addObject("createdTime", TmDateUtil.format(user.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
		mav.addObject("updatedTime", TmDateUtil.format(user.getUpdatedTime(), "yyyy-MM-dd HH:mm:ss"));

		List<Organization> organizations = organizationService.selectList(new EntityWrapper<>());
		mav.addObject("organizations", organizations);

		return mav;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {

		ModelAndView mav = new ModelAndView("user/edit");
		User user = userService.selectById(id);
		if (user == null) {
			// 无权访问
			mav.setViewName("redirect:/un_authorized.do");
			return mav;
		}

		mav.addObject("user", user);
		// 账号过期时间
		long expiredTime = user.getExpiredTime();
		if (expiredTime > 0) {
			mav.addObject("expiredTime", TmDateUtil.format(expiredTime, "yyyy-MM-dd"));
		} else {
			// 永不过期
			mav.addObject("expiredTime", "");
		}

		List<Organization> organizations = organizationService.selectList(new EntityWrapper<>());
		mav.addObject("organizations", organizations);

		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/save")
	public ApiResultMap save(HttpServletRequest request,
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "loginPass", required = true) String loginPass,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "organizationId", required = true) Integer organizationId,
			@RequestParam(value = "gender", required = true) String gender,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "lockedState", required = true) String lockedState,
			@RequestParam(value = "disabledState", required = true) String disabledState,
			@RequestParam(value = "expiredTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expiredTime) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		if (TmStringUtil.isBlank(username)) {
			arm.setMsg("错误：请输入用户名称");
			return arm;
		}
		if (TmStringUtil.isBlank(loginName)) {
			arm.setMsg("错误：请输入登陆名称");
			return arm;
		}
		if (TmStringUtil.isBlank(loginPass)) {
			arm.setMsg("错误：请输入登陆密码");
			return arm;
		}
		if (organizationId.intValue() < 1) {
			arm.setMsg("错误：请选择一个组织");
			return arm;
		}

		if (TmStringUtil.isNotBlank(phone)) {
			if (!TmStringUtil.isMobile(phone)) {
				arm.setMsg("错误：无效的手机号");
				return arm;
			}
		}

		if (TmStringUtil.isNotBlank(email)) {
			if (!TmStringUtil.isEmail(email)) {
				arm.setMsg("错误：无效的邮箱");
				return arm;
			}
		}

		User entity = new User();
		entity.setLoginName(loginName);
		Wrapper<User> wrapper = new EntityWrapper<User>(entity);
		User user = userService.selectOne(wrapper);
		if (user != null) {
			arm.setMsg("错误：登录名已存在");
			return arm;
		}
		user = new User();
		long nowTime = System.currentTimeMillis();
		user.setGender(gender);
		user.setLoginName(loginName);
		// 加密存储
		loginPass = TmMd5Util.md5(loginPass, loginName);
		user.setLoginPass(loginPass);

		user.setUsername(username);
		user.setPhone(phone);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setOrganizationId(organizationId);
		user.setLockedState(lockedState);
		user.setDisabledState(disabledState);
		user.setCreatedTime(nowTime);
		user.setUpdatedTime(nowTime);
		user.setLastLoginTime(0L);
		if (expiredTime == null) {
			user.setExpiredTime(0L);
		} else {
			user.setExpiredTime(expiredTime.getTime());
		}
		if (userService.insert(user)) {
			arm.setStatus(true);
			arm.setData(user);
			arm.setMsg("保存成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/update")
	public ApiResultMap update(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "loginPass", required = true) String loginPass,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "organizationId", required = true) Integer organizationId,
			@RequestParam(value = "gender", required = true) String gender,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "lockedState", required = true) String lockedState,
			@RequestParam(value = "disabledState", required = true) String disabledState,
			@RequestParam(value = "expiredTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expiredTime) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		User user = userService.selectById(id);
		if (user == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}

		if (TmStringUtil.isBlank(username)) {
			arm.setMsg("错误：请输入用户名称");
			return arm;
		}
		if (TmStringUtil.isBlank(loginName)) {
			arm.setMsg("错误：请输入登陆名称");
			return arm;
		}
		if (TmStringUtil.isBlank(loginPass)) {
			arm.setMsg("错误：请输入登陆密码");
			return arm;
		}
		if (organizationId.intValue() < 1) {
			arm.setMsg("错误：请选择一个组织");
			return arm;
		}

		if (TmStringUtil.isNotBlank(phone)) {
			if (!TmStringUtil.isMobile(phone)) {
				arm.setMsg("错误：无效的手机号");
				return arm;
			}
		}

		if (TmStringUtil.isNotBlank(email)) {
			if (!TmStringUtil.isEmail(email)) {
				arm.setMsg("错误：无效的邮箱");
				return arm;
			}
		}

		User entity = new User();
		entity.setLoginName(loginName);
		Wrapper<User> wrapper = new EntityWrapper<User>(entity);
		User _user = userService.selectOne(wrapper);
		if (_user != null && _user.getId() != id) {
			arm.setMsg("错误：登录名已存在");
			return arm;
		}
		long nowTime = System.currentTimeMillis();
		user.setGender(gender);
		user.setUsername(username);
		user.setPhone(phone);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setLockedState(lockedState);
		user.setDisabledState(disabledState);
		user.setUpdatedTime(nowTime);
		if (expiredTime == null) {
			user.setExpiredTime(0L);
		} else {
			user.setExpiredTime(expiredTime.getTime());
		}

		if (userService.updateById(user)) {
			arm.setStatus(true);
			arm.setData(user);
			arm.setMsg("修改成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/delete")
	public ApiResultMap delete(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		User user = userService.selectById(id);
		if (user == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}
		if (userService.deleteById(id)) {
			arm.setStatus(true);
			arm.setData(user);
			arm.setMsg("删除成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/removeRole")
	public ApiResultMap removeRole(HttpServletRequest request,
			@RequestParam(value = "roleId", required = true) Integer roleId,
			@RequestParam(value = "userId", required = true) Integer userId) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		User user = userService.selectById(userId);
		if (user == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}
		UserRole entity = new UserRole();
		entity.setUserId(userId);
		entity.setRoleId(roleId);
		Wrapper<UserRole> wrapper = new EntityWrapper<UserRole>(entity);
		if (userRoleService.delete(wrapper)) {
			arm.setStatus(true);
			arm.setData(user);
			arm.setMsg("删除成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/authRole")
	public ApiResultMap authRole(HttpServletRequest request,
			@RequestParam(value = "roleId", required = true) Integer roleId,
			@RequestParam(value = "userId", required = true) Integer userId) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		User user = userService.selectById(userId);
		if (user == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}
		if (userRoleService.checkUserRole(userId, roleId, user.getOrganizationId())) {
			arm.setMsg("错误：角色已授权");
			return arm;
		}
		UserRole entity = new UserRole();
		entity.setUserId(userId);
		entity.setRoleId(roleId);
		entity.setOrganizationId(user.getOrganizationId());
		if (userRoleService.insert(entity)) {
			arm.setStatus(true);
			arm.setData(user);
			arm.setMsg("授权成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

}
