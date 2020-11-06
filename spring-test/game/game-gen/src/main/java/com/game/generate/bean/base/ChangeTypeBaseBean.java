package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ChangeTypeBaseBean.java
 * <p>
 * 功能：changeType.xls -> changeTypeBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ChangeTypeBaseBean extends BaseBean implements IInitBean {
	/** 消除棋子造成的傷害 */
	private int hurt = 0;

	/** 消除棋子造成的傷害 */
	public int getHurt() {
		return hurt;
	}

	/** 消除棋子造成的傷害 */
	public void setHurt(int hurt) {
		this.hurt = hurt;
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

			// 消除棋子造成的傷害
			if (key.equals("hurt")) {
				if (value == null || "".equals(value.trim())) {
					this.hurt = 0;
				} else {
					this.hurt = Integer.parseInt(value);
				}
			}
		}
	}
}