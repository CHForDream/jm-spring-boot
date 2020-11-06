package com.game.robot.strategy.impl;

import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.model.PveCityInfo;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.game.utils.RandomUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGBattlePveResult.CGBattlePveResultProto;
import buffer.CGBattlePveResult.ChessProto;
import buffer.CGBattlePveStart.CGBattlePveStartProto;
import buffer.CGPveCityInfoMsg.CGPveCityInfoProto;
import buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto;
import buffer.CGRoleBattleSelect.CGRoleBattleSelectProto;
import buffer.GCBattlePveResult.GCBattlePveResultProto;
import buffer.GCPveCityAwardMsg.GCPveCityAwardProto;
import buffer.GCPveCityUnlockMsg.GCPveCityUnlockProto;

@EStrategyType(strategy = EStrategy.BATTLE_PVE)
public class PveCityStrategy extends LoopExecuteStrategy {
	private boolean isBattle = false;
	private int[] chessArray = { 31001, //	红色棋子
			31002, //	绿色棋子
			31003, //	蓝色棋子
			31004, //	黄色棋子
			31005,//	紫色棋子
	};

	public PveCityStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void setStartTime(long startTime) {
		if (!isBattle) {
			// 战斗未开始, 需要修改战斗开始时间
			super.setStartTime(startTime);
		}
	}

	@Override
	public void doAction() {
		if (getRobot().getInfoManager().getPveTragerId() == -1) {
			if (Config.IS_PRINT_LOG) {
				String log = String.format("[PVE][Battle][PveStart][End][RobotId: %s][PveTragerId: -1]", getRobot().getId());
				Loggers.robotLogger.info(log);
			}
			return;
		}
		PveCityInfo pveCityInfo = getRobot().getInfoManager().getPveCityInfo();
		if (pveCityInfo == null) {
			return;
		}
		int pveId = pveCityInfo.getPveId();
		int status = pveCityInfo.getPveStatus();

		if (status == 1) {
			processPveTarget();
		} else if (status == 3) {
			// 已完成需要解锁
			sendUnlockCity(pveId);
		}
	}

	@Override
	public void afterAction() {
		if (!isBattle) {
			// 战斗已结算, 统计用例时间
			super.afterAction();
		}
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCBattlePveResult:
			GCBattlePveResultProto gcBattlePveResultProto = (GCBattlePveResultProto) msg;
			if (gcBattlePveResultProto.getBattleType() == 1) {
				return;
			}
			int nextTargetId = gcBattlePveResultProto.getNextTargetId();
			getRobot().getInfoManager().setPveTragerId(nextTargetId);

			if (Config.IS_PRINT_LOG) {
				String log = String.format("[PVE][Battle][PveEnd][End][RobotId: %s]", getRobot().getId());
				Loggers.robotLogger.info(log);
			}

			updatePveCity();
			break;
		case MsgType.GCPveCityAwardMsg:
			GCPveCityAwardProto gcPveCityAwardProto = (GCPveCityAwardProto) msg;
			gcPveCityAwardProto.getChessAwardsList();
			gcPveCityAwardProto.getHardAwardsList();
			if (Config.IS_PRINT_LOG) {
				String log = String.format("[PVE][Battle][Result][Award][End][RobotId: %s]", getRobot().getId());
				Loggers.robotLogger.info(log);
			}
			break;
		case MsgType.GCPveCityUnlockMsg: {
			GCPveCityUnlockProto gcPveCityUnlockProto = (GCPveCityUnlockProto) msg;

			PveCityInfo pveCityInfo = getRobot().getInfoManager().getPveCityInfo();
			pveCityInfo.setCityId(gcPveCityUnlockProto.getCityId());
			pveCityInfo.setPveId(gcPveCityUnlockProto.getPveId());
			pveCityInfo.setPveStatus(gcPveCityUnlockProto.getPveStatus());
			if (Config.IS_PRINT_LOG) {
				String log = String.format("[PVE][Unlock][End][RobotId: %s]", getRobot().getId());
				Loggers.robotLogger.info(log);
			}
			break;
		}
		}
	}

	private void processPveTarget() {
		if (isBattle) {
			isBattle = false;
			// 战斗中, 发结算
			sendBattlePveResultProto();
		} else {
			isBattle = true;
			if (Config.IS_PRINT_LOG) {
				String log = String.format("[PVE][Battle][PveStart][Start][RobotId: %s]", getRobot().getId());
				Loggers.robotLogger.info(log);
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
		if (Config.IS_PRINT_LOG) {
			String log = String.format("[PVE][Battle][PveEnd][Start][RobotId: %s]", getRobot().getId());
			Loggers.robotLogger.info(log);
		}
		CGBattlePveResultProto.Builder cgBattlePveResultProto = CGBattlePveResultProto.newBuilder();
		cgBattlePveResultProto.setPveId(getRobot().getInfoManager().getPveCityInfo().getPveId());
		cgBattlePveResultProto.setResult(RandomUtil.random.nextInt(2));//(RandomUtil.random.nextInt(2));//(1);
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

	private void sendUnlockCity(int curPveId) {
		if (Config.IS_PRINT_LOG) {
			String log = String.format("[PVE][Unlock][Start][RobotId: %s]", getRobot().getId());
			Loggers.robotLogger.info(log);
		}
		CGPveCityUnlockProto.Builder cgPveCityUnlockProto = CGPveCityUnlockProto.newBuilder();
		cgPveCityUnlockProto.setPveId(curPveId + 1);
		getRobot().sendHttpMessage(cgPveCityUnlockProto.getMsgType(), cgPveCityUnlockProto);
	}

	private void updatePveCity() {
		CGPveCityInfoProto.Builder cgPveCityBuilder = CGPveCityInfoProto.newBuilder();
		this.getRobot().sendHttpMessage(cgPveCityBuilder.getMsgType(), cgPveCityBuilder);
	}
}
