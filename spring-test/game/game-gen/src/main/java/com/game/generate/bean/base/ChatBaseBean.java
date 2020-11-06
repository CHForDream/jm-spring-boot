package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ChatBaseBean.java
 * <p>
 * 功能：chat.xls -> chatBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ChatBaseBean extends BaseBean implements IInitBean {
	/** 快捷顯示分類 */
	private String key = null;
	/** 快捷語 */
	private String value = null;
	/** 參數 */
	private int param = 0;
	/** 音效類型 */
	private String soundType = null;

	/** 快捷顯示分類 */
	public String getKey() {
		return key;
	}

	/** 快捷顯示分類 */
	public void setKey(String key) {
		this.key = key;
	}

	/** 快捷語 */
	public String getValue() {
		return value;
	}

	/** 快捷語 */
	public void setValue(String value) {
		this.value = value;
	}

	/** 參數 */
	public int getParam() {
		return param;
	}

	/** 參數 */
	public void setParam(int param) {
		this.param = param;
	}

	/** 音效類型 */
	public String getSoundType() {
		return soundType;
	}

	/** 音效類型 */
	public void setSoundType(String soundType) {
		this.soundType = soundType;
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

			// 快捷顯示分類
			if (key.equals("key")) {
				this.key = value;
			}

			// 快捷語
			if (key.equals("value")) {
				this.value = value;
			}

			// 參數
			if (key.equals("param")) {
				if (value == null || "".equals(value.trim())) {
					this.param = 0;
				} else {
					this.param = Integer.parseInt(value);
				}
			}

			// 音效類型
			if (key.equals("soundType")) {
				this.soundType = value;
			}
		}
	}
}