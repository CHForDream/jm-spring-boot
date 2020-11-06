package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：GemluckBaseBean.java
 * <p>
 * 功能：gemluck.xls -> gemluckBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class GemluckBaseBean extends BaseBean implements IInitBean {
	/** 名稱 */
	private String dec = null;
	/** 分類1為1抽，5為五抽 */
	private int type = 0;
	/** 貨幣類型道具ID 道具類型，id，數量，折扣（乘以係數）； */
	private String price = null;
	/** 是否免費1免費2不免費 */
	private int isfree = 0;
	/** 免費刷新時間（毫秒） */
	private int isfreshtime = 0;
	/** 迴圈次數 */
	private int raund = 0;
	/** 迴圈取的個數 */
	private String raundnum = null;
	/** 增加幸運值 */
	private int luck = 0;
	/** 幸運值落到高級庫概率除100 */
	private int luckaddnum = 0;

	/** 名稱 */
	public String getDec() {
		return dec;
	}

	/** 名稱 */
	public void setDec(String dec) {
		this.dec = dec;
	}

	/** 分類1為1抽，5為五抽 */
	public int getType() {
		return type;
	}

	/** 分類1為1抽，5為五抽 */
	public void setType(int type) {
		this.type = type;
	}

	/** 貨幣類型道具ID 道具類型，id，數量，折扣（乘以係數）； */
	public String getPrice() {
		return price;
	}

	/** 貨幣類型道具ID 道具類型，id，數量，折扣（乘以係數）； */
	public void setPrice(String price) {
		this.price = price;
	}

	/** 是否免費1免費2不免費 */
	public int getIsfree() {
		return isfree;
	}

	/** 是否免費1免費2不免費 */
	public void setIsfree(int isfree) {
		this.isfree = isfree;
	}

	/** 免費刷新時間（毫秒） */
	public int getIsfreshtime() {
		return isfreshtime;
	}

	/** 免費刷新時間（毫秒） */
	public void setIsfreshtime(int isfreshtime) {
		this.isfreshtime = isfreshtime;
	}

	/** 迴圈次數 */
	public int getRaund() {
		return raund;
	}

	/** 迴圈次數 */
	public void setRaund(int raund) {
		this.raund = raund;
	}

	/** 迴圈取的個數 */
	public String getRaundnum() {
		return raundnum;
	}

	/** 迴圈取的個數 */
	public void setRaundnum(String raundnum) {
		this.raundnum = raundnum;
	}

	/** 增加幸運值 */
	public int getLuck() {
		return luck;
	}

	/** 增加幸運值 */
	public void setLuck(int luck) {
		this.luck = luck;
	}

	/** 幸運值落到高級庫概率除100 */
	public int getLuckaddnum() {
		return luckaddnum;
	}

	/** 幸運值落到高級庫概率除100 */
	public void setLuckaddnum(int luckaddnum) {
		this.luckaddnum = luckaddnum;
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

			// 名稱
			if (key.equals("dec")) {
				this.dec = value;
			}

			// 分類1為1抽，5為五抽
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 貨幣類型道具ID 道具類型，id，數量，折扣（乘以係數）；
			if (key.equals("price")) {
				this.price = value;
			}

			// 是否免費1免費2不免費
			if (key.equals("isfree")) {
				if (value == null || "".equals(value.trim())) {
					this.isfree = 0;
				} else {
					this.isfree = Integer.parseInt(value);
				}
			}

			// 免費刷新時間（毫秒）
			if (key.equals("isfreshtime")) {
				if (value == null || "".equals(value.trim())) {
					this.isfreshtime = 0;
				} else {
					this.isfreshtime = Integer.parseInt(value);
				}
			}

			// 迴圈次數
			if (key.equals("raund")) {
				if (value == null || "".equals(value.trim())) {
					this.raund = 0;
				} else {
					this.raund = Integer.parseInt(value);
				}
			}

			// 迴圈取的個數
			if (key.equals("raundnum")) {
				this.raundnum = value;
			}

			// 增加幸運值
			if (key.equals("luck")) {
				if (value == null || "".equals(value.trim())) {
					this.luck = 0;
				} else {
					this.luck = Integer.parseInt(value);
				}
			}

			// 幸運值落到高級庫概率除100
			if (key.equals("luckaddnum")) {
				if (value == null || "".equals(value.trim())) {
					this.luckaddnum = 0;
				} else {
					this.luckaddnum = Integer.parseInt(value);
				}
			}
		}
	}
}