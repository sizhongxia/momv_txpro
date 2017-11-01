package org.tm.pro.web.cache;

import java.util.Vector;

import org.springframework.beans.factory.InitializingBean;
import org.tm.pro.model.SystemAppEventModel;

public class SystemAppEventCacheUtil implements InitializingBean {

	public static Vector<SystemAppEventModel> SystemAppEvents = new Vector<>();
	public static Vector<String> keys = new Vector<>();

	public static boolean hasKey(String key) {
		return keys.contains(key);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SystemAppEvents.add(new SystemAppEventModel("系统缓存数据", "UpdateSystemBaseInfoCacheEvent", "更新系统缓存数据"));
		keys.add("UpdateSystemBaseInfoCacheEvent");
	}

}
