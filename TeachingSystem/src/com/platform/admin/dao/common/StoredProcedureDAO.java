package com.platform.admin.dao.common;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.object.StoredProcedure;

public class StoredProcedureDAO extends StoredProcedure{
	
	private Map<String ,Object> InParameters = new HashMap<String, Object>();
    
		
	public StoredProcedureDAO() {
		super();		
	}

	public StoredProcedureDAO(DataSource dataSource,String name){
		super(dataSource,name);
	}
	
	public StoredProcedureDAO(DataSource dataSource,String name,boolean isFunction){
		super(dataSource,name);
		super.setFunction(isFunction);
	}
	
	
	//添加输入参数	 
	public void addParameter(String parameterName,int Types, Object value){
		super.declareParameter(new SqlParameter(parameterName,Types));
		InParameters.put(parameterName, value);
	}
	
	//添加输出参数
	public void addOutParameter(String parameterName, int Types){
		super.declareParameter(new SqlOutParameter(parameterName,Types));
	}

	//添加返回游标	
	public <T>void addOutCursorParameter(String parameterName,Class<T> mappedClass){
		super.declareParameter(new SqlOutParameter(parameterName,OracleTypes.CURSOR,ParameterizedBeanPropertyRowMapper.newInstance(mappedClass)));
	}
	
	//返回结果集
	public <T>void setReturnParam(String param, Class<T> mappedClass) {  
		super.declareParameter(new SqlReturnResultSet(param,ParameterizedBeanPropertyRowMapper.newInstance(mappedClass)));  
    }  
	
	//执行	
	public Map<String,Object> execute(){
		compile();
		return super.execute(InParameters);
	}
	
	
}
