package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：FirstpayBaseBean.java
 * <p>
 * 功能：firstpay.xls -> firstpayBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class FirstpayBaseBean extends BaseBean implements IInitBean {
	/** 說明 */
	private String dsc = null;
	/** 主介面圖示 */
	private String icon = null;
	/** 介面圖片 */
	private String pic = null;
	/** 需求值 （以充值獲得的藍鑽計算） */
	private int need = 0;
	/** 開啟條件 */
	private int order = 0;
	/** 獎勵 類型,獎勵ID，數量；類型,獎勵ID，數量 */
	private String reward = null;
	/** 前端顯示道具 類型,獎勵ID，數量； */
	private String show = null;

	/** 說明 */
	public String getDsc() {
		return dsc;
	}

	/** 說明 */
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	/** 主介面圖示 */
	public String getIcon() {
		return icon;
	}

	/** 主介面圖示 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/** 介面圖片 */
	public String getPic() {
		return pic;
	}

	/** 介面圖片 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	/** 需求值 （以充值獲得的藍鑽計算） */
	public int getNeed() {
		return need;
	}

	/** 需求值 （以充值獲得的藍鑽計算） */
	public void setNeed(int need) {
		this.need = need;
	}

	/** 開啟條件 */
	public int getOrder() {
		return order;
	}

	/** 開啟條件 */
	public void setOrder(int order) {
		this.order = order;
	}

	/** 獎勵 類型,獎勵ID，數量；類型,獎勵ID，數量 */
	public String getReward() {
		return reward;
	}

	/** 獎勵 類型,獎勵ID，數量；類型,獎勵ID，數量 */
	public void setReward(String reward) {
		this.reward = reward;
	}

	/** 前端顯示道具 類型,獎勵ID，數量； */
	public String getShow() {
		return show;
	}

	/** 前端顯示道具 類型,獎勵ID，數量； */
	public void setShow(String show) {
		this.show = show;
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

			// 說明
			if (key.equals("dsc")) {
				this.dsc = value;
			}

			// 主介面圖示
			if (key.equals("icon")) {
				this.icon = value;
			}

			// 介面圖片
			if (key.equals("pic")) {
				this.pic = value;
			}

			// 需求值 （以充值獲得的藍鑽計算）
			if (key.equals("need")) {
				if (value == null || "".equals(value.trim())) {
					this.need = 0;
				} else {
					this.need = Integer.parseInt(value);
				}
			}

			// 開啟條件
			if (key.equals("order")) {
				if (value == null || "".equals(value.trim())) {
					this.order = 0;
				} else {
					this.order = Integer.parseInt(value);
				}
			}

			// 獎勵 類型,獎勵ID，數量；類型,獎勵ID，數量
			if (key.equals("reward")) {
				this.reward = value;
			}

			// 前端顯示道具 類型,獎勵ID，數量；
			if (key.equals("show")) {
				this.show = value;
			}
		}
	}
}