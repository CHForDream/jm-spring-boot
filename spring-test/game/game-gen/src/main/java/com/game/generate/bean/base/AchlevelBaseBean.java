package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：AchlevelBaseBean.java
 * <p>
 * 功能：achlevel.xls -> achlevelBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class AchlevelBaseBean extends BaseBean implements IInitBean {
	/** 寶箱圖示 */
	private String Icon = null;
	/** 成就等級 */
	private int level = 0;
	/** 獎勵類型，獎勵ID，獎勵數量；獎勵類型，獎勵ID，獎勵數量；…… */
	private String item = null;

	/** 寶箱圖示 */
	public String getIcon() {
		return Icon;
	}

	/** 寶箱圖示 */
	public void setIcon(String Icon) {
		this.Icon = Icon;
	}

	/** 成就等級 */
	public int getLevel() {
		return level;
	}

	/** 成就等級 */
	public void setLevel(int level) {
		this.level = level;
	}

	/** 獎勵類型，獎勵ID，獎勵數量；獎勵類型，獎勵ID，獎勵數量；…… */
	public String getItem() {
		return item;
	}

	/** 獎勵類型，獎勵ID，獎勵數量；獎勵類型，獎勵ID，獎勵數量；…… */
	public void setItem(String item) {
		this.item = item;
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

			// 寶箱圖示
			if (key.equals("Icon")) {
				this.Icon = value;
			}

			// 成就等級
			if (key.equals("level")) {
				if (value == null || "".equals(value.trim())) {
					this.level = 0;
				} else {
					this.level = Integer.parseInt(value);
				}
			}

			// 獎勵類型，獎勵ID，獎勵數量；獎勵類型，獎勵ID，獎勵數量；……
			if (key.equals("item")) {
				this.item = value;
			}
		}
	}
}