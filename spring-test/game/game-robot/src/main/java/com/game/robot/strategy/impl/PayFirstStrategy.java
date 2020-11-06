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

import buffer.CGGetRoleFirstPayInfoMsg.CGGetRoleFirstPayInfoProto;

@EStrategyType(strategy = EStrategy.PAY_FIRST)
public class PayFirstStrategy extends LoopExecuteStrategy {

	public PayFirstStrategy(Robot robot, long delay, long minInterval, long maxInterval) {
		super(robot, delay, RandomUtil.random(minInterval, maxInterval));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("PayFirstStrategy start. robotId = " + getRobot().getId());
		}

		CGGetRoleFirstPayInfoProto.Builder cgGetRoleFirstPayInfoProto = CGGetRoleFirstPayInfoProto.newBuilder();
		getRobot().sendHttpMessage(cgGetRoleFirstPayInfoProto.getMsgType(), cgGetRoleFirstPayInfoProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCGetRoleFirstPayInfoMsg:
//			GCGetRoleFirstPayInfoProto gcGetRoleFirstPayInfo = (GCGetRoleFirstPayInfoProto) msg;
//			gcGetRoleFirstPayInfo.getFirstStatus();
			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("PayFirstStrategy end. robotId = " + getRobot().getId());
			}
			break;
		}
	}
}
