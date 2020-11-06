package com.game.myapp.module.activity.controller;

import java.util.List;

import com.game.core.handler.MsgBack;
import com.game.myapp.module.activity.handler.ActivityBaseHandler;

/**
 * 累计消耗
 * 
 * @author LPF id,status;id,status
 *
 */
public class ActivityConsumeSumController extends ActivityBaseHandler {

	public boolean onCheck(int completeNum, long uid) {
		return true;
//		if (GameGlobals.activityManager.getOpenActivityByType(ActivityManager.ACTIVITY_TYPE_CONSUME_SUM) == null) {
//			return false;
//		}
//		if (completeNum <= 0) {
//			return false;
//		}
//		clearActivityStatus(uid);
//		ActivityEntity activityEntity = GameGlobals.activityManager.getOpenActivityByType(ActivityManager.ACTIVITY_TYPE_CONSUME_SUM);
//		List<Long> rewards = new ArrayList<Long>();// 完成的奖励id
//		List<ActivityRewardsEntity> rewardsEntities = GameGlobals.activityManager.getActivityRewardsListByAvtivityType(activityEntity.getId());
//		ActivityRecordEntity recordEntity = GameGlobals.activityManager.getActivityRecordDB(uid);
//		int newConsumSum = recordEntity.getConsumSumNum() + completeNum;
//		String record = recordEntity.getConsumeSumRecord();
//		for (ActivityRewardsEntity rewardsEntity : rewardsEntities) {
//			if (rewardsEntity.getTarget() <= newConsumSum) {
//				if (isHaveRewardsId(record, rewardsEntity.getId())) {
//					continue;
//				}
//				rewards.add(rewardsEntity.getId());
//				// 完成活动日志
//				UserBean user = Globals.getUserBeanManager().getUserBean(uid);
//				if (user != null) {
//					GameGlobals.activityManager.addCompleteActivityLog(uid, activityEntity.getId(), rewardsEntity.getTarget(),
//							ActivityManager.ACTIVITY_TYPE_CONSUME_SUM, user);
//				}
//			}
//		}
//		updateRoleActivityDB(rewards, uid, completeNum);
//		if (rewards.size() > 0) {
//			return true;
//		} else {
//			return false;
//		}
	}

	public void updateRoleActivityDB(List<Long> rewardIds, long uid, int num) {
//		ReentrantLock lock = Globals.getLockManager().getLock(uid);
//		lock.lock();
//		try {
//			ActivityRecordEntity recordEntity = GameGlobals.activityManager.getActivityRecordDB(uid);
//			recordEntity.setConsumSumNum(recordEntity.getConsumSumNum() + num);
//			String record = recordEntity.getConsumeSumRecord();
//			for (long rewardId : rewardIds) {
//				record += rewardId + "," + ActivityManager.CAN_REWARD + ";";
//			}
//			recordEntity.setConsumeSumRecord(record);
//			Globals.getEntityProxy().updateAsync(recordEntity);
//		} finally {
//			lock.unlock();
//		}
	}

	public void receiveRewards(MsgBack msgBack, long rewardId, long uid) {
//		GCGetActivityRewardMsg.GCGetActivityRewardProto.Builder builder = GCGetActivityRewardMsg.GCGetActivityRewardProto.newBuilder();
//		msgBack.addBuilder(builder);
//		ReentrantLock lock = Globals.getLockManager().getLock(uid);
//		lock.lock();
//		try {
//			ActivityRecordEntity recordEntity = GameGlobals.activityManager.getActivityRecordDB(uid);
//			ActivityEntity activityEntity = GameGlobals.activityManager.getOpenActivityByType(ActivityManager.ACTIVITY_TYPE_CONSUME_SUM);
//			if (activityEntity == null || activityEntity.getId() != recordEntity.getConsumeSumActivityId()) {
//				builder.setStatus(ActivityManager.ACTIVITY_CLOSE);
//				return;
//			}
//
//			String status = recordEntity.getConsumeSumRecord();
//			if (!isHaveRewardsId(status, rewardId)) {// 没有完成这个奖励挡位
//				builder.setStatus(ActivityManager.NOT_REWARD);
//				return;
//			} else {
//				String[] statusArr = status.split(";");
//				int index = 0;
//				loop1: for (int i = 0; i < statusArr.length; i++) {
//					if (Long.parseLong(statusArr[i].split(",")[0]) != rewardId) {
//						continue;
//					} else {
//						if (Integer.parseInt(statusArr[i].split(",")[1]) != ActivityManager.CAN_REWARD) {
//							builder.setStatus(ActivityManager.ALREADY_REWARD);
//							return;
//						}
//						List<ActivityRewardsEntity> rewardsEntities = GameGlobals.activityManager.getActivityRewardsListByAvtivityType(activityEntity.getId());
//						loop2: for (ActivityRewardsEntity rewardsEntity : rewardsEntities) {
//							if (rewardsEntity.getId() != rewardId) {
//								continue;
//							} else {
//								builder.setRewards(rewardsEntity.getRewards());
//								String[] rewardArr = rewardsEntity.getRewards().split(";");
//
//								for (String reward : rewardArr) {
//									int itemType = Integer.parseInt(reward.split(",")[0]);
//									int itemId = Integer.parseInt(reward.split(",")[0]);
//									int itemNum = Integer.parseInt(reward.split(",")[0]);
//									GameGlobals.bagManager.addItem(uid, itemType, itemId, itemNum, LogfConstants.CHANNEL_ACTIVITY, String.valueOf(rewardId));
//								}
//								// 领取
//								UserBean user = Globals.getUserBeanManager().getUserBean(uid);
//								if (user != null) {
//									GameGlobals.activityManager.addReceiveActivityLog(uid, activityEntity.getId(), rewardsEntity.getTarget(),
//											ActivityManager.ACTIVITY_TYPE_CONSUME_SUM, rewardsEntity.getRewards(), user);
//								}
//								break loop2;
//							}
//						}
//						index = i;
//						break loop1;
//					}
//				}
//				String newSate = rewardId + "," + ActivityManager.ALREADY_REWARD;
//				statusArr[index] = newSate;
//				String newStates = "";
//				for (String s : statusArr) {
//					newStates += s + ";";
//				}
//				recordEntity.setConsumeSumRecord(newStates);
//				Globals.getEntityProxy().updateAsync(recordEntity);
//			}
//			builder.setType(ActivityManager.ACTIVITY_TYPE_CONSUME_SUM);
//			builder.setStatus(ActivityManager.REWARD_SUCCESS);
//		} finally {
//			lock.unlock();
//		}
	}

	/**
	 * 检测是否存在这个奖励id
	 */

	public boolean isHaveRewardsId(String ids, long target) {
		if (ids.equals("")) {
			return false;
		}
		String[] rewardArr = ids.split(";");
		if (rewardArr.length == 0) {
			return false;
		}
		for (String reward : rewardArr) {
			String[] idArr = reward.split(",");
			if (Long.parseLong(idArr[0]) == target) {
				return true;
			}
		}
		return false;
	}

	public String getActivityRewardsState(long uid) {
//		ActivityRecordEntity recordEntity = GameGlobals.activityManager.getActivityRecordDB(uid);
//		return recordEntity.getConsumeSumRecord();
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
//				ActivityEntity activityEntity = GameGlobals.activityManager.getOpenActivityByType(ActivityManager.ACTIVITY_TYPE_CONSUME_SUM);
//				if (activityEntity == null || activityEntity.getId() != activityRecordEntity.getConsumeSumActivityId()) {
//					activityRecordEntity.setConsumeSumActivityId(activityEntity == null ? 0 : activityEntity.getId());
//					activityRecordEntity.setConsumeSumRecord("");
//					activityRecordEntity.setConsumSumNum(0);
//					Globals.getEntityProxy().updateAsync(activityRecordEntity);
//				}
//			} finally {
//				lock.unlock();
//			}
//		}
	}

	public int getRewardsState(long rewardId, long uid) {
//		String states = getActivityRewardsState(uid);
//
//		int result = ActivityManager.NOT_REWARD;
//		if (states.isEmpty()) {
//			return result;
//		}
//		String[] stateArr = states.split(";");
//		if (stateArr.length == 0) {
//			return result;
//		}
//		for (String state : stateArr) {
//			String[] temp = state.split(",");
//			if (Long.parseLong(temp[0]) == rewardId) {
//				if (Integer.parseInt(temp[1]) == ActivityManager.CAN_REWARD) {
//					result = ActivityManager.CAN_REWARD;
//				} else {
//					result = ActivityManager.ALREADY_REWARD;
//				}
//			}
//		}
//		return result;
		return 0;
	}
}
