package com.platform.admin.web.controller.eapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.admin.pojo.eapp.AppStatPOJO;
import com.platform.admin.service.eapp.AppStatService;
import com.platform.admin.util.PageInfo;

/**
 * 游戏行为
 * 
 * @author gxd
 * 
 */
@Controller
@RequestMapping("/appStat")
public class AppStatController {
	@Autowired
	@Qualifier("appStatService")
	private AppStatService appStatService;

	@RequestMapping("/toIndex")
	/**
	 * 查询所有游戏行为
	 */
	public String toIndex(PageInfo<AppStatPOJO> pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		appStatService.queryAppStat(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		return "/eapp/appStat-index";
	}

	public AppStatService getAppStatService() {
		return appStatService;
	}

	public void setAppStatService(AppStatService appStatService) {
		this.appStatService = appStatService;
	}

}
