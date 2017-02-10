package com.platform.admin.web.tld;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.platform.admin.pojo.site.ResourcePOJO;
import com.platform.admin.util.Constant;

public class MenuResourceTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private List<ResourcePOJO> roleResourceBeanList;
	private StringBuffer sb;

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			sb = new StringBuffer();
			resource1Tree(roleResourceBeanList);
			out.println(sb);
			sb = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	//获取主菜单
	private void resource1Tree(List<ResourcePOJO> resourceBeanList) throws IOException {
		if(resourceBeanList!=null&&resourceBeanList.size()>0){
			ResourcePOJO resourceBean = new ResourcePOJO();
			for (int i=0;i<resourceBeanList.size();i++){
				    resourceBean = resourceBeanList.get(i); 
				    if (resourceBean.getResType() == Constant.RES_TYPE_MAIN) { 
							sb.append("<li class=\"parent\"><a href=\"#\" id=\"menuMain_" + resourceBean.getResId() + "\" >");
							sb.append("<i class=\"fa ");
							sb.append(null == resourceBean.getResIcon() ? "" : resourceBean.getResIcon());
							sb.append(" \"></i><span>").append(resourceBean.getResName());
							sb.append("</span></a>");
							sb.append("<ul class=\"children\">");
							resource2Tree(resourceBeanList, resourceBean.getResId());
							sb.append("</ul></li>");
					}
			}
		}
	}
	
	//获取次菜单
	private void resource2Tree(List<ResourcePOJO> resourceBeanList,int currentResId) throws IOException {
		if(resourceBeanList!=null&&resourceBeanList.size()>0){	
			ResourcePOJO resourceBean = new ResourcePOJO();
		    for (int i=0;i<resourceBeanList.size();i++){
		    	resourceBean = resourceBeanList.get(i);
		    	if (resourceBean.getResPid()==currentResId){
		    	sb.append("<li class> <a href=\"").append(resourceBean.getResUrl()).append("\" target=\"mainFrame\" ");
		    	sb.append(" id=\"menuSub_").append(resourceBean.getResId()).append("\"").append(">");
		    	sb.append("<i class=\"fa fa-caret-right\"></i>&nbsp;&nbsp;").append(resourceBean.getResName());
		    	sb.append("</a></li>");
		    	}
		}
		}
		
	}

	public List<ResourcePOJO> getRoleResourceBeanList() {
		return roleResourceBeanList;
	}

	public void setRoleResourceBeanList(List<ResourcePOJO> roleResourceBeanList) {
		this.roleResourceBeanList = roleResourceBeanList;
	}

}
