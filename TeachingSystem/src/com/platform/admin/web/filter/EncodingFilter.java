package com.platform.admin.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * <p>EncodingFilter.java</p>
 * @author      <a href="mailto:herui@linekong.com">herui</a>
 * @company     蓝港在线(北京)科技有限公司
 * @date        Apr 22, 2009 4:06:11 PM
 * @version		1.0
 * Copyright © 2007-2009 LINEKONG Inc. All Rights Reserved  	
 */
public class EncodingFilter implements Filter{
	
	public void destroy() {		
	}
	/*
	 * 	  1,doFilter方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括
	 *    表单数据、cookie和HTTP请求头）的完全访问。第二个参数为ServletResponse，通常在简单的过
	 *    滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或JSP页。
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		StringBuffer url = new StringBuffer();
		url.append("..").append(httpRequest.getServletPath());

//		httpResponse.setHeader("Cache-Control", "no-cache");
//		httpResponse.setHeader("Cache-Control", "no-store");
//		httpResponse.setDateHeader("Expires", 0);
//		httpResponse.setHeader("Pragma", "no-cache");
//		httpResponse.addHeader("P3P", "CP=CAO PSA OUR");
		filterChain.doFilter(httpServletRequest, httpResponse);
	}
	public void init(FilterConfig arg0) throws ServletException {		
	}

}
