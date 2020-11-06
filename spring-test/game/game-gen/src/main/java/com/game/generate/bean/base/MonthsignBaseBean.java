package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：MonthsignBaseBean.java
 * <p>
 * 功能：monthsign.xls -> monthsignBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class MonthsignBaseBean extends BaseBean implements IInitBean {
	/** 道具類型 3：道具 4：禮包 10：英雄 20：裝飾 23.寶石 */
	private int itemtype = 0;
	/** 道具ID */
	private int item = 0;
	/** 獎勵數量 */
	private int num = 0;
	/** 月卡獎勵倍數 */
	private int multiplier = 0;
	/** 補簽價格（類型，id，數量） */
	private String retroactive = null;

	/** 道具類型 3：道具 4：禮包 10：英雄 20：裝飾 23.寶石 */
	public int getItemtype() {
		return itemtype;
	}

	/** 道具類型 3：道具 4：禮包 10：英雄 20：裝飾 23.寶石 */
	public void setItemtype(int itemtype) {
		this.itemtype = itemtype;
	}

	/** 道具ID */
	public int getItem() {
		return item;
	}

	/** 道具ID */
	public void setItem(int item) {
		this.item = item;
	}

	/** 獎勵數量 */
	public int getNum() {
		return num;
	}

	/** 獎勵數量 */
	public void setNum(int num) {
		this.num = num;
	}

	/** 月卡獎勵倍數 */
	public int getMultiplier() {
		return multiplier;
	}

	/** 月卡獎勵倍數 */
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	/** 補簽價格（類型，id，數量） */
	public String getRetroactive() {
		return retroactive;
	}

	/** 補簽價格（類型，id，數量） */
	public void setRetroactive(String retroactive) {
		this.retroactive = retroactive;
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

			// 道具類型 3：道具 4：禮包 10：英雄 20：裝飾 23.寶石
			if (key.equals("itemtype")) {
				if (value == null || "".equals(value.trim())) {
					this.itemtype = 0;
				} else {
					this.itemtype = Integer.parseInt(value);
				}
			}

			// 道具ID
			if (key.equals("item")) {
				if (value == null || "".equals(value.trim())) {
					this.item = 0;
				} else {
					this.item = Integer.parseInt(value);
				}
			}

			// 獎勵數量
			if (key.equals("num")) {
				if (value == null || "".equals(value.trim())) {
					this.num = 0;
				} else {
					this.num = Integer.parseInt(value);
				}
			}

			// 月卡獎勵倍數
			if (key.equals("multiplier")) {
				if (value == null || "".equals(value.trim())) {
					this.multiplier = 0;
				} else {
					this.multiplier = Integer.parseInt(value);
				}
			}

			// 補簽價格（類型，id，數量）
			if (key.equals("retroactive")) {
				this.retroactive = value;
			}
		}
	}
}