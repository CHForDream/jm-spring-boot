package com.myapp.tools.util;

public class Utils {

	/**
	 * 获取当前目录
	 * 
	 * @return 当前目录
	 */
	public static String getCurrentPath() {
		String path;
		if (Utils.class.getResource("/") != null) {
			path = Utils.class.getResource("/").getPath();
			if (path.startsWith("/")
					&& System.getProperty("os.name").toLowerCase()
							.startsWith("windows")) {
				path = path.substring(1);
			}
		} else {
			path = Utils.class.getResource("").getPath();
			if (path.startsWith("file:")) {
				// 剪去前面的file:/
				int end = path.indexOf("jar!");
				path = path.substring(0, end);
				// 减去jar!后面的字符串
				end = path.lastIndexOf("/") + 1;
				path = path.substring("file:".length(), end);
			}
		}
		return path;
	}

}
