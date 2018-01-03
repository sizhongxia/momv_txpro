//package org.tm.pro.elastic;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
//import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;
//import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
//import org.tm.pro.elastic.factory.TransportClientFactory;
//
//public class TestElasticSearchUtil {
//
//	public static void main(String[] args) {
//		
//		
//		
//		TransportClient client = TransportClientFactory.getClient();
//
//		// XContentBuilder builder = null;
//		//
//		// try {
//		// builder = jsonBuilder().startObject().field("ua", "Yunfan Android
//		// 1.0.0.28").endObject();
//		// } catch (IOException e) {
//		// e.printStackTrace();
//		// }
//		// if (builder == null) {
//		// throw new RuntimeException("builder fail!");
//		// }
//		// IndexResponse response = client.prepareIndex("ppc_log_test-20170930",
//		// "access").setSource(builder).get();
//		//
//		// System.out.println(response.getIndex());
//		//
//		// RestStatus status = response.status();
//		//
//		// System.out.println(status.name());
//		// System.out.println(status.ordinal());
//		// System.out.println(status.getStatus());
//
//		// QueryBuilder qb = termQuery("ua", "Yunfan Android 1.0.0.28");
//		// SearchResponse scrollResp = client.prepareSearch("ppc_log_test-20170930")
//		// .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC).setScroll(new
//		// TimeValue(60000)).setQuery(qb)
//		// .setSize(100).get();
//		// do {
//		// for (SearchHit hit : scrollResp.getHits().getHits()) {
//		// System.out.println(hit.getSource());
//		// }
//		// scrollResp =
//		// client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new
//		// TimeValue(60000)).execute()
//		// .actionGet();
//		// } while (scrollResp.getHits().getHits().length != 0);
//
//		Calendar c1 = Calendar.getInstance();
//		// 2017-09-30 12:54:12
//		c1.set(2017, 8, 1, 12, 54, 13);
//		Calendar c2 = Calendar.getInstance();
//		c2.set(2017, 9, 30, 12, 54, 13);
//
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		/**
//		 * 
//		 * 
//		 * StringBuffer sb = new StringBuffer();
//		 * sb.append("{\"from\":0,\"size\":0,\"query\":{\"bool\":{\"must\":[{\"range\":{\"@timestamp\":{\"gte\":\"");
//		 * sb.append(startDate); sb.append(" 00:00:00\",\"lte\":\"");
//		 * sb.append(endDate); sb.append( " 23:59:59\",\"format\":\"yyyy-MM-dd
//		 * HH:mm:ss\",\"include_lower\":false,\"include_upper\":true}}},{\"match_phrase\":{\"cache_device_ip\":{\"query\":\"");
//		 * sb.append(cacheDeviceIp); sb.append(
//		 * "\"}}}]}},\"aggregations\":{\"gb\":{\"terms\":{\"field\":\"status_code\"}}}}");
//		 * 
//		 * 
//		 */
//
//		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//
//		List<QueryBuilder> bools = boolQueryBuilder.must();
//
//		bools.add(QueryBuilders.rangeQuery("@timestamp").format("yyyy-MM-dd HH:mm:ss").from(df.format(c1.getTime()))
//				.to(df.format(c2.getTime())).includeLower(true).includeUpper(true));
//
//		// bools.add(QueryBuilders.matchPhraseQuery("cache_device_ip", ""));
//
//		bools.add(QueryBuilders.termQuery("cache_device_ip", "10.255.248.49"));
//		bools.add(QueryBuilders.termQuery("method", "GET"));
//		bools.add(QueryBuilders.termQuery("domain", "alimov2.a.yximgs.com"));
//		// bools.add(QueryBuilders.queryStringQuery("com").field("domain"));
//
//		// bools.add(QueryBuilders.prefixQuery("", ""));
//
//		// bools.add(QueryBuilders.spanTermQuery(name, value));
//		// bools.add(QueryBuilders.regexpQuery("", "regexp"));
//
//		System.out.println(boolQueryBuilder.toString());
//
//		SearchResponse sr = client.prepareSearch("ppc_log_test-*").setTypes("access").setQuery(boolQueryBuilder)
//				.setFrom(0).setSize(1).
//				// addAggregation(AggregationBuilders.terms("status_code").field("status_code")).
//				addAggregation(AggregationBuilders.dateHistogram("timestamp").field("@timestamp")
//						.dateHistogramInterval(DateHistogramInterval.minutes(5)).format("yyyy-MM-dd HH:mm")
//						.subAggregation(AggregationBuilders.avg("dspeed").field("dspeed")))
//						// .addAggregation(
//						// AggregationBuilders.dateHistogram("agg2")
//						// .field("birth")
//						// .dateHistogramInterval(DateHistogramInterval.YEAR)
//						// )
//				.get();
//
//		// System.out.println(sr.toString());
//
//		System.out.println(sr.getHits().getTotalHits());
//		SearchHits hits = sr.getHits();
//		SearchHit[] hitss = hits.getHits();
//
//		for (SearchHit hit : hitss) {
//			Map<String, Object> map = hit.getSourceAsMap();
//
//			for (Entry<String, Object> item : map.entrySet()) {
//				System.out.println(item.getKey() + ": " + item.getValue());
//			}
//			// // SearchHitField ss1 = hit.getFields().get("_source");
//			// // SearchHitField ss2 = hit.getFields().get("source");
//			// // SearchHitField ss3 = hit.getFields().get("_routing");
//			// // System.out.println(ss3);
//		}
//
//		// Get your facet results
//		// Terms agg1 = sr.getAggregations().get("status_code");
//		// List<Bucket> bs = agg1.getBuckets();
//		// for (Bucket b : bs) {
//		// System.out.println(b.getKey() + " >> " + b.getDocCount());
//		// }
//
//		System.out.println(sr.toString());
//
//		InternalDateHistogram agg2 = sr.getAggregations().get("timestamp");
//		if (agg2 != null) {
//			List<org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram.Bucket> bs = agg2
//					.getBuckets();
//			for (org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram.Bucket b : bs) {
//				InternalAvg s = b.getAggregations().get("dspeed");
//				System.out.println(b.getKeyAsString() + " >> " + b.getDocCount() + " >> " + s.getValue());
//
//			}
//		}
//	}
//
//}