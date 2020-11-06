package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：SignBaseBean.java
 * <p>
 * 功能：sign.xls -> signBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class SignBaseBean extends BaseBean implements IInitBean {
	/** 道具類型 3：道具 4：禮包 10：英雄 20：裝飾 23.寶石 */
	private int itemtype = 0;
	/** 道具ID */
	private int item = 0;
	/** 獎勵數量 */
	private int num = 0;

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
		}
	}
}