package com.game.myapp.module.function.contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.user.UserBean;
import com.game.db.entity.RoleGuideEntity;
import com.game.generate.bean.ProjectBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;

public abstract class AbsFunctionCheck implements IFunctionCheck {

	abstract boolean check(int config, int param);

	@Override
	public List<Integer> checkFunctionOpen(long uid, String... args) {
		int param = Integer.parseInt(args[0]);
		String addOpenId = "";
		List<Integer> result = new ArrayList<Integer>();
		for (ProjectBean projectBean : Datas.getDataMap(ProjectBean.class).values()) {
			// 类型不同
			if (projectBean.getOpenType() != getType()) {
				continue;
			}
			if (check(projectBean.getOpenValue(), param)) {
				continue;
			}
			if (GameGlobals.functionOpenManager.onCheck(uid, projectBean.getId())) {
				continue;
			}
			addOpenId += ("," + projectBean.getId());
			result.add(projectBean.getId());
		}
		if (!addOpenId.equals("")) {
			ReentrantLock lock = Globals.getLockManager().getLock(uid);
			lock.lock();
			try {
				RoleGuideEntity entity = GameGlobals.guideManager.getRoleGuideEntity(uid);
				entity.setSystemId(entity.getSystemId() + addOpenId);
				Globals.getEntityProxy().updateAsync(entity);

				UserBean user = Globals.getUserManager().getUserBean(uid);
				if (user != null) {
					user.setFunctionOpen(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return result;
	}
}
