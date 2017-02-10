package com.platform.admin.pojo.site;

/**
 * 查询条件所有要的参数
 * 
 * @author chentao
 * 
 */
public class SearchPOJO {
	private long codeStat;// 激活码状态:1,未领取;2,领取

	public long getCodeStat() {
		return codeStat;
	}

	public void setCodeStat(long codeStat) {
		this.codeStat = codeStat;
	}

}
