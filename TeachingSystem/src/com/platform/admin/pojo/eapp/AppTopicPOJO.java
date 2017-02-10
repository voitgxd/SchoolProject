/**
 * 
 */
package com.platform.admin.pojo.eapp;

/**
 * @author gxd
 * 
 *         2015-3-11
 */
public class AppTopicPOJO {
	private int flag;
	private int topicId;
	private String topicName;
	private String topicDesc;
	private int platformType;
	private long updatePassportId;
	private String expireTime;
	private String addTime;

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	private int topicState;
	private int orderNum;
	private String topicPic;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	public int getPlatformType() {
		return platformType;
	}

	public void setPlatformType(int platformType) {
		this.platformType = platformType;
	}

	public long getUpdatePassportId() {
		return updatePassportId;
	}

	public void setUpdatePassportId(long updatePassportId) {
		this.updatePassportId = updatePassportId;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public int getTopicState() {
		return topicState;
	}

	public void setTopicState(int topicState) {
		this.topicState = topicState;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getTopicPic() {
		return topicPic;
	}

	public void setTopicPic(String topicPic) {
		this.topicPic = topicPic;
	}
}
