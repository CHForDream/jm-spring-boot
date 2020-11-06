package com.game.robot.test;

import com.game.robot.strategy.EStrategy;

public interface ITest {
	public void testRobot();

	public void initRobotList();

	public void printRobotInfo();

	public void strategyCount(EStrategy eStrategy, long costTime);

	public void messageCount(long costTime);
}
