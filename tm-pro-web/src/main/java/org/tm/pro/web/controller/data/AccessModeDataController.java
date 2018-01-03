package org.tm.pro.web.controller.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tm.pro.elastic.model.AggregationEnum;
import org.tm.pro.elastic.model.RequestDateHistogram;
import org.tm.pro.elastic.model.RequestModel;
import org.tm.pro.elastic.model.RequestSubAggregation;
import org.tm.pro.elastic.model.ResponseAggrItem;
import org.tm.pro.elastic.model.ResponseModel;
import org.tm.pro.elastic.util.ElasticUtil;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;

@Controller
@RequestMapping("accessModeData")
public class AccessModeDataController extends BaseController {

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

		// 拼装请求参数
		RequestModel requestModel = new RequestModel();
		requestModel.setIndex(index);
		requestModel.setType(type);
		// 时间范围
		requestModel.setQmTimeField("@timestamp");
		requestModel.setQmTimeStart(startDate);
		requestModel.setQmTimeEnd(endDate);
		// 接入方式
		String accessMode = request.getParameter("accessMode");
		if (TmStringUtil.isBlank(accessMode)) {
			accessMode = null;
		}
		requestModel.setAccessMode(accessMode);
		// 基本聚合字段
		requestModel.setAggrTerms(new String[] { "status_code"});
		// 请求
		ResponseModel responseModel = elasticUtil.baseAggregationProportion(requestModel);
		// 判断是否请求成功
		if (!responseModel.isSuccess()) {
			arm.setMsg(responseModel.getErrMsg());
			return arm;
		}
		// 获取查询到的聚合数据
		Map<String, List<ResponseAggrItem>> items = responseModel.getAggrTerms();
		List<ResponseAggrItem> datas = items.get("status_code");
		
		if (datas == null || datas.isEmpty()) {
			return arm;
		}
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> _item = null;
		// 拼装数据
		for (ResponseAggrItem item : datas) {
			_item = new HashMap<>();
			int statusCode = new BigDecimal(item.getKeyAsString()).intValue();
			if (statusCode > 0) {
				_item.put("name", item.getKeyAsString());
				_item.put("type", statusCode >= 400 ? "状态码异常" : "状态码正常");
				_item.put("value", item.getDocCount());
				list.add(_item);
			}
		}
		// 排序key
		list.sort(new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return new BigDecimal(o1.get("name").toString()).intValue()
						- new BigDecimal(o2.get("name").toString()).intValue();
			}
		});
		// 成功返回数据
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

		// 拼装请求参数
		RequestModel requestModel = new RequestModel();
		requestModel.setIndex(index);
		requestModel.setType(type);
		// 时间范围
		requestModel.setQmTimeField("@timestamp");
		requestModel.setQmTimeStart(startDate);
		requestModel.setQmTimeEnd(endDate);

		// 接入方式
		String accessMode = request.getParameter("accessMode");
		if (TmStringUtil.isBlank(accessMode)) {
			accessMode = null;
		}
		requestModel.setAccessMode(accessMode);

		// 基本聚合字段
		requestModel.setAggrTerms(new String[] { "cache_stat" });
		// 请求
		ResponseModel responseModel = elasticUtil.baseAggregationProportion(requestModel);
		// 判断是否请求成功
		if (!responseModel.isSuccess()) {
			arm.setMsg(responseModel.getErrMsg());
			return arm;
		}
		// 获取查询到的聚合数据
		Map<String, List<ResponseAggrItem>> items = responseModel.getAggrTerms();
		List<ResponseAggrItem> datas = items.get("cache_stat");
		if (datas == null || datas.isEmpty()) {
			return arm;
		}
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> _item = null;
		// 拼装数据
		for (ResponseAggrItem item : datas) {
			_item = new HashMap<>();
			_item.put("name", item.getKeyAsString());
			_item.put("value", item.getDocCount());
			list.add(_item);
		}
		// 成功返回数据
		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/back-dspeed-statistics")
	public ApiResultMap backDspeedStatistics(HttpServletRequest request) {
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
		// 拼装请求参数
		RequestModel requestModel = new RequestModel();
		requestModel.setIndex(index);
		requestModel.setType(type);
		// 时间范围
		requestModel.setQmTimeField("@timestamp");
		requestModel.setQmTimeStart(startDate);
		requestModel.setQmTimeEnd(endDate);
		// 接入方式
		String accessMode = request.getParameter("accessMode");
		if (TmStringUtil.isBlank(accessMode)) {
			accessMode = null;
		}
		requestModel.setAccessMode(accessMode);

		// 时间范围聚合
		Set<RequestDateHistogram> dateHistograms = new HashSet<>();
		RequestDateHistogram dateHistogram = new RequestDateHistogram();
		dateHistogram.setField("@timestamp");
		dateHistogram.setInterval(DateHistogramInterval.minutes(5));
		List<RequestSubAggregation> subAggregations = new ArrayList<>();

		RequestSubAggregation subAggregation1 = new RequestSubAggregation();
		subAggregation1.setField("back_flow");
		subAggregation1.setType(AggregationEnum.SUM);
		subAggregations.add(subAggregation1);

		RequestSubAggregation subAggregation2 = new RequestSubAggregation();
		subAggregation2.setField("dspeed");
		subAggregation2.setType(AggregationEnum.AVG);
		subAggregations.add(subAggregation2);

		dateHistogram.setSubAggregations(subAggregations);
		dateHistograms.add(dateHistogram);
		requestModel.setDateHistograms(dateHistograms);

		// 请求
		ResponseModel responseModel = elasticUtil.baseAggregationProportion(requestModel);
		// 判断是否请求成功
		if (!responseModel.isSuccess()) {
			arm.setMsg(responseModel.getErrMsg());
			return arm;
		}
		// 获取查询到的聚合数据
		Map<String, List<ResponseAggrItem>> items = responseModel.getAggrDateHistograms();
		List<ResponseAggrItem> datas = items.get("@timestamp");
		if (datas == null || datas.isEmpty()) {
			return arm;
		}

		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> _item = null;
		Map<String, Double> _valueMap = null;

		DecimalFormat df = new DecimalFormat("#.00");
		// 拼装数据
		for (ResponseAggrItem item : datas) {
			_valueMap = item.getValues();
			if (_valueMap == null || _valueMap.isEmpty()) {
				continue;
			}
			Double dspeed = _valueMap.get("dspeed_avg");
			if (dspeed == null || dspeed.isInfinite() || dspeed.isNaN()) {
				continue;
			}
			Double back_flow = _valueMap.get("back_flow_sum");
			if (back_flow == null || back_flow.isInfinite() || back_flow.isNaN()) {
				continue;
			}
			_item = new HashMap<>();
			_item.put("date", item.getKeyAsString());
			BigDecimal v1 = new BigDecimal(dspeed);
			BigDecimal v2 = new BigDecimal(back_flow);
			_item.put("吐出流量", new Double(df.format(v1.divide(new BigDecimal(1024 * 1024), 2, RoundingMode.HALF_EVEN))));
			_item.put("回源流量",
					new Double(df.format(v2.divide(new BigDecimal(60 * 1024 * 1024), 2, RoundingMode.HALF_EVEN))));
			list.add(_item);
		}
		// 成功返回数据
		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/cache-visit-ntps-statistics")
	public ApiResultMap cacheVisitNtpsStatistics(HttpServletRequest request) {
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
		// 拼装请求参数
		RequestModel requestModel = new RequestModel();
		requestModel.setIndex(index);
		requestModel.setType(type);
		// 时间范围
		requestModel.setQmTimeField("@timestamp");
		requestModel.setQmTimeStart(startDate);
		requestModel.setQmTimeEnd(endDate);
		// 接入方式
		String accessMode = request.getParameter("accessMode");
		if (TmStringUtil.isBlank(accessMode)) {
			accessMode = null;
		}
		requestModel.setAccessMode(accessMode);

		// 时间范围聚合
		Set<RequestDateHistogram> dateHistograms = new HashSet<>();
		RequestDateHistogram dateHistogram = new RequestDateHistogram();
		dateHistogram.setField("@timestamp");
		dateHistogram.setInterval(DateHistogramInterval.minutes(5));
		dateHistograms.add(dateHistogram);
		requestModel.setDateHistograms(dateHistograms);

		// 请求
		ResponseModel responseModel = elasticUtil.baseAggregationProportion(requestModel);
		// 判断是否请求成功
		if (!responseModel.isSuccess()) {
			arm.setMsg(responseModel.getErrMsg());
			return arm;
		}
		// 获取查询到的聚合数据
		Map<String, List<ResponseAggrItem>> items = responseModel.getAggrDateHistograms();
		List<ResponseAggrItem> datas = items.get("@timestamp");
		if (datas == null || datas.isEmpty()) {
			return arm;
		}

		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> _item = null;
		// 拼装数据
		for (ResponseAggrItem item : datas) {
			_item = new HashMap<>();
			_item.put("date", item.getKeyAsString());
			_item.put("visitCount", item.getDocCount());
			list.add(_item);
		}
		// 成功返回数据
		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}
}
