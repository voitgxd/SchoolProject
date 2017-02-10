package com.platform.admin.service.eapp;

import com.platform.admin.pojo.eapp.UserLoginPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 玩家登陆详情接口
 * @author gxd
 *
 */
public interface UserLoginService {
	public void queryUserLogin(PageInfo<UserLoginPOJO> pageInfo);
	public int queryRegistNumber(PageInfo<UserLoginPOJO> pageInfo);
}
