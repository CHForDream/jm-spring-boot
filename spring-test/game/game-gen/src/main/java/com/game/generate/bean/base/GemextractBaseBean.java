package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：GemextractBaseBean.java
 * <p>
 * 功能：gemextract.xls -> gemextractBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class GemextractBaseBean extends BaseBean implements IInitBean {
	/** 庫ID（1：普通庫 2：高級庫 3.單抽必掉庫 4.double必掉） */
	private int group = 0;
	/** 掉落類型 3、道具 4、禮包 23、寶石 */
	private int itemType = 0;
	/** 道具ID */
	private int itemId = 0;
	/** 數量 */
	private int num = 0;
	/** 權重 */
	private int rate = 0;

	/** 庫ID（1：普通庫 2：高級庫 3.單抽必掉庫 4.double必掉） */
	public int getGroup() {
		return group;
	}

	/** 庫ID（1：普通庫 2：高級庫 3.單抽必掉庫 4.double必掉） */
	public void setGroup(int group) {
		this.group = group;
	}

	/** 掉落類型 3、道具 4、禮包 23、寶石 */
	public int getItemType() {
		return itemType;
	}

	/** 掉落類型 3、道具 4、禮包 23、寶石 */
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	/** 道具ID */
	public int getItemId() {
		return itemId;
	}

	/** 道具ID */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/** 數量 */
	public int getNum() {
		return num;
	}

	/** 數量 */
	public void setNum(int num) {
		this.num = num;
	}

	/** 權重 */
	public int getRate() {
		return rate;
	}

	/** 權重 */
	public void setRate(int rate) {
		this.rate = rate;
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

			// 庫ID（1：普通庫 2：高級庫 3.單抽必掉庫 4.double必掉）
			if (key.equals("group")) {
				if (value == null || "".equals(value.trim())) {
					this.group = 0;
				} else {
					this.group = Integer.parseInt(value);
				}
			}

			// 掉落類型 3、道具 4、禮包 23、寶石
			if (key.equals("itemType")) {
				if (value == null || "".equals(value.trim())) {
					this.itemType = 0;
				} else {
					this.itemType = Integer.parseInt(value);
				}
			}

			// 道具ID
			if (key.equals("itemId")) {
				if (value == null || "".equals(value.trim())) {
					this.itemId = 0;
				} else {
					this.itemId = Integer.parseInt(value);
				}
			}

			// 數量
			if (key.equals("num")) {
				if (value == null || "".equals(value.trim())) {
					this.num = 0;
				} else {
					this.num = Integer.parseInt(value);
				}
			}

			// 權重
			if (key.equals("rate")) {
				if (value == null || "".equals(value.trim())) {
					this.rate = 0;
				} else {
					this.rate = Integer.parseInt(value);
				}
			}
		}
	}
}