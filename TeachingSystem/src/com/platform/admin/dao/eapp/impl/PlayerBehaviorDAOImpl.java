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
import com.platform.admin.dao.eapp.PlayerBehaviorDAO;
import com.platform.admin.pojo.eapp.PlayerBehaviorPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 玩家游戏行为持久层实现
 * 
 * @author gxd
 * 
 */
@Repository("playerBehaviorDao")
public class PlayerBehaviorDAOImpl extends BaseAppDAO implements
		PlayerBehaviorDAO {
	private static final Logger log = Logger
			.getLogger(PlayerBehaviorDAOImpl.class);

	@SuppressWarnings("unchecked")
	public void queryPlayerBehavior(PageInfo<PlayerBehaviorPOJO> pageInfo) {
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
				.withProcedureName("app_queryPlayerBehavior")
				.declareParameters(
						new SqlParameter("n_page_index", Types.INTEGER),
						new SqlParameter("n_page_size", Types.INTEGER))
				.returningResultSet(
						"result",
						new BeanPropertyRowMapper<PlayerBehaviorPOJO>(
								PlayerBehaviorPOJO.class)).declareParameters(
						new SqlOutParameter("n_total_size", Types.INTEGER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n_page_index", pageInfo.getPageIndex());
		params.put("n_page_size", pageInfo.getPageSize());
		try {
			Map<?, ?> result = call.execute(params);
			pageInfo.setTotalSize((Integer) result.get("n_total_size"));
			pageInfo.setInfoList((List<PlayerBehaviorPOJO>) result
					.get("result"));
		} catch (Exception e) {
			log.error(e);
		}

	}
}
