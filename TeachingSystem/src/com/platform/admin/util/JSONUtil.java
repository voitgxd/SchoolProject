package com.platform.admin.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>
 * 封装JSONObject的工具类，由于JSONObject在获取属性值时key不存在会抛出异常
 * </p>
 * 
 * @author Vicky
 * 
 */
public class JSONUtil {
	private JSONObject jsonObject;

	public static JSONUtil fromObject(Object obj) {
		try {
			return new JSONUtil(JSONObject.fromObject(obj));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONUtil(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONUtil getJSONUtil(String key) {
		JSONObject obj = null;
		try {
			obj = jsonObject.getJSONObject(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == obj) {
			return null;
		} else {
			return JSONUtil.fromObject(obj);
		}
	}

	public String getString(String key) {
		Object value = null;
		value = get(key);
		if (null != value && value instanceof String) {
			return (String) value;
		} else {
			return "";
		}
	}

	public int getInt(String key) {
		Object value = null;
		value = get(key);
		if (null != value) {
			try {
				return Integer.parseInt((String) value);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return 0;
			}
		} else {
			return 0;
		}
	}

	public long getLong(String key) {
		Object value = null;
		value = get(key);
		if (null != value) {
			try {
				return Long.parseLong((String) value);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return 0;
			}
		} else {
			return 0;
		}
	}

	public Object get(String key) {
		if (!jsonObject.containsKey(key)) {
			return null;
		}
		return jsonObject.getString(key);
	}

	public JSONUtil element(String key, Object value) {
		jsonObject.element(key, value.toString());
		return this;
	}

	@SuppressWarnings("unchecked")
	public JSONUtil element(String key, Map value) {
		jsonObject.element(key, JSONObject.fromObject(value));
		return this;
	}

	public JSONArray getJSONArray(String key) {
		try {
			return jsonObject.getJSONArray(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONUtil discard(String key) {
		jsonObject.discard(key);
		return this;
	}

	@SuppressWarnings("unchecked")
	public Set keySet() {
		return jsonObject.keySet();
	}

	public JSONUtil mergeJSON(JSONUtil json) {
		for (Object k : json.keySet()) {
			jsonObject.element((String) k, json.get((String) k));
		}
		return this;
	}

	
	/** 
     * @param object 
     *             任意对象 
     * @return java.lang.String 
     */    
   public static String objectToJson(Object object) {     
        StringBuilder json = new StringBuilder();     
       if (object == null) {     
            json.append("\"\"");     
        } else if (object instanceof String || object instanceof Integer || object instanceof Long) {   
            json.append("\"").append(object.toString()).append("\"");    
        } else {     
            json.append(beanToJson(object));     
        }     
       return json.toString();     
    }     
   
   /** 
     * 功能描述:传入任意一个 javabean 对象生成一个指定规格的字符串 
     * 
     * @param bean 
     *             bean对象 
     * @return String 
     */    
   public static String beanToJson(Object bean) {     
        StringBuilder json = new StringBuilder();     
        json.append("{");     
        PropertyDescriptor[] props = null;     
       try {     
            props = Introspector.getBeanInfo(bean.getClass(), Object.class)     
                    .getPropertyDescriptors();     
        } catch (IntrospectionException e) {     
        }     
       if (props != null) {     
           for (int i = 0; i < props.length; i++) {     
               try {    
                    String name = objectToJson(props[i].getName());     
                    String value = objectToJson(props[i].getReadMethod().invoke(bean));    
                    json.append(name);     
                    json.append(":");     
                    json.append(value);     
                    json.append(",");    
                } catch (Exception e) {     
                }     
            }     
            json.setCharAt(json.length() - 1, '}');     
        } else {     
            json.append("}");     
        }     
       return json.toString();     
    }     
   
   /** 
     * 功能描述:通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串 
     * 
     * @param list 
     *             列表对象 
     * @return java.lang.String 
     */    
   public static String listToJson(List<?> list) {     
        StringBuilder json = new StringBuilder();     
        json.append("[");     
       if (list != null && list.size() > 0) {     
           for (Object obj : list) {     
                json.append(objectToJson(obj));     
                json.append(",");     
            }     
            json.setCharAt(json.length() - 1, ']');     
        } else {     
            json.append("]");     
        }     
       return json.toString();     
    } 
	
   @Override
public String toString() {
		return jsonObject.toString();
	}

	protected JSONObject getJSONObject() {
		return jsonObject;
	}
   
}
