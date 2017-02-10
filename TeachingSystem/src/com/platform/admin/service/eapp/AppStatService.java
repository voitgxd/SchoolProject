package com.platform.admin.service.eapp;

import com.platform.admin.pojo.eapp.AppStatPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏行为
 * 
 * @author gxd
 * 
 */
public interface AppStatService {
	// 查询游戏行为
	public void queryAppStat(PageInfo<AppStatPOJO> pageInfo);
}
