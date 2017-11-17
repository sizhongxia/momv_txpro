//package org.tm.pro.es;
//
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.sort.SortBuilder;
//import org.elasticsearch.search.sort.SortBuilders;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.Test;
//
///**
// * Unit test for simple App.
// */
//public class AppTest2 {
//
//	@Test
//	public void testElasticUtils() {
//
//		ElasticsearchUtil util = new ElasticsearchUtil();
//		util.setServerHost("127.0.0.1");
//		util.setPort(9300);
//		util.setClusterName("elasticsearch");
//		try {
//			util.afterPropertiesSet();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		BoolQueryBuilder builder = QueryBuilders.boolQuery();
//		SortBuilder<?> sortBuilder = SortBuilders.fieldSort("age").order(SortOrder.DESC);
//
//		SearchResponse searchResponse = util.search("user", "type1", builder, sortBuilder, 0, 10);
//		System.out.println(searchResponse);
//	}
//
//}
