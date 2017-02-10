package com.platform.admin.service.eapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.eapp.AppStatDAO;
import com.platform.admin.pojo.eapp.AppStatPOJO;
import com.platform.admin.service.eapp.AppStatService;
import com.platform.admin.util.PageInfo;

@Service("appStatService")
public class AppStatServiceImpl implements AppStatService {
	@Autowired
	@Qualifier("appStatDao")
	private AppStatDAO appStatDao;

	public void queryAppStat(PageInfo<AppStatPOJO> pageInfo) {
		appStatDao.queryAppStat(pageInfo);

	}

	public AppStatDAO getAppStatDao() {
		return appStatDao;
	}

	public void setAppStatDao(AppStatDAO appStatDao) {
		this.appStatDao = appStatDao;
	}

}
