package com.platform.admin.dao.common;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDAO implements ApplicationContextAware {

	@Autowired
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public DataSource getDataSource() {
		return (DataSource) applicationContext.getBean("dataSource");
	}
	
	public DataSource getDataSource(String name) {
		return (DataSource) applicationContext.getBean("dataSource_" + name);
	}
	
	public DataSource getDataSource_eadmin() {
		return (DataSource) applicationContext.getBean("dataSource2");
	}
	
	public DataSource getDataSource_ewebsite() {
		return (DataSource) applicationContext.getBean("dataSource3");
	}

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
