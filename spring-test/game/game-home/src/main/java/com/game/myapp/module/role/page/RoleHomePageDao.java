package com.game.myapp.module.role.page;

import java.util.concurrent.locks.ReentrantLock;

import com.game.db.entity.RoleInfoEntity;
import com.game.global.Globals;

public class RoleHomePageDao {

	public RoleInfoEntity insertRoleInfo(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleInfoEntity entity = new RoleInfoEntity();
			entity.setUid(uid);
			entity.setFavouriteFriend(0L);
			entity.setFavouriteHero(0);
			entity.setFavouritePet(0);
			entity.setPersonSign("");
			Globals.getEntityProxy().insert(entity);
			return entity;
		} finally {
			lock.unlock();
		}
	}

	public RoleInfoEntity getRoleInfoEntityByUid(long uid) {
		RoleInfoEntity result = Globals.getEntityProxy().get(RoleInfoEntity.class, uid);
		if (result == null) {
			result = this.insertRoleInfo(uid);
		}
		return result;
	}
}
