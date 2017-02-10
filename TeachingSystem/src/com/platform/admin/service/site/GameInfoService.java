package com.platform.admin.service.site;

import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏信息模块
 * 
 * @author gxd
 * 
 */
public interface GameInfoService {
	public PageInfo<GameInfoPOJO> queryAllGames();

	public int deleteGameInfo(GameInfoPOJO game);

	public int addGameInfo(GameInfoPOJO game);

	public PageInfo<GameInfoPOJO> getUpdateGame(GameInfoPOJO game);

	public int upDateGameInfo(GameInfoPOJO game);
}
