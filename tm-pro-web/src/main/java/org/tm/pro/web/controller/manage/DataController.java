package org.tm.pro.web.controller.manage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.model.SingleValueTimeDataModel;
import org.tm.pro.web.controller.base.BaseController;

@Controller
@RequestMapping("data")
public class DataController extends BaseController {

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/d1")
	public ApiResultMap d1(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();

		int num = 10000;
		long now = System.currentTimeMillis();
		List<SingleValueTimeDataModel> list = new ArrayList<>();
		SingleValueTimeDataModel item = null;
		long _n = 0;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < num; i++) {
			item = new SingleValueTimeDataModel();
			_n = now - (num - i) * 30000;
			item.setTime(df.format(new Date(_n)));
			item.setValue(Math.random() * 1000);
			list.add(item);
		}

		arm.setCode(200);
		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}

}
