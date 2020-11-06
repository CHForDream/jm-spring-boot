package com.game.myapp.module.activity.handler;

import com.game.common.data.Datas;
import com.game.constants.Loggers;
import com.game.db.entity.ActivityEntity;
import com.game.generate.bean.ActivityBean;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.activity.EActivityRoleResetType;
import com.game.myapp.module.activity.IActivityHandler;
import com.game.myapp.module.activity.RoleActivityInfo;
import com.game.utils.TimeUtils;

public class ActivityBaseHandler implements IActivityHandler {

	@Override
	public void onStart(int activityId) {
	}

	@Override
	public void onEnd(int activityId) {
	}

	@Override
	public boolean resetRoleActivityInfo(long uid, RoleActivityInfo roleActivityInfo) {
		if (roleActivityInfo == null) {
			Loggers.activityLogger.error("resetRoleInfo(): RoleActivityInfo is null!");
			return false;
		}

		ActivityEntity activity = GameGlobals.activityManager.getActivityEntityById(roleActivityInfo.getActivityId());
		if (activity == null) {
			Loggers.activityLogger.error("No ActivityEntity! activityId = " + roleActivityInfo.getActivityId());
			return false;
		}
		if (!activity.isOpen()) {
			return false;
		}

		ActivityBean activityBean = Datas.get(ActivityBean.class, roleActivityInfo.getActivityId());
		if (activityBean == null) {
			Loggers.activityLogger.error("No ActivityBean! activityId = " + activity.getId());
			return false;
		}

		EActivityRoleResetType resetType = EActivityRoleResetType.valuesOf(activityBean.getResetType());
		if (resetType == null) {
			Loggers.activityLogger.error("No EActivityRoleResetType! activityId = " + activity.getId() + ", resetType = " + resetType);
			return false;
		}

		boolean needReset = false;
		long now = System.currentTimeMillis();
		switch (resetType) {
		case DAILY:
			// 每日重置
			needReset = !TimeUtils.isSameDay(roleActivityInfo.getResetTime(), now);
			break;
		case WEEKLY:
			// 每周一重置
			long beginOfWeek = TimeUtils.getBeginOfWeek(now);
			needReset = roleActivityInfo.getResetTime() < beginOfWeek;
			break;
		case ACTIVITY_OPEN:
			// 活动开始时重置
			needReset = roleActivityInfo.getResetTime() < activity.getTimeStart().getTime();
			break;
		case NEVER:
			break;
		default:
			break;
		}

		// 重置活动数据
		if (needReset) {
			roleActivityInfo.init(activity);
			return true;
		}

		return false;
	}
}
