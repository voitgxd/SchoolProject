package com.platform.admin.service.common.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.platform.admin.dao.common.ReportDAO;
import com.platform.admin.service.common.ReportService;
@Service("reportService")
public class ReportServiceImpl implements ReportService {
	@Resource
	private ReportDAO reportDao;
	public Map<String, Object> loginNumReport() throws Exception {
		return reportDao.loginNumReport();
	}

}
