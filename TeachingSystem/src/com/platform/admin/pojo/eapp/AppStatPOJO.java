package com.platform.admin.pojo.eapp;

public class AppStatPOJO {
	/**
	 * 游戏行为
	 */
	private Integer appId;
	private Integer starNum;
	private Integer commentsNum;
	private Integer collectNum;
	private Integer likesNum;
	private Integer playerAmount;
	private Integer accessAmount;
	private Integer popularValue;

	private String appName;

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getStarNum() {
		return starNum;
	}

	public void setStarNum(Integer starNum) {
		this.starNum = starNum;
	}

	public Integer getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getLikesNum() {
		return likesNum;
	}

	public void setLikesNum(Integer likesNum) {
		this.likesNum = likesNum;
	}

	public Integer getPlayerAmount() {
		return playerAmount;
	}

	public void setPlayerAmount(Integer playerAmount) {
		this.playerAmount = playerAmount;
	}

	public Integer getAccessAmount() {
		return accessAmount;
	}

	public void setAccessAmount(Integer accessAmount) {
		this.accessAmount = accessAmount;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getPopularValue() {
		return popularValue;
	}

	public void setPopularValue(Integer popularValue) {
		this.popularValue = popularValue;
	}

}
