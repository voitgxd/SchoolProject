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
import com.platform.admin.pojo.site.GiftPackagePOJO;
import com.platform.admin.service.site.GameInfoService;
import com.platform.admin.service.site.GiftPackageService;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * 礼包Controller
 * 
 * @author gxd
 * 
 */
@Controller
@RequestMapping("/giftPackage")
public class GiftPackageController {
	@Autowired
	@Qualifier("giftPackageService")
	private GiftPackageService giftPackageService;
	@Autowired
	@Qualifier("gameInfoService")
	private GameInfoService gameInfoService;

	/**
	 * 查询所有礼包信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showGiftPackage")
	public String showGiftPackage(Model model) {
		PageInfo<GiftPackagePOJO> pageInfo = giftPackageService
				.queryAllGiftPackage();

		PageInfo<GameInfoPOJO> gameSel = gameInfoService.queryAllGames();
		List<GiftPackagePOJO> gpfList = pageInfo.getList();
		List<GameInfoPOJO> gameList = gameSel.getList();
		model.addAttribute("gpfList", gpfList);
		model.addAttribute("gameList", gameList);
		return "/site/giftPackage-index";

	}

	@RequestMapping("/loadGameInfo")
	@ResponseBody
	public Map<String, Object> loadGameInfo() {
		PageInfo<GameInfoPOJO> gameSel = gameInfoService.queryAllGames();
		List<GameInfoPOJO> gameList = gameSel.getList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("games", gameList);
		return map;
	}

	/**
	 * 删除礼包信息
	 * 
	 * @return
	 */
	@RequestMapping("/deleteGiftPackage")
	@ResponseBody
	public Map<String, Object> deleteGiftPackage(
			@RequestParam("packageId") Integer packageId) {
		GiftPackagePOJO gpp = new GiftPackagePOJO();
		int status = Constant.RET_OK;
		gpp.setPackageId(packageId);
		status = giftPackageService.deleteGiftPackage(gpp);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;

	}

	/**
	 * 保存数据
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(GiftPackagePOJO gift) {
		System.out.println(gift.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		if (gift.getPackageId() == -1) {
			// 新增保存
			status = giftPackageService.addGiftPackage(gift);
		} else {
			// 修改保存
			status = giftPackageService.updateGiftPackage(gift);
		}
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}

	/**
	 * 查询要修改的礼包信息
	 * 
	 * @return
	 */
	@RequestMapping("/getUpdateInfo")
	@ResponseBody
	public Map<String, Object> getUpdateInfo(
			@RequestParam("packageId") Integer packageId) {
		Map<String, Object> result = new HashMap<String, Object>();
		GiftPackagePOJO gift = new GiftPackagePOJO();
		gift.setPackageId(packageId);
		PageInfo<GiftPackagePOJO> pageInfo = new PageInfo<GiftPackagePOJO>();
		pageInfo = giftPackageService.queryUpdateGiftPackage(gift);
		result.put("oneGift", pageInfo.getList().get(0));
		return result;
	}

	public GiftPackageService getGiftPackageService() {
		return giftPackageService;
	}

	public void setGiftPackageService(GiftPackageService giftPackageService) {
		this.giftPackageService = giftPackageService;
	}

	public GameInfoService getGameInfoService() {
		return gameInfoService;
	}

	public void setGameInfoService(GameInfoService gameInfoService) {
		this.gameInfoService = gameInfoService;
	}

}
