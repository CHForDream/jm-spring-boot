package com.game.myapp.module.guide;

import java.util.HashMap;
import java.util.Map;

public enum EGuidePreFinishType {
	HOME_DECORATION_NUM(1), // 获得傢俱個數
	HERO_NUM(2), // 获得英雄的个数
	HERO_DECORATION_NUM(3), // 获得宠物的个数

	;

	private static Map<Integer, EGuidePreFinishType> valueMap = new HashMap<Integer, EGuidePreFinishType>();

	static {
		for (EGuidePreFinishType typeEnum : EGuidePreFinishType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;

	EGuidePreFinishType(int type) {
		this.type = type;
	}

	public static EGuidePreFinishType valueOf(int type) {
		return valueMap.get(type);
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public boolean is(EGuidePreFinishType type) {
		return this == type;
	}

	public int getType() {
		return type;
	}
}
