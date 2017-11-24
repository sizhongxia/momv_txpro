package org.tm.pro.web.controller.manage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.entity.SystemInfo;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.service.SystemInfoService;
import org.tm.pro.web.cache.SystemInfoCacheUtil;
import org.tm.pro.web.controller.base.BaseController;

@Controller
@RequestMapping("system")
public class SystemController extends BaseController {

	@Autowired
	SystemInfoService systemInfoService;

	public SystemController() {
	}

	@RequiresAuthentication
	@RequestMapping(value = "/illustrate", method = { RequestMethod.GET })
	public ModelAndView illustrate(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("system/illustrate");
		return mav;
	}

	@RequiresAuthentication
	@RequiresPermissions("auth_job_manager")
	@RequestMapping(value = "/quartz", method = { RequestMethod.GET })
	public ModelAndView quartz(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("system/quartz");
		return mav;
	}

	@RequiresAuthentication
	@RequiresPermissions("auth_system_info_manager")
	@RequestMapping(value = "/system_info", method = { RequestMethod.GET })
	public ModelAndView systemInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("system/system_info");
		SystemInfo systemInfo = systemInfoService.getDefaultInfo();
		mav.addObject("systemInfo", systemInfo);
		return mav;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequiresPermissions("auth_system_info_manager")
	@RequestMapping(value = "/update")
	public ApiResultMap update(HttpServletRequest request,
			@RequestParam(value = "systemTitle", required = true) String systemTitle,
			@RequestParam(value = "systemDescript", required = true) String systemDescript,
			@RequestParam(value = "loginFailCount", required = true) Integer loginFailCount,
			@RequestParam(value = "loginFailExpired", required = true) Integer loginFailExpired,
			@RequestParam(value = "loginFailLimit", required = true) String loginFailLimit,
			@RequestParam(value = "onlyChrome", required = true) String onlyChrome) {

		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);

		SystemInfo systemInfo = systemInfoService.getDefaultInfo();
		systemInfo.setSystemTitle(systemTitle);
		systemInfo.setSystemDescript(systemDescript);
		systemInfo.setLoginFailCount(loginFailCount);
		systemInfo.setLoginFailExpired(loginFailExpired);
		systemInfo.setOnlyChrome(onlyChrome);
		systemInfo.setLoginFailLimit(loginFailLimit);
		int res = 0;
		try {
			res = systemInfoService.updateSystemInfo(systemInfo);
		} catch (Exception e) {
			e.printStackTrace();
			arm.setMsg("错误：修改失败");
			return arm;
		}
		if (res > 0) {
			arm.setStatus(true);
			arm.setData(systemInfo);
			arm.setMsg("修改成功");
		} else {
			arm.setMsg("错误：未知原因");
		}
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/cache_data")
	@RequiresPermissions("auth_publisher_manager")
	public ApiResultMap cacheData(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		arm.setStatus(true);
		SystemInfo systemCacheInfo = SystemInfoCacheUtil.systemInfo;
		Map<String, Object> data = new HashMap<>();

		if ("Y".equals(systemCacheInfo.getLoginFailLimit())) {
			data.put("loginFailLimit", "启动限制");
		} else {
			data.put("loginFailLimit", "无限制");
		}
		if ("Y".equals(systemCacheInfo.getOnlyChrome())) {
			data.put("onlyChrome", "仅允许Chrome浏览器登陆");
		} else {
			data.put("onlyChrome", "无限制");
		}
		data.put("loginFailCount", systemCacheInfo.getLoginFailCount());
		data.put("loginFailExpired", systemCacheInfo.getLoginFailExpired());
		data.put("systemTitle", systemCacheInfo.getSystemTitle());
		data.put("systemDescript", systemCacheInfo.getSystemDescript());
		arm.setData(data);
		return arm;
	}

}
