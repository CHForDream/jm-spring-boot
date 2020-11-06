package com.myapp.tools;

import com.myapp.tools.bean.ToolsConfig;
import com.myapp.tools.configbuilder.ExcelBeanTool;

public class AppPingshenExcelTool {
	public static void main(String[] args) {
		// 初始化配置
		ToolsConfig.init();

		ExcelBeanTool.run("app_ps");
	}
}
