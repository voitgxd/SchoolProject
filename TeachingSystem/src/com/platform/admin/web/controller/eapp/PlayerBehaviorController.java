package com.platform.admin.web.controller.eapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.admin.service.eapp.PlayerBehaviorService;
import com.platform.admin.util.PageInfo;

/**
 * 玩家游戏行为控制层
 * 
 * @author gxd
 * 
 */
@Controller
@RequestMapping("/playerBehavior")
public class PlayerBehaviorController {
	@Autowired
	@Qualifier("playerBehaviorService")
	private PlayerBehaviorService playerBehaviorService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/toIndex")
	public String toIndex(PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		playerBehaviorService.queryPlayerBehavior(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		return "/eapp/playerBehavior-index";
	}

	public PlayerBehaviorService getPlayerBehaviorService() {
		return playerBehaviorService;
	}

	public void setPlayerBehaviorService(
			PlayerBehaviorService playerBehaviorService) {
		this.playerBehaviorService = playerBehaviorService;
	}

}
