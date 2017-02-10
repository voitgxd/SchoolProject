package com.platform.admin.dao.eapp;

import com.platform.admin.pojo.eapp.UserLoginPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 玩家登陆详情持久层
 * 
 * @author gxd
 * 
 */
public interface UserLoginDAO {
	public void queryUserLogin(PageInfo<UserLoginPOJO> pageInfo);
	public int queryRegistNumber(PageInfo<UserLoginPOJO> pageInfo);
}
