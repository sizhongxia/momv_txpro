package org.tm.pro.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tm.pro.entity.User;

public class AuthHandlerInterceptor extends HandlerInterceptorAdapter {

	private final String StarTimeKey = "tm_req_start_time";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 记录请求开始时间
		request.setAttribute(StarTimeKey, System.currentTimeMillis());

		Subject subject = SecurityUtils.getSubject();
		Object user = subject.getPrincipal();
		if (user == null) {
			throw new UnauthenticatedException();
		}
		
		request.setAttribute("userId", ((User) user).getId());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			modelAndView.addObject("__processing_time",
					System.currentTimeMillis() - (long) request.getAttribute(StarTimeKey));
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Console.log("请求总时间（含模板渲染）：{}", System.currentTimeMillis() - (long)
		// request.getAttribute(StarTimeKey));
		super.afterCompletion(request, response, handler, ex);
	}

}
