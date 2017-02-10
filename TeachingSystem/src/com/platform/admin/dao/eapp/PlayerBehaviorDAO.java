package com.platform.admin.dao.eapp;

import com.platform.admin.pojo.eapp.PlayerBehaviorPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 玩家行为持久层
 * 
 * @author gxd
 * 
 */
public interface PlayerBehaviorDAO {
	// 查看玩家行为信息统计
	public void queryPlayerBehavior(PageInfo<PlayerBehaviorPOJO> pageInfo);

}
