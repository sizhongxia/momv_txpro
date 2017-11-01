package org.tm.pro.picture.zimg.config;

import java.util.ResourceBundle;

public class ZimgConfig {
	private static final String DEFAULT_REDIS_PROPERTIES = "zimg";
	private static ResourceBundle ZIMG_CONFIG = ResourceBundle.getBundle(DEFAULT_REDIS_PROPERTIES);

	public static String getConfigProperty(String key) {
		return ZIMG_CONFIG.getString(key);
	}
}
