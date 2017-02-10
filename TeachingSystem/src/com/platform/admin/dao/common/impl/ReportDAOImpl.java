package com.platform.admin.dao.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.admin.dao.common.BaseDAO;
import com.platform.admin.dao.common.ReportDAO;
import com.platform.admin.dao.common.StoredProcedureDAO;
import com.platform.admin.pojo.common.UserPOJO;
@Repository("reportDao")
public class ReportDAOImpl extends BaseDAO implements ReportDAO {

	public Map<String, Object> loginNumReport() throws Exception {
		
		StoredProcedureDAO sp= new StoredProcedureDAO(this.getDataSource_eadmin(), 
							"sys_LoginNumberReport", false);
		//sp.addOutCursorParameter("cur_week_loginNum", UserPOJO.class);
		//sp.addOutCursorParameter("cur_month_loginNum", UserPOJO.class);
		sp.setReturnParam("cur_week_loginNum", UserPOJO.class);
		sp.setReturnParam("cur_month_loginNum", UserPOJO.class);
		Map<String, Object> resultMap=sp.execute();
		
		Map<String,Object> loginNumMap=new HashMap<String,Object>();
		loginNumMap.put("weekLoginNumList", resultMap.get("cur_week_loginNum"));
		loginNumMap.put("monthLoginNumList", resultMap.get("cur_month_loginNum"));
		return loginNumMap;
	}

}
