package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：GuideBaseBean.java
 * <p>
 * 功能：guide.xls -> guideBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class GuideBaseBean extends BaseBean implements IInitBean {
	/** 說明 */
	private String instructions = null;
	/** 互斥項目 與當前id互斥的id */
	private String mutex = null;
	/** 下一個 */
	private int nextId = 0;
	/** 成組編制 可以跳過的組將全組填上初始id。不能跳過的組在最後一個id上填最終id,根據最終id判斷組 */
	private int groupId = 0;
	/** 跳過時返回組 非強制引導groupid，endid都要填 */
	private int endId = 0;
	/** 跳過按鈕出現時間（秒） */
	private int skipTime = 0;
	/** 開啟條件 1、首次進入遊戲 2、系統開啟 3、等級 4、累計戰鬥場次 5、完成任務 6、完成成就 7.完成某個新手引導 8.完成某個關卡 9.解鎖水晶球 10.解鎖傢俱個數 11.獲得某個英雄 12.獲得英雄的個數 13.獲得寵物的個數 14.完成某個地圖 */
	private int openType = 0;
	/** 開啟條件值 1、0 2、哪個系統 3、等級 4、場數 5、完成的任務id 6、完成的成就id 7、引導id 8.targetid 9.傢俱個數 11.英雄id 12.獲得的數量 13.獲得寵物數量 14.地圖id */
	private int openNum = 0;
	/** 是否需要伺服器主動觸發是1，否是0 */
	private int server = 0;
	/** 介面 */
	private String className = null;
	/** 按鈕 */
	private String controls = null;
	/** 引導類型 1、強制 2、非強制 */
	private int guideType = 0;
	/** 是否可以伺服器 提前完成引導 1:是 其他:否 */
	private int serverFinish = 0;
	/** 默認完成條件 1.獲得傢俱個數:1,1 2.獲得英雄的個數:2,1 3.獲得寵物的個數:3,1 */
	private String serverCondition = null;
	/** 類型： 1、純劇情 2、對話加小手 3、光圈聚過來哦 4.小手加亮圈 5.對話加箭頭 */
	private int type = 0;
	/** 是否掛起0是不掛起 1是掛起 切換介面要刮起，不切不掛 */
	private int isHang = 0;
	/** 類型 1、圖片 2、小對話 3、大對話 */
	private int poltType = 0;
	/** 人像位置 1、左 2、右 */
	private int npcLocation = 0;
	/** 名字 */
	private String npcName = null;
	/** 對話 小對話長度X個字 大對話長度Y個字 */
	private String dialogue = null;
	/** 打開面板 */
	private String openView = null;
	/** 是否自動點擊 */
	private int isAutoClick = 0;
	/** 是否手動觸發 */
	private int isHandleTrigger = 0;
	/** 是否添加遮罩0是1否 */
	private int showMask = 0;
	/** 劇情選擇 id1,選項文字1; id2,選項文字2; id3,選項文字3; */
	private String talkSelect = null;
	/** 是否用劇情對話是為1否為0 */
	private int story = 0;
	/** 是否觸發立即完成 */
	private int endImmediately = 0;

	/** 說明 */
	public String getInstructions() {
		return instructions;
	}

	/** 說明 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/** 互斥項目 與當前id互斥的id */
	public String getMutex() {
		return mutex;
	}

	/** 互斥項目 與當前id互斥的id */
	public void setMutex(String mutex) {
		this.mutex = mutex;
	}

	/** 下一個 */
	public int getNextId() {
		return nextId;
	}

	/** 下一個 */
	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	/** 成組編制 可以跳過的組將全組填上初始id。不能跳過的組在最後一個id上填最終id,根據最終id判斷組 */
	public int getGroupId() {
		return groupId;
	}

	/** 成組編制 可以跳過的組將全組填上初始id。不能跳過的組在最後一個id上填最終id,根據最終id判斷組 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/** 跳過時返回組 非強制引導groupid，endid都要填 */
	public int getEndId() {
		return endId;
	}

	/** 跳過時返回組 非強制引導groupid，endid都要填 */
	public void setEndId(int endId) {
		this.endId = endId;
	}

	/** 跳過按鈕出現時間（秒） */
	public int getSkipTime() {
		return skipTime;
	}

	/** 跳過按鈕出現時間（秒） */
	public void setSkipTime(int skipTime) {
		this.skipTime = skipTime;
	}

	/** 開啟條件 1、首次進入遊戲 2、系統開啟 3、等級 4、累計戰鬥場次 5、完成任務 6、完成成就 7.完成某個新手引導 8.完成某個關卡 9.解鎖水晶球 10.解鎖傢俱個數 11.獲得某個英雄 12.獲得英雄的個數 13.獲得寵物的個數 14.完成某個地圖 */
	public int getOpenType() {
		return openType;
	}

	/** 開啟條件 1、首次進入遊戲 2、系統開啟 3、等級 4、累計戰鬥場次 5、完成任務 6、完成成就 7.完成某個新手引導 8.完成某個關卡 9.解鎖水晶球 10.解鎖傢俱個數 11.獲得某個英雄 12.獲得英雄的個數 13.獲得寵物的個數 14.完成某個地圖 */
	public void setOpenType(int openType) {
		this.openType = openType;
	}

	/** 開啟條件值 1、0 2、哪個系統 3、等級 4、場數 5、完成的任務id 6、完成的成就id 7、引導id 8.targetid 9.傢俱個數 11.英雄id 12.獲得的數量 13.獲得寵物數量 14.地圖id */
	public int getOpenNum() {
		return openNum;
	}

	/** 開啟條件值 1、0 2、哪個系統 3、等級 4、場數 5、完成的任務id 6、完成的成就id 7、引導id 8.targetid 9.傢俱個數 11.英雄id 12.獲得的數量 13.獲得寵物數量 14.地圖id */
	public void setOpenNum(int openNum) {
		this.openNum = openNum;
	}

	/** 是否需要伺服器主動觸發是1，否是0 */
	public int getServer() {
		return server;
	}

	/** 是否需要伺服器主動觸發是1，否是0 */
	public void setServer(int server) {
		this.server = server;
	}

	/** 介面 */
	public String getClassName() {
		return className;
	}

	/** 介面 */
	public void setClassName(String className) {
		this.className = className;
	}

	/** 按鈕 */
	public String getControls() {
		return controls;
	}

	/** 按鈕 */
	public void setControls(String controls) {
		this.controls = controls;
	}

	/** 引導類型 1、強制 2、非強制 */
	public int getGuideType() {
		return guideType;
	}

	/** 引導類型 1、強制 2、非強制 */
	public void setGuideType(int guideType) {
		this.guideType = guideType;
	}

	/** 是否可以伺服器 提前完成引導 1:是 其他:否 */
	public int getServerFinish() {
		return serverFinish;
	}

	/** 是否可以伺服器 提前完成引導 1:是 其他:否 */
	public void setServerFinish(int serverFinish) {
		this.serverFinish = serverFinish;
	}

	/** 默認完成條件 1.獲得傢俱個數:1,1 2.獲得英雄的個數:2,1 3.獲得寵物的個數:3,1 */
	public String getServerCondition() {
		return serverCondition;
	}

	/** 默認完成條件 1.獲得傢俱個數:1,1 2.獲得英雄的個數:2,1 3.獲得寵物的個數:3,1 */
	public void setServerCondition(String serverCondition) {
		this.serverCondition = serverCondition;
	}

	/** 類型： 1、純劇情 2、對話加小手 3、光圈聚過來哦 4.小手加亮圈 5.對話加箭頭 */
	public int getType() {
		return type;
	}

	/** 類型： 1、純劇情 2、對話加小手 3、光圈聚過來哦 4.小手加亮圈 5.對話加箭頭 */
	public void setType(int type) {
		this.type = type;
	}

	/** 是否掛起0是不掛起 1是掛起 切換介面要刮起，不切不掛 */
	public int getIsHang() {
		return isHang;
	}

	/** 是否掛起0是不掛起 1是掛起 切換介面要刮起，不切不掛 */
	public void setIsHang(int isHang) {
		this.isHang = isHang;
	}

	/** 類型 1、圖片 2、小對話 3、大對話 */
	public int getPoltType() {
		return poltType;
	}

	/** 類型 1、圖片 2、小對話 3、大對話 */
	public void setPoltType(int poltType) {
		this.poltType = poltType;
	}

	/** 人像位置 1、左 2、右 */
	public int getNpcLocation() {
		return npcLocation;
	}

	/** 人像位置 1、左 2、右 */
	public void setNpcLocation(int npcLocation) {
		this.npcLocation = npcLocation;
	}

	/** 名字 */
	public String getNpcName() {
		return npcName;
	}

	/** 名字 */
	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}

	/** 對話 小對話長度X個字 大對話長度Y個字 */
	public String getDialogue() {
		return dialogue;
	}

	/** 對話 小對話長度X個字 大對話長度Y個字 */
	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}

	/** 打開面板 */
	public String getOpenView() {
		return openView;
	}

	/** 打開面板 */
	public void setOpenView(String openView) {
		this.openView = openView;
	}

	/** 是否自動點擊 */
	public int getIsAutoClick() {
		return isAutoClick;
	}

	/** 是否自動點擊 */
	public void setIsAutoClick(int isAutoClick) {
		this.isAutoClick = isAutoClick;
	}

	/** 是否手動觸發 */
	public int getIsHandleTrigger() {
		return isHandleTrigger;
	}

	/** 是否手動觸發 */
	public void setIsHandleTrigger(int isHandleTrigger) {
		this.isHandleTrigger = isHandleTrigger;
	}

	/** 是否添加遮罩0是1否 */
	public int getShowMask() {
		return showMask;
	}

	/** 是否添加遮罩0是1否 */
	public void setShowMask(int showMask) {
		this.showMask = showMask;
	}

	/** 劇情選擇 id1,選項文字1; id2,選項文字2; id3,選項文字3; */
	public String getTalkSelect() {
		return talkSelect;
	}

	/** 劇情選擇 id1,選項文字1; id2,選項文字2; id3,選項文字3; */
	public void setTalkSelect(String talkSelect) {
		this.talkSelect = talkSelect;
	}

	/** 是否用劇情對話是為1否為0 */
	public int getStory() {
		return story;
	}

	/** 是否用劇情對話是為1否為0 */
	public void setStory(int story) {
		this.story = story;
	}

	/** 是否觸發立即完成 */
	public int getEndImmediately() {
		return endImmediately;
	}

	/** 是否觸發立即完成 */
	public void setEndImmediately(int endImmediately) {
		this.endImmediately = endImmediately;
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

			// 說明
			if (key.equals("instructions")) {
				this.instructions = value;
			}

			// 互斥項目 與當前id互斥的id
			if (key.equals("mutex")) {
				this.mutex = value;
			}

			// 下一個
			if (key.equals("nextId")) {
				if (value == null || "".equals(value.trim())) {
					this.nextId = 0;
				} else {
					this.nextId = Integer.parseInt(value);
				}
			}

			// 成組編制 可以跳過的組將全組填上初始id。不能跳過的組在最後一個id上填最終id,根據最終id判斷組
			if (key.equals("groupId")) {
				if (value == null || "".equals(value.trim())) {
					this.groupId = 0;
				} else {
					this.groupId = Integer.parseInt(value);
				}
			}

			// 跳過時返回組 非強制引導groupid，endid都要填
			if (key.equals("endId")) {
				if (value == null || "".equals(value.trim())) {
					this.endId = 0;
				} else {
					this.endId = Integer.parseInt(value);
				}
			}

			// 跳過按鈕出現時間（秒）
			if (key.equals("skipTime")) {
				if (value == null || "".equals(value.trim())) {
					this.skipTime = 0;
				} else {
					this.skipTime = Integer.parseInt(value);
				}
			}

			// 開啟條件 1、首次進入遊戲 2、系統開啟 3、等級 4、累計戰鬥場次 5、完成任務 6、完成成就 7.完成某個新手引導 8.完成某個關卡 9.解鎖水晶球 10.解鎖傢俱個數 11.獲得某個英雄 12.獲得英雄的個數 13.獲得寵物的個數 14.完成某個地圖
			if (key.equals("openType")) {
				if (value == null || "".equals(value.trim())) {
					this.openType = 0;
				} else {
					this.openType = Integer.parseInt(value);
				}
			}

			// 開啟條件值 1、0 2、哪個系統 3、等級 4、場數 5、完成的任務id 6、完成的成就id 7、引導id 8.targetid 9.傢俱個數 11.英雄id 12.獲得的數量 13.獲得寵物數量 14.地圖id
			if (key.equals("openNum")) {
				if (value == null || "".equals(value.trim())) {
					this.openNum = 0;
				} else {
					this.openNum = Integer.parseInt(value);
				}
			}

			// 是否需要伺服器主動觸發是1，否是0
			if (key.equals("server")) {
				if (value == null || "".equals(value.trim())) {
					this.server = 0;
				} else {
					this.server = Integer.parseInt(value);
				}
			}

			// 介面
			if (key.equals("className")) {
				this.className = value;
			}

			// 按鈕
			if (key.equals("controls")) {
				this.controls = value;
			}

			// 引導類型 1、強制 2、非強制
			if (key.equals("guideType")) {
				if (value == null || "".equals(value.trim())) {
					this.guideType = 0;
				} else {
					this.guideType = Integer.parseInt(value);
				}
			}

			// 是否可以伺服器 提前完成引導 1:是 其他:否
			if (key.equals("serverFinish")) {
				if (value == null || "".equals(value.trim())) {
					this.serverFinish = 0;
				} else {
					this.serverFinish = Integer.parseInt(value);
				}
			}

			// 默認完成條件 1.獲得傢俱個數:1,1 2.獲得英雄的個數:2,1 3.獲得寵物的個數:3,1
			if (key.equals("serverCondition")) {
				this.serverCondition = value;
			}

			// 類型： 1、純劇情 2、對話加小手 3、光圈聚過來哦 4.小手加亮圈 5.對話加箭頭
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 是否掛起0是不掛起 1是掛起 切換介面要刮起，不切不掛
			if (key.equals("isHang")) {
				if (value == null || "".equals(value.trim())) {
					this.isHang = 0;
				} else {
					this.isHang = Integer.parseInt(value);
				}
			}

			// 類型 1、圖片 2、小對話 3、大對話
			if (key.equals("poltType")) {
				if (value == null || "".equals(value.trim())) {
					this.poltType = 0;
				} else {
					this.poltType = Integer.parseInt(value);
				}
			}

			// 人像位置 1、左 2、右
			if (key.equals("npcLocation")) {
				if (value == null || "".equals(value.trim())) {
					this.npcLocation = 0;
				} else {
					this.npcLocation = Integer.parseInt(value);
				}
			}

			// 名字
			if (key.equals("npcName")) {
				this.npcName = value;
			}

			// 對話 小對話長度X個字 大對話長度Y個字
			if (key.equals("dialogue")) {
				this.dialogue = value;
			}

			// 打開面板
			if (key.equals("openView")) {
				this.openView = value;
			}

			// 是否自動點擊
			if (key.equals("isAutoClick")) {
				if (value == null || "".equals(value.trim())) {
					this.isAutoClick = 0;
				} else {
					this.isAutoClick = Integer.parseInt(value);
				}
			}

			// 是否手動觸發
			if (key.equals("isHandleTrigger")) {
				if (value == null || "".equals(value.trim())) {
					this.isHandleTrigger = 0;
				} else {
					this.isHandleTrigger = Integer.parseInt(value);
				}
			}

			// 是否添加遮罩0是1否
			if (key.equals("showMask")) {
				if (value == null || "".equals(value.trim())) {
					this.showMask = 0;
				} else {
					this.showMask = Integer.parseInt(value);
				}
			}

			// 劇情選擇 id1,選項文字1; id2,選項文字2; id3,選項文字3;
			if (key.equals("talkSelect")) {
				this.talkSelect = value;
			}

			// 是否用劇情對話是為1否為0
			if (key.equals("story")) {
				if (value == null || "".equals(value.trim())) {
					this.story = 0;
				} else {
					this.story = Integer.parseInt(value);
				}
			}

			// 是否觸發立即完成
			if (key.equals("endImmediately")) {
				if (value == null || "".equals(value.trim())) {
					this.endImmediately = 0;
				} else {
					this.endImmediately = Integer.parseInt(value);
				}
			}
		}
	}
}