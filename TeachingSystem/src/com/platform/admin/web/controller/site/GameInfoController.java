package com.platform.admin.web.controller.site;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.service.site.GameInfoService;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * 游戏信息Controller
 * 
 * @author gxd
 * 
 */
@Controller
@RequestMapping("/gameInfo")
public class GameInfoController {
	@Autowired
	@Qualifier("gameInfoService")
	private GameInfoService gameInfo;

	/**
	 * 查询所有游戏
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showGameInfo")
	public String showGameInfo(Model model) {
		PageInfo<GameInfoPOJO> pageInfo = gameInfo.queryAllGames();
		List<GameInfoPOJO> gameList = pageInfo.getList();
		model.addAttribute("gameList", gameList);
		return "/site/gameInfo-index";
	}

	/**
	 * 删除游戏信息
	 * 
	 * @param gameId
	 * @return
	 */
	@RequestMapping("/deleteGameInfo")
	@ResponseBody
	public Map<String, Object> deleteGameInfo(
			@RequestParam("gameId") Integer gameId) {
		GameInfoPOJO game = new GameInfoPOJO();
		game.setGameId(gameId);
		int retCode = Constant.RET_OK;
		retCode = gameInfo.deleteGameInfo(game);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("retCode", retCode);
		resultMap.put("msg", Common.getMsgByCode(retCode));
		return resultMap;

	}

	/**
	 * 添加保存和修改保存的方法
	 * 
	 * @param gameInfoPOJO
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(GameInfoPOJO gameInfoPOJO) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int retCode = Constant.RET_OK;
		if (gameInfoPOJO.getGameId() == -1) {
			retCode = gameInfo.addGameInfo(gameInfoPOJO);
		} else {
			retCode = gameInfo.upDateGameInfo(gameInfoPOJO);
		}
		resMap.put("retCode", retCode);
		resMap.put("msg", Common.getMsgByCode(retCode));
		return resMap;
	}

	/**
	 * 获取要修改的游戏信息
	 * 
	 * @param gameId
	 * @return
	 */
	@RequestMapping("/getUpdateGameInfo")
	@ResponseBody
	public Map<String, Object> getUpdateGameInfo(
			@RequestParam("gameId") Integer gameId) {
		GameInfoPOJO game = new GameInfoPOJO();
		game.setGameId(gameId);
		PageInfo<GameInfoPOJO> onePage = new PageInfo<GameInfoPOJO>();
		onePage = gameInfo.getUpdateGame(game);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("updateGame", onePage.getList().get(0));
		return resMap;
	}

	public GameInfoService getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(GameInfoService gameInfo) {
		this.gameInfo = gameInfo;
	}

}
