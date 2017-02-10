/**
 * 
 */
package com.platform.admin.pojo.site;

/**
 * 游戏信息POJO
 * 
 * @author chentao
 * 
 */
public class GameInfoPOJO {
	private Integer gameId;// 游戏ID
	private String gameName;// 游戏名称
	private Integer gameType;// 游戏类型
	private Integer osType;// 操作系统类型
	private Integer gameState;// 游戏状态
	private Integer rankingOrder;// 游戏排名
	private Float dataPacket;// 游戏包大小
	private Integer gameEvaluate;// 星级评价
	private String pictureUrl;// 图片路径
	private String downloadUrl;// 下载路径
	private String forumUrl;// 论坛地址
	private String officialUrl;// 官方网址
	private String gameDescribe;// 游戏描述
	private String gameTypeName;// 游戏类型名称

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getGameType() {
		return gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

	public Integer getOsType() {
		return osType;
	}

	public void setOsType(Integer osType) {
		this.osType = osType;
	}

	public Integer getGameState() {
		return gameState;
	}

	public void setGameState(Integer gameState) {
		this.gameState = gameState;
	}

	public Integer getRankingOrder() {
		return rankingOrder;
	}

	public void setRankingOrder(Integer rankingOrder) {
		this.rankingOrder = rankingOrder;
	}

	public Float getDataPacket() {
		return dataPacket;
	}

	public void setDataPacket(Float dataPacket) {
		this.dataPacket = dataPacket;
	}

	public Integer getGameEvaluate() {
		return gameEvaluate;
	}

	public void setGameEvaluate(Integer gameEvaluate) {
		this.gameEvaluate = gameEvaluate;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getOfficialUrl() {
		return officialUrl;
	}

	public void setOfficialUrl(String officialUrl) {
		this.officialUrl = officialUrl;
	}

	public String getForumUrl() {
		return forumUrl;
	}

	public void setForumUrl(String forumUrl) {
		this.forumUrl = forumUrl;
	}

	public String getGameDescribe() {
		return gameDescribe;
	}

	public void setGameDescribe(String gameDescribe) {
		this.gameDescribe = gameDescribe;
	}

	public String getGameTypeName() {
		return gameTypeName;
	}

	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}

}
