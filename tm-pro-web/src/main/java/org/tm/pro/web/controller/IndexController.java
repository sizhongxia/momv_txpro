package org.tm.pro.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tm.pro.entity.User;
import org.tm.pro.service.UserService;

@Controller
public class IndexController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");
		User user = userService.getById(2);
		if (user != null) {
			System.out.println(user.getName());
		}
		mv.addObject("name", "welcome");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/data", method = { RequestMethod.GET })
	public Map<String, Object> data(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		User user = userService.getById(2);
		data.put("user", user);
		return data;
	}

}
