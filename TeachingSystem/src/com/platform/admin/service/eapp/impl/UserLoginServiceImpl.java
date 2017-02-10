package com.platform.admin.service.eapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.eapp.UserLoginDAO;
import com.platform.admin.pojo.eapp.UserLoginPOJO;
import com.platform.admin.service.eapp.UserLoginService;
import com.platform.admin.util.PageInfo;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {
	@Autowired
	@Qualifier("userLoginDao")
	private UserLoginDAO userLoginDao;

	public void queryUserLogin(PageInfo<UserLoginPOJO> pageInfo) {
		userLoginDao.queryUserLogin(pageInfo);
	}
	
	public int queryRegistNumber(PageInfo<UserLoginPOJO> pageInfo) {
		return userLoginDao.queryRegistNumber(pageInfo);
	}


	public UserLoginDAO getUserLoginDao() {
		return userLoginDao;
	}

	public void setUserLoginDao(UserLoginDAO userLoginDao) {
		this.userLoginDao = userLoginDao;
	}
}
