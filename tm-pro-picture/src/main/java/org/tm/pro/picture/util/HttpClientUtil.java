package org.tm.pro.picture.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	private static final Logger log = Logger.getLogger(HttpClientUtil.class);
	
	public static String upload(String url, File file) {
		try {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			// 封装文件
			builder.addBinaryBody(UUID.randomUUID().toString(), file, ContentType.MULTIPART_FORM_DATA, file.getName());
			builder.setCharset(Charset.forName("UTF-8"));

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(builder.build());
			httpPost.setHeader("Content-Type", "jpeg");
			CloseableHttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ClientProtocolException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
}
