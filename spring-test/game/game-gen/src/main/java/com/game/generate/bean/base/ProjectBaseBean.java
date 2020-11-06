package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ProjectBaseBean.java
 * <p>
 * 功能：project.xls -> projectBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ProjectBaseBean extends BaseBean implements IInitBean {
	/** 開啟類型 1：玩家等級 2：道具類型開啟 3：關卡開啟 4：解鎖水晶球 */
	private int openType = 0;
	/** 開放等級，數位為等級，類型id關卡填寫targetid */
	private int openValue = 0;
	/** 描述 */
	private String desc = null;
	/** 未開啟描述 */
	private String tips = null;

	/** 開啟類型 1：玩家等級 2：道具類型開啟 3：關卡開啟 4：解鎖水晶球 */
	public int getOpenType() {
		return openType;
	}

	/** 開啟類型 1：玩家等級 2：道具類型開啟 3：關卡開啟 4：解鎖水晶球 */
	public void setOpenType(int openType) {
		this.openType = openType;
	}

	/** 開放等級，數位為等級，類型id關卡填寫targetid */
	public int getOpenValue() {
		return openValue;
	}

	/** 開放等級，數位為等級，類型id關卡填寫targetid */
	public void setOpenValue(int openValue) {
		this.openValue = openValue;
	}

	/** 描述 */
	public String getDesc() {
		return desc;
	}

	/** 描述 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/** 未開啟描述 */
	public String getTips() {
		return tips;
	}

	/** 未開啟描述 */
	public void setTips(String tips) {
		this.tips = tips;
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

			// 開啟類型 1：玩家等級 2：道具類型開啟 3：關卡開啟 4：解鎖水晶球
			if (key.equals("openType")) {
				if (value == null || "".equals(value.trim())) {
					this.openType = 0;
				} else {
					this.openType = Integer.parseInt(value);
				}
			}

			// 開放等級，數位為等級，類型id關卡填寫targetid
			if (key.equals("openValue")) {
				if (value == null || "".equals(value.trim())) {
					this.openValue = 0;
				} else {
					this.openValue = Integer.parseInt(value);
				}
			}

			// 描述
			if (key.equals("desc")) {
				this.desc = value;
			}

			// 未開啟描述
			if (key.equals("tips")) {
				this.tips = value;
			}
		}
	}
}