package com.platform.admin.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * <p>
 * 日期工具类(使用Joda-Time)
 * </p>
 * 
 * @author okpay
 * 
 */
public class DateTimeUtil {
	/** 默认时间格式 */
	public static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** 默认日期格式 */
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	/** 起始日期 */
	public static String FIRST_DATE = "1970-01-01 00:00:00";

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

	/**
	 * 校验日期字符串格式
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static boolean checkStringFormat(String str, String format) {
		if (null == str) {
			return false;
		}
		try {
			DateTimeFormat.forPattern(format).parseDateTime(str);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取当前时间，返回date类型
	 * 
	 * @return
	 */
	public static Date getCurrentTime() {
		DateTime time = new DateTime();
		return time.toDate();
	}

	/**
	 * 获取当前时间，默认格式：yyyy-MM-dd hi:mm:ss
	 */
	public static String getCurrent() {
		DateTime time = new DateTime();
		String current = time.toString(DEFAULT_TIME_FORMAT);
		return current;
	}

	/**
	 * 使用默认时间格式解析时间
	 * 
	 * @param time
	 * @return
	 */
	public static Date parseTime(String time) {
		return parseTime(time, DEFAULT_TIME_FORMAT);
	}

	/**
	 * 使用默认日期格式解析日期
	 * 
	 * @param time
	 * @return
	 */
	public static Date parseDate(String date) {
		return parseTime(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 解析时间
	 * 
	 * @param time
	 *            时间字符串
	 * @param format
	 *            格式
	 * @return
	 */
	public static Date parseTime(String time, String format) {
		try {
			return DateTimeFormat.forPattern(format).parseDateTime(time)
					.toDate();
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 获取当前时间之前几日的日期
	 * 
	 * @param before
	 *            前几日
	 * @return
	 */
	public static Date getBeforeDate(int before) {
		DateTime date = new DateTime();
		return date.minusDays(before).toDate();
	}

	public static void main(String[] args) {
		System.out.println(DateTimeUtil.getCurrentTime());
		System.out.println(DateTimeUtil.getBeforeDate(7));
	}
}