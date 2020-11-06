package com.game.generate.bean.base;

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：TargetBaseBean.java
 * <p>
 * 功能：target.xls -> targetBaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class TargetBaseBean extends BaseBean implements IInitBean {
	/** 步數 */
	private int demand = 0;
	/** 任務獎勵 drop.xls表掉落組ID個數是走drop表 權重,掉落庫,次數;權重,掉落庫,次數, */
	private String reward = null;
	/** 任務對應地圖 */
	private String taskMap = null;
	/** 初始化棋子 權重1,棋子ID1;權重2,棋子2……（棋子ID與權重用“,”隔開,棋子之間用“;”隔開） */
	private String init = null;
	/** 生成棋子權重（掉落） g0#棋子掉落權重&g1#棋子掉落權重& 如果只掉落一個棋子,權重寫1 */
	private String chessdrop = null;
	/** 生成同樣顏色棋子的幾率 （0最小,降低難度需往上加最大為10000） */
	private int sameColour = 0;
	/** 全場蝴蝶最多掉落 */
	private int butterFlyMaxNum = 0;
	/** 對AI時間的修正X/1000 */
	private int aiTime = 0;
	/** AI行為偏移量/原有*偏移值（偏移值除以1000來用） */
	private int robOffset = 0;
	/** 地圖使用類型 用於stage表使用 (0不計入隨機池） */
	private int type = 0;
	/** 測試修改關卡目標最後不要加分隔符號號號（文本格式） Target表格關卡目標 調整為typ,icon,count Typ--1.小車,4,PVP小車 1,棋子id,數量;1,棋子id,數量;······· 2,id#id#id#id,icon,數量 3,id;3,id;3,id ... */
	private String conditions = null;
	/** 關卡等級 （PVP時,判斷條件; PVE時,關卡等級） */
	private int mapLevel = 0;
	/** loading */
	private String chessTipsBat = null;
	/** loading 文字描述 */
	private int loadingDescribe = 0;
	/** 消耗體力 */
	private int physical = 0;
	/** 關卡額外獎勵 */
	private int LevelReward = 0;
	/** 單機關卡隨機看視頻獲得道具 */
	private String randomChess = null;
	/** 小車移動所需要的目標棋子 棋子ID,數量;(只需要收集一種就能前進） */
	private String Roadchess = null;
	/** 關卡難度顯示（1.普通2.困難3.超級困難） */
	private int obstacleLeve = 0;
	/** 關卡加體力 值為1加滿,其他值(或空值)不加體力 */
	private int powerFull = 0;
	/** 是否添加重力特效 */
	private int gravityArrow = 0;

	/** 步數 */
	public int getDemand() {
		return demand;
	}

	/** 步數 */
	public void setDemand(int demand) {
		this.demand = demand;
	}

	/** 任務獎勵 drop.xls表掉落組ID個數是走drop表 權重,掉落庫,次數;權重,掉落庫,次數, */
	public String getReward() {
		return reward;
	}

	/** 任務獎勵 drop.xls表掉落組ID個數是走drop表 權重,掉落庫,次數;權重,掉落庫,次數, */
	public void setReward(String reward) {
		this.reward = reward;
	}

	/** 任務對應地圖 */
	public String getTaskMap() {
		return taskMap;
	}

	/** 任務對應地圖 */
	public void setTaskMap(String taskMap) {
		this.taskMap = taskMap;
	}

	/** 初始化棋子 權重1,棋子ID1;權重2,棋子2……（棋子ID與權重用“,”隔開,棋子之間用“;”隔開） */
	public String getInit() {
		return init;
	}

	/** 初始化棋子 權重1,棋子ID1;權重2,棋子2……（棋子ID與權重用“,”隔開,棋子之間用“;”隔開） */
	public void setInit(String init) {
		this.init = init;
	}

	/** 生成棋子權重（掉落） g0#棋子掉落權重&g1#棋子掉落權重& 如果只掉落一個棋子,權重寫1 */
	public String getChessdrop() {
		return chessdrop;
	}

	/** 生成棋子權重（掉落） g0#棋子掉落權重&g1#棋子掉落權重& 如果只掉落一個棋子,權重寫1 */
	public void setChessdrop(String chessdrop) {
		this.chessdrop = chessdrop;
	}

	/** 生成同樣顏色棋子的幾率 （0最小,降低難度需往上加最大為10000） */
	public int getSameColour() {
		return sameColour;
	}

	/** 生成同樣顏色棋子的幾率 （0最小,降低難度需往上加最大為10000） */
	public void setSameColour(int sameColour) {
		this.sameColour = sameColour;
	}

	/** 全場蝴蝶最多掉落 */
	public int getButterFlyMaxNum() {
		return butterFlyMaxNum;
	}

	/** 全場蝴蝶最多掉落 */
	public void setButterFlyMaxNum(int butterFlyMaxNum) {
		this.butterFlyMaxNum = butterFlyMaxNum;
	}

	/** 對AI時間的修正X/1000 */
	public int getAiTime() {
		return aiTime;
	}

	/** 對AI時間的修正X/1000 */
	public void setAiTime(int aiTime) {
		this.aiTime = aiTime;
	}

	/** AI行為偏移量/原有*偏移值（偏移值除以1000來用） */
	public int getRobOffset() {
		return robOffset;
	}

	/** AI行為偏移量/原有*偏移值（偏移值除以1000來用） */
	public void setRobOffset(int robOffset) {
		this.robOffset = robOffset;
	}

	/** 地圖使用類型 用於stage表使用 (0不計入隨機池） */
	public int getType() {
		return type;
	}

	/** 地圖使用類型 用於stage表使用 (0不計入隨機池） */
	public void setType(int type) {
		this.type = type;
	}

	/** 測試修改關卡目標最後不要加分隔符號號號（文本格式） Target表格關卡目標 調整為typ,icon,count Typ--1.小車,4,PVP小車 1,棋子id,數量;1,棋子id,數量;······· 2,id#id#id#id,icon,數量 3,id;3,id;3,id ... */
	public String getConditions() {
		return conditions;
	}

	/** 測試修改關卡目標最後不要加分隔符號號號（文本格式） Target表格關卡目標 調整為typ,icon,count Typ--1.小車,4,PVP小車 1,棋子id,數量;1,棋子id,數量;······· 2,id#id#id#id,icon,數量 3,id;3,id;3,id ... */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/** 關卡等級 （PVP時,判斷條件; PVE時,關卡等級） */
	public int getMapLevel() {
		return mapLevel;
	}

	/** 關卡等級 （PVP時,判斷條件; PVE時,關卡等級） */
	public void setMapLevel(int mapLevel) {
		this.mapLevel = mapLevel;
	}

	/** loading */
	public String getChessTipsBat() {
		return chessTipsBat;
	}

	/** loading */
	public void setChessTipsBat(String chessTipsBat) {
		this.chessTipsBat = chessTipsBat;
	}

	/** loading 文字描述 */
	public int getLoadingDescribe() {
		return loadingDescribe;
	}

	/** loading 文字描述 */
	public void setLoadingDescribe(int loadingDescribe) {
		this.loadingDescribe = loadingDescribe;
	}

	/** 消耗體力 */
	public int getPhysical() {
		return physical;
	}

	/** 消耗體力 */
	public void setPhysical(int physical) {
		this.physical = physical;
	}

	/** 關卡額外獎勵 */
	public int getLevelReward() {
		return LevelReward;
	}

	/** 關卡額外獎勵 */
	public void setLevelReward(int LevelReward) {
		this.LevelReward = LevelReward;
	}

	/** 單機關卡隨機看視頻獲得道具 */
	public String getRandomChess() {
		return randomChess;
	}

	/** 單機關卡隨機看視頻獲得道具 */
	public void setRandomChess(String randomChess) {
		this.randomChess = randomChess;
	}

	/** 小車移動所需要的目標棋子 棋子ID,數量;(只需要收集一種就能前進） */
	public String getRoadchess() {
		return Roadchess;
	}

	/** 小車移動所需要的目標棋子 棋子ID,數量;(只需要收集一種就能前進） */
	public void setRoadchess(String Roadchess) {
		this.Roadchess = Roadchess;
	}

	/** 關卡難度顯示（1.普通2.困難3.超級困難） */
	public int getObstacleLeve() {
		return obstacleLeve;
	}

	/** 關卡難度顯示（1.普通2.困難3.超級困難） */
	public void setObstacleLeve(int obstacleLeve) {
		this.obstacleLeve = obstacleLeve;
	}

	/** 關卡加體力 值為1加滿,其他值(或空值)不加體力 */
	public int getPowerFull() {
		return powerFull;
	}

	/** 關卡加體力 值為1加滿,其他值(或空值)不加體力 */
	public void setPowerFull(int powerFull) {
		this.powerFull = powerFull;
	}

	/** 是否添加重力特效 */
	public int getGravityArrow() {
		return gravityArrow;
	}

	/** 是否添加重力特效 */
	public void setGravityArrow(int gravityArrow) {
		this.gravityArrow = gravityArrow;
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

			// 步數
			if (key.equals("demand")) {
				if (value == null || "".equals(value.trim())) {
					this.demand = 0;
				} else {
					this.demand = Integer.parseInt(value);
				}
			}

			// 任務獎勵 drop.xls表掉落組ID個數是走drop表 權重,掉落庫,次數;權重,掉落庫,次數,
			if (key.equals("reward")) {
				this.reward = value;
			}

			// 任務對應地圖
			if (key.equals("taskMap")) {
				this.taskMap = value;
			}

			// 初始化棋子 權重1,棋子ID1;權重2,棋子2……（棋子ID與權重用“,”隔開,棋子之間用“;”隔開）
			if (key.equals("init")) {
				this.init = value;
			}

			// 生成棋子權重（掉落） g0#棋子掉落權重&g1#棋子掉落權重& 如果只掉落一個棋子,權重寫1
			if (key.equals("chessdrop")) {
				this.chessdrop = value;
			}

			// 生成同樣顏色棋子的幾率 （0最小,降低難度需往上加最大為10000）
			if (key.equals("sameColour")) {
				if (value == null || "".equals(value.trim())) {
					this.sameColour = 0;
				} else {
					this.sameColour = Integer.parseInt(value);
				}
			}

			// 全場蝴蝶最多掉落
			if (key.equals("butterFlyMaxNum")) {
				if (value == null || "".equals(value.trim())) {
					this.butterFlyMaxNum = 0;
				} else {
					this.butterFlyMaxNum = Integer.parseInt(value);
				}
			}

			// 對AI時間的修正X/1000
			if (key.equals("aiTime")) {
				if (value == null || "".equals(value.trim())) {
					this.aiTime = 0;
				} else {
					this.aiTime = Integer.parseInt(value);
				}
			}

			// AI行為偏移量/原有*偏移值（偏移值除以1000來用）
			if (key.equals("robOffset")) {
				if (value == null || "".equals(value.trim())) {
					this.robOffset = 0;
				} else {
					this.robOffset = Integer.parseInt(value);
				}
			}

			// 地圖使用類型 用於stage表使用 (0不計入隨機池）
			if (key.equals("type")) {
				if (value == null || "".equals(value.trim())) {
					this.type = 0;
				} else {
					this.type = Integer.parseInt(value);
				}
			}

			// 測試修改關卡目標最後不要加分隔符號號號（文本格式） Target表格關卡目標 調整為typ,icon,count Typ--1.小車,4,PVP小車 1,棋子id,數量;1,棋子id,數量;······· 2,id#id#id#id,icon,數量 3,id;3,id;3,id ...
			if (key.equals("conditions")) {
				this.conditions = value;
			}

			// 關卡等級 （PVP時,判斷條件; PVE時,關卡等級）
			if (key.equals("mapLevel")) {
				if (value == null || "".equals(value.trim())) {
					this.mapLevel = 0;
				} else {
					this.mapLevel = Integer.parseInt(value);
				}
			}

			// loading
			if (key.equals("chessTipsBat")) {
				this.chessTipsBat = value;
			}

			// loading 文字描述
			if (key.equals("loadingDescribe")) {
				if (value == null || "".equals(value.trim())) {
					this.loadingDescribe = 0;
				} else {
					this.loadingDescribe = Integer.parseInt(value);
				}
			}

			// 消耗體力
			if (key.equals("physical")) {
				if (value == null || "".equals(value.trim())) {
					this.physical = 0;
				} else {
					this.physical = Integer.parseInt(value);
				}
			}

			// 關卡額外獎勵
			if (key.equals("LevelReward")) {
				if (value == null || "".equals(value.trim())) {
					this.LevelReward = 0;
				} else {
					this.LevelReward = Integer.parseInt(value);
				}
			}

			// 單機關卡隨機看視頻獲得道具
			if (key.equals("randomChess")) {
				this.randomChess = value;
			}

			// 小車移動所需要的目標棋子 棋子ID,數量;(只需要收集一種就能前進）
			if (key.equals("Roadchess")) {
				this.Roadchess = value;
			}

			// 關卡難度顯示（1.普通2.困難3.超級困難）
			if (key.equals("obstacleLeve")) {
				if (value == null || "".equals(value.trim())) {
					this.obstacleLeve = 0;
				} else {
					this.obstacleLeve = Integer.parseInt(value);
				}
			}

			// 關卡加體力 值為1加滿,其他值(或空值)不加體力
			if (key.equals("powerFull")) {
				if (value == null || "".equals(value.trim())) {
					this.powerFull = 0;
				} else {
					this.powerFull = Integer.parseInt(value);
				}
			}

			// 是否添加重力特效
			if (key.equals("gravityArrow")) {
				if (value == null || "".equals(value.trim())) {
					this.gravityArrow = 0;
				} else {
					this.gravityArrow = Integer.parseInt(value);
				}
			}
		}
	}
}