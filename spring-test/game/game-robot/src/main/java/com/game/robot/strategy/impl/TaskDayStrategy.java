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

import buffer.CGGetDayTaskListMsg.CGGetDayTaskListProto;
import buffer.CGGetDayTaskRewardMsg.CGGetDayTaskRewardProto;
import buffer.GCGetDayTaskListMsg.DayTaskProto;
import buffer.GCGetDayTaskListMsg.GCGetDayTaskListProto;

@EStrategyType(strategy = EStrategy.TASK_DAY)
public class TaskDayStrategy extends LoopExecuteStrategy {

	public TaskDayStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[TaskDay][TaskDayStrategy][Start][CGGetDayTaskListProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId()));
		}

		CGGetDayTaskListProto.Builder cgGetDayTaskListProto = CGGetDayTaskListProto.newBuilder();
		getRobot().sendHttpMessage(cgGetDayTaskListProto.getMsgType(), cgGetDayTaskListProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCGetDayTaskListMsg: {
			GCGetDayTaskListProto gcGetDayTaskListProto = (GCGetDayTaskListProto) msg;
			if (Config.IS_PRINT_LOG) {
				String roleInfo = "[TaskDay][TaskDayStrategy][End][GCGetDayTaskListProto][robotId:%d]";
				String logger = String.format(roleInfo, getRobot().getId());
				Loggers.robotLogger.info(logger);
			}
			for (int i = 0; i < gcGetDayTaskListProto.getTasksCount(); i++) {
				DayTaskProto task = gcGetDayTaskListProto.getTasksList().get(i);
				if (task.getStatus() == 1) {
					if (Config.IS_PRINT_LOG) {
						String roleInfo = "[TaskDay][TaskDayStrategy][Start][DayTaskProto][robotId:%d]";
						String logger = String.format(roleInfo, getRobot().getId());
						Loggers.robotLogger.info(logger);
					}

					CGGetDayTaskRewardProto.Builder getDayTaskRewardProto = CGGetDayTaskRewardProto.newBuilder();
					getDayTaskRewardProto.setTaskId(task.getTaskId());
					getDayTaskRewardProto.setRewardType(1);
					getRobot().sendHttpMessage(getDayTaskRewardProto.getMsgType(), getDayTaskRewardProto);
					break;
				}
			}
		}
			break;
		case MsgType.GCGetDayTaskRewardMsg: {
			if (Config.IS_PRINT_LOG) {
				String roleInfo = "[TaskDay][TaskDayStrategy][End][DayTaskProto][robotId:%d]";
				String logger = String.format(roleInfo, getRobot().getId());
				Loggers.robotLogger.info(logger);
			}
		}
			break;
		default:
			break;
		}
	}
}
