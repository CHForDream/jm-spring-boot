package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：EffectBaseBean.java
 * <p>
 * 功能：effect.xls -> effectBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class EffectBaseBean extends BaseBean implements IInitBean {
	/** 效果參數（第一個參數為波及範圍，第二個參數為自訂銷毀座標） */
	private String param2 = null;
	/** 描述 */
	private String desc = null;
	/** 爆炸特效 */
	private String StringBoom = null;
	/** 是否產生間接傷害 */
	private int triIndirectDmg = 0;
	/** 產生棋子效果，填寫棋子id */
	private int spreadFloorId = 0;

	/** 效果參數（第一個參數為波及範圍，第二個參數為自訂銷毀座標） */
	public String getParam2() {
		return param2;
	}

	/** 效果參數（第一個參數為波及範圍，第二個參數為自訂銷毀座標） */
	public void setParam2(String param2) {
		this.param2 = param2;
	}

	/** 描述 */
	public String getDesc() {
		return desc;
	}

	/** 描述 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/** 爆炸特效 */
	public String getStringBoom() {
		return StringBoom;
	}

	/** 爆炸特效 */
	public void setStringBoom(String StringBoom) {
		this.StringBoom = StringBoom;
	}

	/** 是否產生間接傷害 */
	public int getTriIndirectDmg() {
		return triIndirectDmg;
	}

	/** 是否產生間接傷害 */
	public void setTriIndirectDmg(int triIndirectDmg) {
		this.triIndirectDmg = triIndirectDmg;
	}

	/** 產生棋子效果，填寫棋子id */
	public int getSpreadFloorId() {
		return spreadFloorId;
	}

	/** 產生棋子效果，填寫棋子id */
	public void setSpreadFloorId(int spreadFloorId) {
		this.spreadFloorId = spreadFloorId;
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

			// 效果參數（第一個參數為波及範圍，第二個參數為自訂銷毀座標）
			if (key.equals("param2")) {
				this.param2 = value;
			}

			// 描述
			if (key.equals("desc")) {
				this.desc = value;
			}

			// 爆炸特效
			if (key.equals("StringBoom")) {
				this.StringBoom = value;
			}

			// 是否產生間接傷害
			if (key.equals("triIndirectDmg")) {
				if (value == null || "".equals(value.trim())) {
					this.triIndirectDmg = 0;
				} else {
					this.triIndirectDmg = Integer.parseInt(value);
				}
			}

			// 產生棋子效果，填寫棋子id
			if (key.equals("spreadFloorId")) {
				if (value == null || "".equals(value.trim())) {
					this.spreadFloorId = 0;
				} else {
					this.spreadFloorId = Integer.parseInt(value);
				}
			}
		}
	}
}