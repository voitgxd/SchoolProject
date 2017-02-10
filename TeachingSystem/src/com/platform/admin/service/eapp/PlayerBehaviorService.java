package com.platform.admin.service.eapp;

import com.platform.admin.pojo.eapp.PlayerBehaviorPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 玩家游戏行为业务层
 * 
 * @author gxd
 * 
 */
public interface PlayerBehaviorService {
	// 查询玩家游戏行为
	public void queryPlayerBehavior(PageInfo<PlayerBehaviorPOJO> pageInfo);

}
