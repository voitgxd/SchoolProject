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
import com.platform.admin.dao.eapp.TopicDAO;
import com.platform.admin.pojo.eapp.AppTopicPOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.util.Constant;
import com.platform.admin.util.DateTimeUtil;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 * 
 *         2015-3-11
 */
@Repository("topicDAO")
public class TopicDAOImpl extends BaseAppDAO implements TopicDAO {
	public static final Logger log = Logger.getLogger(TopicDAOImpl.class);

	@SuppressWarnings("unchecked")
	public void getTopics(PageInfo<AppTopicPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getAllTopics").declareParameters(
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet(
						"result",
						new BeanPropertyRowMapper<AppTopicPOJO>(
								AppTopicPOJO.class)).declareParameters(
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
		pageInfo.setInfoList((List<AppTopicPOJO>) result.get("result"));
	}

	@SuppressWarnings("unchecked")
	public void getTopic(int topicId, PageInfo<AppTopicPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getTopic").declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER))
				.returningResultSet(
						"result",
						new BeanPropertyRowMapper<AppTopicPOJO>(
								AppTopicPOJO.class));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topicId);
		Map<?, ?> result = null;
		try {
			result = call.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		pageInfo.setInfoList((List<AppTopicPOJO>) result.get("result"));
	}

	@SuppressWarnings("unchecked")
	public void getTopicApp(int topicId, PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getTopicApp").declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topicId);
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
	public int saveTopic(AppTopicPOJO topic) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_saveTopic")
				.declareParameters(
						new SqlParameter("s_topic_name", Types.VARCHAR),
						new SqlParameter("s_topic_pic", Types.VARCHAR),
						new SqlParameter("s_topic_desc", Types.VARCHAR),
						new SqlParameter("n_platform_type", Types.INTEGER),
						new SqlParameter("d_expire_time", Types.TIME),
						new SqlParameter("n_update_passpord_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("s_topic_name", topic.getTopicName());
		params.put("s_topic_pic", topic.getTopicPic());
		params.put("s_topic_desc", topic.getTopicDesc());
		params.put("n_platform_type", topic.getPlatformType());
		params.put("d_expire_time", null == topic.getExpireTime()
				|| "" == topic.getExpireTime() ? DateTimeUtil.getCurrentTime()
				: DateTimeUtil.parseTime(topic.getExpireTime()));
		params.put("n_update_passport_id", topic.getUpdatePassportId());
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
	public int updateTopic(AppTopicPOJO topic) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_updateTopic")
				.declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("s_topic_name", Types.VARCHAR),
						new SqlParameter("s_topic_desc", Types.VARCHAR),
						new SqlParameter("n_platform_type", Types.INTEGER),
						new SqlParameter("d_expire_time", Types.TIMESTAMP),
						new SqlParameter("n_update_passport_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topic.getTopicId());
		params.put("s_topic_name", topic.getTopicName());
		params.put("s_topic_desc", topic.getTopicDesc());
		params.put("n_platform_type", topic.getPlatformType());
		params.put("d_expire_time", null == topic.getExpireTime()
				|| "" == topic.getExpireTime() ? DateTimeUtil.getCurrentTime()
				: DateTimeUtil.parseTime(topic.getExpireTime()));
		params.put("n_update_passport_id", topic.getUpdatePassportId());
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
	public int deleteTopic(AppTopicPOJO topic) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_deleteTopic").declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topic.getTopicId());
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
	 * 2级页面
	 */
	@SuppressWarnings("unchecked")
	public int deleteAppFromTopic(int topicId, int appId) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_deleteAppFromTopic").declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topicId);
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
	public int setAppToTop(int topicId, int appId, String expireTime) {
		expireTime = expireTime + ":00";
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_setAppToTopicTop").declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER),
						new SqlParameter("d_expire_time", Types.TIMESTAMP))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topicId);
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
	public int upOrDown(int flag, int topicId, int appId) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_topicUpOrDown").declareParameters(
						new SqlParameter("n_flag", Types.INTEGER),
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_flag", flag);
		params.put("n_topic_id", topicId);
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
	public void getNeedApp(int topicId, PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getTopicNeedApp").declareParameters(
						new SqlParameter("s_game_name", Types.VARCHAR),
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("s_game_name", pageInfo.getQueryGameName());
		params.put("n_topic_id", topicId);
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
	 * 第三级页面
	 */
	@SuppressWarnings("unchecked")
	public int addAppToTopic(int topicId, String appIds) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_addAppToTopic").declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("s_app_ids", Types.VARCHAR))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topicId);
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

	@SuppressWarnings("unchecked")
	public int updateTopicPic(AppTopicPOJO topic) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_updateTopicPic").declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("s_topic_pic", Types.VARCHAR))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topic.getTopicId());
		params.put("s_topic_pic", topic.getTopicPic());
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
	public int cancelTop(int topicId, int appId) {
		int status = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_cancelTopicTop").declareParameters(
						new SqlParameter("n_topic_id", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map params = new HashMap<String, Object>();
		params.put("n_topic_id", topicId);
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
