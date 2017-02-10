package com.platform.admin.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.admin.dao.common.PurviewDAO;
import com.platform.admin.pojo.common.ProductPOJO;
import com.platform.admin.pojo.common.RolePOJO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.ResourcePOJO;
import com.platform.admin.service.common.PurviewService;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

@Service("purviewService")
@Transactional
public class PurviewServiceImpl implements PurviewService{
	@SuppressWarnings("unused")
	private static Log logger=LogFactory.getLog(PurviewServiceImpl.class);
	@Resource
	private PurviewDAO purviewDao;
	
	public List<ResourcePOJO> getResources(long roleId,int sysFlag)throws Exception{
		return purviewDao.getResources(roleId,sysFlag);
		
	}
	
	public List<ProductPOJO> getAllProducts()throws Exception{
		return purviewDao.getAllProducts();
	}

	public PageInfo<UserPOJO> getAllUsers(String params,int pageSize, int pageIndex)
			throws Exception {
		return purviewDao.getAllUsers(params,pageSize, pageIndex);
	}

	public PageInfo<UserPOJO> getUsersByCondition(UserPOJO userPojo,
			int pageSize, int pageIndex) throws Exception {
		return purviewDao.getUsersByCondition(userPojo, pageSize, pageIndex);
	}
	
	
	public UserPOJO getUserById(UserPOJO userPojo)throws Exception{
		return purviewDao.getUserById(userPojo);
	}
	
	
	public int addUser(UserPOJO userPojo) throws Exception {
		return purviewDao.addUser(userPojo);
	}

	public int updateUserLogin(UserPOJO userPojo)throws Exception{
		return purviewDao.updateUserLogin(userPojo);
	}
	
	public int updateUser(UserPOJO userPojo) throws Exception {
		return purviewDao.updateUser(userPojo);
	}
	
	public int deleteUser(UserPOJO userPojo) throws Exception {
		return purviewDao.deleteUser(userPojo);
	}

	public List<RolePOJO> getAllRoles()throws Exception{
		return purviewDao.getAllRoles();
		
	}
	
	public PageInfo<RolePOJO> getAllRolesByPage(String params,int pageSize, int pageIndex)
			throws Exception {
		return purviewDao.getAllRolesByPage(params,pageSize, pageIndex);
	}

	
	public PageInfo<RolePOJO> getRolesByCondition(RolePOJO rolePojo,
			int pageSize, int pageIndex) throws Exception {
		return purviewDao.getRolesByCondition(rolePojo, pageSize, pageIndex);
	}

	public RolePOJO getRoleById(long roleId) throws Exception {
		return purviewDao.getRoleById(roleId);
	}

	public ArrayList<ResourcePOJO> getRoleResources(long roleId, int resType)throws Exception {
		return purviewDao.getRoleResources(roleId, resType);
	}
	
	public void addRole(String roleName,String[] resIds) throws Exception{
		long roleId = purviewDao.addRole(roleName);
		if (resIds!=null&&resIds.length>0) {
			for(int i=0;i<resIds.length;i++){
				purviewDao.addRoleResource(roleId, Integer.parseInt(resIds[i]));
			}
		}
	}

	public Integer checkRoleName(String roleName) throws Exception {
		return purviewDao.checkRoleName(roleName);
	}

	public void updateRole(RolePOJO rolePojo,String[] resIds) throws Exception {
		purviewDao.updateRole(rolePojo);
		purviewDao.deleteRoleResources(rolePojo.getRoleId(),0);
		if(resIds!=null&&resIds.length>0){
			for(int i=0;i<resIds.length;i++){
				purviewDao.addRoleResource(rolePojo.getRoleId(), Integer.parseInt(resIds[i]));
			}
		}
	}

    public int deleteRole(RolePOJO rolePojo) throws Exception {
    	int resultCode =  purviewDao.deleteRole(rolePojo);
    	int resultCode2 = purviewDao.deleteRoleResources(rolePojo.getRoleId(),0);
    	if(resultCode==1&&resultCode2==1){
    		return resultCode;
    	}
    	else{
    		return -500;
    	}
	}
	
	public List<ResourcePOJO> validateResource(long roleId, int sysFlag) throws Exception {
		List<ResourcePOJO> list = new ArrayList<ResourcePOJO>();
		int resultCode = purviewDao.validateResource(roleId,sysFlag,list);
		if (resultCode == Constant.RET_OK) {
			return list;
		} else {
			return null;
		}
	}

	public int addResource(ResourcePOJO resourcePOJO) throws Exception {
		int resultCode = purviewDao.addResource(resourcePOJO);
		if(resultCode==1){
    		return resultCode;
    	}
    	else{
    		return -500;
    	}
	}

	public int deleteResource(int resId) throws Exception {
		int resultCode = purviewDao.deleteResource(resId);
		int resultCode2 = purviewDao.deleteRoleResources(0, resId);
		if(resultCode==1&&resultCode2==1){
    		return resultCode;
    	}
    	else{
    		return -500;
    	}
	}

	public int deleteRoleResource(long roleId, long resId) throws Exception {
		int resultCode = purviewDao.deleteRoleResources(roleId, resId);
		if(resultCode==1){
    		return resultCode;
    	}
    	else{
    		return -500;
    	}
	}

	



}
