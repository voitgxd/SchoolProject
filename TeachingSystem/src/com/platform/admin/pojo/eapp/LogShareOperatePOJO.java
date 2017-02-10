package com.platform.admin.pojo.eapp;

/**
 * 分享表
 * 
 * @author chentao
 * 
 */
public class LogShareOperatePOJO {
	private Integer logId;
	private Integer appId;// 应用id
	private Integer operatorType;// 操作类型：1-点赞，2-收藏，3-评论，4-点击，5-分享
	private Integer passportId;// 用户id
	private String operatorTime;// 操作时间
	private Integer userIp;// 用户ip
	private Integer loginType;// 登陆状态：1-账号登陆，0-游客登录

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}

	public Integer getUserIp() {
		return userIp;
	}

	public void setUserIp(Integer userIp) {
		this.userIp = userIp;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

}
