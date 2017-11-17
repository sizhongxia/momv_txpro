package org.tm.pro.picture.zimg.config;

import java.util.ResourceBundle;

public class ZimgConfig {
	
	private static ResourceBundle ZIMG_CONFIG = ResourceBundle.getBundle("zimg");

	public static String getConfigProperty(String key) {
		return ZIMG_CONFIG.getString(key);
	}
}
