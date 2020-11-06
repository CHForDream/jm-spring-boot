
package com.game.myapp.module.elite;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.user.UserBean;
import com.game.constants.DayTaskConstants;
import com.game.constants.EliteConstants;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.game.db.entity.ItemEntity;
import com.game.db.entity.RoleBattleEntity;
import com.game.db.entity.RoleEliteEntity;
import com.game.db.entity.RoleEntity;
import com.game.db.vo.EliteVo;
import com.game.generate.bean.EliteDuplicateBean;
import com.game.generate.bean.ItemBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.achieve.EAchieveBehavior;
import com.game.myapp.module.item.EItemId;
import com.game.myapp.module.item.EItemType;
import com.google.common.collect.Maps;

import buffer.CGEliteDuplicateBuyChallengeTimes.CGEliteDuplicateBuyChallengeTimesProto;
import buffer.CGEliteDuplicateBuySteps.CGEliteDuplicateBuyStepsProto;
import buffer.CGEliteDuplicateGameEnd.CGEliteDuplicateGameEndProto;
import buffer.CGEliteDuplicateGameEnd.ChessDto;
import buffer.CGEliteDuplicateGameEnd.ComboDto;
import buffer.CGEliteDuplicateGameStart.CGEliteDuplicateGameStartProto;
import buffer.CGEliteDuplicateGiveUp.CGEliteDuplicateGiveUpProto;
import buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto;
import buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto;
import buffer.GCEliteDuplicateBuySteps.GCEliteDuplicateBuyStepsProto;
import buffer.GCEliteDuplicateGameEnd.GCEliteDuplicateGameEndProto;
import buffer.GCEliteDuplicateGameStart;
import buffer.GCEliteDuplicateGameStart.GCEliteDuplicateGameStartProto;
import buffer.GCEliteDuplicateGiveUp.GCEliteDuplicateGiveUpProto;
import buffer.GCEliteDuplicateInfo.EliteDuplicateInfoDto;
import buffer.GCEliteDuplicateInfo.GCEliteDuplicateInfoProto;
import buffer.GCEliteDuplicateSyncStep.GCEliteDuplicateSyncStepProto;

public class EliteManager {

	public void CGEliteDuplicateInfo(MsgBack msgBack, UserBean userBean) {
		GCEliteDuplicateInfoProto.Builder gcEliteDuplicateInfo = GCEliteDuplicateInfoProto.newBuilder();
		gcEliteDuplicateInfo.setStatus(ErrorCodeConst.SUCCESS);
		msgBack.addBuilder(gcEliteDuplicateInfo);

		RoleEliteEntity roleEliteEntity = getRoleEliteEntity(userBean.getUid());
		TreeMap<Integer, EliteDuplicateBean> dataMap = Datas.getDataMap(EliteDuplicateBean.class);
		for (EliteDuplicateBean eliteDuplicateBean : dataMap.values()) {
			EliteDuplicateInfoDto.Builder eliteInfo = EliteDuplicateInfoDto.newBuilder();
			if (roleEliteEntity.getEliteMap().containsKey(eliteDuplicateBean.getId())) {
				EliteVo eliteVo = roleEliteEntity.getEliteMap().get(eliteDuplicateBean.getId());
				eliteInfo.setEliteId(eliteVo.getEliteId());// 精英副本Id（城市Id）
				eliteInfo.setState(eliteVo.getState());// 状态。-1未解锁；0未开始挑战；1-3关卡进度（花费挑战次数后初始为1）
				eliteInfo.setLeftChallengeTimes(eliteVo.getFreeTimes() + eliteVo.getChallengeTimes());// 剩余挑战次数(免费+购买)
				eliteInfo.setLeftChallengeBuyTimes(eliteVo.getChallengeBuyTimes());// 挑战门票剩余购买次数（每日重置，清零）
				eliteInfo.setLeftSteps(eliteVo.getSteps());// 剩余挑战步数
				eliteInfo.setLeftStepsBuyTimes(eliteVo.getStepBuyTimes());// 剩余步数购买次数
			} else {
				eliteInfo.setEliteId(eliteDuplicateBean.getId());// 精英副本Id（城市Id）
				eliteInfo.setState(EliteConstants.STATE_LOCK);// 状态。-1未解锁；0未开始挑战；1-3关卡进度（花费挑战次数后初始为1）
			}
			gcEliteDuplicateInfo.addEliteDuplicateInfo(eliteInfo);
		}
	}

	public void CGEliteDuplicateBuyChallengeTimes(MsgBack msgBack, UserBean userBean, CGEliteDuplicateBuyChallengeTimesProto msg) {
		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			GCEliteDuplicateBuyChallengeTimesProto.Builder gcEliteDuplicateBuyChallengeTimes = GCEliteDuplicateBuyChallengeTimesProto.newBuilder();
			msgBack.addBuilder(gcEliteDuplicateBuyChallengeTimes);

			RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
			if (roleEntity == null) {
				Loggers.eliteLogger.error("No RoleEntity! uid= " + userBean.getUid());
				gcEliteDuplicateBuyChallengeTimes.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}

			int eliteId = msg.getEliteId();
			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(userBean.getUid());
			EliteVo eliteVo = roleEliteEntity.getEliteMap().get(eliteId);
			if (eliteVo == null) {
				Loggers.eliteLogger.error("No EliteVo! eliteId = " + eliteId + ", uid= " + userBean.getUid());
				gcEliteDuplicateBuyChallengeTimes.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			if (eliteVo.getChallengeBuyTimes() <= 0) {
				// 没有购买次数
				gcEliteDuplicateBuyChallengeTimes.setStatus(ErrorCodeConst.ELITE_DUPLICATE_NO_CHALLENGE_BUY_TIMES);
				return;
			}

			int cashCost = GameGlobals.configManager.getEliteDupBuyCost();
			if (!GameGlobals.unitManager.cost(userBean.getUid(), EItemType.ITEM.getType(), EItemId.REWARD_CASH.getType(), cashCost,
					LogfConstants.CHANNEL_BATTLE, "elite_buy_challenge")) {
				// 钻石不足
				gcEliteDuplicateBuyChallengeTimes.setStatus(ErrorCodeConst.CASH_NOT_ENOUGH);
				return;
			}

			// 增加挑战次数
			eliteVo.setChallengeTimes(eliteVo.getChallengeTimes() + 1);
			Globals.getEntityProxy().updateAsync(roleEliteEntity);

			gcEliteDuplicateBuyChallengeTimes.setStatus(ErrorCodeConst.SUCCESS);
			gcEliteDuplicateBuyChallengeTimes.setEliteId(eliteId);
			gcEliteDuplicateBuyChallengeTimes.setLeftChallengeTimes(eliteVo.getChallengeTimes() + eliteVo.getFreeTimes());
			gcEliteDuplicateBuyChallengeTimes.setLeftBuyTimes(eliteVo.getChallengeBuyTimes());
		} finally {
			lock.unlock();
		}
	}

	public void CGEliteDuplicateSyncStep(MsgBack msgBack, UserBean userBean, CGEliteDuplicateSyncStepProto msg) {
		int eliteId = msg.getEliteId();
		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			GCEliteDuplicateSyncStepProto.Builder gcEliteDuplicateSyncStep = GCEliteDuplicateSyncStepProto.newBuilder();
			msgBack.addBuilder(gcEliteDuplicateSyncStep);

			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(userBean.getUid());
			EliteVo eliteVo = roleEliteEntity.getEliteMap().get(eliteId);
			if (eliteVo == null) {
				Loggers.eliteLogger.error("No EliteVo! eliteId = " + eliteId + ", uid= " + userBean.getUid());
				gcEliteDuplicateSyncStep.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			if (eliteVo.getState() <= EliteConstants.STATE_NOT_START) {
				// 不在战斗状态
				gcEliteDuplicateSyncStep.setStatus(ErrorCodeConst.ELITE_DUPLICATE_NOT_START);
				return;
			}

			int leftSteps = msg.getLeftSteps();
			if (leftSteps < 0 || leftSteps >= eliteVo.getSteps()) {
				// 步数异常
				gcEliteDuplicateSyncStep.setStatus(ErrorCodeConst.ELITE_DUPLICATE_INVALID_STEP);
				return;
			}

			// 更新步数
			eliteVo.setSteps(leftSteps);
			Globals.getEntityProxy().updateAsync(roleEliteEntity);

			gcEliteDuplicateSyncStep.setStatus(ErrorCodeConst.SUCCESS).setEliteId(eliteId).setLeftSteps(leftSteps);
		} finally {
			lock.unlock();
		}
	}

	public void CGEliteDuplicateGiveUp(MsgBack msgBack, UserBean userBean, CGEliteDuplicateGiveUpProto msg) {
		int eliteId = msg.getEliteId();
		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			GCEliteDuplicateGiveUpProto.Builder gcEliteDuplicateGiveUp = GCEliteDuplicateGiveUpProto.newBuilder();
			msgBack.addBuilder(gcEliteDuplicateGiveUp);

			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(userBean.getUid());
			EliteVo eliteVo = roleEliteEntity.getEliteMap().get(eliteId);
			if (eliteVo == null) {
				Loggers.eliteLogger.error("No EliteVo! eliteId = " + eliteId + ", uid= " + userBean.getUid());
				gcEliteDuplicateGiveUp.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			// 放弃挑战, 重置状态
			eliteVo.resetState();
			Globals.getEntityProxy().updateAsync(roleEliteEntity);

			gcEliteDuplicateGiveUp.setStatus(ErrorCodeConst.SUCCESS).setEliteId(eliteId);
		} finally {
			lock.unlock();
		}
	}

	public void CGEliteDuplicateGameStart(MsgBack msgBack, UserBean userBean, CGEliteDuplicateGameStartProto msg) {
		int eliteId = msg.getEliteId();
		EliteDuplicateBean eliteDuplicateBean = Datas.get(EliteDuplicateBean.class, eliteId);
		if (eliteDuplicateBean == null) {
			Loggers.eliteLogger.error("No EliteDuplicateBean! eliteId = " + eliteId + ", uid = " + userBean.getUid());
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			GCEliteDuplicateGameStartProto.Builder gcEliteDuplicateGameStart = GCEliteDuplicateGameStartProto.newBuilder();
			msgBack.addBuilder(gcEliteDuplicateGameStart);

			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(userBean.getUid());
			EliteVo eliteVo = roleEliteEntity.getEliteMap().get(eliteId);
			if (eliteVo == null) {
				Loggers.eliteLogger.error("No EliteVo! eliteId = " + eliteId + ", uid= " + userBean.getUid());
				gcEliteDuplicateGameStart.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			switch (eliteVo.getState()) {
			case EliteConstants.STATE_LOCK:
				// 状态异常
				gcEliteDuplicateGameStart.setStatus(ErrorCodeConst.ELITE_DUPLICATE_STATE_ERROR);
				return;
			case EliteConstants.STATE_NOT_START:
				// 挑战次数处理
				if (eliteVo.getFreeTimes() > 0) {// 免费次数
					eliteVo.setFreeTimes(eliteVo.getFreeTimes() - 1);
				} else if (eliteVo.getChallengeTimes() > 0) {// 购买的挑战次数
					eliteVo.setChallengeTimes(eliteVo.getChallengeTimes() - 1);
				} else {// 挑战次数不足
					gcEliteDuplicateGameStart.setStatus(ErrorCodeConst.ELITE_DUPLICATE_NO_CHALLENGE_TIME);
					return;
				}

				// 初始化经营副本状态, 数据存储
				eliteVo.setState(EliteConstants.STATE_PROGRESS_1);
				eliteVo.setSteps(eliteDuplicateBean.getStep());
				eliteVo.setStepBuyTimes(GameGlobals.configManager.getEliteDupStepsAddTimesLimit());
				Globals.getEntityProxy().updateAsync(roleEliteEntity);
				break;
			case EliteConstants.STATE_PROGRESS_1:
			case EliteConstants.STATE_PROGRESS_2:
			case EliteConstants.STATE_PROGRESS_3:
			default:
				break;
			}

			// 填充返回信息内容
			gcEliteDuplicateGameStart.setStatus(ErrorCodeConst.SUCCESS);
			gcEliteDuplicateGameStart.setEliteId(eliteId); // 精英副本Id
			gcEliteDuplicateGameStart.setTargetId(eliteDuplicateBean.getTargetIdByState(eliteVo.getState())); // 关卡Id
			gcEliteDuplicateGameStart.setLeftSteps(eliteVo.getSteps()); // 剩余总步数
			gcEliteDuplicateGameStart.setLeftStepsBuyTimes(eliteVo.getStepBuyTimes());// 剩余步数购买次数

			// 战斗数据
			RoleBattleEntity roleBattleEntity = GameGlobals.roleManager.getRoleBattleEntity(userBean.getUid());
			gcEliteDuplicateGameStart.setHeroId(roleBattleEntity.getHeroId()); // 英雄Id

			// 战前道具
			Map<Integer, Integer> itemMap = new HashMap<Integer, Integer>();
			for (int itemId : roleBattleEntity.getEliteItemIdList()) {
				if (GameGlobals.itemManager.costItem(userBean.getUid(), itemId, 1)) {
					gcEliteDuplicateGameStart.addSelectedItemId(itemId);
					itemMap.put(itemId, 1);

					// 精英副本战前道具:加步数(特殊处理)
					if (EItemId.ELITE_PRE_BATTLE_ITEM_ADD_STEP.is(itemId)) {
						ItemBean itemBean = Datas.get(ItemBean.class, itemId);
						int addSteps = itemBean.getAddAttr();

						// 更新数据库数据
						eliteVo.setSteps(eliteVo.getSteps() + addSteps);
						Globals.getEntityProxy().updateAsync(roleEliteEntity);

						// 更新步数消息数据
						gcEliteDuplicateGameStart.setLeftSteps(eliteVo.getSteps());
					}
				}
			}
			// 检查道具消耗任务
			GameGlobals.taskManager.onTask(userBean.getUid(), DayTaskConstants.TYPE_BATTLE_OUT_ITEM, roleBattleEntity.getEliteItemIdList().size());
			GameGlobals.achieveManager.onTask(userBean.getUid(), EAchieveBehavior.TYPE_BATTLE_OUT_ITEM, itemMap);

			// 道具信息(战斗中道具)
			for (int itemId : GameGlobals.itemManager.getInBattleItemIdSet()) {
				ItemEntity itemEntity = GameGlobals.itemManager.getItemEntity(userBean.getUid(), itemId);
				if (itemEntity == null) {
					gcEliteDuplicateGameStart.addItem(GCEliteDuplicateGameStart.ItemDto.newBuilder().setItemId(itemId).setItemNum(0).setFreeNum(0));
				} else {
					gcEliteDuplicateGameStart.addItem(GCEliteDuplicateGameStart.ItemDto.newBuilder().setItemId(itemId).setItemNum(itemEntity.getNum())
							.setFreeNum(itemEntity.getFreeNum()));
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public void CGEliteDuplicateGameEnd(MsgBack msgBack, UserBean userBean, CGEliteDuplicateGameEndProto msg) {
		int eliteId = msg.getEliteId();
		EliteDuplicateBean eliteDuplicateBean = Datas.get(EliteDuplicateBean.class, eliteId);
		if (eliteDuplicateBean == null) {
			Loggers.eliteLogger.error("No EliteDuplicateBean! eliteId = " + eliteId + ", uid = " + userBean.getUid());
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			GCEliteDuplicateGameEndProto.Builder gcEliteDuplicateGameEnd = GCEliteDuplicateGameEndProto.newBuilder();
			msgBack.addBuilder(gcEliteDuplicateGameEnd);

			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(userBean.getUid());
			EliteVo eliteVo = roleEliteEntity.getEliteMap().get(eliteId);
			if (eliteVo == null) {
				Loggers.eliteLogger.error("No EliteVo! eliteId = " + eliteId + ", uid= " + userBean.getUid());
				gcEliteDuplicateGameEnd.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			if (eliteVo.getState() <= EliteConstants.STATE_NOT_START) {
				// 状态异常
				gcEliteDuplicateGameEnd.setStatus(ErrorCodeConst.ELITE_DUPLICATE_STATE_ERROR);
				return;
			}

			int result = msg.getResult(); // 战斗结果. 0失败; 1胜利
			if (result == 1) {
				handleWin(userBean.getUid(), eliteVo);
			} else {
				handleLose(userBean.getUid(), eliteVo);
			}
			Globals.getEntityProxy().updateAsync(roleEliteEntity);

			// 收集棋子
			Map<Integer, Integer> chessMap = Maps.newHashMap();
			for (ChessDto chessDto : msg.getChessInfoList()) {
				chessMap.put(chessDto.getChessId(), chessDto.getChessNum());
			}
			GameGlobals.achieveManager.onTask(userBean.getUid(), EAchieveBehavior.TYPE_COLLEC_CHESS, chessMap);

			// 连击次数
			for (ComboDto comboDto : msg.getComboInfoList()) {
				EAchieveBehavior achieveType = EAchieveBehavior.valueOf(comboDto.getComboId());
				if (achieveType == null) {
					continue;
				}

				switch (achieveType) {
				case TYPE_COMBO_1:
				case TYPE_COMBO_2:
					GameGlobals.achieveManager.onTask(userBean.getUid(), achieveType, comboDto.getNum());
					break;
				default:
					break;
				}
			}

			// 合成棋子
			Map<Integer, Integer> composeMap = new HashMap<Integer, Integer>();
			for (ChessDto chessDto : msg.getComposeInfoList()) {
				composeMap.put(chessDto.getChessId(), chessDto.getChessNum());
			}
			GameGlobals.achieveManager.onTask(userBean.getUid(), EAchieveBehavior.TYPE_BATTLE_COMPOSE, composeMap);
		} finally {
			lock.unlock();
		}
	}

	public void CGEliteDuplicateBuySteps(MsgBack msgBack, UserBean userBean, CGEliteDuplicateBuyStepsProto msg) {
		int eliteId = msg.getEliteId();
		ReentrantLock lock = Globals.getLockManager().getLock(userBean.getUid());
		lock.lock();
		try {
			GCEliteDuplicateBuyStepsProto.Builder gcEliteDuplicateBuyStepsProto = GCEliteDuplicateBuyStepsProto.newBuilder();
			msgBack.addBuilder(gcEliteDuplicateBuyStepsProto);

			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(userBean.getUid());
			EliteVo eliteVo = roleEliteEntity.getEliteMap().get(eliteId);
			if (eliteVo == null) {
				Loggers.eliteLogger.error("No EliteVo! eliteId = " + eliteId + ", uid= " + userBean.getUid());
				gcEliteDuplicateBuyStepsProto.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			if (eliteVo.getState() <= EliteConstants.STATE_NOT_START) {
				// 状态异常
				gcEliteDuplicateBuyStepsProto.setStatus(ErrorCodeConst.ELITE_DUPLICATE_NOT_START);
				return;
			}

			if (eliteVo.getStepBuyTimes() <= 0) {
				// 没有购买次数
				gcEliteDuplicateBuyStepsProto.setStatus(ErrorCodeConst.ELITE_DUPLICATE_NO_STEPS_BUY_TIMES);
				return;
			}

			int cashCost = GameGlobals.configManager.getEliteDupAddStepsCost();
			if (!GameGlobals.unitManager.cost(userBean.getUid(), EItemType.ITEM.getType(), EItemId.REWARD_CASH.getType(), cashCost,
					LogfConstants.CHANNEL_BATTLE, "elite_buy_step")) {
				// 钻石不足
				gcEliteDuplicateBuyStepsProto.setStatus(ErrorCodeConst.CASH_NOT_ENOUGH);
				return;
			}

			// 加步数, 减购买次数
			eliteVo.setSteps(eliteVo.getSteps() + GameGlobals.configManager.getEliteDupAddStepsCount());
			eliteVo.setStepBuyTimes(eliteVo.getStepBuyTimes() - 1);
			Globals.getEntityProxy().updateAsync(roleEliteEntity);

			gcEliteDuplicateBuyStepsProto.setStatus(ErrorCodeConst.SUCCESS).setEliteId(eliteId).setLeftSteps(eliteVo.getSteps());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 城市关卡通关, 开启精英副本
	 * 
	 * @param uid
	 * @param cityId
	 */
	public void onCityClear(long uid, int cityId) {
		EliteDuplicateBean eliteDuplicateBean = Datas.get(EliteDuplicateBean.class, cityId);
		if (eliteDuplicateBean == null) {
//			Loggers.eliteLogger.error("No EliteDuplicateBean! id = " + cityId + ", uid = " + uid);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(uid);
			if (roleEliteEntity.getEliteMap().containsKey(cityId)) {
				Loggers.eliteLogger.warn("Elite has already open! id = " + cityId + ", uid = " + uid);
				return;
			}

			EliteVo eliteVo = new EliteVo();
			eliteVo.setEliteId(eliteDuplicateBean.getId());// 精英副本Id
			eliteVo.setFreeTimes(GameGlobals.configManager.getEliteDupDialyFreeTimes());// 免费挑战次数（每日重置，补充免费次数）
			eliteVo.setChallengeBuyTimes(GameGlobals.configManager.getEliteDupBuyTimesLimit());// 每日副本购买次数
			eliteVo.setState(EliteConstants.STATE_NOT_START);// -1为解锁；0未开始；1-3挑战进度
			roleEliteEntity.getEliteMap().put(eliteVo.getEliteId(), eliteVo);
			Globals.getEntityProxy().updateAsync(roleEliteEntity);
		} finally {
			lock.unlock();
		}
	}

	public void reset(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(uid);
			for (EliteVo eliteVo : roleEliteEntity.getEliteMap().values()) {
				// 每日免费挑战次数
				eliteVo.setFreeTimes(GameGlobals.configManager.getEliteDupDialyFreeTimes());
				// 每日副本购买次数
				eliteVo.setChallengeBuyTimes(GameGlobals.configManager.getEliteDupBuyTimesLimit());
			}
			Globals.getEntityProxy().updateAsync(roleEliteEntity);
		} finally {
			lock.unlock();
		}
	}

	public void gmOpenAll(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEliteEntity roleEliteEntity = getRoleEliteEntity(uid);
			for (EliteDuplicateBean eliteDuplicateBean : Datas.getDataMap(EliteDuplicateBean.class).values()) {
				if (roleEliteEntity.getEliteMap().containsKey(eliteDuplicateBean.getId())) {
					continue;
				}

				EliteVo eliteVo = new EliteVo();
				eliteVo.setEliteId(eliteDuplicateBean.getId());// 精英副本Id
				eliteVo.setFreeTimes(GameGlobals.configManager.getEliteDupDialyFreeTimes());// 免费挑战次数（每日重置，补充免费次数）
				eliteVo.setChallengeBuyTimes(GameGlobals.configManager.getEliteDupBuyTimesLimit());// 每日副本购买次数
				eliteVo.setState(EliteConstants.STATE_NOT_START);// -1为解锁；0未开始；1-3挑战进度
				roleEliteEntity.getEliteMap().put(eliteVo.getEliteId(), eliteVo);
			}

			Globals.getEntityProxy().updateAsync(roleEliteEntity);
		} finally {
			lock.unlock();
		}

	}

	private RoleEliteEntity getRoleEliteEntity(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEliteEntity roleEliteEntity = Globals.getEntityProxy().get(RoleEliteEntity.class, uid);
			if (roleEliteEntity == null) {
				roleEliteEntity = new RoleEliteEntity();
				roleEliteEntity.setUid(uid);
				Globals.getEntityProxy().insert(roleEliteEntity);
			}
			return roleEliteEntity;
		} finally {
			lock.unlock();
		}
	}

	private void handleWin(long uid, EliteVo eliteVo) {
		// 修改精英副本状态
		switch (eliteVo.getState()) {
		case EliteConstants.STATE_PROGRESS_1:
			eliteVo.setState(EliteConstants.STATE_PROGRESS_2);
			break;
		case EliteConstants.STATE_PROGRESS_2:
			eliteVo.setState(EliteConstants.STATE_PROGRESS_3);
			break;
		case EliteConstants.STATE_PROGRESS_3:
			eliteVo.setState(EliteConstants.STATE_NOT_START);
			break;
		}
	}

	private void handleLose(long uid, EliteVo eliteVo) {
		if (eliteVo.getSteps() <= 0) {
			// 挑战失败
			eliteVo.resetState();
		} else {
			// 状态不变, 没有数据变化, 不做任何处理
		}
	}

}
