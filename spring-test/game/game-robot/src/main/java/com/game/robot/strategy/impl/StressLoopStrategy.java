package com.game.robot.strategy.impl;

import com.game.robot.Robot;
import com.game.robot.RobotState;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.IRobotStrategy;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.game.robot.thread.StrategryExcutor;
import com.game.robot.thread.StrategyRunnable;
import com.game.utils.RandomUtil;
import com.google.protobuf.GeneratedMessage;

@EStrategyType(strategy = EStrategy.STRESS_LOOP)
public class StressLoopStrategy extends LoopExecuteStrategy {

	private IRobotStrategy currentStrategy = null;

	public StressLoopStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (getRobot().getState() != RobotState.IDLE) {
			return;
		}

		// 进入gaming状态
		getRobot().setState(RobotState.GAMING);
		// 随机一个策略执行
		int index = RandomUtil.random(0, EStrategy.getRandomEStrategyArray().length - 1);
		currentStrategy = EStrategy.getRandomEStrategyArray()[index].getStrategyFactory().createStrategy(getRobot());

//		// 随机一个策略执行
//		currentStrategy = EStrategy.FRIEND_LIST.getStrategyFactory().createStrategy(getRobot());

		StrategyRunnable runnable = new StrategyRunnable(currentStrategy);
		// 仅执行一次机器人操作
		StrategryExcutor.getInstance().execute(runnable);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		if (currentStrategy != null) {
			currentStrategy.onResponse(msgType, msg);
		}
	}

	@Override
	public void afterAction() {
	}
}
