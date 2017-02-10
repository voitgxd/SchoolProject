package com.platform.admin.service.eapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.platform.admin.dao.eapp.PlayerBehaviorDAO;
import com.platform.admin.pojo.eapp.PlayerBehaviorPOJO;
import com.platform.admin.service.eapp.PlayerBehaviorService;
import com.platform.admin.util.PageInfo;

/**
 * 玩家游戏行为业务实现
 * 
 * @author chentao
 * 
 */
@Service("playerBehaviorService")
public class PlayerBehaviorServiceImpl implements PlayerBehaviorService {
	@Autowired
	@Qualifier("playerBehaviorDao")
	private PlayerBehaviorDAO playerBehaviorDao;

	// 查询玩家游戏行为
	public void queryPlayerBehavior(PageInfo<PlayerBehaviorPOJO> pageInfo) {
		playerBehaviorDao.queryPlayerBehavior(pageInfo);
	}

	public PlayerBehaviorDAO getPlayerBehaviorDao() {
		return playerBehaviorDao;
	}

	public void setPlayerBehaviorDao(PlayerBehaviorDAO playerBehaviorDao) {
		this.playerBehaviorDao = playerBehaviorDao;
	}

}
