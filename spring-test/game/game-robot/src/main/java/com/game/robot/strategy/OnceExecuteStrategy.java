package com.game.robot.strategy;

import com.game.robot.Robot;

/**
 * 单词执行策略
 *
 * @author haijiang.jin
 *
 */
public abstract class OnceExecuteStrategy extends BaseRobotStrategry {

	/**
	 * 类参数构造器
	 *
	 * @param robot 发起操作的机器人对象
	 */
	public OnceExecuteStrategy(Robot robot) {
		super(robot);
	}

	/**
	 * 类参数构造器
	 *
	 * @param robot 发起操作的机器人对象
	 * @param delay 延迟时间
	 */
	public OnceExecuteStrategy(Robot robot, long delay) {
		super(robot, delay, 0);
	}

	@Override
	public final boolean isRepeatable() {
		return false;
	}
}
