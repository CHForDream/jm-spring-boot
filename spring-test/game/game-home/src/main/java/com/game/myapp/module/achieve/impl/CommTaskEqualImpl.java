package com.game.myapp.module.achieve.impl;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.constants.AchieveConstants;
import com.game.db.entity.AchieveEntity;
import com.game.generate.bean.AchieveBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.achieve.IAchieveTask;
import com.game.myapp.module.guide.GuideConst;

public class CommTaskEqualImpl implements IAchieveTask {

	private static IAchieveTask _Instance = new CommTaskEqualImpl();

	public static IAchieveTask getInstance() {
		return _Instance;
	}

	@Override
	public void onImpl(int type, long uid, Object... param) {
		List<AchieveBean> achieveList = GameGlobals.achieveManager.getAchieveListByType(type);
		if (achieveList == null) {
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			AchieveEntity entity = GameGlobals.achieveManager.getAchieveEntity(uid);
			if (entity == null) {
				return;
			}

			for (AchieveBean bean : achieveList) {
				int achieveId = bean.getId();
				if (entity.getStatus(achieveId) != AchieveConstants.STATUS_PROCESS) {
					continue;
				}

				int process = (int) param[0];
				entity.updateProcess(achieveId, process);

				if (process >= bean.getParam2()) {
					entity.updateStatus(achieveId, AchieveConstants.STATUS_COMPLETE);
					GameGlobals.guideManager.onCheck(uid, GuideConst.OPEN_TYPE_ACHIEVEMENT, achieveId);
					GameGlobals.achieveManager.addCompleteLog(uid, achieveId);
				}
			}
			Globals.getEntityProxy().updateAsync(entity);
		} finally {
			lock.unlock();
		}
	}
}
