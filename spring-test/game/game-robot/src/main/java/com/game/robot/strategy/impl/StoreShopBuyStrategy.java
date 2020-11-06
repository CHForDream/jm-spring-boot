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

import buffer.CGShopBuyMsg.CGShopBuyProto;
import buffer.GCShopBuyMsg.GCShopBuyProto;

@EStrategyType(strategy = EStrategy.STORE_SHOPBUY)
public class StoreShopBuyStrategy extends LoopExecuteStrategy {

	private int[] shopIds = new int[] { 3, 5, 14, 16, 21, 22, 23, 24, 25, 31, 32, 33, 34 };

	public StoreShopBuyStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		int shopIndex = RandomUtil.random(0, shopIds.length - 1);
		if (Config.IS_PRINT_LOG) {
			String logger = "[Store][StoreInfoStrategy][Start][CGShopBuyProto][robotId:%d][shopId:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId(), shopIds[shopIndex]));
		}

		CGShopBuyProto.Builder cgShopBuyProto = CGShopBuyProto.newBuilder();
		cgShopBuyProto.setShopId(shopIds[shopIndex]);
		cgShopBuyProto.setNum(1);
		cgShopBuyProto.setCostType(4);
		getRobot().sendHttpMessage(cgShopBuyProto.getMsgType(), cgShopBuyProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCShopBuyMsg: {
			GCShopBuyProto gcShopBuyProto = (GCShopBuyProto) msg;
			if (Config.IS_PRINT_LOG) {
				String logger = "[Store][StoreInfoStrategy][End][GCShopBuyProto][robotId:%d][status:%d][shopId:%d][num:%d]";
				Loggers.robotLogger
						.info(String.format(logger, getRobot().getId(), gcShopBuyProto.getStatus(), gcShopBuyProto.getShopId(), gcShopBuyProto.getNum()));
			}
		}
			break;
		default:
			break;
		}
	}
}
