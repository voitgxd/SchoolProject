/**
 * 
 */
package com.platform.admin.web.controller.eapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.eapp.AppTopicPOJO;
import com.platform.admin.service.eapp.impl.TopicServiceImpl;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;
import com.platform.admin.util.UploadIconUtil;

/**
 * @author gxd
 * 
 *         2015-3-11
 */
@Controller
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	@Qualifier("topicService")
	private TopicServiceImpl topicService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/toIndex")
	public String toIndex(PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		topicService.getTopics(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		return "/eapp/topic";
	}

	/**
	 * 获取要修改的数据
	 * 
	 * @author gxd
	 * 
	 *         2015-3-9
	 */
	@RequestMapping("/toUpdate")
	@ResponseBody
	public Map<String, Object> toUpdate(@RequestParam(value = "id") int id) {
		Map<String, Object> result = new HashMap<String, Object>();
		PageInfo<AppTopicPOJO> pageInfo = new PageInfo<AppTopicPOJO>();
		topicService.getTopic(id, pageInfo);
		result.put("data", pageInfo.getList().get(0));
		return result;
	}

	/**
	 * 新增和修改保存
	 * 
	 * @author gxd
	 * 
	 *         2015-3-10
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(AppTopicPOJO topic, MultipartFile data,
			HttpServletRequest request, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		// 新增保存
		// 1.先上传图片得到图片路径
		String rootDir = request.getSession().getServletContext().getRealPath(
				Constant.UPLOAD_ICON_DIR);
		String appDir = rootDir + File.separator + data.getOriginalFilename();// 服务器临时存放图片路径
		appDir = appDir.replace("\\", "/");
		File desFile = new File(appDir);
		String newIconDir = "";
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			newIconDir = UploadIconUtil.uploadImage(appDir,
					Constant.UPLOAD_ICON_TO_SERVER_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 2.设置回pojo，执行保存
		topic.setTopicPic(Constant.ICON_DIR_PREFIX + newIconDir);
		// 修改者的id设置回pojo中
		UserPOJO user = (UserPOJO) session.getAttribute(Constant.SESSION_USER);
		topic.setUpdatePassportId(user.getUserId());
		topic.setExpireTime(topic.getExpireTime() + ":00");
		status = topicService.saveTopic(topic);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping("/updateSave")
	@ResponseBody
	public String updateSave(AppTopicPOJO topic, MultipartFile data,
			HttpServletRequest request, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		// 新增保存
		// 1.先上传图片得到图片路径
		String rootDir = request.getSession().getServletContext().getRealPath(
				Constant.UPLOAD_ICON_DIR);
		String appDir = rootDir + File.separator + data.getOriginalFilename();// 服务器临时存放图片路径
		appDir = appDir.replace("\\", "/");
		File desFile = new File(appDir);
		String newIconDir = "";
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			newIconDir = UploadIconUtil.uploadImage(appDir,
					Constant.UPLOAD_ICON_TO_SERVER_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 修改者的id设置回pojo中
		UserPOJO user = (UserPOJO) session.getAttribute(Constant.SESSION_USER);
		topic.setUpdatePassportId(user.getUserId());
		topic.setExpireTime(topic.getExpireTime() + ":00");
		status = topicService.updateTopic(topic);
		// 2.设置回pojo，执行保存
		AppTopicPOJO topic2 = new AppTopicPOJO();
		topic2.setTopicId(topic.getTopicId());
		topic2.setTopicPic(Constant.ICON_DIR_PREFIX + newIconDir);
		status = topicService.updateTopicPic(topic2);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return JSONObject.fromObject(result).toString();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(AppTopicPOJO topic, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		// 修改保存
		// 修改者的id设置回pojo中
		UserPOJO user = (UserPOJO) session.getAttribute(Constant.SESSION_USER);
		topic.setUpdatePassportId(user.getUserId());
		System.out.println(topic.getUpdatePassportId());
		topic.setExpireTime(topic.getExpireTime() + ":00");
		status = topicService.updateTopic(topic);
		result.put("msg", Common.getMsgByCode(status));
		result.put("status", status);
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value = "id") int id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Integer status = Constant.RET_OK;
		AppTopicPOJO topic = new AppTopicPOJO();
		topic.setTopicId(id);
		status = topicService.deleteTopic(topic);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}

	@RequestMapping("/uploadPic")
	@ResponseBody
	public String uploadPic(Integer topicId, MultipartFile data,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		String rootDir = request.getSession().getServletContext().getRealPath(
				Constant.UPLOAD_ICON_DIR);
		String serverconDir = rootDir + File.separator
				+ data.getOriginalFilename();
		serverconDir = serverconDir.replace("\\", "/");
		File desFile = new File(serverconDir);
		try {
			FileUtils.copyInputStreamToFile(data.getInputStream(), desFile);
			// 调工具类的先删除 后添加
			String newIconDir = UploadIconUtil.uploadImage(serverconDir,
					Constant.UPLOAD_ICON_TO_SERVER_DIR);
			AppTopicPOJO topic = new AppTopicPOJO();
			topic.setTopicId(topicId);
			topic.setTopicPic(Constant.ICON_DIR_PREFIX + "/" + newIconDir);
			status = topicService.updateTopicPic(topic);
		} catch (IOException e) {
			status = -1;
			e.printStackTrace();
		}
		result.put("status", status);
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * 配置游戏
	 * 
	 * @author gxd
	 * 
	 *         2015-3-9
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toSetApp")
	public String toSetApp(@RequestParam(value = "topicId") int topicId,
			@RequestParam(value = "topicName") String topicName,
			PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		topicService.getTopicApp(topicId, pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("topicId", topicId);
		model.addAttribute("topicName", topicName);
		return "/eapp/topic-apps";
	}

	/**
	 * 删除专题游戏
	 */
	@RequestMapping("/deleteAppFromTopic")
	@ResponseBody
	public Map<String, Object> deleteAppFromTopic(int topicId, int appId) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = topicService.deleteAppFromTopic(topicId, appId);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}

	/**
	 * 专题所属游戏排行上移、下移
	 * 
	 * @author gxd
	 * 
	 *         2015-3-10
	 */
	@RequestMapping("/upOrDown")
	@ResponseBody
	public Map<String, Object> upOrDown(int flag, int topicId, int appId) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = topicService.upOrDown(flag, topicId, appId);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}

	/**
	 * 置顶
	 * 
	 * @author gxd
	 * 
	 *         2015-3-11
	 */
	@RequestMapping("/setAppToTop")
	@ResponseBody
	public Map<String, Object> setAppToTop(int topicId, int appId,
			String expireTime) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		// 置顶
		status = topicService.setAppToTop(topicId, appId, expireTime);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	/**
	 * 取消置顶
	 * 
	 * @author gxd
	 * 
	 *         2015-3-11
	 */
	@RequestMapping("/cancelTop")
	@ResponseBody
	public Map<String, Object> cancelTop(int topicId, int appId) {
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		// 置顶
		status = topicService.cancelTop(topicId, appId);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}

	/**
	 * 获取未添加的游戏
	 * 
	 * @author gxd
	 * 
	 *         2015-3-9
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toAddTopicApp")
	public String toAddTypeApp(@RequestParam(value = "topicId") int topicId,
			@RequestParam(value = "topicName") String topicName,
			PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		topicService.getNeedApp(topicId, pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("topicId", topicId);
		model.addAttribute("topicName", topicName);
		return "/eapp/topic-apps-add";
	}

	/**
	 * 异步获取未添加的游戏
	 * 
	 * @author gxd
	 * 
	 *         2015-3-9
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getNeedApps")
	@ResponseBody
	public String getNeedApps(int topicId, PageInfo pageInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		topicService.getNeedApp(topicId, pageInfo);
		result.put("pageInfo", pageInfo);
		result.put("topicId", topicId);
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * 添加游戏到该专题
	 */
	@RequestMapping("/addAppToTopic")
	@ResponseBody
	public Map<String, Object> addAppToTopic(int topicId, String appIds) {
		// 处理传入的id参数
		String[] arr = appIds.split(",");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		Iterator<String> sListIterator = list.iterator();
		while (sListIterator.hasNext()) {
			String e = sListIterator.next();
			if (e.equals("")) {
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
		status = topicService.addAppToTopic(topicId, appIds);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
}
