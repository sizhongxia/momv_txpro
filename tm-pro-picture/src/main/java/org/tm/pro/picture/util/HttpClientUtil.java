package org.tm.pro.picture.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpClientUtil {
	public static String upload(String url, byte[] bytes, String fileType) {
		OutputStream outStream = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("Content-Type", fileType);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			outStream = conn.getOutputStream();
			outStream.write(bytes);
			outStream.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null)
				result += line;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {// 关闭输出流、输入流
				if (outStream != null)
					outStream.close();
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	// private static final Logger log = Logger.getLogger(HttpClientUtil.class);
	// public static String checkDomain(String domain) {
	// try {
	// MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	// builder.setCharset(Charset.forName("UTF-8"));
	// CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	// HttpPost httpPost = new
	// HttpPost("http://panda.www.net.cn/cgi-bin/check.cgi?area_domain=" + domain);
	// httpPost.setEntity(builder.build());
	// CloseableHttpResponse response = httpClient.execute(httpPost);
	// return EntityUtils.toString(response.getEntity(), "UTF-8");
	// } catch (ClientProtocolException e) {
	// log.error(e);
	// } catch (IOException e) {
	// log.error(e);
	// }
	// return null;
	// }
}
