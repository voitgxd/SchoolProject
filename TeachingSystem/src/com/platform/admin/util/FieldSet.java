package com.platform.admin.util;

import java.util.HashMap;
import java.util.Map;

public class FieldSet {
	
	public static final Map<String, String> MAP_FIELD = new HashMap<String, String>();
	static{
		MAP_FIELD.put(FieldSet.PASSPORT_ID,"用户名");	
		MAP_FIELD.put(FieldSet.FILE_URL, "图片地址");
		
	}

	public static final String PASSPORT_ID = "passportId";
	public static final String FILE_URL = "url";
	
    //例如将passportCount转换为getPassportCount
    public static String transformField2Get(String field){
        StringBuffer sb = new StringBuffer();
        sb.append("get").append(field.substring(0,1).toUpperCase()).append(field.substring(1));
        return sb.toString();
    }
}
