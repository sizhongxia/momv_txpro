package org.tm.pro.utils;

import org.junit.Assert;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	public void testApp() {
		// String idCard = "412326199206203372";
		// Console.log(IdcardUtil.getProvinceByIdCard(idCard));
		// Console.log(IdcardUtil.getGenderByIdCard(idCard));
		// Console.log(IdcardUtil.getBirthByIdCard(idCard));
		// Console.log(IdcardUtil.getAgeByIdCard(idCard));
		// Console.log(IdcardUtil.getYearByIdCard(idCard));
		// Console.log(IdcardUtil.getMonthByIdCard(idCard));
		// Console.log(IdcardUtil.getDayByIdCard(idCard));
		// String content = "123456789123456789123456789000";
		String content = "司仲夏司仲夏司仲夏司仲夏司仲夏司仲夏司仲夏司仲夏司仲夏司仲夏";
		String mi = TmDesUtil.encrypt(content);
		// System.out.println(mi.length());
		Assert.assertEquals(content, TmDesUtil.decrypt(mi));

	}
}
