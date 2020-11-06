package com.game.myapp.module.share;

import java.util.HashMap;
import java.util.Map;

public enum ShareResetType {
	NEVER(1), // 不重置
	DAILY(2), // 每日重置

	;

	private static Map<Integer, ShareResetType> valueMap = new HashMap<Integer, ShareResetType>();

	static {
		for (ShareResetType typeEnum : ShareResetType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;

	ShareResetType(int type) {
		this.type = type;
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public boolean is(ShareResetType shareResetType) {
		return this == shareResetType;
	}

	public static ShareResetType valuesOf(int type) {
		return valueMap.get(type);
	}

	public int getType() {
		return type;
	}
}
