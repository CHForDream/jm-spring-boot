package com.game.robot.strategy.impl;

import java.util.List;

import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.game.utils.RandomUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetSevenSignStatusMsg.CGGetSevenSignStatusProto;
import buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto;
import buffer.GCGetSevenSignStatusMsg.GCGetSevenSignStatusProto;
import buffer.GCGetSevenSignStatusMsg.GCSivenSignProto;
import buffer.GCReceiveSevenSignRewardMsg.GCReceiveSevenSignRewardProto;

@EStrategyType(strategy = EStrategy.SIGN_LOOP)
public class SignLoopStrategy extends LoopExecuteStrategy {

	public SignLoopStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("SignLoopStrategy start. robotId = " + getRobot().getId());
		}

		// 发送签到数据请求
		CGGetSevenSignStatusProto.Builder cgGetSevenSignStatusProto = CGGetSevenSignStatusProto.newBuilder();
		getRobot().sendHttpMessage(cgGetSevenSignStatusProto.getMsgType(), cgGetSevenSignStatusProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCGetSevenSignStatusMsg:
			GCGetSevenSignStatusProto gcGetSevenSignStatusProto = (GCGetSevenSignStatusProto) msg;
			boolean hasCanSignDay = false;
			List<GCSivenSignProto> sivenSignsList = gcGetSevenSignStatusProto.getSivenSignsList();
			for (GCSivenSignProto sivenSign : sivenSignsList) {
				if (Config.IS_PRINT_LOG) {
					Loggers.robotLogger.info("robotId = " + getRobot().getId() + ", day = " + sivenSign.getDay() + ", status = " + sivenSign.getStatus()
							+ ", itemType = " + sivenSign.getUnitType() + ", itemId = " + sivenSign.getUnitId() + ", itemNum = " + sivenSign.getNum());
				}
				int day = sivenSign.getDay();
				int status = sivenSign.getStatus();// 0不可领取, 1可领取, 2已领取
				if (status == 1) {
					hasCanSignDay = true;
					// 领取签到奖励
					CGReceiveSevenSignRewardProto.Builder cgMsg = CGReceiveSevenSignRewardProto.newBuilder();
					cgMsg.setDay(day);
					getRobot().sendHttpMessage(cgMsg.getMsgType(), cgMsg);
					break;
				}
			}

			if (Config.IS_PRINT_LOG && !hasCanSignDay) {
				Loggers.robotLogger.info("SignLoopStrategy end 2. robotId = " + getRobot().getId());
			}

			break;
		case MsgType.GCReceiveSevenSignRewardMsg:
			GCReceiveSevenSignRewardProto gcMsg = (GCReceiveSevenSignRewardProto) msg;
			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("status = " + gcMsg.getStatus() + ", rewards = " + gcMsg.getReward());
				Loggers.robotLogger.info("SignLoopStrategy end 1. robotId = " + getRobot().getId());
			}

			break;

		default:
			break;
		}
	}
}
