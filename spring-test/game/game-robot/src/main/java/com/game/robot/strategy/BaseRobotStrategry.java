package com.game.robot.strategy;

import com.game.robot.Robot;
import com.game.robot.RobotMain;
import com.game.robot.RobotState;
import com.game.robot.thread.StrategyRunnable;
import com.game.utils.TimeUtils;
import com.google.protobuf.AbstractMessage.Builder;

import buffer.CGChatGm.CGChatGmProto;

public abstract class BaseRobotStrategry implements IRobotStrategy {
	public static final boolean IS_PRINT_LOG = false;

	/** case执行时间默认在10分钟以内, 超过10分钟认为是异常时间, 不统计 */
	public final static long COST_TIME_MAX = TimeUtils.MIN * 10;

	/** 默认第一次执行的延迟时间 */
	public final static int DEFAULT_DELAY = 100;

	/** 默认循环执行的时间间隔 */
	public final int DEFAULT_INTERVAL = 1000;

	/** 发起操作的机器人对象 */
	private Robot robot;
	/** 运行测试的Runnable */
	private StrategyRunnable strategyRunnable;

	/** 第一次执行的延迟时间(毫秒) */
	private long delay = 0;
	/** 循环执行的时间间隔(毫秒) */
	private long interval = 0;

	/** 策略开始执行时间 */
	protected long startTime = 0L;

	public BaseRobotStrategry(Robot robot) {
		this.robot = robot;
		this.delay = DEFAULT_DELAY;
		this.interval = DEFAULT_INTERVAL;
	}

	public BaseRobotStrategry(Robot robot, long delay, long interval) {
		this.robot = robot;
		this.delay = delay;
		this.interval = interval;
	}

	@Override
	public boolean canDoAction() {
		if (robot.getState() == RobotState.IDLE || robot.getState() == RobotState.GAMING) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void afterAction() {
		robot.setState(RobotState.IDLE);

		long now = System.currentTimeMillis();
		long costTime = now - startTime;
		// case执行时间默认在10分钟以内, 超过10分钟认为是异常时间, 不统计
		if (costTime < COST_TIME_MAX) {
			EStrategyType annotation = this.getClass().getAnnotation(EStrategyType.class);
			if (annotation != null) {
				RobotMain.strategyCount(annotation.strategy(), costTime);
			}
		}
	}

	@Override
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	protected void sendHttpMessage(int msgType, @SuppressWarnings("rawtypes") Builder builder) {
		robot.sendHttpMessage(msgType, builder);
	}

	protected void sendChatGm(String gmString) {
		CGChatGmProto.Builder cgChatGmProto = CGChatGmProto.newBuilder();
		cgChatGmProto.setGmString(gmString);
		robot.sendHttpMessage(cgChatGmProto.getMsgType(), cgChatGmProto);
	}

	protected void sendChatGmAddHomeMoney(int homeMoneyNum) {
		CGChatGmProto.Builder cgChatGmProto = CGChatGmProto.newBuilder();
		cgChatGmProto.setGmString("!item 3 3 " + homeMoneyNum);
		robot.sendHttpMessage(cgChatGmProto.getMsgType(), cgChatGmProto);
	}

	protected void sendChatGmAddCash(int cashNum) {
		CGChatGmProto.Builder cgChatGmProto = CGChatGmProto.newBuilder();
		cgChatGmProto.setGmString("!item 3 4 " + cashNum);
		robot.sendHttpMessage(cgChatGmProto.getMsgType(), cgChatGmProto);
	}

	@Override
	public Robot getRobot() {
		return robot;
	}

	@Override
	public final long getDelay() {
		return this.delay;
	}

	@Override
	public final long getInterval() {
		return this.interval;
	}

	@Override
	public final StrategyRunnable getStrategyRunnable() {
		return strategyRunnable;
	}

	@Override
	public final void setStrategyRunnable(StrategyRunnable strategyRunnable) {
		this.strategyRunnable = strategyRunnable;
	}
}
