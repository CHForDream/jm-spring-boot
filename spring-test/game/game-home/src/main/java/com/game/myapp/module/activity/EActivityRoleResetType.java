package com.game.myapp.module.activity;

import java.util.HashMap;
import java.util.Map;

public enum EActivityRoleResetType {
	NEVER(1), // 不重置
	DAILY(2), // 每日重置，每天0点
	WEEKLY(3), // 每周重置，每周一0点
	ACTIVITY_OPEN(4), // 活动开启时，即每次活动开启重置一次

	;

	private static Map<Integer, EActivityRoleResetType> valueMap = new HashMap<Integer, EActivityRoleResetType>();

	static {
		for (EActivityRoleResetType typeEnum : EActivityRoleResetType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;

	EActivityRoleResetType(int type) {
		this.type = type;
	}

	public static EActivityRoleResetType valuesOf(int type) {
		return valueMap.get(type);
	}

	public int getType() {
		return type;
	}
}
