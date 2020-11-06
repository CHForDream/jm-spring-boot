package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：QuestBaseBean.java
 * <p>
 * 功能：quest.xls -> questBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class QuestBaseBean extends BaseBean implements IInitBean {
	/** 任務類型 1.每日登錄 2.通關單機x次 3.進行x次副本挑戰 4.觀看x次廣告 5.使用關卡前道具x次 6.使用戰鬥內道具x次 7.消耗金幣xxxx */
	private int type = 0;
	/** 任務參數 */
	private int param = 0;
	/** 任務次數 */
	private int totaltimes = 0;
	/** 任務排序 */
	private int rank = 0;
	/** 任務標題 */
	private String title = null;
	/** 任務描述 */
	private String info = null;
	/** 任務圖示 */
	private String icon = null;
	/** 解鎖條件（系統ID） */
	private int projectId = 0;
	/** 獎勵類型 */
	private String item = null;
	/** 前往介面名字 */
	private int goal = 0;

	/** 任務類型 1.每日登錄 2.通關單機x次 3.進行x次副本挑戰 4.觀看x次廣告 5.使用關卡前道具x次 6.使用戰鬥內道具x次 7.消耗金幣xxxx */
	public int getType() {
		return type;
	}

	/** 任務類型 1.每日登錄 2.通關單機x次 3.進行x次副本挑戰 4.觀看x次廣告 5.使用關卡前道具x次 6.使用戰鬥內道具x次 7.消耗金幣xxxx */
	public void setType(int type) {
		this.type = type;
	}

	/** 任務參數 */
	public int getParam() {
		return param;
	}

	/** 任務參數 */
	public void setParam(int param) {
		this.param = param;
	}

	/** 任務次數 */
	public int getTotaltimes() {
		return totaltimes;
	}

	/** 任務次數 */
	public void setTotaltimes(int totaltimes) {
		this.totaltimes = totaltimes;
	}

	/** 任務排序 */
	public int getRank() {
		return rank;
	}

	/** 任務排序 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/** 任務標題 */
	public String getTitle() {
		return title;
	}

	/** 任務標題 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 任務描述 */
	public String getInfo() {
		return info;
	}

	/** 任務描述 */
	public void setInfo(String info) {
		this.info = info;
	}

	/** 任務圖示 */
	public String getIcon() {
		return icon;
	}

	/** 任務圖示 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/** 解鎖條件（系統ID） */
	public int getProjectId() {
		return projectId;
	}

	/** 解鎖條件（系統ID） */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/** 獎勵類型 */
	public String getItem() {
		return item;
	}

	/** 獎勵類型 */
	public void setItem(String item) {
		this.item = item;
	}

	/** 前往介面名字 */
	public int getGoal() {
		return goal;
	}

	/** 前往介面名字 */
	public void setGoal(int goal) {
		this.goal = goal;
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

			// 任務類型 1.每日登錄 2.通關單機x次 3.進行x次副本挑戰 4.觀看x次廣告 5.使用關卡前道具x次 6.使用戰鬥內道具x次 7.消耗金幣xxxx
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 任務參數
			if (key.equals("param")) {
				if (value == null || "".equals(value.trim())) {
					this.param = 0;
				} else {
					this.param = Integer.parseInt(value);
				}
			}

			// 任務次數
			if (key.equals("totaltimes")) {
				if (value == null || "".equals(value.trim())) {
					this.totaltimes = 0;
				} else {
					this.totaltimes = Integer.parseInt(value);
				}
			}

			// 任務排序
			if (key.equals("rank")) {
				if (value == null || "".equals(value.trim())) {
					this.rank = 0;
				} else {
					this.rank = Integer.parseInt(value);
				}
			}

			// 任務標題
			if (key.equals("title")) {
				this.title = value;
			}

			// 任務描述
			if (key.equals("info")) {
				this.info = value;
			}

			// 任務圖示
			if (key.equals("icon")) {
				this.icon = value;
			}

			// 解鎖條件（系統ID）
			if (key.equals("projectId")) {
				if (value == null || "".equals(value.trim())) {
					this.projectId = 0;
				} else {
					this.projectId = Integer.parseInt(value);
				}
			}

			// 獎勵類型
			if (key.equals("item")) {
				this.item = value;
			}

			// 前往介面名字
			if (key.equals("goal")) {
				if (value == null || "".equals(value.trim())) {
					this.goal = 0;
				} else {
					this.goal = Integer.parseInt(value);
				}
			}
		}
	}
}