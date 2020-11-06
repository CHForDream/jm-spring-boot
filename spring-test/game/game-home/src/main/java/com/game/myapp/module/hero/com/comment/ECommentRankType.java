package com.game.myapp.module.hero.com.comment;

import java.util.Map;

import com.google.common.collect.Maps;

public enum ECommentRankType {
	/** 时间榜 */
	TIME(1),
	/** 点赞榜 */
	FABULOUS(2),;

	private static Map<Integer, ECommentRankType> values = Maps.newHashMap();

	static {
		for (ECommentRankType value : ECommentRankType.values()) {
			values.put(value.getType(), value);
		}
	}

	public static ECommentRankType valueOf(int type) {
		return values.get(type);
	}

	private final int type;

	private ECommentRankType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	};
}
