package org.tm.pro.web.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("x-requested-with");
		return requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest");
	}

}
