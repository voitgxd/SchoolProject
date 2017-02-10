package com.platform.admin.pojo.site;

import com.platform.admin.util.JSONUtil;

/**
 * <p>
 * 封装账号信息
 * </p>
 * 
 * @author Vicky
 * 
 */
public class AccountPOJO {
	private long accountId;// 账号ID
	private String accountName;// 账号名
	private String iconUrl;// 头像地址
	private String token;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 从json读取数据
	 * 
	 * @param json
	 */
	public void parseFromJson(String json) {
		JSONUtil info = JSONUtil.fromObject(json);
		if (null != info) {
			this.setAccountId(info.getLong("passportId"));
			this.setAccountName(info.getString("passportName"));
			this.setIconUrl(info.getString("iconUrl"));
		}
	}
}
