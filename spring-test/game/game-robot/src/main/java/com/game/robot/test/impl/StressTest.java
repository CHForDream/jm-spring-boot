package com.game.robot.test.impl;

import com.game.robot.RobotStress;
import com.game.robot.config.Config;
import com.game.robot.strategy.EStrategy;
import com.game.robot.test.BaseTest;

public class StressTest extends BaseTest {

	@Override
	public void initRobotList() {
		for (int i = Config.ROBOT_START_ID; i < Config.ROBOT_START_ID + Config.ROBOT_COUNT; i++) {
			RobotStress robot = new RobotStress(i);
			getRobotList().add(robot);

			// 压力测试
			robot.addRobotStrategy(EStrategy.STRESS_LOOP);
		}
	}
}
