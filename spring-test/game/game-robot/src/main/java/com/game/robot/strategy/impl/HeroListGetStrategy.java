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

import buffer.CGGetHeroListMsg.CGGetHeroListProto;
import buffer.GCGetHeroListMsg.GCGetHeroListProto;

@EStrategyType(strategy = EStrategy.HERO_LISTGET)
public class HeroListGetStrategy extends LoopExecuteStrategy {

	public HeroListGetStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[Hero][HeroListGetStrategy][Start][CGGetHeroListProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger, getRobot().getId()));
		}
		// 发送获得伙伴列表
		CGGetHeroListProto.Builder cgGetHeroListProto = CGGetHeroListProto.newBuilder();
		getRobot().sendHttpMessage(cgGetHeroListProto.getMsgType(), cgGetHeroListProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCGetHeroListMsg: {
			GCGetHeroListProto gcGetHeroListProto = (GCGetHeroListProto) msg;

			if (Config.IS_PRINT_LOG) {
				String logger = "[Hero][HeroListGetStrategy][End][GCGetHeroListProto][robotId:%d][HeroNum:%d]";
				Loggers.robotLogger.info(String.format(logger, getRobot().getId(), gcGetHeroListProto.getHerosCount()));

				logger = "[Hero][HeroListGetStrategy][Start][GCGetHeroDecorationInfosProto][robotId:%d]";
				Loggers.robotLogger.info(String.format(logger, getRobot().getId()));
			}
		}
			break;
		default:
			break;
		}
	}
}
