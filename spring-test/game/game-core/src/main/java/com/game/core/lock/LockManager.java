package com.game.core.lock;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class LockManager {
	private static final int LOCK_INIT_COUNT = 20;
	private static volatile LockManager INSTANCE = null;

	/** uid映射locker Map<uid, ReentrantLock> */
	private Map<Long, ReentrantLock> lockMap = Maps.newConcurrentMap();
	/** ReentrantLock对象池 */
	private List<ReentrantLock> lockPool = Lists.newCopyOnWriteArrayList();

	/** 整个服务器的逻辑锁 */
	private ReentrantLock serverLock = new ReentrantLock();

	/** LockManager的逻辑锁 */
	private ReentrantLock myLock = new ReentrantLock();

	private LockManager() {
	}

	public static LockManager getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}

		synchronized (LockManager.class) {
			if (INSTANCE != null) {
				return INSTANCE;
			}

			INSTANCE = new LockManager();
		}
		return INSTANCE;
	}

	public ReentrantLock getLock(long uid) {
		if (lockMap.containsKey(uid)) {
			return lockMap.get(uid);
		}

		myLock.lock();
		try {
			// 二次确认
			if (lockMap.containsKey(uid)) {
				return lockMap.get(uid);
			}

			ReentrantLock lock = null;
			if (lockPool.isEmpty()) {
				// 初始化(LOCK_INIT_COUNT - 1)个锁, 另一个在下面new
				for (int i = 0; i < LOCK_INIT_COUNT - 1; i++) {
					lockPool.add(new ReentrantLock());
				}
				lock = new ReentrantLock();
			} else {
				lock = lockPool.remove(0);
			}

			lockMap.put(uid, lock);
			return lock;
		} finally {
			myLock.unlock();
		}
	}

	public void releaseLock(long uid) {
		myLock.lock();
		try {
			ReentrantLock removedLock = lockMap.remove(uid);
			if (removedLock != null) {
				lockPool.add(removedLock);
			}
		} finally {
			myLock.unlock();
		}
	}

	public ReentrantLock getServerLock() {
		return serverLock;
	}

	public void setServerLock(ReentrantLock serverLock) {
		this.serverLock = serverLock;
	}
}
