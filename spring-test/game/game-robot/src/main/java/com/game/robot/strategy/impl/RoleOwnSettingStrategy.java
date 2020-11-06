package com.game.robot.strategy.impl;

import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.constant.StatusCode;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.game.utils.RandomUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto;
import buffer.CGRoleHomePageSetUpMsg.CGRoleHomePageSetUpProto;
import buffer.GCGetHeroListMsg.GCGetHeroListProto;
import buffer.GCRoleHomePageInfoMsg.GCRoleHomePageInfoProto;
import buffer.GCRoleHomePageSetUpMsg.GCRoleHomePageSetUpProto;

@EStrategyType(strategy = EStrategy.ROLE_SETTING)
public class RoleOwnSettingStrategy extends LoopExecuteStrategy {

	public RoleOwnSettingStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[Role][RoleOwnSettingStrategy][Start][CGRoleHomePageInfoProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger, getRobot().getId()));
		}
		// 点击“我”的请求
		CGRoleHomePageInfoProto.Builder cgRoleHomePageInfoProto = CGRoleHomePageInfoProto.newBuilder();
		cgRoleHomePageInfoProto.setTargetUid(getRobot().getUid());
		getRobot().sendHttpMessage(cgRoleHomePageInfoProto.getMsgType(), cgRoleHomePageInfoProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCRoleHomePageInfoMsg: {
			GCRoleHomePageInfoProto gcRoleHomePageInfoProto = (GCRoleHomePageInfoProto) msg;
			int status = gcRoleHomePageInfoProto.getStatus();
			if (status != StatusCode.SUCCESS) {
				Loggers.robotLogger.error(
						String.format("[Role][RoleOwnSettingStrategy][End][GCRoleHomePageInfoProto][robotId:%d][status:%d]", getRobot().getId(), status));
				return;
			}
			if (Config.IS_PRINT_LOG) {
				String roleInfo = "[Role][RoleOwnSettingStrategy][End][GCRoleHomePageInfoProto][robotId:%d][Header:%s][Name:%s][Sex:%d][ShowId:%d][HeroCode:%d][PersonSign:%s][PetsCode:%d][ChangeNameCardNum:%d][FriendsFabulousNum:%d]";
				String message = String.format(roleInfo, getRobot().getId(), gcRoleHomePageInfoProto.getHeader(), gcRoleHomePageInfoProto.getName(),
						gcRoleHomePageInfoProto.getSex(), gcRoleHomePageInfoProto.getShowId(), gcRoleHomePageInfoProto.getHeroCode(),
						gcRoleHomePageInfoProto.getPersonSign(), gcRoleHomePageInfoProto.getPetsCode(), gcRoleHomePageInfoProto.getChangeNameCardNum(),
						gcRoleHomePageInfoProto.getFriendsFabulousNum());
				Loggers.robotLogger.info(message);
			}
			// 随机设置某一个选项 后期可能需要添加新的功能
			RoleHomePageSetUp();
		}
			break;
		case MsgType.GCRoleHomePageSetUpMsg: {
			GCRoleHomePageSetUpProto gcRoleHomePageSetUpProto = (GCRoleHomePageSetUpProto) msg;

			if (Config.IS_PRINT_LOG) {
				String gcRoleHomePageSetUpMsg = "[Role][RoleOwnSettingStrategy][End][GCRoleHomePageSetUpProto][robotId:%d][OptType:%s][Status:%d]";
				String message = "";
				if (gcRoleHomePageSetUpProto.getOptType() == 5) {
					message = String.format(gcRoleHomePageSetUpMsg, getRobot().getId(), "设置最爱的伙伴", gcRoleHomePageSetUpProto.getStatus());
				} else if (gcRoleHomePageSetUpProto.getOptType() == 6) {
					message = String.format(gcRoleHomePageSetUpMsg, getRobot().getId(), "设置最爱的宠物", gcRoleHomePageSetUpProto.getStatus());
				}
				Loggers.robotLogger.info(message);
			}
		}
			break;
		default:
			break;
		}

	}

	private void RoleHomePageSetUp() {
		int type = RandomUtil.random(5, 6);

		StringBuilder logger = new StringBuilder();
		logger.append("[Role][RoleOwnSettingStrategy][Start]");

		if (type == 5) {
			// 设置最爱的伙伴
			CGRoleHomePageSetUpProto.Builder cgRoleHomePageSetUpProto = CGRoleHomePageSetUpProto.newBuilder();
			cgRoleHomePageSetUpProto.setOptType(5); // 5英雄 6伙伴
			GCGetHeroListProto proto = getRobot().getInfoManager().getGcGetHeroListProto();
			if (proto != null && proto.getHerosCount() > 0) {
				int heroCode = proto.getHerosList().get(RandomUtil.random(0, proto.getHerosCount() - 1)).getHeroCode();
				cgRoleHomePageSetUpProto.setHeroCode(heroCode); // 最爱英雄id
			}

			if (Config.IS_PRINT_LOG) {
				logger.append("[CGRoleHomePageSetUpProto][robotId:%d][type:5]");
				Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId()));
			}

			getRobot().sendHttpMessage(cgRoleHomePageSetUpProto.getMsgType(), cgRoleHomePageSetUpProto);
		}
	}
}
