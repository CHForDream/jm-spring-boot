package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：GradingBaseBean.java
 * <p>
 * 功能：grading.xls -> gradingBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class GradingBaseBean extends BaseBean implements IInitBean {
	/** 積分下限 */
	private int pointDown = 0;
	/** 積分上限 */
	private int pointUp = 0;
	/** 大段位 1萌新小白 2初級入門 3探險學徒 4中級歷練 5小有所成 6冒險開始 7高級達人 8消除大牛 */
	private int gradingTpye = 0;
	/** 段位元等級 */
	private int level = 0;
	/** 星星上限 */
	private int starLimit = 0;
	/** 段位名稱 */
	private String gradingName = null;
	/** 賽季段位獎勵 達成段位任務即可獲得獎勵 對應道具表ID */
	private String reward = null;
	/** 賽季結束後掉落積分數量 */
	private int lossStart = 0;
	/** 段位元圖示 */
	private String icon = null;
	/** 獎勵郵件標題 對應CN表，用戶端目錄下的 */
	private String mailTitle = null;
	/** 獎勵郵件內容 對應CN表，用戶端目錄下的 */
	private String mailContent = null;
	/** 段位任務類型 1：組隊排位次數 2：單人排位次數 3:參與排位賽次數 4：擊殺玩家次數 */
	private String taskName = null;
	/** GM工具配段位任務類型 1：組隊排位次數 2：單人排位次數 3:參與排位賽次數 4：擊殺玩家次數 用於備註，具體由GM配置 */
	private int taskType = 0;
	/** GM工具配任務需求 */
	private int valua = 0;
	/** 段位分值 */
	private int score = 0;
	/** 最大等待真實玩家時間,初始等待5秒，之後用這個時間 */
	private int waitTime = 0;
	/** 賽季結束後退到哪個段位 */
	private int endBack = 0;

	/** 積分下限 */
	public int getPointDown() {
		return pointDown;
	}

	/** 積分下限 */
	public void setPointDown(int pointDown) {
		this.pointDown = pointDown;
	}

	/** 積分上限 */
	public int getPointUp() {
		return pointUp;
	}

	/** 積分上限 */
	public void setPointUp(int pointUp) {
		this.pointUp = pointUp;
	}

	/** 大段位 1萌新小白 2初級入門 3探險學徒 4中級歷練 5小有所成 6冒險開始 7高級達人 8消除大牛 */
	public int getGradingTpye() {
		return gradingTpye;
	}

	/** 大段位 1萌新小白 2初級入門 3探險學徒 4中級歷練 5小有所成 6冒險開始 7高級達人 8消除大牛 */
	public void setGradingTpye(int gradingTpye) {
		this.gradingTpye = gradingTpye;
	}

	/** 段位元等級 */
	public int getLevel() {
		return level;
	}

	/** 段位元等級 */
	public void setLevel(int level) {
		this.level = level;
	}

	/** 星星上限 */
	public int getStarLimit() {
		return starLimit;
	}

	/** 星星上限 */
	public void setStarLimit(int starLimit) {
		this.starLimit = starLimit;
	}

	/** 段位名稱 */
	public String getGradingName() {
		return gradingName;
	}

	/** 段位名稱 */
	public void setGradingName(String gradingName) {
		this.gradingName = gradingName;
	}

	/** 賽季段位獎勵 達成段位任務即可獲得獎勵 對應道具表ID */
	public String getReward() {
		return reward;
	}

	/** 賽季段位獎勵 達成段位任務即可獲得獎勵 對應道具表ID */
	public void setReward(String reward) {
		this.reward = reward;
	}

	/** 賽季結束後掉落積分數量 */
	public int getLossStart() {
		return lossStart;
	}

	/** 賽季結束後掉落積分數量 */
	public void setLossStart(int lossStart) {
		this.lossStart = lossStart;
	}

	/** 段位元圖示 */
	public String getIcon() {
		return icon;
	}

	/** 段位元圖示 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/** 獎勵郵件標題 對應CN表，用戶端目錄下的 */
	public String getMailTitle() {
		return mailTitle;
	}

	/** 獎勵郵件標題 對應CN表，用戶端目錄下的 */
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	/** 獎勵郵件內容 對應CN表，用戶端目錄下的 */
	public String getMailContent() {
		return mailContent;
	}

	/** 獎勵郵件內容 對應CN表，用戶端目錄下的 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	/** 段位任務類型 1：組隊排位次數 2：單人排位次數 3:參與排位賽次數 4：擊殺玩家次數 */
	public String getTaskName() {
		return taskName;
	}

	/** 段位任務類型 1：組隊排位次數 2：單人排位次數 3:參與排位賽次數 4：擊殺玩家次數 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/** GM工具配段位任務類型 1：組隊排位次數 2：單人排位次數 3:參與排位賽次數 4：擊殺玩家次數 用於備註，具體由GM配置 */
	public int getTaskType() {
		return taskType;
	}

	/** GM工具配段位任務類型 1：組隊排位次數 2：單人排位次數 3:參與排位賽次數 4：擊殺玩家次數 用於備註，具體由GM配置 */
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	/** GM工具配任務需求 */
	public int getValua() {
		return valua;
	}

	/** GM工具配任務需求 */
	public void setValua(int valua) {
		this.valua = valua;
	}

	/** 段位分值 */
	public int getScore() {
		return score;
	}

	/** 段位分值 */
	public void setScore(int score) {
		this.score = score;
	}

	/** 最大等待真實玩家時間,初始等待5秒，之後用這個時間 */
	public int getWaitTime() {
		return waitTime;
	}

	/** 最大等待真實玩家時間,初始等待5秒，之後用這個時間 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	/** 賽季結束後退到哪個段位 */
	public int getEndBack() {
		return endBack;
	}

	/** 賽季結束後退到哪個段位 */
	public void setEndBack(int endBack) {
		this.endBack = endBack;
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

			// 積分下限
			if (key.equals("pointDown")) {
				if (value == null || "".equals(value.trim())) {
					this.pointDown = 0;
				} else {
					this.pointDown = Integer.parseInt(value);
				}
			}

			// 積分上限
			if (key.equals("pointUp")) {
				if (value == null || "".equals(value.trim())) {
					this.pointUp = 0;
				} else {
					this.pointUp = Integer.parseInt(value);
				}
			}

			// 大段位 1萌新小白 2初級入門 3探險學徒 4中級歷練 5小有所成 6冒險開始 7高級達人 8消除大牛
			if (key.equals("gradingTpye")) {
				if (value == null || "".equals(value.trim())) {
					this.gradingTpye = 0;
				} else {
					this.gradingTpye = Integer.parseInt(value);
				}
			}

			// 段位元等級
			if (key.equals("level")) {
				if (value == null || "".equals(value.trim())) {
					this.level = 0;
				} else {
					this.level = Integer.parseInt(value);
				}
			}

			// 星星上限
			if (key.equals("starLimit")) {
				if (value == null || "".equals(value.trim())) {
					this.starLimit = 0;
				} else {
					this.starLimit = Integer.parseInt(value);
				}
			}

			// 段位名稱
			if (key.equals("gradingName")) {
				this.gradingName = value;
			}

			// 賽季段位獎勵 達成段位任務即可獲得獎勵 對應道具表ID
			if (key.equals("reward")) {
				this.reward = value;
			}

			// 賽季結束後掉落積分數量
			if (key.equals("lossStart")) {
				if (value == null || "".equals(value.trim())) {
					this.lossStart = 0;
				} else {
					this.lossStart = Integer.parseInt(value);
				}
			}

			// 段位元圖示
			if (key.equals("icon")) {
				this.icon = value;
			}

			// 獎勵郵件標題 對應CN表，用戶端目錄下的
			if (key.equals("mailTitle")) {
				this.mailTitle = value;
			}

			// 獎勵郵件內容 對應CN表，用戶端目錄下的
			if (key.equals("mailContent")) {
				this.mailContent = value;
			}

			// 段位任務類型 1：組隊排位次數 2：單人排位次數 3:參與排位賽次數 4：擊殺玩家次數
			if (key.equals("taskName")) {
				this.taskName = value;
			}

			// GM工具配段位任務類型 1：組隊排位次數 2：單人排位次數 3:參與排位賽次數 4：擊殺玩家次數 用於備註，具體由GM配置
			if (key.equals("taskType")) {
				if (value == null || "".equals(value.trim())) {
					this.taskType = 0;
				} else {
					this.taskType = Integer.parseInt(value);
				}
			}

			// GM工具配任務需求
			if (key.equals("valua")) {
				if (value == null || "".equals(value.trim())) {
					this.valua = 0;
				} else {
					this.valua = Integer.parseInt(value);
				}
			}

			// 段位分值
			if (key.equals("score")) {
				if (value == null || "".equals(value.trim())) {
					this.score = 0;
				} else {
					this.score = Integer.parseInt(value);
				}
			}

			// 最大等待真實玩家時間,初始等待5秒，之後用這個時間
			if (key.equals("waitTime")) {
				if (value == null || "".equals(value.trim())) {
					this.waitTime = 0;
				} else {
					this.waitTime = Integer.parseInt(value);
				}
			}

			// 賽季結束後退到哪個段位
			if (key.equals("endBack")) {
				if (value == null || "".equals(value.trim())) {
					this.endBack = 0;
				} else {
					this.endBack = Integer.parseInt(value);
				}
			}
		}
	}
}