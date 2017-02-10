package com.platform.admin.dao.site.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.platform.admin.dao.common.BaseDAO;
import com.platform.admin.dao.common.StoredProcedureDAO;
import com.platform.admin.dao.site.GiftPackageDao;
import com.platform.admin.pojo.site.GiftPackagePOJO;
import com.platform.admin.util.DateTimeUtil;
import com.platform.admin.util.PageInfo;

/**
 * 礼包信息持久层操作
 * 
 * @author chentao
 * 
 */
// 查询所有礼包信息
@Repository("giftPackageDao")
public class GiftPackageDaoImpl extends BaseDAO implements GiftPackageDao {
	public static final Logger log = Logger.getLogger(GiftPackageDaoImpl.class);

	// 所有礼包信息查询
	@SuppressWarnings("unchecked")
	public PageInfo<GiftPackagePOJO> queryGiftPackage() {
		PageInfo<GiftPackagePOJO> pageInfo = null;

		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getAllGiftPackages");
		try {
			sp.addOutCursorParameter("cur_result", GiftPackagePOJO.class);
			Map<String, Object> resMap = sp.execute();
			pageInfo = new PageInfo<GiftPackagePOJO>();
			pageInfo.setInfoList((List<GiftPackagePOJO>) resMap
					.get("cur_result"));
		} catch (Exception e) {
			log.error(e);
		}

		return pageInfo;
	}

	// 删除礼包信息
	public int deleteGiftPackage(GiftPackagePOJO giftPojo) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.deleteGiftPackage");
		Integer result = null;
		try {
			sp.addParameter("s_package_id", Types.VARCHAR, giftPojo
					.getPackageId());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();
			result = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

	// 添加礼包信息
	@SuppressWarnings("static-access")
	public int addGiftPackage(GiftPackagePOJO gift) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.addGiftPackages");
		Integer result = null;
		try {
			sp.addParameter("n_game_id", Types.INTEGER, gift.getGameId());
			sp.addParameter("n_package_type", Types.INTEGER, gift
					.getPackageType());
			sp.addParameter("s_package_title", Types.VARCHAR, gift
					.getPackageTitle());
			sp.addParameter("s_package_describe", Types.VARCHAR, gift
					.getPackageDescribe());
			DateTimeUtil dtu = new DateTimeUtil();
			Date d = dtu.parseTime(gift.getEndTime(), "yyyy-MM-dd HH:mm");
			sp.addParameter("d_end_time", Types.TIME, d);
			sp.addParameter("s_use_method", Types.VARCHAR, gift.getUseMethod());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();
			result = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

	// 查询要修改的礼包信息
	@SuppressWarnings("unchecked")
	public PageInfo<GiftPackagePOJO> queryUpdateGiftPackage(
			GiftPackagePOJO giftPojo) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.getOneGiftPackage");
		PageInfo<GiftPackagePOJO> pageInfo = null;
		try {
			sp.addParameter("s_package_id", Types.INTEGER, giftPojo
					.getPackageId());
			sp.addOutCursorParameter("cur_result", GiftPackagePOJO.class);
			Map<String, Object> resMap = sp.execute();
			pageInfo = new PageInfo<GiftPackagePOJO>();
			pageInfo.setInfoList((List<GiftPackagePOJO>) resMap
					.get("cur_result"));
		} catch (Exception e) {
			log.error(e);
		}

		return pageInfo;
	}

	// 修改礼包信息
	@SuppressWarnings("static-access")
	public int updateGiftPackage(GiftPackagePOJO gift) {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_ewebsite(),
				"PKG_ADMIN_WEBSITE_APP.updateGiftPackage");
		Integer result = null;
		try {
			sp.addParameter("n_package_id", Types.INTEGER, gift.getPackageId());
			sp.addParameter("n_game_id", Types.INTEGER, gift.getGameId());
			sp.addParameter("n_package_type", Types.INTEGER, gift
					.getPackageType());
			sp.addParameter("s_package_title", Types.VARCHAR, gift
					.getPackageTitle());
			sp.addParameter("s_package_describe", Types.VARCHAR, gift
					.getPackageDescribe());
			DateTimeUtil dtu = new DateTimeUtil();
			Date d = dtu.parseTime(gift.getEndTime(), "yyyy-MM-dd HH:mm");
			sp.addParameter("d_end_time", Types.TIME, d);
			sp.addParameter("s_use_method", Types.VARCHAR, gift.getUseMethod());
			sp.addOutParameter("n_ret_code", Types.INTEGER);
			Map<String, Object> resMap = sp.execute();
			result = (Integer) resMap.get("n_ret_code");
		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

}
