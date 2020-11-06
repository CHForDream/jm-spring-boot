package com.game.common.logf;

import java.util.HashMap;
import java.util.Map;

public enum LogfType {
	// APP PVE战斗结束
	BATTLE_PVE_COMPLETE_APP(LogfConstants.PVE_COMPLETE_APP, LogfConstants.LOG_TYPE_BATTLE),
	// APP pve开始战斗
	BATTLE_PVE_START_APP(LogfConstants.PVE_START_APP, LogfConstants.LOG_TYPE_BATTLE),
	// APP 副本战斗结束
	BATTLE_DUP_COMPLETE_APP(LogfConstants.DUP_COMPLETE_APP, LogfConstants.LOG_TYPE_BATTLE),
	// APP 副本开始战斗
	BATTLE_DUP_START_APP(LogfConstants.DUP_START_APP, LogfConstants.LOG_TYPE_BATTLE),

	// 触发引导
	GUIDE_TRIGGER(LogfConstants.GUIDE_TRIGGER, LogfConstants.LOG_TYPE_GUIDE),
	//结束引导
	GUIDE_COMPLETE(LogfConstants.GUIDE_COMPLETE, LogfConstants.LOG_TYPE_GUIDE),

	// 获得英雄
	HERO_INSERT(LogfConstants.INSERT_HERO, LogfConstants.LOG_TYPE_ITEM),

	// 抽奖
	LUCK_DRAW(LogfConstants.LUCK_DRAW, LogfConstants.LOG_TYPE_TASK),

	// 成就升级
	ACHIEVEMENT_UPGRADE(LogfConstants.ACHIEVEMENT_UPGRADE, LogfConstants.LOG_TYPE_ACHIEVEMENT),
	// 成就达成
	ACHIEVEMENT_COMPLETE(LogfConstants.ACHIEVEMENT_COMPLETE, LogfConstants.LOG_TYPE_ACHIEVEMENT),

	// 活动达成
	ACTIVITY_COMPLETE(LogfConstants.COMPLETE_ACTIVITY, LogfConstants.LOG_TYPE_ACTIVITY),
	// 领取活动奖励
	ACTIVITY_RECEIVE(LogfConstants.RECEIVE_ACTIVITY, LogfConstants.LOG_TYPE_ACTIVITY),

	// GM指令
	GM_TYPE(LogfConstants.GM_TYPE, LogfConstants.LOG_TYPE_PAY),

	// 获得物品
	ITEM_ADD(LogfConstants.ADD_ITEM, LogfConstants.LOG_TYPE_ITEM),
	// 消耗物品
	ITEM_COST(LogfConstants.COST_ITEM, LogfConstants.LOG_TYPE_ITEM),
	// 商店购买
	ITEM_BUY(LogfConstants.BUY_ITEM, LogfConstants.LOG_TYPE_ITEM),
	// 货币消耗
	CURRENCY_COST(LogfConstants.COST_CURRENCY, LogfConstants.LOG_TYPE_ITEM),

	// 聊天
	CHAT_TYPE(LogfConstants.CHAT_TYPE, LogfConstants.LOG_TYPE_CHAT),

	// 角色登录
	ROLE_LOGIN(LogfConstants.LOGIN_TYPE, LogfConstants.LOG_TYPE_LOGIN),
	// 角色退出
	ROLE_LOGOUT(LogfConstants.LOGOUT_TYPE, LogfConstants.LOG_TYPE_LOGIN),
	// 角色注册
	ROLE_REGISTER(LogfConstants.REGISTER_TYPE, LogfConstants.LOG_TYPE_LOGIN),

	// 充值
	PAY_RECEIVE(LogfConstants.PAY_RECEIVE, LogfConstants.LOG_TYPE_PAY),
	// 充值成功
	PAY_SUCCESS(LogfConstants.PAY_SUCCESS, LogfConstants.LOG_TYPE_PAY),

	// 七日签到
	ACTIVITY_SEVEN_SIGN(LogfConstants.SEVEN_SIGN, LogfConstants.LOG_TYPE_ACTIVITY),
	// 月签到
	ACTIVITY_MONTH_SIGN(LogfConstants.MONTH_SIGN, LogfConstants.LOG_TYPE_ACTIVITY),

	// 完成每日任务
	TASK_COMPLETE(LogfConstants.DAILY_TASK_COMPLETE, LogfConstants.LOG_TYPE_TASK),
	// 更新每日任务活跃度
	TASK_POINT_UPDATE(LogfConstants.DAILY_TASK_POINT_UPDATE, LogfConstants.LOG_TYPE_TASK),
	//领取活跃宝箱
	TASK_RECEIVE_BOX(LogfConstants.DAILY_TASK_RECEIVE_BOX, LogfConstants.LOG_TYPE_TASK),

	// 添加好友
	FRIEND_ADD(LogfConstants.FRIEND_ADD, LogfConstants.LOG_TYPE_FRIEND),
	// 好友点赞
	FRIEND_FABULOUS(LogfConstants.FRIEND_FABULOUS, LogfConstants.LOG_TYPE_FRIEND),

	;

	private static Map<String, LogfType> valueMap = new HashMap<String, LogfType>();

	static {
		for (LogfType logfType : LogfType.values()) {
			valueMap.put(logfType.getLogfType(), logfType);
		}
	}

	/** 打点日志字符串类型 */
	private final String logfType;
	/** 日志存储文件夹 */
	private final String logfFile;

	LogfType(String logfType, String logfPath) {
		this.logfType = logfType;
		this.logfFile = logfPath;
	}

	public static LogfType typeOf(String logfType) {
		return valueMap.get(logfType);
	}

	public String getLogfType() {
		return logfType;
	}

	public String getLogfFile() {
		return logfFile;
	}
}
