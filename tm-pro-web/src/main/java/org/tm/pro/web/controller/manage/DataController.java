package org.tm.pro.web.controller.manage;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tm.pro.es.ElasticUtil;
import org.tm.pro.es.entity.EsSearchItem;
import org.tm.pro.es.entity.EsSearchResult;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.model.SingleValueTimeDataModel;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;

@Controller
@RequestMapping("data")
public class DataController extends BaseController {

	@Autowired
	ElasticUtil elasticUtil;

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/status-code-statistics")
	public ApiResultMap statusCodeStatistics(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		arm.setStatus(false);
		String index = request.getParameter("index");
		if (TmStringUtil.isBlank(index)) {
			index = "ppc_log_test*";
		}
		String type = request.getParameter("type");
		if (TmStringUtil.isBlank(type)) {
			type = "access";
		}
		String startDate = request.getParameter("startDate");
		if (TmStringUtil.isBlank(startDate)) {
			startDate = null;
		}
		String endDate = request.getParameter("endDate");
		if (TmStringUtil.isBlank(endDate)) {
			endDate = null;
		}
		String node = request.getParameter("node");
		if (TmStringUtil.isBlank(node)) {
			node = null;
		}
		EsSearchResult result = elasticUtil.statusCodeStatistics(index, type, startDate, endDate, node);

		List<EsSearchItem> datas = result.getDatas();
		if (datas == null || datas.isEmpty()) {
			return arm;
		}
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> _item = null;
		Map<String, Object> _itemf = new HashMap<>();
		for (EsSearchItem item : datas) {
			_item = new HashMap<>();
			int statusCode = new BigDecimal(item.getName()).intValue();
			if (statusCode < 0) {
				_itemf.put("name", item.getName());
				_itemf.put("type", "状态码异常");
				_itemf.put("value", item.getValue());
			} else {
				_item.put("name", item.getName());
				_item.put("type", statusCode >= 400 ? "状态码异常" : "状态码正常");
				_item.put("value", item.getValue());
				list.add(_item);
			}
		}
		list.sort(new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return new BigDecimal(o1.get("name").toString()).intValue()
						- new BigDecimal(o2.get("name").toString()).intValue();
			}
		});
		list.add(_itemf);
		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/cache-stat-statistics")
	public ApiResultMap cacheStatStatistics(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		arm.setStatus(false);
		String index = request.getParameter("index");
		if (TmStringUtil.isBlank(index)) {
			index = "ppc_log_test*";
		}
		String type = request.getParameter("type");
		if (TmStringUtil.isBlank(type)) {
			type = "access";
		}
		String startDate = request.getParameter("startDate");
		if (TmStringUtil.isBlank(startDate)) {
			startDate = null;
		}
		String endDate = request.getParameter("endDate");
		if (TmStringUtil.isBlank(endDate)) {
			endDate = null;
		}
		String node = request.getParameter("node");
		if (TmStringUtil.isBlank(node)) {
			node = null;
		}
		EsSearchResult result = elasticUtil.cacheStatStatistics(index, type, startDate, endDate, node);

		List<EsSearchItem> datas = result.getDatas();
		if (datas == null || datas.isEmpty()) {
			return arm;
		}
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> _item = null;
		for (EsSearchItem item : datas) {
			_item = new HashMap<>();
			_item.put("name", item.getName());
			_item.put("value", item.getValue());
			list.add(_item);
		}

		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/avg-dspeed-statistics")
	public ApiResultMap avgDspeedStatistics(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();
		arm.setCode(200);
		arm.setStatus(false);
		String index = request.getParameter("index");
		if (TmStringUtil.isBlank(index)) {
			index = "ppc_log_test*";
		}
		String type = request.getParameter("type");
		if (TmStringUtil.isBlank(type)) {
			type = "access";
		}
		String startDate = request.getParameter("startDate");
		if (TmStringUtil.isBlank(startDate)) {
			startDate = null;
		}
		String endDate = request.getParameter("endDate");
		if (TmStringUtil.isBlank(endDate)) {
			endDate = null;
		}
		String node = request.getParameter("node");
		if (TmStringUtil.isBlank(node)) {
			node = null;
		}
		EsSearchResult result = elasticUtil.avgDspeedStatistics(index, type, startDate, endDate, node);

		List<EsSearchItem> datas = result.getDatas();
		if (datas == null || datas.isEmpty()) {
			return arm;
		}
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> _item = null;
		DecimalFormat df = new DecimalFormat("#.00");
		for (EsSearchItem item : datas) {
			_item = new HashMap<>();
			_item.put("date", item.getName());
			_item.put("吐出流量", new Double(df.format(item.getValue().divide(new BigDecimal(1024 * 1024)))));
			_item.put("回源流量", new Double(0.00));
			list.add(_item);
		}
		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}
	
	
//	@ResponseBody
//	@RequiresAuthentication
//	@RequestMapping(value = "/avg-dspeed-statistics")
//	public ApiResultMap avgDspeedStatistics2(HttpServletRequest request) {
//		ApiResultMap arm = new ApiResultMap();
//		arm.setCode(200);
//		arm.setStatus(false);
//		String index = request.getParameter("index");
//		if (TmStringUtil.isBlank(index)) {
//			index = "ppc_log_test*";
//		}
//		String type = request.getParameter("type");
//		if (TmStringUtil.isBlank(type)) {
//			type = "access";
//		}
//		String startDate = request.getParameter("startDate");
//		if (TmStringUtil.isBlank(startDate)) {
//			startDate = null;
//		}
//		String endDate = request.getParameter("endDate");
//		if (TmStringUtil.isBlank(endDate)) {
//			endDate = null;
//		}
//		String node = request.getParameter("node");
//		if (TmStringUtil.isBlank(node)) {
//			node = null;
//		}
//		EsSearchResult result = elasticUtil.avgDspeedStatistics(index, type, startDate, endDate, node);
//
//		List<EsSearchItem> datas = result.getDatas();
//		if (datas == null || datas.isEmpty()) {
//			return arm;
//		}
//		List<Map<String, Object>> list = new ArrayList<>();
//		Map<String, Object> _item = null;
//		DecimalFormat df = new DecimalFormat("#.00");
//		for (EsSearchItem item : datas) {
//			_item = new HashMap<>();
//			_item.put("date", item.getName());
//			_item.put("吐出流量", new Double(df.format(item.getValue().divide(new BigDecimal(1024 * 1024)))));
//			_item.put("回源流量", new Double(0.00));
//			list.add(_item);
//		}
//		arm.setStatus(true);
//		arm.setList(list);
//		return arm;
//	}

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

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/line")
	public ApiResultMap line(HttpServletRequest request) {
		ApiResultMap arm = new ApiResultMap();

		int num = 100;
		long now = System.currentTimeMillis();
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> item = null;
		long _n = 0;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < num; i++) {
			item = new HashMap<>();
			_n = now - (num - i) * 1000;
			item.put("date", df.format(new Date(_n)));
			item.put("吐出流量", Math.random() * 1000);
			item.put("回源流量", Math.random() * 1000);
			list.add(item);
		}

		arm.setCode(200);
		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}

}
