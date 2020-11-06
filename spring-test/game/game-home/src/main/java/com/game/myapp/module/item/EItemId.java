package com.game.myapp.module.item;

import java.util.HashMap;
import java.util.Map;

public enum EItemId {
	UNKNOWN(0, true), // 未知

	// 货币
	REWARD_CASH(1, false), // 产出钻石
	COIN(3, false), // 银币
	RECHARGE_CASH(4, false), // 充值钻石
	DUPSTAR(5, false), // 副本收集的星星数量
	POWER(7, false), // 体力
	ACTIVE(11, false), // 任务活跃度

	// 道具
	POWER_SMALL(33, true), // 体力药
	POWER_NO_LIMIT(34, true), // 无限体力
	PVE_ADD_STEP_CARD(35, true), // pve增加步数卡

	MONTH_EXPERIENCE_CARD(500, true), // 月卡体验卡(3天)
	CHANGE_NAME_CARD(501, true), // 改名卡

	ELITE_PRE_BATTLE_ITEM_ADD_STEP(603, true), // 精英副本战前道具:加步数

	;

	private static Map<Integer, EItemId> valueMap = new HashMap<Integer, EItemId>();

	static {
		for (EItemId typeEnum : EItemId.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;
	/** 是否是道具 */
	private final boolean isItem;

	EItemId(int type, boolean isItem) {
		this.type = type;
		this.isItem = isItem;
	}

	public static EItemId valueOf(int type) {
		return valueMap.get(type);
	}

	public static EItemId valueOfOrDefault(int type, EItemId itemId) {
		return valueMap.getOrDefault(type, itemId);
	}

	public boolean is(EItemId type) {
		return this == type;
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public int getType() {
		return type;
	}

	public boolean isItem() {
		return isItem;
	}
}
