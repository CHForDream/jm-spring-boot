package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ScoreconfigBaseBean.java
 * <p>
 * 功能：scoreconfig.xls -> scoreconfigBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ScoreconfigBaseBean extends BaseBean implements IInitBean {
	/** 1.普通得分 2.合成得分 3.技能得分 4.目標得分 */
	private int scoreType = 0;
	/** 1.普通棋子單消 2.普通棋子三消 3.特殊棋子單消 4.合成飛機棋子 5.合成炸彈棋子 6.合成火箭棋子 7.合成彩虹棋子 8.道具棋子交換融合 9.技能釋放 10.完成目標 */
	private int type = 0;
	/** 分數值 */
	private int score = 0;

	/** 1.普通得分 2.合成得分 3.技能得分 4.目標得分 */
	public int getScoreType() {
		return scoreType;
	}

	/** 1.普通得分 2.合成得分 3.技能得分 4.目標得分 */
	public void setScoreType(int scoreType) {
		this.scoreType = scoreType;
	}

	/** 1.普通棋子單消 2.普通棋子三消 3.特殊棋子單消 4.合成飛機棋子 5.合成炸彈棋子 6.合成火箭棋子 7.合成彩虹棋子 8.道具棋子交換融合 9.技能釋放 10.完成目標 */
	public int getType() {
		return type;
	}

	/** 1.普通棋子單消 2.普通棋子三消 3.特殊棋子單消 4.合成飛機棋子 5.合成炸彈棋子 6.合成火箭棋子 7.合成彩虹棋子 8.道具棋子交換融合 9.技能釋放 10.完成目標 */
	public void setType(int type) {
		this.type = type;
	}

	/** 分數值 */
	public int getScore() {
		return score;
	}

	/** 分數值 */
	public void setScore(int score) {
		this.score = score;
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

			// 1.普通得分 2.合成得分 3.技能得分 4.目標得分
			if (key.equals("scoreType")) {
				if (value == null || "".equals(value.trim())) {
					this.scoreType = 0;
				} else {
					this.scoreType = Integer.parseInt(value);
				}
			}

			// 1.普通棋子單消 2.普通棋子三消 3.特殊棋子單消 4.合成飛機棋子 5.合成炸彈棋子 6.合成火箭棋子 7.合成彩虹棋子 8.道具棋子交換融合 9.技能釋放 10.完成目標
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 分數值
			if (key.equals("score")) {
				if (value == null || "".equals(value.trim())) {
					this.score = 0;
				} else {
					this.score = Integer.parseInt(value);
				}
			}
		}
	}
}