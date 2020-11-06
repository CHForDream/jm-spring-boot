package com.game.myapp.module.redhat.checker.task;

import com.game.common.user.UserBean;
import com.game.constants.DayTaskConstants;
import com.game.db.entity.DaytaskEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public class TaskRedChecker extends RedHatBaseChecker {

	@Override
	protected void doCheck(long uid) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}
		boolean flag = false;
		DaytaskEntity entity = GameGlobals.taskManager.getDaytaskEntity(uid);
		if (entity == null || entity.getTaskInfoMap().size() == 0) {
			flag = true;
		} else {
			for (int taskId : entity.getTaskInfoMap().keySet()) {
				if (entity.getStatus(taskId) == DayTaskConstants.STATUS_COMPLETE) {
					flag = true;
					break;
				}
			}
		}
		user.setDirty(RedHatBehavior.TASK_COMPLETE.getBehavior(), flag);
	}
}
