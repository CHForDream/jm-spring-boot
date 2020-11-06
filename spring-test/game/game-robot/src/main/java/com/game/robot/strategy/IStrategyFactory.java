package com.game.robot.strategy;

import com.game.robot.Robot;

public interface IStrategyFactory {
	public IRobotStrategy createStrategy(Robot robot);
}
