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

import com.platform.admin.pojo.site.GameDownloadInfoPOJO;
import com.platform.admin.pojo.site.GameTypeInfoPOJO;
import com.platform.admin.service.site.impl.GameDownloadServiceImpl;
import com.platform.admin.service.site.impl.GameTypeServiceImpl;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-2-9
 */
@Controller
@RequestMapping("/gameDownload")
public class GameDownloadController {
	@Autowired
	@Qualifier("gameDownloadService")
	private GameDownloadServiceImpl gameDownloadService;
	
	@Autowired
	@Qualifier("gameTypeService")
	private GameTypeServiceImpl gameTypeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/toIndex")
	public String toIndex(PageInfo pageInfo, Model model){
		if(pageInfo.getPageIndex() == 0){
			pageInfo.setPageIndex(1);
		} 
		pageInfo.setPageSize(10);
		gameDownloadService.getRecords(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		return "/site/gameDownload-index";
	}
	@RequestMapping("/toUpdate")
	@ResponseBody
	public Map<String, Object> toUpdate(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		GameDownloadInfoPOJO record = new GameDownloadInfoPOJO();
		record.setLogId(id);
		Integer status = Constant.RET_OK;
		status = gameDownloadService.getRecord(record);	
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		result.put("data", record);		
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value="id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		GameDownloadInfoPOJO record = new GameDownloadInfoPOJO();
		record.setLogId(id);
		status = gameDownloadService.deleteGameDownload(record);
		result.put("status", status);
		result.put("msg", Common.getMsgByCode(status));
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(GameDownloadInfoPOJO record){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = Constant.RET_OK;
		//新增保存
		status = gameDownloadService.saveGameDownload(record);	
		result.put("msg", Common.getMsgByCode(status));
		result.put("status", status);
		return result;
	}
	@RequestMapping("/loadGameTypes")
	@ResponseBody
	public Map<String, Object> loadGameTypes(){
		Map<String, Object> result = new HashMap<String, Object>();
		PageInfo<GameTypeInfoPOJO> pageInfo = new PageInfo<GameTypeInfoPOJO>();
		gameTypeService.getAllTypes(pageInfo);
		List<GameTypeInfoPOJO> types = pageInfo.getList();
		result.put("status", 1);
		result.put("types", types);
		return result;
	}
}
