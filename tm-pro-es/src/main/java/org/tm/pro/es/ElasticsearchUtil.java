package org.tm.pro.es;

import java.net.InetAddress;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class ElasticsearchUtil implements InitializingBean, DisposableBean {
	private TransportClient client = null;
	private String clusterName;
	private String serverHost;
	private int port;

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void destroy() throws Exception {
		if (client != null) {
			client.close();
		}
	}

	public SearchResponse search(final String index, final String type, QueryBuilder builder,
			SortBuilder<?> sortBuilder, AggregationBuilder aggregationBuilder, final Integer from, final Integer size) {
		SearchRequestBuilder requestBuilder = client.prepareSearch(index).setTypes(type);
		requestBuilder.setQuery(builder);
		if (sortBuilder != null) {
			requestBuilder.addSort(sortBuilder);	
		}

		if (aggregationBuilder != null) {
			requestBuilder.addAggregation(aggregationBuilder);
		}

		requestBuilder.setFrom(from).setSize(size);
		SearchResponse response = requestBuilder.execute().actionGet();
		return response;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Settings settings = Settings.builder().put("cluster.name", clusterName).build();
		InetAddress inetAddress = InetAddress.getByName(serverHost);
		InetSocketTransportAddress iAddress = new InetSocketTransportAddress(inetAddress, port);

		this.client = new PreBuiltTransportClient(settings);
		this.client.addTransportAddress(iAddress);
	}

}