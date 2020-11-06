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

import buffer.CGFriendsRankInfoMsg.CGFriendsRankInfoProto;

@EStrategyType(strategy = EStrategy.RANK_INFO)
public class RankInfoStrategy extends LoopExecuteStrategy {

	public RankInfoStrategy(Robot robot, long delay, long minInterval, long maxInterval) {
		super(robot, delay, RandomUtil.random(minInterval, maxInterval));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("RankInfoStrategy start. robotId = " + getRobot().getId());
		}

		CGFriendsRankInfoProto.Builder cgFriendsRankInfoProto = CGFriendsRankInfoProto.newBuilder();
		getRobot().sendHttpMessage(cgFriendsRankInfoProto.getMsgType(), cgFriendsRankInfoProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCFriendsRankInfoMsg:
			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("RankInfoStrategy end. robotId = " + getRobot().getId());
			}
			break;
		}
	}
}
