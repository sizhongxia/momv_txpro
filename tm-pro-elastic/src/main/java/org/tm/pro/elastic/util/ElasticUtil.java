package org.tm.pro.elastic.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.tm.pro.elastic.factory.TransportClientFactory;
import org.tm.pro.elastic.model.RequestDateHistogram;
import org.tm.pro.elastic.model.RequestModel;
import org.tm.pro.elastic.model.RequestSubAggregation;
import org.tm.pro.elastic.model.ResponseAggrItem;
import org.tm.pro.elastic.model.ResponseModel;
import org.tm.pro.utils.TmStringUtil;

public class ElasticUtil {

	private TransportClientFactory transportClientFactory;

	public void setTransportClientFactory(TransportClientFactory transportClientFactory) {
		this.transportClientFactory = transportClientFactory;
	}

	public String baseJsonSearch(RequestModel requestModel) {
		try {
			return transportClientFactory.postBaseRequest(requestModel.getIndex(), requestModel.getType(),
					requestModel.getSearchJson());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 基本聚合查询
	public ResponseModel baseAggregationProportion(RequestModel requestModel) {
		ResponseModel rm = new ResponseModel();
		rm.setSuccess(false);

		String index = requestModel.getIndex();
		if (TmStringUtil.isBlank(index)) {
			rm.setErrMsg("无索引信息！");
			return rm;
		}

		String type = requestModel.getType();
		if (TmStringUtil.isBlank(type)) {
			rm.setErrMsg("无类别信息！");
			return rm;
		}

		String[] aggrTerms = requestModel.getAggrTerms();
		Set<RequestDateHistogram> dateHistograms = requestModel.getDateHistograms();

		// 获取客户端连接
		TransportClient client = transportClientFactory.getClient();

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		List<QueryBuilder> bools = boolQueryBuilder.must();

		// 时间范围
		String tField = requestModel.getQmTimeField();
		String ts = requestModel.getQmTimeStart();
		String te = requestModel.getQmTimeEnd();
		if (TmStringUtil.isNotBlank(tField) && (TmStringUtil.isNotBlank(ts) || TmStringUtil.isNotBlank(te))) {
			RangeQueryBuilder timeRQB = QueryBuilders.rangeQuery(tField);
			timeRQB.format("yyyy-MM-dd HH:mm:ss");
			timeRQB.includeLower(true).includeUpper(true);
			if (TmStringUtil.isNotBlank(ts)) {
				timeRQB.from(ts);
			}
			if (TmStringUtil.isNotBlank(te)) {
				timeRQB.to(te);
			}
			bools.add(timeRQB);
		}

		// 缓存设备IP
		String cacheDeviceIp = requestModel.getCacheDeviceIp();
		if (TmStringUtil.isNotBlank(cacheDeviceIp)) {
			bools.add(QueryBuilders.termQuery("cache_device_ip", cacheDeviceIp));
		}

		// 地区
		String ipipCity = requestModel.getIpipCity();
		if (TmStringUtil.isNotBlank(ipipCity)) {
			bools.add(QueryBuilders.termQuery("ipip.city", ipipCity));
		}

		// 域名
		String domain = requestModel.getDomain();
		if (TmStringUtil.isNotBlank(domain)) {
			bools.add(QueryBuilders.termQuery("domain", domain));
		}

		SearchRequestBuilder builder = client.prepareSearch(index).setTypes(type);
		builder.setQuery(boolQueryBuilder);
		builder.setFrom(requestModel.getFrom()).setSize(requestModel.getSize());

		if (aggrTerms != null && aggrTerms.length > 0) {
			for (String aggrTerm : aggrTerms) {
				builder.addAggregation(AggregationBuilders.terms(aggrTerm).field(aggrTerm));
			}
		}

		// 时间范围聚合
		if (dateHistograms != null && dateHistograms.size() > 0) {
			for (RequestDateHistogram dateHistogram : dateHistograms) {

				// 时间范围聚合
				DateHistogramAggregationBuilder dhaBuilder = AggregationBuilders
						.dateHistogram(dateHistogram.getField());
				dhaBuilder.field(dateHistogram.getField());
				dhaBuilder.dateHistogramInterval(dateHistogram.getInterval());
				dhaBuilder.format(getTimeFormatByInterval(dateHistogram.getInterval()));

				// 子聚合
				List<RequestSubAggregation> subAggregations = dateHistogram.getSubAggregations();
				if (subAggregations != null && subAggregations.size() > 0) {
					for (RequestSubAggregation subAggregation : subAggregations) {

						String field = subAggregation.getField();
						String format = subAggregation.getFormat();

						// 子聚合类别
						switch (subAggregation.getType()) {
						case AVG:
							AvgAggregationBuilder avgBuilder = AggregationBuilders.avg(field + "_avg");
							avgBuilder.field(field);
							if (TmStringUtil.isNotBlank(format)) {
								avgBuilder.format(format);
							}
							dhaBuilder.subAggregation(avgBuilder);
							break;
						case SUM:
							SumAggregationBuilder sumBuilder = AggregationBuilders.sum(field + "_sum");
							sumBuilder.field(field);
							if (TmStringUtil.isNotBlank(format)) {
								sumBuilder.format(format);
							}
							dhaBuilder.subAggregation(sumBuilder);
							break;
						case MAX:
							MaxAggregationBuilder maxBuilder = AggregationBuilders.max(field + "_max");
							maxBuilder.field(field);
							if (TmStringUtil.isNotBlank(format)) {
								maxBuilder.format(format);
							}
							dhaBuilder.subAggregation(maxBuilder);
							break;
						case MIN:
							MinAggregationBuilder minBuilder = AggregationBuilders.min(field + "_min");
							minBuilder.field(field);
							if (TmStringUtil.isNotBlank(format)) {
								minBuilder.format(format);
							}
							dhaBuilder.subAggregation(minBuilder);
							break;
						}

					}
				}

				builder.addAggregation(dhaBuilder);
			}
		}

		SearchResponse sr = builder.get();

		if (aggrTerms != null && aggrTerms.length > 0) {

			Map<String, List<ResponseAggrItem>> aggrTermMap = new HashMap<>();
			List<ResponseAggrItem> aggrTermItems = null;
			ResponseAggrItem aggrTermItem = null;

			for (String aggrTerm : aggrTerms) {
				aggrTermItems = new ArrayList<>();
				Terms agg = sr.getAggregations().get(aggrTerm);
				List<Terms.Bucket> bs = agg.getBuckets();
				for (Terms.Bucket b : bs) {
					aggrTermItem = new ResponseAggrItem();
					aggrTermItem.setKeyAsString(b.getKeyAsString());
					aggrTermItem.setKey(b.getKey());
					aggrTermItem.setDocCount(b.getDocCount());
					aggrTermItems.add(aggrTermItem);
				}
				aggrTermMap.put(aggrTerm, aggrTermItems);
			}

			rm.setAggrTerms(aggrTermMap);
		}

		if (dateHistograms != null && dateHistograms.size() > 0) {

			Map<String, List<ResponseAggrItem>> aggrDateHistogramMap = new HashMap<>();
			List<ResponseAggrItem> aggrTermItems = null;
			ResponseAggrItem aggrTermItem = null;

			for (RequestDateHistogram dateHistogram : dateHistograms) {
				aggrTermItems = new ArrayList<>();
				InternalDateHistogram agg = sr.getAggregations().get(dateHistogram.getField());
				List<InternalDateHistogram.Bucket> bs = agg.getBuckets();
				for (InternalDateHistogram.Bucket b : bs) {
					aggrTermItem = new ResponseAggrItem();
					aggrTermItem.setKeyAsString(b.getKeyAsString());
					aggrTermItem.setKey(b.getKey());
					aggrTermItem.setDocCount(b.getDocCount());

					Map<String, Double> values = new HashMap<>();
					List<RequestSubAggregation> requestSubAggregations = dateHistogram.getSubAggregations();
					if (requestSubAggregations != null && !requestSubAggregations.isEmpty()) {
						Aggregations subAggregations = b.getAggregations();
						for (RequestSubAggregation requestSubAggregation : requestSubAggregations) {

							String field = requestSubAggregation.getField();
							switch (requestSubAggregation.getType()) {
							case AVG:
								field = field + "_avg";
								InternalAvg avg = subAggregations.get(field);
								values.put(field, avg.getValue());
								break;
							case SUM:
								field = field + "_sum";
								InternalSum sum = subAggregations.get(field);
								values.put(field, sum.getValue());
								break;
							case MAX:
								field = field + "_max";
								InternalMax max = subAggregations.get(field);
								values.put(field, max.getValue());
								break;
							case MIN:
								field = field + "_min";
								InternalMin min = subAggregations.get(field);
								values.put(field, min.getValue());
								break;
							}
						}
					}
					aggrTermItem.setValues(values);
					aggrTermItems.add(aggrTermItem);
				}
				aggrDateHistogramMap.put(dateHistogram.getField(), aggrTermItems);
			}
			rm.setAggrDateHistograms(aggrDateHistogramMap);
		}

		rm.setSuccess(true);
		return rm;
	}

	private String getTimeFormatByInterval(DateHistogramInterval interval) {
		if (interval == null) {
			throw new RuntimeException();
		}
		String intervalStr = interval.toString();
		String lastChar = intervalStr.substring(intervalStr.length() - 1, intervalStr.length());
		String format = "yyyy-MM-dd";
		switch (lastChar) {
		case "s":
			format = "yyyy-MM-dd HH:mm:ss";
			break;
		case "m":
			format = "yyyy-MM-dd HH:mm";
			break;
		case "h":
			format = "yyyy-MM-dd HH";
			break;
		case "d":
			format = "yyyy-MM-dd";
			break;
		case "w":
			format = "yyyy-MM-dd";
			break;
		case "M":
			format = "yyyy-MM";
			break;
		case "q":
			format = "yyyy-MM";
			break;
		case "y":
			format = "yyyy";
			break;
		}
		return format;
	}
}
