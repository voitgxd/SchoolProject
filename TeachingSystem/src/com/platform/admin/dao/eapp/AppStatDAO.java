package com.platform.admin.dao.eapp;

import com.platform.admin.pojo.eapp.AppStatPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏行为持久层
 * 
 * @author gxd
 * 
 */
public interface AppStatDAO {
	public void queryAppStat(PageInfo<AppStatPOJO> pageInfo);
}
