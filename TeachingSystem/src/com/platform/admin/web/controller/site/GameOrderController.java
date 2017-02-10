/**
 * 
 */
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
import com.platform.admin.pojo.site.GameOrderInfoPOJO;
import com.platform.admin.service.site.GameOrderService;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-1-30
 */
@Controller
@RequestMapping("/order")
public class GameOrderController {
	@Autowired
	@Qualifier("gameOrderService")
	private GameOrderService gameOrderService;
	
	@RequestMapping("/toIndex")
	public String toIndex(Model model){
		PageInfo<GameOrderInfoPOJO> pageInfo = gameOrderService.getAll();
		model.addAttribute("games", pageInfo);
		return "/site/order-index";
	}
	@RequestMapping("/toUpdate")
	@ResponseBody
	public Map<String, Object> toUpdate(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		GameOrderInfoPOJO order = new GameOrderInfoPOJO();
		order = gameOrderService.getOrder(id);
		int status = Constant.RET_OK;
		if(order == null){
			status = Constant.GAME_ORDER_NOTFOUND;
			result.put("msg", Common.getMsgByCode(status));
		} else {
			result.put("status", status);
			result.put("data", order);
		}
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(GameOrderInfoPOJO order){
		System.out.println(order.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		if(order.getFlag() == Constant.DEFAULT_SAVE_FLAG) {
			//新增保存
			status = gameOrderService.saveGameOrder(order);	
		} else {
			//修改保存
			status = gameOrderService.updateGameOrder(order);
		}
		result.put("msg", Common.getMsgByCode(status));
		result.put("status", status);
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = gameOrderService.deleteGameOrder(id);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	@RequestMapping("/up")
	@ResponseBody
	public Map<String, Object> up(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = gameOrderService.upGameOrder(id);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	@RequestMapping("/down")
	@ResponseBody
	public Map<String, Object> down(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		status = gameOrderService.downGameOrder(id);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	@RequestMapping("/loadGameNames")
	@ResponseBody
	public Map<String, Object> loadGameTypes(){
		Map<String, Object> result = new HashMap<String, Object>();
		PageInfo<GameInfoPOJO> pageInfo = new PageInfo<GameInfoPOJO>();
		gameOrderService.getAllGameNames(pageInfo);
		List<GameInfoPOJO> games = pageInfo.getList();
		result.put("status", 1);
		result.put("games", games);
		return result;
	}
}
