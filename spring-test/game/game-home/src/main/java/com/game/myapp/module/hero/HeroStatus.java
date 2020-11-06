package com.game.myapp.module.hero;

import java.util.HashMap;
import java.util.Map;

public enum HeroStatus {
	/** 装饰家园 */
	HOME(1);

	private static Map<Integer, HeroStatus> valueMap = new HashMap<Integer, HeroStatus>();

	static {
		for (HeroStatus value : HeroStatus.values()) {
			valueMap.put(value.getCode(), value);
		}
	}

	private final int code;

	public int getCode() {
		return code;
	}

	private HeroStatus(int code) {
		this.code = code;
	}

	public static HeroStatus valueOf(int code) {
		return valueMap.get(code);
	}
}
