package com.tm.pro.redis.config;

import java.util.ResourceBundle;

/**
 * 读取配置文件
 * 
 * @author sizho
 *
 */
public class RedisConfig {
	private static final String DEFAULT_REDIS_PROPERTIES = "redis";
	private static ResourceBundle REDIS_CONFIG = ResourceBundle.getBundle(DEFAULT_REDIS_PROPERTIES);

	public static String getConfigProperty(String key) {
		return REDIS_CONFIG.getString(key);
	}
}
