package com.myapp.tools.configbuilder.base;

public class FieldBean {
	// field英文名字
	private String enName = null;
	// field类型
	private String fieldType = null;
	// field中文名字
	private String znName = null;
	// 是否注释列
	private String isComment = null;

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getZnName() {
		return znName;
	}

	public void setZnName(String znName) {
		this.znName = znName;
	}

	public String getIsComment() {
		return isComment;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}
}
