package com.platform.admin.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.platform.admin.pojo.common.FileInfoPOJO;

public class ReportExcelFactory {
	@SuppressWarnings("unchecked")
	public static ModelAndView fileReport(FileInfoPOJO fileInfo,PageInfo pageInfo){
		Map model = new HashMap();
		model.put("excelName", "文件信息报表");
		model.put("pageInfo", pageInfo);
		StringBuffer sb= new StringBuffer();
		sb.append(FieldSet.PASSPORT_ID);
		sb.append(",").append(FieldSet.FILE_URL);
		String[] fieldSet=sb.toString().split(",");
		model.put("fieldSet", fieldSet);
		ExcelUtil excelUtil= new ExcelUtil();
		
		return new ModelAndView(excelUtil,model);
		
	}
}
