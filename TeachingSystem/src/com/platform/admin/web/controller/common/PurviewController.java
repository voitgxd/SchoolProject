package com.platform.admin.web.controller.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.platform.admin.pojo.common.RolePOJO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.ResourcePOJO;
import com.platform.admin.service.common.PurviewService;
import com.platform.admin.service.site.AccountService;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;


@Controller
public class PurviewController {
	private static  Log logger=LogFactory.getLog(PurviewController.class);
	@Resource
	private PurviewService purviewService;
	
	@Resource
	private AccountService accountService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String showHome(Integer sysFlag,Model model,HttpSession session){
		logger.info("showHome...start begin");
		try {
			logger.info("sysFlag=="+session.getAttribute("sysFlag"));
			//获取用户拥有的所有权限
			List<ResourcePOJO>	reourceList = (List<ResourcePOJO>)session.getAttribute(Constant.SESSION_ROLE_RESOURCE_BEAN_LIST);
            //选择资源，存入session
			List<ResourcePOJO> resultList = new ArrayList<ResourcePOJO>();
			for(ResourcePOJO resources : reourceList ){
			    if(resources.getSysFlag()==(sysFlag==null?3:sysFlag)){
			    	if(resources.getResPid()!=0)
			    		resultList.add(resources);
			    	else{
			    		model.addAttribute("product",resources);
			    	}
			    }
			}
			session.setAttribute("role_resources", resultList);
			reourceList = (List<ResourcePOJO>)session.getAttribute(Constant.SESSION_ROLE_RESOURCE_BEAN_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/home/home";
	}
	
	/**
	 * 异步获取所有的产品
	 * @param model
	 * @param redirect
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/home/getProduct",method=RequestMethod.GET)
	@ResponseBody
	public List<ResourcePOJO> geAllProducts(Model model,RedirectAttributes redirect,HttpSession session){
		logger.info("geAllProducts...start begin");
		List<ResourcePOJO> resultList = new ArrayList<ResourcePOJO>();
		try {
			List<ResourcePOJO>	reourceList = (List<ResourcePOJO>) session.getAttribute(Constant.SESSION_ROLE_RESOURCE_BEAN_LIST);
			for(int i=0;i<reourceList.size();i++){
			    if(reourceList.get(i).getResPid()==0){
			    	resultList.add(reourceList.get(i));
			    }
			}
			//对resultList按照ID进行排序
			 Collections.sort(resultList,new Comparator<ResourcePOJO>(){
		            public int compare(ResourcePOJO arg0,ResourcePOJO arg1) {
		                return new Integer(arg0.getResId()).compareTo(new Integer(arg1.getResId()));
		            }
		        });
			 
		} catch (Exception e) {	
		}
		return resultList;
		
	}
	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String showUserInfo(Model model,HttpSession session){
		logger.info("showUserInfo...start begin");
		List<RolePOJO> roleList;
		try {
			//获取系统角色
			roleList = purviewService.getAllRoles();
			model.addAttribute(Constant.SESSION_ROLE_LIST, roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "/user/userList";
	}
	
	@RequestMapping(value="/users/getAllUsers",method=RequestMethod.POST)
	public void getAllUsers(Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("getAllUsers...start begin");
		String sEcho = "", iDisplayStart = "", iDisplayLength = "",sSearch="";
		try {
			String jsonData=request.getParameter("aoData");
			JSONArray jsonArr=JSONArray.fromObject(jsonData);
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject obj = (JSONObject) jsonArr.get(i);
				if (obj.get("name").equals("sEcho"))
					sEcho = obj.get("value").toString();
				if (obj.get("name").equals("iDisplayStart"))
					iDisplayStart = obj.get("value").toString();
				if (obj.get("name").equals("iDisplayLength"))
					iDisplayLength = obj.get("value").toString();
				if (obj.get("name").equals("sSearch"))
					sSearch = obj.get("value").toString();
				}
			logger.info("sEcho="+sEcho+"-"+"iDisplayStart="+iDisplayStart+"-"+"iDisplayLength="+iDisplayLength+"sSearch="+sSearch);
			
			PageInfo<UserPOJO> userInfo=purviewService.getAllUsers(sSearch,Integer.parseInt(iDisplayLength), Integer.parseInt(sEcho)/Integer.parseInt(iDisplayLength)+1);
			String result=this.toJSON(userInfo,Integer.parseInt(sEcho));
			response.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/users/userAdd",method=RequestMethod.POST)
	public String userAdd(UserPOJO userPojo,Model model,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirect){
		logger.info("userAdd-------");
		try {
			UserPOJO resultPOJO=accountService.validateUser(userPojo.getUserName(),Common.getIpAddr(request));
			if(resultPOJO!=null){
				resultPOJO.setUserName(userPojo.getUserName());
				resultPOJO.setRealName(userPojo.getRealName());
				resultPOJO.setRoleId(userPojo.getRoleId());
				resultPOJO.setUserType(1);
			}else{
				throw new Exception("该用户在passport系统中不存在");
			}
			
			int result=purviewService.addUser(resultPOJO);
			logger.info("result=="+result);
		} catch (Exception e) {
			logger.info("userAdd异常信息为:"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/users";
	}
	
	@RequestMapping(value="/users/prepareUserUpdate",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> prepareUserUpdate(String userId,Model model){
		Map<String,Object> result=new HashMap<String,Object>();
		UserPOJO userPojo= new UserPOJO();
		try {
			if(!StringUtils.isBlank(userId)){
				userPojo.setUserId(Long.parseLong(userId));
				UserPOJO user =purviewService.getUserById(userPojo);		
					result.put("data", user);
			}
		}catch (Exception e) {
			logger.info("prepareUserUpdate异常信息:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/users/updateUser",method=RequestMethod.GET)
	public String userUpdate(UserPOJO userPojo,Model model,RedirectAttributes redirect,HttpSession session){
		logger.info("userUpdate-------begin");
		try {
			if(userPojo!=null&&userPojo.getUserId()!=0){
				int res = purviewService.updateUser(userPojo);
				UserPOJO user=purviewService.getUserById(userPojo);
				session.setAttribute(Constant.SESSION_USER, user);
				if (res==1) {
					redirect.addFlashAttribute("msg", "操作成功");
				}else{
					redirect.addFlashAttribute("msg", "操作失败");
				}
				
				//更新用户拥有权限
			     List<ResourcePOJO>  roleResourceList = (List<ResourcePOJO>) purviewService.getResources(user.getRoleId(), 0);
			     session.setAttribute(Constant.SESSION_ROLE_RESOURCE_BEAN_LIST, roleResourceList);
			}
		} catch (Exception e) {
			logger.info("userUpdate异常信息:"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/users";
		
	}
	
	@RequestMapping(value="/users/deleteUser",method=RequestMethod.GET)
	public String userDelete(UserPOJO userPojo,RedirectAttributes redirect){
		logger.info("userDelete-------begin");
		try {
			if(userPojo!=null&&userPojo.getUserId()!=-1){
				int status = purviewService.deleteUser(userPojo);
				if (status==1) {
					redirect.addFlashAttribute("msg", "操作成功");
				}else{
					redirect.addFlashAttribute("msg", "操作失败");
				}
			}
		} catch (Exception e) {
			logger.info("userDelete异常信息:"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/users";
	}
	
	
	
	@RequestMapping(value="/roles",method=RequestMethod.GET)
	public String showRoleInfo(Model model){
		logger.info("showRoleInfo...start begin");
		return "/role/roleList";
	}
	
	@RequestMapping(value="/roles/getAllRoles",method=RequestMethod.POST)
	public void getAllRoles(Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("getAllRoles...start begin");
		String sEcho = "", iDisplayStart = "", iDisplayLength = "",sSearch="";
		try {
			String jsonData=request.getParameter("aoData");
			JSONArray jsonArr=JSONArray.fromObject(jsonData);
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject obj = (JSONObject) jsonArr.get(i);
				if (obj.get("name").equals("sEcho"))
					sEcho = obj.get("value").toString();
				if (obj.get("name").equals("iDisplayStart"))
					iDisplayStart = obj.get("value").toString();
				if (obj.get("name").equals("iDisplayLength"))
					iDisplayLength = obj.get("value").toString();
				if (obj.get("name").equals("sSearch"))
					sSearch = obj.get("value").toString();
				}
			logger.info("sEcho="+sEcho+"-"+"iDisplayStart="+iDisplayStart+"-"+"iDisplayLength="+iDisplayLength);
			PageInfo<RolePOJO> roleInfo=purviewService.getAllRolesByPage(sSearch,Integer.parseInt(iDisplayLength),Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1);
			String result=this.toJSON(roleInfo,Integer.parseInt(sEcho));
			response.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/roles/prepareAdd",method=RequestMethod.GET)
	public String showRoleAdd(Model model){
		model.addAttribute(new RolePOJO());
		return "/role/roleAdd";
	}
	
	/**
	 * 获取角色资源
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/roles/getRoleResources",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> getRoleResources(Model model){
		
		Map<String,String> param = new HashMap<String,String>();
		String treeJSON = "";
		JSONObject jsonObj = new JSONObject();
		try{
		List<ResourcePOJO> resultList=purviewService.getRoleResources(0, 0);
		if(resultList!=null&&resultList.size()>0){

		for(ResourcePOJO result:resultList){
			jsonObj.put("text",result.getResName());
			jsonObj.put("nodeid",result.getResId());
			jsonObj.put("parentid", result.getResPid());
			jsonObj.put("type", result.getResType()+"");
			treeJSON += jsonObj.toString() + ",";
				}
			}
		}catch(Exception ex){
		ex.printStackTrace();
		}
		if (!treeJSON.equals("")){
		//去掉了最后的,
		treeJSON = treeJSON.substring(0, treeJSON.lastIndexOf(","));
		}
		treeJSON = "[" + treeJSON + "]";
		if (treeJSON.equals("[{}]")){
		treeJSON = "[]";
		}
		param.put("data", treeJSON);
		return param;
		}
	
	/**
	 * 增加角色
	 */
	@RequestMapping(value="/role/addRole",method=RequestMethod.GET)
	public String addRole(@RequestParam("resId") String resId,@RequestParam("roleName") String roleName,RedirectAttributes redirectAttributes){
		
		try {
			if(roleName!=null&&resId!=null){
				RolePOJO roleBean= new RolePOJO();
				String ids[] = resId.split(",");
				roleBean.setResIds(ids);
				purviewService.addRole(roleName.trim(), roleBean.getResIds());
			}
			redirectAttributes.addFlashAttribute("msg","操作成功");
		} catch (Exception e) {
			logger.info(e.getMessage());
			redirectAttributes.addFlashAttribute("msg","操作失败:"+e.getMessage());
		}
		
		return "redirect:/roles";
	}
	
	/**
	 * 
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/roles/prepareUpdate",method=RequestMethod.GET)
	public String prepareUpdateRole(String roleId,Model model){
		try {
			if(roleId!=null||!"".equals(roleId)){
				RolePOJO role=purviewService.getRoleById(Long.parseLong(roleId));
				model.addAttribute("rolePOJO",role);
				model.addAttribute("roleId",roleId);
			}
		} catch (Exception e) {
			logger.info("prepareUpdateRole异常信息:"+e.getMessage());
			e.printStackTrace();
		}
		
		return "/role/roleUpdate";
	}
	
	
	/**
	 * 显示更新角色菜单资源
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/roles/roleResourceUpdate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> roleResourceUpdate(long data,Model model){
		Map<String,String> param = new HashMap<String,String>();
		String treeJSON = "";
		
		try{
		List<ResourcePOJO> resultAllList = purviewService.getRoleResources(0,0);	
		List<ResourcePOJO> resultList=purviewService.getRoleResources(data,0);
		if(resultAllList!=null&&resultAllList.size()>0&&resultList!=null&&resultList.size()>0){
			for(int i=0;i<resultAllList.size();i++){
				//不能定义成全局变量
				JSONObject jsonObj = new JSONObject();
				ResourcePOJO resourceAllPojo =resultAllList.get(i);
				for(int j=0;j<resultList.size();j++){
					
					ResourcePOJO resourcePojo =resultList.get(j);
					if(resourcePojo.getResId()==resourceAllPojo.getResId()){
						jsonObj.put("flag",1);//表示选中的角色的标识
					}
				}
				jsonObj.put("text",resourceAllPojo.getResName());
				jsonObj.put("nodeid",resourceAllPojo.getResId());
				jsonObj.put("parentid", resourceAllPojo.getResPid());
				jsonObj.put("type", resourceAllPojo.getResType()+"");
				treeJSON += jsonObj.toString() + ",";
				}
			}else{
				logger.info("");
			}
		}catch(Exception ex){
		ex.printStackTrace();
		}
		if (!treeJSON.equals("")){
		//去掉了最后的,
		treeJSON = treeJSON.substring(0, treeJSON.lastIndexOf(","));
		}
		treeJSON = "[" + treeJSON + "]";
		if (treeJSON.equals("[{}]")){
		treeJSON = "[]";
		}
		param.put("data", treeJSON);
		return param;
	}
	@RequestMapping(value="/roles/roleUpdate",method=RequestMethod.GET)
	public String roleUpdate(@RequestParam("roleId") Long roleId,@RequestParam("resId") String resId,@RequestParam("roleName") String roleName,@RequestParam("roleNameGlob") String roleNameGlob,RedirectAttributes redirectAttributes,HttpSession session){
		try {
			if(roleName!=null&&!"".equals(roleName)&&!roleName.equals(roleNameGlob)){
				Integer count =purviewService.checkRoleName(roleName.trim());
				if(count.compareTo(1)>=0){
					throw new Exception("该角色名已经存在!");
				}
			}
			if(roleId!=null&&resId!=null){
				RolePOJO rolePojo= new RolePOJO();
				String[] resIds= resId.split(",");
				rolePojo.setRoleId(roleId);
				rolePojo.setRoleName(roleName);
				purviewService.updateRole(rolePojo, resIds);
				
				//更新角色拥有权限
			    UserPOJO userPojo = (UserPOJO) session.getAttribute(Constant.SESSION_USER);
				List<ResourcePOJO>  roleResourceList = (List<ResourcePOJO>) purviewService.getResources(userPojo.getRoleId(), 0);
				session.setAttribute(Constant.SESSION_ROLE_RESOURCE_BEAN_LIST, roleResourceList);
			}else{
				throw new Exception("角色没有关联资源");
			}
			redirectAttributes.addFlashAttribute("msg","操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("msg","操作失败:"+e.getMessage());
		}
		return "redirect:/roles";		
	}
	
	@RequestMapping(value="/roles/deleteRole",method=RequestMethod.GET)
	public String roleDelete(RolePOJO rolePojo,RedirectAttributes redirect){
		logger.info("deleteRole----------begin");
		try {
			if(rolePojo!=null&&rolePojo.getRoleId()!=0){
				int res_code =  purviewService.deleteRole(rolePojo);
				if (res_code==1){
					redirect.addFlashAttribute("msg","操作成功");
				}
				else{
					redirect.addFlashAttribute("msg","操作失败");
				}
			}
		} catch (Exception e) {
			logger.info("roleDelete异常信息:"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/roles";
	}
	
	
	/**
	 * 角色名称重复校验
	 * @param roleName-
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/role/roleCheck",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> checkRoleName(String roleName,RedirectAttributes redirectAttributes){
		Map<String,String> result = new HashMap<String,String>();
		try {
			if(roleName!=null&&!"".equals(roleName.trim())){
				Integer count =purviewService.checkRoleName(roleName.trim());
				if(count.compareTo(0)>0){
					result.put("msg", "该角色名已经存在!");
				}else{
					result.put("msg", "该角色名可以使用!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/resources")
    public String resources(){	
    	return "/resources/resources";	
    }
	
     /**
      * 跳转资源添加页面
      * @param model
      * @param session
      * @return
      */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/resources/addResPage")
	public String addResPage(Model model,HttpSession session){
		List<ResourcePOJO> allResList= (List<ResourcePOJO>) session.getAttribute(Constant.SESSION_ALL_RESOURCE_BEAN_LIST);
		List<ResourcePOJO> resultList = new ArrayList<ResourcePOJO>();
		for(ResourcePOJO resourcePOJO : allResList){
			if(resourcePOJO.getResType()!=2){
				resultList.add(resourcePOJO);
			}
		}
		model.addAttribute("resultList",resultList);
		return "/resources/resourceAdd";
	}
	
	/**
	 * 添加资源
	 * @param Resources
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/resources/addResource")
	public String addResource(ResourcePOJO Resource,Model model,HttpSession session){
		try {
			List<ResourcePOJO> allResList= (List<ResourcePOJO>) session.getAttribute(Constant.SESSION_ALL_RESOURCE_BEAN_LIST);
			for(ResourcePOJO resourcePOJO : allResList){
				if(Resource.getResPid()==resourcePOJO.getResId()){
					Resource.setResType(resourcePOJO.getResType()+1);
					Resource.setSysFlag(resourcePOJO.getSysFlag());
					break;
				}
			}
             int resultCode = purviewService.addResource(Resource);
             if(resultCode==1){    				
     			//更新所有资源
     			List<ResourcePOJO>  allResourceList = (List<ResourcePOJO>) purviewService.getResources(0, 0);
     			session.setAttribute(Constant.SESSION_ALL_RESOURCE_BEAN_LIST, allResourceList);
             }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/resources/resources";
	}
	
	/**
	 * 删除资源
	 * @param resId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/resources/deleteResource")
	public String deleteResource(int resId,HttpSession session){
		try {
            int resultCode = purviewService.deleteResource(resId);         
            if(resultCode==1){    				
     			//更新所有资源
     			List<ResourcePOJO>  allResourceList = (List<ResourcePOJO>) purviewService.getResources(0, 0);
     			session.setAttribute(Constant.SESSION_ALL_RESOURCE_BEAN_LIST, allResourceList);
             }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/resources/resources";
	}
	
	@SuppressWarnings("unchecked")
	public String toJSON(PageInfo<?> obj,int sEcho)throws Exception{
		String jsonStr=null;
		String aaData="";
		List<Object> resultList=new ArrayList<Object>();
		if(obj!=null&&obj.getList().size()>0){
			resultList=(List<Object>) obj.getList();
			aaData=JSONArray.fromObject(resultList).toString();
		}
		if(StringUtils.isBlank(aaData)){
			aaData="0";
		}
		jsonStr="{\"sEcho\":"+sEcho+",\"iTotalRecords\":"+obj.getTotalSize()+",\"iTotalDisplayRecords\":" +
		obj.getTotalSize()+",\"aaData\":"+aaData+"}";
		
		
		return jsonStr;
	}
	
	
}
