package com.platform.admin.pojo.common;

import java.io.Serializable;

public class FileInfoPOJO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4966076362598622296L;
	
	private int fileId;
	private long passportId;
	private String fileAddress;
	private String url;
	private String createTime;
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	
	
	public long getPassportId() {
		return passportId;
	}
	public void setPassportId(long passportId) {
		this.passportId = passportId;
	}
	
	public String getFileAddress() {
		return fileAddress;
	}
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	

}
