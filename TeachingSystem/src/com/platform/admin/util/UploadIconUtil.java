/**
 * 
 */
package com.platform.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.codec.binary.Base64;

/**
 * @author gxd
 * 
 *         2015-3-4
 */
public class UploadIconUtil {
	/** 上传图片 */
	@SuppressWarnings("unchecked")
	public static String uploadImage(String serverDir, String f_path) {
		XmlRpcUtil xml = new XmlRpcUtil("http://img.linekong.com/storage.php", 100);
		//新增
		File file = new File(serverDir);
		FileInputStream finps;
		String da;
		byte[] data = null;
		try {
			finps = new FileInputStream(file);
			data = new byte[finps.available()];
			finps.read(data);
			finps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		da = new String(Base64.encodeBase64(data));
		Vector paramsAdd = new Vector();
		paramsAdd.add(f_path);// 图片存储在图片服务器的根路径
		paramsAdd.add(String.valueOf(System.currentTimeMillis() / 1000));// 图片ID
		paramsAdd.add(da);// Base64编码图片数据
		paramsAdd.add(serverDir.substring(serverDir.lastIndexOf('.')));// 文件类型.gif/.jpg/.png/.swf
		Object result = xml.invoke("insert", paramsAdd);
		return result.toString();
	}
	/** 删除图片 */
	@SuppressWarnings("unchecked")
	public static Integer deleteImage(String iconDir) {
		XmlRpcUtil xml = new XmlRpcUtil("http://img.linekong.com/storage.php", 100);
		//删除
		Vector paramsAdd = new Vector();
		paramsAdd.add(iconDir);// 图片存储在图片服务器图片路径
		Integer result = xml.invoke("delete", paramsAdd);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(UploadIconUtil.uploadImage("e:\\app_icon.png", "wan"));
	}
}
