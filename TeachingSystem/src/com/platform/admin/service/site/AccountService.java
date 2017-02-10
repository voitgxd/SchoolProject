package com.platform.admin.service.site;

import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.AccountPOJO;

/**
 * <p>
 * 处理账号相关业务逻辑
 * </p>
 * 
 * @author gxd
 * 
 */
public interface AccountService {
	/** 登录 */
	public AccountPOJO login(String passportName, String password, String loginIp);

	/** 认证登录Token */
	public AccountPOJO validateToken(String passportId,String tokenId) throws Exception;
	/**
	 * 校验登录用户
	 * @param passportName
	 * @param loginIp
	 * @return
	 * @throws Exception
	 */
	public UserPOJO validateUser(String passportName,String loginIp) throws Exception;
}
