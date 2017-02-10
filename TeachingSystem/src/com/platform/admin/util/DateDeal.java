package com.platform.admin.util;

import org.joda.time.DateTime;

/**
 * <p>
 * 日期工具类(使用Joda-Time)
 * </p>
 * 
 * @author okpay
 * 
 */
public class DateDeal {
	public static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将时间戳转成指定格式字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timeLongToStr(long time) {
		return timeLongToStr(time, DEFAULT_TIME_FORMAT);
	}

	/**
	 * 将时间戳转成指定格式字符串
	 * 
	 * @param time
	 * @param formatStr
	 *            格式
	 * @return
	 */
	public static String timeLongToStr(long time, String formatStr) {
		try {
			DateTime date = new DateTime(time);
			return date.toString(formatStr);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(DateDeal.timeLongToStr(System.currentTimeMillis()));
		DateTime date = new DateTime();
		System.out.println(date.toString("yyyy-MM-dd HH:mm:ss"));
	}
}