
package com.game.myapp.module.activity;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.ActivityCompleteLog;
import com.game.common.logf.impl.ActivityReceiveLog;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.game.core.lock.LockManager;
import com.game.db.entity.ActivityEntity;
import com.game.db.entity.RoleActivityEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.ActivityBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.utils.JsonUtil;
import com.game.vo.item.ItemVo;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import buffer.CGActivityInfo.CGActivityInfoProto;
import buffer.GCActivityInfo.ActivityInfo;
import buffer.GCActivityInfo.ActivityInfo.Builder;
import buffer.GCActivityInfo.GCActivityInfoProto;

@SuppressWarnings("unused")
public class RoleActivityManager {
	public RoleActivityManager() {
	}

	public void CGActivityInfoProto(MsgBack msgBack, UserBean userBean, CGActivityInfoProto msg) {
		RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(userBean.getUid());
		if (roleEntity == null) {
			Loggers.serverLogger.error("No RoleEntity! uid = " + userBean.getUid());
			return;
		}

		int activityId = msg.getActivityId();
		if (activityId == ActivityConstant.ACTIVITY_TYPE_ALL) {
			// 获取全部活动信息
			CGActivityInfoProto(msgBack, userBean);
			return;
		}

		// 获取单个活动信息
		GCActivityInfoProto.Builder activityInfoProto = buildActivityInfoMsgById(activityId);
		if (activityInfoProto != null) {
			msgBack.addBuilder(activityInfoProto);
		}
	}

	public void CGActivityInfoProto(MsgBack msgBack, UserBean userBean) {
		// 获取全部活动信息
		GCActivityInfoProto.Builder gcActivityInfoProto = buildAllActivityInfoMsg();
		if (gcActivityInfoProto != null) {
			msgBack.addBuilder(gcActivityInfoProto);
		}
	}

	public void initRoleActivityEntity(long uid) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleActivityEntity entity = Globals.getEntityProxy().get(RoleActivityEntity.class, uid);
			if (entity == null) {
				// 初始化数据
				createRoleActivityEntity(uid);
				return;
			}

			// 升级活动数据
			upgradeRoleActivityEntity(entity);
		} finally {
			lock.unlock();
		}
	}

	public void resetActivityInfo(long uid) {
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleActivityEntity entity = Globals.getEntityProxy().get(RoleActivityEntity.class, uid);
			if (entity == null) {
				// 初始化数据。正常逻辑不会走到这里，玩家登录时会通过initRoleActivityEntity()创建数据。
				createRoleActivityEntity(uid);
				return;
			}

			// 更新活动数据
			resetRoleActivityEntity(entity);
		} finally {
			lock.unlock();
		}
	}

	public void onCheck(int activityId, int num, long uid) {
		// 活动目标+num
		ReentrantLock lock = LockManager.getInstance().getLock(uid);
		lock.lock();
		try {
			RoleActivityEntity entity = Globals.getEntityProxy().get(RoleActivityEntity.class, uid);
			if (entity == null) {
				Loggers.activityLogger.error("No RoleActivityEntity! uid = " + uid + ", activityId = " + activityId);
				return;
			}

			Map<Integer, RoleActivityInfo> activityInfoMap = JsonUtil.toMapIntegerKey(entity.getActivityInfo(), RoleActivityInfo.class);
			RoleActivityInfo roleActivityInfo = activityInfoMap.get(activityId);
			if (roleActivityInfo == null) {
				Loggers.activityLogger.error("No RoleActivityInfo! uid = " + uid + ", activityId = " + activityId);
				return;
			}
			roleActivityInfo.setMyTarget(roleActivityInfo.getMyTarget() + num);
			entity.setActivityInfo(JsonUtil.toJson(activityInfoMap));
			Globals.getEntityProxy().update(entity);
		} finally {
			lock.unlock();
		}
	}

	private GCActivityInfoProto.Builder buildAllActivityInfoMsg() {
		GCActivityInfoProto.Builder gcActivityInfoProto = GCActivityInfoProto.newBuilder();
		gcActivityInfoProto.setStatus(ErrorCodeConst.SUCCESS);
		gcActivityInfoProto.setActivityId(ActivityConstant.ACTIVITY_TYPE_ALL);// 0代表全部活动
		TreeMap<Integer, ActivityBean> dataMap = Datas.getDataMap(ActivityBean.class);
		for (int activityId : dataMap.keySet()) {
			ActivityInfo.Builder activityInfo = buildActivityInfo(activityId);
			if (activityInfo == null) {
				continue;
			}
			gcActivityInfoProto.addActivityInfo(activityInfo);
		}
		return gcActivityInfoProto;
	}

	private GCActivityInfoProto.Builder buildActivityInfoMsgById(int activityId) {
		GCActivityInfoProto.Builder gcActivityInfoProto = GCActivityInfoProto.newBuilder();
		gcActivityInfoProto.setStatus(ErrorCodeConst.SUCCESS);
		gcActivityInfoProto.setActivityId(activityId);
		ActivityInfo.Builder activityInfo = buildActivityInfo(activityId);
		if (activityInfo == null) {
			return gcActivityInfoProto;
		}
		gcActivityInfoProto.addActivityInfo(activityInfo);
		return gcActivityInfoProto;
	}

	private Builder buildActivityInfo(int activityId) {
		// 获取活动存储数据
		ActivityEntity activityEntity = GameGlobals.activityManager.getActivityEntityById(activityId);
		if (activityEntity == null) {
			Loggers.serverLogger.error("No ActivityEntity! activityId = " + activityId);
			return null;
		}

		long now = System.currentTimeMillis();
		boolean isOpen = activityEntity.isOpen();
		boolean isShow = now >= activityEntity.getShowTimeStart().getTime() && now < activityEntity.getShowTimeEnd().getTime();
		int state = activityEntity.getState();
		int startTimeSeconds = 0;
		if (isOpen && isShow && state == ActivityConstant.ACITIVITY_STATE_NOT_START) {
			startTimeSeconds = (int) ((activityEntity.getTimeStart().getTime() - now) / 1000);
		}
		int endTimeSeconds = 0;
		if (isOpen && isShow && state == ActivityConstant.ACITIVITY_STATE_START) {
			endTimeSeconds = (int) ((activityEntity.getTimeEnd().getTime() - now) / 1000);
		}

		ActivityInfo.Builder activityInfo = ActivityInfo.newBuilder();
		activityInfo.setId(activityId);// 活动id
		activityInfo.setIsShow(isOpen && isShow);// 是否显示
		activityInfo.setState(state);// 活动状态. 0未开始，1进行中，2已结束
		activityInfo.setStartTimeSeconds(startTimeSeconds);// 距离活动开始剩余时间（秒）
		activityInfo.setEndTimeSeconds(endTimeSeconds);// 距离活动结束剩余时间（秒）
		// activityInfo.addReward(ActivityRewardProto.newBuilder());// 奖励（兼容原有活动）
		return activityInfo;
	}

	private void createRoleActivityEntity(long uid) {
		RoleActivityEntity roleActivityEntity = new RoleActivityEntity();
		roleActivityEntity.setUid(uid);

		// 活动信息
		TreeMap<Integer, RoleActivityInfo> activityMap = Maps.newTreeMap();
		for (ActivityEntity activity : GameGlobals.activityManager.getActivityEntityMap().values()) {
			RoleActivityInfo roleActivityInfo = new RoleActivityInfo();
			roleActivityInfo.init(activity);
			activityMap.put(roleActivityInfo.getActivityId(), roleActivityInfo);
		}
		String activityInfo = JsonUtil.toJson(activityMap);
		roleActivityEntity.setActivityInfo(activityInfo);
		Globals.getEntityProxy().insert(roleActivityEntity);
	}

	private void upgradeRoleActivityEntity(RoleActivityEntity roleActivityEntity) {
		boolean isActivityInfoChanged = false;
		// 玩家活动信息
		Map<Integer, RoleActivityInfo> activityInfoMap = JsonUtil.toMapIntegerKey(roleActivityEntity.getActivityInfo(), RoleActivityInfo.class);
		// 活动当前所有信息
		for (ActivityEntity activity : GameGlobals.activityManager.getActivityEntityMap().values()) {
			// 活动已关闭
			if (!activity.isOpen()) {
				continue;
			}

			// 玩家活动版本与当前活动版本相同，不做任何处理
			if (activityInfoMap.containsKey(activity.getId()) && activityInfoMap.get(activity.getId()).getVersion() == activity.getVersion()) {
				continue;
			}

			// 活动数据升级
			RoleActivityInfo roleActivityInfo = null;
			if (!activityInfoMap.containsKey(activity.getId())) {
				// 新活动
				roleActivityInfo = new RoleActivityInfo();
				activityInfoMap.put(activity.getId(), roleActivityInfo);
			} else {
				// 版本升级
				roleActivityInfo = activityInfoMap.get(activity.getId());
			}
			roleActivityInfo.init(activity);

			isActivityInfoChanged = true;
		}

		// 检测已不存在的活动，并从玩家活动数据中删除
		Set<Integer> removedActivityIdSet = Sets.newHashSet();
		for (int activityId : activityInfoMap.keySet()) {
			if (!GameGlobals.activityManager.getActivityEntityMap().containsKey(activityId)) {
				removedActivityIdSet.add(activityId);
			}
		}
		for (int removedActivityId : removedActivityIdSet) {
			activityInfoMap.remove(removedActivityId);
			isActivityInfoChanged = true;
		}

		// 活动数据有变化，更新数据库
		if (isActivityInfoChanged) {
			String activityInfo = JsonUtil.toJson(activityInfoMap);
			roleActivityEntity.setActivityInfo(activityInfo);
			Globals.getEntityProxy().updateAsync(roleActivityEntity);
		}
	}

	private void resetRoleActivityEntity(RoleActivityEntity roleActivityEntity) {
		if (roleActivityEntity == null) {
			return;
		}

		boolean isActivityInfoChanged = false;
		// 玩家活动信息
		Map<Integer, RoleActivityInfo> activityInfoMap = JsonUtil.toMapIntegerKey(roleActivityEntity.getActivityInfo(), RoleActivityInfo.class);
		for (ActivityBean activityBean : Datas.getDataMap(ActivityBean.class).values()) {
			EActivityLogicType logicType = EActivityLogicType.valuesOf(activityBean.getLogicType());
			if (logicType == null) {
				Loggers.activityLogger.error("No EActivityLogicType! activityId = " + activityBean.getId() + ", logicType = " + activityBean.getLogicType());
				continue;
			}

			RoleActivityInfo roleActivityInfo = activityInfoMap.get(activityBean.getId());
			if (roleActivityInfo == null) {
				// 在玩家登录成功时，会检测并更新玩家的活动版本数据，应该永远不会出现null的情况，这里仅增强程序健壮性
				Loggers.activityLogger.error("No RoleActivityInfo! activityId = " + activityBean.getId() + ", uid = " + roleActivityEntity.getUid());
				continue;
			}

			// 重置玩家活动信息
			isActivityInfoChanged |= logicType.getHandler().resetRoleActivityInfo(roleActivityEntity.getUid(), roleActivityInfo);
		}

		// 活动数据有变化，更新数据库
		if (isActivityInfoChanged) {
			String activityInfo = JsonUtil.toJson(activityInfoMap);
			roleActivityEntity.setActivityInfo(activityInfo);
			Globals.getEntityProxy().updateAsync(roleActivityEntity);
		}
	}

	private void addCompleteActivityLog(long uid, long activityId, int target, int type, UserBean user) {
		ActivityCompleteLog log = new ActivityCompleteLog(user);
		log.setActivityType(type);
		log.setActivityId(activityId);
		log.setTarget(target);
		LogfPrinter.getInstance().push(log);
	}

	private void addReceiveActivityLog(long uid, long activityId, int target, int type, List<ItemVo> items, UserBean user) {
		ActivityReceiveLog log = new ActivityReceiveLog(user);
		log.setActivityType(type);
		log.setActivityId(activityId);
		log.setTarget(target);
		log.getItemList().addAll(items);
		LogfPrinter.getInstance().push(log);
	}
}
