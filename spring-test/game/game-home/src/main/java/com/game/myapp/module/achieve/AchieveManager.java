package com.game.myapp.module.achieve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.AchieveCompleteLog;
import com.game.common.logf.impl.AchieveUpgradeLog;
import com.game.common.user.UserBean;
import com.game.constants.AchieveConstants;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.game.db.entity.AchieveEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.AchieveBean;
import com.game.generate.bean.AchlevelBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.function.ISystemId;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.vo.item.ItemVo;

import buffer.CGGetAchieveListMsg.CGGetAchieveListProto;
import buffer.CGGetAchieveRewardMsg.CGGetAchieveRewardProto;
import buffer.GCGetAchieveListMsg;
import buffer.GCGetAchieveRewardMsg;
import buffer.GCGetAchieveRewardMsg.GCGetAchieveRewardProto.Builder;

public class AchieveManager {
	// 将成就配置按组别分组
	private Map<Integer, List<AchieveBean>> achieveGroupMap = new HashMap<Integer, List<AchieveBean>>();
	// 将成就配置按类型分组
	private Map<Integer, List<AchieveBean>> achieveTypeMap = new HashMap<Integer, List<AchieveBean>>();

	public AchieveManager() {
		init();
	}

	public void CGGetAchieveListMsg(MsgBack msgBack, long uid, CGGetAchieveListProto cgMsg) {
//		check(uid);
		GCGetAchieveListMsg.GCGetAchieveListProto.Builder gcMsg = GCGetAchieveListMsg.GCGetAchieveListProto.newBuilder();

		AchieveEntity entity = this.getAchieveEntity(uid);
		for (int group : achieveGroupMap.keySet()) {
			AchieveBean bean = this.getClientAchievebean(group, entity);
			if (bean == null) {
				continue;
			}

			int achieveId = bean.getId();
			GCGetAchieveListMsg.AchieveListProto.Builder tempTask = GCGetAchieveListMsg.AchieveListProto.newBuilder();
			tempTask.setTaskId(achieveId);
			tempTask.setProcess(entity.getProcess(achieveId) + "," + bean.getParam2());
			tempTask.setStatus(entity.getStatus(achieveId));
			gcMsg.addTasks(tempTask);
		}
		gcMsg.setTotalPoint(entity.getTotalPoint());
		gcMsg.setTotalReward(entity.getTotalReward());

		Map<Integer, Integer> sortPointMap = entity.getSortPointsMap();
		if (sortPointMap.size() == 0) {
			for (AchieveBean bean : Datas.getDataMap(AchieveBean.class).values()) {
				sortPointMap.put(bean.getSort(), 0);
			}
		}
		String sortPointStr = getClientSortString(sortPointMap);
		gcMsg.setActiveList(sortPointStr);
		msgBack.addBuilder(gcMsg);
	}

	public void CGGetAchieveRewardMsg(MsgBack msgBack, long uid, CGGetAchieveRewardProto cgMsg) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		GCGetAchieveRewardMsg.GCGetAchieveRewardProto.Builder gcMsg = GCGetAchieveRewardMsg.GCGetAchieveRewardProto.newBuilder();
		msgBack.addBuilder(gcMsg);
		gcMsg.setTaskId(cgMsg.getTaskId());
		gcMsg.setRewardType(cgMsg.getRewardType());
		AchieveEntity entity = Globals.getEntityProxy().get(AchieveEntity.class, uid);
		if (entity == null) {
			gcMsg.setStatus(1);
			return;
		}
		if (!handleAchieveReward(uid, entity, gcMsg, cgMsg.getTaskId(), cgMsg.getRewardType())) {
			return;
		}
		if (!handleAchieveLevelReward(uid, entity, gcMsg, cgMsg.getRewardType())) {
			return;
		}

		gcMsg.setPoint(entity.getTotalPoint());
		String sortPointStr = getClientSortString(entity.getSortPointsMap());
		gcMsg.setActiveList(sortPointStr);

		// 成就宝箱红点检测
		GameGlobals.redHatManager.checkBehaviorRedHat(role.getUid(), RedHatBehavior.ACHIEVEMENT_LEVEL_REWARD);
	}

	public void onTask(long uid, EAchieveBehavior behavior, Object... param) {
		if (behavior.isSystemOpen() && !GameGlobals.functionOpenManager.onCheck(uid, ISystemId.SYSTEM_ACHIEVEMENT)) {
			return;
		}
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			int taskType = behavior.getType();
			IAchieveTask task = behavior.getDoChecker();
			task.onImpl(taskType, uid, param);

			// 检测小红点
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.ACHIEVEMENT_UPGRADE);
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.ACHIEVEMENT_BATTLE);
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.ACHIEVEMENT_COLLECT);
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.serverLogger.error(e);
		} finally {
			lock.unlock();
		}
	}

	public AchieveEntity getAchieveEntity(long uid) {
		AchieveEntity entity = Globals.getEntityProxy().get(AchieveEntity.class, uid);
		if (entity == null) {
			ReentrantLock lock = Globals.getLockManager().getLock(uid);
			lock.lock();
			try {
				entity = new AchieveEntity();
				entity.setUid(uid);
				Globals.getEntityProxy().insert(entity);
			} finally {
				lock.unlock();
			}
		}
		return entity;
	}

	public List<AchieveBean> getAchieveListByType(int type) {
		return achieveTypeMap.get(type);
	}

	public List<AchieveBean> getAchieveListByGroup(int group) {
		return achieveGroupMap.get(group);
	}

	public void checkAchieveExist(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			AchieveEntity entity = Globals.getEntityProxy().get(AchieveEntity.class, uid);
			if (entity == null) {
				return;
			}
			Iterator<Map.Entry<Integer, AchieveEntity.AchieveInfo>> iterator = entity.getAchieveInfoMap().entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, AchieveEntity.AchieveInfo> entry = iterator.next();
				AchieveBean bean = Datas.get(AchieveBean.class, entry.getKey());
				if (bean == null) {
					iterator.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.serverLogger.error(e);
		} finally {
			lock.unlock();
		}
	}

	public boolean hasLevelReward(long uid) {
		try {
			AchieveEntity entity = Globals.getEntityProxy().get(AchieveEntity.class, uid);
			if (entity == null) {
				return false;
			}

			int curPoint = entity.getTotalPoint();
			int curId = entity.getTotalReward();
			// 当前是最后一个奖励, 直接返回
			if (curId == Datas.getDataMap(AchlevelBean.class).lastKey()) {
				return false;
			}

			// 没有达到下一个奖励的分数, 直接返回
			AchlevelBean nextBean = Datas.getCeilingData(AchlevelBean.class, curId + 1);
			if (curPoint < nextBean.getId()) {
				return false;
			}

			// 如果玩家当前任务奖励
			Map<Integer, Integer> mapDB = entity.getTotalStatusMap();
			if (mapDB.get(curId) != null) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void addCompleteLog(long uid, int achieveId) {
		UserBean userBean = Globals.getUserManager().getUserBean(uid);
		AchieveCompleteLog log = new AchieveCompleteLog(userBean);
		log.setAchieveId(achieveId);
		LogfPrinter.getInstance().push(log);
	}

	private void init() {
		for (AchieveBean bean : Datas.getDataMap(AchieveBean.class).values()) {

			int type = bean.getType();
			List<AchieveBean> achieveList = achieveTypeMap.get(type);
			if (achieveList == null) {
				achieveList = new ArrayList<AchieveBean>();
				achieveTypeMap.put(type, achieveList);
			}
			achieveList.add(bean);

			int group = bean.getGroup();
			List<AchieveBean> achieveGroupList = achieveGroupMap.get(group);
			if (achieveGroupList == null) {
				achieveGroupList = new ArrayList<AchieveBean>();
				achieveGroupMap.put(group, achieveGroupList);
			}
			achieveGroupList.add(bean);
		}
	}

	private AchieveBean getClientAchievebean(int group, AchieveEntity entity) {
		List<AchieveBean> beans = getAchieveListByGroup(group);
		AchieveBean result = null;
		if (beans == null) {
			return null;
		}
		for (AchieveBean bean : beans) {
			int id = bean.getId();
			if (entity.getStatus(id) == AchieveConstants.STATUS_AWARD) {
				continue;
			}
			if (result == null) {
				result = bean;
			} else if (id < result.getId()) {
				result = bean;
			}
		}
		return result;
	}

	private String getClientSortString(Map<Integer, Integer> sortPointMap) {
		String sortPointStr = "";
		for (Map.Entry<Integer, Integer> entry : sortPointMap.entrySet()) {
			sortPointStr = sortPointStr + entry.getKey() + "," + entry.getValue() + ";";
		}

		if (!sortPointStr.equals("")) {
			sortPointStr = sortPointStr.substring(0, sortPointStr.length() - 1);
		}

		return sortPointStr;
	}

//	private void check(long uid) {
//		SignEntity signEntity = Globals.getEntityProxy().get(SignEntity.class, uid);
//		if (signEntity == null) {
//			return;
//		} else {
//			GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_CONTINUITY_SIGN, signEntity.getSevenSignTime(), 1);
//		}
//	}

	private void packNextAchieve(AchieveEntity entity, int nextId, GCGetAchieveRewardMsg.GCGetAchieveRewardProto.Builder builder) {
		if (nextId == 0) {
			return;
		}
		AchieveBean tempBean = Datas.get(AchieveBean.class, nextId);
		GCGetAchieveRewardMsg.nextAchieveProto.Builder nextBuilder = GCGetAchieveRewardMsg.nextAchieveProto.newBuilder();

		nextBuilder.setStatus(entity.getStatus(nextId));
		nextBuilder.setTaskId(nextId);
		nextBuilder.setProcess(entity.getProcess(nextId) + "," + tempBean.getParam2());
		builder.setNext(nextBuilder);
	}

	private boolean handleAchieveReward(long uid, AchieveEntity entity, Builder gcMsg, int taskId, int rewardType) {
		// 成就等级奖励不在这里领取 这里只负责领取普通成就奖励
		if (rewardType == 2) {
			return true;
		}

		AchieveBean bean = Datas.get(AchieveBean.class, taskId);
		if (bean == null) {
			gcMsg.setStatus(1);
			return false;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			if (entity.getStatus(taskId) != AchieveConstants.STATUS_COMPLETE) {
				gcMsg.setStatus(1);
				return false;
			}
			entity.updateStatus(taskId, AchieveConstants.STATUS_AWARD);
			packNextAchieve(entity, bean.getNext(), gcMsg);
			// 成就点升级
			checkAchieveUpgrade(uid, entity.getTotalPoint(), bean.getPoint());
			entity.setTotalPoint(entity.getTotalPoint() + bean.getPoint());
			Map<Integer, Integer> sortMap = entity.getSortPointsMap();
			int value = bean.getPoint();
			if (sortMap.get(bean.getSort()) != null) {
				value += sortMap.get(bean.getSort());
			}
			sortMap.put(bean.getSort(), value);
			Globals.getEntityProxy().updateAsync(entity);

			for (int i = 0; i < bean.getAwardList().size(); i++) {
				ItemVo award = bean.getAwardList().get(i);
				if (award == null || award.getNum() == 0) {
					continue;
				}
				GameGlobals.bagManager.addItem(uid, award, LogfConstants.CHANNEL_ACHIEVEMENT, String.valueOf(taskId));
			}

			AchieveType achieveType = AchieveType.valuesOf(bean.getSort());
			if (achieveType != null) {
				GameGlobals.redHatManager.checkBehaviorRedHat(uid, achieveType.getBehavior());
			} else {
				Loggers.serverLogger.error("No AchieveType for AchieveBean.sort: " + bean.getSort());
			}
		} finally {
			lock.unlock();
		}
		return true;
	}

	private boolean checkAchieveUpgrade(long uid, int currPoint, int addPoint) {
		boolean result = false;
		// AchlevelBean数据从0开始，初始point为0。所以，逻辑上Datas.getFloorData()返回一定不为空，可以直接调用getLevel()
		int oldLevel = Datas.getFloorData(AchlevelBean.class, currPoint).getLevel();
		int newLevel = Datas.getFloorData(AchlevelBean.class, currPoint + addPoint).getLevel();
		if (newLevel > oldLevel) {
			// 成就升级打点日志
			UserBean userBean = Globals.getUserManager().getUserBean(uid);
			AchieveUpgradeLog log = new AchieveUpgradeLog(userBean);
			log.setAchieveLeve(newLevel);
			LogfPrinter.getInstance().push(log);

			result = true;
		}
		return result;
	}

	private boolean handleAchieveLevelReward(long uid, AchieveEntity entity, Builder gcMsg, int awardType) {
		// 普通成就奖励不在这里领取 这里只负责领取成就等级奖励
		if (awardType == 1) {
			return true;
		}
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			int curId = entity.getTotalReward();

			if (!hasLevelReward(uid)) {
				gcMsg.setStatus(1);
				return false;
			}

			AchlevelBean nextBean = Datas.getCeilingData(AchlevelBean.class, curId + 1);
			// 如果玩家当前任务奖励
			Map<Integer, Integer> totalStatusMap = entity.getTotalStatusMap();
			if (totalStatusMap.get(curId) != null) {
				gcMsg.setStatus(1);
				return false;
			}

			entity.setTotalReward(nextBean.getId());
			totalStatusMap.put(curId, 2);
			Globals.getEntityProxy().updateAsync(entity);

			for (int[] item : nextBean.getItemsArray()) {
				GameGlobals.bagManager.addItem(uid, item[0], item[1], item[2], LogfConstants.CHANNEL_ACHIEVEMENT, String.valueOf(nextBean.getId()));
			}

			gcMsg.setTotalReward(curId);
		} finally {
			lock.unlock();
		}
		return true;
	}
}
