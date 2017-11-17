package org.tm.pro.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xiaoleilu.hutool.date.DateUtil;

public class TmDateUtil {
	public static String now() {
		return DateUtil.now();
	}

	public static String format(long ts, String format) {
		Date date = new Date(ts);
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
}
