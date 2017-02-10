package com.platform.admin.dao.common;

import java.util.ArrayList;
import java.util.List;

import com.platform.admin.pojo.common.ProductPOJO;
import com.platform.admin.pojo.common.RolePOJO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.ResourcePOJO;
import com.platform.admin.util.PageInfo;

public interface PurviewDAO{
	/**
	 * 查询所有资源
	 * @return
	 * @throws Exception
	 */
	public List<ResourcePOJO> getResources(long roleId,int sysFlag)throws Exception;
	
	public List<ProductPOJO> getAllProducts()throws Exception;
	
	public PageInfo<UserPOJO> getAllUsers(String params,int pageSize,int pageIndex)throws Exception;
	
	public PageInfo<UserPOJO> getUsersByCondition(UserPOJO userPojo,int pageSize,int pageIndex)throws Exception;
	
	public UserPOJO getUserById(UserPOJO userPojo)throws Exception;
	
	public int addUser(UserPOJO userPojo)throws Exception;
	
	public int updateUserLogin(UserPOJO userPojo)throws Exception;
	
	public int updateUser(UserPOJO userPojo) throws Exception;
	
	public int deleteUser(UserPOJO userPojo) throws Exception;
	
	public PageInfo<RolePOJO> getAllRolesByPage(String params,int pageSize,int pageIndex)throws Exception;
	
	public PageInfo<RolePOJO> getRolesByCondition(RolePOJO rolePojo,int pageSize,int pageIndex)throws Exception;
	
	public List<RolePOJO> getAllRoles()throws Exception;
	
	public RolePOJO getRoleById(long roleId)throws Exception;
	
	public ArrayList<ResourcePOJO> getRoleResources(long roleId,int resType)throws Exception;
	
	public long addRole(String roleName)throws Exception;
	
	public int addRoleResource(long roleId,int resId)throws Exception;
	
	public Integer checkRoleName(String roleName)throws Exception;
	
	public int updateRole(RolePOJO rolePojo) throws Exception;
	
	public int deleteRole(RolePOJO rolePojo) throws Exception;
	
	public int deleteRoleResources(long roleId,long resId)throws Exception;
	
    public int addResource(ResourcePOJO resourcePOJO) throws Exception;
	
	public int deleteResource(int resId) throws Exception;

	public int validateResource(long roleId,int sysFlag,List<ResourcePOJO> list) throws Exception;
	
	
}
