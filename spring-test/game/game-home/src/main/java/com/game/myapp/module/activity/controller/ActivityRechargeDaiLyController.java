package com.game.myapp.module.activity.controller;

import java.util.List;

import com.game.core.handler.MsgBack;
import com.game.myapp.module.activity.handler.ActivityBaseHandler;

/**
 * 每日充值 (状态 time,rewardId,status;)
 * 
 * @author lpf id,status;id,status
 *
 */
public class ActivityRechargeDaiLyController extends ActivityBaseHandler {

	public boolean onCheck(int completeNum, long uid) {
		return false;
//		if (completeNum <= 0) {
//			return false;
//		}
//		long currTime = System.currentTimeMillis();
//		ActivityEntity activityEntity = GameGlobals.activityManager.getOpenActivityByType(ActivityManager.ACTIVITY_TYPE_RECHARGE_DAILY);
//		if (activityEntity == null) {
//			return false;
//		}
//		clearActivityStatus(uid);
//		List<Long> rewardIds = new ArrayList<Long>();
//		ActivityRecordEntity activityRecordEntity = GameGlobals.activityManager.getActivityRecordDB(uid);
//
//		List<ActivityRewardsEntity> activityRecordEntities = GameGlobals.activityManager.getActivityRewardsListByAvtivityType(activityEntity.getId());
//
//		Collections.sort(activityRecordEntities, new Comparator<ActivityRewardsEntity>() {
//			
//			public int compare(ActivityRewardsEntity o1, ActivityRewardsEntity o2) {
//				return o2.getTarget() - o1.getTarget();
//			}
//		});
//
//		String record = activityRecordEntity.getRechargeDailyRecord();
//		if (record.isEmpty()) {
//			rewardIds.add(activityRecordEntities.get(0).getId());
//		} else {
//			String[] statusArr = record.split(";");
//			int day = statusArr.length;
//			String lastUpdate = statusArr[day - 1];
//			long lastUpdateDay = Long.parseLong(lastUpdate.split(",")[0]);
//			Date lastUpdateDate = new Date(lastUpdateDay);
//			Date currDate = new Date(currTime);
//			if (Utils.differentDays(lastUpdateDate, currDate) < 1) {// 今天的每日已完成
//				return false;
//			} else {
//				ActivityRewardsEntity rewardsEntity = activityRecordEntities.get(activityRecordEntities.size() - 1);
//				if (rewardsEntity == null) {
//					return false;
//				} else {
//					rewardIds.add(rewardsEntity.getId());
//					// 完成活动日志
//					UserBean user = Globals.getUserBeanManager().getUserBean(uid);
//					if (user != null) {
//						GameGlobals.activityManager.addCompleteActivityLog(uid, activityEntity.getId(), rewardsEntity.getTarget(),
//								ActivityManager.ACTIVITY_TYPE_RECHARGE_DAILY, user);
//					}
//				}
//			}
//		}
//
//		this.updateRoleActivityDB(rewardIds, uid, completeNum);
//
//		if (rewardIds.size() > 0) {
//			return true;
//		} else {
//			return false;
//		}
	}

	public void updateRoleActivityDB(List<Long> rewardIds, long uid, int num) {
//		ReentrantLock lock = Globals.getLockManager().getLock(uid);
//		lock.lock();
//		try {
//			long currTime = System.currentTimeMillis();
//			ActivityRecordEntity recordEntity = GameGlobals.activityManager.getActivityRecordDB(uid);
//			String record = recordEntity.getRechargeDailyRecord();
//			for (long rewardId : rewardIds) {
//				record += currTime + "," + rewardId + "," + ActivityManager.CAN_REWARD + ";";
//			}
//			recordEntity.setRechargeDailyRecord(record);
//			Globals.getEntityProxy().updateAsync(recordEntity);
//		} finally {
//			lock.unlock();
//		}
	}

	public void receiveRewards(MsgBack msgBack, long rewardId, long uid) {
//		GCGetActivityRewardMsg.GCGetActivityRewardProto.Builder builder = GCGetActivityRewardMsg.GCGetActivityRewardProto.newBuilder();
//		msgBack.addBuilder(builder);
//		ActivityEntity activityEntity = GameGlobals.activityManager.getOpenActivityByType(ActivityManager.ACTIVITY_TYPE_RECHARGE_DAILY);
//		if (activityEntity == null) {
//			builder.setStatus(ActivityManager.ACTIVITY_CLOSE);
//			return;
//		}
//		ReentrantLock lock = Globals.getLockManager().getLock(uid);
//		lock.lock();
//		try {
//			ActivityRecordEntity recordEntity = GameGlobals.activityManager.getActivityRecordDB(uid);
//			String status = recordEntity.getRechargeDailyRecord();
//			if (!isHaveRewardsId(status, rewardId)) {
//				builder.setStatus(ActivityManager.NOT_REWARD);
//				return;
//			}
//			String[] statusArr = status.split(";");
//			int index = 0;
//			loop1: for (int i = 0; i < statusArr.length; i++) {
//				if (Long.parseLong(statusArr[i].split(",")[1]) != rewardId) {
//					continue;
//				} else {
//					if (Integer.parseInt(statusArr[i].split(",")[2]) != ActivityManager.CAN_REWARD) {
//						builder.setStatus(ActivityManager.ALREADY_REWARD);
//						return;
//					}
//					List<ActivityRewardsEntity> rewardsEntities = GameGlobals.activityManager.getActivityRewardsListByAvtivityType(activityEntity.getId());
//					loop2: for (ActivityRewardsEntity rewardsEntity : rewardsEntities) {
//						if (rewardsEntity.getId() != rewardId) {
//							continue;
//						} else {
//							builder.setRewards(rewardsEntity.getRewards());
//							String[] rewardArr = rewardsEntity.getRewards().split(";");
//
//							for (String reward : rewardArr) {
//								int itemType = Integer.parseInt(reward.split(",")[0]);
//								int itemId = Integer.parseInt(reward.split(",")[0]);
//								int itemNum = Integer.parseInt(reward.split(",")[0]);
//								GameGlobals.bagManager.addItem(uid, itemType, itemId, itemNum, LogfConstants.CHANNEL_ACTIVITY, String.valueOf(rewardId));
//							}
//							// 领取
//							UserBean user = Globals.getUserBeanManager().getUserBean(uid);
//							if (user != null) {
//								GameGlobals.activityManager.addReceiveActivityLog(uid, activityEntity.getId(), rewardsEntity.getTarget(),
//										ActivityManager.ACTIVITY_TYPE_RECHARGE_DAILY, rewardsEntity.getRewards(), user);
//							}
//							break loop2;
//						}
//					}
//					index = i;
//					break loop1;
//				}
//			}
//			String newSate = statusArr[index].split(",")[0] + "," + rewardId + "," + ActivityManager.ALREADY_REWARD;
//			statusArr[index] = newSate;
//			String newStates = "";
//			for (String s : statusArr) {
//				newStates += s + ";";
//			}
//			recordEntity.setRechargeDailyRecord(newStates);
//			Globals.getEntityProxy().updateAsync(recordEntity);
//		} finally {
//			lock.unlock();
//		}
//
//		builder.setType(ActivityManager.ACTIVITY_TYPE_RECHARGE_DAILY);
//		builder.setStatus(ActivityManager.REWARD_SUCCESS);
	}

	public String getActivityRewardsState(long uid) {
//		ActivityRecordEntity recordEntity = GameGlobals.activityManager.getActivityRecordDB(uid);
//		return recordEntity.getRechargeDailyRecord();
		return null;
	}

	public void clearActivityStatus(long uid) {
//		ActivityRecordEntity activityRecordEntity = Globals.getEntityProxy().get(ActivityRecordEntity.class, uid);
//		if (activityRecordEntity == null) {
//			return;
//		} else {
//			ReentrantLock lock = Globals.getLockManager().getLock(uid);
//			lock.lock();
//			try {
//				ActivityEntity activityEntity = GameGlobals.activityManager.getOpenActivityByType(ActivityManager.ACTIVITY_TYPE_RECHARGE_DAILY);
//				if (activityEntity == null || activityEntity.getId() != activityRecordEntity.getRechargeDailyActivityId()) {
//					activityRecordEntity.setRechargeDailyActivityId(activityEntity == null ? 0 : activityEntity.getId());
//					activityRecordEntity.setRechargeDailyRecord("");
//					Globals.getEntityProxy().updateAsync(activityRecordEntity);
//				}
//			} finally {
//				lock.unlock();
//			}
//		}
	}

	public int getRewardsState(long rewardId, long uid) {
//		String status = getActivityRewardsState(uid);
//		int result = ActivityManager.NOT_REWARD;
//		if (status.isEmpty()) {
//			return result;
//		}
//		String[] stateArr = status.split(";");
//		if (stateArr.length == 0) {
//			return result;
//		}
//		for (String state : stateArr) {
//			String[] temp = state.split(",");
//			if (Long.parseLong(temp[1]) == rewardId) {
//				if (Integer.parseInt(temp[2]) == ActivityManager.CAN_REWARD) {
//					result = ActivityManager.CAN_REWARD;
//				} else {
//					result = ActivityManager.ALREADY_REWARD;
//				}
//			}
//		}
//		return result;
		return 0;
	}

	public boolean isHaveRewardsId(String ids, long target) {
		if (ids.isEmpty()) {
			return false;
		}
		String[] rewardArr = ids.split(";");
		if (rewardArr.length == 0) {
			return false;
		}
		for (String reward : rewardArr) {
			String[] idArr = reward.split(",");
			if (Long.parseLong(idArr[1]) == target) {
				return true;
			}
		}
		return false;
	}
}
