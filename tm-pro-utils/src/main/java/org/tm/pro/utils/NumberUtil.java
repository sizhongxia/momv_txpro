package org.tm.pro.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil {

	public static int toInt(String value, int defaultValue) {
		return NumberUtils.toInt(value, defaultValue);
	}

	public static long toLong(String value, long defaultValue) {
		return NumberUtils.toLong(value, defaultValue);
	}

}
