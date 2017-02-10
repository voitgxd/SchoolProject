/**
 * 
 */
package com.platform.admin.dao.site.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.platform.admin.dao.common.BaseDAO;
import com.platform.admin.dao.common.StoredProcedureDAO;
import com.platform.admin.dao.site.GameOrderDAO;
import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.pojo.site.GameOrderInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏排行模块
 * 
 * @author gxd
 * 
 *         2015-1-30
 */
@Repository("gameOrderDAO")
public class GameOrderDAOImpl extends BaseDAO implements GameOrderDAO {
	
	public static final Logger log = Logger.getLogger(GameOrderDAOImpl.class);

	@SuppressWarnings("unchecked")
	public PageInfo<GameOrderInfoPOJO> getAll() {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getAllGameTopLists", false);
		PageInfo<GameOrderInfoPOJO> pageInfo = null;
		try {
			sp.addOutParameter("n_min_order_id", Types.INTEGER);
			sp.addOutParameter("n_max_order_id", Types.INTEGER);
			sp.addOutCursorParameter("cur_result", GameOrderInfoPOJO.class);
			Map<String, Object> resMap = sp.execute();
			pageInfo = new PageInfo<GameOrderInfoPOJO>();
			pageInfo.setMinOrderId((Integer) resMap.get("n_min_order_id"));
			pageInfo.setMaxOrderId((Integer) resMap.get("n_max_order_id"));
			pageInfo.setInfoList((List<GameOrderInfoPOJO>) resMap.get("cur_result"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return pageInfo;
	}

	/**
	 * 获取要修改的gameOrder信息
	 */
	@SuppressWarnings("unchecked")
	public GameOrderInfoPOJO getOrder(Integer gameId) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getOneGameOrder", false);
		GameOrderInfoPOJO order = null;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, gameId);
			sp.addOutCursorParameter("cur_result", GameOrderInfoPOJO.class);
			Map<String, Object> resMap = sp.execute();
			order = new GameOrderInfoPOJO();
			List<GameOrderInfoPOJO> orders = (List<GameOrderInfoPOJO>) resMap
					.get("cur_result");
			for (GameOrderInfoPOJO game : orders) {
				order.setGameId(game.getGameId());
				order.setGameName(game.getGameName());
				order.setGameScore(game.getGameScore());
				order.setGameState(game.getGameState());
				order.setGameUrl(game.getGameUrl());
				order.setInitScore(game.getInitScore());
				order.setInnerName(game.getInnerName());
				order.setOrderId(game.getOrderId());
				order.setOrderState(game.getOrderState());
				order.setPictureUrl(game.getPictureUrl());
				order.setQueryType(game.getQueryType());
				order.setStatTime(game.getStatTime());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return order;
	}

	/**
	 * 修改游戏排行数据
	 */
	public Integer updateGameOrder(GameOrderInfoPOJO order) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.updateGameTopList", false);
		int status = 1;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, order.getGameId());
			sp.addParameter("s_game_name", Types.VARCHAR, order.getGameName());
			sp.addParameter("n_query_type", Types.INTEGER, order.getQueryType());
			sp.addParameter("n_order_id", Types.INTEGER, order.getOrderId());
			sp.addParameter("n_game_score", Types.FLOAT, order.getGameScore());
			sp.addParameter("n_order_state", Types.INTEGER, order.getOrderState());
			sp.addParameter("n_init_score", Types.FLOAT, order.getInitScore());
			sp.addParameter("n_game_state", Types.INTEGER, order.getGameState());
			sp.addParameter("d_stat_time", Types.VARCHAR, order.getStatTime());
			sp.addParameter("s_picture_url", Types.VARCHAR, order.getPictureUrl());
			sp.addParameter("s_inner_name", Types.VARCHAR, order.getInnerName());
			sp.addParameter("s_game_url", Types.VARCHAR, order.getGameUrl());

			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();

			status = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return status;
	}

	/**
	 * 保存游戏排行数据
	 */
	public Integer saveGameOrder(GameOrderInfoPOJO order) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.addGameTopList", false);
		int status = 1;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, order.getGameId());
			sp.addParameter("s_game_name", Types.VARCHAR, order.getGameName());
			sp.addParameter("n_query_type", Types.INTEGER, order.getQueryType());
			sp.addParameter("n_order_id", Types.INTEGER, order.getOrderId());
			sp.addParameter("n_game_score", Types.FLOAT, order.getGameScore());
			sp.addParameter("n_order_state", Types.INTEGER, order.getOrderState());
			sp.addParameter("n_init_score", Types.FLOAT, order.getInitScore());
			sp.addParameter("n_game_state", Types.INTEGER, order.getGameState());
			sp.addParameter("d_stat_time", Types.VARCHAR, order.getStatTime());
			sp.addParameter("s_picture_url", Types.VARCHAR, order.getPictureUrl());
			sp.addParameter("s_inner_name", Types.VARCHAR, order.getInnerName());
			sp.addParameter("s_game_url", Types.VARCHAR, order.getGameUrl());

			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();

			status = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return status;
	}

	/**
	 * 删除游戏排行数据
	 */
	public Integer deleteGameOrder(Integer gameId) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.deleteGameTopList", false);
		int status = 1;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, gameId);
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();

			status = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return status;
	}

	/**
	 * 上升游戏排行
	 */
	public Integer upGameOrder(Integer gameId) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.upGameTopList", false);
		int status = 1;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, gameId);
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();

			status = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return status;
	}

	/**
	 * 下降游戏排行
	 */
	public Integer downGameOrder(Integer gameId) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.downGameTopList", false);
		int status = 1;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, gameId);
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();

			status = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return status;
	}

	/**
	 * 查找所有未被添加到游戏排行中的游戏名称
	 */
	@SuppressWarnings("unchecked")
	public void getAllGameNames(PageInfo<GameInfoPOJO> pageInfo) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
			"PKG_ADMIN_WEBSITE_APP.getAllGameNames");
		sp.addOutCursorParameter("cur_result", GameInfoPOJO.class);
		Map<String, Object> resMap = sp.execute();
		pageInfo.setInfoList((List<GameInfoPOJO>) resMap.get("cur_result"));
	}
}
