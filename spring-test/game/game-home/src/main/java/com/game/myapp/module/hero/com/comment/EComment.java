package com.game.myapp.module.hero.com.comment;

import java.util.Map;

import com.google.common.collect.Maps;

public enum EComment {
	HERO(1),
	PETS(2),

	;

	private static Map<Integer, EComment> values = Maps.newHashMap();
	static {
		for (EComment value : EComment.values()) {
			values.put(value.getType(), value);
		}
	}

	private final int type;

	private EComment(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public static EComment valueOf(int type) {
		return values.get(type);
	}
}
