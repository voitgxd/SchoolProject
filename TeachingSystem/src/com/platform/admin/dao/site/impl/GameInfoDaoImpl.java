package com.platform.admin.dao.site.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.platform.admin.dao.common.BaseDAO;
import com.platform.admin.dao.common.StoredProcedureDAO;
import com.platform.admin.dao.site.GameInfoDao;
import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏信息DaoImpl
 * 
 * @author gxd
 * 
 */
@Repository("gameInfoDao")
public class GameInfoDaoImpl extends BaseDAO implements GameInfoDao {
	public static final Logger log = Logger.getLogger(GameInfoDaoImpl.class);

	// 添加游戏信息
	public int addGameInfo(GameInfoPOJO game) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.addGame");
		Integer result = null;
		try {
			sp.addParameter("n_game_name", Types.VARCHAR, game.getGameName());
			sp.addParameter("n_game_type", Types.INTEGER, game.getGameType());
			sp.addParameter("n_os_type", Types.INTEGER, game.getOsType());
			sp.addParameter("n_game_state", Types.INTEGER, game.getGameState());
			sp.addParameter("n_ranking_order", Types.INTEGER, game
					.getRankingOrder());
			sp.addParameter("n_data_packet", Types.INTEGER, game
					.getDataPacket());
			sp.addParameter("n_game_evaluate", Types.INTEGER, game
					.getGameEvaluate());
			sp.addParameter("s_picture_url", Types.VARCHAR, game
					.getPictureUrl());
			sp.addParameter("s_download_url", Types.VARCHAR, game
					.getDownloadUrl());
			sp.addParameter("s_forum_url", Types.VARCHAR, game.getForumUrl());
			sp.addParameter("s_official_url", Types.VARCHAR, game
					.getOfficialUrl());
			sp.addParameter("s_game_describe", Types.VARCHAR, game
					.getGameDescribe());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();
			result = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

	// 删除游戏信息
	public int deleteGame(GameInfoPOJO game) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.deleteGame");
		Integer result = null;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, game.getGameId());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();
			result = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

	// 获取要修改的游戏信息
	@SuppressWarnings("unchecked")
	public PageInfo<GameInfoPOJO> getUpdateGame(GameInfoPOJO game) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getOneUpdate");
		PageInfo<GameInfoPOJO> pageInfo = null;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, game.getGameId());
			sp.addOutCursorParameter("cur_result", GameInfoPOJO.class);
			Map<String, Object> resMap = sp.execute();
			pageInfo = new PageInfo<GameInfoPOJO>();
			pageInfo.setInfoList((List<GameInfoPOJO>) resMap.get("cur_result"));
		} catch (Exception e) {
			log.error(e);
		}

		return pageInfo;
	}

	// 查询所有的游戏信息
	@SuppressWarnings("unchecked")
	public PageInfo<GameInfoPOJO> queryAllGames() {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getAllGames");
		PageInfo<GameInfoPOJO> pageInfo = null;
		try {
			sp.addOutCursorParameter("cur_result", GameInfoPOJO.class);
			Map<String, Object> resMap = sp.execute();
			pageInfo = new PageInfo<GameInfoPOJO>();
			pageInfo.setInfoList((List<GameInfoPOJO>) resMap.get("cur_result"));
		} catch (Exception e) {
			log.error(e);
		}

		return pageInfo;
	}

	// 修改游戏信息
	public int updateGame(GameInfoPOJO game) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.updateGame");
		Integer result = null;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, game.getGameId());
			sp.addParameter("n_game_name", Types.VARCHAR, game.getGameName());
			sp.addParameter("n_game_type", Types.INTEGER, game.getGameType());
			sp.addParameter("n_os_type", Types.INTEGER, game.getOsType());
			sp.addParameter("n_game_state", Types.INTEGER, game.getGameState());
			sp.addParameter("n_ranking_order", Types.INTEGER, game
					.getRankingOrder());
			sp.addParameter("n_data_packet", Types.INTEGER, game
					.getDataPacket());
			sp.addParameter("n_game_evaluate", Types.INTEGER, game
					.getGameEvaluate());
			sp.addParameter("s_picture_url", Types.VARCHAR, game
					.getPictureUrl());
			sp.addParameter("s_download_url", Types.VARCHAR, game
					.getDownloadUrl());
			sp.addParameter("s_forum_url", Types.VARCHAR, game.getForumUrl());
			sp.addParameter("s_official_url", Types.VARCHAR, game
					.getOfficialUrl());
			sp.addParameter("s_game_describe", Types.VARCHAR, game
					.getGameDescribe());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();
			result = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

}
