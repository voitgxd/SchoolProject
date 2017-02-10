package com.platform.admin.pojo.site;

/**
 * 礼包实体类
 * 
 * @author chentao
 * 
 */
public class GiftPackagePOJO {
	private Integer packageId;// 礼包id
	private Integer gameId;// 游戏id
	private Integer packageType;// 礼包类型：1激活码，2特权礼包，3新服礼包，4，公会礼包
	private String packageTitle;// 礼包标题
	private String packageDescribe;// 礼包描述
	private String beginTime;// 开始时间
	private String endTime;// 结束时间
	private String useMethod;// 使用方法

	private String gameName;// 游戏名称
	private Integer osType;// 操作系统类型

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public String getPackageTitle() {
		return packageTitle;
	}

	public void setPackageTitle(String packageTitle) {
		this.packageTitle = packageTitle;
	}

	public String getPackageDescribe() {
		return packageDescribe;
	}

	public void setPackageDescribe(String packageDescribe) {
		this.packageDescribe = packageDescribe;
	}

	public String getBeginTime() {

		return this.beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {

		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUseMethod() {
		return useMethod;
	}

	public void setUseMethod(String useMethod) {
		this.useMethod = useMethod;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getOsType() {
		return osType;
	}

	public void setOsType(Integer osType) {
		this.osType = osType;
	}

	@Override
	public String toString() {
		return "GiftPackagePOJO [beginTime=" + beginTime + ", endTime="
				+ endTime + ", gameId=" + gameId + ", gameName=" + gameName
				+ ", osType=" + osType + ", packageDescribe=" + packageDescribe
				+ ", packageId=" + packageId + ", packageTitle=" + packageTitle
				+ ", packageType=" + packageType + ", useMethod=" + useMethod
				+ "]";
	}

}
