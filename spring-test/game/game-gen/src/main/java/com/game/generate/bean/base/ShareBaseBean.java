package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ShareBaseBean.java
 * <p>
 * 功能：share.xls -> shareBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ShareBaseBean extends BaseBean implements IInitBean {
	/** 類型： 1、廣告 2、分享 */
	private int type = 0;
	/** 標題 最大6個字 */
	private String title = null;
	/** 描述 最大20個字 */
	private String desc = null;
	/** 每日次數上限 */
	private int limit = 0;

	/** 類型： 1、廣告 2、分享 */
	public int getType() {
		return type;
	}

	/** 類型： 1、廣告 2、分享 */
	public void setType(int type) {
		this.type = type;
	}

	/** 標題 最大6個字 */
	public String getTitle() {
		return title;
	}

	/** 標題 最大6個字 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 描述 最大20個字 */
	public String getDesc() {
		return desc;
	}

	/** 描述 最大20個字 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/** 每日次數上限 */
	public int getLimit() {
		return limit;
	}

	/** 每日次數上限 */
	public void setLimit(int limit) {
		this.limit = limit;
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

			// 類型： 1、廣告 2、分享
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 標題 最大6個字
			if (key.equals("title")) {
				this.title = value;
			}

			// 描述 最大20個字
			if (key.equals("desc")) {
				this.desc = value;
			}

			// 每日次數上限
			if (key.equals("limit")) {
				if (value == null || "".equals(value.trim())) {
					this.limit = 0;
				} else {
					this.limit = Integer.parseInt(value);
				}
			}
		}
	}
}