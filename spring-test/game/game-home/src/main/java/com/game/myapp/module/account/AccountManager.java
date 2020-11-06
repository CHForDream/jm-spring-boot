
package com.game.myapp.module.account;

import java.util.concurrent.locks.ReentrantLock;

import com.game.core.lock.LockManager;
import com.game.db.entity.AccountEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;

public class AccountManager {
	public long getUidByUserId(String pfId) {
		AccountEntity dbEntity = null;
		ReentrantLock serverLock = LockManager.getInstance().getServerLock();
		serverLock.lock();
		try {
			dbEntity = Globals.getEntityProxy().get(AccountEntity.class, pfId);
			if (dbEntity == null) {
				dbEntity = new AccountEntity();
				dbEntity.setId(pfId);
				dbEntity.setUid(GameGlobals.redisManager.getIncreaseUid());
				Globals.getEntityProxy().insert(dbEntity);
			}

			return dbEntity.getUid();
		} finally {
			serverLock.unlock();
		}
	}
}
