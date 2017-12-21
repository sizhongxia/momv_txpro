package org.tm.pro.web.sms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.tm.pro.utils.TmNumberUtil;

import com.alibaba.fastjson.JSONObject;
import com.tm.pro.redis.util.RedisUtil;
import com.xiaoleilu.hutool.util.RandomUtil;

public class SendSmsWorker implements ApplicationListener<SendSmsEvent> {

	private Logger logger = LoggerFactory.getLogger(SendSmsWorker.class);

	private final String DEF_CHATSET = "UTF-8";
	private final int DEF_CONN_TIMEOUT = 30000;
	private final int DEF_READ_TIMEOUT = 30000;
	private final int DEF_SEND_SMS_PRE_HOUR = 5;
	private final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
	// 配置您申请的KEY
	private final String APPKEY = "aefa9400c45a1ff3b90f17770e41a681";
	// 短信验证码RedisKey前缀
	public static final String VERIFICATION_CODE_PREFIX = "system:sms:code:";
	// 聚合短信模板 发送验证码模板ID
	public static final String JUHE_TPL_ID_VERIFICATION_CODE = "56915";

	private static final String SEND_SUC_COUNT_PREFIX = "system:sms:send:suc:count:";
	
	private RedisUtil redisUtil;

	public void setRedisUtil(RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}

	public boolean sendVerificationCode(String mobile) {
		// 检查每小时发送次数
		String key = SEND_SUC_COUNT_PREFIX + getHourStr() + ":" + mobile;
		String count = redisUtil.get(key);
		int countPreHour = TmNumberUtil.toInt(count, 0);
		if (countPreHour >= DEF_SEND_SMS_PRE_HOUR) {
			return false;
		}
		String randomNumber = RandomUtil.randomNumbers(6);
		key = VERIFICATION_CODE_PREFIX + mobile;
		redisUtil.setex(key, 60 * 5, randomNumber);
		sendSms(mobile, JUHE_TPL_ID_VERIFICATION_CODE, "#code#=" + randomNumber);
		return true;
	}

	// 发送短信
	private void sendSms(String mobile, String tplId, String tplValue) {
		String result = null;
		// 请求接口地址
		String url = "http://v.juhe.cn/sms/send";
		// 请求参数
		Map<String, Object> params = new HashMap<String, Object>();
		// 接收短信的手机号码
		params.put("mobile", mobile);
		// 短信模板ID，请参考个人中心短信模板设置
		params.put("tpl_id", tplId);
		// 变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递
		params.put("tpl_value", tplValue);
		// 应用APPKEY(应用详细页查询)
		params.put("key", APPKEY);
		// 返回数据的格式,xml或json，默认json
		params.put("dtype", "json");
		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.parseObject(result);
			if (object.getInteger("error_code") == 0) {
				String key = SEND_SUC_COUNT_PREFIX + getHourStr() + ":" + mobile;
				// 发送短信成功次数记录
				redisUtil.incr(key);
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	private String net(String strUrl, Map<String, Object> params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	private String getHourStr() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHH");
		return df.format(new Date());
	}

	// 将map型转为请求参数型
	private String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	@Override
	public void onApplicationEvent(SendSmsEvent event) {
		SendSmsEventObj obj = (SendSmsEventObj) event.getSource();
		if ("56915".equals(obj.getType())) {
			if (!sendVerificationCode(obj.getMobile())) {
				logger.error("Send Sms Verification Code to " + obj.getMobile() + " faile!");
			}
		}
	}
}