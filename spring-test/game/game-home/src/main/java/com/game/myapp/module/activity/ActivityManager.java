
package com.game.myapp.module.activity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.user.UserBean;
import com.game.constants.Loggers;
import com.game.db.entity.ActivityEntity;
import com.game.generate.bean.ActivityBean;
import com.game.global.Globals;
import com.game.myapp.module.event.ActivityChangedEvent;
import com.game.rpc.RpcManager;
import com.game.rpc.service.IActivityService;
import com.game.utils.TimeUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ActivityManager {
	/** 全部的活动数据<活动Id, 活动数据>。因为ActivityEntity的缓存类型为NoCache, 数据只能由业务Manager自己做缓存 */
	private Map<Integer, ActivityEntity> activityEntityMap = Maps.newConcurrentMap();
	/** 活动逻辑映射Map<逻辑类型, 活动数据List> */
	private Map<EActivityLogicType, List<ActivityEntity>> logicActivityMap = Maps.newConcurrentMap();
	/** 是否在重载活动数据 */
	private AtomicBoolean isReloading = new AtomicBoolean(false);
	/** 活动逻辑锁 */
	private ReentrantLock lock = new ReentrantLock();

	public ActivityManager() {
	}

	public void loadData() {
		isReloading.set(true);
		lock.lock();
		try {
			for (ActivityBean activityBean : Datas.getDataMap(ActivityBean.class).values()) {
				ActivityEntity entity = Globals.getEntityProxy().getNotCache(ActivityEntity.class, activityBean.getId());
				if (entity == null) {
					entity = new ActivityEntity();
					entity.setId(activityBean.getId());// 活动Id
					entity.setName(activityBean.getName());// 活动名
					entity.setDescription(activityBean.getDesc());// 活动描述
					//entity.setOpen(false);// 活动是否开启。初始化默认关闭
					//entity.setShowTimeStart(new Timestamp(0L));// 活动显示开始时间
					//entity.setShowTimeEnd(new Timestamp(0L));// 活动显示结束时间
					//entity.setTimeStart(new Timestamp(0L));// 活动开始时间
					//entity.setTimeEnd(new Timestamp(0L));// 活动结束时间

					// FIXME 测试代码，先默认开放所有活动
					entity.setOpen(true);// 活动是否开启。初始化默认关闭
					entity.setShowTimeStart(new Timestamp(TimeUtils.getTime(System.currentTimeMillis(), 2020, 6, 1, 0, 0, 0, 0)));// 活动显示开始时间
					entity.setShowTimeEnd(new Timestamp(TimeUtils.getTime(System.currentTimeMillis(), 2022, 7, 1, 0, 0, 0, 0)));// 活动显示结束时间
					// 月份从0开始，8月是7
					entity.setTimeStart(new Timestamp(TimeUtils.getTime(System.currentTimeMillis(), 2020, 7, 1, 0, 0, 0, 0)));// 活动开始时间
					entity.setTimeEnd(new Timestamp(TimeUtils.getTime(System.currentTimeMillis(), 2020, 8, 1, 0, 0, 0, 0)));// 活动结束时间
					Globals.getEntityProxy().insert(entity);
				}

				// 活动缓存数据
				activityEntityMap.put(activityBean.getId(), entity);

				EActivityLogicType logicType = EActivityLogicType.valuesOf(activityBean.getLogicType());
				if (logicType == null) {
					Loggers.activityLogger.error("No logicType! activityId = " + activityBean.getId() + ", logicType = " + activityBean.getLogicType());
					continue;
				}

				// 活动逻辑类型映射数据
				if (!logicActivityMap.containsKey(logicType)) {
					logicActivityMap.put(logicType, Lists.newArrayList());
				}
				logicActivityMap.get(logicType).add(entity);
			}
		} finally {
			isReloading.set(false);
			lock.unlock();
		}
	}

	/**
	 * 活动数据变更回调
	 */
	public void onActivityChanged() {
		// 重新初始化数据
		loadData();

		// 通知玩家活动数据变化
		Globals.applicationContext.publishEvent(new ActivityChangedEvent(new Object()));
	}

	public Map<Integer, ActivityEntity> getActivityEntityMap() {
		if (isReloading.get()) {
			lock.lock();
			try {
				return activityEntityMap;
			} finally {
				lock.unlock();
			}
		}
		return activityEntityMap;
	}

	public ActivityEntity getActivityEntityById(int activityId) {
		if (isReloading.get()) {
			lock.lock();
			try {
				return activityEntityMap.get(activityId);
			} finally {
				lock.unlock();
			}
		}
		return activityEntityMap.get(activityId);
	}

	public boolean isActivityOpenByType(EActivityLogicType logicType) {
		List<ActivityEntity> activityList = logicActivityMap.get(logicType);
		if (activityList == null || activityList.isEmpty()) {
			return false;
		}

		for (ActivityEntity activityEntity : activityList) {
			if (activityEntity.isOpen() && activityEntity.getState() == ActivityConstant.ACITIVITY_STATE_START) {
				return true;
			}
		}

		return false;
	}

	public void onCheck() {
		if (!Globals.getAppConfigBean().isMainHome()) {
			return;
		}

		// 逻辑主服控制活动的监测和状态切换
		lock.lock();
		boolean isChanged;
		try {
			isChanged = false;
			long now = System.currentTimeMillis();
			for (ActivityEntity entity : activityEntityMap.values()) {
				// 未开放的活动不处理
				if (!entity.isOpen()) {
					continue;
				}

				// 未开始
				int newState = ActivityConstant.ACITIVITY_STATE_NOT_START;
				if (now >= entity.getTimeStart().getTime() && now < entity.getTimeEnd().getTime()) {
					// 进行中
					newState = ActivityConstant.ACITIVITY_STATE_START;
				} else if (now >= entity.getTimeEnd().getTime()) {
					// 已结束
					newState = ActivityConstant.ACITIVITY_STATE_END;
				}

				if (entity.getState() == newState) {
					continue;
				}

				// 更新活动状态
				isChanged |= updateActivityState(entity, newState);
			}
		} finally {
			lock.unlock();
		}

		if (isChanged) {
			// 通知当服玩家活动数据变化
			Globals.applicationContext.publishEvent(new ActivityChangedEvent(new Object()));
			// 通知其他逻辑服更新活动数据
			notifyOtherServerActivityChanged();
		}
	}

	public void notifyOnlineUserActivityChanged() {
		Map<Long, UserBean> allUserMap = Globals.getUserManager().getAll();
		for (UserBean userBean : allUserMap.values()) {
			userBean.setActivityChanged(true);
		}
	}

	public void gmOnOffActivity(EActivityLogicType logicType, boolean isOpen) {
		List<ActivityEntity> list = logicActivityMap.get(logicType);
		if (list == null) {
			return;
		}

		for (ActivityEntity activityEntity : list) {
			if (activityEntity.isOpen() != isOpen) {
				activityEntity.setOpen(isOpen);
				Globals.getEntityProxy().update(activityEntity);
			}
		}

		// 通知客户端活动数据变化
		notifyOnlineUserActivityChanged();
	}

	private boolean updateActivityState(ActivityEntity entity, int newState) {
		int activityId = entity.getId();
		ActivityBean activityBean = Datas.get(ActivityBean.class, activityId);
		if (activityBean == null) {
			Loggers.activityLogger.error("No ActivityBean! activityId = " + activityId);
			return false;
		}

		Loggers.activityLogger.info("Acitivity state is changed! activityId = " + activityId + ", state = " + newState);
		entity.setState(newState);
		Globals.getEntityProxy().update(entity);

		EActivityLogicType logicType = EActivityLogicType.valuesOf(activityBean.getLogicType());
		if (logicType == null) {
			Loggers.activityLogger.error("No EActivityLogicType! activityId = " + activityId + ", logicType = " + logicType);
			return false;
		}
		switch (newState) {
		case ActivityConstant.ACITIVITY_STATE_START:
			// 活动开始
			logicType.getHandler().onStart(activityId);
			break;
		case ActivityConstant.ACITIVITY_STATE_END:
			// 活动结束，触发结算
			logicType.getHandler().onEnd(activityId);
			break;
		default:
			break;
		}
		return true;
	}

	private void notifyOtherServerActivityChanged() {
		Map<Integer, String> serverMap = Globals.getAppConfigBean().getHomeServerMap();
		for (int sid : serverMap.keySet()) {
			if (Globals.getAppConfigBean().getSid() == sid) {
				// 当前服已更新数据
				continue;
			}

			// 通知其他服活动信息变化
			IActivityService service = RpcManager.getRpcImplBySid(sid, IActivityService.class);
			service.onActivityChanged();
		}
	}
}
