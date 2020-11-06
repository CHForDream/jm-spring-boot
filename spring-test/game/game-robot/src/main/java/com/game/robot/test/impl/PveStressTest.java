package com.game.robot.test.impl;

import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.strategy.EStrategy;
import com.game.robot.test.BaseTest;

public class PveStressTest extends BaseTest {

	@Override
	public void initRobotList() {
		for (int i = Config.ROBOT_START_ID; i < Config.ROBOT_START_ID + Config.ROBOT_COUNT; i++) {
			Robot robot = new Robot(i);
			getRobotList().add(robot);

			// 战斗(循环执行)
			robot.addRobotStrategy(EStrategy.BATTLE_PVE);
		}
	}
}
