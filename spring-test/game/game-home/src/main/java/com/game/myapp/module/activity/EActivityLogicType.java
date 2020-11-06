package com.game.myapp.module.activity;

import java.util.HashMap;
import java.util.Map;

import com.game.myapp.module.activity.handler.ActivityBaseHandler;
import com.game.myapp.module.activity.handler.ActivityBattlePveWinningStreak1Handler;

public enum EActivityLogicType {
	RECHARGE_ACCUMULATIVE(1, new ActivityBaseHandler()), // 累计充值
	RECHARGE_DAILY(2, new ActivityBaseHandler()), // 每日充值
	CONSUME_ACCUMULATIVE(3, new ActivityBaseHandler()), // 累计消耗钻石
	CONSUME_DAILY(4, new ActivityBaseHandler()), // 每日消费钻石
	BATTLE_DAILY(5, new ActivityBaseHandler()), // 每日对战
	BATTLE_ACCUMULATIVE(6, new ActivityBaseHandler()), // 累计对战
	BATTLE_PVE_WINNING_STREAK_1(7, new ActivityBattlePveWinningStreak1Handler()), // 连胜活动（清零）
	BATTLE_PVE_WINNING_STREAK_2(8, new ActivityBaseHandler()),// 连胜活动（降级）

	;

	private static Map<Integer, EActivityLogicType> valueMap = new HashMap<Integer, EActivityLogicType>();

	static {
		for (EActivityLogicType typeEnum : EActivityLogicType.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;

	private final IActivityHandler handler;

	EActivityLogicType(int type, IActivityHandler handler) {
		this.type = type;
		this.handler = handler;
	}

	public static EActivityLogicType valuesOf(int type) {
		return valueMap.get(type);
	}

	public int getType() {
		return type;
	}

	public IActivityHandler getHandler() {
		return handler;
	}
}
