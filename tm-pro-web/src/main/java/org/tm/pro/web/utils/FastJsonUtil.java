package org.tm.pro.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * FastJson 序列化工具
 *
 * @author xiao
 *
 */
public final class FastJsonUtil {

	/**
	 * 序列化参数
	 */
	private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero };

	/**
	 * 对象转换成json 支持list,map,array
	 *
	 * @param obj
	 * @return
	 */
	public static String objToJson(Object obj) {
		return JSON.toJSONString(obj, features);
	}

	/**
	 * json 转换成对象
	 *
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObj(String json, Class<?> clazz) {
		return (T) JSON.parseObject(json, clazz);
	}
}