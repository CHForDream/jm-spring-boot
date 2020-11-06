package com.game.robot.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.game.constants.Loggers;
import com.game.robot.Robot;
import com.game.robot.RobotState;
import com.game.robot.strategy.EStrategy;
import com.game.utils.TimeUtils;
import com.google.common.collect.Maps;

public abstract class BaseTest implements ITest {

	// 测试用例
	public static AtomicLong STRATEGY_TOTAL_COST_TIME = new AtomicLong();
	public static AtomicInteger STRATEGY_COUNT = new AtomicInteger();
	public static HashMap<EStrategy, AtomicLong> STRATEGY_TOTAL_COST_TIME_MAP = new HashMap<EStrategy, AtomicLong>();
	public static HashMap<EStrategy, AtomicInteger> STRATEGY_COUNT_MAP = new HashMap<EStrategy, AtomicInteger>();

	// 通讯消息
	public static AtomicLong MESSAGE_TOTAL_COST_TIME = new AtomicLong();
	public static AtomicInteger MESSAGE_COUNT = new AtomicInteger();

	private List<Robot> robotList = new ArrayList<Robot>();

	// 测试用例
	private long strategyLastTotalCostTime = 0L;
	private int strategyLastCount = 0;
	// 通讯消息
	private long messageTotalCostTime = 0L;
	private int messageCount = 0;

	// 测试用例
	private long tmpStrategyTotalCostTime = 0L;
	private int tmpStrategyCount = 0;
	// 通讯消息
	private long tmpMessageTotalCostTime = 0L;
	private int tmpMessageCount = 0;

	@Override
	public void testRobot() {

		for (int i = 0; i < EStrategy.values().length; i++) {
			STRATEGY_TOTAL_COST_TIME_MAP.put(EStrategy.values()[i], new AtomicLong());
			STRATEGY_COUNT_MAP.put(EStrategy.values()[i], new AtomicInteger());
		}

		initRobotList();
		startTest();
		printRobotInfo();
	}

	@Override
	public void strategyCount(EStrategy eStrategy, long costTime) {
		STRATEGY_TOTAL_COST_TIME.addAndGet(costTime);
		STRATEGY_COUNT.incrementAndGet();
		STRATEGY_TOTAL_COST_TIME_MAP.get(eStrategy).addAndGet(costTime);
		STRATEGY_COUNT_MAP.get(eStrategy).incrementAndGet();
	}

	@Override
	public void messageCount(long costTime) {
		MESSAGE_TOTAL_COST_TIME.addAndGet(costTime);
		MESSAGE_COUNT.incrementAndGet();
	}

	@Override
	public void printRobotInfo() {
		try {
			Map<RobotState, Integer> stateMap = Maps.newTreeMap();
			for (RobotState robotState : RobotState.values()) {
				stateMap.put(robotState, 0);
			}

			while (true) {
				Loggers.robotLogger.info("------------------------------");

				// 状态信息
				for (Robot robot : robotList) {
					stateMap.put(robot.getState(), stateMap.get(robot.getState()) + 1);
				}
				StringBuilder infoSb = new StringBuilder();
				for (RobotState robotState : stateMap.keySet()) {
					infoSb.append("[").append(robotState).append(" : ").append(stateMap.get(robotState)).append("]");
					stateMap.put(robotState, 0);
				}
				Loggers.robotLogger.info(infoSb.toString());

				tmpStrategyTotalCostTime = STRATEGY_TOTAL_COST_TIME.get();
				tmpStrategyCount = STRATEGY_COUNT.get();
				tmpMessageTotalCostTime = MESSAGE_TOTAL_COST_TIME.get();
				tmpMessageCount = MESSAGE_COUNT.get();

				// 测试用例耗时
				StringBuilder strategySb = new StringBuilder();
				strategySb.append("[strategyTimeCost : ").append(tmpStrategyTotalCostTime).append("ms] [strategyCount : ").append(tmpStrategyCount)
						.append("] [avgStrategyTimeCost : ").append(tmpStrategyCount == 0 ? 0 : tmpStrategyTotalCostTime / tmpStrategyCount).append("ms]");
				Loggers.robotLogger.info(strategySb.toString());

				// 消息耗时
				StringBuilder messageSb = new StringBuilder();
				messageSb.append("[messageTimeCost : ").append(tmpMessageTotalCostTime).append("ms] [messageCount : ").append(tmpMessageCount)
						.append("] [avgMessageTimeCost : ").append(tmpMessageCount == 0 ? 0 : tmpMessageTotalCostTime / tmpMessageCount).append("ms]");
				Loggers.robotLogger.info(messageSb.toString());

				// 每个用例的单独耗时
				StringBuilder stategyTypeSb = new StringBuilder();
				for (EStrategy stategy : STRATEGY_TOTAL_COST_TIME_MAP.keySet()) {
					int count = STRATEGY_COUNT_MAP.get(stategy).get();
					if (count == 0) {
						continue;
					}
					long costTime = STRATEGY_TOTAL_COST_TIME_MAP.get(stategy).get();
					long averTime = costTime / count;
					stategyTypeSb.append("\n[").append(stategy.toString()).append("] [cost: ").append(costTime).append("ms] [count: ").append(count)
							.append("] [avgCost: ").append(averTime).append("ms]");
				}
				Loggers.robotLogger.info(stategyTypeSb.toString());

				// 两次间隔增量
				if (strategyLastTotalCostTime != 0) {
					StringBuilder messageLastSb = new StringBuilder().append("[IncreseInfo]\n")

							.append("Strategy:[deltaTime: ").append(tmpStrategyTotalCostTime - strategyLastTotalCostTime).append("ms] [deltaCount: ")
							.append(tmpStrategyCount - strategyLastCount).append("] [avgDeltaTime: ")
							.append((tmpStrategyCount - strategyLastCount) == 0 ? 0
									: (tmpStrategyTotalCostTime - strategyLastTotalCostTime) / (tmpStrategyCount - strategyLastCount))
							.append("ms]\nMessage:[deltaTime: ").append(tmpMessageTotalCostTime - messageTotalCostTime).append("ms] [deltaCount: ")
							.append(tmpMessageCount - messageCount).append("] [avgDeltaTime: ").append((tmpMessageCount - messageCount) == 0 ? 0
									: (tmpMessageTotalCostTime - messageTotalCostTime) / (tmpMessageCount - messageCount))
							.append("ms]");
					Loggers.robotLogger.info(messageLastSb.toString());
				}

				strategyLastTotalCostTime = tmpStrategyTotalCostTime;
				strategyLastCount = tmpStrategyCount;
				messageTotalCostTime = tmpMessageTotalCostTime;
				messageCount = tmpMessageCount;

				// 每秒进行一次机器人状态查询
				Thread.sleep(TimeUtils.MIN);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void startTest() {
		for (Robot robot : robotList) {
			robot.start();
			// 间隔200-500毫秒启动一个机器人
//			try {
//				Thread.sleep(RandomUtil.random(200, 500));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		Loggers.robotLogger.info("==========> Robot init complete!");
	}

	public List<Robot> getRobotList() {
		return robotList;
	}

	public void setRobotList(List<Robot> robotList) {
		this.robotList = robotList;
	}
}
