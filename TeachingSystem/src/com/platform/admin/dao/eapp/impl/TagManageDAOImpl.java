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
import com.platform.admin.dao.eapp.TagManageDAO;
import com.platform.admin.pojo.eapp.AppTagePOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.pojo.eapp.TagPOJO;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * 标签管理持久层实现
 * 
 * @author gxd
 * 
 */
@Repository("tagManageDAO")
public class TagManageDAOImpl extends BaseAppDAO implements TagManageDAO {
	private static final Logger log = Logger.getLogger(TagManageDAOImpl.class);

	// 添加标签
	public int addTag(TagPOJO tag) {
		int resultCode = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_addTag").declareParameters(
						new SqlParameter("n_tag_name", Types.VARCHAR),
						new SqlParameter("n_platform_type", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_tag_name", tag.getTagName());
		params.put("n_platform_type", tag.getplatformType());
		try {
			Map<?, ?> result = call.execute(params);
			resultCode = (Integer) result.get("n_ret_code");

		} catch (Exception e) {
			log.error(e);
		}
		return resultCode;
	}

	// 删除标签
	public int deleteTag(TagPOJO tag) {
		int resultCode = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_deleteTag").declareParameters(
						new SqlParameter("n_tag_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_tag_id", tag.getTagId());
		try {
			Map<?, ?> result = call.execute(params);
			resultCode = (Integer) result.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return resultCode;
	}

	// 查询所有标签信息
	@SuppressWarnings("unchecked")
	public void getAllTagInfo(PageInfo<TagPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getAllTagInfo").declareParameters(
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<TagPOJO>(TagPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_page_index", pageInfo.getPageIndex());
		params.put("n_page_size", pageInfo.getPageSize());
		try {
			Map<?, ?> result = call.execute(params);
			pageInfo.setTotalSize((Integer) result.get("n_total_size"));
			pageInfo.setInfoList((List<TagPOJO>) result.get("result"));
		} catch (Exception e) {
			log.error(e);
		}

	}

	// 查询要修改的标签信息
	@SuppressWarnings("unchecked")
	public PageInfo<TagPOJO> getTagInfo(TagPOJO tag) {
		PageInfo<TagPOJO> pageInfo = new PageInfo<TagPOJO>();
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getTagInfo").declareParameters(
						new SqlParameter("n_tag_id", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<TagPOJO>(TagPOJO.class));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_tag_id", tag.getTagId());
		try {
			Map<?, ?> result = call.execute(params);

			pageInfo.setInfoList((List<TagPOJO>) result.get("result"));
		} catch (Exception e) {
			log.error(e);
		}

		return pageInfo;
	}

	// 修改标签信息
	public int updateTagInfo(TagPOJO tag) {
		int resultCode = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_updateTagInfo").declareParameters(
						new SqlParameter("n_tag_id", Types.INTEGER),
						new SqlParameter("n_tag_name", Types.VARCHAR),
						new SqlParameter("n_platform_type", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_tag_id", tag.getTagId());
		params.put("n_tag_name", tag.getTagName());
		params.put("n_platform_type", tag.getplatformType());
		try {
			Map<?, ?> result = call.execute(params);
			resultCode = (Integer) result.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return resultCode;
	}

	// 根据标签获取游戏信息
	@SuppressWarnings("unchecked")
	public void getAppsByTag(Integer tagId, PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getAppsByTag").declareParameters(
						new SqlParameter("n_tag_id", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_tag_name", Types.VARCHAR),
						new SqlOutParameter("n_total_size", Types.INTEGER));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_tag_id", tagId);
		params.put("n_page_index", pageInfo.getPageIndex());
		params.put("n_page_size", pageInfo.getPageSize());
		try {
			Map<?, ?> result = call.execute(params);
			pageInfo.setTotalSize((Integer) result.get("n_total_size"));
			pageInfo.setInfoList((List<AppsPOJO>) result.get("result"));
			pageInfo.setTagName((String) result.get("n_tag_name"));
		} catch (Exception e) {
			log.error(e);
		}

	}

	// 取消游戏标签
	public int deleteAppTag(AppTagePOJO appTag) {
		int resultCode = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_deleteAppTag").declareParameters(
						new SqlParameter("n_tag_id", Types.INTEGER),
						new SqlParameter("n_app_id", Types.INTEGER))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_tag_id", appTag.getTagId());
		params.put("n_app_id", appTag.getAppId());
		try {
			Map<?, ?> result = call.execute(params);
			resultCode = (Integer) result.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return resultCode;

	}

	/**
	 * 获取没有打某标签的游戏信息
	 */
	@SuppressWarnings("unchecked")
	public void getAddApps(Integer tagId, PageInfo<AppsPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_getAddApps").declareParameters(
						new SqlParameter("n_tag_id", Types.INTEGER),
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet("result",
						new BeanPropertyRowMapper<AppsPOJO>(AppsPOJO.class))
				.declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER),
						new SqlOutParameter("n_tag_name", Types.VARCHAR));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_tag_id", tagId);
		params.put("n_page_index", pageInfo.getPageIndex());
		params.put("n_page_size", pageInfo.getPageSize());
		try {
			Map<?, ?> result = call.execute(params);
			pageInfo.setTagName((String) result.get("n_tag_name"));
			pageInfo.setTotalSize((Integer) result.get("n_total_size"));
			pageInfo.setInfoList((List<AppsPOJO>) result.get("result"));
		} catch (Exception e) {
			log.error(e);
		}

	}

	/**
	 * 为游戏添加标签
	 */
	public int addAppToTag(Integer tagId, String appIds) {
		int resultCode = Constant.SYSTEM_ERROR;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_addAppToTag").declareParameters(
						new SqlParameter("n_tag_id", Types.INTEGER),
						new SqlParameter("n_app_ids", Types.VARCHAR))
				.declareParameters(
						new SqlOutParameter("n_ret_code", Types.INTEGER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_tag_id", tagId);
		params.put("n_app_ids", appIds);
		try {
			Map<?, ?> result = call.execute(params);
			resultCode = (Integer) result.get("n_ret_code");
		} catch (Exception e) {

			log.error(e);
		}

		return resultCode;

	}

}
