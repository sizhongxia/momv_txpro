package org.tm.pro.web.controller.manage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tm.pro.web.cache.SystemAppEventCacheUtil;
import org.tm.pro.web.controller.base.BaseController;
import org.tm.pro.web.event.TmApplicationEvent;

@Controller
@RequestMapping("manage")
public class PublisherController extends BaseController implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	public PublisherController() {
	}

	@ResponseBody
	@RequestMapping(value = "/publisher", method = { RequestMethod.GET })
	public Map<String, Object> index(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		String key = request.getParameter("key");
		if (!SystemAppEventCacheUtil.hasKey(key)) {
			data.put("status", "Invalid Key");
		} else {
			applicationEventPublisher.publishEvent(new TmApplicationEvent(key));
			data.put("status", "OK");
		}
		return data;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

}
