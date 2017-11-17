package org.tm.pro.web.cache.config;

import java.util.ResourceBundle;

/**
 * 读取配置文件
 * 
 * @author sizho
 *
 */
public class SystemConfig {
	private static ResourceBundle SYSTEM_CONFIG = ResourceBundle.getBundle("system");

	public static String getConfigProperty(String key) {
		return SYSTEM_CONFIG.getString(key);
	}
}
