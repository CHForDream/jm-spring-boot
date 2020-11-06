package com.game.myapp.module.daytask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.TaskPointUpdateLog;
import com.game.common.logf.impl.TaskReceiveBoxLog;
import com.game.constants.AchieveConstants;
import com.game.constants.DayTaskConstants;
import com.game.core.handler.MsgBack;
import com.game.db.entity.DaytaskEntity;
import com.game.generate.bean.ActivenessBean;
import com.game.generate.bean.QuestBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.function.ISystemId;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.utils.DateUtil;
import com.game.vo.item.ItemVo;

import buffer.CGGetDayTaskListMsg.CGGetDayTaskListProto;
import buffer.CGGetDayTaskRewardMsg.CGGetDayTaskRewardProto;
import buffer.GCGetDayTaskListMsg;
import buffer.GCGetDayTaskRewardMsg;
import buffer.GCGetDayTaskRewardMsg.GCGetDayTaskRewardProto;

public class DayTaskManager {
	// 将成就配置按类型分组
	private Map<Integer, List<QuestBean>> taskTypeMap = new HashMap<Integer, List<QuestBean>>();

	public DayTaskManager() {
		for (QuestBean bean : Datas.getDataMap(QuestBean.class).values()) {
			int type = bean.getType();
			List<QuestBean> taskList = taskTypeMap.get(type);
			if (taskList == null) {
				taskList = new ArrayList<QuestBean>();
				taskTypeMap.put(type, taskList);
			}
			taskList.add(bean);
		}
	}

	public void CGGetDayTaskList(MsgBack msgBack, long uid, CGGetDayTaskListProto cgMsg) {
		GCGetDayTaskListMsg.GCGetDayTaskListProto.Builder gcMsg = GCGetDayTaskListMsg.GCGetDayTaskListProto.newBuilder();
		DaytaskEntity entity = this.getDaytaskEntity(uid);
		if (entity == null) {
			return;
		}
		for (QuestBean bean : Datas.getDataMap(QuestBean.class).values()) {
			//未开放的系统不显示
			int projectId = bean.getProjectId();
			if (projectId != 0 && !GameGlobals.functionOpenManager.onCheck(uid, projectId)) {
				continue;
			}
			int taskId = bean.getId();
			GCGetDayTaskListMsg.DayTaskProto.Builder tempTask = GCGetDayTaskListMsg.DayTaskProto.newBuilder();
			tempTask.setTaskId(taskId);
			tempTask.setProcess(String.valueOf(entity.getProcess(taskId)));
			tempTask.setStatus(entity.getStatus(taskId));
			gcMsg.addTasks(tempTask);
		}
		gcMsg.setActive(entity.getPoint());
		String actStr = entity.getActiveId();
		gcMsg.setRewards(actStr.substring(1, actStr.length() - 1));
		msgBack.addBuilder(gcMsg);
	}

	public void CGGetDayTaskReward(MsgBack msgBack, long uid, CGGetDayTaskRewardProto cgMsg) {
		GCGetDayTaskRewardMsg.GCGetDayTaskRewardProto.Builder gcMsg = GCGetDayTaskRewardMsg.GCGetDayTaskRewardProto.newBuilder();
		msgBack.addBuilder(gcMsg);

		int taskId = cgMsg.getTaskId();
		int rewardType = cgMsg.getRewardType();
		int actId = cgMsg.getActiveId();

		gcMsg.setTaskId(taskId);
		gcMsg.setActiveId(actId);
		gcMsg.setRewardType(rewardType);

		// 领取每日任务奖励
		handleDayTaskReward(uid, taskId, rewardType, gcMsg);
		// 领取活跃度奖励
		handleActiveReward(uid, actId, rewardType, gcMsg);
	}

	public void onTask(long uid, int taskType, int addNum, String... param) {
		if (taskType != DayTaskConstants.TYPE_LOGIN && !GameGlobals.functionOpenManager.onCheck(uid, ISystemId.SYSTEM_EVERY_TASK)) {
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			IDayTask task = DayTaskFactory.getImpl(taskType);
			task.onImpl(taskType, uid, addNum, param);
			// 检测小红点
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.TASK_COMPLETE);
		} finally {
			lock.unlock();
		}
	}

	public void addPoint(long uid, int point) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			DaytaskEntity entity = this.getDaytaskEntity(uid);
			if (entity == null) {
				return;
			}
			entity.setPoint(entity.getPoint() + point);
			Globals.getEntityProxy().updateAsync(entity);

			// 任务宝箱红点检测
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.TASK_REWARD);
		} finally {
			lock.unlock();
		}
	}

	public DaytaskEntity getDaytaskEntity(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 暂时写成0点重置
			DaytaskEntity entity = Globals.getEntityProxy().get(DaytaskEntity.class, uid);
			if (entity == null) {
				entity = new DaytaskEntity();
				entity.setUid(uid);
				entity.setResfreshTime(DateUtil.getDayRefreshTime(0));
				Globals.getEntityProxy().insert(entity);
			}
			long curTime = System.currentTimeMillis();
			if (curTime > entity.getResfreshTime().getTime()) {
				entity.setResfreshTime(DateUtil.getDayRefreshTime(0));
				entity.getTaskInfoMap().clear();
				entity.setPoint(0);
				entity.getActiveIdList().clear();
				Globals.getEntityProxy().updateAsync(entity);
			}
			return entity;
		} finally {
			lock.unlock();
		}
	}

	public List<QuestBean> getTaskListByType(int taskType) {
		return taskTypeMap.get(taskType);
	}

	/**
	 * 是否有任务宝箱可以领取
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public boolean hasActiveReward(long uid) {
		DaytaskEntity entity = this.getDaytaskEntity(uid);
		if (entity == null) {
			return false;
		}
		for (ActivenessBean bean : Datas.getDataMap(ActivenessBean.class).values()) {
			if (!entity.getActiveIdList().contains(bean.getId()) && bean.getNeed() <= entity.getPoint()) {
				return true;
			}
		}
		return false;
	}

	private void handleDayTaskReward(long uid, int taskId, int rewardType, GCGetDayTaskRewardProto.Builder gcMsg) {
		// 这里只领取每日任务奖励
		if (rewardType != 1) {
			return;
		}
		QuestBean bean = Datas.get(QuestBean.class, taskId);
		if (bean == null) {
			gcMsg.setStatus(1);
			return;
		}
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			DaytaskEntity entity = this.getDaytaskEntity(uid);
			if (entity == null || entity.getStatus(taskId) != AchieveConstants.STATUS_COMPLETE) {
				gcMsg.setStatus(1);
				return;
			}
			entity.updateStatus(taskId, AchieveConstants.STATUS_AWARD);
			Globals.getEntityProxy().updateAsync(entity);

			for (int i = 0; i < bean.getRewardList().size(); i++) {
				ItemVo award = bean.getRewardList().get(i);
				if (award == null || award.getNum() == 0) {
					continue;
				}
				GameGlobals.bagManager.addItem(uid, award, LogfConstants.CHANNEL_TASK, String.valueOf(taskId));
			}

			int point = entity.getPoint();
			gcMsg.setPoint(entity.getPoint());

			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.TASK_COMPLETE);

			LogfPrinter.getInstance().push(new TaskPointUpdateLog(Globals.getUserManager().getUserBean(uid), taskId, point));
		} finally {
			lock.unlock();
		}
	}

	private void handleActiveReward(long uid, int actId, int rewardType, GCGetDayTaskRewardProto.Builder gcMsg) {
		// 这里只领取活跃度奖励
		if (rewardType != 2) {
			return;
		}

		ActivenessBean bean = Datas.get(ActivenessBean.class, actId);
		if (bean == null) {
			gcMsg.setStatus(1);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			DaytaskEntity entity = this.getDaytaskEntity(uid);
			if (entity == null || bean.getNeed() > entity.getPoint() || !entity.addAciveId(actId)) {
				// 活跃度不足 或者 今天已经领取过该活跃值的奖励
				gcMsg.setStatus(1);
				return;
			}
			Globals.getEntityProxy().updateAsync(entity);
			gcMsg.setPoint(entity.getPoint());

			// Oct.24.2019 宝箱直接换成道具发送给玩家
			GameGlobals.giftManager.addGiftAwards(uid, bean.getGiftId(), 1, LogfConstants.CHANNEL_TASK);
			// 任务宝箱红点检测
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.TASK_REWARD);
			//添加领取宝箱日志
			LogfPrinter.getInstance().push(new TaskReceiveBoxLog(Globals.getUserManager().getUserBean(uid), bean.getGiftId()));
		} finally {
			lock.unlock();
		}
	}
}
