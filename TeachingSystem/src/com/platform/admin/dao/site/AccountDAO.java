package com.platform.admin.dao.site;

import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.AccountPOJO;

public interface AccountDAO {
	public int login(String passportName, String password, String loginIp, AccountPOJO account);

	public int validateToken(String passportId,String tokenId,String loginIp, AccountPOJO info) throws Exception;
	
	public int validateUser(String passportName,String userIp,UserPOJO userPOJO) throws Exception ;
}
