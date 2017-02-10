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
import com.platform.admin.dao.eapp.UserLoginDAO;
import com.platform.admin.pojo.eapp.UserLoginPOJO;
import com.platform.admin.util.DateTimeUtil;
import com.platform.admin.util.PageInfo;

/**
 * 玩家活跃度
 * 
 * @author gxd
 * 
 */
@Repository("userLoginDao")
public class UserLoginDAOImpl extends BaseAppDAO implements UserLoginDAO {
	private static final Logger log = Logger.getLogger(UserLoginDAOImpl.class);

	@SuppressWarnings( { "unchecked" })
	public void queryUserLogin(PageInfo<UserLoginPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_queryUserLogin").declareParameters(
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER),
						new SqlParameter("n_begin_time", Types.DATE),
						new SqlParameter("n_end_tiame", Types.DATE))
				.returningResultSet(
						"result",
						new BeanPropertyRowMapper<UserLoginPOJO>(
								UserLoginPOJO.class)).declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_page_index", pageInfo.getPageIndex());
		params.put("n_page_size", pageInfo.getPageSize());
		params.put("n_begin_time", null == pageInfo.getBeginTime()
				|| "" == pageInfo.getBeginTime() ? DateTimeUtil
				.getBeforeDate(7) : DateTimeUtil.parseDate(pageInfo
				.getBeginTime()));
		params.put("n_end_time", null == pageInfo.getEndTime()
				|| "" == pageInfo.getEndTime() ? DateTimeUtil.getCurrentTime()
				: DateTimeUtil.parseDate(pageInfo.getEndTime()));
		try {
			Map<?, ?> result = call.execute(params);
			pageInfo.setTotalSize((Integer) result.get("n_total_size"));
			pageInfo.setInfoList((List<UserLoginPOJO>) result.get("result"));
		} catch (Exception e) {
			log.error(e);
		}

	}

	public int queryRegistNumber(PageInfo<UserLoginPOJO> pageInfo) {
		int registNumber = 0;
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_queryRegistNumber").declareParameters(
						new SqlParameter("s_begin_time", Types.VARCHAR),
						new SqlParameter("s_end_time", Types.VARCHAR))
				.declareParameters(
						new SqlOutParameter("n_regist_number", Types.INTEGER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("s_begin_time",pageInfo.getBeginTime());
		params.put("s_end_time", pageInfo.getEndTime());
		try {
			Map<?, ?> result = call.execute(params);
			registNumber =  (Integer)result.get("n_regist_number");
		} catch (Exception e) {
			log.error(e);
		}
		return registNumber;
	}

}
