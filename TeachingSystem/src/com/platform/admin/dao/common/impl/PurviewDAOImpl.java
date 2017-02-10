package com.platform.admin.dao.common.impl;

import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.platform.admin.dao.common.BaseDAO;
import com.platform.admin.dao.common.PurviewDAO;
import com.platform.admin.dao.common.StoredProcedureDAO;
import com.platform.admin.pojo.common.ProductPOJO;
import com.platform.admin.pojo.common.RolePOJO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.ResourcePOJO;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.HttpRequestUtil;
import com.platform.admin.util.JSONUtil;
import com.platform.admin.util.PageInfo;
import com.platform.admin.util.SecurityUtil;

@Repository("purviewDao")
public class PurviewDAOImpl extends BaseDAO implements PurviewDAO{
	public static final Logger log = Logger.getLogger(PurviewDAOImpl.class);
	
	/*pkg_8864admin_purview.*/
	/**
	 * 查询所有资源
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ResourcePOJO> getResources(long roleId,int sysFlag)throws Exception{
		List<ResourcePOJO> resultList = new ArrayList<ResourcePOJO>();
		StoredProcedureDAO sp= new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_getResources",false);	
		sp.addParameter("n_role_id", Types.INTEGER, roleId);
		sp.addParameter("n_sysFlag", Types.INTEGER, sysFlag);
		//sp.addOutCursorParameter("cur_result", ResourcePOJO.class);
		sp.setReturnParam("cur_result", ResourcePOJO.class); 
		Map<String,Object> map=sp.execute();
		resultList=(List<ResourcePOJO>) map.get("cur_result");
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductPOJO> getAllProducts()throws Exception{
		List<ProductPOJO> resultList = new ArrayList<ProductPOJO>();
		StoredProcedureDAO sp= new StoredProcedureDAO(this.getDataSource_eadmin(),
						"sys_getAllProducts",false);		 
		//sp.addOutCursorParameter("cur_result", ProductPOJO.class);
		sp.setReturnParam("cur_result", ProductPOJO.class);
		Map<String,Object> map=sp.execute();
		resultList=(List<ProductPOJO>) map.get("cur_result");
		return resultList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public PageInfo<UserPOJO> getAllUsers(String params,int pageSize,int pageIndex)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_getAllUsers", false);
		if(!StringUtils.isBlank(params)){
			sp.addParameter("s_param", Types.VARCHAR, params.trim());	
		}else{
			sp.addParameter("s_param", Types.VARCHAR, null);
		}
		sp.addParameter("n_page_size", Types.INTEGER, pageSize);
		sp.addParameter("n_page_index", Types.INTEGER, pageIndex);
		sp.addOutParameter("n_total_size", Types.INTEGER);
		sp.setReturnParam("cur_result", UserPOJO.class); 
		//sp.addOutCursorParameter("cur_result", UserPOJO.class);
		Map<String,Object> Map  = sp.execute();
		PageInfo<UserPOJO> pageInfo = new PageInfo<UserPOJO>();
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setTotalSize((Integer)Map.get("n_total_size"));
		pageInfo.setInfoList((List<UserPOJO>)Map.get("cur_result"));
		return pageInfo;
	}
	
	@SuppressWarnings("unchecked")
	public PageInfo<UserPOJO> getUsersByCondition(UserPOJO userPojo,int pageSize,int pageIndex)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_getConditionUsers", false);
		sp.addParameter("n_page_size", Types.INTEGER, pageSize);
		sp.addParameter("n_page_index", Types.INTEGER, pageIndex);
		sp.addParameter("s_user_name", Types.VARCHAR,userPojo.getUserName() );
		sp.addParameter("s_real_name", Types.VARCHAR,userPojo.getRealName());
		sp.addOutParameter("n_total_size", Types.INTEGER);
		//sp.addOutCursorParameter("cur_result", UserPOJO.class);
		sp.setReturnParam("cur_result", UserPOJO.class); 
		Map<String,Object> Map  = sp.execute();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setTotalSize((Integer)Map.get("n_total_size"));
		pageInfo.setInfoList((ArrayList<UserPOJO>)Map.get("cur_result"));
		return pageInfo;
	}
	
	@SuppressWarnings("unchecked")
	public UserPOJO getUserById(UserPOJO userPojo){
		List<UserPOJO> userList = new ArrayList<UserPOJO>();
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_getUserById", false);
		sp.addParameter("n_user_id", Types.BIGINT, userPojo.getUserId());
		sp.setReturnParam("cur_result", UserPOJO.class); 
		Map<String,Object> Map=sp.execute();
		userList=(ArrayList<UserPOJO>)Map.get("cur_result");
		if(null==userList||userList.size()==0){
			return null;
		}else{
			return (UserPOJO)userList.get(0);
		}
	}
	
	public int updateUserLogin(UserPOJO userPojo)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_updateUserLogin", false);
		sp.addParameter("n_user_id", Types.BIGINT, userPojo.getUserId());
		sp.addParameter("n_login_count", Types.INTEGER, userPojo.getLoginCount());
		sp.addParameter("s_last_login_ip", Types.VARCHAR, userPojo.getLastLoginIp());
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap = sp.execute();
		return (Integer)resMap.get("n_ret_code");
		
	}
	
	
	
	public int addUser(UserPOJO userPojo)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_addUser", false);
		//sp.addParameter("n_user_id", Types.BIGINT, userPojo.getUserId());
		sp.addParameter("s_user_name", Types.VARCHAR, userPojo.getUserName());
		sp.addParameter("s_real_name", Types.VARCHAR, userPojo.getRealName());
		sp.addParameter("n_user_type", Types.INTEGER, userPojo.getUserType());
		sp.addParameter("n_role_id", Types.INTEGER, userPojo.getRoleId());
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		
		Map<String,Object> map=sp.execute();
		Integer result=(Integer) map.get("n_ret_code");
		return result;
	}
	
	public int updateUser(UserPOJO userPojo) throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_updateUser", false);
		sp.addParameter("n_user_id", Types.BIGINT, userPojo.getUserId());
		sp.addParameter("s_real_name", Types.VARCHAR, userPojo.getRealName());
		sp.addParameter("n_role_id", Types.INTEGER, userPojo.getRoleId());
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap = sp.execute();
		return (Integer)resMap.get("n_ret_code");
	}
	
	public int deleteUser (UserPOJO userPojo) throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_deleteUser", false);
		sp.addParameter("n_user_id", Types.BIGINT, userPojo.getUserId());
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap = sp.execute();
		return (Integer)resMap.get("n_ret_code");
	}
	
	@SuppressWarnings("unchecked")
	public List<RolePOJO> getAllRoles()throws Exception{
		List<RolePOJO> resultList = new ArrayList<RolePOJO>();
		StoredProcedureDAO sp= new StoredProcedureDAO(this.getDataSource_eadmin(),
						"sys_getAllRoles",false);
		//sp.addOutCursorParameter("cur_result", RolePOJO.class);
		sp.setReturnParam("cur_result", RolePOJO.class); 
		Map<String,Object> map=sp.execute();
		resultList=(List<RolePOJO>) map.get("cur_result");
		return resultList;
	}
	
	
	@SuppressWarnings("unchecked")
	public PageInfo<RolePOJO> getAllRolesByPage(String params,int pageSize,int pageIndex)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_getAllRolesByPage", false);
		
		if(!StringUtils.isBlank(params)){
			sp.addParameter("s_param", Types.VARCHAR, params.trim());	
		}else{
			sp.addParameter("s_param", Types.VARCHAR, null);
		}
		
		sp.addParameter("n_page_size", Types.INTEGER, pageSize);
		sp.addParameter("n_page_index", Types.INTEGER, pageIndex);
		sp.addOutParameter("n_total_size", Types.INTEGER);
		//sp.addOutCursorParameter("cur_result", RolePOJO.class);
		sp.setReturnParam("cur_result", RolePOJO.class); 
		Map<String,Object> Map  = sp.execute();
		PageInfo<RolePOJO> pageInfo = new PageInfo<RolePOJO>();
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setTotalSize((Integer)Map.get("n_total_size"));
		pageInfo.setInfoList((List<RolePOJO>)Map.get("cur_result"));
		return pageInfo;
	}
	
	@SuppressWarnings("unchecked")
	public PageInfo<RolePOJO> getRolesByCondition(RolePOJO rolePojo,int pageSize,int pageIndex)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_getConditionRoles", false);
		sp.addParameter("s_role_name", Types.VARCHAR,rolePojo.getRoleName().trim());
		sp.addParameter("n_page_size", Types.INTEGER, pageSize);
		sp.addParameter("n_page_index", Types.INTEGER, pageIndex);
		sp.addOutParameter("n_total_size", Types.INTEGER);
		//sp.addOutCursorParameter("cur_result", RolePOJO.class);
		sp.setReturnParam("cur_result", RolePOJO.class); 
		Map<String,Object> Map  = sp.execute();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setTotalSize((Integer)Map.get("n_total_size"));
		pageInfo.setInfoList((ArrayList<RolePOJO>)Map.get("cur_result"));
		return pageInfo;
	}
	
	
	@SuppressWarnings("unchecked")
	public RolePOJO getRoleById(long roleId) throws Exception {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_getRoleById", false);
		sp.addParameter("n_role_id", Types.BIGINT, roleId);
		//sp.addOutCursorParameter("cur_result", RolePOJO.class);
		sp.setReturnParam("cur_result", RolePOJO.class); 
		Map<String,Object> Map=sp.execute();
		List<RolePOJO> roleList=(ArrayList<RolePOJO>)Map.get("cur_result");
		return (RolePOJO)roleList.get(0);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ResourcePOJO> getRoleResources(long roleId,int resType)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_getRoleResources", false);
		sp.addParameter("n_role_id", Types.BIGINT, roleId);
		sp.addParameter("n_res_type", Types.BIGINT, resType);
		//sp.addOutCursorParameter("cur_result", ResourcePOJO.class);
		sp.setReturnParam("cur_result", ResourcePOJO.class); 
		Map<String,Object> Map  = sp.execute();

		return (ArrayList<ResourcePOJO>)Map.get("cur_result");
	}
	
	public long addRole(String roleName)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_addRole", false);
		sp.addParameter("s_role_name", Types.VARCHAR, roleName);
		sp.addOutParameter("n_ret_code", Types.BIGINT);
		Map<String,Object> resMap = sp.execute();
		return (Long)resMap.get("n_ret_code");
	}
	
	public int addRoleResource(long roleId,int resId)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_addRoleResource", false);
		sp.addParameter("n_role_id", Types.BIGINT, roleId);
		sp.addParameter("n_res_id", Types.BIGINT, resId);
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		 
		Map<String,Object> resMap = sp.execute();
		return (Integer)resMap.get("n_ret_code");
	}
	
	public Integer checkRoleName(String roleName)throws Exception{
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_checkRoleName", false);
		sp.addParameter("s_role_name", Types.VARCHAR, roleName);
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap =sp.execute();
		return (Integer) resMap.get("n_ret_code");
	}

	public int deleteRoleResources(long roleId,long resId) throws Exception {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_deleteRoleResources", false);
		sp.addParameter("n_role_id", Types.BIGINT, roleId);
		sp.addParameter("n_res_id", Types.BIGINT, resId);
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap =sp.execute();
		return (Integer) resMap.get("n_ret_code");
		
	}

	public int updateRole(RolePOJO rolePojo) throws Exception {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_updateRole", false);
		sp.addParameter("n_role_id", Types.BIGINT, rolePojo.getRoleId());
		sp.addParameter("s_role_name", Types.VARCHAR, rolePojo.getRoleName());
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap =sp.execute();
		return (Integer) resMap.get("n_ret_code");
	}
	
	public int deleteRole(RolePOJO rolePojo) throws Exception {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_deleteRole", false);
		sp.addParameter("n_role_id", Types.BIGINT, rolePojo.getRoleId());
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap =sp.execute();
		return (Integer) resMap.get("n_ret_code");
	} 

	
	public int validateResource(long roleId, int sysFlag,List<ResourcePOJO> list)throws Exception {
		int resultCode = Constant.SYSTEM_ERROR;
		String validateUrl = Common.getCallbackUrl();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("p.roleID",roleId);
		params.put("p.sysFlag",sysFlag);
		params.put("p."+Constant.SIGN_KEY, SecurityUtil.getSign(SecurityUtil.getSignSalt(), roleId));
		try {
			String response = HttpRequestUtil.sendPostRequest(validateUrl, params);
			log.info("mthod:validateResource, params:[roleId:" + roleId + ",sysFlag:" + sysFlag + ",response:" + response + "]");
			JSONUtil json = JSONUtil.fromObject(response);
			if (null != json) {
				resultCode = json.getInt(Constant.RESULT_CODE_KEY);
				String resultInfo=json.getString(Constant.RESULT_INFO_KEY);
				if (resultCode == Constant.RET_OK) {
					@SuppressWarnings("unused")
					JSONUtil info = JSONUtil.fromObject(resultInfo);
					//userPOJO.setUserId(info.getLong("passportId"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return resultCode;
    }

	public int addResource(ResourcePOJO resourcePOJO) throws Exception {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_addResource", false);
		sp.addParameter("n_res_name", Types.VARCHAR, resourcePOJO.getResName());
		sp.addParameter("n_res_url", Types.VARCHAR, resourcePOJO.getResUrl());
		sp.addParameter("n_res_pid", Types.INTEGER, resourcePOJO.getResPid());
		sp.addParameter("n_res_type", Types.INTEGER, resourcePOJO.getResType());
		sp.addParameter("n_res_order", Types.INTEGER, resourcePOJO.getResOrder());
		sp.addParameter("n_res_icon", Types.VARCHAR, resourcePOJO.getResType());
		sp.addParameter("n_sys_flag", Types.INTEGER, resourcePOJO.getSysFlag());
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap =sp.execute();
		return (Integer) resMap.get("n_ret_code");
	}


	public int deleteResource(int resId) throws Exception {
		StoredProcedureDAO sp = new StoredProcedureDAO(this.getDataSource_eadmin(),
				"sys_deleteResource", false);
		sp.addParameter("n_res_id", Types.INTEGER, resId);
		sp.addOutParameter("n_ret_code", Types.INTEGER);
		Map<String,Object> resMap =sp.execute();
		return (Integer) resMap.get("n_ret_code");
	}

	
	
	
}
