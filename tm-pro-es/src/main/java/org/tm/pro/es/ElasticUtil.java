package org.tm.pro.es;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ElasticUtil {

	private String baseUrl;

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseRequest(String index, String type, String param) throws Exception {
		StringBuffer sb = new StringBuffer(baseUrl);
		if (index != null && !"".equals(index.trim())) {
			sb.append(index);
			if (type != null && !"".equals(type.trim())) {
				sb.append("/").append(type);
			}
		}
		if (param != null && !"".equals(param.trim())) {
			sb.append("?").append(param);
		}
		HttpClient client = HttpClients.createDefault();
		URI uri = new URI(sb.toString());
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = client.execute(httpGet);
		return EntityUtils.toString(response.getEntity());
	}

	public String postBaseRequest(String index, String type, String json) throws Exception {
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

	public Map<String, Long> getHttpStatusInfo(String index, String type) {
		Map<String, Long> data = new LinkedHashMap<>();
		String json = "{" + 
				"  \"query\": {" + 
				"    \"bool\":{" + 
				"      \"must_not\": [{" + 
				"          \"terms\": {" + 
				"            \"status_code\": [" + 
				"            ]" + 
				"          }" + 
				"        } " + 
				"      ]" + 
				"    }" + 
				"  }," + 
				"  \"size\": 0," + 
				"  \"aggs\": {" + 
				"    \"types\": {" + 
				"      \"terms\": {" + 
				"        \"field\": \"status_code\"" +
				"      }" + 
				"    }" + 
				"  }" + 
				"}";

		String result = null;
		try {
			result = postBaseRequest(index, type, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result == null) {
			return data;
		}

		JSONObject jsonObj = JSONObject.parseObject(result);
		if (jsonObj != null) {
			JSONObject aggregations = jsonObj.getJSONObject("aggregations");
			JSONObject types = aggregations.getJSONObject("types");
			JSONArray buckets = types.getJSONArray("buckets");
			buckets.forEach(new Consumer<Object>() {
				@Override
				public void accept(Object obj) {
					JSONObject item = (JSONObject) obj;
					data.put(item.getString("key"), item.getLong("doc_count"));
				}
			});
		}
		return data;
	}
	
	public Map<String, Object> getBackFlowInfo(String index, String type) {
		Map<String, Object> data = new HashMap<>();
		String json = "{" + 
				"  \"query\": {" + 
				"    \"bool\": {" + 
				"      \"must\": [" + 
				"        {" + 
				"          \"terms\": {" + 
				"            \"cache_stat\": [" + 
				"              \"MISS\"," + 
				"             \"HIT\" " + 
				"            ]" + 
				"          }" + 
				"        }" + 
				"      ]" + 
				"    }" + 
				"  }," + 
				"  \"aggs\": {" + 
				"    \"cache_stat_group_by\": {" + 
				"      \"terms\": {" + 
				"        \"field\": \"cache_stat\"" + 
				"      }," + 
				"      \"aggs\" : {" + 
				"        \"sum_back_flow\" : {" + 
				"          \"sum\" : { " + 
				"            \"field\" : \"back_flow\"" + 
				"          }" + 
				"        }," + 
				"        \"sum_out_flow\" : {" + 
				"           \"sum\" : { " + 
				"             \"field\" : \"out_flow\"" + 
				"           }" + 
				"        }" + 
				"      }" + 
				"    }," + 
				"    \"sum_back_flow\" : {" + 
				"      \"sum\" : { " + 
				"        \"field\" : \"back_flow\"" + 
				"      }" + 
				"    }," + 
				"    \"sum_out_flow\" : {" + 
				"       \"sum\" : { " + 
				"         \"field\" : \"out_flow\"" + 
				"       }" + 
				"    }" + 
				"  }," + 
				"  \"size\": 0" + 
				"}";

		String result = null;
		try {
			result = postBaseRequest(index, type, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result == null) {
			return data;
		}

		JSONObject jsonObj = JSONObject.parseObject(result);
		if (jsonObj != null) {
			JSONObject aggregations = jsonObj.getJSONObject("aggregations");
			JSONObject sumOutFlow = aggregations.getJSONObject("sum_out_flow");
			data.put("sumOutFlow", sumOutFlow.getLongValue("value"));
			JSONObject sumBackFlow = aggregations.getJSONObject("sum_back_flow");
			data.put("sumBackFlow", sumBackFlow.getLongValue("value"));
			
			JSONObject cacheStatGroupBy = aggregations.getJSONObject("cache_stat_group_by");
			
			JSONArray buckets = cacheStatGroupBy.getJSONArray("buckets");
			buckets.forEach(new Consumer<Object>() {
				@Override
				public void accept(Object obj) {
					Map<String, Object> _item = new HashMap<>();
					JSONObject item = (JSONObject) obj;
					_item.put("sumOutFlow", item.getJSONObject("sum_out_flow").getLongValue("value"));
					_item.put("sumBackFlow", item.getJSONObject("sum_back_flow").getLongValue("value"));
					data.put(item.getString("key"), _item);
				}
			});
		}
		return data;
	}
	
	public Map<String, Long> getCacheStatusInfo(String index, String type) {
		Map<String, Long> data = new HashMap<>();
		String json = "{" + 
				"  \"query\": {" + 
				"    \"bool\":{" + 
				"    }" + 
				"  }," + 
				"  \"size\": 0," + 
				"  \"aggs\": {" + 
				"    \"types\": {" + 
				"      \"terms\": {" + 
				"        \"field\": \"cache_stat\"" + 
				"      }" + 
				"    }" + 
				"  }" + 
				"}";

		String result = null;
		try {
			result = postBaseRequest(index, type, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result == null) {
			return data;
		}

		JSONObject jsonObj = JSONObject.parseObject(result);
		if (jsonObj != null) {
			JSONObject aggregations = jsonObj.getJSONObject("aggregations");
			JSONObject types = aggregations.getJSONObject("types");
			JSONArray buckets = types.getJSONArray("buckets");
			buckets.forEach(new Consumer<Object>() {
				@Override
				public void accept(Object obj) {
					JSONObject item = (JSONObject) obj;
					data.put(item.getString("key"), item.getLong("doc_count"));
				}
			});
		}
		return data;
	}
	
	public Map<String, Long> getUrlInfo(String index, String type) {
		Map<String, Long> data = new HashMap<>();
		String json = "{" + 
				"  \"query\": {" + 
				"    \"bool\":{" + 
				"    }" + 
				"  }," + 
				"  \"size\": 0," + 
				"  \"aggs\": {" + 
				"    \"types\": {" + 
				"      \"terms\": {" + 
				"        \"field\": \"url\"" + 
				"      }" + 
				"    }" + 
				"  }" + 
				"}";

		String result = null;
		try {
			result = postBaseRequest(index, type, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result == null) {
			return data;
		}

		JSONObject jsonObj = JSONObject.parseObject(result);
		if (jsonObj != null) {
			JSONObject aggregations = jsonObj.getJSONObject("aggregations");
			JSONObject types = aggregations.getJSONObject("types");
			JSONArray buckets = types.getJSONArray("buckets");
			buckets.forEach(new Consumer<Object>() {
				@Override
				public void accept(Object obj) {
					JSONObject item = (JSONObject) obj;
					data.put(item.getString("key"), item.getLong("doc_count"));
				}
			});
		}
		return data;
	}
/**
 * GET /ppc_log_test-20170930/access/_search
{
  "query": {
    "bool":{
    }
  },
  "size": 1,
  "aggs": {
    "types": {
      "terms": {
        "field": "domain"
      },
      "aggs": {
        "cache_stat": {
          "terms": {
            "field": "domain"
          }
        },
        "sum_out_flow": {
          "sum": {
            "field": "out_flow"
          }
        },
        "sum_hit_out_flow": {
          "sum": {
            "field": "out_flow"
          }
        }
      }
    }
  }
}
 */
}