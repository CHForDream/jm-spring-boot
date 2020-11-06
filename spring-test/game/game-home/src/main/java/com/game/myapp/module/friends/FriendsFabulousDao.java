package com.game.myapp.module.friends;

import java.util.concurrent.locks.ReentrantLock;

import com.game.db.entity.FriendsFabulousEntity;
import com.game.global.Globals;

public class FriendsFabulousDao {

	public FriendsFabulousEntity getFriendsFabulousByUid(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			FriendsFabulousEntity result = Globals.getEntityProxy().get(FriendsFabulousEntity.class, uid);
			if (result == null) {
				result = new FriendsFabulousEntity();
				result.setUid(uid);
				result.setFabulous(0);
				result.setUids("");
				result.setFabulousTime(System.currentTimeMillis());
				Globals.getEntityProxy().insert(result);
			}
			return result;
		} finally {
			lock.unlock();
		}
	}
}
