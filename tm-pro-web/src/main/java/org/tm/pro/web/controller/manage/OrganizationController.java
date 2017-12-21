package org.tm.pro.web.controller.manage;

import java.util.List;

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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;

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

		Wrapper<Organization> wrapper = new EntityWrapper<Organization>();
		String organizationName = getParameter(request, "organizationName", "");
		if (TmStringUtil.isNotBlank(organizationName)) {
			wrapper.like("organization_name", organizationName);
		}

		Integer page = getParameter(request, "page", 1);
		Integer size = getParameter(request, "size", 10);

		PageHelper.startPage(page, size);
		List<Organization> organizations = organizationService.selectList(wrapper);

		arm.setTotalPage(PageHelper.getPagination().getPages());
		arm.setList(organizations);
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
		Organization organization = organizationService.selectById(id);
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
		if (organizationService.insert(organization)) {
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

		Organization organization = organizationService.selectById(id);
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

		if (organizationService.updateById(organization)) {
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

		Organization organization = organizationService.selectById(id);
		if (organization == null) {
			arm.setMsg("错误：无效的ID");
			return arm;
		}
		if (organizationService.deleteById(id)) {
			arm.setStatus(true);
			arm.setData(organization);
			arm.setMsg("删除成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

}
