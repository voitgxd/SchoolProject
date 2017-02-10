package com.platform.admin.dao.site.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.platform.admin.dao.site.AccountDAO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.AccountPOJO;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.HttpRequestUtil;
import com.platform.admin.util.JSONUtil;
import com.platform.admin.util.SecurityUtil;

@Repository("accountDao")
public class AccountDAOImpl implements AccountDAO {
	public static final Logger log = Logger.getLogger(AccountDAOImpl.class);

	/**
	 * 登录
	 */
	public int login(String passportName, String password, String loginIp, AccountPOJO account) {
		int resultCode = Constant.SYSTEM_ERROR;
		String pfserver = Common.getLoginUrl();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p.passportName", passportName);
		params.put("p.password", SecurityUtil.encodeBase64(password));
		params.put("p.userIp", loginIp);
		params.put("p."+Constant.SIGN_KEY, SecurityUtil.getSign(SecurityUtil.getSignSalt(), passportName));
		try {
			String response = HttpRequestUtil.sendPostRequest(pfserver, params);
//			log.info("mthod:login, params:[passportName:" + passportName + ",password:" + password
//					+ ",loginIp:" + loginIp + ", response:" + response + "]");
			JSONUtil json = JSONUtil.fromObject(response);
			if (null != json) {
				resultCode = json.getInt(Constant.RESULT_CODE_KEY);
				String token = json.getString(Constant.TOKEN_KEY);
				if (resultCode == Constant.RET_OK && Common.checkNull(false, token)) {
					JSONUtil info = json.getJSONUtil(Constant.RESULT_INFO_KEY);
					account.setAccountId(info.getLong("passportId"));
					account.setAccountName(info.getString("passportName"));
					account.setIconUrl(info.getString("iconUrl"));
					account.setToken(info.getString("token"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return resultCode;
	}

	/**
	 * 校验Token
	 * @throws Exception 
	 */
	public int validateToken(String passportId,String tokenId, String loginIp, AccountPOJO account) throws Exception {
		int resultCode = Constant.SYSTEM_ERROR;
		String pfserver = Common.getTokenUrl();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p.passportId",passportId);
		params.put("p.token",tokenId);
		params.put("p.userIp",loginIp);
		params.put("p."+Constant.SIGN_KEY, SecurityUtil.getSign(SecurityUtil.getSignSalt(), passportId));
		try {
			String response = HttpRequestUtil.sendPostRequest(pfserver, params);
//			log.info("mthod:validateToken, params:[passportId:"+passportId+",token:" + tokenId + ", response:" + response + "]");
			JSONUtil json = JSONUtil.fromObject(response);
			if (null != json) {
				resultCode = json.getInt(Constant.RESULT_CODE_KEY);
				if (resultCode == Constant.RET_OK) {
					JSONUtil info = json.getJSONUtil(Constant.RESULT_INFO_KEY);
					account.setAccountName(info.getString("passportName"));
					account.setIconUrl(info.getString("iconUrl"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return resultCode;
	}

	public int validateUser(String passportName, String userIp,
			UserPOJO userPOJO) throws Exception {
		int resultCode = Constant.SYSTEM_ERROR;
		String validateUrl = Common.getValidateUrl();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p.passportName",passportName);
		params.put("p.userIP",userIp);
		params.put("p."+Constant.SIGN_KEY, SecurityUtil.getSign(SecurityUtil.getSignSalt(), passportName));
		try {
			String response = HttpRequestUtil.sendPostRequest(validateUrl, params);
//			log.info("mthod:validateToken, params:[passportName:" + passportName + ", response:" + response + "]");
			JSONUtil json = JSONUtil.fromObject(response);
			if (null != json) {
				resultCode = json.getInt(Constant.RESULT_CODE_KEY);
				String resultInfo=json.getString(Constant.RESULT_INFO_KEY);
				if (resultCode == Constant.RET_OK) {
					JSONUtil info = JSONUtil.fromObject(resultInfo);
					userPOJO.setUserId(info.getLong("passportId"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return resultCode;
	}
	
	
	
	
}
