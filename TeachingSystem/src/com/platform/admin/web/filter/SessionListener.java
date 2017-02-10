package com.platform.admin.web.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * <p>
 * 监听Session的创建和销毁事件， 在Session销毁时清空用户打包记录
 * </p>
 * 
 * @author Vicky
 * 
 */
public class SessionListener implements HttpSessionListener {
	private static final Logger log = Logger.getLogger(SessionListener.class);

	public void sessionCreated(HttpSessionEvent eve) {

	}

	public void sessionDestroyed(HttpSessionEvent eve) {
		log.info("session destroyed...");
	}

}
