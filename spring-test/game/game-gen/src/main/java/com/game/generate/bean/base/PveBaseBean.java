package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：PveBaseBean.java
 * <p>
 * 功能：pve.xls -> pveBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class PveBaseBean extends BaseBean implements IInitBean {
	/** 城市 */
	private int city = 0;
	/** 城市名稱 */
	private String cityname = null;
	/** 類型 1.關卡 2.景點 */
	private int type = 0;
	/** 關聯target表id */
	private int targetId = 0;
	/** 通關獎勵 */
	private String reward = null;
	/** 步數獎勵每一步 */
	private String stepGet = null;
	/** 難度關卡獎勵 */
	private String hardReward = null;
	/** 寶箱獎勵 */
	private String chestReward = null;
	/** 副本id 對應duplicate表Id */
	private int dupId = 0;
	/** 推薦英雄 heroId */
	private int recommendHero = 0;
	/** 推薦道具 itemtype,itemid; */
	private String recommendItems = null;
	/** 觸發推薦的次數 */
	private int failedNum = 0;

	/** 城市 */
	public int getCity() {
		return city;
	}

	/** 城市 */
	public void setCity(int city) {
		this.city = city;
	}

	/** 城市名稱 */
	public String getCityname() {
		return cityname;
	}

	/** 城市名稱 */
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	/** 類型 1.關卡 2.景點 */
	public int getType() {
		return type;
	}

	/** 類型 1.關卡 2.景點 */
	public void setType(int type) {
		this.type = type;
	}

	/** 關聯target表id */
	public int getTargetId() {
		return targetId;
	}

	/** 關聯target表id */
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	/** 通關獎勵 */
	public String getReward() {
		return reward;
	}

	/** 通關獎勵 */
	public void setReward(String reward) {
		this.reward = reward;
	}

	/** 步數獎勵每一步 */
	public String getStepGet() {
		return stepGet;
	}

	/** 步數獎勵每一步 */
	public void setStepGet(String stepGet) {
		this.stepGet = stepGet;
	}

	/** 難度關卡獎勵 */
	public String getHardReward() {
		return hardReward;
	}

	/** 難度關卡獎勵 */
	public void setHardReward(String hardReward) {
		this.hardReward = hardReward;
	}

	/** 寶箱獎勵 */
	public String getChestReward() {
		return chestReward;
	}

	/** 寶箱獎勵 */
	public void setChestReward(String chestReward) {
		this.chestReward = chestReward;
	}

	/** 副本id 對應duplicate表Id */
	public int getDupId() {
		return dupId;
	}

	/** 副本id 對應duplicate表Id */
	public void setDupId(int dupId) {
		this.dupId = dupId;
	}

	/** 推薦英雄 heroId */
	public int getRecommendHero() {
		return recommendHero;
	}

	/** 推薦英雄 heroId */
	public void setRecommendHero(int recommendHero) {
		this.recommendHero = recommendHero;
	}

	/** 推薦道具 itemtype,itemid; */
	public String getRecommendItems() {
		return recommendItems;
	}

	/** 推薦道具 itemtype,itemid; */
	public void setRecommendItems(String recommendItems) {
		this.recommendItems = recommendItems;
	}

	/** 觸發推薦的次數 */
	public int getFailedNum() {
		return failedNum;
	}

	/** 觸發推薦的次數 */
	public void setFailedNum(int failedNum) {
		this.failedNum = failedNum;
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

			// 城市
			if (key.equals("city")) {
				if (value == null || "".equals(value.trim())) {
					this.city = 0;
				} else {
					this.city = Integer.parseInt(value);
				}
			}

			// 城市名稱
			if (key.equals("cityname")) {
				this.cityname = value;
			}

			// 類型 1.關卡 2.景點
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 關聯target表id
			if (key.equals("targetId")) {
				if (value == null || "".equals(value.trim())) {
					this.targetId = 0;
				} else {
					this.targetId = Integer.parseInt(value);
				}
			}

			// 通關獎勵
			if (key.equals("reward")) {
				this.reward = value;
			}

			// 步數獎勵每一步
			if (key.equals("stepGet")) {
				this.stepGet = value;
			}

			// 難度關卡獎勵
			if (key.equals("hardReward")) {
				this.hardReward = value;
			}

			// 寶箱獎勵
			if (key.equals("chestReward")) {
				this.chestReward = value;
			}

			// 副本id 對應duplicate表Id
			if (key.equals("dupId")) {
				if (value == null || "".equals(value.trim())) {
					this.dupId = 0;
				} else {
					this.dupId = Integer.parseInt(value);
				}
			}

			// 推薦英雄 heroId
			if (key.equals("recommendHero")) {
				if (value == null || "".equals(value.trim())) {
					this.recommendHero = 0;
				} else {
					this.recommendHero = Integer.parseInt(value);
				}
			}

			// 推薦道具 itemtype,itemid;
			if (key.equals("recommendItems")) {
				this.recommendItems = value;
			}

			// 觸發推薦的次數
			if (key.equals("failedNum")) {
				if (value == null || "".equals(value.trim())) {
					this.failedNum = 0;
				} else {
					this.failedNum = Integer.parseInt(value);
				}
			}
		}
	}
}