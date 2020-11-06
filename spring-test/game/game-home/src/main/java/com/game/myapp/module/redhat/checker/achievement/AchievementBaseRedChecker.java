package com.game.myapp.module.redhat.checker.achievement;

import java.util.Map;

import com.game.common.data.Datas;
import com.game.common.user.UserBean;
import com.game.db.entity.AchieveEntity;
import com.game.generate.bean.AchieveBean;
import com.game.global.Globals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.checker.RedHatBaseChecker;

public abstract class AchievementBaseRedChecker extends RedHatBaseChecker {

	public void doAchievementCheck(long uid, RedHatBehavior behavior, int sort) {
		AchieveEntity db = Globals.getEntityProxy().get(AchieveEntity.class, uid);
		if (db == null) {
			return;
		}

		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		boolean flag = false;
		Map<Integer, AchieveEntity.AchieveInfo> statusMap = db.getAchieveInfoMap();
		for (Map.Entry<Integer, AchieveEntity.AchieveInfo> entry : statusMap.entrySet()) {
			int id = entry.getKey();
			AchieveBean bean = Datas.get(AchieveBean.class, id);
			if (bean == null) {
				continue;
			}

			if (bean.getSort() != sort) {
				continue;
			}
			int status = entry.getValue().getStatus();
			if (status == 1) {
				flag = true;
				break;
			}
		}

		boolean curr = user.isRedHatDirty(behavior.getBehavior());
		if (curr != flag) {
			user.setDirty(behavior.getBehavior(), flag);
		}
	}
}
