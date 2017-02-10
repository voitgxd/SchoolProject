package com.platform.admin.web.controller.eapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.admin.pojo.eapp.AppTagePOJO;
import com.platform.admin.pojo.eapp.TagPOJO;
import com.platform.admin.service.eapp.TagManageService;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * 标签管理控制层
 * 
 * @author chentao
 * 
 */
@Controller
@RequestMapping("/tagManage")
public class TagManageController {
	@Autowired
	@Qualifier("tagManageService")
	private TagManageService tagManageService;

	/**
	 * 查询全部标签信息
	 * 
	 * @param pageInfo
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/showTagInfo")
	public String showTagInfo(PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		tagManageService.getAllTagInfo(pageInfo);
		model.addAttribute("tagInfo", pageInfo);
		return "/eapp/tagManage-index";
	}

	/**
	 * 删除标签信息
	 * 
	 * @param tagId
	 * @return
	 */
	@RequestMapping("/deleteTagInfo")
	@ResponseBody
	public Map<String, Object> deleteTagInfo(
			@RequestParam("tagId") Integer tagId) {
		Map<String, Object> map = new HashMap<String, Object>();
		TagPOJO tag = new TagPOJO();
		tag.setTagId(tagId);
		int retCode = tagManageService.deleteTag(tag);
		map.put("result", retCode);
		map.put("message", Common.getMsgByCode(retCode));
		return map;
	}

	/**
	 * 添加标签信息
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(TagPOJO tag) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int retCode = Constant.RET_OK;
		if (tag.getTagId() == -1) {
			retCode = tagManageService.addTag(tag);
		} else {
			retCode = tagManageService.updateTagInfo(tag);
		}
		resMap.put("status", retCode);
		resMap.put("msg", Common.getMsgByCode(retCode));
		return resMap;
	}

	/**
	 * 获取要修改的标签信息
	 * 
	 * @return
	 */
	@RequestMapping("/getUpdateInfo")
	@ResponseBody
	public Map<String, Object> getTagInfo(@RequestParam("tagId") int tagId) {
		TagPOJO tag = new TagPOJO();
		tag.setTagId(tagId);
		PageInfo<TagPOJO> pageInfo = tagManageService.getTagInfo(tag);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oneTag", pageInfo.getList().get(0));
		return map;
	}

	/**
	 * 获取游戏信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/showApps")
	public String showAppsByTag(@RequestParam("tagId") Integer tagId,
			PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		tagManageService.getAppsByTag(tagId, pageInfo);
		model.addAttribute("tagId", tagId);
		model.addAttribute("pageInfo", pageInfo);
		return "/eapp/tagApps-apps";

	}

	/**
	 * 取消游戏标签
	 * 
	 * @return
	 */
	@RequestMapping("/deleteAppTag")
	@ResponseBody
	public Map<String, Object> deleteAppTag(
			@RequestParam("tagId") Integer tagId,
			@RequestParam("appId") Integer appId) {
		AppTagePOJO appTag = new AppTagePOJO();
		appTag.setAppId(appId);
		appTag.setTagId(tagId);
		int retCode = tagManageService.deleteAppTag(appTag);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", retCode);
		map.put("msg", Common.getMsgByCode(retCode));
		return map;

	}

	/**
	 * 获取没有打某标签的游戏信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/showAddApp")
	public String showAddApp(@RequestParam("tagId") Integer tagId,
			PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		tagManageService.getAddApps(tagId, pageInfo);
		model.addAttribute("tagName", pageInfo.getTagName());
		model.addAttribute("tagId", tagId);
		model.addAttribute("pageInfo", pageInfo);

		return "/eapp/tagApp-apps-add";

	}

	/**
	 * 异步获取没有打某标签的游戏信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getNeedApps")
	@ResponseBody
	public String getNeedApps(Integer tagId, Integer pageIndex) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		Map<String, Object> result = new HashMap<String, Object>();
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		tagManageService.getAddApps(tagId, pageInfo);
		result.put("pageInfo", pageInfo);
		result.put("tagId", tagId);
		return JSONObject.fromObject(result).toString();
	}

	// 给未打标签的游戏打标签
	@RequestMapping("/addAppToTag")
	@ResponseBody
	public Map<String, Object> addAppToTag(int tagId, String appIds) {
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
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				appIds += list.get(i);
			} else {
				appIds += list.get(i) + ",";
			}
		}

		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = tagManageService.addAppToTag(tagId, appIds);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}

	public TagManageService getTagManageService() {
		return tagManageService;
	}

	public void setTagManageService(TagManageService tagManageService) {
		this.tagManageService = tagManageService;
	}
}
