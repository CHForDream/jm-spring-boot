package com.myapp.tools.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.myapp.tools.util.Utils;

public class ToolsConfig {
	// 日志
	private static Logger log = Logger.getLogger(ToolsConfig.class);

	public static AppToolsConfigBean appToolsConfigBean = new AppToolsConfigBean();

	public static void init() {
		String configFile = Utils.getCurrentPath() + "config/AppTools.properties";
		Properties prop = new Properties();
		InputStream is = null;
		Reader re = null;
		try {
			is = new FileInputStream(configFile);
			re = new InputStreamReader(is, "UTF-8");
			prop.load(re);
			// 初始化bean
			ToolsConfig.initBean(prop);
		} catch (FileNotFoundException e) {
			log.error(String.format("Can't find File[%s]", configFile), e);
			System.exit(1);
		} catch (UnsupportedEncodingException e) {
			log.error(String.format("Unsupported Encoding - File[%s]", configFile), e);
			System.exit(1);
		} catch (IOException e) {
			log.error(String.format("Read error - File[%s]", configFile), e);
			System.exit(1);
		} finally {
			try {
				if (re != null) {
					re.close();
					re = null;
				}
			} catch (Exception e) {
			}

			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (Exception e) {
			}
		}
	}

	private static void initBean(Properties prop) {
		Method[] methods = AppToolsConfigBean.class.getMethods();

		// 整理方法和名字的对应
		Map<String, Method> methodMap = new HashMap<String, Method>();
		String methodName;
		for (Method method : methods) {
			methodName = method.getName();
			if (methodName.startsWith("set")) {
				// 除去set
				methodName = methodName.substring(3);
				methodMap.put(methodName, method);
			}
		}

		// 初始化参数
		Enumeration<Object> keys = prop.keys();
		String key;
		Method method;
		while (keys.hasMoreElements()) {
			key = (String) keys.nextElement();
			if (methodMap.containsKey(key)) {
				method = methodMap.get(key);
				try {
					method.invoke(appToolsConfigBean, prop.get(key));
				} catch (Exception e) {
					log.error(String.format("Failured to init key[%s]", key), e);
				}
			}
		}
	}
}
