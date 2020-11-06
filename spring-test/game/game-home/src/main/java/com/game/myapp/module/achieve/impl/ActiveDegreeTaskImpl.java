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

public class ActiveDegreeTaskImpl implements IAchieveTask {

	private static IAchieveTask _Instance = new ActiveDegreeTaskImpl();

	public static IAchieveTask getInstance() {
		return _Instance;
	}

	@Override
	public void onImpl(int type, long uid, Object... param) {
		List<AchieveBean> achieveList = GameGlobals.achieveManager.getAchieveListByType(type);
		if (achieveList == null) {
			return;
		}

		int lastPoint = (int) param[0];
		int curPoint = (int) param[1];
		int degree = GameGlobals.configManager.getAchieveDegree();
		if (degree <= lastPoint || degree > curPoint) {
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

				int process = entity.getProcess(achieveId);
				process++;
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
