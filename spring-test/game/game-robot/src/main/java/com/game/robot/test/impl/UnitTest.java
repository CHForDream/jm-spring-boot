package com.game.robot.test.impl;

import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.test.BaseTest;

public class UnitTest extends BaseTest {

	@Override
	public void initRobotList() {
		for (int i = Config.ROBOT_START_ID; i < Config.ROBOT_START_ID + Config.ROBOT_COUNT; i++) {
			Robot robot = new Robot(i);
			getRobotList().add(robot);

//			// 登录签到(执行一次)
//			robot.addRobotStrategy(EStrategy.SIGN_ONCE);
//			// 签到(循环执行)
//			robot.addRobotStrategy(EStrategy.SIGN_LOOP);
			// 打开“我”
//			robot.addRobotStrategy(EStrategy.ROLE_SETTING);
			// 打开“伙伴”
//			robot.addRobotStrategy(EStrategy.HERO_LISTGET);
			// 打开商城
//			robot.addRobotStrategy(EStrategy.STORE_INFO);
			// 打开夺宝界面并抽奖
//			robot.addRobotStrategy(EStrategy.STORE_LUCKDRAW);
			// 在商场购买物品（道具，英雄，宠物等）
//			robot.addRobotStrategy(EStrategy.STORE_SHOPBUY);
			// 家园装饰
//			robot.addRobotStrategy(EStrategy.HOME_ATTACH_DEC);
			// 成就
//			robot.addRobotStrategy(EStrategy.ACHIEVE_LIST);
			// 任务
//			robot.addRobotStrategy(EStrategy.TASK_DAY);
			// 首充界面
//			robot.addRobotStrategy(EStrategy.PAY_FIRST);
			// 排行界面
//			robot.addRobotStrategy(EStrategy.RANK_INFO);
			// 邮件界面
//			robot.addRobotStrategy(EStrategy.MAIL_LIST);
			// 背包界面
//			robot.addRobotStrategy(EStrategy.ITEM_BAG);
			// 好友界面
//			robot.addRobotStrategy(EStrategy.FRIEND_LIST);
			// 好友申请界面
//			robot.addRobotStrategy(EStrategy.FRIEND_REQUEST_LIST);
			// 战斗(循环执行)
//			robot.addRobotStrategy(EStrategy.BATTLE_PVE);
			// PVE城市
//			robot.addRobotStrategy(EStrategy.PVE_CITY);
			// 副本
//			robot.addRobotStrategy(EStrategy.DUPLICATE);
			// 城市成就
//			robot.addRobotStrategy(EStrategy.CITYACHIEVE);
			// 精英副本
//			robot.addRobotStrategy(EStrategy.BATTLE_ELITE);
		}
	}
}
