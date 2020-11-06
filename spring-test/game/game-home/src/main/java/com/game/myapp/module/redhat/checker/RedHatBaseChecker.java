package com.game.myapp.module.redhat.checker;

import org.apache.log4j.Logger;

import com.game.common.data.Datas;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.RedpointBean;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.redhat.RedHatManager;

public abstract class RedHatBaseChecker implements IRedHatChecker {

	protected abstract void doCheck(long uid);

	@Override
	public void check(long uid, RedHatBehavior behavior) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return;
		}
		if (!checkFunctionOpen(uid, behavior)) {
			return;
		}

		doCheck(uid);
	}

	private boolean checkFunctionOpen(long uid, RedHatBehavior behavior) {
		try {
			RedpointBean redpointBean = Datas.get(RedpointBean.class, behavior.getBehavior());
			if (redpointBean == null) {
				Logger.getLogger(RedHatManager.class).error("No redpoint id in redpoint.xls! codeId = " + behavior.getBehavior());
				return false;
			}
			if (!GameGlobals.functionOpenManager.onCheck(uid, redpointBean.getProjectId())) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
