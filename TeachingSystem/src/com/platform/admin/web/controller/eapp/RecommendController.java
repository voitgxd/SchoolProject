/**
 * 
 */
package com.platform.admin.web.controller.eapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.eapp.RecommendPOJO;
import com.platform.admin.service.eapp.impl.RecommendServiceImpl;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-3-23
 */
@Controller
@RequestMapping("/recommend")
public class RecommendController {
	
	@Autowired
	@Qualifier("recommendService")
	private RecommendServiceImpl recommendService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/toIndex")
	public String toIndex(Integer recommendType, PageInfo pageInfo, Model model){
		if(pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		if(recommendType == null) {
			recommendType = 1;
		}
		pageInfo.setPageSize(10);
		recommendService.getRecommendApps(recommendType, pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("recommendType", recommendType);
		return "/eapp/recommend";
	}
	@ResponseBody
	@RequestMapping("/cancelRecommend")
	public Map<String, Object> cancelRecommend(int appId, int recommendType){
		Map<String, Object> result = new HashMap<String, Object>();
		Integer status = Constant.RET_OK;
		status = recommendService.cancelRecommend(appId, recommendType);
		System.out.println(status);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/toRecommendNeedApp")
	public String toRecommendNeedApp(Integer recommendType, PageInfo pageInfo, Model model){
		if(pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		if(recommendType == null) {
			recommendType = 1;
		}
		pageInfo.setPageSize(10);
		recommendService.toRecommendNeedApp(recommendType, pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("recommendType", recommendType);
		return "/eapp/recommend-apps-add";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getNeedApps")
	@ResponseBody
	public String getNeedApps(int recommendType, PageInfo pageInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		if(recommendType == 0){
			recommendType = 1;
		}
		pageInfo.setPageSize(10);
		recommendService.toRecommendNeedApp(recommendType, pageInfo);
		result.put("pageInfo", pageInfo);
		result.put("recommendType", recommendType);
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * 添加游戏到该推荐
	 */
	@RequestMapping("/addAppToRecommend")
	@ResponseBody
	public Map<String, Object> addAppToRecommend(RecommendPOJO recommend, String appIds, HttpSession session) {
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
		UserPOJO user = (UserPOJO) session.getAttribute(Constant.SESSION_USER);
		int passportId = (int) user.getUserId();
		recommend.setPassportId(passportId);
		if(!(recommend.getEffectTime().equals("") && recommend.getExpireTime().equals(""))){
			recommend.setEffectTime(recommend.getEffectTime() + ":00");
			recommend.setExpireTime(recommend.getExpireTime() + ":00");
		}
		status = recommendService.addAppToRecommend(recommend, appIds);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	/**
	 * 推荐所属游戏排行上移、下移
	 * @author gxd
	 *
	 * 2015-3-10
	 */
	@RequestMapping("/upOrDown")
	@ResponseBody
	public Map<String, Object> upOrDown(int flag, int recommendType, int appId){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = recommendService.upOrDown(flag, recommendType, appId);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}

}
