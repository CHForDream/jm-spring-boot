package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：FirstnameBaseBean.java
 * <p>
 * 功能：firstname.xls -> firstnameBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class FirstnameBaseBean extends BaseBean implements IInitBean {
	/** 名字1 */
	private String fistName = null;
	/** 名字2 */
	private String secondName = null;
	/** 頭像 */
	private String head = null;

	/** 名字1 */
	public String getFistName() {
		return fistName;
	}

	/** 名字1 */
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	/** 名字2 */
	public String getSecondName() {
		return secondName;
	}

	/** 名字2 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/** 頭像 */
	public String getHead() {
		return head;
	}

	/** 頭像 */
	public void setHead(String head) {
		this.head = head;
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

			// 名字1
			if (key.equals("fistName")) {
				this.fistName = value;
			}

			// 名字2
			if (key.equals("secondName")) {
				this.secondName = value;
			}

			// 頭像
			if (key.equals("head")) {
				this.head = value;
			}
		}
	}
}