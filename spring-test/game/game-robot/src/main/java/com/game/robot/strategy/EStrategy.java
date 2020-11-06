package com.game.robot.strategy;

import java.util.HashMap;
import java.util.Map;

public enum EStrategy {
	// 基础
	HEARTBEAT_HTTP(1, StrategyFactory.HeartbeatLoopStrategyFactory), // 逻辑服心跳
	HEARTBEAT_CHAT(2, StrategyFactory.HeartbeatChatLoopStrategyFactory), // 聊天服心跳

	// UI
	SIGN_ONCE(1000, StrategyFactory.SignOnceStrategyFactory), // 每日签到(单次)
	SIGN_LOOP(1001, StrategyFactory.SignLoopStrategyFactory), // 每日签到(循环)
	ROLE_SETTING(1002, StrategyFactory.RoleOwnSettingStrategyFactory), // 点击我
	HERO_LISTGET(1003, StrategyFactory.HeroListGetStrategyFactory), // 点击伙伴
	STORE_INFO(1004, StrategyFactory.StoreInfoStrategyFactory), // 打开商城
	STORE_LUCKDRAW(1005, StrategyFactory.StoreLuckDrawStrategyFactory), // 打开夺宝界面并抽奖
	STORE_SHOPBUY(1006, StrategyFactory.StoreShopBuyStrategyFactory), // 在商场购买物品（道具，英雄，宠物等）
	ACHIEVE_LIST(1008, StrategyFactory.AchieveStrategyFactory), // 成就界面
	TASK_DAY(1009, StrategyFactory.TaskDayStrategyFactory), // 任务界面

	PAY_FIRST(1100, StrategyFactory.PayFirstStrategyFactory), // 首充界面
	RANK_INFO(1101, StrategyFactory.RankInfoStrategyFactory), // 排行界面
	MAIL_LIST(1102, StrategyFactory.MailListStrategyFactory), // 邮件界面
	ITEM_BAG(1103, StrategyFactory.ItemBagStrategyFactory), // 背包界面
	FRIEND_LIST(1104, StrategyFactory.FriendListStrategyFactory), // 好友界面
	FRIEND_REQUEST_LIST(1105, StrategyFactory.FriendRequestListStrategyFactory), // 好友申请界面

	PVE_CITY(1106, StrategyFactory.PveCityStrategyFactory), // 
	DUPLICATE(1107, StrategyFactory.DuplicateStrategyFactory),
	CITYACHIEVE(1108, StrategyFactory.CityAchieveStrategyFactory),

	// PVE战斗
	BATTLE_PVE(2000, StrategyFactory.BattlePveLoopStrategyFactory),

	// 精英副本
	ELITE(2100, StrategyFactory.EliteLoopStrategyFactory),

	// 压测
	STRESS_LOOP(3000, StrategyFactory.StressLoopStrategyFactory),

	;

	private static Map<Integer, EStrategy> valueMap = new HashMap<Integer, EStrategy>();

	private final static EStrategy[] randomEStrategyArray = { SIGN_ONCE, SIGN_LOOP, ROLE_SETTING, HERO_LISTGET, STORE_INFO, STORE_LUCKDRAW, STORE_SHOPBUY,
			ACHIEVE_LIST, TASK_DAY, PAY_FIRST, RANK_INFO, MAIL_LIST, ITEM_BAG, FRIEND_REQUEST_LIST, };

	static {
		for (EStrategy typeEnum : EStrategy.values()) {
			valueMap.put(typeEnum.getType(), typeEnum);
		}
	}

	/** 类型 */
	private final int type;
	/** 测试用例工厂类 */
	private final IStrategyFactory strategyFactory;

	EStrategy(int type, IStrategyFactory strategyFactory) {
		this.type = type;
		this.strategyFactory = strategyFactory;
	}

	public static EStrategy valueOf(int type) {
		return valueMap.get(type);
	}

	public static EStrategy[] getRandomEStrategyArray() {
		return randomEStrategyArray;
	}

	public boolean is(int type) {
		return this.type == type;
	}

	public boolean is(EStrategy shareType) {
		return this == shareType;
	}

	public int getType() {
		return type;
	}

	public IStrategyFactory getStrategyFactory() {
		return strategyFactory;
	}
}
