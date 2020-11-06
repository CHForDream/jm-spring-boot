package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：StageBaseBean.java
 * <p>
 * 功能：stage.xls -> stageBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class StageBaseBean extends BaseBean implements IInitBean {
	/** 關卡地圖廢棄 */
	private String map = null;
	/** 匹配類型 1、1v1 2、2v2 3、3v3 */
	private int matchType = 0;
	/** 玩家出現最小等級 */
	private int levelDown = 0;
	/** 玩家出現最大等級 */
	private int levelUp = 0;
	/** 任務ID1（對應target） */
	private int taskId1 = 0;
	/** 任務ID2 */
	private int taskId2 = 0;
	/** 任務ID3 */
	private int taskId3 = 0;
	/** 掉落效果ID掉落的炸彈（對應chessdrop） */
	private int chessdropId = 0;

	/** 關卡地圖廢棄 */
	public String getMap() {
		return map;
	}

	/** 關卡地圖廢棄 */
	public void setMap(String map) {
		this.map = map;
	}

	/** 匹配類型 1、1v1 2、2v2 3、3v3 */
	public int getMatchType() {
		return matchType;
	}

	/** 匹配類型 1、1v1 2、2v2 3、3v3 */
	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}

	/** 玩家出現最小等級 */
	public int getLevelDown() {
		return levelDown;
	}

	/** 玩家出現最小等級 */
	public void setLevelDown(int levelDown) {
		this.levelDown = levelDown;
	}

	/** 玩家出現最大等級 */
	public int getLevelUp() {
		return levelUp;
	}

	/** 玩家出現最大等級 */
	public void setLevelUp(int levelUp) {
		this.levelUp = levelUp;
	}

	/** 任務ID1（對應target） */
	public int getTaskId1() {
		return taskId1;
	}

	/** 任務ID1（對應target） */
	public void setTaskId1(int taskId1) {
		this.taskId1 = taskId1;
	}

	/** 任務ID2 */
	public int getTaskId2() {
		return taskId2;
	}

	/** 任務ID2 */
	public void setTaskId2(int taskId2) {
		this.taskId2 = taskId2;
	}

	/** 任務ID3 */
	public int getTaskId3() {
		return taskId3;
	}

	/** 任務ID3 */
	public void setTaskId3(int taskId3) {
		this.taskId3 = taskId3;
	}

	/** 掉落效果ID掉落的炸彈（對應chessdrop） */
	public int getChessdropId() {
		return chessdropId;
	}

	/** 掉落效果ID掉落的炸彈（對應chessdrop） */
	public void setChessdropId(int chessdropId) {
		this.chessdropId = chessdropId;
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

			// 關卡地圖廢棄
			if (key.equals("map")) {
				this.map = value;
			}

			// 匹配類型 1、1v1 2、2v2 3、3v3
			if (key.equals("matchType")) {
				if (value == null || "".equals(value.trim())) {
					this.matchType = 0;
				} else {
					this.matchType = Integer.parseInt(value);
				}
			}

			// 玩家出現最小等級
			if (key.equals("levelDown")) {
				if (value == null || "".equals(value.trim())) {
					this.levelDown = 0;
				} else {
					this.levelDown = Integer.parseInt(value);
				}
			}

			// 玩家出現最大等級
			if (key.equals("levelUp")) {
				if (value == null || "".equals(value.trim())) {
					this.levelUp = 0;
				} else {
					this.levelUp = Integer.parseInt(value);
				}
			}

			// 任務ID1（對應target）
			if (key.equals("taskId1")) {
				if (value == null || "".equals(value.trim())) {
					this.taskId1 = 0;
				} else {
					this.taskId1 = Integer.parseInt(value);
				}
			}

			// 任務ID2
			if (key.equals("taskId2")) {
				if (value == null || "".equals(value.trim())) {
					this.taskId2 = 0;
				} else {
					this.taskId2 = Integer.parseInt(value);
				}
			}

			// 任務ID3
			if (key.equals("taskId3")) {
				if (value == null || "".equals(value.trim())) {
					this.taskId3 = 0;
				} else {
					this.taskId3 = Integer.parseInt(value);
				}
			}

			// 掉落效果ID掉落的炸彈（對應chessdrop）
			if (key.equals("chessdropId")) {
				if (value == null || "".equals(value.trim())) {
					this.chessdropId = 0;
				} else {
					this.chessdropId = Integer.parseInt(value);
				}
			}
		}
	}
}