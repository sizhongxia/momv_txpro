package org.tm.pro.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * FastJson 序列化工具
 */
public final class FastJsonUtil {

	private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero };

	// 对象转换成json
	public static String objToJson(Object obj) {
		return JSON.toJSONString(obj, features);
	}

	// json 转换成对象
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObj(String json, Class<?> clazz) {
		return (T) JSON.parseObject(json, clazz);
	}
}