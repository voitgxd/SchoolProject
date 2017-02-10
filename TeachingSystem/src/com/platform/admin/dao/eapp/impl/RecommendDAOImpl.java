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
import com.platform.admin.dao.eapp.BaseAppDAO;
import com.platform.admin.dao.eapp.RecommendDAO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.pojo.eapp.RecommendPOJO;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 * 
 *         2015-3-23
 */
@Repository("recommendDAO")
public class RecommendDAOImpl extends BaseAppDAO implements RecommendDAO {
	public static final Logger log = Logger.getLogger(RecommendDAOImpl.class);

	@SuppressWarnings("unchecked")
	public void getRecommendApps(int recommendType,
			PageInfo<RecommendPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getRecommendApps").declareParameters(
						new SqlParameter("n_recommend_type", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet(
						"result",
						new BeanPropertyRowMapper<RecommendPOJO>(
								RecommendPOJO.class)).declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_recommend_type", recommendType);
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
		pageInfo.setInfoList((List<RecommendPOJO>) result.get("result"));
	}

	@SuppressWarnings("unchecked")
	public int cancelRecommend(int appId, int recommendType) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_cancelRecommend").declareParameters(
						new SqlParameter("n_app_id", Types.INTEGER),
						new SqlParameter("n_recommend_type", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_app_id", appId);
		params.put("n_recommend_type", recommendType);
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
	public void toRecommendNeedApp(int recommendType,
			PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getRecommendNeedApp")
				.declareParameters(
						new SqlParameter("s_game_name", Types.VARCHAR),
						new SqlParameter("n_recommend_type", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("s_game_name", pageInfo.getQueryGameName());
		params.put("n_recommend_type", recommendType);
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
	public int addAppToRecommend(RecommendPOJO recommend, String appIds) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_addAppToRecommend").declareParameters(
						new SqlParameter("n_recommend_type", Types.INTEGER),
						new SqlParameter("s_app_ids", Types.VARCHAR),
						new SqlParameter("s_effect_time", Types.VARCHAR),
						new SqlParameter("s_expire_time", Types.VARCHAR),
						new SqlParameter("s_recommend_reason", Types.VARCHAR),
						new SqlParameter("n_passport_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_recommend_type", recommend.getRecommendType());
		params.put("s_app_ids", appIds);
		params.put("s_effect_time", recommend.getEffectTime());
		params.put("s_expire_time", recommend.getExpireTime());
		params.put("s_recommend_reason", recommend.getRecommendReason());
		params.put("n_passport_id", recommend.getPassportId());
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
	public int upOrDown(int flag, int recommendType, int appId) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_recommendUpOrDown").declareParameters(
						new SqlParameter("n_flag", Types.INTEGER),
						new SqlParameter("n_recommend_type", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_flag", flag);
		params.put("n_recommend_type", recommendType);
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
}
