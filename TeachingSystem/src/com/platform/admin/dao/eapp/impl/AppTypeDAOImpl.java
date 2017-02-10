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

import com.platform.admin.dao.eapp.AppTypeDAO;
import com.platform.admin.dao.eapp.BaseAppDAO;
import com.platform.admin.pojo.eapp.AppTypePOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.util.Constant;
import com.platform.admin.util.DateTimeUtil;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 * 
 *         2015-3-3
 */
@Repository("appTypeDAO")
public class AppTypeDAOImpl extends BaseAppDAO implements AppTypeDAO {
	public static final Logger log = Logger.getLogger(AppTypeDAOImpl.class);

	@SuppressWarnings("unchecked")
	public Integer deleteAppType(AppTypePOJO appType) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_deleteAppType").declareParameters(
						new SqlParameter("n_type_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_type_id", appType.getTypeId());
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
	public Integer getAppType(Integer typeId, PageInfo<AppTypePOJO> pageInfo) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getOneAppType").declareParameters(
						new SqlParameter("n_type_id", Types.INTEGER))
				.returningResultSet(
						"result",
						new BeanPropertyRowMapper<AppTypePOJO>(
								AppTypePOJO.class)).declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_type_id", typeId);
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		pageInfo.setInfoList((List<AppTypePOJO>) result.get("result"));
		status = (Integer) result.get("n_ret_code");
		return status;
	}

	@SuppressWarnings("unchecked")
	public void getAppTypesByPage(PageInfo<AppTypePOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getAllAppTypes").declareParameters(
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet(
						"result",
						new BeanPropertyRowMapper<AppTypePOJO>(
								AppTypePOJO.class)).declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
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
		pageInfo.setInfoList((List<AppTypePOJO>) result.get("result"));
	}

	@SuppressWarnings("unchecked")
	public Integer saveAppType(AppTypePOJO appType) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_saveAppType").declareParameters(
						new SqlParameter("s_type_name", Types.VARCHAR),
						new SqlParameter("s_type_pic", Types.VARCHAR),
						new SqlParameter("n_platform_type", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("s_type_name", appType.getTypeName());
		params.put("s_type_pic", appType.getTypePic());
		params.put("n_platform_type", appType.getPlatformType());
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
	public Integer updateAppType(AppTypePOJO appType) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_updateAppType").declareParameters(
						new SqlParameter("n_type_id", Types.INTEGER),
						new SqlParameter("s_type_name", Types.VARCHAR),
						new SqlParameter("s_type_pic", Types.VARCHAR),
						new SqlParameter("n_platform_type", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_type_id", appType.getTypeId());
		params.put("s_type_name", appType.getTypeName());
		params.put("s_type_pic", appType.getTypePic());
		params.put("n_platform_type", appType.getPlatformType());
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
	 * 获取所属类型的应用
	 */
	@SuppressWarnings("unchecked")
	public void getTypeApp(Integer typeId, PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getTypeApp").declareParameters(
						new SqlParameter("n_type_id", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_type_id", typeId);
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
	public void getNeedApp(Integer typeId, PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getNeedApp").declareParameters(
						new SqlParameter("s_game_name", Types.VARCHAR),
						new SqlParameter("n_type_id", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("s_game_name", pageInfo.getQueryGameName());
		params.put("n_type_id", typeId);
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

	/**
	 * 添加游戏到游戏类型
	 */
	@SuppressWarnings("unchecked")
	public Integer addAppToType(Integer typeId, String appIds) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_addAppToType").declareParameters(
						new SqlParameter("n_type_id", Types.INTEGER),
						new SqlParameter("s_app_ids", Types.VARCHAR))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_type_id", typeId);
		params.put("s_app_ids", appIds);
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
	 * 删除分类中的选中游戏
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteAppFromType(int appId, int typeId) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_deleteAppFromType").declareParameters(
						new SqlParameter("n_app_id", Types.INTEGER),
						new SqlParameter("n_type_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_app_id", appId);
		params.put("n_type_id", typeId);
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
	 * 分类所属游戏排行上下移
	 */
	@SuppressWarnings("unchecked")
	public int upOrDown(int flag, int typeId, int appId) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_upOrDown").declareParameters(
						new SqlParameter("n_flag", Types.INTEGER),
						new SqlParameter("n_type_id", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_flag", flag);
		params.put("n_type_id", typeId);
		params.put("n_app_id", appId);
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
	public int updateTypePic(AppTypePOJO appType) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_updateTypePic").declareParameters(
						new SqlParameter("n_type_id", Types.INTEGER),
						new SqlParameter("s_type_pic", Types.VARCHAR))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_type_id", appType.getTypeId());
		params.put("s_type_pic", appType.getTypePic());
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
	public int setAppToTop(int typeId, int appId, String expireTime) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_setAppToTop").declareParameters(
						new SqlParameter("n_type_id", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER),
						new SqlParameter("d_expire_time", Types.TIMESTAMP))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_type_id", typeId);
		params.put("n_app_id", appId);
		params.put("d_expire_time",
				null == expireTime || "" == expireTime ? DateTimeUtil
						.getCurrentTime() : DateTimeUtil.parseTime(expireTime));
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
	public int cancelTop(int typeId, int appId) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_cancelTypeTop").declareParameters(
						new SqlParameter("n_type_id", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_type_id", typeId);
		params.put("n_app_id", appId);
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
	 * 类型排行上下移动
	 */
	@SuppressWarnings("unchecked")
	public int typeUpOrDown(int flag, int typeId) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_typeUpOrDown").declareParameters(
						new SqlParameter("n_flag", Types.INTEGER),
						new SqlParameter("n_type_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_flag", flag);
		params.put("n_type_id", typeId);
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
