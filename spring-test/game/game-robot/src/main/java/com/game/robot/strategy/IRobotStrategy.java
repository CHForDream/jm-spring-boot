package com.game.robot.strategy;

import com.game.robot.Robot;
import com.game.robot.thread.StrategyRunnable;
import com.google.protobuf.GeneratedMessage;

/**
 * 机器人执行策略接口, 在该接口中定义机器人进入游戏后可以做什么以及怎样做?
 *
 * @author haijiang.jin
 *
 */
public interface IRobotStrategy {
	/**
	 * 是否可以循环执行
	 *
	 * @return true = 可以循环执行的策略, false = 不可以循环执行的策略
	 */
	public boolean isRepeatable();

	/**
	 * 是否可以
	 *
	 * @return
	 */
	public boolean canDoAction();

	/**
	 * 执行机器人动作
	 *
	 */
	public void doAction();

	/**
	 * 执行机器人动作
	 *
	 */
	public void afterAction();

	/**
	 * 获取第一次执行的延迟时间, 以毫秒为单位
	 *
	 * @return
	 */
	public long getDelay();

	/**
	 * 获取循环执行的时间间隔, 以毫秒为单位
	 *
	 * @return
	 */
	public long getInterval();

	/**
	 * 处理返回消息的处理
	 * 
	 * @param message
	 */
	public void onResponse(int msgType, GeneratedMessage msg);

	/**
	 * 获取运行策略的Runnable
	 * 
	 */
	public StrategyRunnable getStrategyRunnable();

	/**
	 * 设置运行策略的Runnable
	 * 
	 */
	public void setStrategyRunnable(StrategyRunnable strategyRunnable);

	public Robot getRobot();

	public void setStartTime(long startTime);
}
