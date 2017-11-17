package org.tm.pro.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tm.pro.web.anno.UserAgentRecord;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

public class UserAgentHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			UserAgentRecord userAgent = ((HandlerMethod) handler).getMethodAnnotation(UserAgentRecord.class);
			if (userAgent != null) {
				UserAgent agent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

				OperatingSystem operatingSystem = agent.getOperatingSystem();
				if (operatingSystem != null) {
					request.setAttribute("operatingSystem", operatingSystem.getName());
				} else {
					request.setAttribute("operatingSystem", "Unknown");
				}
				Browser browser = agent.getBrowser();
				if (browser != null) {
					request.setAttribute("browser", browser.getName());
				} else {
					request.setAttribute("browser", "Unknown");
				}
				Version version = agent.getBrowserVersion();
				if (version != null) {
					request.setAttribute("browserVersion", version.getVersion());
				} else {
					request.setAttribute("browserVersion", "Unknown");
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

}
