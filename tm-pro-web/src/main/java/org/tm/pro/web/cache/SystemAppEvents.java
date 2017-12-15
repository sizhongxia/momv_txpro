package org.tm.pro.web.cache;

import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;

public class SystemAppEvents implements InitializingBean {

	public static Vector<String> keys = new Vector<>();

	public static boolean hasKey(String key) {
		return keys.contains(key);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		keys.add("UpdateSystemInfoCacheEvent");
		keys.add("UpdateAuthorizationCacheEvent");
		keys.add("UpdateSystemJobCacheEvent");
	}

}
