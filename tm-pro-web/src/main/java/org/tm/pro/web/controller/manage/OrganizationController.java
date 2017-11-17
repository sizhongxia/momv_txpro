package org.tm.pro.web.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.entity.Organization;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.service.OrganizationService;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;

@Controller
@RequestMapping("organization")
public class OrganizationController extends BaseController {

	@Autowired
	OrganizationService organizationService;

	@RequiresAuthentication
	@RequestMapping(value = "/index")
	@RequiresPermissions("auth_organization_manager")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("organization/index");
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/list")
	public ApiResultMap list(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();

		Map<String, Object> params = new HashMap<>();

		params.put("organizationName", getParameter(request, "organizationName", ""));

		long count = organizationService.getOrganizationCount(params);

		Integer page = getParameter(request, "page", 1);
		Integer size = getParameter(request, "size", 10);

		int totalPage = (int) (count % size == 0 ? count / size : (count / size + 1));
		arm.setTotalPage(totalPage);

		if (count > 0) {
			List<Organization> organizations = organizationService.getOrganizationList(params, page, size);
			arm.setList(organizations);
		}

		return arm;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("organization/add");
		return mav;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id) {

		ModelAndView mav = new ModelAndView("organization/edit");
		Organization organization = organizationService.getById(id);
		if (organization == null) {
			// 无权访问
			mav.setViewName("redirect:/un_authorized.do");
			return mav;
		}

		mav.addObject("organization", organization);
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/save")
	public ApiResultMap save(HttpServletRequest request,
			@RequestParam(value = "organizationName", required = true) String organizationName,
			@RequestParam(value = "organizationDesc", required = true) String organizationDesc,
			@RequestParam(value = "sortNumber", required = true) Integer sortNumber) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		if (TmStringUtil.isBlank(organizationName)) {
			arm.setMsg("错误：请输入组织机构名称");
			return arm;
		}
		if (sortNumber == null || sortNumber.intValue() < 1) {
			sortNumber = 100;
		}

		Organization organization = new Organization();
		organization.setOrganizationName(organizationName);
		organization.setSortNumber(sortNumber);
		organization.setOrganizationDesc(organizationDesc);
		int id = 0;
		try {
			id = organizationService.insert(organization);
		} catch (Exception e) {
			e.printStackTrace();
			arm.setMsg("错误：保存失败");
			return arm;
		}
		if (id > 0) {
			arm.setStatus(true);
			arm.setData(organization);
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
			@RequestParam(value = "organizationName", required = true) String organizationName,
			@RequestParam(value = "organizationDesc", required = true) String organizationDesc,
			@RequestParam(value = "sortNumber", required = true) Integer sortNumber) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		Organization organization = organizationService.getById(id);
		if (organization == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}

		if (TmStringUtil.isBlank(organizationName)) {
			arm.setMsg("错误：请输入组织机构名称");
			return arm;
		}
		if (sortNumber == null || sortNumber.intValue() < 1) {
			sortNumber = 100;
		}
		organization.setOrganizationName(organizationName);
		organization.setSortNumber(sortNumber);
		organization.setOrganizationDesc(organizationDesc);

		int res = 0;
		try {
			res = organizationService.update(organization);
		} catch (Exception e) {
			e.printStackTrace();
			arm.setMsg("错误：修改失败");
			return arm;
		}
		if (res > 0) {
			arm.setStatus(true);
			arm.setData(organization);
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

		Organization organization = organizationService.getById(id);
		if (organization == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}
		int res = 0;
		try {
			res = organizationService.delete(organization);
		} catch (Exception e) {
			e.printStackTrace();
			arm.setMsg("错误：删除失败");
			return arm;
		}
		if (res > 0) {
			arm.setStatus(true);
			arm.setData(organization);
			arm.setMsg("删除成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

}
