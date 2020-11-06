package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ActivityBaseBean.java
 * <p>
 * 功能：activity.xls -> activityBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ActivityBaseBean extends BaseBean implements IInitBean {
	/** 活動名字 */
	private String name = null;
	/** 活動描述 */
	private String desc = null;
	/** 邏輯類型 */
	private int logicType = 0;
	/** 時間類型 */
	private int timeType = 0;
	/** 重置類型 */
	private int resetType = 0;

	/** 活動名字 */
	public String getName() {
		return name;
	}

	/** 活動名字 */
	public void setName(String name) {
		this.name = name;
	}

	/** 活動描述 */
	public String getDesc() {
		return desc;
	}

	/** 活動描述 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/** 邏輯類型 */
	public int getLogicType() {
		return logicType;
	}

	/** 邏輯類型 */
	public void setLogicType(int logicType) {
		this.logicType = logicType;
	}

	/** 時間類型 */
	public int getTimeType() {
		return timeType;
	}

	/** 時間類型 */
	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	/** 重置類型 */
	public int getResetType() {
		return resetType;
	}

	/** 重置類型 */
	public void setResetType(int resetType) {
		this.resetType = resetType;
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

			// 活動名字
			if (key.equals("name")) {
				this.name = value;
			}

			// 活動描述
			if (key.equals("desc")) {
				this.desc = value;
			}

			// 邏輯類型
			if (key.equals("logicType")) {
				if (value == null || "".equals(value.trim())) {
					this.logicType = 0;
				} else {
					this.logicType = Integer.parseInt(value);
				}
			}

			// 時間類型
			if (key.equals("timeType")) {
				if (value == null || "".equals(value.trim())) {
					this.timeType = 0;
				} else {
					this.timeType = Integer.parseInt(value);
				}
			}

			// 重置類型
			if (key.equals("resetType")) {
				if (value == null || "".equals(value.trim())) {
					this.resetType = 0;
				} else {
					this.resetType = Integer.parseInt(value);
				}
			}
		}
	}
}