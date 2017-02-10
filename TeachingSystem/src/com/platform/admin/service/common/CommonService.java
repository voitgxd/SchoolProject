package com.platform.admin.service.common;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.admin.pojo.common.RolePOJO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.ResourcePOJO;
import com.platform.admin.util.Constant;

@Service
@Transactional
public class CommonService {
	private static  Log logger=LogFactory.getLog(CommonService.class);
	
	@Resource
	private PurviewService purviewService;

	public void refreshSession(HttpSession session) throws Exception {
		
		Integer sysFlag=(Integer) session.getAttribute("sysFlag");
		logger.info("logger sysFlag="+sysFlag);
		if(sysFlag==null){
			sysFlag=1;
		}
		UserPOJO userPojo = (UserPOJO)session.getAttribute(Constant.SESSION_USER);
		if (userPojo==null) {
			return;
		}
		
		
		List<ResourcePOJO> resourceList=purviewService.getResources(userPojo.getRoleId(),sysFlag);
		
		List<RolePOJO> roleList=purviewService.getAllRoles();
		//获取二级菜单
		session.setAttribute(Constant.SESSION_ROLE_RESOURCE_BEAN_LIST, resourceList);
		
		//获取系统角色
		session.setAttribute(Constant.SESSION_ROLE_LIST, roleList);
		

	}
}
