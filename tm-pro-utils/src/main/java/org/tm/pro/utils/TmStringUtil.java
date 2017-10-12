package org.tm.pro.utils;

import com.xiaoleilu.hutool.lang.Validator;

public class TmStringUtil {
	/**
	 * 验证是否为空<br>
	 * 对于String类型判定是否为empty(null 或 "")<br>
	 * 
	 * @param value 值
	 * @return 是否为空
	 * @return 是否为空
	 */
	public static boolean isBlank(String value) {
		return Validator.isEmpty(value);
	}
	
	/**
	 * 验证是否不为空<br>
	 * 对于String类型判定是否不为empty(null 或 "")<br>
	 * 
	 * @param value 值
	 * @return 是否不为空
	 * @return 是否不为空
	 */
	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}
	
	/**
	 * 验证是否为可用邮箱地址
	 * 
	 * @param value 值
	 * @return 否为可用邮箱地址
	 */
	public static boolean isEmail(String value) {
		return Validator.isEmail(value);
	}
	
	/**
	 * 验证该字符串是否是数字
	 * 
	 * @param value 字符串内容
	 * @return 是否是数字
	 */
	public static boolean isNumber(String value) {
		return Validator.isNumber(value);
	}
	
	/**
	 * 验证是否为手机号码（中国）
	 * 
	 * @param value 值
	 * @return 是否为手机号码（中国）
	 */
	public static boolean isMobile(String value) {
		return Validator.isMobile(value);
	}

	/**
	 * 验证是否为汉字
	 * 
	 * @param value 值
	 * @return 是否为汉字
	 */
	public static boolean isChinese(String value) {
		return Validator.isChinese(value);
	}

	/**
	 * 验证是否为英文字母 、数字和下划线
	 * 
	 * @param value 值
	 * @return 是否为英文字母 、数字和下划线
	 */
	public static boolean isGeneral(String value) {
		return Validator.isGeneral(value);
	}
}
