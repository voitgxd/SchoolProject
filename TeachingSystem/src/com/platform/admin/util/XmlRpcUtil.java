package com.platform.admin.util;

import java.net.MalformedURLException;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcClient;

/**
 * <p>
 * XmlRpc工具类
 * </p>
 * 
 * @author okpay
 * 
 */
public class XmlRpcUtil {
	public static final Logger log = Logger.getLogger(XmlRpcUtil.class);
	public static final int DEFAULT_MAX_THREAS = 1000;// 默认最大线程数
	private XmlRpcClient client = null;

	public XmlRpcUtil(String url) {
		this(url, DEFAULT_MAX_THREAS);
	}

	public XmlRpcUtil(String url, int maxThreads) {
		if (null == url || "".equals(url)) {
			throw new RuntimeException("url cannot be null...");
		}
		if (maxThreads <= 0) {
			maxThreads = Integer.MAX_VALUE;
		}
		try {
			this.client = new XmlRpcClient(url);
			this.client.setMaxThreads(maxThreads);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 调用远程方法
	 * 
	 * @param <T>
	 * @param method
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T invoke(String method, Vector params) {
		Object obj = null;
		try {
			obj = client.execute(method, params);
			if (obj instanceof Exception) {
				throw (Exception) obj;
			}
			return (T) obj;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
	}
	
}
