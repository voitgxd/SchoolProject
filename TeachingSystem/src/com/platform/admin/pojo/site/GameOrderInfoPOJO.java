/**
 * 
 */
package com.platform.admin.pojo.site;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 游戏排行
 * @author gxd
 *
 * 2015-1-30
 */
public class GameOrderInfoPOJO {
	

	private Integer gameId;
	private String gameName;
	private Integer queryType;
	private Integer orderId;
	private Float gameScore;
	private Integer orderState;
	private Float initScore;
	private Integer gameState;
	private String statTime;	//统计时间
	private String pictureUrl;
	private String innerName;	
	private String gameUrl;	//链接地址
	
	private Integer flag;//保存还是修改的标示数
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
	public Integer getQueryType() {
		return queryType;
	}
	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	
	public Float getGameScore() {
		return gameScore;
	}
	public void setGameScore(Float gameScore) {
		this.gameScore = gameScore;
	}
	public Float getInitScore() {
		return initScore;
	}
	public void setInitScore(Float initScore) {
		this.initScore = initScore;
	}
	public Integer getGameState() {
		return gameState;
	}
	public void setGameState(Integer gameState) {
		this.gameState = gameState;
	}
	
	public String getStatTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date d = sdf.parse(this.statTime);
			statTime = sdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return statTime;
	}
	public void setStatTime(String statTime) {
		this.statTime = statTime;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public String getInnerName() {
		return innerName;
	}
	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}
	public String getGameUrl() {
		return gameUrl;
	}
	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "GameOrderInfoPOJO [gameId=" + gameId + ", gameName=" + gameName
				+ ", gameScore=" + gameScore + ", gameState=" + gameState
				+ ", gameUrl=" + gameUrl + ", initScore=" + initScore
				+ ", innerUame=" + innerName + ", orderId=" + orderId
				+ ", orderState=" + orderState + ", pictureUrl=" + pictureUrl
				+ ", queryType=" + queryType + ", statTime=" + statTime + "]";
	}
}
