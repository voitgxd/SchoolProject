package com.platform.admin.util;

import java.util.Random;

/**
 * <p>
 * 随机数工具类
 * </p>
 * 
 * @author okpay
 * 
 */
public class RandomUtil {
	private static ThreadLocal<Random> random = new ThreadLocal<Random>() {
		@Override
		protected Random initialValue() {
			return new Random();
		}
	};
	private static final StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");

	public static String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		Random r = random.get();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
}
