package com.game.robot.strategy;

import com.game.robot.Robot;
import com.game.robot.strategy.impl.AchieveStrategy;
import com.game.robot.strategy.impl.EliteLoopStrategy;
import com.game.robot.strategy.impl.BattlePveLoopStrategy;
import com.game.robot.strategy.impl.CityAchieveStrategy;
import com.game.robot.strategy.impl.DuplicateStrategy;
import com.game.robot.strategy.impl.FriendListStrategy;
import com.game.robot.strategy.impl.FriendRequestListStrategy;
import com.game.robot.strategy.impl.HeartbeatChatLoopStrategy;
import com.game.robot.strategy.impl.HeartbeatLoopStrategy;
import com.game.robot.strategy.impl.HeroListGetStrategy;
import com.game.robot.strategy.impl.ItemBagStrategy;
import com.game.robot.strategy.impl.MailListStrategy;
import com.game.robot.strategy.impl.PayFirstStrategy;
import com.game.robot.strategy.impl.PveCityStrategy;
import com.game.robot.strategy.impl.RankInfoStrategy;
import com.game.robot.strategy.impl.RoleOwnSettingStrategy;
import com.game.robot.strategy.impl.SignLoopStrategy;
import com.game.robot.strategy.impl.SignOnceStrategy;
import com.game.robot.strategy.impl.StoreInfoStrategy;
import com.game.robot.strategy.impl.StoreLuckDrawStrategy;
import com.game.robot.strategy.impl.StoreShopBuyStrategy;
import com.game.robot.strategy.impl.StressLoopStrategy;
import com.game.robot.strategy.impl.TaskDayStrategy;

public class StrategyFactory {
	// 基础
	/** 逻辑服心跳 */
	public static IStrategyFactory HeartbeatLoopStrategyFactory = (Robot robot) -> new HeartbeatLoopStrategy(robot, 5000L);
	/** 聊天服心跳 */
	public static IStrategyFactory HeartbeatChatLoopStrategyFactory = (Robot robot) -> new HeartbeatChatLoopStrategy(robot, 10000L);

	// UI
	/** 每日签到(单次) */
	public static IStrategyFactory SignOnceStrategyFactory = (Robot robot) -> new SignOnceStrategy(robot, 3000);
	/** 每日签到(循环) */
	public static IStrategyFactory SignLoopStrategyFactory = (Robot robot) -> new SignLoopStrategy(robot, 3000, 3000, 5000);
	/** 点击我 -》 设置我的最爱伙伴 -》 设置宠物 */
	public static IStrategyFactory RoleOwnSettingStrategyFactory = (Robot robot) -> new RoleOwnSettingStrategy(robot, 3000, 3000, 5000);
	/** 打开宠物伙伴 */
	public static IStrategyFactory HeroListGetStrategyFactory = (Robot robot) -> new HeroListGetStrategy(robot, 3000, 3000, 5000);
	/** 打开商城界面 */
	public static IStrategyFactory StoreInfoStrategyFactory = (Robot robot) -> new StoreInfoStrategy(robot, 3000, 3000, 5000);
	/** 打开夺宝界面并进行抽奖 */
	public static IStrategyFactory StoreLuckDrawStrategyFactory = (Robot robot) -> new StoreLuckDrawStrategy(robot, 3000, 3000, 5000);
	/** 购买商城物品 */
	public static IStrategyFactory StoreShopBuyStrategyFactory = (Robot robot) -> new StoreShopBuyStrategy(robot, 3000, 3000, 5000);
	/** 成就 */
	public static IStrategyFactory AchieveStrategyFactory = (Robot robot) -> new AchieveStrategy(robot, 3000, 3000, 5000);
	/** 任务 */
	public static IStrategyFactory TaskDayStrategyFactory = (Robot robot) -> new TaskDayStrategy(robot, 3000, 3000, 5000);
	/** 首充界面 */
	public static IStrategyFactory PayFirstStrategyFactory = (Robot robot) -> new PayFirstStrategy(robot, 3000, 5000, 10000);
	/** 排行界面 */
	public static IStrategyFactory RankInfoStrategyFactory = (Robot robot) -> new RankInfoStrategy(robot, 3000, 5000, 10000);
	/** 邮件界面 */
	public static IStrategyFactory MailListStrategyFactory = (Robot robot) -> new MailListStrategy(robot, 3000, 5000, 10000);
	/** 背包界面 */
	public static IStrategyFactory ItemBagStrategyFactory = (Robot robot) -> new ItemBagStrategy(robot, 3000, 5000, 10000);
	/** 好友界面 */
	public static IStrategyFactory FriendListStrategyFactory = (Robot robot) -> new FriendListStrategy(robot, 3000, 5000, 10000);
	/** 好友申请界面 */
	public static IStrategyFactory FriendRequestListStrategyFactory = (Robot robot) -> new FriendRequestListStrategy(robot, 3000, 5000, 10000);
	/** 新修关卡系统 */
	public static IStrategyFactory PveCityStrategyFactory = (Robot robot) -> new PveCityStrategy(robot, 1000, 5000, 5000);
	/** 副本系统 */
	public static IStrategyFactory DuplicateStrategyFactory = (Robot robot) -> new DuplicateStrategy(robot, 1000, 3000, 5000);
	/** 城市间成就 */
	public static IStrategyFactory CityAchieveStrategyFactory = (Robot robot) -> new CityAchieveStrategy(robot, 1000, 3000, 5000);
	// 战斗
	/** PVE战斗 */
	public static IStrategyFactory BattlePveLoopStrategyFactory = (Robot robot) -> new BattlePveLoopStrategy(robot, 3000, 60000L, 90000L);

	/** 精英副本 */
	public static IStrategyFactory EliteLoopStrategyFactory = (Robot robot) -> new EliteLoopStrategy(robot, 3000, 60000L, 90000L);

	/** 压测 */
	public static IStrategyFactory StressLoopStrategyFactory = (Robot robot) -> new StressLoopStrategy(robot, 3000, 3000L, 5000L);

}
