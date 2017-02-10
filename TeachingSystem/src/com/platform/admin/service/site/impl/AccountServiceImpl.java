package com.platform.admin.service.site.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.site.AccountDAO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.AccountPOJO;
import com.platform.admin.service.site.AccountService;
import com.platform.admin.util.Constant;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	public static final Logger log = Logger.getLogger(AccountServiceImpl.class);

	@Autowired
	@Qualifier("accountDao")
	private AccountDAO accountDao;

	/**
	 * 登录
	 */
	public AccountPOJO login(String passportName, String password,
			String loginIp) {
		AccountPOJO account = new AccountPOJO();
		int resultCode = accountDao.login(passportName, password, loginIp,
				account);
		if (resultCode >= Constant.RET_OK) {
			// 登录成功，获取用户信息
			try {
				resultCode = accountDao.validateToken(String.valueOf(account
						.getAccountId()), account.getToken(), loginIp, account);
			} catch (Exception e) {
				e.printStackTrace();
				resultCode = Constant.SYSTEM_ERROR;
			}
			return resultCode >= Constant.RET_OK ? account : null;
		} else {
			return null;
		}
	}

	/**
	 * Token认证
	 * 
	 * @throws Exception
	 */
	public AccountPOJO validateToken(String passportId, String tokenId)
			throws Exception {
		AccountPOJO account = new AccountPOJO();
		int resultCode = accountDao.validateToken(passportId, tokenId, "", account);
		if (resultCode == Constant.RET_OK) {
			account.setAccountId(Long.parseLong(passportId));
			account.setToken(tokenId);
			return account;
		} else {
			return null;
		}
	}

	public UserPOJO validateUser(String passportName, String loginIp)
			throws Exception {
		UserPOJO userPOJO = new UserPOJO();
		int resultCode = accountDao.validateUser(passportName, loginIp,
				userPOJO);

		if (resultCode == Constant.RET_OK) {
			return userPOJO;
		} else {
			return null;
		}
	}

}