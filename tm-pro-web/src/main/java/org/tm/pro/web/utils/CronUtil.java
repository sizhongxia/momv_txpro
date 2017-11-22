package org.tm.pro.web.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.expression.ParseException;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.tm.pro.utils.TmStringUtil;

public class CronUtil {
	/**
	 * 
	 * @desc 计算表达式近20次时间
	 * @param cron
	 * @return
	 */
	public static List<String> seeExcuteTime(String cron) throws ParseException, IllegalArgumentException {
		if (TmStringUtil.isBlank(cron)) {
			throw new IllegalArgumentException("参数不能为空");
		}
		CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<String> list = new ArrayList<>(20);

		Date nextTimePoint = new Date();
		for (int i = 0; i < 20; i++) {
			// 计算下次时间点的开始时间
			nextTimePoint = cronSequenceGenerator.next(nextTimePoint);
			list.add(sdf.format(nextTimePoint));
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		List<String> list = seeExcuteTime("0/5 * * * * ?");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}