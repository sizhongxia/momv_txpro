package org.tm.pro.web.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.tm.pro.utils.TmDateUtil;
import org.tm.pro.web.cache.config.SystemConfig;
import org.tm.pro.web.event.TmApplicationEvent;

import com.xiaoleilu.hutool.lang.Console;

public class SystemBaseInfoCacheUtil implements InitializingBean, ApplicationListener<TmApplicationEvent> {

	public static ConcurrentHashMap<String, String> systemInfo = new ConcurrentHashMap<>();

	/**
	 * 初始化系统基本信息数据
	 */
	private void initSystemInfo() {
		systemInfo.put("update_time", TmDateUtil.now());
		// 默认主页
		systemInfo.put("default_url", SystemConfig.getConfigProperty("default_url"));
		systemInfo.put("login_bg_pic", SystemConfig.getConfigProperty("login_bg_pic"));
		// 1小时（60×60）可允许登陆失败5次
		systemInfo.put("login_fail_count", SystemConfig.getConfigProperty("login_fail_count"));
		systemInfo.put("login_fail_expired", SystemConfig.getConfigProperty("login_fail_expired"));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initSystemInfo();
	}

	@Override
	public void onApplicationEvent(TmApplicationEvent e) {
		if ("UpdateSystemBaseInfoCacheEvent".equals(e.getSource().toString())) {
			initSystemInfo();
			Console.log("update system cache  suc.");
		}
	}
}
