package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：AchieveBaseBean.java
 * <p>
 * 功能：achieve.xls -> achieveBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class AchieveBaseBean extends BaseBean implements IInitBean {
	/** 任務類型 1：登錄x天 2.通關副本個數 3.通關副本次數 4.累計x天活躍度到達xxx 5：收集棋子 棋子ID，數量 6：累積獲得金幣xxx 7：累積獲得鑽石xxx 8:關卡內觸發連擊x次（1-2連擊） 9:關卡內觸發連擊x次（3-4連擊） 10：使用戰鬥內道具x次: 11：使... */
	private int type = 0;
	/** 下一個成就 */
	private int next = 0;
	/** 任務組 */
	private int group = 0;
	/** 任務參數1 任務條件 */
	private String param1 = null;
	/** 任務參數2 任務數量 */
	private int param2 = 0;
	/** 任務排序 */
	private int rank = 0;
	/** 任務標題 */
	private String title = null;
	/** 任務描述 */
	private String info = null;
	/** 任務圖示 */
	private String icon = null;
	/** 獎勵:類型,id，數量 */
	private String item = null;
	/** 前往介面名字 */
	private int goal = 0;
	/** 成就點數 */
	private int point = 0;
	/** 任務所屬 */
	private int sort = 0;
	/** 是否需要開啟後判斷1為需要判斷，0為不需要 */
	private int judge = 0;

	/** 任務類型 1：登錄x天 2.通關副本個數 3.通關副本次數 4.累計x天活躍度到達xxx 5：收集棋子 棋子ID，數量 6：累積獲得金幣xxx 7：累積獲得鑽石xxx 8:關卡內觸發連擊x次（1-2連擊） 9:關卡內觸發連擊x次（3-4連擊） 10：使用戰鬥內道具x次: 11：使... */
	public int getType() {
		return type;
	}

	/** 任務類型 1：登錄x天 2.通關副本個數 3.通關副本次數 4.累計x天活躍度到達xxx 5：收集棋子 棋子ID，數量 6：累積獲得金幣xxx 7：累積獲得鑽石xxx 8:關卡內觸發連擊x次（1-2連擊） 9:關卡內觸發連擊x次（3-4連擊） 10：使用戰鬥內道具x次: 11：使... */
	public void setType(int type) {
		this.type = type;
	}

	/** 下一個成就 */
	public int getNext() {
		return next;
	}

	/** 下一個成就 */
	public void setNext(int next) {
		this.next = next;
	}

	/** 任務組 */
	public int getGroup() {
		return group;
	}

	/** 任務組 */
	public void setGroup(int group) {
		this.group = group;
	}

	/** 任務參數1 任務條件 */
	public String getParam1() {
		return param1;
	}

	/** 任務參數1 任務條件 */
	public void setParam1(String param1) {
		this.param1 = param1;
	}

	/** 任務參數2 任務數量 */
	public int getParam2() {
		return param2;
	}

	/** 任務參數2 任務數量 */
	public void setParam2(int param2) {
		this.param2 = param2;
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

	/** 獎勵:類型,id，數量 */
	public String getItem() {
		return item;
	}

	/** 獎勵:類型,id，數量 */
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

	/** 成就點數 */
	public int getPoint() {
		return point;
	}

	/** 成就點數 */
	public void setPoint(int point) {
		this.point = point;
	}

	/** 任務所屬 */
	public int getSort() {
		return sort;
	}

	/** 任務所屬 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/** 是否需要開啟後判斷1為需要判斷，0為不需要 */
	public int getJudge() {
		return judge;
	}

	/** 是否需要開啟後判斷1為需要判斷，0為不需要 */
	public void setJudge(int judge) {
		this.judge = judge;
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

			// 任務類型 1：登錄x天 2.通關副本個數 3.通關副本次數 4.累計x天活躍度到達xxx 5：收集棋子 棋子ID，數量 6：累積獲得金幣xxx 7：累積獲得鑽石xxx 8:關卡內觸發連擊x次（1-2連擊） 9:關卡內觸發連擊x次（3-4連擊） 10：使用戰鬥內道具x次: 11：使...
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 下一個成就
			if (key.equals("next")) {
				if (value == null || "".equals(value.trim())) {
					this.next = 0;
				} else {
					this.next = Integer.parseInt(value);
				}
			}

			// 任務組
			if (key.equals("group")) {
				if (value == null || "".equals(value.trim())) {
					this.group = 0;
				} else {
					this.group = Integer.parseInt(value);
				}
			}

			// 任務參數1 任務條件
			if (key.equals("param1")) {
				this.param1 = value;
			}

			// 任務參數2 任務數量
			if (key.equals("param2")) {
				if (value == null || "".equals(value.trim())) {
					this.param2 = 0;
				} else {
					this.param2 = Integer.parseInt(value);
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

			// 獎勵:類型,id，數量
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

			// 成就點數
			if (key.equals("point")) {
				if (value == null || "".equals(value.trim())) {
					this.point = 0;
				} else {
					this.point = Integer.parseInt(value);
				}
			}

			// 任務所屬
			if (key.equals("sort")) {
				if (value == null || "".equals(value.trim())) {
					this.sort = 0;
				} else {
					this.sort = Integer.parseInt(value);
				}
			}

			// 是否需要開啟後判斷1為需要判斷，0為不需要
			if (key.equals("judge")) {
				if (value == null || "".equals(value.trim())) {
					this.judge = 0;
				} else {
					this.judge = Integer.parseInt(value);
				}
			}
		}
	}
}