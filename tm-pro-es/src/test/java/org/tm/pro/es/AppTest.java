//package org.tm.pro.es;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.ExecutionException;
//
//import org.elasticsearch.action.ActionFuture;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequestBuilder;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.sort.SortOrder;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.junit.Test;
//
///**
// * Unit test for simple App.
// */
//public class AppTest {
//	@SuppressWarnings("resource")
//	public void testApp() throws UnknownHostException, InterruptedException, ExecutionException {
//
//		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//
//		SearchRequest request = new SearchRequest();
//
//		ActionFuture<SearchResponse> actionFuture = client.search(request);
//
//		SearchResponse searchResponse = actionFuture.get();
//
//		SearchHits searchHits = searchResponse.getHits();
//		System.out.println(searchHits.totalHits);
//		SearchHit[] hits = searchHits.getHits();
//		for (SearchHit hit : hits) {
//			System.out.println(hit.docId());
//			System.out.println(hit.getClusterAlias());
//			System.out.println(hit.getId());
//			System.out.println(hit.getScore());
//			System.out.println(hit.getSourceAsString());
//			System.out.println(hit.getType());
//			System.out.println(hit.getVersion());
//			System.out.println(hit.getFields());
//			System.out.println("====================");
//		}
//		client.close();
//
//	}
//
//	@Test
//	@SuppressWarnings("resource")
//	public void getById() throws UnknownHostException, InterruptedException, ExecutionException {
//
//		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//
//		GetRequestBuilder requestBuilder = client.prepareGet("user", "type1", "1");
//		GetResponse response = requestBuilder.execute().actionGet();
//
//		System.out.println(response.getId());
//		System.out.println(response.getSource().get("name"));
//		System.out.println(response.getSource().get("age"));
//		System.out.println("====================");
//		client.close();
//
//	}
//
//	@Test
//	@SuppressWarnings("resource")
//	public void getByParam() throws UnknownHostException, InterruptedException, ExecutionException {
//
//		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//
//		SearchRequestBuilder requestBuilder = client.prepareSearch("user").setTypes("type1");
//
//		// requestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
//
//		// requestBuilder.setQuery(queryBuilder );
//
//		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//		List<QueryBuilder> qBuilders = boolQueryBuilder.must();
//		qBuilders.add(QueryBuilders.matchQuery("name", "夏"));
//		qBuilders.add(QueryBuilders.rangeQuery("age").gt(20).lt(50));
//		qBuilders.add(QueryBuilders.rangeQuery("birthday").gt("1990-02-01").lt("1993/01/01"));
//
//		requestBuilder.setQuery(boolQueryBuilder);
//
//		requestBuilder.addSort("age", SortOrder.ASC);
//
//		requestBuilder.setFrom(0).setSize(100);
//		requestBuilder.setExplain(true);
//
//		SearchResponse searchResponse = requestBuilder.execute().actionGet();
//
//		SearchHits hits = searchResponse.getHits();
//		System.out.println(hits.totalHits);
//		for (SearchHit hit : hits) {
//			System.out.println(hit.getSourceAsString());
//			System.out.println(hit.getSource().get("birthday"));
//			System.out.println("——————————");
//		}
//		client.close();
//
//	}
//
//	@SuppressWarnings("resource")
//	public void save() throws UnknownHostException, InterruptedException, ExecutionException {
//
//		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//
//		// for (int i = 0; i < 1000; i++) {
//		Map<String, Object> data = new HashMap<>();
//		data.put("name", UUID.randomUUID().toString());
//		data.put("age", (int) (Math.random() * 100));
//		IndexResponse indexResponse = client.prepareIndex("user", "man").setSource(data).execute().actionGet();
//		// System.out.println(indexResponse);
//		if (indexResponse.getResult().getLowercase().equals("created")) {
//			System.out.println("OK");
//		}
//		// }
//		client.close();
//
//	}
//
//	@Test
//	@SuppressWarnings("resource")
//	public void update() throws UnknownHostException, InterruptedException, ExecutionException {
//
//		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//
//		Map<String, Object> params = new HashMap<>();
//		params.put("age", 43);
//		UpdateResponse response = client.prepareUpdate("user", "type1", "1").setDoc(params).execute().actionGet();
//
//		if (response.getResult().getLowercase().equals("updated")) {
//			System.out.println("OK");
//		}
//
//		client.close();
//
//	}
//
//	@SuppressWarnings("resource")
//	public void delete() throws UnknownHostException, InterruptedException, ExecutionException {
//
//		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//		TransportClient client = new PreBuiltTransportClient(settings)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//		DeleteResponse response = client.prepareDelete("user", "man", "AV-5zVVCGnDa7sG5b_wb").execute().actionGet();
//
//		if (response.getResult().getLowercase().equals("deleted")) {
//			System.out.println("OK");
//		}
//
//		client.close();
//
//	}
//
//}
