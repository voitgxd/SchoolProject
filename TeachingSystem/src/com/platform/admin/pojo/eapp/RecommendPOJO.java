/**
 * 
 */
package com.platform.admin.pojo.eapp;

/**
 * @author gxd
 *
 * 2015-3-23
 */
public class RecommendPOJO {
	private int appId;
	private String appName;
	private String appIcon;
	private int recommendType;
	private String recommendReason;
	private String recommendTime;
	private String effectTime;
	private String expireTime;
	private int passportId;
	private String userName;
	private int timeFlag;
	public int getTimeFlag() {
		return timeFlag;
	}
	public void setTimeFlag(int timeFlag) {
		this.timeFlag = timeFlag;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}
	public int getRecommendType() {
		return recommendType;
	}
	public void setRecommendType(int recommendType) {
		this.recommendType = recommendType;
	}
	public String getRecommendReason() {
		return recommendReason;
	}
	public void setRecommendReason(String recommendReason) {
		this.recommendReason = recommendReason;
	}
	public String getRecommendTime() {
		return recommendTime;
	}
	public void setRecommendTime(String recommendTime) {
		this.recommendTime = recommendTime;
	}
	public String getEffectTime() {
		return effectTime;
	}
	public void setEffectTime(String effectTime) {
		this.effectTime = effectTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public int getPassportId() {
		return passportId;
	}
	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "RecommendPOJO [appIcon=" + appIcon + ", appId=" + appId
				+ ", appName=" + appName + ", effectTime=" + effectTime
				+ ", expireTime=" + expireTime + ", passportId=" + passportId
				+ ", recommendReason=" + recommendReason + ", recommendTime="
				+ recommendTime + ", recommendType=" + recommendType
				+ ", userName=" + userName + "]";
	}
}
