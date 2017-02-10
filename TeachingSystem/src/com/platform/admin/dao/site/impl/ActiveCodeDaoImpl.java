package com.platform.admin.dao.site.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.platform.admin.dao.common.BaseDAO;
import com.platform.admin.dao.common.StoredProcedureDAO;
import com.platform.admin.dao.site.ActiveCodeDao;
import com.platform.admin.pojo.site.ActiveCodePOJO;
import com.platform.admin.util.PageInfo;

/**
 * 激活码业务持久层
 * 
 * @author chentao
 * 
 */
// 查询激活码信息
@Repository("activeCodeDao")
public class ActiveCodeDaoImpl extends BaseDAO implements ActiveCodeDao {
	public static final Logger log = Logger.getLogger(ActiveCodeDaoImpl.class);

	@SuppressWarnings("unchecked")
	public void getAllActiveCode(PageInfo<ActiveCodePOJO> pageInfo) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getAllActivationCodes");
		try {
			sp.addParameter("n_page_index", Types.INTEGER, pageInfo
					.getPageIndex());
			sp.addParameter("n_page_size", Types.INTEGER, pageInfo
					.getPageSize());
			sp.addOutParameter("n_total_size", Types.INTEGER);
			sp.addOutCursorParameter("cur_result", ActiveCodePOJO.class);
			Map<String, Object> resMap = sp.execute();
			int totalSize = (Integer) resMap.get("n_total_size");
			pageInfo.setTotalSize(totalSize);
			pageInfo.setInfoList((List<ActiveCodePOJO>) resMap
					.get("cur_result"));
		} catch (Exception e) {
			log.error(e);
		}

	}

	// 删除激活码信息
	public int deleteActiveCode(ActiveCodePOJO activeCode) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.deleteActivationCode");
		Integer result = null;
		try {
			sp.addParameter("s_code_id", Types.VARCHAR, activeCode.getCodeId());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();
			result = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

	// 通过excel导入激活码信息
	public int insertActiveCode(ActiveCodePOJO activeCode) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.addActivationCode");
		Integer result = null;
		try {
			sp.addParameter("s_code_id", Types.VARCHAR, activeCode.getCodeId());
			sp.addParameter("s_package_id", Types.INTEGER, activeCode
					.getPackageId());
			sp.addParameter("s_code_stat", Types.INTEGER, activeCode
					.getCodeStat());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();
			result = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return result;

	}

}