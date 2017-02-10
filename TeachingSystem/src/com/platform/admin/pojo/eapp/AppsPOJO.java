/**
 * 
 */
package com.platform.admin.pojo.eapp;

/**
 * @author gxd
 *
 * 2015-3-3
 */
public class AppsPOJO {
	private Integer flag;
	private Integer appId;
    private String appName; 
    private String appIcon; 
    private Integer platformType;
    private String appUrl;
    private Integer appType; 
    private String appSummary;
    private String appDesc;
    private String submitTime;
    private String addTime; 
    private Integer developerId;
    private Integer appState;
    private int stickState;//查询分类所属应用时用于检查是否置顶
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
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
	public Integer getPlatformType() {
		return platformType;
	}
	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public Integer getAppType() {
		return appType;
	}
	public void setAppType(Integer appType) {
		this.appType = appType;
	}
	
	public String getAppSummary() {
		return appSummary;
	}
	public void setAppSummary(String appSummary) {
		this.appSummary = appSummary;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}
	public Integer getAppState() {
		return appState;
	}
	public void setAppState(Integer appState) {
		this.appState = appState;
	}
	public int getStickState() {
		return stickState;
	}
	public void setStickState(int stickState) {
		this.stickState = stickState;
	}
	@Override
	public String toString() {
		return "AppsPOJO [addTime=" + addTime + ", appDesc=" + appDesc
				+ ", appIcon=" + appIcon + ", appId=" + appId + ", appName="
				+ appName + ", appState=" + appState + ", appType=" + appType
				+ ", appUrl=" + appUrl + ", developerId=" + developerId
				+ ", flag=" + flag + ", platformType=" + platformType + "]";
	}
	
}
