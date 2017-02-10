package com.platform.admin.pojo.eapp;

/**
 * 用户评论表
 * 
 * @author chentao
 * 
 */
public class LogPassportCommentPOJO {
	private Integer logId;
	private Integer appId;// 应用id
	private Integer passportId;// 玩家id
	private String commentTitle;// 评论标题
	private String commentContent;// 评论内容
	private Integer commentRating;// 评论星数
	private Integer commentState;// 1正常，-1删除
	private Integer clientIp;// 用户ip
	private String commentTime;// 评论时间

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

	public String getCommentTitle() {
		return commentTitle;
	}

	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentRating() {
		return commentRating;
	}

	public void setCommentRating(Integer commentRating) {
		this.commentRating = commentRating;
	}

	public Integer getCommentState() {
		return commentState;
	}

	public void setCommentState(Integer commentState) {
		this.commentState = commentState;
	}

	public Integer getClientIp() {
		return clientIp;
	}

	public void setClientIp(Integer clientIp) {
		this.clientIp = clientIp;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

}
