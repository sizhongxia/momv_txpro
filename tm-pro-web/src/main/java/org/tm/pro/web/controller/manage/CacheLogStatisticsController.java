package org.tm.pro.web.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.service.DictionaryService;
import org.tm.pro.web.controller.base.BaseController;

@Controller
@RequestMapping("cache-log-statistics")
public class CacheLogStatisticsController extends BaseController {

	@Autowired
	DictionaryService dictionaryService;

	// 全局统计
	@RequiresAuthentication
	@RequestMapping(value = "/global")
	public ModelAndView global(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/global");
		mav.addObject("page", "global");

		mav.addObject("manufacturers", dictionaryService.getItemsByVCode("SYSTEM_MANUFACTURERS"));
		mav.addObject("requestHttpUrlTypes", dictionaryService.getItemsByVCode("REQUEST_HTTP_URL_TYPE"));
		mav.addObject("cacheSystemNodes", dictionaryService.getItemsByVCode("CACHE_SYSTEM_NODE"));

		return mav;
	}

	// 地市统计
	@RequiresAuthentication
	@RequestMapping(value = "/local")
	public ModelAndView local(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/local");
		
		mav.addObject("manufacturers", dictionaryService.getItemsByVCode("SYSTEM_MANUFACTURERS"));
		mav.addObject("requestHttpUrlTypes", dictionaryService.getItemsByVCode("REQUEST_HTTP_URL_TYPE"));
		mav.addObject("cacheSystemCitys", dictionaryService.getItemsByVCode("CACHE_SYSTEM_CITY"));

		return mav;
	}

	// 接入方式统计
	@RequiresAuthentication
	@RequestMapping(value = "/access-mode")
	public ModelAndView accessMode(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/access_mode");

		mav.addObject("manufacturers", dictionaryService.getItemsByVCode("SYSTEM_MANUFACTURERS"));
		mav.addObject("requestHttpUrlTypes", dictionaryService.getItemsByVCode("REQUEST_HTTP_URL_TYPE"));
		mav.addObject("cacheSystemEthernets", dictionaryService.getItemsByVCode("CACHE_SYSTEM_ETHERNET"));

		return mav;
	}

	// 域名访问统计
	@RequiresAuthentication
	@RequestMapping(value = "/domain-visit-statistics")
	public ModelAndView domainVsit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/domain_visit_statistics");
		mav.addObject("page", "domain-visit-statistics");
		return mav;
	}

	// 文档类型统计
	@RequiresAuthentication
	@RequestMapping(value = "/document-type-statistics")
	public ModelAndView documentType(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/document_type_statistics");
		mav.addObject("page", "document-type-statistics");
		return mav;
	}

	// 请求方式统计
	@RequiresAuthentication
	@RequestMapping(value = "/request-method-statistics")
	public ModelAndView requestMethod(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/request_method_statistics");
		mav.addObject("page", "request-method-statistics");
		return mav;
	}

	// 请求状态统计
	@RequiresAuthentication
	@RequestMapping(value = "/http-status-statistics")
	public ModelAndView httpStatusCode(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/http_status_statistics");
		mav.addObject("page", "http-status-statistics");
		return mav;
	}

	// 节点质量统计
	@RequiresAuthentication
	@RequestMapping(value = "/node-quality-statistics")
	public ModelAndView nodeQuality(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/node_quality_statistics");
		mav.addObject("page", "node-quality-statistics");
		return mav;
	}

	// 用户访问统计
	@RequiresAuthentication
	@RequestMapping(value = "/user-visit-statistics")
	public ModelAndView userVisit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/user_visit_statistics");
		mav.addObject("page", "user-visit-statistics");
		return mav;
	}

	// 域名类型统计
	@RequiresAuthentication
	@RequestMapping(value = "/domain-type-statistics")
	public ModelAndView domainType(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/domain_type_statistics");
		mav.addObject("page", "domain-type-statistics");
		return mav;
	}

	// 网站访问统计
	@RequiresAuthentication
	@RequestMapping(value = "/web-visit-statistics")
	public ModelAndView webVisit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cache_log_statistics/web_visit_statistics");
		mav.addObject("page", "web-visit-statistics");
		return mav;
	}

}
