package com.techfun.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getCurrentTime() {

		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		return now;
	}

	public static String changeDateFormat(Date date, String format) {

		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
}
