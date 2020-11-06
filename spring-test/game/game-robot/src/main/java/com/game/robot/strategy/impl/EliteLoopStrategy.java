package com.game.robot.strategy.impl;

import java.util.List;

import com.game.constants.EliteConstants;
import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.robot.Robot;
import com.game.robot.config.Config;
import com.game.robot.strategy.EStrategy;
import com.game.robot.strategy.EStrategyType;
import com.game.robot.strategy.LoopExecuteStrategy;
import com.game.utils.RandomUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGEliteDuplicateBuyChallengeTimes.CGEliteDuplicateBuyChallengeTimesProto;
import buffer.CGEliteDuplicateBuySteps.CGEliteDuplicateBuyStepsProto;
import buffer.CGEliteDuplicateGameEnd.CGEliteDuplicateGameEndProto;
import buffer.CGEliteDuplicateGameEnd.ChessDto;
import buffer.CGEliteDuplicateGameStart.CGEliteDuplicateGameStartProto;
import buffer.CGEliteDuplicateGiveUp.CGEliteDuplicateGiveUpProto;
import buffer.CGEliteDuplicateInfo.CGEliteDuplicateInfoProto;
import buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto;
import buffer.CGRoleBattleSelect.CGRoleBattleSelectProto;
import buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto;
import buffer.GCEliteDuplicateBuySteps.GCEliteDuplicateBuyStepsProto;
import buffer.GCEliteDuplicateGameEnd.GCEliteDuplicateGameEndProto;
import buffer.GCEliteDuplicateGameEnd.ItemDto;
import buffer.GCEliteDuplicateGameStart.GCEliteDuplicateGameStartProto;
import buffer.GCEliteDuplicateInfo.EliteDuplicateInfoDto;
import buffer.GCEliteDuplicateInfo.GCEliteDuplicateInfoProto;
import buffer.GCEliteDuplicateSyncStep.GCEliteDuplicateSyncStepProto;

@EStrategyType(strategy = EStrategy.ELITE)
public class EliteLoopStrategy extends LoopExecuteStrategy {
	private static final int[] CHESS_ARRAY = { //
			31001, //	红色棋子
			31002, //	绿色棋子
			31003, //	蓝色棋子
			31004, //	黄色棋子
			31005,//	紫色棋子
	};

	private boolean isBattle = false;
	private int eliteId = 0;// 精英副本Id
	@SuppressWarnings("unused")
	private int eliteState = 0;// 精英副本状态
	private int leftSteps = 0;// 剩余步数
	private int leftStepBuyTimes = 0;// 剩余步数购买次数

	private CGEliteDuplicateInfoProto.Builder cgEliteDuplicateInfo = CGEliteDuplicateInfoProto.newBuilder();

	public EliteLoopStrategy(Robot robot, long delay, long min, long max) {
		super(robot, delay, RandomUtil.random(min, max));
	}

	@Override
	public void doAction() {
		if (isBattle) {
			handleBattle();
			return;
		}

		if (Config.IS_PRINT_LOG) {
			Loggers.robotLogger.info("EliteLoopStrategy start. robotId = " + getRobot().getId());
		}

		// 选择精英副本
		EliteDuplicateInfoDto eliteDuplicateInfoDto = selectElite();
		if (eliteDuplicateInfoDto == null) {
			return;
		}

		// 开始战斗
		battleStart(eliteDuplicateInfoDto);
	}

	private void handleBattle() {
		int random = RandomUtil.random.nextInt(100);
		if (random < 20) {
			// 20%的概率直接结算
			sendBattleResult(RandomUtil.random.nextInt(2) == 1);
			return;
		} else if (random >= 95) {
			// 5%的概率直接放弃
			sendBattleGiveUp();
			return;
		}

		// 75%消耗步数
		if (leftSteps > 0) {
			// 消耗步数
			costSteps();
			return;
		}

		if (leftStepBuyTimes > 0) {
			// 买步数
			buySteps();
			return;
		}

		// 没有步数, 战斗失败
		sendBattleResult(false);
	}

	private void sendBattleGiveUp() {
		CGEliteDuplicateGiveUpProto.Builder cgEliteDuplicateGiveUp = CGEliteDuplicateGiveUpProto.newBuilder();
		cgEliteDuplicateGiveUp.setEliteId(eliteId);
		sendHttpMessage(cgEliteDuplicateGiveUp.getMsgType(), cgEliteDuplicateGiveUp);
	}

	private void buySteps() {
		// 先给点钱
		sendChatGm("!item 3 4 1000");

		CGEliteDuplicateBuyStepsProto.Builder cgEliteDuplicateBuyStepsProto = CGEliteDuplicateBuyStepsProto.newBuilder();
		cgEliteDuplicateBuyStepsProto.setEliteId(eliteId);
		sendHttpMessage(cgEliteDuplicateBuyStepsProto.getMsgType(), cgEliteDuplicateBuyStepsProto);
	}

	private void costSteps() {
		int randomStepCost = RandomUtil.random.nextInt(5);
		randomStepCost = Math.min(randomStepCost, leftSteps);
		leftSteps -= randomStepCost;
		CGEliteDuplicateSyncStepProto.Builder cgEliteDuplicateSyncStepProto = CGEliteDuplicateSyncStepProto.newBuilder();
		cgEliteDuplicateSyncStepProto.setEliteId(eliteId);
		cgEliteDuplicateSyncStepProto.setLeftSteps(leftSteps);
		sendHttpMessage(cgEliteDuplicateSyncStepProto.getMsgType(), cgEliteDuplicateSyncStepProto);
	}

	@Override
	public void onResponse(int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.GCEliteDuplicateGameStart:// 精英副本.游戏开始
			GCEliteDuplicateGameStartProto gcEliteDuplicateGameStart = (GCEliteDuplicateGameStartProto) msg;
			eliteId = gcEliteDuplicateGameStart.getEliteId();
			leftSteps = gcEliteDuplicateGameStart.getLeftSteps();
			leftStepBuyTimes = gcEliteDuplicateGameStart.getLeftStepsBuyTimes();
			break;

		case MsgType.GCEliteDuplicateBuyChallengeTimes:// 精英副本.购买次数
			GCEliteDuplicateBuyChallengeTimesProto gcEliteDuplicateBuyChallengeTimes = (GCEliteDuplicateBuyChallengeTimesProto) msg;
			gcEliteDuplicateBuyChallengeTimes.getEliteId();
			break;
		case MsgType.GCEliteDuplicateSyncStep:// 精英副本.同步步数
			GCEliteDuplicateSyncStepProto gcEliteDuplicateSyncStep = (GCEliteDuplicateSyncStepProto) msg;
			leftSteps = gcEliteDuplicateSyncStep.getLeftSteps();
			break;
		case MsgType.GCEliteDuplicateBuySteps:// 精英副本.购买步数
			GCEliteDuplicateBuyStepsProto gcEliteDuplicateBuySteps = (GCEliteDuplicateBuyStepsProto) msg;
			leftSteps = gcEliteDuplicateBuySteps.getLeftSteps();
			break;
		case MsgType.GCEliteDuplicateGiveUp:// 精英副本.放弃挑战
			isBattle = false;
			Loggers.robotLogger.info("BattlePveLoopStrategy end 2. Give up! robotId = " + getRobot().getId());
			break;
		case MsgType.GCEliteDuplicateGameEnd:// 精英副本.游戏结束
			GCEliteDuplicateGameEndProto gcEliteDuplicateGameEnd = (GCEliteDuplicateGameEndProto) msg;
			int status = gcEliteDuplicateGameEnd.getStatus();
			int eliteId2 = gcEliteDuplicateGameEnd.getEliteId();
			int result = gcEliteDuplicateGameEnd.getResult();
			List<ItemDto> rewardItemList = gcEliteDuplicateGameEnd.getRewardItemList();

			isBattle = false;
			Loggers.robotLogger.info("BattlePveLoopStrategy end. robotId = " + getRobot().getId() + ", status = " + status + ", eliteId = " + eliteId2
					+ ", result = " + result + ", rewardItemList = " + rewardItemList);
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

	private EliteDuplicateInfoDto selectElite() {
		// 获取最新的精英副本信息
		sendHttpMessage(cgEliteDuplicateInfo.getMsgType(), cgEliteDuplicateInfo);

		// 没精英副本,开启所有精英副本
		GCEliteDuplicateInfoProto gcEliteDuplicateInfoProto = getRobot().getInfoManager().getGcEliteDuplicateInfoProto();
		if (gcEliteDuplicateInfoProto == null || gcEliteDuplicateInfoProto.getEliteDuplicateInfoCount() == 0) {
			// 开启所有的精英副本
			sendChatGm("!eliteall");

			// 获取最新的精英副本信息
			sendHttpMessage(cgEliteDuplicateInfo.getMsgType(), cgEliteDuplicateInfo);

			gcEliteDuplicateInfoProto = getRobot().getInfoManager().getGcEliteDuplicateInfoProto();
			if (gcEliteDuplicateInfoProto.getEliteDuplicateInfoCount() == 0) {
				Loggers.robotLogger.info("BattlePveLoopStrategy end 3. No elite duplicate! robotId = " + getRobot().getId());
				return null;
			}
		}

		// 有在挑战的精英副本, 直接返回
		for (EliteDuplicateInfoDto eliteDuplicateInfoDto : gcEliteDuplicateInfoProto.getEliteDuplicateInfoList()) {
			if (eliteDuplicateInfoDto.getState() > 0) {
				return eliteDuplicateInfoDto;
			}
		}

		// 检测有没有挑战次数
		for (EliteDuplicateInfoDto eliteDuplicateInfoDto : gcEliteDuplicateInfoProto.getEliteDuplicateInfoList()) {
			if (eliteDuplicateInfoDto.getLeftChallengeTimes() > 0) {
				return eliteDuplicateInfoDto;
			}
		}

		// 购买挑战次数
		for (EliteDuplicateInfoDto eliteDuplicateInfoDto : gcEliteDuplicateInfoProto.getEliteDuplicateInfoList()) {
			if (eliteDuplicateInfoDto.getLeftChallengeBuyTimes() > 0) {
				// 购买一次
				CGEliteDuplicateBuyChallengeTimesProto.Builder cgEliteDuplicateBuyChallengeTimes = CGEliteDuplicateBuyChallengeTimesProto.newBuilder();
				cgEliteDuplicateBuyChallengeTimes.setEliteId(eliteDuplicateInfoDto.getEliteId());
				sendHttpMessage(cgEliteDuplicateBuyChallengeTimes.getMsgType(), cgEliteDuplicateBuyChallengeTimes);

				// 获取最新的精英副本信息
				sendHttpMessage(cgEliteDuplicateInfo.getMsgType(), cgEliteDuplicateInfo);
				return eliteDuplicateInfoDto;
			}
		}

		// 没有挑战次数, 也没有购买次数, 重置所有精英副本
		sendChatGm("!elitereset");
		// 获取最新的精英副本信息
		sendHttpMessage(cgEliteDuplicateInfo.getMsgType(), cgEliteDuplicateInfo);

		// 重新选关
		return selectElite();
	}

	private void battleStart(EliteDuplicateInfoDto eliteDuplicateInfoDto) {
		if (eliteDuplicateInfoDto == null) {
			Loggers.robotLogger.error("No EliteDuplicateInfoDto! eliteId = " + eliteDuplicateInfoDto);
			return;
		}

		switch (eliteDuplicateInfoDto.getState()) {
		case EliteConstants.STATE_NOT_START:
		case EliteConstants.STATE_PROGRESS_1:
		case EliteConstants.STATE_PROGRESS_2:
		case EliteConstants.STATE_PROGRESS_3:
			isBattle = true;
			eliteId = eliteDuplicateInfoDto.getEliteId();
			eliteState = eliteDuplicateInfoDto.getState();
			leftSteps = eliteDuplicateInfoDto.getLeftSteps();
			leftStepBuyTimes = eliteDuplicateInfoDto.getLeftStepsBuyTimes();

			// 选英雄, 宠物, 道具
			sendRoleBattleSelectProto();
			// 战斗开始
			sendBattleStart(eliteDuplicateInfoDto.getEliteId());
			break;
		case EliteConstants.STATE_LOCK:
		default:
			break;
		}
	}

	private void sendBattleStart(int eliteId) {
		CGEliteDuplicateGameStartProto.Builder cgEliteDuplicateGameStartProto = CGEliteDuplicateGameStartProto.newBuilder();
		cgEliteDuplicateGameStartProto.setEliteId(eliteId);
		getRobot().sendHttpMessage(cgEliteDuplicateGameStartProto.getMsgType(), cgEliteDuplicateGameStartProto);
	}

	private void sendBattleResult(boolean isWin) {
		CGEliteDuplicateGameEndProto.Builder cgEliteDuplicateGameEndProto = CGEliteDuplicateGameEndProto.newBuilder();
		cgEliteDuplicateGameEndProto.setEliteId(getRobot().getInfoManager().getEliteId());
		cgEliteDuplicateGameEndProto.setResult(isWin ? 1 : 0);

		// 随机棋子信息
		int randomChessId1 = CHESS_ARRAY[RandomUtil.random.nextInt(CHESS_ARRAY.length)];
		int randomChessId2 = CHESS_ARRAY[RandomUtil.random.nextInt(CHESS_ARRAY.length)];
		while (randomChessId1 == randomChessId2) {
			randomChessId2 = CHESS_ARRAY[RandomUtil.random.nextInt(CHESS_ARRAY.length)];
		}
		cgEliteDuplicateGameEndProto.addChessInfo(ChessDto.newBuilder().setChessId(randomChessId1).setChessNum(RandomUtil.random(15, 25)));
		cgEliteDuplicateGameEndProto.addChessInfo(ChessDto.newBuilder().setChessId(randomChessId2).setChessNum(RandomUtil.random(15, 25)));

		//		repeated ChessDto composeInfo = 5; // 合成的棋子信息
		//		repeated ComboDto comboInfo = 6; // 连击信息

		getRobot().sendHttpMessage(cgEliteDuplicateGameEndProto.getMsgType(), cgEliteDuplicateGameEndProto);
	}

	private void sendRoleBattleSelectProto() {
		CGRoleBattleSelectProto.Builder cgRoleBattleSelectProto = CGRoleBattleSelectProto.newBuilder();
		cgRoleBattleSelectProto.setHeroId(40001);// 初始英雄
		// 精英副本战前道具: 603,605,606
		cgRoleBattleSelectProto.addItemId(603);
		cgRoleBattleSelectProto.addItemId(605);
		cgRoleBattleSelectProto.addItemId(606);
		cgRoleBattleSelectProto.setBattleType(2);// 精英副本
		getRobot().sendHttpMessage(cgRoleBattleSelectProto.getMsgType(), cgRoleBattleSelectProto);
	}
}
