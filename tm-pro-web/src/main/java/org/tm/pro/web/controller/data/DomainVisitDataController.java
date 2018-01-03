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
@RequestMapping("domainVisitData")
public class DomainVisitDataController extends BaseController {

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
		// 域名
		String domain = request.getParameter("domain");
		if (TmStringUtil.isBlank(domain)) {
			domain = null;
		}
		requestModel.setDomain(domain);
		
		// 基本聚合字段
		requestModel.setAggrTerms(new String[] { "domain" });
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
			_item.put("name", item.getKeyAsString());
			_item.put("value", item.getDocCount());
			list.add(_item);
		}
		// 成功返回数据
		arm.setStatus(true);
		arm.setList(list);
		return arm;
	}
}
