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

import buffer.CGMailgetListMsg.CGMailgetListProto;
import buffer.GCMailgetListMsg.GCMailgetListProto;

@EStrategyType(strategy = EStrategy.MAIL_LIST)
public class MailListStrategy extends LoopExecuteStrategy {

	public MailListStrategy(Robot robot, long delay, long minInterval, long maxInterval) {
		super(robot, delay, RandomUtil.random(minInterval, maxInterval));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("MailListStrategy start. robotId = " + getRobot().getId());
		}

		CGMailgetListProto.Builder cgMailgetListProto1 = CGMailgetListProto.newBuilder();
		cgMailgetListProto1.setMailType(1);
		getRobot().sendHttpMessage(cgMailgetListProto1.getMsgType(), cgMailgetListProto1);

		CGMailgetListProto.Builder cgMailgetListProto2 = CGMailgetListProto.newBuilder();
		cgMailgetListProto2.setMailType(2);
		getRobot().sendHttpMessage(cgMailgetListProto2.getMsgType(), cgMailgetListProto2);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCMailgetListMsg:
			GCMailgetListProto gcMailgetListProto = (GCMailgetListProto) msg;
			int mailType = gcMailgetListProto.getMailType();
			if (Config.IS_PRINT_LOG && mailType == 2) {
				Loggers.robotLogger.info("MailListStrategy end. robotId = " + getRobot().getId());
			}
			break;
		}
	}
}
