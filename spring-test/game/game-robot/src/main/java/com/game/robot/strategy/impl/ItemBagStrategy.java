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

import buffer.CGGetBagListMsg.CGGetBagListProto;

@EStrategyType(strategy = EStrategy.ITEM_BAG)
public class ItemBagStrategy extends LoopExecuteStrategy {
	private static final int[] BAG_TYPE_ARRAY = { 0, 3, 4 };

	public ItemBagStrategy(Robot robot, long delay, long minInterval, long maxInterval) {
		super(robot, delay, RandomUtil.random(minInterval, maxInterval));
	}

	@Override
	public void doAction() {
		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("ItemBagStrategy start. robotId = " + getRobot().getId());
		}

		CGGetBagListProto.Builder cgGetBagListProto = CGGetBagListProto.newBuilder();
		cgGetBagListProto.setType(BAG_TYPE_ARRAY[RandomUtil.random.nextInt(BAG_TYPE_ARRAY.length)]);
		getRobot().sendHttpMessage(cgGetBagListProto.getMsgType(), cgGetBagListProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCGetBagListMsg:
//			GCGetBagListProto gcGetBagList = (GCGetBagListProto) msg;
//			gcGetBagList.getUnitsCount();
			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("ItemBagStrategy end. robotId = " + getRobot().getId());
			}
			break;
		}
	}
}
