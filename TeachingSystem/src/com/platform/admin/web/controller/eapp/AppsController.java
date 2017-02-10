/**
 * 
 */
package com.platform.admin.web.controller.eapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.service.eapp.impl.AppsServiceImpl;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;
import com.platform.admin.util.UploadFile;
import com.platform.admin.util.UploadIconUtil;
/**
 * @author gxd
 *
 * 2015-3-3
 */
@Controller
@RequestMapping("/apps")
public class AppsController {
	@Autowired
	@Qualifier("appsService")
	private AppsServiceImpl appsService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/toIndex")
	public String toIndex(PageInfo pageInfo, Model model){
		if(pageInfo.getPageIndex() == 0){
			pageInfo.setPageIndex(1);
		} 
		pageInfo.setPageSize(10);
		appsService.getApps(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		return "/eapp/apps";
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		AppsPOJO app = new AppsPOJO();
		app.setAppId(id);
		status = appsService.deleteApps(app);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	@RequestMapping("/toUpdate")
	@ResponseBody
	public Map<String, Object> toUpdate(@RequestParam(value="id") int id){
		Map<String, Object> result = new HashMap<String, Object>();
		PageInfo<AppsPOJO> pageInfo = new PageInfo<AppsPOJO>();
		Integer status = Constant.RET_OK;
		if("".equals(id)){
			status = Constant.GAME_ORDER_NOTFOUND;
			result.put("msg", Common.getMsgByCode(status));
		} else {
			status = appsService.getApp(id, pageInfo);
			result.put("status", status);
			result.put("msg", Common.getMsgByCode(status));
			result.put("data", pageInfo.getList().get(0));	
		}
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public String save(AppsPOJO app, MultipartFile data, HttpServletRequest request){
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
		app.setAppIcon(Constant.ICON_DIR_PREFIX + newIconDir);
		status = appsService.saveApp(app);
		result.put("msg", Common.getMsgByCode(status));
		result.put("status", status);
		System.out.println("status:"+status + "mgs:" + Common.getMsgByCode(status));
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping("/updateSave")
	@ResponseBody
	public Map<String, Object> updateSave(AppsPOJO app){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		//修改保存
		status = appsService.updateApp(app);
		result.put("msg", Common.getMsgByCode(status));
		result.put("status", status);
		return result;
	}
	@RequestMapping("/updateSaveWithIcon")
	@ResponseBody
	public String updateSaveWithIcon(AppsPOJO app, MultipartFile data, HttpServletRequest request){
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
		status = appsService.updateApp(app);
		//2.设置回pojo，执行修改图标操作
		AppsPOJO app2 = new AppsPOJO();
		app2.setAppId(app.getAppId());
		app2.setAppIcon(Constant.ICON_DIR_PREFIX + newIconDir);
		status = appsService.addAppIcon(app2);
		result.put("msg", Common.getMsgByCode(status));
		result.put("status", status);
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping("/uploadApp")
	@ResponseBody
	public String uploadApp(int appId, String address, MultipartFile data, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		String rootDir = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_DIR);
		String appDir = rootDir + File.separator + address + File.separator + data.getOriginalFilename();
		appDir = appDir.replace("\\", "/");
		File desFile = new File(appDir);
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			//1.对接接口上传游戏到指定服务器返回    应用路径
			//2.修改应用地址
//			AppsPOJO app = new AppsPOJO();
//			app.setAppId(appId);
//			app.setAppUrl(appUrl);
//			appsService.updateAppUrl(app);
			result.put("status", status);
		} catch (IOException e) {
			status = -1;
			result.put("status", status);
			e.printStackTrace();
		}		
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping("/uploadIcon")
	@ResponseBody
	public String uploadIcon(Integer appId, MultipartFile data, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		//1.先上传图片得到图片路径
		String rootDir = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_ICON_DIR);
		String serverconDir = rootDir + File.separator + data.getOriginalFilename();//服务器临时存放图片路径
		serverconDir = serverconDir.replace("\\", "/");
		File desFile = new File(serverconDir);
		String newIconDir = "";
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			newIconDir = UploadIconUtil.uploadImage(serverconDir, Constant.UPLOAD_ICON_TO_SERVER_DIR);
			AppsPOJO app = new AppsPOJO();
			app.setAppId(appId);
			app.setAppIcon(Constant.ICON_DIR_PREFIX + "/" + newIconDir);
			status = appsService.addAppIcon(app);
			result.put("status", status);
		} catch (IOException e) {
			status = -1;
			result.put("status", status);
			e.printStackTrace();
		}
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping("/uploadExcell")
	@ResponseBody
	public String uploadExcell(MultipartFile data, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Integer status = Constant.RET_OK;
		String rootDir = request.getSession().getServletContext().getRealPath(Constant.UPLOAD_EXCELL_DIR);
		String serverExcellDir = rootDir + File.separator + data.getOriginalFilename();
		
		serverExcellDir = serverExcellDir.replace("\\", "/");
		File desFile = new File(serverExcellDir);
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			//解析Excell将数据封装在集合中
			String[][] resultData = UploadFile.getData(desFile, 1);//从第2行获取数据,不取例子数据
			int rowLength = resultData.length;
			List<AppsPOJO> appList = new ArrayList<AppsPOJO>();
			for (int i = 0; i < rowLength; i++) {
				AppsPOJO app = new AppsPOJO();
				app.setAppName(resultData[i][0]);
				app.setAppIcon(resultData[i][1]);
				app.setAppUrl(resultData[i][2]);
				app.setPlatformType(Integer.parseInt(resultData[i][3]));
				app.setAppType(Integer.parseInt(resultData[i][4]));
				app.setAppDesc(resultData[i][5]);
				app.setDeveloperId(0);
				appList.add(app);
			}
			appsService.insertApp(appList);
			status = 1;
			result.put("status", status);
		} catch (IOException e) {
			status = -1;
			result.put("status", status);
			e.printStackTrace();
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * 下载模板
	 * @author gxd
	 *
	 * 2015-3-5
	 */
	@RequestMapping("/downloadExcell")
	@ResponseBody
	public String downloadExcell(HttpServletRequest request,
			HttpServletResponse response) {
		String excellDir = request.getSession().getServletContext().getRealPath(Constant.DOWNLOAD_EXCELL_DIR);
		excellDir = excellDir.replace('\\', '/');
		UploadFile.writeFile(new File(excellDir), response);
		return "/eapp/apps";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/displayFunc")
	public String displayFunc(Integer appStateFlag, PageInfo pageInfo, Model model){
		if(pageInfo.getPageIndex() == 0){
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		if(appStateFlag == null){
			appStateFlag = 1;
		}
		appsService.getUncheckedApps(appStateFlag, pageInfo);
		model.addAttribute("appStateFlag", appStateFlag);
		model.addAttribute("pageInfo", pageInfo);
		return "/eapp/appfunc";
	}
	/**
	 * 审核选中APP
	 * @author gxd
	 *
	 * 2015-3-6
	 */
	@RequestMapping("/checkChooseApp")
	@ResponseBody
	public Map<String, Object> checkChooseApp(String appIds, Integer statusCode){
		Map<String, Object> result = new HashMap<String, Object>();
		//审核业务
		Integer status = Constant.RET_OK;
		status = appsService.checkApp(appIds, statusCode);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
}
