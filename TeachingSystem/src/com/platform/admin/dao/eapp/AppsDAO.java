/**
 * 
 */
package com.platform.admin.dao.eapp;

import java.util.List;

import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-3-3
 */
public interface AppsDAO {
	public void getAppsByPage(PageInfo<AppsPOJO> pageInfo);
	public void getUncheckedApps(int appStateFlag, PageInfo<AppsPOJO> pageInfo);
	public Integer deleteApps(AppsPOJO app);
	public Integer getApp(Integer appId, PageInfo<AppsPOJO> pageInfo);
	public Integer saveApp(AppsPOJO app);
	public Integer updateApp(AppsPOJO app);
	public int updateAppUrl(AppsPOJO app);
	public Integer addAppIcon(AppsPOJO app);
	public void insertApp(List<AppsPOJO> appList);
	public Integer checkApp(String appIds, Integer statusCode);
}
