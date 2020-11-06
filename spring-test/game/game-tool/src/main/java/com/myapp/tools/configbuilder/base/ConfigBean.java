package com.myapp.tools.configbuilder.base;

import java.util.List;

public class ConfigBean {
	// excel名
	private String fileName = null;

	// sheet 名
	private String sheetName = null;

	// 列bean
	private List<FieldBean> fields = null;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<FieldBean> getFields() {
		return fields;
	}

	public void setFields(List<FieldBean> fields) {
		this.fields = fields;
	}

}
