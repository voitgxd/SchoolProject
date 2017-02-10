package com.platform.admin.web.filter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.platform.admin.dao.common.PurviewDAO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.ResourcePOJO;
import com.platform.admin.service.common.CommonService;
import com.platform.admin.util.Constant;
import com.platform.admin.web.controller.common.AccountController;

public class CommonInterceptor implements HandlerInterceptor {
	private Logger logger = Logger.getLogger(CommonInterceptor.class);
	@SuppressWarnings("unused")
	@Resource
	private CommonService commonService;
	@SuppressWarnings("unused")
	@Resource
	private AccountController accountController;
	@SuppressWarnings("unused")
	@Resource
	private PurviewDAO purviewDAO;

	@SuppressWarnings("unused")
	private ArrayList<String> guestUrlList = new ArrayList<String>(); // 不需要权限验证的URL

	public CommonInterceptor() {
		logger.info("拦截器初始化.....");
		/*guestUrlList.add("/login"); // 登录
		guestUrlList.add("/home"); // 退出
		guestUrlList.add("/product");
		guestUrlList.add("/home/getProduct");*/
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		return;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		logger.info("postHandle...begin");

	}

	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		boolean flag = false;
		HttpSession httpSession = request.getSession();
		String servletPath = request.getServletPath();
		logger.info("servletPath:" + servletPath);
		

		//检测用户session是否为空，空则返回登陆页
		UserPOJO userPojo = (UserPOJO) httpSession.getAttribute(Constant.SESSION_USER);
			if (userPojo == null){
				response.sendRedirect(request.getContextPath()+"/login");
				return flag;
		}
		
		//拦截用户权限
		//获取该用户拥有权限的resources，如果为空返回登陆页
		List<ResourcePOJO>  resourceList = (List<ResourcePOJO>) httpSession.getAttribute(Constant.SESSION_ROLE_RESOURCE_BEAN_LIST);
		if (resourceList == null){
		    response.sendRedirect(request.getContextPath()+"/login");
		    return flag;
		}
		
		//查看拦截地址是否为模块地址
		List<ResourcePOJO>  allResourceList = (List<ResourcePOJO>) httpSession.getAttribute(Constant.SESSION_ALL_RESOURCE_BEAN_LIST);
		for (int i=0;i<allResourceList.size();i++){
			 //如果不是模块地址查找下一个
			 if((allResourceList.get(i).getResUrl().equals("")||!("/" + allResourceList.get(i).getResUrl()).equals(servletPath)))
				 continue;	 
			 else{
		        //循环查看角色是否拥有权限
		        for (ResourcePOJO resourcePOJO : resourceList) { 
			        if (servletPath.equals("/" + resourcePOJO.getResUrl())) {
				        flag = true;
				        break;
		            }
		        }
		        //角色没有权限，返回登陆页面
				if (flag == false){
				response.sendRedirect(request.getContextPath()+"/login");
				}
				return flag; 
		     }
		}
		flag = true;
		return flag;
	}

}
