package org.tm.pro.web.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tm.pro.enums.TmErrCode;
import org.tm.pro.utils.TmJsonUtil;
import org.tm.pro.web.utils.RequestUtil;

public class BaseController {
	/**
	 * 认证（未登录）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
	public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.isAjaxRequest(request)) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", TmErrCode.RequestUnauthentication.getCode());
			map.put("msg", TmErrCode.RequestUnauthentication.getMsg());
			writeJson(map, response);
			return null;
		} else {
			return "redirect:/login";
		}
	}

	/**
	 * 授权（未授权）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
	public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.isAjaxRequest(request)) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", TmErrCode.RequestUnauthorization.getCode());
			map.put("msg", TmErrCode.RequestUnauthorization.getMsg());
			writeJson(map, response);
			return null;
		} else {
			return "redirect:/ep/401";
		}
	}

	private void writeJson(Map<String, Object> map, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			out = response.getWriter();
			out.write(TmJsonUtil.mapToJson(map));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
