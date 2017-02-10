package com.platform.admin.service.site.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.site.GameInfoDao;
import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.service.site.GameInfoService;
import com.platform.admin.util.PageInfo;

/**
 * 游戏信息Service
 * 
 * @author gxd
 * 
 */
@Service("gameInfoService")
public class GameInfoServiceImpl implements GameInfoService {
	@Autowired
	@Qualifier("gameInfoDao")
	private GameInfoDao gameInfoDao;

	public int addGameInfo(GameInfoPOJO game) {
		return gameInfoDao.addGameInfo(game);
	}

	public int deleteGameInfo(GameInfoPOJO game) {
		return gameInfoDao.deleteGame(game);
	}

	public PageInfo<GameInfoPOJO> getUpdateGame(GameInfoPOJO game) {
		return gameInfoDao.getUpdateGame(game);
	}

	public PageInfo<GameInfoPOJO> queryAllGames() {
		return gameInfoDao.queryAllGames();
	}

	public int upDateGameInfo(GameInfoPOJO game) {
		return gameInfoDao.updateGame(game);
	}

	public GameInfoDao getGameInfoDao() {
		return gameInfoDao;
	}

	public void setGameInfoDao(GameInfoDao gameInfoDao) {
		this.gameInfoDao = gameInfoDao;
	}

}
