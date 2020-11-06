package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：WinningStreakBaseBean.java
 * <p>
 * 功能：winningStreak.xls -> winningStreakBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class WinningStreakBaseBean extends BaseBean implements IInitBean {
	/** 連續勝利的次數 */
	private int number = 0;
	/** 關卡前道具獎勵 （棋子id，數量；棋子id，數量） */
	private String cssProps = null;

	/** 連續勝利的次數 */
	public int getNumber() {
		return number;
	}

	/** 連續勝利的次數 */
	public void setNumber(int number) {
		this.number = number;
	}

	/** 關卡前道具獎勵 （棋子id，數量；棋子id，數量） */
	public String getCssProps() {
		return cssProps;
	}

	/** 關卡前道具獎勵 （棋子id，數量；棋子id，數量） */
	public void setCssProps(String cssProps) {
		this.cssProps = cssProps;
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

			// 連續勝利的次數
			if (key.equals("number")) {
				if (value == null || "".equals(value.trim())) {
					this.number = 0;
				} else {
					this.number = Integer.parseInt(value);
				}
			}

			// 關卡前道具獎勵 （棋子id，數量；棋子id，數量）
			if (key.equals("cssProps")) {
				this.cssProps = value;
			}
		}
	}
}