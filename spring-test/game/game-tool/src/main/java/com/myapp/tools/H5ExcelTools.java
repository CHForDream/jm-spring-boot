package com.myapp.tools;

import com.myapp.tools.bean.ToolsConfig;
import com.myapp.tools.configbuilder.ExcelBeanTool;

public class H5ExcelTools {
	public static void main(String[] args) {
		// 初始化配置
		ToolsConfig.init();

		ExcelBeanTool.run("h5");
	}
}
