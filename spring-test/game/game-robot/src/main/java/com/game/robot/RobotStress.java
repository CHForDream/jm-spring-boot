package com.game.robot;

import com.game.robot.strategy.EStrategy;

/**
 * 压力测试用机器人
 * 
 * @author pky
 *
 */
public class RobotStress extends Robot {

//	private int stressId = 0;

	public RobotStress(int id) {
		super(id);

//		stressId = this.getStrategyList().size();

//		for (int i = 0; i < EStrategy.values().length; i++) {
//			EStrategy eStrategy = EStrategy.values()[i];
//			if (eStrategy.getType() < 1000 || eStrategy.getType() == 2000) {
//				// 心跳和战斗排除掉 心跳是因为在父类加载了循环请求 战斗是因为目前不太符合单次请求 后期需要用状态控制下
//				continue;
//			}
//			addRobotStrategy(eStrategy);
//		}

		addRobotStrategy(EStrategy.STRESS_LOOP);
	}

	@Override
	public void doStrategy() {
		super.doStrategy();

		setState(RobotState.IDLE);
	}
}
