package com.game.myapp.module.rank;

import java.util.HashMap;
import java.util.Map;

import com.game.myapp.module.rank.handler.IRankHandler;
import com.game.myapp.module.rank.handler.LadderRankHandler;

public enum RankLogicalType {
	LADDER(1, "RankLadder", new LadderRankHandler()), // 天梯排行

	;

	private static Map<Integer, RankLogicalType> valueMap = new HashMap<Integer, RankLogicalType>();

	static {
		for (RankLogicalType typeEnum : RankLogicalType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;
	private final String name;
	private final IRankHandler handler;

	RankLogicalType(int type, String name, IRankHandler handler) {
		this.type = type;
		this.name = name;
		this.handler = handler;
	}

	public static RankLogicalType valueOf(int type) {
		return valueMap.get(type);
	}

	public boolean is(RankLogicalType type) {
		return this == type;
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public IRankHandler getHandler() {
		return handler;
	}
}
