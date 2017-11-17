package org.tm.pro.web.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.web.cache.SystemAppEventCacheUtil;
import org.tm.pro.web.controller.base.BaseController;
import org.tm.pro.web.event.TmApplicationEvent;

@Controller
@RequestMapping("publisher")
public class PublisherController extends BaseController implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	public PublisherController() {
	}

	@ResponseBody
	@RequiresAuthentication
	@RequiresPermissions("auth_publisher_manager")
	@RequestMapping(value = "/todo")
	public ApiResultMap todo(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		// 默认失败
		arm.setStatus(false);
		String key = request.getParameter("key");
		if (!SystemAppEventCacheUtil.hasKey(key)) {
			arm.setMsg("错误：无效的Key信息");
			return arm;
		} else {
			applicationEventPublisher.publishEvent(new TmApplicationEvent(key));
			arm.setStatus(true);
			arm.setMsg("更新成功");
			return arm;
		}
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

}
