/**
 * 
 */
package com.platform.admin.service.eapp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.eapp.impl.AppsDAOImpl;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.service.eapp.AppsService;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-3-3
 */
@Service("appsService")
public class AppsServiceImpl implements AppsService {
	@Autowired
	@Qualifier("appsDAO")
	private AppsDAOImpl appsDAO;

	public void getUncheckedApps(int appStateFlag, PageInfo<AppsPOJO> pageInfo) {
		appsDAO.getUncheckedApps(appStateFlag, pageInfo);
	}
	
	public void getApps(PageInfo<AppsPOJO> pageInfo) {
		appsDAO.getAppsByPage(pageInfo);
	}

	public Integer deleteApps(AppsPOJO app) {
		return appsDAO.deleteApps(app);
	}

	
	public Integer getApp(Integer appId, PageInfo<AppsPOJO> pageInfo) {
		return appsDAO.getApp(appId, pageInfo);
	}

	public Integer saveApp(AppsPOJO app) {
		return appsDAO.saveApp(app);
	}

	public Integer updateApp(AppsPOJO app) {
		return appsDAO.updateApp(app);
	}

	public Integer addAppIcon(AppsPOJO app) {
		return appsDAO.addAppIcon(app);
	}

	public void insertApp(List<AppsPOJO> appList) {
		appsDAO.insertApp(appList);
	}

	public Integer checkApp(String appIds, Integer statusCode) {
		return appsDAO.checkApp(appIds, statusCode);
	}

	public int updateAppUrl(AppsPOJO app) {
		return appsDAO.updateAppUrl(app);
	}

}
