package com.platform.admin.pojo.eapp;

/**
 * 用户应用积分榜
 * 
 * @author chentao
 * 
 */
public class LogAppScorePOJO {
	private Integer logId;
	private Integer appId;// 游戏id
	private Integer passportId;// 玩家id
	private Integer score;// 积分
	private Integer clientIp;// 用户ip
	private String addTime;// 添加时间
	private Integer loginType;// 登陆状态：1账号登陆，2游客登录

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

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getClientIp() {
		return clientIp;
	}

	public void setClientIp(Integer clientIp) {
		this.clientIp = clientIp;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

}
