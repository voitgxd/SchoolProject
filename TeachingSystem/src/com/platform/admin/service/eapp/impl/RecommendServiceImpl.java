/**
 * 
 */
package com.platform.admin.service.eapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.platform.admin.dao.eapp.impl.RecommendDAOImpl;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.pojo.eapp.RecommendPOJO;
import com.platform.admin.service.eapp.RecommendService;
import com.platform.admin.util.PageInfo;
/**
 * @author gxd
 *
 * 2015-3-23
 */
@Service("recommendService")
public class RecommendServiceImpl implements RecommendService {
	
	@Autowired
	@Qualifier("recommendDAO")
	private RecommendDAOImpl recommendDAO;

	public void getRecommendApps(int recommendType, PageInfo<RecommendPOJO> pageInfo) {
		recommendDAO.getRecommendApps(recommendType, pageInfo);
	}

	public int cancelRecommend(int appId, int recommendType) {
		return recommendDAO.cancelRecommend(appId, recommendType);
	}

	public void toRecommendNeedApp(int recommendType,
			PageInfo<AppsPOJO> pageInfo) {
		recommendDAO.toRecommendNeedApp(recommendType, pageInfo);
	}

	public int addAppToRecommend(RecommendPOJO recommend, String appIds) {
		return recommendDAO.addAppToRecommend(recommend, appIds);
	}

	public int upOrDown(int flag, int recommendType, int appId) {
		return recommendDAO.upOrDown(flag, recommendType, appId);
	}

}
