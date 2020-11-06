package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ActivenessBaseBean.java
 * <p>
 * 功能：activeness.xls -> activenessBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ActivenessBaseBean extends BaseBean implements IInitBean {
	/** 活躍度需求額度 */
	private int need = 0;
	/** 寶箱id */
	private int giftId = 0;
	/** 寶箱描述 */
	private String activeDesc = null;

	/** 活躍度需求額度 */
	public int getNeed() {
		return need;
	}

	/** 活躍度需求額度 */
	public void setNeed(int need) {
		this.need = need;
	}

	/** 寶箱id */
	public int getGiftId() {
		return giftId;
	}

	/** 寶箱id */
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	/** 寶箱描述 */
	public String getActiveDesc() {
		return activeDesc;
	}

	/** 寶箱描述 */
	public void setActiveDesc(String activeDesc) {
		this.activeDesc = activeDesc;
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

			// 活躍度需求額度
			if (key.equals("need")) {
				if (value == null || "".equals(value.trim())) {
					this.need = 0;
				} else {
					this.need = Integer.parseInt(value);
				}
			}

			// 寶箱id
			if (key.equals("giftId")) {
				if (value == null || "".equals(value.trim())) {
					this.giftId = 0;
				} else {
					this.giftId = Integer.parseInt(value);
				}
			}

			// 寶箱描述
			if (key.equals("activeDesc")) {
				this.activeDesc = value;
			}
		}
	}
}