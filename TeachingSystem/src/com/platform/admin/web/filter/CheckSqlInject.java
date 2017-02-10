package com.platform.admin.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @Title: CheckSqlInject.java
 * @Desc: 过滤特殊字符
 * @Project: ereport2.0
 * @Date: 2013-12-02 2:12pm
 * @Author: yqy Copyright © 2007-2014 LINEKONG Inc. All Rights Reserved
 */
public class CheckSqlInject implements Filter {

	protected FilterConfig filterConfig = null;
	protected String tmp = null;
	private final static Logger log = org.apache.log4j.Logger.getLogger(CheckSqlInject.class);

	public void destroy() {
		this.filterConfig = null;
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest myrequest = (HttpServletRequest) request;
		java.util.Enumeration enu = myrequest.getParameterNames();

		try {
			String sql = "";
			// 对所有参数进行循环
			while (enu.hasMoreElements()) {
				// 得到参数名
				String name = (String) enu.nextElement();
				// 得到这个参数的所有值
				String[] value = myrequest.getParameterValues(name);
				for (int i = 0; i < value.length; i++) {
					sql = sql + value[i];
				}
			}
			String inj_stra[] = this.tmp.split("\\|");

			for (int i = 0, len = inj_stra.length; i < len; i++) {
				if (sql.indexOf(inj_stra[i]) >= 0) {
					log.error("非法的参数：" + sql);
					myrequest.getRequestDispatcher("/illegal.jsp").forward(request, response);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// 非法的字符
		this.tmp = "'|--|/*|*/";
	}

}
