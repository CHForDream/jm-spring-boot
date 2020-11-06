package com.game.robot.strategy.impl;

import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.game.utils.RandomUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetAchieveListMsg.CGGetAchieveListProto;
import buffer.CGGetAchieveRewardMsg.CGGetAchieveRewardProto;
import buffer.GCGetAchieveListMsg;
import buffer.GCGetAchieveListMsg.AchieveListProto;

@EStrategyType(strategy = EStrategy.ACHIEVE_LIST)
public class AchieveStrategy extends LoopExecuteStrategy {

	public AchieveStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[Achieve][AchieveStrategy][Start][CGGetAchieveListProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId()));
		}

		CGGetAchieveListProto.Builder cgGetAchieveListProto = CGGetAchieveListProto.newBuilder();
		getRobot().sendHttpMessage(cgGetAchieveListProto.getMsgType(), cgGetAchieveListProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCGetAchieveListMsg: {
			GCGetAchieveListMsg.GCGetAchieveListProto gcGetMonthCardInfoProto = (GCGetAchieveListMsg.GCGetAchieveListProto) msg;
			if (Config.IS_PRINT_LOG) {
				String roleInfo = "[Achieve][AchieveStrategy][End][GCGetAchieveListProto][robotId:%d]";
				String logger = String.format(roleInfo, getRobot().getId());
				Loggers.robotLogger.info(logger);
			}
			for (int i = 0; i < gcGetMonthCardInfoProto.getTasksCount(); i++) {
				AchieveListProto task = gcGetMonthCardInfoProto.getTasksList().get(i);
				if (task.getStatus() == 1) {
					if (Config.IS_PRINT_LOG) {
						String roleInfo = "[Achieve][AchieveStrategy][Start][CGGetAchieveRewardProto][robotId:%d]";
						String logger = String.format(roleInfo, getRobot().getId());
						Loggers.robotLogger.info(logger);
					}
					CGGetAchieveRewardProto.Builder cgGetAchieveRewardProto = CGGetAchieveRewardProto.newBuilder();
					cgGetAchieveRewardProto.setTaskId(task.getTaskId());
					getRobot().sendHttpMessage(cgGetAchieveRewardProto.getMsgType(), cgGetAchieveRewardProto);
					break;
				}
			}
		}
			break;
		case MsgType.GCGetAchieveRewardMsg: {
//			GCGetAchieveRewardProto gcGetAchieveRewardProto = (GCGetAchieveRewardProto) msg;

			if (Config.IS_PRINT_LOG) {
				String logger = "[Achieve][AchieveStrategy][End][GCGetAchieveRewardProto][robotId:%d]";
				Loggers.robotLogger.info(String.format(logger, getRobot().getId()));
			}
		}
			break;
		default:
			break;
		}
	}
}
