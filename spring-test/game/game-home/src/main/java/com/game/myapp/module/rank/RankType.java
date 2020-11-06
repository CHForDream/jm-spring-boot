package com.game.myapp.module.rank;

import java.util.HashMap;
import java.util.Map;

import com.game.core.redis.RedisConnFactory;

public enum RankType {
	LADDER_1(1, RankLogicalType.LADDER, 20), // 天梯第1关排行
	LADDER_2(2, RankLogicalType.LADDER, 20), // 天梯第2关排行
	LADDER_3(3, RankLogicalType.LADDER, 20), // 天梯第3关排行
	LADDER_4(4, RankLogicalType.LADDER, 20), // 天梯第4关排行
	LADDER_5(5, RankLogicalType.LADDER, 20), // 天梯第5关排行
	LADDER_6(6, RankLogicalType.LADDER, 20), // 天梯第6关排行

	;

	private static Map<Integer, RankType> valueMap = new HashMap<Integer, RankType>();

	static {
		for (RankType typeEnum : RankType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;
	private final RankLogicalType logicalType;
	private final int topNum;

	RankType(int type, RankLogicalType logicalType, int topNum) {
		this.type = type;
		this.logicalType = logicalType;
		this.topNum = topNum;
	}

	public static RankType valueOf(int type) {
		return valueMap.get(type);
	}

	public String getRedisKey(String subType) {
		return RedisConnFactory.GAME_FOLDER_RANK + ":" + logicalType.getName() + "-" + subType;
	}

	public String getName() {
		return logicalType.getName();
	}

	public boolean isLadder() {
		return logicalType.is(RankLogicalType.LADDER);
	}

	public int getType() {
		return type;
	}

	public RankLogicalType getLogicalType() {
		return logicalType;
	}

	public int getTopNum() {
		return topNum;
	}
}
