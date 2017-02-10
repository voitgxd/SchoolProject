package com.platform.admin.common;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.platform.admin.pojo.site.AccountPOJO;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PropertiesCache;
import com.platform.admin.util.SecurityUtil;
import com.platform.admin.util.XmlRpcUtil;

/**
 * <p>
 * 与Passport中间件进行通信
 * </p>
 * 
 * @author Vicky
 * 
 */
public class PfServerInvoke {
	public static final Logger log = Logger.getLogger(PfServerInvoke.class);
	private static PfServerInvoke singleton = new PfServerInvoke();

	private XmlRpcUtil xmlrpc;

	private PfServerInvoke() {
		String pfserverUrl = PropertiesCache.getInstance().getString(Constant.PROJECT_CONFIG_NAME, "pfserverUrl", null);
		if (Common.checkNull(false, pfserverUrl)) {
			log.error("XmlRpcUtil.java is null...");
			throw new RuntimeException("XmlRpcUtil.java is null...");
		}
		int maxThreads = PropertiesCache.getInstance().getInt(Constant.PROJECT_CONFIG_NAME, "pfserverClientThreads",
				Constant.DEFAULT_PFSERVER_CLIENT_THREADS);
		xmlrpc = new XmlRpcUtil(pfserverUrl, maxThreads);
	}

	public static PfServerInvoke getInstance() {
		return singleton;
	}

	/**
	 * 认证token
	 * 
	 * @param token
	 * @return
	 */
	public AccountPOJO validateToken(String token) {
		if (Common.checkNull(false, token)) {
			return null;
		}
		Vector<String> params = new Vector<String>();
		params.add(token);
		params.add(SecurityUtil.getSign(token));
		String passportIdStr = "-1";
		try {
			passportIdStr = xmlrpc.invoke("ePassportMid.getPassportID", params);// 获取passportId
			long passportId = Long.parseLong(passportIdStr);
			if (passportId < 0) {
				return null;
			}
			AccountPOJO pp = new AccountPOJO();
			pp.setAccountId(passportId);
			return pp;
		} catch (Exception e) {
			log.error("getPassportID method with params[token:" + token + "], with result:" + passportIdStr
					+ " throw exception:" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
