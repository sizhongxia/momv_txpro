package org.tm.pro.web.controller.manage;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.entity.Authorization;
import org.tm.pro.entity.Organization;
import org.tm.pro.entity.Role;
import org.tm.pro.entity.RoleAuthorization;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.model.AuthorizationModel;
import org.tm.pro.service.AuthorizationService;
import org.tm.pro.service.OrganizationService;
import org.tm.pro.service.RoleAuthorizationService;
import org.tm.pro.service.RoleService;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.cache.SystemInfoCacheUtil;
import org.tm.pro.web.controller.base.BaseController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

	@Autowired
	RoleService roleService;
	@Autowired
	RoleAuthorizationService roleAuthorizationService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	AuthorizationService authorizationService;

	@RequiresAuthentication
	@RequestMapping(value = "/index")
	@RequiresPermissions("auth_role_manager")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("role/index");
		List<Organization> organizations = organizationService.selectList(new EntityWrapper<>());
		mav.addObject("organizations", organizations);
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/list")
	public ApiResultMap list(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();

		Wrapper<Role> wrapper = new EntityWrapper<Role>();
		Integer organizationId = getParameter(request, "organizationId", 0);
		if (organizationId > 0) {
			wrapper.eq("organization_id", organizationId);
		}
		String roleName = getParameter(request, "roleName", "");
		if (TmStringUtil.isNotBlank(roleName)) {
			wrapper.like("role_name", roleName);
		}
		String roleCode = getParameter(request, "roleCode", "");
		if (TmStringUtil.isNotBlank(roleCode)) {
			wrapper.like("role_code", roleCode);
		}
		String usingState = getParameter(request, "usingState", "");
		if (TmStringUtil.isNotBlank(usingState)) {
			wrapper.eq("using_state", usingState);
		}
		arm.setList(roleService.selectList(wrapper));
		return arm;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("role/add");
		List<Organization> organizations = organizationService.selectList(new EntityWrapper<>());
		mav.addObject("organizations", organizations);
		return mav;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/auth")
	public ModelAndView auth(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView("role/auth");

		Role role = roleService.selectById(id);
		if (role == null) {
			// 无权访问
			mav.setViewName("redirect:/un_authorized.do");
			return mav;
		}

		mav.addObject("role", role);

		Set<String> authorizations = roleAuthorizationService.getRoleAuthorizations(id);

		mav.addObject("parentAuthorizations", SystemInfoCacheUtil.parentAuthorizations);

		Vector<AuthorizationModel> ams = new Vector<>();
		Vector<Authorization> cas = SystemInfoCacheUtil.childAuthorizations;
		AuthorizationModel am = null;
		for (Authorization a : cas) {
			am = new AuthorizationModel();
			am.setAuthorizationCode(a.getAuthorizationCode());
			am.setChecked(!authorizations.add(a.getAuthorizationCode()));
			am.setId(a.getId());
			am.setPid(a.getPid());
			am.setModuleIntroduce(a.getModuleIntroduce());
			am.setModuleName(a.getModuleName());
			ams.add(am);
		}
		mav.addObject("childAuthorizations", ams);
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/roleAuths")
	public ApiResultMap roleAuths(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {
		ApiResultMap arm = new ApiResultMap();
		return arm;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {

		ModelAndView mav = new ModelAndView("role/edit");
		Role role = roleService.selectById(id);
		if (role == null) {
			// 无权访问
			mav.setViewName("redirect:/un_authorized.do");
			return mav;
		}

		mav.addObject("role", role);
		List<Organization> organizations = organizationService.selectList(new EntityWrapper<>());
		mav.addObject("organizations", organizations);
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/save")
	public ApiResultMap save(HttpServletRequest request,
			@RequestParam(value = "roleName", required = true) String roleName,
			@RequestParam(value = "roleCode", required = true) String roleCode,
			@RequestParam(value = "organizationId", required = true) Integer organizationId,
			@RequestParam(value = "usingState", required = true) String usingState,
			@RequestParam(value = "roleExplain", required = true) String roleExplain) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		if (organizationId.intValue() < 1) {
			arm.setMsg("错误：请选择一个组织");
			return arm;
		}
		if (TmStringUtil.isBlank(roleName)) {
			arm.setMsg("错误：请输入角色名称");
			return arm;
		}
		if (TmStringUtil.isBlank(roleCode)) {
			arm.setMsg("错误：请输入角色编码");
			return arm;
		}

		Role entity = new Role();
		entity.setRoleCode(roleCode);
		Wrapper<Role> wrapper = new EntityWrapper<Role>(entity);
		Role role = roleService.selectOne(wrapper);
		if (role != null) {
			arm.setMsg("错误：角色编码已存在");
			return arm;
		}
		role = new Role();

		role.setOrganizationId(organizationId);
		role.setRoleCode(roleCode);
		role.setRoleName(roleName);
		role.setUsingState(usingState);
		role.setRoleExplain(roleExplain);

		if (roleService.insert(role)) {
			arm.setStatus(true);
			arm.setData(role);
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
			@RequestParam(value = "roleName", required = true) String roleName,
			@RequestParam(value = "roleCode", required = true) String roleCode,
			@RequestParam(value = "usingState", required = true) String usingState,
			@RequestParam(value = "roleExplain", required = true) String roleExplain) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		Role role = roleService.selectById(id);
		if (role == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}

		if (TmStringUtil.isBlank(roleName)) {
			arm.setMsg("错误：请输入角色名称");
			return arm;
		}
		if (TmStringUtil.isBlank(roleCode)) {
			arm.setMsg("错误：请输入角色编码");
			return arm;
		}
		Role entity = new Role();
		entity.setRoleCode(roleCode);
		Wrapper<Role> wrapper = new EntityWrapper<Role>(entity);
		Role _role = roleService.selectOne(wrapper);
		if (_role != null && _role.getId() != id) {
			arm.setMsg("错误：角色编码已存在");
			return arm;
		}

		role.setRoleCode(roleCode);
		role.setRoleName(roleName);
		role.setUsingState(usingState);
		role.setRoleExplain(roleExplain);

		if (roleService.updateById(role)) {
			arm.setStatus(true);
			arm.setData(role);
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

		Role role = roleService.selectById(id);
		if (role == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}
		if (roleService.deleteById(id)) {
			arm.setStatus(true);
			arm.setData(role);
			arm.setMsg("删除成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/toAuth")
	public ApiResultMap toAuth(HttpServletRequest request,
			@RequestParam(value = "roleId", required = true) Integer roleId,
			@RequestParam(value = "authorizationCode", required = true) String authorizationCode,
			@RequestParam(value = "checked", required = true) Boolean checked) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		Role role = roleService.selectById(roleId);
		if (role == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}

		Authorization authorization = SystemInfoCacheUtil.getAuthorizationByCode(authorizationCode);
		if (authorization == null) {
			
			Authorization entity = new Authorization();
			entity.setAuthorizationCode(authorizationCode);
			Wrapper<Authorization> wrapper = new EntityWrapper<Authorization>(entity);
			authorization = authorizationService.selectOne(wrapper );
			if (authorization == null) {
				arm.setMsg("错误：无效的权限字信息，请关闭当前窗口重试！");
				return arm;
			}
		}

		RoleAuthorization entity = new RoleAuthorization();
		entity.setRoleId(roleId);
		entity.setAuthorizationCode(authorizationCode);

		boolean res = false;
		String operation = "授权";
		try {
			if (checked) {
				res = roleAuthorizationService.insert(entity);
			} else {
				operation = "取消授权";
				Wrapper<RoleAuthorization> wrapper = new EntityWrapper<RoleAuthorization>(entity);
				res = roleAuthorizationService.delete(wrapper);
			}
		} catch (Exception e) {
			e.printStackTrace();
			arm.setMsg("错误：操作失败，请稍后重试");
			return arm;
		}
		if (res) {
			arm.setStatus(true);
			arm.setMsg("“" + authorization.getModuleName() + "”" + operation + "成功");
			return arm;
		}
		arm.setMsg("错误：未知错误");
		return arm;
	}

}
