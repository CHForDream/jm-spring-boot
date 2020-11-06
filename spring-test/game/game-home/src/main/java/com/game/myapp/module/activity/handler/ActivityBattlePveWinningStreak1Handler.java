package com.game.myapp.module.activity.handler;

import com.game.myapp.module.activity.RoleActivityInfo;

public class ActivityBattlePveWinningStreak1Handler extends ActivityBaseHandler {

	@Override
	public boolean resetRoleActivityInfo(long uid, RoleActivityInfo roleActivityInfo) {
		// PVE连胜会每日重置，这里不需要处理
//		ReentrantLock lock = LockManager.getInstance().getLock(uid);
//		lock.lock();
//		try {
//			RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
//			if (roleEntity.getPveConWinNum() != 0) {
//				roleEntity.setPveConWinNum(0);
//				Globals.getEntityProxy().update(roleEntity);
//			}
//			return super.resetRoleActivityInfo(uid, roleActivityInfo);
//		} finally {
//			lock.unlock();
//		}

		return super.resetRoleActivityInfo(uid, roleActivityInfo);
	}
}
