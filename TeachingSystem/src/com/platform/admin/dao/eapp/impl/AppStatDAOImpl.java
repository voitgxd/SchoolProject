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

import com.platform.admin.dao.eapp.AppStatDAO;
import com.platform.admin.dao.eapp.BaseAppDAO;
import com.platform.admin.pojo.eapp.AppStatPOJO;
import com.platform.admin.util.PageInfo;

@Repository("appStatDao")
/**
 * 游戏行为持久层实现类
 */
public class AppStatDAOImpl extends BaseAppDAO implements AppStatDAO {
	private static final Logger log = Logger.getLogger(AppStatDAOImpl.class);

	@SuppressWarnings("unchecked")
	public void queryAppStat(PageInfo<AppStatPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_queryAppStat").declareParameters(
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet(
						"result",
						new BeanPropertyRowMapper<AppStatPOJO>(
								AppStatPOJO.class)).declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("n_page_index", pageInfo.getPageIndex());
			params.put("n_page_size", pageInfo.getPageSize());
			Map<?, ?> result = call.execute(params);
			pageInfo.setInfoList((List<AppStatPOJO>) result.get("result"));
			pageInfo.setTotalSize((Integer) result.get("n_total_size"));
		} catch (Exception e) {
			log.error(e);
		}

	}

}
