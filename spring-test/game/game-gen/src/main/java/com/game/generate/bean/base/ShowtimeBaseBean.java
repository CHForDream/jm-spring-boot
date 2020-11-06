package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ShowtimeBaseBean.java
 * <p>
 * 功能：showtime.xls -> showtimeBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ShowtimeBaseBean extends BaseBean implements IInitBean {
	/** 達成條件 1、連擊彈幕 2、合成出棋子 3、棋子和棋子合成 4、釋放技能 */
	private int conditions = 0;
	/** 達成條件值 */
	private String conditionsNum = null;
	/** 描述文字 */
	private String des = null;
	/** 發送目標 */
	private int targets = 0;
	/** 持續時間 */
	private int duration = 0;
	/** 再次達成顯示 */
	private int next = 0;

	/** 達成條件 1、連擊彈幕 2、合成出棋子 3、棋子和棋子合成 4、釋放技能 */
	public int getConditions() {
		return conditions;
	}

	/** 達成條件 1、連擊彈幕 2、合成出棋子 3、棋子和棋子合成 4、釋放技能 */
	public void setConditions(int conditions) {
		this.conditions = conditions;
	}

	/** 達成條件值 */
	public String getConditionsNum() {
		return conditionsNum;
	}

	/** 達成條件值 */
	public void setConditionsNum(String conditionsNum) {
		this.conditionsNum = conditionsNum;
	}

	/** 描述文字 */
	public String getDes() {
		return des;
	}

	/** 描述文字 */
	public void setDes(String des) {
		this.des = des;
	}

	/** 發送目標 */
	public int getTargets() {
		return targets;
	}

	/** 發送目標 */
	public void setTargets(int targets) {
		this.targets = targets;
	}

	/** 持續時間 */
	public int getDuration() {
		return duration;
	}

	/** 持續時間 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/** 再次達成顯示 */
	public int getNext() {
		return next;
	}

	/** 再次達成顯示 */
	public void setNext(int next) {
		this.next = next;
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

			// 達成條件 1、連擊彈幕 2、合成出棋子 3、棋子和棋子合成 4、釋放技能
			if (key.equals("conditions")) {
				if (value == null || "".equals(value.trim())) {
					this.conditions = 0;
				} else {
					this.conditions = Integer.parseInt(value);
				}
			}

			// 達成條件值
			if (key.equals("conditionsNum")) {
				this.conditionsNum = value;
			}

			// 描述文字
			if (key.equals("des")) {
				this.des = value;
			}

			// 發送目標
			if (key.equals("targets")) {
				if (value == null || "".equals(value.trim())) {
					this.targets = 0;
				} else {
					this.targets = Integer.parseInt(value);
				}
			}

			// 持續時間
			if (key.equals("duration")) {
				if (value == null || "".equals(value.trim())) {
					this.duration = 0;
				} else {
					this.duration = Integer.parseInt(value);
				}
			}

			// 再次達成顯示
			if (key.equals("next")) {
				if (value == null || "".equals(value.trim())) {
					this.next = 0;
				} else {
					this.next = Integer.parseInt(value);
				}
			}
		}
	}
}