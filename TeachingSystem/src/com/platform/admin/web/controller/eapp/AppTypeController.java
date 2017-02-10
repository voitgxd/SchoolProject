/**
 * 
 */
package com.platform.admin.web.controller.eapp;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.platform.admin.pojo.eapp.AppTypePOJO;
import com.platform.admin.service.eapp.impl.AppTypeServiceImpl;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;
import com.platform.admin.util.UploadIconUtil;

/**
 * @author gxd
 *
 * 2015-3-5
 */
@Controller
@RequestMapping("/appType")
public class AppTypeController {
	@Autowired
	@Qualifier("appTypeService")
	private AppTypeServiceImpl appTypeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/toIndex")
	public String toIndex(PageInfo pageInfo, Model model){
		if(pageInfo.getPageIndex() == 0){
			pageInfo.setPageIndex(1);
		} 
		pageInfo.setPageSize(10);
		appTypeService.getAppTypes(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		return "/eapp/apptype";
	}
	/**
	 * 新增和修改保存
	 * @author gxd
	 *
	 * 2015-3-10
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(AppTypePOJO appType, MultipartFile data, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		//新增保存
		//1.先上传图片得到图片路径
		String rootDir = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_ICON_DIR);
		String appDir = rootDir + File.separator + data.getOriginalFilename();//服务器临时存放图片路径
		appDir = appDir.replace("\\", "/");
		File desFile = new File(appDir);
		String newIconDir = "";
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			newIconDir = UploadIconUtil.uploadImage(appDir, Constant.UPLOAD_ICON_TO_SERVER_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//2.设置回pojo，执行保存
		appType.setTypePic(Constant.ICON_DIR_PREFIX + newIconDir);
		status = appTypeService.saveAppType(appType);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping("/updateSaveWithIcon")
	@ResponseBody
	public String updateSaveWithIcon(AppTypePOJO appType, MultipartFile data, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		//新增保存
		//1.先上传图片得到图片路径
		String rootDir = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_ICON_DIR);
		String appDir = rootDir + File.separator + data.getOriginalFilename();//服务器临时存放图片路径
		appDir = appDir.replace("\\", "/");
		File desFile = new File(appDir);
		String newIconDir = "";
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			newIconDir = UploadIconUtil.uploadImage(appDir, Constant.UPLOAD_ICON_TO_SERVER_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		status = appTypeService.updateAppType(appType);
		//2.设置回pojo，执行保存
		AppTypePOJO appType2 = new AppTypePOJO();
		appType2.setTypeId(appType.getTypeId());
		appType2.setTypePic(Constant.ICON_DIR_PREFIX + newIconDir);
		status = appTypeService.updateTypePic(appType2);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping("/updateSave")
	@ResponseBody
	public Map<String, Object> updateSave(AppTypePOJO appType){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		//修改保存
		status = appTypeService.updateAppType(appType);
		result.put("msg", Common.getMsgByCode(status));
		result.put("status", status);
		return result;
	}
	/**
	 * 获取要修改的数据
	 * @author gxd
	 *
	 * 2015-3-9
	 */
	@RequestMapping("/toUpdate")
	@ResponseBody
	public Map<String, Object> toUpdate(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		PageInfo<AppTypePOJO> pageInfo = new PageInfo<AppTypePOJO>();
		Integer status = Constant.RET_OK;
		status = appTypeService.getAppType(id, pageInfo);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		result.put("data", pageInfo.getList().get(0));		
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		Integer status = Constant.RET_OK;
		AppTypePOJO appType = new AppTypePOJO();
		appType.setTypeId(id);
		status = appTypeService.deleteAppType(appType);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	@RequestMapping("/cancelTop")
	@ResponseBody
	public Map<String, Object> cancelTop(int typeId, int appId){
		Map<String, Object> result = new HashMap<String, Object>();
		Integer status = Constant.RET_OK;
		status = appTypeService.cancelTop(typeId, appId);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	/**
	 * 配置游戏
	 * @author gxd
	 *
	 * 2015-3-9
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toSetApp")
	public String toSetApp(@RequestParam(value="typeId")Integer typeId, @RequestParam(value="typeName")String typeName, PageInfo pageInfo, Model model) {
		if(pageInfo.getPageIndex() == 0){
			pageInfo.setPageIndex(1);
		} 
		pageInfo.setPageSize(10);
		appTypeService.getTypeApp(typeId, pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("typeId", typeId);
		model.addAttribute("typeName", typeName);
		return "/eapp/apptype-apps";
	}
	/**
	 * 获取未添加的游戏
	 * @author gxd
	 *
	 * 2015-3-9
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toAddTypeApp")
	public String toAddTypeApp(@RequestParam(value="typeId") Integer typeId, String typeName, PageInfo pageInfo, Model model){
		if(pageInfo.getPageIndex() == 0){
			pageInfo.setPageIndex(1);
		} 
		pageInfo.setPageSize(10);
		appTypeService.getNeedApp(typeId, pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("typeId", typeId);
		model.addAttribute("typeName", typeName);
		return "/eapp/apptype-apps-add";
	}
	/**
	 * 异步获取未添加的游戏
	 * @author gxd
	 *
	 * 2015-3-9
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getNeedApps")
	@ResponseBody
	public String getNeedApps(Integer typeId, PageInfo pageInfo){
		Map<String, Object> result = new HashMap<String, Object>();
		if(pageInfo.getPageIndex() == 0){
			pageInfo.setPageIndex(1);
		} 
		pageInfo.setPageSize(10);
		appTypeService.getNeedApp(typeId, pageInfo);
		result.put("pageInfo", pageInfo);
		result.put("typeId", typeId);
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * 添加游戏到该分类
	 */
	@RequestMapping("/addAppToType")
	@ResponseBody
	public Map<String, Object> addAppToType(int typeId, String appIds){
		//处理传入的id参数
		String[] arr = appIds.split(",");
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < arr.length; i++){
			list.add(arr[i]);
		}
		Iterator<String> sListIterator = list.iterator();  
		while(sListIterator.hasNext()){  
		    String e = sListIterator.next();  
		    if(e.equals("")){  
		    	sListIterator.remove();  
		    }  
		}
		appIds = "";
		for(int i = 0; i<list.size(); i++){
			if(i == list.size() - 1) {
				appIds += list.get(i);
			} else {
				appIds += list.get(i) + ",";
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = appTypeService.addAppToType(typeId, appIds);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	/**
	 * 删除分类游戏
	 */
	@RequestMapping("/deleteAppFromType")
	@ResponseBody
	public Map<String, Object> deleteAppFromType(int appId, int typeId){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = appTypeService.deleteAppFromType(appId, typeId);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	/**
	 * 类型所属游戏排行上移、下移
	 * @author gxd
	 *
	 * 2015-3-10
	 */
	@RequestMapping("/upOrDown")
	@ResponseBody
	public Map<String, Object> upOrDown(int flag, int typeId, int appId){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = appTypeService.upOrDown(flag, typeId, appId);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	/**
	 * 类型排行上下移动
	 * @author gxd
	 *
	 * 2015-4-27
	 */
	@RequestMapping("/typeUpOrDown")
	@ResponseBody
	public Map<String, Object> typeUpOrDown(int flag, int typeId){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = appTypeService.typeUpOrDown(flag, typeId);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	@RequestMapping("/uploadPic")
	@ResponseBody
	public String uploadPic(Integer picTypeId, MultipartFile data, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		String rootDir = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_ICON_DIR);
		String serverconDir = rootDir + File.separator + data.getOriginalFilename();
		serverconDir = serverconDir.replace("\\", "/");
		File desFile = new File(serverconDir);
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			//调工具类的先删除  后添加
			String newIconDir = UploadIconUtil.uploadImage(serverconDir, Constant.UPLOAD_ICON_TO_SERVER_DIR);
			AppTypePOJO appType = new AppTypePOJO();
			appType.setTypeId(picTypeId);
			appType.setTypePic(Constant.ICON_DIR_PREFIX + "/" + newIconDir);
			status = appTypeService.updateTypePic(appType);
		} catch (IOException e) {
			status = -1;
			e.printStackTrace();
		}
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * 置顶
	 * @author gxd
	 *
	 * 2015-3-11
	 */
	@RequestMapping("/setAppToTop")
	@ResponseBody
	public Map<String, Object> setAppToTop(int typeId, int appId, String expireTime){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		//置顶
		expireTime = expireTime + ":00";
		status = appTypeService.setAppToTop(typeId, appId, expireTime);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
}
