package com.game.myapp.module.role;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.core.hibernate.key.LongLongPrimaryKey;
import com.game.db.entity.UserNameUidEntity;
import com.game.global.Globals;
import com.game.utils.StringUtils;

public class UserNameCheckManager {
	public boolean isNameExist(String name) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}

		int dbIndex = name.hashCode();
		// dbIndex作为第一索引, 放在uid位置, 提供模糊查询功能
		// 玩家的uid作为第二索引, 放在id的位置上
		List<UserNameUidEntity> names = Globals.getEntityProxy().findAllByUid(UserNameUidEntity.class, dbIndex);
		if (names.isEmpty()) {
			return true;
		}

		for (UserNameUidEntity db : names) {
			if (db.getName().equals(name)) {
				return false;
			}
		}
		return true;
	}

	public void update(long uid, String oldName, String name) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			int oldDbIndex = oldName.hashCode();
			UserNameUidEntity entity = Globals.getEntityProxy().get(UserNameUidEntity.class, new LongLongPrimaryKey(oldDbIndex, uid));
			if (entity != null) {
				Globals.getEntityProxy().delete(entity);
			}

			int dbIndex = name.hashCode();
			UserNameUidEntity newEntity = new UserNameUidEntity();
			newEntity.setHashcodeId(dbIndex);// dbIndex作为第一索引, 提供模糊查询功能
			newEntity.setUid(uid);// uid作为第二索引, 放在xid的位置上
			newEntity.setName(name);
			Globals.getEntityProxy().insert(newEntity);
		} finally {
			lock.unlock();
		}
	}
}
