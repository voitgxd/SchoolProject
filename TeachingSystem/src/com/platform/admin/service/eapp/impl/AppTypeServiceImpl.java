/**
 * 
 */
package com.platform.admin.service.eapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.eapp.impl.AppTypeDAOImpl;
import com.platform.admin.pojo.eapp.AppTypePOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.service.eapp.AppTypeService;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-3-3
 */
@Service("appTypeService")
public class AppTypeServiceImpl implements AppTypeService {
	@Autowired
	@Qualifier("appTypeDAO")
	private AppTypeDAOImpl appTypeDAO;

	public void getAppTypes(PageInfo<AppTypePOJO> pageInfo) {
		appTypeDAO.getAppTypesByPage(pageInfo);
	}

	public Integer deleteAppType(AppTypePOJO appType) {
		return appTypeDAO.deleteAppType(appType);
	}

	
	public Integer getAppType(Integer typeId, PageInfo<AppTypePOJO> pageInfo) {
		return appTypeDAO.getAppType(typeId, pageInfo);
	}

	public Integer saveAppType(AppTypePOJO appType) {
		return appTypeDAO.saveAppType(appType);
	}

	public Integer updateAppType(AppTypePOJO appType) {
		return appTypeDAO.updateAppType(appType);
	}
	
	public void getTypeApp(Integer typeId, PageInfo<AppsPOJO> pageInfo) {
		appTypeDAO.getTypeApp(typeId, pageInfo);
	}
	
	public int typeUpOrDown(int flag, int typeId) {
		return appTypeDAO.typeUpOrDown(flag, typeId);
	}

	public void getNeedApp(Integer typeId, PageInfo<AppsPOJO> pageInfo) {
		appTypeDAO.getNeedApp(typeId, pageInfo);
	}

	public Integer addAppToType(Integer typeId, String appIds) {
		return appTypeDAO.addAppToType(typeId, appIds);
	}

	public Integer deleteAppFromType(int appId, int typeId) {
		return appTypeDAO.deleteAppFromType(appId, typeId);
	}

	public int upOrDown(int flag, int typeId, int appId) {
		return appTypeDAO.upOrDown(flag, typeId, appId);
	}
	
	public int updateTypePic(AppTypePOJO appType) {
		return appTypeDAO.updateTypePic(appType);
	}
	
	public int setAppToTop(int typeId, int appId, String expireTime) {
		return appTypeDAO.setAppToTop(typeId, appId, expireTime);
	}

	public int cancelTop(int typeId, int appId) {
		return appTypeDAO.cancelTop(typeId, appId);
	}
}
