package com.platform.admin.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共方法类
 * 
 * @author lk
 */

public class Common {
	/**
	 * 获取访问者ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteHost();
		}
		String[] ipArray = ip.split(",");
		return ipArray[0].trim();
	}

	/**
	 * 检验参数是否为null
	 * 
	 * @param canBlank
	 *            :表示是否可接受空字符串
	 * @param params
	 *            :待校验的参数
	 * @return false :不为null
	 */
	public static boolean checkNull(boolean canBlank, String... params) {
		boolean result = false;
		for (String s : params) {
			if (canBlank) {
				result = (null == s);
			} else {
				result = (null == s || "".equals(s.trim()));
			}
			if (result) {
				break;
			}
		}
		return result;
	}

	/**
	 * 将字符串形式IP转换成long
	 * 
	 * @param ip
	 * @return
	 */
	public static long ipToLong(String ip) {
		if (null == ip || "".equals(ip.trim())) {
			return -1;
		}
		long result = -1;
		try {
			String[] strs = ip.split("\\.");
			result = (Long.parseLong(strs[0]) << 24) + (Long.parseLong(strs[1]) << 16) + (Long.parseLong(strs[2]) << 8)
					+ Long.parseLong(strs[3]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	/**
	 * 将整型IP转成字符串
	 * 
	 * @param ip
	 * @return
	 */
	public static String longToIp(long ip) {
		StringBuffer buffer = new StringBuffer("");
		if (ip <= 0) {
			return null;
		}
		buffer.append(ip >>> 24).append(".");
		buffer.append((ip & 0x00FFFFFF) >>> 16).append(".");
		buffer.append((ip & 0x0000FFFF) >>> 8).append(".");
		buffer.append(ip & 0x000000FF);
		return buffer.toString();
	}

	/**
	 * 获取统一登录地址
	 * 
	 * @return
	 */
	public static String getLoginUrl() {
		return PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME, "loginUrl", "");
	}
	
	/**
	 * 获取登录回调地址
	 * 
	 * @return
	 */
	public static String getCallbackUrl() {
		return PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME, "callbackUrl", "");
	}
	
	/**
	 * 获取pfserver地址
	 * 
	 * @return
	 */
	public static String getPfserverUrl() {
		return PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME, "pfserverUrl", "");
	}
	/**
	 * 获取toke地址
	 * @return
	 */
	public static String getTokenUrl(){
		return PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME, "tokenUrl","");
	}
	/**
	 * 获取校验用户URL
	 * @return
	 */
	public static String getValidateUrl(){
		return PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME, "validateUrl", "");
	}
	/**
	 * 获取图片URL
	 * @return
	 */
	public static String getImageUrl(){
		return PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME, "imageUrl", "");
	}
	
	/**
	 * 生成唯一标示
	 * 
	 * @return
	 */
	public static String generateRequestId() {
		return System.currentTimeMillis() + "_" + RandomUtil.getRandomString(5);
	}

	/**
	 * 根据返回值获取返回信息
	 * 
	 * @param resultCode
	 * @return
	 */
	public static String getMsgByCode(int resultCode) {
		String msg = "未知错误";
		switch (resultCode) {
			case 1: {
				msg = "操作成功!";
				break;
			}
			case -200: {
				msg = "系统错误";
				break;
			}
			case -201: {
				msg = "参数为NULL";
				break;
			}
			case -202: {
				msg = "appId不存在";
				break;
			}
			case -203: {
				msg = "没有该条游戏排行信息.";
				break;
			}
			case -302: {
				msg = "已到顶部或底部,不能继续上移或下移.";
				break;
			}
			case -303: {
				msg = "置顶和未指定的应用只能在各自的范围移动.";
				break;
			}
			case -304: {
				msg = "未定义排行值.";
				break;
			}
			case -500: {
				msg = "操作失败!";
				break;
			}
		}
		return msg;
	}
}
