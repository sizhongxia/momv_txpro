package org.tm.pro.web.controller.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.tm.pro.elastic.model.RequestModel;
import org.tm.pro.elastic.util.ElasticUtil;
import org.tm.pro.model.ApiResultMap;
import org.tm.pro.utils.TmNumberUtil;
import org.tm.pro.utils.TmStringUtil;
import org.tm.pro.web.controller.base.BaseController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("domainVisitData")
public class DomainVisitDataController extends BaseController {

	@Autowired
	ElasticUtil elasticUtil;

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/data")
	public ApiResultMap data(HttpServletRequest request) {
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

		// 拼装请求参数
		RequestModel requestModel = new RequestModel();
		requestModel.setIndex(index);
		requestModel.setType(type);

		String size = request.getParameter("size");
		long top = TmNumberUtil.toLong(size, 10);
		if (top < 1) {
			top = Long.MAX_VALUE;
		}
		// 域名
		String domain = request.getParameter("domain");
		String query = "";
		if (TmStringUtil.isNotBlank(domain)) {
			query = "\"query\": {" + "	  \"bool\": {" + "	    \"must\": [" + "	      {"
					+ "	        \"match_phrase\": {" + "	          \"domain\": {"
					+ "  						\"query\": \"" + domain + "\"" + "  					}"
					+ "	        }" + "	      }" + "	    ]" + "	  }" + "	}, ";
		}
		String requestJson = "{" + "	\"from\": 0," + "	\"size\": 0," + query + "	 \"aggregations\": {"
				+ "      \"domain\" : {" + "        \"terms\": {" + "          \"field\": \"domain\", \"size\":" + top
				+ "        }," + "        \"aggs\": {" + "          \"cache_stat\": {" + "            \"terms\": {"
				+ "              \"field\": \"cache_stat\"" + "            }," + "            \"aggs\": {"
				+ "              \"out_flow\": {" + "                \"sum\": {"
				+ "                  \"field\": \"out_flow\"" + "                }" + "              }"
				+ "            }" + "          }," + "          \"status_code\": {" + "            \"terms\": {"
				+ "              \"field\": \"status_code\"" + "            }" + "          },"
				+ "          \"flow\": {" + "            \"sum\": {" + "              \"field\": \"out_flow\""
				+ "            }" + "          }," + "          \"dspeed\" : {" + "            \"avg\": {"
				+ "              \"field\": \"dspeed\"" + "            }" + "          }" + "        }" + "      }"
				+ "   }" + "}";
		requestModel.setSearchJson(requestJson);
		// 请求
		String response = elasticUtil.baseJsonSearch(requestModel);
		try {
			Map<String, Object> data = analysis(response);
			arm.setStatus(true);
			arm.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			arm.setMsg(e.getMessage());
		}
		// 成功返回数据
		return arm;
	}

	@ResponseBody
	@RequiresAuthentication
	@RequestMapping(value = "/detail")
	public ApiResultMap detail(HttpServletRequest request) {
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

		// 拼装请求参数
		RequestModel requestModel = new RequestModel();
		requestModel.setIndex(index);
		requestModel.setType(type);

		String size = request.getParameter("size");
		long top = TmNumberUtil.toLong(size, 200);
		if (top < 1) {
			top = Long.MAX_VALUE;
		}
		// 域名
		String domain = request.getParameter("domain");
		String query = "";
		if (TmStringUtil.isNotBlank(domain)) {
			query = "\"query\": {" + "	  \"bool\": {" + "	    \"must\": [" + "	      {"
					+ "	        \"match_phrase\": {" + "	          \"domain\": {"
					+ "  						\"query\": \"" + domain + "\"" + "  					}"
					+ "	        }" + "	      }" + "	    ]" + "	  }" + "	}, ";
		}
		String requestJson = "{" + "	\"from\": 0," + "	\"size\": 0," + query + "	 \"aggregations\": {"
				+ "      \"domain\" : {" + "        \"terms\": {" + "          \"field\": \"url\", \"size\":" + top
				+ "        }," + "        \"aggs\": {" + "          \"cache_stat\": {" + "            \"terms\": {"
				+ "              \"field\": \"cache_stat\"" + "            }," + "            \"aggs\": {"
				+ "              \"out_flow\": {" + "                \"sum\": {"
				+ "                  \"field\": \"out_flow\"" + "                }" + "              }"
				+ "            }" + "          }," + "          \"status_code\": {" + "            \"terms\": {"
				+ "              \"field\": \"status_code\"" + "            }" + "          },"
				+ "          \"flow\": {" + "            \"sum\": {" + "              \"field\": \"out_flow\""
				+ "            }" + "          }," + "          \"dspeed\" : {" + "            \"avg\": {"
				+ "              \"field\": \"dspeed\"" + "            }" + "          }" + "        }" + "      }"
				+ "   }" + "}";
		requestModel.setSearchJson(requestJson);
		// 请求
		String response = elasticUtil.baseJsonSearch(requestModel);
		try {
			Map<String, Object> data = analysis(response);
			arm.setStatus(true);
			arm.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			arm.setMsg(e.getMessage());
		}
		// 成功返回数据
		return arm;
	}
	
	
	/**
	 * @throws Exception
	 */
	private Map<String, Object> analysis(String json) throws Exception {
		Map<String, Object> data = new HashMap<>();
		JSONObject result = JSONObject.parseObject(json);
		boolean timedOut = result.getBooleanValue("timed_out");
		// 请求超时
		if (timedOut) {
			throw new Exception("请求Es服务超时");
		}
		JSONObject hits = result.getJSONObject("hits");
		Long hitTotal = hits.getLong("total");
		if (hitTotal == null) {
			throw new Exception("请求的数据无效");
		}
		if (hitTotal.longValue() == 0) {
			throw new Exception("未请求到数据");
		}
		// System.out.println("命中文档总数：" + hitTotal);
		data.put("hitTotal", hitTotal);

		JSONObject aggregations = result.getJSONObject("aggregations");

		JSONObject domain = aggregations.getJSONObject("domain");

		long docCountErrorUpperBound = domain.getLongValue("doc_count_error_upper_bound");
		// System.out.println("文档总数统计错误上限值：" + docCountErrorUpperBound);
		data.put("docCountErrorUpperBound", docCountErrorUpperBound);

		List<Map<String, Object>> domains = new ArrayList<>();
		Map<String, Object> domainItem = null;

		DecimalFormat df = new DecimalFormat("#0.00");

		JSONArray buckets = domain.getJSONArray("buckets");

		int size = buckets.size();
		if (size > 0) {
			JSONObject item = null;

			JSONObject itemStatusCode = null;
			JSONArray statusCodeBuckets = null;

			JSONObject itemCacheStat = null;
			JSONArray cacheStatBuckets = null;

			JSONObject flow = null;
			JSONObject dspeed = null;

			for (int index = 0; index < size; index++) {
				domainItem = new HashMap<>();
				// 域名统计信息
				item = buckets.getJSONObject(index);

				String domainName = item.getString("key");
				// System.out.println("当前解析的域名：" + domainName);
				domainItem.put("domainName", domainName);

				long visitCount = item.getLongValue("doc_count");
				// System.out.println("当前域名请求次数：" + visitCount);
				domainItem.put("visitCount", visitCount);

				itemStatusCode = item.getJSONObject("status_code");
				statusCodeBuckets = itemStatusCode.getJSONArray("buckets");
				long visitSuccessCount = 0;
				long visitFailCount = 0;
				int statusCodeBucketSize = statusCodeBuckets.size();
				for (int m = 0; m < statusCodeBucketSize; m++) {
					JSONObject mItem = statusCodeBuckets.getJSONObject(m);
					int statusCode = mItem.getIntValue("key");
					long statusCodeCount = mItem.getLongValue("doc_count");
					if (statusCode > 0 && statusCode < 400) {
						visitSuccessCount += statusCodeCount;
					} else {
						visitFailCount += statusCodeCount;
					}
				}
				// System.out.println("当前域名请求成功次数：" + visitSuccessCount);
				domainItem.put("visitSuccessCount", visitSuccessCount);
				// System.out.println("当前域名请求失败次数：" + visitFailCount);
				domainItem.put("visitFailCount", visitFailCount);

				BigDecimal bd_visitCount = new BigDecimal(visitCount);
				BigDecimal bd_visitSuccessCount = new BigDecimal(visitSuccessCount * 100);

				if (visitCount > 0) {
					// System.out.println("当前域名请求成功率：" + bd_visitSuccessCount.divide(bd_visitCount,
					// 2, RoundingMode.FLOOR));
					domainItem.put("visitSuccessRate",
							bd_visitSuccessCount.divide(bd_visitCount, 2, RoundingMode.FLOOR));
				} else {
					domainItem.put("visitSuccessRate", 0);
				}

				itemCacheStat = item.getJSONObject("cache_stat");
				cacheStatBuckets = itemCacheStat.getJSONArray("buckets");
				long hitCount = 0;
				long missCount = 0;

				double hitOutFlow = 0d;
				double missOutFlow = 0d;

				int cacheStatBucketSize = cacheStatBuckets.size();
				for (int m = 0; m < cacheStatBucketSize; m++) {
					JSONObject mItem = cacheStatBuckets.getJSONObject(m);
					String cacheStat = mItem.getString("key");
					long cacheStatCount = mItem.getLongValue("doc_count");

					JSONObject outFlowItem = mItem.getJSONObject("out_flow");
					double outFlowValue = outFlowItem.getDoubleValue("value");

					switch (cacheStat) {
					case "HIT":
						hitCount += cacheStatCount;
						hitOutFlow += outFlowValue;
						break;
					case "MISS":
						missCount += cacheStatCount;
						missOutFlow += outFlowValue;
						break;
					}
				}
				// System.out.println("当前域名请求HIT次数：" + hitCount);
				domainItem.put("hitCount", hitCount);
				// System.out.println("当前域名请求MISS次数：" + missCount);
				domainItem.put("missCount", missCount);

				BigDecimal bd_hitCount = new BigDecimal(hitCount * 100);
				if (visitCount > 0) {
					// System.out.println("当前域名请求命中率：" + bd_hitCount.divide(bd_visitCount, 2,
					// RoundingMode.FLOOR));
					domainItem.put("hitRate", df.format(bd_hitCount.divide(bd_visitCount, 2, RoundingMode.FLOOR)));
				} else {
					domainItem.put("hitRate", 0);
				}

				flow = item.getJSONObject("flow");
				double flowValue = flow.getDoubleValue("value");
				// System.out.println("当前域名请求总流量：" + flowValue / 1024 / 1024);
				BigDecimal bd_flowValue = new BigDecimal(flowValue / 1024 / 1024);
				domainItem.put("flowValue", df.format(bd_flowValue));

				// System.out.println("当前域名请求HIT总流量：" + hitOutFlow / 1024 / 1024);
				BigDecimal bd_hitOutFlow = new BigDecimal(hitOutFlow / 1024 / 1024);
				domainItem.put("hitOutFlow", df.format(bd_hitOutFlow));
				// System.out.println("当前域名请求MISS总流量：" + missOutFlow / 1024 / 1024);
				BigDecimal bd_missOutFlow = new BigDecimal(missOutFlow / 1024 / 1024);
				domainItem.put("missOutFlow", df.format(bd_missOutFlow));

				if (flowValue > 0) {
					// System.out.println("当前域名流量命中占比：" + hitOutFlow * 100 / flowValue);
					domainItem.put("hitOutFlowRate", df.format(
							bd_hitOutFlow.multiply(new BigDecimal(100)).divide(bd_flowValue, 2, RoundingMode.FLOOR)));
				} else {
					domainItem.put("hitOutFlowRate", 0);
				}

				dspeed = item.getJSONObject("dspeed");
				double dspeedValue = dspeed.getDoubleValue("value");
				// System.out.println("当前域名平均下载速度：" + dspeedValue / 1024);
				domainItem.put("dspeed", df.format(dspeedValue / 1024));
				domainItem.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				domains.add(domainItem);
			}
			data.put("domains", domains);
		}
		return data;
	}

}
