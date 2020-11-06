package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：DuplicateBaseBean.java
 * <p>
 * 功能：duplicate.xls -> duplicateBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class DuplicateBaseBean extends BaseBean implements IInitBean {
	/** 類型 1、普通副本 只給首次通關獎勵 2、難度副本 */
	private int type = 0;
	/** 關卡Id 副本所對應的關卡ID */
	private int target = 0;
	/** 進入副本所需花費： （暫定只有金幣和鑽石、看視頻） itemtype,itemid,itemnum */
	private String costItem = null;
	/** 1星星對應的分數 */
	private int starScore1 = 0;
	/** 1星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	private String starAward1 = null;
	/** 2星星對應的分數 */
	private int starScore2 = 0;
	/** 2星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	private String starAward2 = null;
	/** 3星星對應的分數 */
	private int starScore3 = 0;
	/** 3星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	private String starAward3 = null;

	/** 類型 1、普通副本 只給首次通關獎勵 2、難度副本 */
	public int getType() {
		return type;
	}

	/** 類型 1、普通副本 只給首次通關獎勵 2、難度副本 */
	public void setType(int type) {
		this.type = type;
	}

	/** 關卡Id 副本所對應的關卡ID */
	public int getTarget() {
		return target;
	}

	/** 關卡Id 副本所對應的關卡ID */
	public void setTarget(int target) {
		this.target = target;
	}

	/** 進入副本所需花費： （暫定只有金幣和鑽石、看視頻） itemtype,itemid,itemnum */
	public String getCostItem() {
		return costItem;
	}

	/** 進入副本所需花費： （暫定只有金幣和鑽石、看視頻） itemtype,itemid,itemnum */
	public void setCostItem(String costItem) {
		this.costItem = costItem;
	}

	/** 1星星對應的分數 */
	public int getStarScore1() {
		return starScore1;
	}

	/** 1星星對應的分數 */
	public void setStarScore1(int starScore1) {
		this.starScore1 = starScore1;
	}

	/** 1星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	public String getStarAward1() {
		return starAward1;
	}

	/** 1星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	public void setStarAward1(String starAward1) {
		this.starAward1 = starAward1;
	}

	/** 2星星對應的分數 */
	public int getStarScore2() {
		return starScore2;
	}

	/** 2星星對應的分數 */
	public void setStarScore2(int starScore2) {
		this.starScore2 = starScore2;
	}

	/** 2星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	public String getStarAward2() {
		return starAward2;
	}

	/** 2星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	public void setStarAward2(String starAward2) {
		this.starAward2 = starAward2;
	}

	/** 3星星對應的分數 */
	public int getStarScore3() {
		return starScore3;
	}

	/** 3星星對應的分數 */
	public void setStarScore3(int starScore3) {
		this.starScore3 = starScore3;
	}

	/** 3星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	public String getStarAward3() {
		return starAward3;
	}

	/** 3星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;) */
	public void setStarAward3(String starAward3) {
		this.starAward3 = starAward3;
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

			// 類型 1、普通副本 只給首次通關獎勵 2、難度副本
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 關卡Id 副本所對應的關卡ID
			if (key.equals("target")) {
				if (value == null || "".equals(value.trim())) {
					this.target = 0;
				} else {
					this.target = Integer.parseInt(value);
				}
			}

			// 進入副本所需花費： （暫定只有金幣和鑽石、看視頻） itemtype,itemid,itemnum
			if (key.equals("costItem")) {
				this.costItem = value;
			}

			// 1星星對應的分數
			if (key.equals("starScore1")) {
				if (value == null || "".equals(value.trim())) {
					this.starScore1 = 0;
				} else {
					this.starScore1 = Integer.parseInt(value);
				}
			}

			// 1星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;)
			if (key.equals("starAward1")) {
				this.starAward1 = value;
			}

			// 2星星對應的分數
			if (key.equals("starScore2")) {
				if (value == null || "".equals(value.trim())) {
					this.starScore2 = 0;
				} else {
					this.starScore2 = Integer.parseInt(value);
				}
			}

			// 2星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;)
			if (key.equals("starAward2")) {
				this.starAward2 = value;
			}

			// 3星星對應的分數
			if (key.equals("starScore3")) {
				if (value == null || "".equals(value.trim())) {
					this.starScore3 = 0;
				} else {
					this.starScore3 = Integer.parseInt(value);
				}
			}

			// 3星星對應的獎勵 (itemtype,itemid,itemnum; itemtype,itemid,itemnum;)
			if (key.equals("starAward3")) {
				this.starAward3 = value;
			}
		}
	}
}