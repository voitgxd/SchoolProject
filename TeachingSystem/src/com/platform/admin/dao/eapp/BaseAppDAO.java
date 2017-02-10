package com.platform.admin.dao.eapp;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseAppDAO implements ApplicationContextAware {

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
	
	public DataSource getDataSource2() {
		return (DataSource) applicationContext.getBean("dataSource2");
	}

	public DataSource getDataSource2(String name) {
		return (DataSource) applicationContext.getBean("dataSource2_" + name);
	}
	
	
	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate jdbcTemplate;

	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
