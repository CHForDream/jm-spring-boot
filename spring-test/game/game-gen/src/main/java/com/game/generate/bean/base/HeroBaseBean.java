package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：HeroBaseBean.java
 * <p>
 * 功能：hero.xls -> heroBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class HeroBaseBean extends BaseBean implements IInitBean {
	/** 英雄進入關卡增加步數 */
	private int addStep = 0;
	/** 英雄, 加體力上限:值大於0, 體力上限增加值. 其他值(或空值)不加體力上限 */
	private int powerLimit = 0;
	/** 重複獲得後,返還的獎勵 道具ID,數量 */
	private String exchange = null;
	/** 解鎖英雄所需要的星星數量 */
	private int limitStar = 0;
	/** 開放狀態 0 未開放 1 開放 */
	private int lockStatus = 0;
	/** 英雄排序 */
	private int sort = 0;

	/** 英雄進入關卡增加步數 */
	public int getAddStep() {
		return addStep;
	}

	/** 英雄進入關卡增加步數 */
	public void setAddStep(int addStep) {
		this.addStep = addStep;
	}

	/** 英雄, 加體力上限:值大於0, 體力上限增加值. 其他值(或空值)不加體力上限 */
	public int getPowerLimit() {
		return powerLimit;
	}

	/** 英雄, 加體力上限:值大於0, 體力上限增加值. 其他值(或空值)不加體力上限 */
	public void setPowerLimit(int powerLimit) {
		this.powerLimit = powerLimit;
	}

	/** 重複獲得後,返還的獎勵 道具ID,數量 */
	public String getExchange() {
		return exchange;
	}

	/** 重複獲得後,返還的獎勵 道具ID,數量 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	/** 解鎖英雄所需要的星星數量 */
	public int getLimitStar() {
		return limitStar;
	}

	/** 解鎖英雄所需要的星星數量 */
	public void setLimitStar(int limitStar) {
		this.limitStar = limitStar;
	}

	/** 開放狀態 0 未開放 1 開放 */
	public int getLockStatus() {
		return lockStatus;
	}

	/** 開放狀態 0 未開放 1 開放 */
	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}

	/** 英雄排序 */
	public int getSort() {
		return sort;
	}

	/** 英雄排序 */
	public void setSort(int sort) {
		this.sort = sort;
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

			// 英雄進入關卡增加步數
			if (key.equals("addStep")) {
				if (value == null || "".equals(value.trim())) {
					this.addStep = 0;
				} else {
					this.addStep = Integer.parseInt(value);
				}
			}

			// 英雄, 加體力上限:值大於0, 體力上限增加值. 其他值(或空值)不加體力上限
			if (key.equals("powerLimit")) {
				if (value == null || "".equals(value.trim())) {
					this.powerLimit = 0;
				} else {
					this.powerLimit = Integer.parseInt(value);
				}
			}

			// 重複獲得後,返還的獎勵 道具ID,數量
			if (key.equals("exchange")) {
				this.exchange = value;
			}

			// 解鎖英雄所需要的星星數量
			if (key.equals("limitStar")) {
				if (value == null || "".equals(value.trim())) {
					this.limitStar = 0;
				} else {
					this.limitStar = Integer.parseInt(value);
				}
			}

			// 開放狀態 0 未開放 1 開放
			if (key.equals("lockStatus")) {
				if (value == null || "".equals(value.trim())) {
					this.lockStatus = 0;
				} else {
					this.lockStatus = Integer.parseInt(value);
				}
			}

			// 英雄排序
			if (key.equals("sort")) {
				if (value == null || "".equals(value.trim())) {
					this.sort = 0;
				} else {
					this.sort = Integer.parseInt(value);
				}
			}
		}
	}
}