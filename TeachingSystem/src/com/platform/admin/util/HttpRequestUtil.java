package com.platform.admin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class HttpRequestUtil {
	public static final Logger log = Logger.getLogger(HttpRequestUtil.class);

	/**
	 * 通过get方式发送请求
	 * 
	 * @param url
	 *            url
	 * @param params
	 *            参数
	 * @return 返回信息
	 * @throws IOException
	 */
	public static String sendUrlByGet(String url, Map<String, Object> params) throws IOException {
		String result = "";
		if (null == url) {
			return result;
		}
		StringBuffer builder = new StringBuffer(url).append("?");
		for (String s : params.keySet()) {
			builder.append(s).append("=").append(params.get(s)).append("&");
		}
		builder.deleteCharAt(builder.length() - 1);
		log.debug("send url request by get, url = " + builder.toString());
		HttpURLConnection conn = (HttpURLConnection) new URL(builder.toString()).openConnection();
		conn.connect();
		int count = 0;
		byte[] buffer = new byte[4096];
		InputStream urlInps;
		urlInps = conn.getInputStream();
		while ((count = urlInps.read(buffer)) != -1) {
			result += new String(buffer, 0, count);
		}
		urlInps.close();
		conn.disconnect();
		conn = null;
		url = null;
		return result;
	}

	/**
	 * 通过post方式发送请求
	 * 
	 * @param url
	 *            url
	 * @param params
	 *            参数
	 * @return 返回信息
	 * @throws IOException
	 */
	public static String sendUrlByPost(String url, Map<String, Object> params) throws IOException {
		String result = null;
		if (null == url) {
			return result;
		}
		StringBuilder builder = new StringBuilder();
		for (String s : params.keySet()) {
			builder.append(s).append("=").append(params.get(s)).append("&");
		}
		builder.delete(builder.length() - 1, builder.length() - 1);
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(40000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setAllowUserInteraction(false);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.connect();
		
		
		OutputStream oups = conn.getOutputStream();
		oups.write(builder.toString().getBytes("UTF-8"));
		
		int count = 0;
		byte[] buffer = new byte[4096];
		InputStream urlInps;
		urlInps = conn.getInputStream();
		while ((count = urlInps.read(buffer)) != -1) {
			result += new String(buffer, 0, count);
		}
		urlInps.close();
		conn.disconnect();
		conn = null;
		url = null;
		return result;
	}
	
	public static String  sendPostRequest(String url, Map<String, Object> params) throws Exception{
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder connResult = new StringBuilder("");
		
		try {
			
			StringBuilder builder = new StringBuilder();
			for (String s : params.keySet()) {
				builder.append(s).append("=").append(params.get(s)).append("&");
			}
			builder.delete(builder.length() - 1, builder.length() - 1);
			
			URL destURL = new URL(url);			
//			log.info("url="+destURL+"="+builder.toString());
			HttpURLConnection urlConn = (HttpURLConnection) destURL.openConnection();
			urlConn.setConnectTimeout(5000);
			urlConn.setReadTimeout(40000);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setAllowUserInteraction(false);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod("POST");	
			urlConn.getOutputStream().write(builder.toString().getBytes("UTF-8"));
			
			urlConn.connect();

				
			in = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
			String s = null;
			while ((s = in.readLine()) != null) {
				connResult.append(s);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (out != null) {
					out.close();
				}
				
				if (in != null) {
					in.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return connResult.toString().trim();

	}
	
	
	
	
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p.passportName", "passport_0");
		params.put("p.password", SecurityUtil.encodeBase64("passport_0"));
		params.put("p.userIP", "127.0.0.1");
		params.put("p."+Constant.SIGN_KEY, SecurityUtil.getSign(SecurityUtil.getSignSalt(), "passport_0"));
		try {
			System.out.println(HttpRequestUtil.sendUrlByGet("http://192.168.41.186:8080/pfServer/passport/passport!userLogin", params));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
