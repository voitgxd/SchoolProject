package com.platform.admin.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.platform.admin.pojo.site.AccountPOJO;
import com.platform.admin.util.Constant;

@Controller
public class AdminControll {

	/**
	 * 首页
	 * 
	 * @return
	 */
	public String index(HttpServletRequest request, HttpServletResponse response) {
		AccountPOJO account = (AccountPOJO) request.getAttribute(Constant.ACCOUNT_INFO_KEY);
		if (null == account) {
			return "/error";
		}
		// 加载资源
		
		return "/admin/index";
	}
}
