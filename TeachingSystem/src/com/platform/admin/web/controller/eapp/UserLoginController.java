package com.platform.admin.web.controller.eapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.admin.service.eapp.UserLoginService;
import com.platform.admin.util.PageInfo;

/**
 * 玩家登陆详情
 * 
 * @author gxd
 * 
 */
@Controller
@RequestMapping("/userLogin")
public class UserLoginController {
	@Autowired
	@Qualifier("userLoginService")
	private UserLoginService userLoginService;

	/**
	 * 查看玩家登陆活跃度
	 * 
	 * @param pageInfo
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toIndex")
	public String toIndex(PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		int registNumber = userLoginService.queryRegistNumber(pageInfo);
		userLoginService.queryUserLogin(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("registNumber", registNumber);
		return "/eapp/userLogin-index";
	}

	public UserLoginService getUserLoginService() {
		return userLoginService;
	}

	public void setUserLoginService(UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}

}
