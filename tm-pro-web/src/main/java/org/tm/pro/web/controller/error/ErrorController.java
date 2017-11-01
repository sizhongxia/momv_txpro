package org.tm.pro.web.controller.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.cache.SystemBaseInfoCacheUtil;
import org.tm.pro.web.controller.base.BaseController;

@Controller
@RequestMapping("ep")
public class ErrorController extends BaseController {

	public ErrorController() {
	}

	@RequestMapping(value = "/401")
	public ModelAndView ePage401(HttpServletRequest request) {
		return toErrorPage(request, "401");
	}

	@RequestMapping(value = "/404")
	public ModelAndView ePage404(HttpServletRequest request) {
		return toErrorPage(request, "404");
	}

	@RequestMapping(value = "/500")
	public ModelAndView ePage500(HttpServletRequest request) {
		return toErrorPage(request, "500");
	}

	private ModelAndView toErrorPage(HttpServletRequest request, String viewName) {
		ModelAndView view = new ModelAndView("epage/" + viewName);
		String referer = request.getHeader("Referer");
		view.addObject("referer", TmStringUtil.isBlank(referer) ? SystemBaseInfoCacheUtil.systemInfo.get("default_url") : referer);
		return view;
	}

}
