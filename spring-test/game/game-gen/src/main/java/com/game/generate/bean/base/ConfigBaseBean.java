package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ConfigBaseBean.java
 * <p>
 * 功能：config.xls -> configBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ConfigBaseBean extends BaseBean implements IInitBean {
	/** 效果名稱 用戶端讀 */
	private String type = null;
	/** 數字 */
	private float num1 = 0.0f;
	/** 數字 */
	private String num2 = null;
	/** 描述 */
	private String describe = null;

	/** 效果名稱 用戶端讀 */
	public String getType() {
		return type;
	}

	/** 效果名稱 用戶端讀 */
	public void setType(String type) {
		this.type = type;
	}

	/** 數字 */
	public float getNum1() {
		return num1;
	}

	/** 數字 */
	public void setNum1(float num1) {
		this.num1 = num1;
	}

	/** 數字 */
	public String getNum2() {
		return num2;
	}

	/** 數字 */
	public void setNum2(String num2) {
		this.num2 = num2;
	}

	/** 描述 */
	public String getDescribe() {
		return describe;
	}

	/** 描述 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public void initBean(String[] keyArray, String[] data) {
		// id
		if (data[0] == null || "".equals(data[0].trim())) {
			this.id = 0;
		} else {
			this.id = Integer.parseInt(data[0]);
		}

		for (int i = 1; i < keyArray.length; i++) {
			String key = keyArray[i];
			String value = data[i];

			// id
			if (key.equals("id")) {
				if (value == null || "".equals(value.trim())) {
					this.id = 0;
				} else {
					this.id = Integer.parseInt(value);
				}
			}

			// 效果名稱 用戶端讀
			if (key.equals("type")) {
				this.type = value;
			}

			// 數字
			if (key.equals("num1")) {
				if (value == null || "".equals(value.trim())) {
					this.num1 = 0;
				} else {
					this.num1 = Float.parseFloat(value);
				}
			}

			// 數字
			if (key.equals("num2")) {
				this.num2 = value;
			}

			// 描述
			if (key.equals("describe")) {
				this.describe = value;
			}
		}
	}
}