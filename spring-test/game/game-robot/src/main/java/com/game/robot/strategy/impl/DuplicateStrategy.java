package com.game.robot.strategy.impl;

import java.util.List;

import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.model.CityInfo;
import com.game.robot.model.DupInfo;
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
import buffer.CGRoleBattleSelect.CGRoleBattleSelectProto;
import buffer.GCBattlePveResult.GCBattlePveResultProto;

@EStrategyType(strategy = EStrategy.DUPLICATE)
public class DuplicateStrategy extends LoopExecuteStrategy {
	private boolean isBattle = false;
	private int getOtherCityNum = 0;
	private int[] chessArray = { 31001, //	红色棋子
			31002, //	绿色棋子
			31003, //	蓝色棋子
			31004, //	黄色棋子
			31005,//	紫色棋子
	};

	public DuplicateStrategy(Robot robot, long delay, long min, long max) {
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
		PveCityInfo pveCityInfo = getRobot().getInfoManager().getPveCityInfo();
		if (pveCityInfo == null) {
			return;
		}
		CityInfo city = pveCityInfo.getCityInfo();
		if (city == null) {
			getOtherCityData(0, pveCityInfo);
			return;
		}
		int cityId = city.getCityId();
		List<DupInfo> dup = city.getDupInfo();
		if (dup == null || dup.size() == 0) {
			if (Config.IS_PRINT_LOG) {
				String log = String.format("[Dup][Battle][DupStart][End][RobotId: %s][DupOpen: 0]", getRobot().getId());
				Loggers.robotLogger.info(log);
			}
			getOtherCityData(cityId, pveCityInfo);
			return;
		}
		int dupId = 0;
		boolean need = true;
		// 选出一个可以打的副本id
		for (int i = 0; i < dup.size(); i++) {
			DupInfo dupInfo = dup.get(i);
			if (dupInfo == null || dupInfo.getStar() == 3) {
				continue;
			}
			need = false;
			dupId = dupInfo.getDupId();
			break;
		}
		if (need) {
			getOtherCityData(cityId, pveCityInfo);
			return;
		}
		if (dupId != 0) {
			processPveTarget(0, cityId, dupId);
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
			if (gcBattlePveResultProto.getBattleType() == 0) {
				return;
			}

			int nextTargetId = gcBattlePveResultProto.getNextTargetId();
			getRobot().getInfoManager().setPveTragerId(nextTargetId);

			if (Config.IS_PRINT_LOG) {
				String log = String.format("[Dup][Battle][DupEnd][End][RobotId: %s]", getRobot().getId());
				Loggers.robotLogger.info(log);
			}
			break;
		}
	}

	private void processPveTarget(int pveId, int cityId, int dupId) {
		if (isBattle) {
			isBattle = false;
			// 战斗中, 发结算
			sendBattlePveResultProto(pveId, cityId, dupId);
		} else {
			isBattle = true;
			if (Config.IS_PRINT_LOG) {
				String log = String.format("[Dup][Battle][DupStart][Start][RobotId: %s]", getRobot().getId());
				Loggers.robotLogger.info(log);
			}
			// 选英雄, 宠物, 道具
			sendRoleBattleSelectProto();
			// 战斗开始
			sendBattlePveStartProto(pveId, cityId, dupId);
		}
	}

	private void sendRoleBattleSelectProto() {
		CGRoleBattleSelectProto.Builder cgRoleBattleSelectProto = CGRoleBattleSelectProto.newBuilder();
		cgRoleBattleSelectProto.setHeroId(40001);// 初始英雄
		cgRoleBattleSelectProto.addItemId(221);// 锤子
		getRobot().sendHttpMessage(cgRoleBattleSelectProto.getMsgType(), cgRoleBattleSelectProto);
	}

	private void sendBattlePveStartProto(int pveId, int cityId, int dupId) {
		CGBattlePveStartProto.Builder cgBattlePveStartProto = CGBattlePveStartProto.newBuilder();
		cgBattlePveStartProto.setPveId(pveId);
		cgBattlePveStartProto.setDupId(dupId);
		cgBattlePveStartProto.setCityId(cityId);
		cgBattlePveStartProto.setBattleType(1);
		cgBattlePveStartProto.setIndex(2);
		getRobot().sendHttpMessage(cgBattlePveStartProto.getMsgType(), cgBattlePveStartProto);
	}

	private void sendBattlePveResultProto(int pveId, int cityId, int dupId) {
		if (Config.IS_PRINT_LOG) {
			String log = String.format("[Dup][Battle][DupEnd][Start][RobotId: %s]", getRobot().getId());
			Loggers.robotLogger.info(log);
		}
		CGBattlePveResultProto.Builder cgBattlePveResultProto = CGBattlePveResultProto.newBuilder();
		cgBattlePveResultProto.setResult(RandomUtil.random.nextInt(2));//(RandomUtil.random.nextInt(2));//(1);
		cgBattlePveResultProto.setLeftStep(10);
		cgBattlePveResultProto.setCostStep(20);
		cgBattlePveResultProto.setBattleType(1);
		cgBattlePveResultProto.setPveId(pveId);
		cgBattlePveResultProto.setCityId(cityId);
		cgBattlePveResultProto.setDupId(dupId);

		int n = RandomUtil.random.nextInt(3);
		if (n >= 0) {
			cgBattlePveResultProto.addScoreInfo(ChessProto.newBuilder().setChessId(10).setChessNum(1));
		}
		if (n >= 1) {
			cgBattlePveResultProto.addScoreInfo(ChessProto.newBuilder().setChessId(10).setChessNum(1));
		}
		if (n >= 2) {
			cgBattlePveResultProto.addScoreInfo(ChessProto.newBuilder().setChessId(10).setChessNum(1));
		}
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

	private void getOtherCityData(int cityId, PveCityInfo pveCityInfo) {
		// 所有的城市都选择了一遍仍然没有找到 那就退出
		if (getOtherCityNum > pveCityInfo.getCityIds().size()) {
			return;
		}
		for (int i = 0; i < pveCityInfo.getCityIds().size(); i++) {
			if (cityId == pveCityInfo.getCityIds().get(i))
				continue;
			getOtherCityNum++;
			CGPveCityInfoProto.Builder cgPveCityBuilder = CGPveCityInfoProto.newBuilder();
			cgPveCityBuilder.setCityId(pveCityInfo.getCityIds().get(i));
			this.getRobot().sendHttpMessage(cgPveCityBuilder.getMsgType(), cgPveCityBuilder);
			break;
		}
	}
}
