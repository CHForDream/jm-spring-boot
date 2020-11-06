package com.game.myapp.module.daytask.impl;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.TaskCompleteLog;
import com.game.common.user.UserBean;
import com.game.constants.AchieveConstants;
import com.game.db.entity.DaytaskEntity;
import com.game.generate.bean.QuestBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.daytask.IDayTask;
import com.game.myapp.module.guide.GuideConst;

public class CommTaskImpl implements IDayTask {
	@Override
	public void onImpl(int taskType, long uid, Object... param) {
		List<QuestBean> taskList = GameGlobals.taskManager.getTaskListByType(taskType);
		if (taskList == null) {
			return;
		}
		int num = (int) param[0];

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			DaytaskEntity entity = GameGlobals.taskManager.getDaytaskEntity(uid);
			if (entity == null) {
				return;
			}

			for (QuestBean bean : taskList) {
				int taskId = bean.getId();
				if (entity.getStatus(taskId) != AchieveConstants.STATUS_PROCESS) {
					continue;
				}

				int process = entity.getProcess(taskId);
				process += num;
				entity.updateProcess(taskId, Math.min(process, bean.getTotaltimes()));

				if (process >= bean.getTotaltimes()) {
					entity.updateStatus(taskId, AchieveConstants.STATUS_COMPLETE);
					GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_TASK, taskId);
					UserBean userBean = Globals.getUserManager().getUserBean(uid);
					if (userBean != null) {
						LogfPrinter.getInstance().push(new TaskCompleteLog(Globals.getUserManager().getUserBean(uid), taskType, taskId));
					}
				}
			}
			Globals.getEntityProxy().updateAsync(entity);
		} finally {
			lock.unlock();
		}
	}
}
