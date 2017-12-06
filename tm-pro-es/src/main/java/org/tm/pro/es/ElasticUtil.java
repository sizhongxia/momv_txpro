package org.tm.pro.es;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.function.Consumer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.tm.pro.es.entity.EsSearchItem;
import org.tm.pro.es.entity.EsSearchResult;
import org.tm.pro.utils.TmNumberUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ElasticUtil {

	private String baseUrl;

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	private String postBaseRequest(String index, String type, String json) throws Exception {
		StringBuffer sb = new StringBuffer(baseUrl);
		sb.append(index).append("/").append(type).append("/_search");
		HttpClient client = HttpClients.createDefault();
		URI uri = new URI(sb.toString());
		HttpPost httpPost = new HttpPost(uri);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
		StringEntity se = new StringEntity(json);
		httpPost.setEntity(se);
		HttpResponse response = client.execute(httpPost);
		return EntityUtils.toString(response.getEntity());
	}

	public EsSearchResult statusCodeStatistics(String index, String type, String startDate, String endDate,
			String cacheDeviceIp) {
		EsSearchResult data = new EsSearchResult();
		data.setDatas(new ArrayList<>());
		String json = ConstantDSL.queryAggsStatusCode(startDate, endDate, cacheDeviceIp);
		String result = null;
		try {
			result = postBaseRequest(index, type, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null) {
			return data;
		}
		return parseObject(data, result);
	}

	public EsSearchResult cacheStatStatistics(String index, String type, String startDate, String endDate,
			String cacheDeviceIp) {
		EsSearchResult data = new EsSearchResult();
		data.setDatas(new ArrayList<>());
		String json = ConstantDSL.queryAggsCacheStat(startDate, endDate, cacheDeviceIp);
		String result = null;
		try {
			result = postBaseRequest(index, type, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null) {
			return data;
		}
		return parseObject(data, result);
	}

	public EsSearchResult avgDspeedStatistics(String index, String type, String startDate, String endDate,
			String cacheDeviceIp) {
		EsSearchResult data = new EsSearchResult();
		data.setDatas(new ArrayList<>());
		String json = ConstantDSL.queryAvgDspeed(startDate, endDate, cacheDeviceIp);
		String result = null;
		try {
			result = postBaseRequest(index, type, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null) {
			return data;
		}
		return parseAvgObject(data, result);
	}

	public EsSearchResult cachePreSecondStatistics(String index, String type, String startDate, String endDate,
			String cacheDeviceIp) {
		EsSearchResult data = new EsSearchResult();
		data.setDatas(new ArrayList<>());
		String json = ConstantDSL.queryCachePreSecond(startDate, endDate, cacheDeviceIp);
		String result = null;
		try {
			result = postBaseRequest(index, type, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null) {
			return data;
		}
		return parseObject(data, result);
	}

	private EsSearchResult parseAvgObject(EsSearchResult data, String result) {
		JSONObject jsonObj = JSONObject.parseObject(result);
		if (jsonObj == null) {
			return data;
		}
		JSONObject aggregations = jsonObj.getJSONObject("aggregations");
		if (aggregations == null) {
			return data;
		}
		JSONObject statistic = aggregations.getJSONObject("av");
		if (statistic == null) {
			return data;
		}
		JSONArray buckets = statistic.getJSONArray("buckets");
		if (buckets == null) {
			return data;
		}
		buckets.forEach(new Consumer<Object>() {
			@Override
			public void accept(Object obj) {
				if (obj != null) {
					try {
						JSONObject item = (JSONObject) obj;
						JSONObject _obj = item.getJSONObject("av");
						if (_obj != null) {
							String value = _obj.getString("value");
							if (TmNumberUtil.isNumber(value)) {
								EsSearchItem _item = new EsSearchItem();
								_item.setName(item.getString("key_as_string"));
								_item.setValue(new BigDecimal(value));
								data.getDatas().add(_item);
							}
						}
					} catch (Exception e) {
					}
				}
			}
		});
		return data;
	}

	private EsSearchResult parseObject(EsSearchResult data, String result) {
		JSONObject jsonObj = JSONObject.parseObject(result);
		if (jsonObj == null) {
			return data;
		}
		JSONObject aggregations = jsonObj.getJSONObject("aggregations");
		if (aggregations == null) {
			return data;
		}
		JSONObject statistic = aggregations.getJSONObject("gb");
		if (statistic == null) {
			return data;
		}
		JSONArray buckets = statistic.getJSONArray("buckets");
		if (buckets == null) {
			return data;
		}
		buckets.forEach(new Consumer<Object>() {
			@Override
			public void accept(Object obj) {
				if (obj != null) {
					try {
						JSONObject item = (JSONObject) obj;
						String value = item.getString("doc_count");
						if (TmNumberUtil.isNumber(value)) {
							EsSearchItem _item = new EsSearchItem();
							if (item.getString("key_as_string") != null) {
								_item.setName(item.getString("key_as_string"));
							} else {
								_item.setName(item.getString("key"));
							}
							_item.setValue(new BigDecimal(value));
							data.getDatas().add(_item);
						}
					} catch (Exception e) {
					}
				}
			}
		});
		return data;
	}
}