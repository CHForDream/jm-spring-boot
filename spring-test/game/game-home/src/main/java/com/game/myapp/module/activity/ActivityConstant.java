package com.game.myapp.module.activity;

public interface ActivityConstant {
	// 活动类型
	public static final int ACTIVITY_TYPE_ALL = 0;// 全部活动
	public static final int ACTIVITY_TYPE_RECHARFE_SUM = 1;// 累计充值
	public static final int ACTIVITY_TYPE_RECHARGE_DAILY = 2;// 每日充值
	public static final int ACTIVITY_TYPE_CONSUME_SUM = 3;// 累计消耗钻石
	public static final int ACTIVITY_TYPE_CONSUME_DAILY = 4;// 每日消费钻石
	public static final int ACTIVITY_TYPE_BATTLE_DAILY = 5;// 每日对战
	public static final int ACTIVITY_TYPE_BATTLE_SUM = 6;// 累计对战
	public static final int ACTIVITY_TYPE_BATTLE_PVE_WINNING_STREAK_1 = 7;// 连胜活动（清零）
	public static final int ACTIVITY_TYPE_BATTLE_PVE_WINNING_STREAK_2 = 8;// 连胜活动（降级）

	// 活动状态
	public static final int ACITIVITY_STATE_NOT_START = 0;
	public static final int ACITIVITY_STATE_START = 1;
	public static final int ACITIVITY_STATE_END = 2;

	// 活动奖励状态
	public static final int NOT_REWARD = 0;// 不可领取
	public static final int CAN_REWARD = 1;// 可领取
	public static final int ALREADY_REWARD = 2;// 已领取
	public static final int ACTIVITY_CLOSE = 3;// 活动已关闭;
	public static final int REWARD_SUCCESS = 4;// 成功
}
