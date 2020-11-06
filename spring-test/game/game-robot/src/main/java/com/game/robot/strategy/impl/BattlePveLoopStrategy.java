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

import buffer.CGBattlePveResult.CGBattlePveResultProto;
import buffer.CGBattlePveResult.ChessProto;
import buffer.CGBattlePveStart.CGBattlePveStartProto;
import buffer.CGRoleBattleSelect.CGRoleBattleSelectProto;
import buffer.GCBattlePveResult.GCBattlePveResultProto;

@EStrategyType(strategy = EStrategy.BATTLE_PVE)
public class BattlePveLoopStrategy extends LoopExecuteStrategy {
	private boolean isBattle = false;
	private int[] chessArray = { 31001, //	红色棋子
			31002, //	绿色棋子
			31003, //	蓝色棋子
			31004, //	黄色棋子
			31005,//	紫色棋子
	};

	public BattlePveLoopStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (isBattle) {
			isBattle = false;

			// 战斗中, 发结算
			sendBattlePveResultProto();
		} else {
			isBattle = true;

			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("BattlePveLoopStrategy start. robotId = " + getRobot().getId());
			}

			if (getRobot().getInfoManager().getPveTragerId() == -1) {
				Loggers.robotLogger.info("BattlePveLoopStrategy end2. No target! robotId = " + getRobot().getId());
				return;
			}

			// 选英雄, 宠物, 道具
			sendRoleBattleSelectProto();
			// 战斗开始
			sendBattlePveStartProto();
		}
	}

	private void sendRoleBattleSelectProto() {
		CGRoleBattleSelectProto.Builder cgRoleBattleSelectProto = CGRoleBattleSelectProto.newBuilder();
		cgRoleBattleSelectProto.setHeroId(40001);// 初始英雄
		cgRoleBattleSelectProto.addItemId(221);// 锤子
		getRobot().sendHttpMessage(cgRoleBattleSelectProto.getMsgType(), cgRoleBattleSelectProto);
	}

	private void sendBattlePveStartProto() {
		CGBattlePveStartProto.Builder cgBattlePveStartProto = CGBattlePveStartProto.newBuilder();
		cgBattlePveStartProto.setPveId(getRobot().getInfoManager().getPveCityInfo().getPveId());
		getRobot().sendHttpMessage(cgBattlePveStartProto.getMsgType(), cgBattlePveStartProto);
	}

	private void sendBattlePveResultProto() {
		CGBattlePveResultProto.Builder cgBattlePveResultProto = CGBattlePveResultProto.newBuilder();
		cgBattlePveResultProto.setPveId(getRobot().getInfoManager().getPveCityInfo().getPveId());
		cgBattlePveResultProto.setResult(RandomUtil.random.nextInt(2));
		cgBattlePveResultProto.setLeftStep(1);
		cgBattlePveResultProto.setCostStep(20);
		// 随机棋子信息
		int randomChessId1 = chessArray[RandomUtil.random.nextInt(chessArray.length)];
		int randomChessId2 = chessArray[RandomUtil.random.nextInt(chessArray.length)];
		while (randomChessId1 == randomChessId2) {
			randomChessId2 = chessArray[RandomUtil.random.nextInt(chessArray.length)];
		}
		cgBattlePveResultProto.addChessInfo(ChessProto.newBuilder().setChessId(randomChessId1).setChessNum(RandomUtil.random(15, 25)));
		cgBattlePveResultProto.addChessInfo(ChessProto.newBuilder().setChessId(randomChessId2).setChessNum(RandomUtil.random(15, 25)));
		getRobot().sendHttpMessage(cgBattlePveResultProto.getMsgType(), cgBattlePveResultProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCBattlePveResult:
			GCBattlePveResultProto gcBattlePveResultProto = (GCBattlePveResultProto) msg;
//			optional int32 result = 3;// 战斗结果. 0失败; 1胜利
//			optional int32 targetId = 4;// 当前完成的关卡Id
//			optional int32 nextTargetId = 5;// 下一个关卡Id, 没有关卡为-1
			int nextTargetId = gcBattlePveResultProto.getNextTargetId();
			getRobot().getInfoManager().setPveTragerId(nextTargetId);

			if (Config.IS_PRINT_LOG) {
				Loggers.robotLogger.info("BattlePveLoopStrategy end1. robotId = " + getRobot().getId());
			}
			break;
		}
	}

	@Override
	public void setStartTime(long startTime) {
		if (!isBattle) {
			// 战斗未开始, 需要修改战斗开始时间
			super.setStartTime(startTime);
		}
	}

	@Override
	public void afterAction() {
		if (!isBattle) {
			// 战斗已结算, 统计用例时间
			super.afterAction();
		}
	}
}
