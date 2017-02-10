package com.platform.admin.pojo.eapp;

/**
 * 玩家登陆详情
 * 
 * @author chentao
 * 
 */
public class UserLoginPOJO {
	private Integer logId;
	private Integer passportId;
	private String loginTime;
	private String logoutTime;
	private Integer loginIp;
	private String country;
	private String province;
	private String city;
	private String loginFrom;// 登陆来源，网站地址
	private String clientInfo;// 客户端信息

	private Integer passportCount;
	private Integer loginCount;
	private String loadTime;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getLoginTime() {

		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Integer getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(Integer loginIp) {
		this.loginIp = loginIp;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLoginFrom() {
		return loginFrom;
	}

	public void setLoginFrom(String loginFrom) {
		this.loginFrom = loginFrom;
	}

	public String getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}

	public Integer getPassportCount() {
		return passportCount;
	}

	public void setPassportCount(Integer passportCount) {
		this.passportCount = passportCount;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(String loadTime) {
		this.loadTime = loadTime;
	}

}
