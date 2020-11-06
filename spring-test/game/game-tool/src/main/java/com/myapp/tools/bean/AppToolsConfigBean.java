package com.myapp.tools.bean;

public class AppToolsConfigBean {
	// 基础配置信息
	private String base_Path = null;

	// --------------- Config Builder ---------------
	// 配置文件路径
	private String config_Path_App = null;
	private String config_Path_App_ps = null;
	private String config_Path_H5 = null;

	// 配置文件服务器端路径
	private String config_BaseBeanPackage = null;
	private String config_BeanPackage = null;

	// 配置文件服务器端模板
	private String config_BaseBeanTemplate = null;
	// 配置文件服务器端模板
	private String config_BeanTemplate = null;

	public String getBase_Path() {
		return base_Path;
	}

	public void setBase_Path(String base_Path) {
		this.base_Path = base_Path;
	}

	public String getConfig_Path_App() {
		return config_Path_App;
	}

	public void setConfig_Path_App(String config_Path_App) {
		this.config_Path_App = config_Path_App;
	}

	public String getConfig_Path_App_ps() {
		return config_Path_App_ps;
	}

	public void setConfig_Path_App_ps(String config_Path_App_ps) {
		this.config_Path_App_ps = config_Path_App_ps;
	}

	public String getConfig_Path_H5() {
		return config_Path_H5;
	}

	public void setConfig_Path_H5(String config_Path_H5) {
		this.config_Path_H5 = config_Path_H5;
	}

	public String getConfig_BaseBeanPackage() {
		return config_BaseBeanPackage;
	}

	public void setConfig_BaseBeanPackage(String config_BaseBeanPackage) {
		this.config_BaseBeanPackage = config_BaseBeanPackage;
	}

	public String getConfig_BeanPackage() {
		return config_BeanPackage;
	}

	public void setConfig_BeanPackage(String config_BeanPackage) {
		this.config_BeanPackage = config_BeanPackage;
	}

	public String getConfig_BaseBeanTemplate() {
		return config_BaseBeanTemplate;
	}

	public void setConfig_BaseBeanTemplate(String config_BaseBeanTemplate) {
		this.config_BaseBeanTemplate = config_BaseBeanTemplate;
	}

	public String getConfig_BeanTemplate() {
		return config_BeanTemplate;
	}

	public void setConfig_BeanTemplate(String config_BeanTemplate) {
		this.config_BeanTemplate = config_BeanTemplate;
	}
}
