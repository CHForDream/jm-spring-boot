package com.game.myapp.module.item;

import java.util.HashMap;
import java.util.Map;

public enum EItemType {
	ITEM(3), // 道具
	GIFT(4), // 礼品
	HERO(10), // 英雄

	;

	private static Map<Integer, EItemType> valueMap = new HashMap<Integer, EItemType>();

	static {
		for (EItemType typeEnum : EItemType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;

	EItemType(int type) {
		this.type = type;
	}

	public static EItemType valueOf(int type) {
		return valueMap.get(type);
	}

	public boolean is(EItemType type) {
		return this == type;
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public int getType() {
		return type;
	}
}
