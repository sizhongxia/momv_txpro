package org.tm.pro.utils;

import com.xiaoleilu.hutool.lang.Console;
import com.xiaoleilu.hutool.util.IdcardUtil;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	public void testApp() {
		String idCard = "412326199206203372";
		Console.log(IdcardUtil.getProvinceByIdCard(idCard));
		Console.log(IdcardUtil.getGenderByIdCard(idCard));
		Console.log(IdcardUtil.getBirthByIdCard(idCard));
		Console.log(IdcardUtil.getAgeByIdCard(idCard));
		Console.log(IdcardUtil.getYearByIdCard(idCard));
		Console.log(IdcardUtil.getMonthByIdCard(idCard));
		Console.log(IdcardUtil.getDayByIdCard(idCard));
	}
}
