/**
 * 
 */
package com.platform.admin.dao.eapp;

import com.platform.admin.pojo.eapp.AppTypePOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-3-3
 */
public interface AppTypeDAO {
	public void getAppTypesByPage(PageInfo<AppTypePOJO> pageInfo);
	public Integer deleteAppType(AppTypePOJO appType);
	public Integer getAppType(Integer typeId, PageInfo<AppTypePOJO> pageInfo);
	public Integer saveAppType(AppTypePOJO appType);
	public Integer updateAppType(AppTypePOJO appType);
	public int typeUpOrDown(int flag, int typeId);
	
	public void getTypeApp(Integer typeId, PageInfo<AppsPOJO> pageInfo);
	public void getNeedApp(Integer typeId, PageInfo<AppsPOJO> pageInfo);
	public Integer addAppToType(Integer typeId, String appIds);
	public Integer deleteAppFromType(int appId, int typeId);
	public int upOrDown(int flag, int typeId, int appId);
	public int updateTypePic(AppTypePOJO appType);
	public int setAppToTop(int typeId, int appId, String expireTime);
	public int cancelTop(int typeId, int appId);
}
