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

import buffer.CGGetLuckDrawInfoMsg.CGGetLuckDrawInfoProto;
import buffer.CGLuckDrawMsg.CGLuckDrawMsgProto;
import buffer.GCGetLuckDrawInfoMsg.GCGetLuckDrawInfoProto;
import buffer.GCLuckDrawMsg.GCLuckDrawMsgProto;

@EStrategyType(strategy = EStrategy.STORE_LUCKDRAW)
public class StoreLuckDrawStrategy extends LoopExecuteStrategy {

	public StoreLuckDrawStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[Store][StoreInfoStrategy][Start][CGGetLuckDrawInfoProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId()));
		}

		CGGetLuckDrawInfoProto.Builder cgGetLuckDrawInfoProto = CGGetLuckDrawInfoProto.newBuilder();
		getRobot().sendHttpMessage(cgGetLuckDrawInfoProto.getMsgType(), cgGetLuckDrawInfoProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCGetLuckDrawInfoMsg: {
			GCGetLuckDrawInfoProto gcGetLuckDrawInfoProto = (GCGetLuckDrawInfoProto) msg;
			if (Config.IS_PRINT_LOG) {
				String logger = "[Store][StoreInfoStrategy][End][GCGetLuckDrawInfoProto][robotId:%d][status:%d]";
				Loggers.robotLogger.info(String.format(logger, getRobot().getId(), gcGetLuckDrawInfoProto.getStatus()));
			}

			luckDraw();
		}
			break;
		case MsgType.GCLuckDrawMsg: {
			GCLuckDrawMsgProto gcLuckDrawMsgProto = (GCLuckDrawMsgProto) msg;
			if (Config.IS_PRINT_LOG) {
				String logger = "[Store][StoreInfoStrategy][End][GCLuckDrawMsgProto][robotId:%d][Status:%d][rewardsNum:%d]";
				Loggers.robotLogger.info(String.format(logger, getRobot().getId(), gcLuckDrawMsgProto.getStatus(), gcLuckDrawMsgProto.getRewardsCount()));
			}
		}
			break;
		default:
			break;
		}
	}

	/**
	 * 夺宝中抽奖
	 */
	private void luckDraw() {
		int type = RandomUtil.random(1, 2) == 1 ? 1 : 10;
		if (Config.IS_PRINT_LOG) {
			String logger = "[Store][StoreInfoStrategy][Start][CGLuckDrawMsgProto][robotId:%d][type:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId(), type));
		}

		CGLuckDrawMsgProto.Builder cgLuckDrawMsgProto = CGLuckDrawMsgProto.newBuilder();
		cgLuckDrawMsgProto.setType(type);
		getRobot().sendHttpMessage(cgLuckDrawMsgProto.getMsgType(), cgLuckDrawMsgProto);
	}
}
