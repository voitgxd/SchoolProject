package com.platform.admin.web.form;

/**
 * <p>
 * 封装登录信息
 * </p>
 * 
 * @author Vicky
 * 
 */
public class LoginForm {
	private String accountName;
	private String accountPwd;
	private String sessionId;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
