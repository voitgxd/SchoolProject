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
import com.platform.admin.dao.site.GameDownloadDAO;
import com.platform.admin.pojo.site.GameDownloadInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-2-9
 */
@Repository("gameDownloadDAO")
public class GameDownloadDAOImpl extends BaseDAO implements GameDownloadDAO  {
	public static final Logger log = Logger.getLogger(GameDownloadDAOImpl.class);
	/**
	 * 获取指定页的下载记录信息
	 */
	@SuppressWarnings("unchecked")
	public void getRecordsByPage(PageInfo<GameDownloadInfoPOJO> pageInfo) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getAllGameDownloads", false);
		try {
			sp.addParameter("n_page_index", Types.INTEGER, pageInfo.getPageIndex());
			sp.addParameter("n_page_size", Types.INTEGER, pageInfo.getPageSize());
			sp.addParameter("n_gameType", Types.INTEGER, pageInfo.getQueryGameType());
			sp.addOutParameter("n_total_size", Types.INTEGER);
			sp.addOutCursorParameter("cur_result", GameDownloadInfoPOJO.class);
			Map<String, Object> resMap = sp.execute();
			int totalSize = (Integer) resMap.get("n_total_size");
			pageInfo.setTotalSize(totalSize);
			pageInfo.setInfoList((List<GameDownloadInfoPOJO>) resMap.get("cur_result"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
	}
	/**
	 * 获取要修改的下载信息
	 */
	@SuppressWarnings("unchecked")
	public Integer getRecordById(GameDownloadInfoPOJO record) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getOneGameDownload", false);
		Integer status = null;
		try {
			sp.addParameter("n_log_id", Types.INTEGER, record.getLogId());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			sp.addOutCursorParameter("cur_result", GameDownloadInfoPOJO.class);
			Map<String, Object> resMap = sp.execute();
			status = (Integer) resMap.get("n_ret_code");
			List<GameDownloadInfoPOJO> records = (List<GameDownloadInfoPOJO>) resMap.get("cur_result");
			for(GameDownloadInfoPOJO gd : records){
				record.setLogId(gd.getLogId());
				record.setQueryType(gd.getQueryType());
				record.setGameType(gd.getGameType());
				record.setDownloadTime(gd.getDownloadTime());
				record.setDownloadUrl(gd.getDownloadUrl());
				record.setDownloadSource(gd.getDownloadSource());
				record.setDownloadIp(gd.getDownloadIp());
				record.setUserAgent(gd.getUserAgent());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e);
		}
		return status;
	}
	/**
	 * 删除下载信息
	 */
	public Integer deleteGameDownload(GameDownloadInfoPOJO record) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.deleteGameDownload", false);
		Integer status = null;
		try {
			sp.addParameter("n_log_id", Types.INTEGER, record.getLogId());
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
	 * 修改保存游戏下载信息
	 */
	public Integer saveGameDownload(GameDownloadInfoPOJO record) {
		System.out.println(record.toString());
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.updateGameDownload", false);
		Integer status = null;
		try {
			sp.addParameter("n_log_id", Types.INTEGER, record.getLogId());
			sp.addParameter("n_query_type", Types.INTEGER, record.getQueryType());
			sp.addParameter("n_game_type", Types.INTEGER, record.getGameType());
			sp.addParameter("d_download_time", Types.VARCHAR, record.getDownloadTime());
			sp.addParameter("s_download_url", Types.VARCHAR, record.getDownloadUrl());
			sp.addParameter("s_download_source", Types.VARCHAR, record.getDownloadSource());
			sp.addParameter("s_download_ip", Types.VARCHAR, record.getDownloadIp());
			sp.addParameter("s_user_agent", Types.VARCHAR, record.getUserAgent());
	
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
