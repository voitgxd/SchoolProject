package com.platform.admin.pojo.eapp;

/**
 * 标签管理实体类
 * 
 * @author chentao
 * 
 */
public class TagPOJO {
	private int tagId;// 标签ID
	private String tagName;// 标签名称
	private int platformType;// 平台类型
	private String addTime;// 添加时间

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getplatformType() {
		return platformType;
	}

	public void setplatformType(Integer platformType) {
		this.platformType = platformType;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
}
