package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：EvaluationAddBaseBean.java
 * <p>
 * 功能：evaluationAdd.xls -> evaluationAddBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class EvaluationAddBaseBean extends BaseBean implements IInitBean {
	/** 名稱 */
	private String Name = null;
	/** 評分區間 */
	private float score = 0.0f;
	/** 類型/100 黃金，英雄熟練度，銀幣 */
	private String add = null;

	/** 名稱 */
	public String getName() {
		return Name;
	}

	/** 名稱 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/** 評分區間 */
	public float getScore() {
		return score;
	}

	/** 評分區間 */
	public void setScore(float score) {
		this.score = score;
	}

	/** 類型/100 黃金，英雄熟練度，銀幣 */
	public String getAdd() {
		return add;
	}

	/** 類型/100 黃金，英雄熟練度，銀幣 */
	public void setAdd(String add) {
		this.add = add;
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
			if (key.equals("Name")) {
				this.Name = value;
			}

			// 評分區間
			if (key.equals("score")) {
				if (value == null || "".equals(value.trim())) {
					this.score = 0;
				} else {
					this.score = Float.parseFloat(value);
				}
			}

			// 類型/100 黃金，英雄熟練度，銀幣
			if (key.equals("add")) {
				this.add = value;
			}
		}
	}
}