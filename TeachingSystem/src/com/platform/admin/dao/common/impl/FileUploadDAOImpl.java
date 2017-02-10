package com.platform.admin.dao.common.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.platform.admin.dao.common.BaseDAO;
import com.platform.admin.dao.common.FileUploadDAO;
import com.platform.admin.dao.common.StoredProcedureDAO;
import com.platform.admin.pojo.common.FileInfoPOJO;
import com.platform.admin.util.PageInfo;

@Repository("fileUploadDAO")
public class FileUploadDAOImpl extends BaseDAO implements FileUploadDAO  {

	public int saveFileInfo(FileInfoPOJO fileInfo)throws Exception {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_saveFileInfo", false);
		sp.addParameter("n_passportId", Types.BIGINT, fileInfo.getPassportId());
		sp.addParameter("s_file_url", Types.VARCHAR, fileInfo.getUrl());
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> map=sp.execute();
		Integer result=(Integer) map.get("n_ret_code");
		return result;
	}

	@SuppressWarnings("unchecked")
	public PageInfo<FileInfoPOJO> getAllFileInfo(String params,int pageSize,int pageIndex) throws Exception {
		List<FileInfoPOJO> resultList = new ArrayList<FileInfoPOJO>();
		StoredProcedureDAO sp= new StoredProcedureDAO(this.getDataSource_eadmin(),
						"sys_getAllFileInfo",false);
		if(!StringUtils.isBlank(params)){
			sp.addParameter("s_param", Types.VARCHAR, params.trim());	
		}else{
			sp.addParameter("s_param", Types.VARCHAR, null);
		}
		sp.addParameter("n_page_size", Types.INTEGER,pageSize);
		sp.addParameter("n_page_index", Types.INTEGER, pageIndex);
		sp.addOutParameter("n_total_size", Types.INTEGER);
		sp.setReturnParam("cur_result", FileInfoPOJO.class);
		//sp.addOutCursorParameter("cur_result", FileInfoPOJO.class);
		Map<String,Object> map=sp.execute();
		PageInfo<FileInfoPOJO> pageInfo= new PageInfo<FileInfoPOJO>();
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setTotalSize((Integer)map.get("n_total_size"));
		resultList=(List<FileInfoPOJO>) map.get("cur_result");
		pageInfo.setInfoList(resultList);
		return pageInfo;
	}
	
	
	
}
