package com.platform.admin.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * <p>
 * 安全工具类
 * </p>
 * 
 * @author Vicky
 * 
 */
public class SecurityUtil {
	/**
	 * 校验签名
	 * 
	 * @param sign
	 *            --签名
	 * @param salt
	 *            --指定签名的盐
	 * @param params
	 *            --用于签名的参数列表
	 * @return
	 */
	public static boolean validateSign(String sign, String salt, Object... params) {
		if (null == sign) {
			return false;
		}
		StringBuffer buf = new StringBuffer();
		if (null != params) {
			for (Object s : params) {
				buf.append(s);
			}
		}
		buf.append(null == salt ? "" : salt);
		String valSign = getSign(buf.toString());
		return !(null == valSign || !sign.equalsIgnoreCase(valSign));
	}

	/**
	 * 签名
	 * 
	 * @param salt
	 *            加盐
	 * @param params
	 *            加密参数
	 * @return
	 */
	public static String getSign(String salt, Object... params) {
		StringBuffer buf = new StringBuffer();
		if (null != params) {
			for (Object s : params) {
				buf.append(s);
			}
		}
		buf.append(null == salt ? "" : salt);
		return getSign(buf.toString());
	}

	/**
	 * 签名
	 */
	public static String getSign(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	/**
	 * 将字符串进行Base64解码
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeBase64(String str) {
		if (str == null) {
			return null;
		}
		return new String(Base64.decodeBase64(str.getBytes()));
	}

	/**
	 * 将字符串进行base64转码
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeBase64(String str) {
		if (str == null) {
			return null;
		}
		return new String(Base64.encodeBase64(str.getBytes()));
	}

	/**
	 * 获取签名盐
	 * 
	 * @return
	 */
	public static String getSignSalt() {
		return PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME, Constant.SIGN_KEY, "");
	}

	/**
	 * 校验登录请求
	 * 
	 * @param type
	 * @param callback
	 * @return
	 */
	public static boolean validateLoginRequest(String type, String callback) {
		boolean result = false;
		String str = type + ":" + callback;
		String validateLoginStr = PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME,
				"validateLoginStr", "admin:http://192.168.41.168:8080/loginback;");
		String[] valis = validateLoginStr.split(";");
		for (String val : valis) {
			if (val.indexOf(str) != -1) {
				result = true;
				break;
			}
		}
		return result;
	}
}
