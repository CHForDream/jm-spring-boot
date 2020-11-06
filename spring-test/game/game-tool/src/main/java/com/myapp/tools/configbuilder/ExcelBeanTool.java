package com.myapp.tools.configbuilder;

import java.io.File;

import com.myapp.tools.bean.ToolsConfig;

public class ExcelBeanTool {
	public static boolean run(String arg) {
		String path = "";
		String caseStr = arg.toLowerCase();
		switch (caseStr) {
		case "app":// APP
			path = ToolsConfig.appToolsConfigBean.getBase_Path() + "/" + ToolsConfig.appToolsConfigBean.getConfig_Path_App();
			new ConfigParse().parsePath(new File(path), caseStr);
			return true;
		case "app_ps":// APP评审
			path = ToolsConfig.appToolsConfigBean.getBase_Path() + "/" + ToolsConfig.appToolsConfigBean.getConfig_Path_App_ps();
			new ConfigParse().parsePath(new File(path), caseStr);
			return true;
		case "h5":
			path = ToolsConfig.appToolsConfigBean.getBase_Path() + "/" + ToolsConfig.appToolsConfigBean.getConfig_Path_H5();
			new ConfigParse().parsePath(new File(path), caseStr);
			return true;
		default:
			break;
		}
		return false;
	}
}
