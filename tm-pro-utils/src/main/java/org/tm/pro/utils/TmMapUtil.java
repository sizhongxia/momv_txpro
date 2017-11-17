package org.tm.pro.utils;

import java.util.Map;

public class TmMapUtil {
	@SuppressWarnings("unchecked")
	public static <T> T getMapVal(Map<String, Object> map, String key, T defaultValue) {
		if (map == null || map.isEmpty()) {
			return defaultValue;
		}
		Object value = map.get(key);
		if (value == null) {
			return defaultValue;
		}
		if (defaultValue instanceof Integer) {
			return (T) new Integer(value.toString());
		} else if (defaultValue instanceof Long) {
			return (T) new Long(value.toString());
		} else if (defaultValue instanceof String) {
			return (T) value.toString();
		} else {
			return (T) value;
		}
	}
}
