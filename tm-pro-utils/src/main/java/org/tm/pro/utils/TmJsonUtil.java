package org.tm.pro.utils;

import java.util.Map;

import com.xiaoleilu.hutool.json.JSONUtil;

public class TmJsonUtil {

	public static String mapToJson(Map<String, Object> map) {
		return JSONUtil.parseFromMap(map).toString();
	}

}
