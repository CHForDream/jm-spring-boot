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

import buffer.CGGetLuckDrawInfoMsg.CGGetLuckDrawInfoProto;
import buffer.CGGetMonthCardInfoMsg.CGGetMonthCardInfoProto;
import buffer.CGGetPayInfoMsg.CGGetPayInfoProto;
import buffer.CGShopBuyMsg.CGShopBuyProto;
import buffer.GCGetMonthCardInfoMsg.GCGetMonthCardInfoProto;
import buffer.GCGetPayInfoMsg.GCGetPayInfoProto;

@EStrategyType(strategy = EStrategy.STORE_INFO)
public class StoreInfoStrategy extends LoopExecuteStrategy {

	private int[] shopIds = new int[] { 3, 5, 14, 16, 21, 22, 23, 24, 25, 31, 32, 33, 34 };

	public StoreInfoStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
//		// 打开商城
		getMonthCardInfo();
		getPayInfo();
//		randomOperation();
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCGetMonthCardInfoMsg: {
			GCGetMonthCardInfoProto gcGetMonthCardInfoProto = (GCGetMonthCardInfoProto) msg;
			int status = gcGetMonthCardInfoProto.getStatus();
			if (status != StatusCode.SUCCESS) {
				Loggers.robotLogger
						.error(String.format("[Store][StoreInfoStrategy][End][GCGetMonthCardInfoProto][robotId:%d][status:%d]", getRobot().getId(), status));
				return;
			}
			if (Config.IS_PRINT_LOG) {
				String roleInfo = "[Store][StoreInfoStrategy][End][GCGetMonthCardInfoProto][monthCardAendTime:%d][monthCardBEndTime:%d][subscribeMonthCardB:%d][noPowerLimitReceiveNum:%d]";
				String logger = String.format(roleInfo, getRobot().getId(), gcGetMonthCardInfoProto.getMonthCardAendTime(),
						gcGetMonthCardInfoProto.getMonthCardBEndTime(), gcGetMonthCardInfoProto.getSubscribeMonthCardB(),
						gcGetMonthCardInfoProto.getNoPowerLimitReceiveNum());
				Loggers.robotLogger.info(logger);
			}
		}
			break;
		case MsgType.GCGetPayInfoMsg: {
			GCGetPayInfoProto gcGetPayInfoProto = (GCGetPayInfoProto) msg;
			if (Config.IS_PRINT_LOG) {
				String logger = "[Store][StoreInfoStrategy][End][GCGetPayInfoProto][robotId:%d][PayinfosNum:%d]";
				Loggers.robotLogger.info(String.format(logger, getRobot().getId(), gcGetPayInfoProto.getPayInfosCount()));
			}
		}
			break;
//		case MsgType.GCGetLuckDrawInfoMsg: {
//			GCGetLuckDrawInfoProto gcGetLuckDrawInfoProto = (GCGetLuckDrawInfoProto) msg;
//			if (Config.IS_PRINT_LOG) {
//				String logger = "[Store][StoreInfoStrategy][End][GCGetLuckDrawInfoProto][robotId:%d][status:%d]";
//				Loggers.robotLogger.info(String.format(logger, getRobot().getId(), gcGetLuckDrawInfoProto.getStatus()));
//			}
//
//			luckDraw();
//		}
//			break;
//		case MsgType.GCShopBuyMsg: {
//			GCShopBuyProto gcShopBuyProto = (GCShopBuyProto) msg;
//			if (Config.IS_PRINT_LOG) {
//				String logger = "[Store][StoreInfoStrategy][End][GCShopBuyProto][robotId:%d][status:%d][shopId:%d][num:%d]";
//				Loggers.robotLogger.info(String.format(logger, getRobot().getId(), gcShopBuyProto.getStatus(), gcShopBuyProto.getShopId(),
//						gcShopBuyProto.getNum()));
//			}
//			afterAction();
//		}
//			break;
//		case MsgType.GCLuckDrawMsg: {
//			GCLuckDrawMsgProto gcLuckDrawMsgProto = (GCLuckDrawMsgProto) msg;
//			if (Config.IS_PRINT_LOG) {
//				String logger = "[Store][StoreInfoStrategy][End][GCLuckDrawMsgProto][robotId:%d][Status:%d][rewardsNum:%d]";
//				Loggers.robotLogger.info(String.format(logger, getRobot().getId(), gcLuckDrawMsgProto.getStatus(), gcLuckDrawMsgProto.getRewardsCount()));
//			}
//			afterAction();
//		}
//			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unused")
	private void randomOperation() {
		int opId = RandomUtil.random(0, 5);
		switch (opId) {
		case 0:
			getMonthCardInfo();
			getPayInfo();
			break;
		case 1:
			getMonthCardInfo();
			break;
		case 2:
			getPayInfo();
			break;
		case 3:
			getLuckDrawInfo();
			break;
		case 4:
			shopBuy();
			break;
		case 5:
//			pay();
			break;
		default:
			break;
		}
	}

	/**
	 * 获取月卡信息
	 */
	private void getMonthCardInfo() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[Store][StoreInfoStrategy][Start][CGGetMonthCardInfoProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId()));
		}

		CGGetMonthCardInfoProto.Builder cgGetMonthCardInfoProto = CGGetMonthCardInfoProto.newBuilder();
		getRobot().sendHttpMessage(cgGetMonthCardInfoProto.getMsgType(), cgGetMonthCardInfoProto);
	}

	/**
	 * 获取支付信息
	 */
	private void getPayInfo() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[Store][StoreInfoStrategy][Start][CGGetPayInfoProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId()));
		}

		CGGetPayInfoProto.Builder cgGetPayInfoProto = CGGetPayInfoProto.newBuilder();
		cgGetPayInfoProto.setPlatform(1);
		getRobot().sendHttpMessage(cgGetPayInfoProto.getMsgType(), cgGetPayInfoProto);
	}

	/**
	 * 夺宝界面
	 */
	private void getLuckDrawInfo() {
		if (Config.IS_PRINT_LOG) {
			String logger = "[Store][StoreInfoStrategy][Start][CGGetLuckDrawInfoProto][robotId:%d]";
			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId()));
		}

		CGGetLuckDrawInfoProto.Builder cgGetLuckDrawInfoProto = CGGetLuckDrawInfoProto.newBuilder();
		getRobot().sendHttpMessage(cgGetLuckDrawInfoProto.getMsgType(), cgGetLuckDrawInfoProto);
	}

	/**
	 * 商城购买
	 */
	private void shopBuy() {
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

//	/**
//	 * 夺宝中抽奖
//	 */
//	private void luckDraw() {
//		int type = RandomUtil.random(1, 2) == 1 ? 1 : 10;
//		if (Config.IS_PRINT_LOG) {
//			String logger = "[Store][StoreInfoStrategy][Start][CGLuckDrawMsgProto][robotId:%d][type:%d]";
//			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId(), type));
//		}
//
//		CGLuckDrawMsgProto.Builder cgLuckDrawMsgProto = CGLuckDrawMsgProto.newBuilder();
//		cgLuckDrawMsgProto.setType(type);
//		getRobot().sendHttpMessage(cgLuckDrawMsgProto.getMsgType(), cgLuckDrawMsgProto);
//	}

//	/**
//	 * 充值
//	 */
//	private void pay() {
//		int itemId = RandomUtil.random(1, 9);
//		if (Config.IS_PRINT_LOG) {
//			String logger = "[Store][StoreInfoStrategy][Start][Pay][robotId:%d][itemId:%d]";
//			Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId(), itemId));
//
//		}
//		String logger = "[Store][StoreInfoStrategy][Start][Pay][robotId:%d][itemId:%d]";
//		Loggers.robotLogger.info(String.format(logger.toString(), getRobot().getId(), itemId));
//
//		PayInfo info = createPayInfo(itemId, getRobot().getUid());
//		getRobot().sendPayHttpMessage(JsonUtil.toJson(info));
//	}
//
//	/**
//	 * 创建假订单
//	 */
//	private PayInfo createPayInfo(int itemId, long uid) {
//		PayInfo pay = new PayInfo();
//		pay.setOrderid(String.valueOf(System.nanoTime()));
//		pay.setGssid(1);
//		pay.setCharid(uid);
//		pay.setGateway("2098161931");
//		pay.setItemid(String.valueOf(itemId));
//		pay.setPoint(100);
//		pay.setGift_point(10);
//		pay.setCreatetime((int) (System.currentTimeMillis() / 1000l));
//		pay.setPaytime((int) (System.currentTimeMillis() / 1000l));
//		pay.setItems("");
//		return pay;
//	}
}
