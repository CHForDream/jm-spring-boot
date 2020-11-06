package com.game.myapp.module.activity;

import java.util.HashMap;
import java.util.Map;

public enum EActivityTimeType {
	FOREVER(1), // 永久，无时间限制
	START_END(2), // 起始时间+结束时间
	CYCLE(3), // 按照一定周期规则循环。（cron表达式）例：每周一，每周六日，每周，每天，。。。
	ROLE_CREATE(4), // 创建账号开始+结束条件
//	GAME_START(5), // 游戏开服开始+结束条件(暂时停用)

	;

	private static Map<Integer, EActivityTimeType> valueMap = new HashMap<Integer, EActivityTimeType>();

	static {
		for (EActivityTimeType typeEnum : EActivityTimeType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;

	EActivityTimeType(int type) {
		this.type = type;
	}

	public static EActivityTimeType valuesOf(int type) {
		return valueMap.get(type);
	}

	public int getType() {
		return type;
	}
}
