package com.game.myapp.module.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.BattleDupResultLog;
import com.game.common.logf.impl.BattleDupStartLog;
import com.game.common.logf.impl.BattlePveResultLog;
import com.game.common.logf.impl.BattlePveStartLog;
import com.game.common.user.UserBean;
import com.game.constants.DayTaskConstants;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.constants.UserConstants;
import com.game.core.handler.MsgBack;
import com.game.core.lock.LockManager;
import com.game.db.entity.ItemEntity;
import com.game.db.entity.PveCityEntity;
import com.game.db.entity.PveCityEntity.Duplicate;
import com.game.db.entity.RoleBattleEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.DuplicateBean;
import com.game.generate.bean.PveBean;
import com.game.generate.bean.ScoreconfigBean;
import com.game.generate.bean.TargetBean;
import com.game.generate.bean.WinningStreakBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.achieve.EAchieveBehavior;
import com.game.myapp.module.activity.EActivityLogicType;
import com.game.myapp.module.function.ISystemId;
import com.game.myapp.module.guide.GuideConst;
import com.game.myapp.module.item.EItemId;
import com.game.myapp.module.item.EItemType;
import com.game.myapp.module.item.ItemConstants;
import com.game.myapp.module.pvecity.pveenum.EPveStatus;
import com.game.myapp.module.role.EPowerChannel;
import com.game.utils.TimeUtils;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;
import com.google.common.collect.Maps;

import buffer.CGBattlePveFailedAddStepMsg.CGBattlePveFailedAddStepProto;
import buffer.CGBattlePveItemList.CGBattlePveItemListProto;
import buffer.CGBattlePveResult.CGBattlePveResultProto;
import buffer.CGBattlePveResult.ChessProto;
import buffer.CGBattlePveResult.ComboProto;
import buffer.CGBattlePveStart.CGBattlePveStartProto;
import buffer.CGPveBuyPowerMsg.CGPveBuyPowerProto;
import buffer.GCBattlePveFailedAddStepMsg.GCBattlePveFailedAddStepProto;
import buffer.GCBattlePveItemList;
import buffer.GCBattlePveItemList.GCBattlePveItemListProto;
import buffer.GCBattlePveItemList.PreBattleItemVo;
import buffer.GCBattlePveResult.GCBattlePveResultProto;
import buffer.GCBattlePveResult.ItemProto;
import buffer.GCBattlePveStart;
import buffer.GCBattlePveStart.ChessVo;
import buffer.GCBattlePveStart.GCBattlePveStartProto;
import buffer.GCBattlePveStart.GCBattlePveStartProto.Builder;
import buffer.GCPowerGetInfoMsg.GCPowerGetInfoProto;
import buffer.GCPveBuyPowerMsg.GCPveBuyPowerProto;

/**
 * @author pky
 *
 */
public class PveManager {

	private TreeMap<Integer, WinningStreakBean> winStreakBeanMap = new TreeMap<Integer, WinningStreakBean>();

	public PveManager() {
		for (WinningStreakBean bean : Datas.getDataMap(WinningStreakBean.class).values()) {
			winStreakBeanMap.put(bean.getNumber(), bean);
		}
	}

	public void CGBattlePveStart(MsgBack msgBack, UserBean userBean, CGBattlePveStartProto msg) {
		RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
		if (roleEntity == null) {
			Loggers.battleLogger.error("No roleEntity! uid = " + userBean.getUid());
			return;
		}

		ReentrantLock lock = LockManager.getInstance().getLock(userBean.getUid());
		lock.lock();
		try {
			GCBattlePveStartProto.Builder gcBattlePveStartProto = GCBattlePveStartProto.newBuilder();
			msgBack.addBuilder(gcBattlePveStartProto);
			long oprTime = System.currentTimeMillis();
			if (msg.getPveId() == userBean.getUserInfoVo().getPveId() && msg.getDupId() == userBean.getUserInfoVo().getDupId()
					&& oprTime - userBean.getOprStartTime() < UserConstants.OPR_TIME) {
				gcBattlePveStartProto.setStatus(ErrorCodeConst.ERROR_PARAM);
				Loggers.battleLogger.warn("CGBattlePveStart double click! uid = " + userBean.getUid());
				return;
			}
			RoleBattleEntity battleEntity = GameGlobals.roleManager.getRoleBattleEntity(userBean.getUid());
			if (battleEntity == null) {
				battleEntity = GameGlobals.roleManager.initRoleBattle(userBean.getUid());
			}

			BattleType battleType = BattleType.valueOf(msg.getBattleType());
			if (battleType == null) {
				gcBattlePveStartProto.setStatus(ErrorCodeConst.ERROR_PARAM);
				return;
			}

			List<Integer> preItems = null; // 战前道具选择
			int dupType = 0;// 副本类型
			switch (battleType) {
			case PVE_MAIN:
				// pve战斗 获取targetid 扣除体力
				pveStart(msg, gcBattlePveStartProto, roleEntity);
				preItems = battleEntity.getPveItemIdList();
				break;
			case PVE_DUP:
				// 副本战斗 获取targetid 扣除副本消耗
				dupType = dupStart(msg, gcBattlePveStartProto, roleEntity);
				preItems = battleEntity.getDupItemIdList();
				break;
			case PVE_ELITE:
				// 精英副本
			default:
				gcBattlePveStartProto.setStatus(ErrorCodeConst.ERROR_PARAM);
				break;
			}
			if (gcBattlePveStartProto.getStatus() != ErrorCodeConst.SUCCESS) {
				return;
			}

			int pveTaskId = gcBattlePveStartProto.getTargetId();
			gcBattlePveStartProto.setDupId(msg.getDupId());
			gcBattlePveStartProto.setBattleType(msg.getBattleType());
			gcBattlePveStartProto.setHeroId(battleEntity.getHeroId());
			// 选择的推荐英雄需要优先使用
			if (battleEntity.getRecommendHeroId() != 0) {
				gcBattlePveStartProto.setHeroId(battleEntity.getRecommendHeroId());
			}
			gcBattlePveStartProto.setSteps(GameGlobals.targetManager.getTargetSteps(pveTaskId, gcBattlePveStartProto.getHeroId()));
			gcBattlePveStartProto.setCostCash(GameGlobals.configManager.getAddStepCost());
			gcBattlePveStartProto.setAddStepNum(GameGlobals.configManager.getAddStepNum());
			// 记录战斗开始数据到userbean.
			// 有数据重置逻辑, 必须在战前道具消耗之前处理
			userBean.setOprStartTime(oprTime);
			recordBattleInfo(userBean, gcBattlePveStartProto);
			if (preItems != null) {
				Map<Integer, Integer> itemMap = new HashMap<Integer, Integer>();
				// 战前道具
				for (int itemId : preItems) {
					if (GameGlobals.itemManager.costItem(userBean.getUid(), itemId, 1)) {
						gcBattlePveStartProto.addPreBattleItemId(itemId);
						itemMap.put(itemId, 1);
					}
				}
				// 检查道具消耗任务
				GameGlobals.taskManager.onTask(userBean.getUid(), DayTaskConstants.TYPE_BATTLE_OUT_ITEM, preItems.size());
				GameGlobals.achieveManager.onTask(userBean.getUid(), EAchieveBehavior.TYPE_BATTLE_OUT_ITEM, itemMap);
			}
			// 战斗中道具固定
			for (int itemId : GameGlobals.itemManager.getInBattleItemIdSet()) {
				ItemEntity itemEntity = GameGlobals.itemManager.getItemEntity(userBean.getUid(), itemId);
				if (itemEntity == null) {
					gcBattlePveStartProto.addItem(GCBattlePveStart.ItemVo.newBuilder().setItemId(itemId).setItemNum(0).setFreeNum(0));
				} else {
					gcBattlePveStartProto.addItem(
							GCBattlePveStart.ItemVo.newBuilder().setItemId(itemId).setItemNum(itemEntity.getNum()).setFreeNum(itemEntity.getFreeNum()));
				}
			}
			// optional ChessVo adChessVo = 9;// 视频获得棋子
			if (userBean.getRandomChessId() != 0) {
				gcBattlePveStartProto.setAdChessVo(ChessVo.newBuilder().setChessId(userBean.getRandomChessId()).setChessNum(1));

				// 清空看视频广告获得棋子
				userBean.setRandomChessId(0);
			}

			// 连胜活动，确认活动是否有连胜类型的活动开启，以及连胜的逻辑类型
			// repeated ChessVo conWinChessVo = 10;// 连胜携带棋子
			Map<Integer, Integer> chessMap = getConWinChessMap(userBean, roleEntity.getPveConWinNum());
			if (!chessMap.isEmpty()) {
				Iterator<Integer> iterator = chessMap.keySet().iterator();
				while (iterator.hasNext()) {
					int chessId = iterator.next();
					int chessNum = chessMap.get(chessId);
					gcBattlePveStartProto.addConWinChessVo(ChessVo.newBuilder().setChessId(chessId).setChessNum(chessNum));
				}
			}

			// 打点日志
			if (BattleType.PVE_MAIN.is(battleType)) {
				BattlePveStartLog pveLog = new BattlePveStartLog(userBean);
				pveLog.setHeroId(gcBattlePveStartProto.getHeroId());
				pveLog.setTargetId(pveTaskId);
				LogfPrinter.getInstance().push(pveLog);
			} else if (BattleType.PVE_DUP.is(battleType)) {
				BattleDupStartLog dupLog = new BattleDupStartLog(userBean);
				dupLog.setHeroId(gcBattlePveStartProto.getHeroId());
				dupLog.setTargetId(pveTaskId);
				dupLog.setDupType(dupType);
				LogfPrinter.getInstance().push(dupLog);
			}
		} finally {
			lock.unlock();
		}
	}

	public void CGBattlePveResult(MsgBack msgBack, UserBean userBean, CGBattlePveResultProto msg) {
		if (!checkResultValid(userBean, msg)) {
			// 战斗数据校验失败
			msgBack.addBuilder(GCBattlePveResultProto.newBuilder().setStatus(ErrorCodeConst.ERROR_PARAM));
			return;
		}

		long oprTime = System.currentTimeMillis();
		if (msg.getPveId() == userBean.getUserInfoVo().getPveId() && msg.getDupId() == userBean.getUserInfoVo().getDupId()
				&& oprTime - userBean.getOprEndTime() < UserConstants.OPR_TIME) {
			Loggers.battleLogger.warn("CGBattlePveResult double click! uid = " + userBean.getUid());
			msgBack.addBuilder(GCBattlePveResultProto.newBuilder().setStatus(ErrorCodeConst.ERROR_PARAM));
			return;
		}
		userBean.setOprEndTime(oprTime);

		ReentrantLock lock = LockManager.getInstance().getLock(userBean.getUid());
		lock.lock();
		try {
			long uid = userBean.getUid();

			GCBattlePveResultProto.Builder gcBattlePveResultProto = GCBattlePveResultProto.newBuilder();
			msgBack.addBuilder(gcBattlePveResultProto);
			gcBattlePveResultProto.setBattleType(msg.getBattleType());
			gcBattlePveResultProto.setStatus(ErrorCodeConst.SUCCESS);
			gcBattlePveResultProto.setResult(msg.getResult());// 战斗结果. 0失败; 1胜利

			int result = msg.getResult();// 战斗结果. 0失败; 1胜利
			if (result == 1) {// 胜利
				handleWin(uid, msg, gcBattlePveResultProto);

				if (BattleType.PVE_MAIN.is(msg.getBattleType())) {
					GameGlobals.pveCityManager.completePveTarget(msgBack, userBean.getUid());
				}
			} else {// 失败
				handleLose(uid, msg, gcBattlePveResultProto);
			}
			// 收集星星
			RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
			if (roleEntity != null) {
				gcBattlePveResultProto.setRoleDupStar(roleEntity.getDupStar());
			}

			// 战斗结束, 清空战斗数据记录
			userBean.getUserInfoVo().resetBattleInfo();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 花费钻石增加步数
	 * 
	 * @param msgBack
	 * 
	 * @param userBean
	 */
	public void CGBattlePveFailedAddStep(MsgBack msgBack, UserBean userBean, CGBattlePveFailedAddStepProto msg) {
		GCBattlePveFailedAddStepProto.Builder gcBattlePveFailedAddStepProto = GCBattlePveFailedAddStepProto.newBuilder();
		msgBack.addBuilder(gcBattlePveFailedAddStepProto);
		long uid = userBean.getUid();

		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
			if (roleEntity == null) {
				Loggers.serverLogger.error("No roleEntity! uid = " + uid);
				gcBattlePveFailedAddStepProto.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}
			if (msg.getOpType() == 1) {
				// 记录战斗打点数据: 看视频买步数
				userBean.getUserInfoVo().recordBuyStepInfo(System.currentTimeMillis(), GameGlobals.configManager.getAddStepNum(),
						new ItemVo());
			} else if (msg.getOpType() == 0) {
				// 使用钻石买步数
				int cost = GameGlobals.configManager.getAddStepCost();
				if (!GameGlobals.unitManager.isEnough(roleEntity, EItemId.REWARD_CASH.getType(), cost)) {
					// 钻石不足
					gcBattlePveFailedAddStepProto.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
					return;
				}
				GameGlobals.unitManager.cost(uid, EItemType.ITEM.getType(), EItemId.REWARD_CASH.getType(), cost, LogfConstants.CHANNEL_BATTLE, "add_step");
				gcBattlePveFailedAddStepProto.setCostCash(cost);
				gcBattlePveFailedAddStepProto.setCostCashNext(cost);// 下次买步数消耗
				// 记录战斗打点数据: 买步数
				userBean.getUserInfoVo().recordBuyStepInfo(System.currentTimeMillis(), GameGlobals.configManager.getAddStepNum(),
						new ItemVo(EItemType.ITEM.getType(), EItemId.REWARD_CASH.getType(), cost));
			}
		} finally {
			lock.unlock();
		}
		gcBattlePveFailedAddStepProto.setStatus(ErrorCodeConst.SUCCESS);
		gcBattlePveFailedAddStepProto.setAddStep(GameGlobals.configManager.getAddStepNum());
		gcBattlePveFailedAddStepProto.setOpType(msg.getOpType());
	}

	/**
	 * 蓝钻购买体力
	 * 
	 * @param msgBack
	 * 
	 * @param uid
	 * @param msg
	 * 
	 */
	public void CGPveBuyPower(MsgBack msgBack, long uid, CGPveBuyPowerProto msg) {
		GCPveBuyPowerProto.Builder builder = GCPveBuyPowerProto.newBuilder();
		msgBack.addBuilder(builder);
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		ItemVo pveBuyCostPowerOne = GameGlobals.configManager.getPveBuyCostPowerOne();
		if (!GameGlobals.unitManager.isEnough(role, pveBuyCostPowerOne.getId(), pveBuyCostPowerOne.getNum())) {
			builder.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
			return;
		}
		// 扣资源
		GameGlobals.unitManager.cost(uid, pveBuyCostPowerOne.getType(), pveBuyCostPowerOne.getId(), pveBuyCostPowerOne.getNum(), LogfConstants.CHANNEL_BATTLE,
				"buy_pve_power");
		// 加体力
		GameGlobals.roleManager.addPower(EPowerChannel.BUY, role.getUid(), 1);
		builder.setStatus(ErrorCodeConst.SUCCESS);

		// 返回体力信息
		GCPowerGetInfoProto.Builder powerInfo = GCPowerGetInfoProto.newBuilder();
		powerInfo.setStatus(ErrorCodeConst.SUCCESS);
		powerInfo.setPower(role.getPower());
		msgBack.addBuilder(powerInfo);
	}

	public void CGBattlePveItemList(MsgBack msgBack, UserBean userBean, CGBattlePveItemListProto msg) {
		GCBattlePveItemListProto.Builder builder = GCBattlePveItemListProto.newBuilder();
		msgBack.addBuilder(builder);

		RoleBattleEntity battleEntity = GameGlobals.roleManager.getRoleBattleEntity(userBean.getUid());
		if (battleEntity == null) {
			return;
		}

		BattleType battleType = BattleType.valueOf(msg.getBattleType());
		if (battleType == null) {
			return;
		}

		// 默认玩家选择的战前道具
		List<Integer> preItems = null;
		// 配置的战前可选择道具
		Set<Integer> preItemsConfig = null;
		switch (battleType) {
		case PVE_DUP:
			preItems = battleEntity.getDupItemIdList();
			preItemsConfig = GameGlobals.itemManager.getPreDupBattleItemIdSet();
			break;
		case PVE_ELITE:
			preItems = battleEntity.getEliteItemIdList();
			preItemsConfig = GameGlobals.itemManager.getPreEliteBattleItemIdSet();
			break;
		case PVE_MAIN:
			preItems = battleEntity.getPveItemIdList();
			preItemsConfig = GameGlobals.itemManager.getPrePveBattleItemIdSet();

			// 推荐道具和英雄
			List<ItemVo> recommendItem = GameGlobals.pveCityManager.recommendItems(msg.getPveId(), userBean.getUid());
			for (int i = 0; i < recommendItem.size(); i++) {
				ItemVo info = recommendItem.get(i);
				GCBattlePveItemList.RecommendItems.Builder recommendBuilder = GCBattlePveItemList.RecommendItems.newBuilder();
				recommendBuilder.setItemId(info.getId());
				recommendBuilder.setItemType(info.getType());
				recommendBuilder.setNeedBuy(info.getNum());
				builder.addRecommend(recommendBuilder);
			}
			// 连胜/连败次数
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
			if (role != null) {
				builder.setCoinNum(role.getPveConWinNum());
			}
			break;

		default:
			break;
		}

		long now = System.currentTimeMillis();
		// 战前道具的显示    无限道具以及默认选择道具的显示
		for (int itemId : preItemsConfig) {
			ItemEntity itemEntity = GameGlobals.itemManager.getItemEntity(userBean.getUid(), itemId);
			PreBattleItemVo.Builder itemBuilder = PreBattleItemVo.newBuilder();
			if (itemEntity == null) {
				itemBuilder.setItemId(itemId).setItemNum(0).setIsSelect(false).setIsInfinite(false);
			} else {
				itemBuilder.setItemId(itemId).setItemNum(itemEntity.getNum());

				long deltaTime = itemEntity.getInfiniteTime() - now - ItemConstants.INFINITE_ITEM_ADJUST_TIME;
				boolean isInfinite = deltaTime > 0;
				itemBuilder.setIsInfinite(isInfinite);
				itemBuilder.setRemainingSeconds((int) (isInfinite ? (deltaTime / TimeUtils.SECOND) : 0));
				if (isInfinite) {
					// 有无限道具, 默认勾选
					itemBuilder.setIsSelect(true);
				} else {
					// 没有无限道具, 如果上次选择了该道具, 并且背包数量大于0, 则选择该道具; 其他情况, 不选择
					boolean isSelected = preItems.contains(itemId) && itemEntity.getNum() > 0;
					itemBuilder.setIsSelect(isSelected);
				}
			}
			builder.addItem(itemBuilder);
		}
	}

	private boolean costPvePower(long uid) {
		if (GameGlobals.roleManager.inNoLimitPowerTime(uid)) {
			return true;
		}
		return GameGlobals.roleManager.reducePower(uid, 1);
	}

	private void handleLose(long uid, CGBattlePveResultProto msg, GCBattlePveResultProto.Builder gcBattlePveResultProto) {
		// 打点类型
		int targetId = 0;
		Map<Integer, Integer> leftTargetsMap = new HashMap<Integer, Integer>();
		for (ChessProto chessProto : msg.getLeftTargetsList()) {
			leftTargetsMap.put(chessProto.getChessId(), chessProto.getChessNum());
		}
		RoleBattleEntity battleEntity = GameGlobals.roleManager.getRoleBattleEntity(uid);
		if (BattleType.PVE_MAIN.is(msg.getBattleType())) {
			PveCityEntity pveEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
			if (pveEntity != null) {
				targetId = pveEntity.getTargetId();
			}
			// optional int32 nextTargetId = 5;// 下一个关卡Id, 没有关卡为-1
			gcBattlePveResultProto.setNextTargetId(targetId);

			RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
			if (roleEntity != null) {
				// 战斗失败, 增加连败次数. 若连胜数大于0, 则清零再记连败次数
				roleEntity.setPveConWinNum(Math.min(roleEntity.getPveConWinNum(), 0) - 1);
				// 战斗失败, 连胜清零
//					roleEntity.setPveConWinNum(0);
				Globals.getEntityProxy().updateAsync(roleEntity);
				// optional int32 pveConWinNum = 11;//连胜次数
				gcBattlePveResultProto.setPveConWinNum(roleEntity.getPveConWinNum());
			}

			//repeated ItemProto resultRewardItem = 6;// 战斗结算奖励

			// 战斗结算日志打点
			UserBean user = Globals.getUserManager().getUserBean(uid);
			BattlePveResultLog log = new BattlePveResultLog(user);
			log.setTargetId(targetId);
			log.setResult(2);
			log.setStepCost(msg.getCostStep());
			log.setLeftStep(msg.getLeftStep());
			TargetBean targetBean = Datas.get(TargetBean.class, targetId);
			if (targetBean != null) {
				log.setTotalStep(targetBean.getDemand());
				log.getTotalTargets().putAll(targetBean.getTargetMap());
			}
			log.setSkillNum(msg.getSkillNum());
			log.setLeftTargets(leftTargetsMap);
			if (user != null) {
				log.getPreItems().addAll(user.getUserInfoVo().getPreItemIdList());
				log.getBattleItems().addAll(user.getUserInfoVo().getBattleItemIdList());
				log.getBuyStepInfo().addAll(user.getUserInfoVo().getBuyStepsList());
			}
			if (battleEntity != null) {
				log.setHeroId(battleEntity.getRecommendHeroId() == 0 ? battleEntity.getHeroId() : battleEntity.getRecommendHeroId());
			}
			log.setPower(roleEntity.getPower());
			LogfPrinter.getInstance().push(log);
		} else {
			DuplicateBean dupBean = Datas.get(DuplicateBean.class, msg.getDupId());
			if (dupBean != null) {
				UserBean user = Globals.getUserManager().getUserBean(uid);
				BattleDupResultLog log = new BattleDupResultLog(user);
				log.setTargetId(dupBean.getTarget());
				log.setResult(2);
				log.setStepCost(msg.getCostStep());
				log.setDupType(dupBean.getType());
				log.setLeftStep(msg.getLeftStep());
				TargetBean targetBean = Datas.get(TargetBean.class, dupBean.getTarget());
				if (targetBean != null) {
					log.setTotalStep(targetBean.getDemand());
					log.getTotalTargets().putAll(targetBean.getTargetMap());
				}
				log.setSkillNum(msg.getSkillNum());
				log.setLeftTargets(leftTargetsMap);
				if (user != null) {
					log.getPreItems().addAll(user.getUserInfoVo().getPreItemIdList());
					log.getBattleItems().addAll(user.getUserInfoVo().getBattleItemIdList());
					log.getBuyStepInfo().addAll(user.getUserInfoVo().getBuyStepsList());
				}
				if (battleEntity != null) {
					log.setHeroId(battleEntity.getRecommendHeroId() == 0 ? battleEntity.getHeroId() : battleEntity.getRecommendHeroId());
				}
				LogfPrinter.getInstance().push(log);
			}
		}
	}

	private void handleWin(long uid, CGBattlePveResultProto msg, GCBattlePveResultProto.Builder gcBattlePveResultProto) {
		BattleType battleType = BattleType.valueOf(msg.getBattleType());
		if (battleType == null) {
			gcBattlePveResultProto.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}

		switch (battleType) {
		case PVE_MAIN:
			pveWin(uid, msg, gcBattlePveResultProto);
			break;
		case PVE_DUP:
			dupWin(uid, msg, gcBattlePveResultProto);
			break;
		default:
			gcBattlePveResultProto.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}

		// 检测: 系统开启\成就\任务
		int targetId = gcBattlePveResultProto.getTargetId();
		// 系统开启检测
		GameGlobals.functionOpenManager.check(uid, ISystemId.PVE_TYPE, String.valueOf(targetId));
		// 引导检测
		GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_PVE_COMPLETE, targetId);
		// 收集棋子
		Map<Integer, Integer> chessMap = Maps.newHashMap();
		for (buffer.CGBattlePveResult.ChessProto chessProto : msg.getChessInfoList()) {
			chessMap.put(chessProto.getChessId(), chessProto.getChessNum());
		}
		GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_COLLEC_CHESS, chessMap);
		// 连击次数
		for (ComboProto comboProto : msg.getComboInfoList()) {
			EAchieveBehavior achive = EAchieveBehavior.valueOf(comboProto.getComboId());
			if (achive != null) {
				GameGlobals.achieveManager.onTask(uid, achive, comboProto.getComboNum());
			}
		}
		// 合成棋子
		Map<Integer, Integer> composeMap = new HashMap<Integer, Integer>();
		for (ChessProto compose : msg.getComposeInfoList()) {
			composeMap.put(compose.getChessId(), compose.getChessNum());
		}
		GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_BATTLE_COMPOSE, composeMap);
	}

	private Map<Integer, Integer> getConWinChessMap(UserBean userBean, int conWinNum) {
		Map<Integer, Integer> chessMap = Maps.newHashMap();
		if (!GameGlobals.activityManager.isActivityOpenByType(EActivityLogicType.BATTLE_PVE_WINNING_STREAK_1)) {
			// 连胜活动未开启
			return chessMap;
		}

		// 连胜
		if (winStreakBeanMap == null) {
			return chessMap;
		}
		Entry<Integer, WinningStreakBean> beanEntry = winStreakBeanMap.floorEntry(conWinNum);
		if (beanEntry == null) {
			return chessMap;
		}
		WinningStreakBean bean = beanEntry.getValue();
		if (bean != null && !bean.getChessMap().isEmpty()) {
			chessMap.putAll(bean.getChessMap());
		}
		return chessMap;
	}

	private void pveStart(CGBattlePveStartProto msg, GCBattlePveStartProto.Builder gcBattlePveStartProto, RoleEntity roleEntity) {
		// 验证是否可以进入pve战斗
		PveCityEntity entity = GameGlobals.pveCityManager.getPveCityEntityByUid(roleEntity.getUid());
		if (entity == null) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.BATTLE_PVE_ERROR_TARGET_ID);
			return;
		}
		PveBean pveBean = Datas.get(PveBean.class, entity.getCurPveTarget());
		if (pveBean == null) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.BATTLE_PVE_ERROR_TARGET_ID);
			return;
		}
		// 战斗结算就不要判断了 因为可能会增加不可预知的结果
		if (entity.getCurTargetStatus() != EPveStatus.UNLOCK.getStatus()) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.BATTLE_PVE_ERROR_TARGET_ID);
			return;
		}
		int pveTaskId = pveBean.getTargetId();
		if (pveTaskId == 0) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.BATTLE_PVE_ERROR_TARGET_ID);
			return;
		}
		gcBattlePveStartProto.setTargetId(pveTaskId);
		gcBattlePveStartProto.setPveId(entity.getCurPveTarget());
		// 扣体力
		int status = GameGlobals.roleManager.checkPvePower(roleEntity.getUid());
		if (status == ErrorCodeConst.SUCCESS) {
			costPvePower(roleEntity.getUid());
		}
		// 体力不足
		gcBattlePveStartProto.setStatus(status);
	}

	private void pveWin(long uid, CGBattlePveResultProto msg, GCBattlePveResultProto.Builder gcBattlePveResultProto) {
		PveCityEntity entity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
		if (entity == null) {
			Loggers.battleLogger.error("No PveCityEntity! uid = " + uid);
			return;
		}

		RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
		if (roleEntity == null) {
			Loggers.battleLogger.error("No RoleEntity! uid = " + uid);
			return;
		}

		int pveId = entity.getCurPveTarget();
		int targetId = entity.getTargetId();
		int nextTargetId = GameGlobals.pveCityManager.getPveNextTarget(uid);

		gcBattlePveResultProto.setTargetId(targetId);
		if (targetId != -1) {
			entity.setTargetId(nextTargetId);
			Globals.getEntityProxy().updateAsync(entity);
		}

		// optional int32 nextTargetId = 5;// 下一个关卡Id, 没有关卡为-1
		gcBattlePveResultProto.setNextTargetId(nextTargetId);
		// 战斗胜利, 恢复1点体力
		GameGlobals.roleManager.addPower(EPowerChannel.BATTLE, uid, 1);

		PveBean pveBean = Datas.get(PveBean.class, pveId);
		if (pveBean == null) {
			Loggers.battleLogger.error("No PveBean! pveId = " + pveId);
			return;
		}

		// 结算奖励
		List<ItemVo> allRewardList = new ArrayList<ItemVo>();

		// repeated ItemProto resultRewardItem = 6;// 战斗胜利奖励
		List<ItemVo> winRewardList = ItemUtil.merge(pveBean.getRewardList());// clone一下数据
		for (ItemVo itemVo : winRewardList) {
			gcBattlePveResultProto
					.addResultRewardItem(ItemProto.newBuilder().setItemType(itemVo.getType()).setItemId(itemVo.getId()).setItemNum(itemVo.getNum()));
		}
		allRewardList.addAll(winRewardList);

		// repeated ItemProto stepRrewardItem = 7;// 剩余步数奖励(原 步数奖励)
		int leftStep = msg.getLeftStep();// 剩余步数
		for (ItemVo itemVo : pveBean.getStepRewardList()) {
			ItemVo stepReward = itemVo.clone();
			stepReward.setNum(stepReward.getNum() * leftStep);
			allRewardList.add(stepReward);
			gcBattlePveResultProto.addResultRewardItem(
					ItemProto.newBuilder().setItemType(stepReward.getType()).setItemId(stepReward.getId()).setItemNum(stepReward.getNum()));
		}

		// 玩家连胜处理
		// 战斗胜利, 连胜+1 (连胜奖励). 若连胜值小于0, 先清零再记录连胜次数
		roleEntity.setPveConWinNum(Math.max(roleEntity.getPveConWinNum(), 0) + 1);
		Globals.getEntityProxy().updateAsync(roleEntity);
		// 连胜
		GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_BATTLE_CONTINUITY_WIN, roleEntity.getPveConWinNum());
		// optional int32 pveConWinNum = 11;//连胜次数
		gcBattlePveResultProto.setPveConWinNum(roleEntity.getPveConWinNum());

		// 给玩家奖励(不要性能, 要代码简洁)
		allRewardList = ItemUtil.merge(allRewardList);// 合并相同物品数量
		for (ItemVo itemVo : allRewardList) {
			GameGlobals.bagManager.addItem(uid, itemVo, LogfConstants.CHANNEL_BATTLE, "0");
		}
		// 记录要翻倍领取的奖励
		UserBean userBean = Globals.getUserManager().getUserBean(uid);
		userBean.setPveRewardList(allRewardList);

		// pve通关成就
		GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_PVE_NUM, pveId);
		// 任务检测
		GameGlobals.taskManager.onTask(uid, DayTaskConstants.TYPE_PVE_NUM, 1);

		// PVE结算日志
		BattlePveResultLog log = new BattlePveResultLog(userBean);
		log.setTargetId(targetId);
		log.setResult(1);
		log.getRewards().addAll(allRewardList);
		log.setStepCost(msg.getCostStep());
		log.setLeftStep(msg.getLeftStep());
		TargetBean targetBean = Datas.get(TargetBean.class, targetId);
		if (targetBean != null) {
			log.setTotalStep(targetBean.getDemand());
			log.getTotalTargets().putAll(targetBean.getTargetMap());
		}
		log.setSkillNum(msg.getSkillNum());

		Map<Integer, Integer> leftTargetsMap = new HashMap<Integer, Integer>();
		for (ChessProto chessProto : msg.getLeftTargetsList()) {
			leftTargetsMap.put(chessProto.getChessId(), chessProto.getChessNum());
		}
		log.setLeftTargets(leftTargetsMap);
		if (userBean != null) {
			log.getPreItems().addAll(userBean.getUserInfoVo().getPreItemIdList());
			log.getBattleItems().addAll(userBean.getUserInfoVo().getBattleItemIdList());
			log.getBuyStepInfo().addAll(userBean.getUserInfoVo().getBuyStepsList());
		}
		RoleBattleEntity battleEntity = GameGlobals.roleManager.getRoleBattleEntity(userBean.getUid());
		if (battleEntity != null) {
			log.setHeroId(battleEntity.getRecommendHeroId() == 0 ? battleEntity.getHeroId() : battleEntity.getRecommendHeroId());
		}
		log.setPower(roleEntity.getPower());
		LogfPrinter.getInstance().push(log);
	}

	private int dupStart(CGBattlePveStartProto msg, GCBattlePveStartProto.Builder gcBattlePveStartProto, RoleEntity roleEntity) {
		// 验证是否可以进入副本战斗
		int dupFlag = GameGlobals.pveCityManager.checkDupOpen(roleEntity.getUid(), msg.getCityId(), msg.getDupId());
		if (dupFlag == 0) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.DUPLICATE_ERROR_ID);
			return 0;
		} else if (dupFlag == -1) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.DUPLICATE_ERROR_OPEN);
			return 0;
		}

		// 获取副本配置
		DuplicateBean dupBean = Datas.get(DuplicateBean.class, msg.getDupId());
		if (dupBean == null) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.DUPLICATE_ERROR_ID);
			return 0;
		}
		gcBattlePveStartProto.setTargetId(dupBean.getTarget());
		// 没有配置副本消耗
		if (dupBean.getCostItems() == null || dupBean.getCostItems().size() == 0) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.SUCCESS);
			return 0;
		}
		// 验证索引是否越界
		if (msg.getIndex() >= dupBean.getCostItems().size() || msg.getIndex() < 0) {
			gcBattlePveStartProto.setStatus(ErrorCodeConst.ERROR_PARAM);
			return 0;
		}
		// 副本配置表里的相应消耗品
		ItemVo info = dupBean.getCostItems().get(msg.getIndex());
		// 看视频
		if (info != null && info.getType() != 10 && info.getNum() > 0) {
			GameGlobals.unitManager.costCoinAndCash(roleEntity.getUid(), info.getType(), info.getId(), info.getNum(), LogfConstants.CHANNEL_BATTLE,
					"dupcost" + dupBean.getType(), "" + dupBean.getTarget(), "" + dupBean.getId());
		}
		gcBattlePveStartProto.setStatus(ErrorCodeConst.SUCCESS);
		gcBattlePveStartProto.setPveId(msg.getPveId());
		return dupBean.getType();
	}

	private void dupWin(long uid, CGBattlePveResultProto msg, GCBattlePveResultProto.Builder gcBattlePveResultProto) {
		DuplicateBean dupBean = Datas.get(DuplicateBean.class, msg.getDupId());
		if (dupBean == null)
			return;
		int targetId = dupBean.getTarget();
		// 副本对应的关卡id
		gcBattlePveResultProto.setTargetId(targetId);

		// 处理副本统计次数 因为这里不处理精英副本 所以不存分级了
		RoleBattleEntity battleEntity = GameGlobals.roleManager.getRoleBattleEntity(uid);
		if (battleEntity == null) {
			battleEntity = GameGlobals.roleManager.initRoleBattle(uid);
		}
		battleEntity.setNormalDupTimes(battleEntity.getNormalDupTimes() + 1);
		Globals.getEntityProxy().updateAsync(battleEntity);

		// 加分类型合并 不然要嵌套双重循环 
		Map<Integer, Integer> scoreMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < msg.getScoreInfoCount(); i++) {
			buffer.CGBattlePveResult.ChessProto scoreInfo = msg.getScoreInfo(i);
			int scoreId = scoreInfo.getChessId();
			scoreMap.put(scoreId, scoreMap.getOrDefault(scoreId, 0) + scoreInfo.getChessNum());
		}

		int score = 0;
		Map<Integer, Integer> scoreTypeMap = new HashMap<Integer, Integer>();
		for (ScoreconfigBean bean : Datas.getDataMap(ScoreconfigBean.class).values()) {
			if (!scoreMap.containsKey(bean.getType()))
				continue;
			int typeScore = bean.getScore() * scoreMap.get(bean.getType());
			score += typeScore;
			scoreTypeMap.put(bean.getScoreType(), scoreTypeMap.getOrDefault(bean.getScoreType(), 0) + typeScore);
		}

		int totalStep = 100;
		TargetBean targetBean = Datas.get(TargetBean.class, targetId);
		if (targetBean != null) {
			totalStep = targetBean.getDemand();
		}

		score = score + score * msg.getLeftStep() / totalStep;
		for (int scoreType : scoreTypeMap.keySet()) {
			int typeScore = scoreTypeMap.get(scoreType);
			buffer.GCBattlePveResult.ScoreInfo.Builder scorebd = buffer.GCBattlePveResult.ScoreInfo.newBuilder();
			scorebd.setScoreType(scoreType);
			scorebd.setScore(typeScore + typeScore * msg.getLeftStep() / totalStep);
			gcBattlePveResultProto.addScoreInfo(scorebd);
		}
		gcBattlePveResultProto.setDupScore(score);

		PveCityEntity pveEntity = GameGlobals.pveCityManager.getPveCityEntityByUid(uid);
		if (pveEntity == null) {
			return;
		}
		Duplicate dupInfo = pveEntity.getCityDupInfo(msg.getCityId(), msg.getDupId());
		if (dupInfo == null) {
			return;
		}
		dupInfo.setDupNum(dupInfo.getDupNum() + 1);
		// 通关一次副本的成就和每日任务
		GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_DUP_TIMES, 1);
		GameGlobals.taskManager.onTask(uid, DayTaskConstants.TYPE_DUP_TIMES, 1);
		if (dupInfo.getDupNum() == 1) {
			// 通关副本个数成就
			GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_DUP_NUM, 1);
		}

		Integer starKey = dupBean.getScoreStarMap().floorKey(score);
		if (starKey == null) {
			// 虽然不能领取奖励 但是副本的进行次数还是要更新的
			Globals.getEntityProxy().updateAsync(pveEntity);
			return;
		}
		Integer star = dupBean.getScoreStarMap().get(starKey);
		gcBattlePveResultProto.setDupStar(star);
		int ogrinStar = dupInfo.getStar();
		int addStar = star - ogrinStar;
		// 如果没有之前的星级高 那么不能领取奖励
		if (addStar <= 0) {
			// 虽然不能领取奖励 但是副本的进行次数还是要更新的
			Globals.getEntityProxy().updateAsync(pveEntity);
			return;
		}
		dupInfo.setStar(star);
		Globals.getEntityProxy().updateAsync(pveEntity);

		List<ItemVo> awards = new ArrayList<ItemVo>();
		for (int i = ogrinStar + 1; i <= star; i++) {
			List<ItemVo> awardList = dupBean.getStarAwardMap().get(i);
			if (awardList == null || awardList.size() == 0) {
				continue;
			}
			for (int j = 0; j < awardList.size(); j++) {
				ItemVo starAward = awardList.get(j).clone();
				gcBattlePveResultProto
						.addDupRewardItem(ItemProto.newBuilder().setItemType(starAward.getType()).setItemId(starAward.getId()).setItemNum(starAward.getNum()));
				awards.add(starAward);
			}
		}

		int dupType = dupBean.getType();
		String dupLogType = "dup" + dupType;
		String dupLogId = "" + dupBean.getId();
		String dupLogTarget = "" + dupBean.getTarget();

		// 收集星星奖励
		GameGlobals.bagManager.addItem(uid, new ItemVo(EItemType.ITEM.getType(), EItemId.DUPSTAR.getType(), addStar), LogfConstants.CHANNEL_BATTLE, "0",
				dupLogType, dupLogTarget, dupLogId);

		// 给玩家奖励(不要性能, 要代码简洁)
		List<ItemVo> allRewardList = ItemUtil.merge(awards);
		for (ItemVo itemVo : allRewardList) {
			GameGlobals.bagManager.addItem(uid, itemVo, LogfConstants.CHANNEL_BATTLE, "0", dupLogType, dupLogTarget, dupLogId);
		}

		// PVE结算日志
		UserBean user = Globals.getUserManager().getUserBean(uid);
		BattleDupResultLog log = new BattleDupResultLog(user);
		log.setTargetId(targetId);
		log.setResult(1);
		log.getRewards().addAll(allRewardList);
		log.setStepCost(msg.getCostStep());
		log.setDupType(dupType);
		log.setLeftStep(msg.getLeftStep());
		if (targetBean != null) {
			log.setTotalStep(totalStep);
			log.getTotalTargets().putAll(targetBean.getTargetMap());
		}
		log.setSkillNum(msg.getSkillNum());

		Map<Integer, Integer> leftTargetsMap = new HashMap<Integer, Integer>();
		for (ChessProto chessProto : msg.getLeftTargetsList()) {
			leftTargetsMap.put(chessProto.getChessId(), chessProto.getChessNum());
		}
		log.setLeftTargets(leftTargetsMap);
		if (user != null) {
			log.getPreItems().addAll(user.getUserInfoVo().getPreItemIdList());
			log.getBattleItems().addAll(user.getUserInfoVo().getBattleItemIdList());
			log.getBuyStepInfo().addAll(user.getUserInfoVo().getBuyStepsList());
		}
		if (battleEntity != null) {
			log.setHeroId(battleEntity.getRecommendHeroId() == 0 ? battleEntity.getHeroId() : battleEntity.getRecommendHeroId());
		}
		log.setDupStar(star);
		LogfPrinter.getInstance().push(log);
	}

	private void recordBattleInfo(UserBean userBean, Builder gcBattlePveStartProto) {
		// 先重置信息
		userBean.getUserInfoVo().resetBattleInfo();

		userBean.getUserInfoVo().setBattleType(gcBattlePveStartProto.getBattleType());
		userBean.getUserInfoVo().setPveId(gcBattlePveStartProto.getPveId());
		userBean.getUserInfoVo().setDupId(gcBattlePveStartProto.getDupId());
		userBean.getUserInfoVo().setTargetId(gcBattlePveStartProto.getTargetId());
		// 数据修改后必须检测更新离线数据保存开关
		userBean.getUserInfoVo().checkNeedSave();
	}

	private boolean checkResultValid(UserBean userBean, CGBattlePveResultProto msg) {
		if (userBean.getUserInfoVo().getBattleType() != msg.getBattleType()) {
			Loggers.battleLogger.warn("Error battle type! uid = " + userBean.getUid() + ", startBattleType = " + userBean.getUserInfoVo().getBattleType()
					+ ", endBattleType = " + msg.getBattleType());
			return false;
		}

		if (BattleType.PVE_MAIN.is(msg.getBattleType()) && userBean.getUserInfoVo().getPveId() != msg.getPveId()) {
			Loggers.battleLogger.error(
					"Error pve id! uid = " + userBean.getUid() + ", startPveId = " + userBean.getUserInfoVo().getPveId() + ", endPveId = " + msg.getPveId());
			return false;
		}

		if (BattleType.PVE_DUP.is(msg.getBattleType()) && userBean.getUserInfoVo().getDupId() != msg.getDupId()) {
			Loggers.battleLogger.error(
					"Error dup id! uid = " + userBean.getUid() + ", startDupId = " + userBean.getUserInfoVo().getDupId() + ", endDupId = " + msg.getDupId());
			return false;
		}

		return true;
	}
}
