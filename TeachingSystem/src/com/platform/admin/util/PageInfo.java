package com.platform.admin.util;

import java.util.List;

public class PageInfo<T> {

	private int pageSize;// 每页的最大行数
	private int pageIndex;// 当前页
	private long codeStat;
	private int queryGameType = -1;// 用于条件查询的游戏类型(-1查询全部)

	private int totalSize;// 总行数
	private int pageNum;// 总页数

	private List<T> list;

	private int minOrderId;
	private int maxOrderId;// 最大排序ID
	private String beginTime;
	private String endTime;
	private long passportId;
	private String tagName;
	
	private String queryGameName = ""; //用于条件查询的游戏名称(""为查询全部)

	public String getQueryGameName() {
		return queryGameName;
	}

	public void setQueryGameName(String queryGameName) {
		this.queryGameName = queryGameName;
	}

	public int getPageNum() {
		if (pageSize == 0) {
			pageNum = 0;
		} else if (totalSize % pageSize == 0) {
			pageNum = totalSize / pageSize;
		} else {
			pageNum = totalSize / pageSize + 1;
		}

		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public void setInfoList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public long getCodeStat() {
		return codeStat;
	}

	public void setCodeStat(long codeStat) {
		this.codeStat = codeStat;
	}

	public int getMaxOrderId() {
		return maxOrderId;
	}

	public void setMaxOrderId(int maxOrderId) {
		this.maxOrderId = maxOrderId;
	}

	public int getMinOrderId() {
		return minOrderId;
	}

	public void setMinOrderId(int minOrderId) {
		this.minOrderId = minOrderId;
	}

	public int getQueryGameType() {
		return queryGameType;
	}

	public void setQueryGameType(int queryGameType) {
		this.queryGameType = queryGameType;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public long getPassportId() {
		return passportId;
	}

	public void setPassportId(long passportId) {
		this.passportId = passportId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
