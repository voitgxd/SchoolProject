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

import com.platform.admin.pojo.site.GameTypeInfoPOJO;
import com.platform.admin.service.site.GameTypeService;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-2-9
 */
@Controller
@RequestMapping("/gameType")
public class GameTypeController {
	@Autowired
	@Qualifier("gameTypeService")
	private GameTypeService gameTypeService;
	
	@RequestMapping("/toIndex")
	public String toIndex(Model model){
		PageInfo<GameTypeInfoPOJO> pageInfo = new PageInfo<GameTypeInfoPOJO>();
		gameTypeService.getAllTypes(pageInfo);
		List<GameTypeInfoPOJO> types = pageInfo.getList();
		model.addAttribute("types", types);
		return "/site/gameType-index";
	}
	@RequestMapping("/toUpdate")
	@ResponseBody
	public Map<String, Object> toUpdate(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		PageInfo<GameTypeInfoPOJO> pageInfo = new PageInfo<GameTypeInfoPOJO>();
		int status = Constant.RET_OK;
		status = gameTypeService.getGameTypeById(pageInfo, id);		
		result.put("status", status);
		result.put("data", pageInfo.getList().get(0));
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(GameTypeInfoPOJO gameType){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		if(gameType.getGameTypeId() == Constant.DEFAULT_SAVE_FLAG) {
			//新增保存
			status = gameTypeService.saveGameType(gameType);	
		} else {
			//修改保存
			status = gameTypeService.updateGameType(gameType);
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
		status = gameTypeService.deleteGameType(id);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
}
