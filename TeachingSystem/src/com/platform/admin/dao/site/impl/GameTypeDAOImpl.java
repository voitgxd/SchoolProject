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
import com.platform.admin.dao.site.GameTypeDAO;
import com.platform.admin.pojo.site.GameTypeInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏排行模块
 * @author gxd
 *
 * 2015-1-30
 */
@Repository("gameTypeDAO")
public class GameTypeDAOImpl extends BaseDAO implements GameTypeDAO {
	public static final Logger log = Logger.getLogger(GameTypeDAOImpl.class);

	/**
	 *按照类型ID删除游戏类型
	 */
	public Integer deleteGameType(Integer gameTypeId) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.deleteGameType", false);
		Integer status = null;
		try {
			sp.addParameter("n_game_type_id", Types.INTEGER, gameTypeId);
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
	 * 获取全部的游戏类型
	 */
	@SuppressWarnings("unchecked")
	public void getAllTypes(PageInfo<GameTypeInfoPOJO> pageInfo) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getAllGameTypes", false);
		try {
			sp.addOutCursorParameter("cur_result", GameTypeInfoPOJO.class);
			Map<String, Object> resMap = sp.execute();
			pageInfo.setInfoList((List<GameTypeInfoPOJO>) resMap.get("cur_result"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
	}

	/**
	 * 根据类型ID获取游戏类型
	 */
	@SuppressWarnings("unchecked")
	public Integer getGameTypeById(PageInfo<GameTypeInfoPOJO> pageInfo, Integer gameTypeId) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getOneGameType", false);
		Integer status = null;
		try {
			sp.addParameter("n_game_type_id", Types.INTEGER, gameTypeId);
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			sp.addOutCursorParameter("cur_result", GameTypeInfoPOJO.class);
			Map<String, Object> resMap = sp.execute();

			status = (Integer) resMap.get("n_ret_code");
			pageInfo.setInfoList((List<GameTypeInfoPOJO>) resMap.get("cur_result"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return status;
	}

	/**
	 * 新增保存
	 */
	public Integer saveGameType(GameTypeInfoPOJO gameType) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.addGameType", false);
		Integer status = null;
		try {      
			sp.addParameter("s_game_type_name", Types.VARCHAR, gameType.getGameTypeName());
			sp.addParameter("s_type_describe", Types.VARCHAR, gameType.getTypeDescribe());
			sp.addParameter("n_game_numbers", Types.INTEGER, gameType.getGameNumbers());
			sp.addParameter("n_type_order", Types.INTEGER, gameType.getTypeOrder());
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
	 * 修改保存
	 */
	public Integer updateGameType(GameTypeInfoPOJO gameType) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.updateGameType", false);
		Integer status = null;
		try {      
			sp.addParameter("n_game_type_id", Types.INTEGER, gameType.getGameTypeId());
			sp.addParameter("s_game_type_name", Types.VARCHAR, gameType.getGameTypeName());
			sp.addParameter("s_type_describe", Types.VARCHAR, gameType.getTypeDescribe());
			sp.addParameter("n_game_numbers", Types.INTEGER, gameType.getGameNumbers());
			sp.addParameter("n_type_order", Types.INTEGER, gameType.getTypeOrder());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();

			status = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return status;
	}
	
}
