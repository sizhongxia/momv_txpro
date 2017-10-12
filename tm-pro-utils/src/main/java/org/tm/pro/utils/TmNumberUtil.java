package org.tm.pro.utils;

import org.apache.commons.lang3.math.NumberUtils;

import com.xiaoleilu.hutool.util.NumberUtil;

public class TmNumberUtil {
	
	/**
	 * 是否为数字
	 * 
	 * @param value  字符串值
	 * @return 是否为数字
	 */
	public static boolean isNumber(String value) {
		return NumberUtil.isNumber(value);
	}

	/**
	 * 字符串转换为Int
	 * 
	 * @param value  字符串值
	 * @param defaultValue  默认Int值
	 * @return Int值
	 */
	public static int toInt(String value, int defaultValue) {
		return NumberUtils.toInt(value, defaultValue);
	}

	/**
	 * 字符串转换为Long
	 * 
	 * @param value  字符串值
	 * @param defaultValue  默认Long值
	 * @return Long值
	 */
	public static long toLong(String value, long defaultValue) {
		return NumberUtils.toLong(value, defaultValue);
	}

}
