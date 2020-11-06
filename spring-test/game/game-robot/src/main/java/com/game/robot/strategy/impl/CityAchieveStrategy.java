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

import buffer.CGCityAwardMsg.CGCityAwardProto;
import buffer.CGCityInfoMsg.CGCityInfoProto;
import buffer.GCCityInfoMsg;
import buffer.GCCityInfoMsg.CityInfo;

@EStrategyType(strategy = EStrategy.CITYACHIEVE)
public class CityAchieveStrategy extends LoopExecuteStrategy {

	public CityAchieveStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[CityAchieve][CityAchieveStrategy][Start][CGGetAchieveListProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId()));
		}
		CGCityInfoProto.Builder cgCityInfoProto = CGCityInfoProto.newBuilder();
		int type = 1;//RandomUtil.random(1, 2);
		cgCityInfoProto.setInfoType(type);
		getRobot().sendHttpMessage(cgCityInfoProto.getMsgType(), cgCityInfoProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCCityInfoMsg: {
			GCCityInfoMsg.GCCityInfoProto gcCityInfoProto = (GCCityInfoMsg.GCCityInfoProto) msg;
			if (Config.IS_PRINT_LOG) {
				String roleInfo = "[CityAchieve][CityAchieveStrategy][End][GCCityInfoProto][robotId:%d]";
				String logger = String.format(roleInfo, getRobot().getId());
				Loggers.robotLogger.info(logger);
			}
			for (int i = 0; i < gcCityInfoProto.getCityInfoCount(); i++) {
				CityInfo task = gcCityInfoProto.getCityInfoList().get(i);
				if (task.getStatus() == 1) {
					if (Config.IS_PRINT_LOG) {
						String roleInfo = "[CityAchieve][CityAchieveStrategy][Start][CGGetAchieveRewardProto][robotId:%d]";
						String logger = String.format(roleInfo, getRobot().getId());
						Loggers.robotLogger.info(logger);
					}
					CGCityAwardProto.Builder cgCityAwardProto = CGCityAwardProto.newBuilder();
					cgCityAwardProto.setCityId(task.getCityId());
					cgCityAwardProto.setInfoType(gcCityInfoProto.getInfoType());
					getRobot().sendHttpMessage(cgCityAwardProto.getMsgType(), cgCityAwardProto);
					break;
				}
			}
		}
			break;
		case MsgType.GCCityAwardMsg: {
//			GCGetAchieveRewardProto gcGetAchieveRewardProto = (GCGetAchieveRewardProto) msg;

			if (Config.IS_PRINT_LOG) {
				String logger = "[CityAchieve][CityAchieveStrategy][End][GCCityAwardProto][robotId:%d]";
				Loggers.robotLogger.info(String.format(logger, getRobot().getId()));
			}
		}
			break;
		default:
			break;
		}
		Loggers.robotLogger.info("[CityAchieve][CityAchieveStrategy][End]" + msg.toString());
	}
}
