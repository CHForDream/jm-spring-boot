package com.game.myapp.module.achieve;

import java.util.HashMap;
import java.util.Map;

import com.game.myapp.module.redhat.RedHatBehavior;

public enum AchieveType {
	A_TYPE_UP(1, RedHatBehavior.ACHIEVEMENT_UPGRADE), // 成长
	A_TYPE_BATTLE(2, RedHatBehavior.ACHIEVEMENT_BATTLE), // 战斗
	A_TYPE_COLLEC(3, RedHatBehavior.ACHIEVEMENT_COLLECT), // 收集

	;

	private static Map<Integer, AchieveType> valueMap = new HashMap<Integer, AchieveType>();

	static {
		for (AchieveType typeEnum : AchieveType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 成就类型 */
	private final int type;

	private final RedHatBehavior behavior;

	AchieveType(int type, RedHatBehavior behavior) {
		this.type = type;
		this.behavior = behavior;
	}

	public static AchieveType valuesOf(int type) {
		return valueMap.get(type);
	}

	public int getType() {
		return type;
	}

	public RedHatBehavior getBehavior() {
		return behavior;
	}
}
