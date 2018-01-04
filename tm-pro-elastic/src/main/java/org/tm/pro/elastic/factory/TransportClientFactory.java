package org.tm.pro.elastic.factory;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.tm.pro.elastic.model.ElasticConfig;

@SuppressWarnings("resource")
public class TransportClientFactory implements InitializingBean, DisposableBean {

	private ElasticConfig elasticConfig;

	public void setElasticConfig(ElasticConfig elasticConfig) {
		this.elasticConfig = elasticConfig;
	}

	private TransportClient client;

	public TransportClient getClient() {
		if (client == null) {
			throw new RuntimeException("Init Transport Client Error!!!");
		}
		return client;
	}

	public String postBaseRequest(String index, String type, String json) throws Exception {
		StringBuffer sb = new StringBuffer(elasticConfig.getHttp());
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

	@Override
	public void destroy() throws Exception {
		if (client != null) {
			client.close();
			client = null;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			Builder builder = Settings.builder();
			builder.put("cluster.name", elasticConfig.getClusterName());
			builder.put("client.transport.sniff", false);
			builder.put("client.transport.ignore_cluster_name", true);
			builder.put("client.transport.ping_timeout", "5s");
			builder.put("client.transport.nodes_sampler_interval", "5s");

			Settings settings = builder.build();
			InetSocketTransportAddress addr = new InetSocketTransportAddress(
					InetAddress.getByName(elasticConfig.getHost()), elasticConfig.getPort());

			client = new PreBuiltTransportClient(settings).addTransportAddress(addr);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}