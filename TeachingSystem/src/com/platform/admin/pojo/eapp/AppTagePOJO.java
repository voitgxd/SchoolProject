package com.platform.admin.pojo.eapp;

/**
 * 游戏标签实体类
 * 
 * @author chentao
 * 
 */
public class AppTagePOJO {
	private Integer appId;// 应用id
	private Integer tagId;// 标签id
	private String addTime;// 添加时间

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}
