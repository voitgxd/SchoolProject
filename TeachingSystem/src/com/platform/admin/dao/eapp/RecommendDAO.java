/**
 * 
 */
package com.platform.admin.dao.eapp;

import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.pojo.eapp.RecommendPOJO;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-3-23
 */
public interface RecommendDAO {
	public void getRecommendApps(int recommendType, PageInfo<RecommendPOJO> pageInfo);
	public int cancelRecommend(int appId, int recommendType);
	public void toRecommendNeedApp(int recommendType, PageInfo<AppsPOJO> pageInfo);
	public int upOrDown(int flag, int recommendType, int appId);
	
	public int addAppToRecommend(RecommendPOJO recommend, String appIds);
}
