package com.game.robot.thread;

import java.util.concurrent.ScheduledFuture;

import com.game.robot.strategy.IRobotStrategy;

public class StrategyRunnable implements Runnable {
	/** 机器人执行策略 */
	private IRobotStrategy strategy = null;
	/** 执行结果 */
	private ScheduledFuture<?> future = null;

	/**
	 * 类参数构造器
	 *
	 * @param strategy 机器人执行策略
	 * @throws IllegalArgumentException if strategy is null
	 */
	public StrategyRunnable(IRobotStrategy strategy) {
		if (strategy == null) {
			throw new IllegalArgumentException("strategy is null");
		}

		this.strategy = strategy;
		this.strategy.setStrategyRunnable(this);
	}

	@Override
	public void run() {
		if (this.strategy.canDoAction()) {
			this.strategy.setStartTime(System.currentTimeMillis());
			this.strategy.doAction();
			this.strategy.afterAction();
//			this.strategy.getRobot().setState(RobotState.IDLE);
		}
	}

	/**
	 * 获取执行结果
	 *
	 * @return
	 */
	public ScheduledFuture<?> getFuture() {
		return future;
	}

	/**
	 * 设置执行结果
	 *
	 * @param future
	 */
	public void setFuture(ScheduledFuture<?> future) {
		this.future = future;
	}
}
