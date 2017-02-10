package com.platform.admin.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class PropertiesCache {
	private static final Logger log = Logger.getLogger(PropertiesCache.class);
	private static String fileName = "linekong-config.xml";

	private static PropertiesCache instance = new PropertiesCache();

	private Map<String, String> cache;
	private FileAlterationMonitor monitor;
	private File configFile;

	private PropertiesCache() {
		this.cache = new HashMap<String, String>();
		addConfigFile();
		loadConfig();
	}

	public static PropertiesCache getInstance() {
		return instance;
	}

	/**
	 * 获取配置文件
	 * 
	 * @return
	 */
	private void addConfigFile() {
		//String configHome = System.getProperty("jboss.home.dir");// 该参数值由jboss自动加载
		String configHome = System.getProperty("catalina.home");// 该参数值由jboss自动加载
		if (null != configHome) {
			//configFile = new File(configHome + "/server/default/deploy/" + fileName);
			configFile = new File(configHome + "/conf/" + fileName);
		} else {
			configHome = System.getenv("CONFIG_HOME");// 避免依赖于JBOSS
			configFile = new File(configHome + File.separator + fileName);
		}
		if (!configFile.exists()) {
			log.error("config file[" + configFile.getAbsolutePath() + "] is not exist...");
			throw new RuntimeErrorException(new Error("config file[" + configFile.getAbsolutePath()
					+ "] is not exists ..."));
		}
		// 监控配置文件
		monitor();
	}

	/**
	 * 监控文件变化
	 */
	private void monitor() {
		int interval = getInt(Constant.PROJECT_CONFIG_NAME, "reloadConfigInterval", 0);// 监控文件间隔
		if (interval <= 0) {
			return;
		}
		monitor = new FileAlterationMonitor(TimeUnit.MINUTES.toMillis(interval));// 间隔
		FileAlterationListenerAdaptor listener = new FileAlterationListenerAdaptor() {
			/** 文件修改，则重新加载配置文件 */
			@Override
			public void onFileChange(File file) {
				loadConfig();
			}
		};
		NameFileFilter nameFilter = new NameFileFilter(fileName);
		FileAlterationObserver obs = new FileAlterationObserver(configFile.getParentFile(), nameFilter);
		obs.addListener(listener);
		monitor.addObserver(obs);
		try {
			monitor.start();
			log.info("start monitor config file[" + configFile.getAbsolutePath() + "], with interval = " + interval
					+ " minute.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载配置文件
	 */
	private void loadConfig() {
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			doc = sb.build(configFile);
		} catch (IOException e) {
			log.error(e.getMessage());
			return;
		} catch (JDOMException e) {
			log.error(e.getMessage());
			return;
		}
		Element root = doc.getRootElement();
		List<?> projects = root.getChildren("project");
		String projectName = "";
		String propertiesName = "";
		String value = "";
		Map<String, String> newCache = new HashMap<String, String>();
		for (int i = 0; i < projects.size(); i++) {
			Element project = (Element) projects.get(i);
			projectName = project.getAttributeValue("name");
			List<?> properties = project.getChildren("property");
			for (int j = 0; j < properties.size(); j++) {
				Element property = (Element) properties.get(j);
				propertiesName = property.getAttributeValue("name");
				value = property.getText();
				newCache.put(projectName + propertiesName, value);
			}
		}
		this.cache = newCache;
		log.info("load config file is successed...");
	}

	public String getProperties(String projectName, String propertiesName) {
		String key = projectName + propertiesName;
		String value = cache.get(key);
		if (null == value || "".equals(value.trim())) {
			return "";
		} else {
			return value.trim();
		}
	}

	public int getInt(String projectName, String propertiesName, int defaultValue) {
		int value = 0;
		String str = getProperties(projectName, propertiesName);
		if (null == str || "".equals(str)) {
			value = defaultValue;
		} else {
			try {
				value = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				value = defaultValue;
			}
		}
		return value;
	}

	public long getLong(String projectName, String propertiesName, long defaultValue) {
		long value = 0;
		String str = getProperties(projectName, propertiesName);
		if (null == str || "".equals(str)) {
			value = defaultValue;
		} else {
			try {
				value = Long.parseLong(str);
			} catch (NumberFormatException e) {
				value = defaultValue;
			}
		}
		return value;
	}

	public float getFloat(String projectName, String propertiesName, float defaultValue) {
		float value = 0;
		String str = getProperties(projectName, propertiesName);
		if (null == str || "".equals(str)) {
			value = defaultValue;
		} else {
			try {
				value = Float.parseFloat(str);
			} catch (NumberFormatException e) {
				value = defaultValue;
			}
		}
		return value;
	}

	public double getDouble(String projectName, String propertiesName, double defaultValue) {
		double value = 0;
		String str = getProperties(projectName, propertiesName);
		if (null == str || "".equals(str)) {
			value = defaultValue;
		} else {
			try {
				value = Double.parseDouble(str);
			} catch (NumberFormatException e) {
				value = defaultValue;
			}
		}
		return value;
	}

	public String getString(String projectName, String propertiesName, String defaultValue) {
		String value = getProperties(projectName, propertiesName);
		if (null == value || "".equals(value)) {
			value = defaultValue;
		}
		return value;
	}

	// 在JVM退出时调用，用于销毁内存以及定时器
	public void destory() {
		this.cache.clear();
		if (null != monitor) {
			try {
				monitor.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		//获取是项目的绝对路径
		System.getProperty("catalina.home");
	}
}