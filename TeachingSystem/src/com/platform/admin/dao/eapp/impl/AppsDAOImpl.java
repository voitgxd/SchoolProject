/**
 * 
 */
package com.platform.admin.dao.eapp.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.platform.admin.dao.eapp.AppsDAO;
import com.platform.admin.dao.eapp.BaseAppDAO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 * 
 *         2015-3-3
 */
@Repository("appsDAO")
public class AppsDAOImpl extends BaseAppDAO implements AppsDAO {
	public static final Logger log = Logger.getLogger(AppsDAOImpl.class);

	@SuppressWarnings("unchecked")
	public void getAppsByPage(PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getAllApps").declareParameters(
						new SqlParameter("s_game_name", Types.VARCHAR),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("s_game_name", pageInfo.getQueryGameName());
		params.put("n_page_index", pageInfo.getPageIndex());
		params.put("n_page_size", pageInfo.getPageSize());
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		pageInfo.setTotalSize((Integer) result.get("n_total_size"));
		pageInfo.setInfoList((List<AppsPOJO>) result.get("result"));
	}

	@SuppressWarnings("unchecked")
	public void getUncheckedApps(int appStateFlag, PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getUncheckedApps").declareParameters(
						new SqlParameter("n_app_state_flag", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_app_state_flag", appStateFlag);
		params.put("n_page_index", pageInfo.getPageIndex());
		params.put("n_page_size", pageInfo.getPageSize());
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		pageInfo.setTotalSize((Integer) result.get("n_total_size"));
		pageInfo.setInfoList((List<AppsPOJO>) result.get("result"));
	}

	@SuppressWarnings("unchecked")
	public Integer deleteApps(AppsPOJO app) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_deleteApps").declareParameters(
						new SqlParameter("n_app_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_app_id", app.getAppId());
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		status = (Integer) result.get("n_ret_code");
		return status;
	}

	@SuppressWarnings("unchecked")
	public Integer getApp(Integer appId, PageInfo<AppsPOJO> pageInfo) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getOneApp").declareParameters(
						new SqlParameter("n_app_id", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_app_id", appId);
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		pageInfo.setInfoList((List<AppsPOJO>) result.get("result"));
		status = (Integer) result.get("n_ret_code");
		return status;
	}

	@SuppressWarnings("unchecked")
	public Integer saveApp(AppsPOJO app) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_saveApp").declareParameters(
						new SqlParameter("s_app_name", Types.VARCHAR),
						new SqlParameter("s_app_icon", Types.VARCHAR),
						new SqlParameter("s_app_url", Types.VARCHAR),
						new SqlParameter("n_platform_type", Types.INTEGER),
						new SqlParameter("n_app_type", Types.INTEGER),
						new SqlParameter("s_app_summary", Types.VARCHAR),
						new SqlParameter("s_app_desc", Types.VARCHAR),
						new SqlParameter("n_developer_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("s_app_name", app.getAppName());
		params.put("s_app_icon", app.getAppIcon());
		params.put("s_app_url", app.getAppUrl());
		params.put("n_platform_type", app.getPlatformType());
		params.put("n_app_type", app.getAppType());
		params.put("s_app_summary", app.getAppSummary());
		params.put("s_app_desc", app.getAppDesc());
		params.put("n_developer_id", app.getDeveloperId());
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		status = (Integer) result.get("n_ret_code");
		return status;
	}

	@SuppressWarnings("unchecked")
	public Integer updateApp(AppsPOJO app) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_updateApp").declareParameters(
						new SqlParameter("n_app_id", Types.INTEGER),
						new SqlParameter("s_app_name", Types.VARCHAR),
						new SqlParameter("s_app_url", Types.VARCHAR),
						new SqlParameter("n_platform_type", Types.INTEGER),
						new SqlParameter("n_app_type", Types.INTEGER),
						new SqlParameter("s_app_summary", Types.VARCHAR),
						new SqlParameter("s_app_desc", Types.VARCHAR),
						new SqlParameter("n_developer_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_app_id", app.getAppId());
		params.put("s_app_name", app.getAppName());
		params.put("s_app_url", app.getAppUrl());
		params.put("n_platform_type", app.getPlatformType());
		params.put("n_app_type", app.getAppType());
		params.put("s_app_summary", app.getAppSummary());
		params.put("s_app_desc", app.getAppDesc());
		params.put("n_developer_id", app.getDeveloperId());
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		status = (Integer) result.get("n_ret_code");
		return status;
	}

	@SuppressWarnings("unchecked")
	public Integer addAppIcon(AppsPOJO app) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_addAppIcon").declareParameters(
						new SqlParameter("n_app_id", Types.INTEGER),
						new SqlParameter("s_app_icon", Types.VARCHAR))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_app_id", app.getAppId());
		params.put("s_app_icon", app.getAppIcon());
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		status = (Integer) result.get("n_ret_code");
		return status;
	}

	/**
	 * 批量添加应用
	 */
	@SuppressWarnings("unchecked")
	public void insertApp(List<AppsPOJO> appList) {
		for (AppsPOJO app : appList) {
			SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
					.withProcedureName("app_saveApp").declareParameters(
							new SqlParameter("s_app_name", Types.VARCHAR),
							new SqlParameter("s_app_icon", Types.VARCHAR),
							new SqlParameter("s_app_url", Types.VARCHAR),
							new SqlParameter("n_platform_type", Types.INTEGER),
							new SqlParameter("n_app_type", Types.INTEGER),
							new SqlParameter("s_app_desc", Types.VARCHAR),
							new SqlParameter("n_developer_id", Types.INTEGER))
					.declareParameters(
							new SqlOutParameter("n_ret_code", Types.INTEGER));
			Map params = new HashMap<String, Object>();
			params.put("s_app_name", app.getAppName());
			params.put("s_app_icon", app.getAppIcon());
			params.put("s_app_url", app.getAppUrl());
			params.put("n_platform_type", app.getPlatformType());
			params.put("n_app_type", app.getAppType());
			params.put("s_app_summary", app.getAppSummary());
			params.put("s_app_desc", app.getAppDesc());
			params.put("n_developer_id", app.getDeveloperId());
			try {
				call.execute(params);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
		}
	}

	/**
	 * 审核游戏
	 */
	@SuppressWarnings("unchecked")
	public Integer checkApp(String appIds, Integer statusCode) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_checkApps").declareParameters(
						new SqlParameter("s_app_ids", Types.VARCHAR),
						new SqlParameter("n_state_code", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("s_app_ids", appIds);
		params.put("n_state_code", statusCode);
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		status = (Integer) result.get("n_ret_code");
		return status;
	}

	/**
	 * 修改应用地址，用于在上传游戏的时候使用
	 */
	@SuppressWarnings("unchecked")
	public int updateAppUrl(AppsPOJO app) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_updateAppUrl").declareParameters(
						new SqlParameter("n_app_id", Types.INTEGER),
						new SqlParameter("s_app_url", Types.VARCHAR))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_app_id", app.getAppId());
		params.put("s_app_url", app.getAppUrl());
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		status = (Integer) result.get("n_ret_code");
		return status;
	}
}
