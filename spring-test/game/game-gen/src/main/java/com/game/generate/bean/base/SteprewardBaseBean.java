package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：SteprewardBaseBean.java
 * <p>
 * 功能：stepreward.xls -> steprewardBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class SteprewardBaseBean extends BaseBean implements IInitBean {
	/** 累計剩餘步數下限 */
	private int stepDown = 0;
	/** 累計剩餘步數上限 */
	private int stepUp = 0;
	/** 步數獎勵 drop.xls表掉落組ID */
	private String reward = null;

	/** 累計剩餘步數下限 */
	public int getStepDown() {
		return stepDown;
	}

	/** 累計剩餘步數下限 */
	public void setStepDown(int stepDown) {
		this.stepDown = stepDown;
	}

	/** 累計剩餘步數上限 */
	public int getStepUp() {
		return stepUp;
	}

	/** 累計剩餘步數上限 */
	public void setStepUp(int stepUp) {
		this.stepUp = stepUp;
	}

	/** 步數獎勵 drop.xls表掉落組ID */
	public String getReward() {
		return reward;
	}

	/** 步數獎勵 drop.xls表掉落組ID */
	public void setReward(String reward) {
		this.reward = reward;
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

			// 累計剩餘步數下限
			if (key.equals("stepDown")) {
				if (value == null || "".equals(value.trim())) {
					this.stepDown = 0;
				} else {
					this.stepDown = Integer.parseInt(value);
				}
			}

			// 累計剩餘步數上限
			if (key.equals("stepUp")) {
				if (value == null || "".equals(value.trim())) {
					this.stepUp = 0;
				} else {
					this.stepUp = Integer.parseInt(value);
				}
			}

			// 步數獎勵 drop.xls表掉落組ID
			if (key.equals("reward")) {
				this.reward = value;
			}
		}
	}
}