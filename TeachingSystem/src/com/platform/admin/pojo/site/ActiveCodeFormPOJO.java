package com.platform.admin.pojo.site;

/**
 * 返回激活码查询数据集
 * 
 * @author chentao
 * 
 */
public class ActiveCodeFormPOJO {
	private String codeId;// 激活码id
	private String packageId;// 礼包id
	private Integer codeStat;// 激活码状态:1,未领取;2,领取
	private String getTime;// 领取时间
	private Integer userId;// 领取账号
	private String userName;// 用户通行证

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public Integer getCodeStat() {
		return codeStat;
	}

	public void setCodeStat(Integer codeStat) {
		this.codeStat = codeStat;
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
