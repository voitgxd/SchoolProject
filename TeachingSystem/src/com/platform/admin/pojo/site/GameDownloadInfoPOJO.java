/**
 * 
 */
package com.platform.admin.pojo.site;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gxd
 *
 * 2015-2-9
 */
public class GameDownloadInfoPOJO {
	private Integer logId;
	private Integer queryType;
	private Integer gameType;
	private String downloadTime;
	private String downloadUrl;
	private String downloadSource;
	private String downloadIp;
	private String userAgent;
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getQueryType() {
		return queryType;
	}
	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}
	public Integer getGameType() {
		return gameType;
	}
	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}
	public String getDownloadTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date d = sdf.parse(this.downloadTime);
			downloadTime = sdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return downloadTime;
	}
	public void setDownloadTime(String downloadTime) {
		this.downloadTime = downloadTime;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getDownloadSource() {
		return downloadSource;
	}
	public void setDownloadSource(String downloadSource) {
		this.downloadSource = downloadSource;
	}
	public String getDownloadIp() {
		return downloadIp;
	}
	public void setDownloadIp(String downloadIp) {
		this.downloadIp = downloadIp;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
