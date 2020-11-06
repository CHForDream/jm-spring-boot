package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：EliteDuplicateBaseBean.java
 * <p>
 * 功能：eliteDuplicate.xls -> eliteDuplicateBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class EliteDuplicateBaseBean extends BaseBean implements IInitBean {
	/** 总步数 */
	private int step = 0;
	/** 關卡Id1 */
	private int target1 = 0;
	/** 關卡Id2 */
	private int target2 = 0;
	/** 關卡Id3 */
	private int target3 = 0;
	/** 关卡1獎勵 type,id,num */
	private String reward1 = null;
	/** 关卡2獎勵 type,id,num */
	private String reward2 = null;
	/** 关卡3獎勵 type,id,num */
	private String reward3 = null;

	/** 总步数 */
	public int getStep() {
		return step;
	}

	/** 总步数 */
	public void setStep(int step) {
		this.step = step;
	}

	/** 關卡Id1 */
	public int getTarget1() {
		return target1;
	}

	/** 關卡Id1 */
	public void setTarget1(int target1) {
		this.target1 = target1;
	}

	/** 關卡Id2 */
	public int getTarget2() {
		return target2;
	}

	/** 關卡Id2 */
	public void setTarget2(int target2) {
		this.target2 = target2;
	}

	/** 關卡Id3 */
	public int getTarget3() {
		return target3;
	}

	/** 關卡Id3 */
	public void setTarget3(int target3) {
		this.target3 = target3;
	}

	/** 关卡1獎勵 type,id,num */
	public String getReward1() {
		return reward1;
	}

	/** 关卡1獎勵 type,id,num */
	public void setReward1(String reward1) {
		this.reward1 = reward1;
	}

	/** 关卡2獎勵 type,id,num */
	public String getReward2() {
		return reward2;
	}

	/** 关卡2獎勵 type,id,num */
	public void setReward2(String reward2) {
		this.reward2 = reward2;
	}

	/** 关卡3獎勵 type,id,num */
	public String getReward3() {
		return reward3;
	}

	/** 关卡3獎勵 type,id,num */
	public void setReward3(String reward3) {
		this.reward3 = reward3;
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

			// 总步数
			if (key.equals("step")) {
				if (value == null || "".equals(value.trim())) {
					this.step = 0;
				} else {
					this.step = Integer.parseInt(value);
				}
			}

			// 關卡Id1
			if (key.equals("target1")) {
				if (value == null || "".equals(value.trim())) {
					this.target1 = 0;
				} else {
					this.target1 = Integer.parseInt(value);
				}
			}

			// 關卡Id2
			if (key.equals("target2")) {
				if (value == null || "".equals(value.trim())) {
					this.target2 = 0;
				} else {
					this.target2 = Integer.parseInt(value);
				}
			}

			// 關卡Id3
			if (key.equals("target3")) {
				if (value == null || "".equals(value.trim())) {
					this.target3 = 0;
				} else {
					this.target3 = Integer.parseInt(value);
				}
			}

			// 关卡1獎勵 type,id,num
			if (key.equals("reward1")) {
				this.reward1 = value;
			}

			// 关卡2獎勵 type,id,num
			if (key.equals("reward2")) {
				this.reward2 = value;
			}

			// 关卡3獎勵 type,id,num
			if (key.equals("reward3")) {
				this.reward3 = value;
			}
		}
	}
}