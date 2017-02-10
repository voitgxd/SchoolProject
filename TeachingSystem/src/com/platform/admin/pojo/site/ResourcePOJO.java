package com.platform.admin.pojo.site;

import java.util.Date;

/**
 * <p>
 * 资源封装类
 * </p>
 * 
 * @author Vicky
 * @date 2015-1-31
 */
public class ResourcePOJO {
	private int resId;
	private String resName;
	private String resUrl;
	private int resPid;
	private int resType;
	private Date createTime;
	private String resIcon;
	private int resOrder;
	private int sysFlag;

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public int getResPid() {
		return resPid;
	}

	public void setResPid(int resPid) {
		this.resPid = resPid;
	}

	public int getResType() {
		return resType;
	}

	public void setResType(int resType) {
		this.resType = resType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getResIcon() {
		return resIcon;
	}

	public void setResIcon(String resIcon) {
		this.resIcon = resIcon;
	}

	public int getResOrder() {
		return resOrder;
	}

	public void setResOrder(int resOrder) {
		this.resOrder = resOrder;
	}

	public int getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(int sysFlag) {
		this.sysFlag = sysFlag;
	}
	
	
}
