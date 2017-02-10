package com.platform.admin.dao.site;

import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏信息Dao
 * 
 * @author gxd
 * 
 */
public interface GameInfoDao {
	public PageInfo<GameInfoPOJO> queryAllGames();

	public int deleteGame(GameInfoPOJO game);

	public PageInfo<GameInfoPOJO> getUpdateGame(GameInfoPOJO game);

	public int updateGame(GameInfoPOJO game);

	public int addGameInfo(GameInfoPOJO game);
}
