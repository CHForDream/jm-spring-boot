package com.game.myapp.module.battle;

import java.util.HashMap;
import java.util.Map;

public enum BattleType {
	PVE_MAIN(0), // PVE通关主线
	PVE_DUP(1), // PVE副本
	PVE_ELITE(2), // PVE精英副本

	;

	private static Map<Integer, BattleType> valueMap = new HashMap<Integer, BattleType>();

	static {
		for (BattleType typeEnum : BattleType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;

	BattleType(int type) {
		this.type = type;
	}

	public static BattleType valueOf(int type) {
		return valueMap.get(type);
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public boolean is(BattleType type) {
		return this == type;
	}

	public int getType() {
		return type;
	}
}
