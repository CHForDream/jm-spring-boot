package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：ActiveBaseBean.java
 * <p>
 * 功能：active.xls -> activeBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ActiveBaseBean extends BaseBean implements IInitBean {
	/** 技能名字 */
	private String name = null;
	/** 技能描述 */
	private String desc = null;
	/** 觸發技能需要點數 */
	private int needPoint = 0;
	/** 棋子ID */
	private int chessId = 0;
	/** 釋放技能特效 */
	private String releaseEffect = null;
	/** 飛行彈道特效 */
	private String flyEffect = null;
	/** 飛向時間 */
	private float flyTime = 0.0f;
	/** 打中生成棋子特效 */
	private String hitEffect = null;
	/** 技能音效 */
	private String soundeffect = null;
	/** 技能圖示 */
	private String icon = null;

	/** 技能名字 */
	public String getName() {
		return name;
	}

	/** 技能名字 */
	public void setName(String name) {
		this.name = name;
	}

	/** 技能描述 */
	public String getDesc() {
		return desc;
	}

	/** 技能描述 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/** 觸發技能需要點數 */
	public int getNeedPoint() {
		return needPoint;
	}

	/** 觸發技能需要點數 */
	public void setNeedPoint(int needPoint) {
		this.needPoint = needPoint;
	}

	/** 棋子ID */
	public int getChessId() {
		return chessId;
	}

	/** 棋子ID */
	public void setChessId(int chessId) {
		this.chessId = chessId;
	}

	/** 釋放技能特效 */
	public String getReleaseEffect() {
		return releaseEffect;
	}

	/** 釋放技能特效 */
	public void setReleaseEffect(String releaseEffect) {
		this.releaseEffect = releaseEffect;
	}

	/** 飛行彈道特效 */
	public String getFlyEffect() {
		return flyEffect;
	}

	/** 飛行彈道特效 */
	public void setFlyEffect(String flyEffect) {
		this.flyEffect = flyEffect;
	}

	/** 飛向時間 */
	public float getFlyTime() {
		return flyTime;
	}

	/** 飛向時間 */
	public void setFlyTime(float flyTime) {
		this.flyTime = flyTime;
	}

	/** 打中生成棋子特效 */
	public String getHitEffect() {
		return hitEffect;
	}

	/** 打中生成棋子特效 */
	public void setHitEffect(String hitEffect) {
		this.hitEffect = hitEffect;
	}

	/** 技能音效 */
	public String getSoundeffect() {
		return soundeffect;
	}

	/** 技能音效 */
	public void setSoundeffect(String soundeffect) {
		this.soundeffect = soundeffect;
	}

	/** 技能圖示 */
	public String getIcon() {
		return icon;
	}

	/** 技能圖示 */
	public void setIcon(String icon) {
		this.icon = icon;
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

			// 技能名字
			if (key.equals("name")) {
				this.name = value;
			}

			// 技能描述
			if (key.equals("desc")) {
				this.desc = value;
			}

			// 觸發技能需要點數
			if (key.equals("needPoint")) {
				if (value == null || "".equals(value.trim())) {
					this.needPoint = 0;
				} else {
					this.needPoint = Integer.parseInt(value);
				}
			}

			// 棋子ID
			if (key.equals("chessId")) {
				if (value == null || "".equals(value.trim())) {
					this.chessId = 0;
				} else {
					this.chessId = Integer.parseInt(value);
				}
			}

			// 釋放技能特效
			if (key.equals("releaseEffect")) {
				this.releaseEffect = value;
			}

			// 飛行彈道特效
			if (key.equals("flyEffect")) {
				this.flyEffect = value;
			}

			// 飛向時間
			if (key.equals("flyTime")) {
				if (value == null || "".equals(value.trim())) {
					this.flyTime = 0;
				} else {
					this.flyTime = Float.parseFloat(value);
				}
			}

			// 打中生成棋子特效
			if (key.equals("hitEffect")) {
				this.hitEffect = value;
			}

			// 技能音效
			if (key.equals("soundeffect")) {
				this.soundeffect = value;
			}

			// 技能圖示
			if (key.equals("icon")) {
				this.icon = value;
			}
		}
	}
}