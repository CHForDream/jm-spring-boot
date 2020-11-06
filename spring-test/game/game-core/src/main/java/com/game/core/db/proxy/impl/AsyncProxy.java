package com.game.core.db.proxy.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.game.constants.Loggers;
import com.game.core.cache.CacheType;
import com.game.core.db.EntityClassManager;
import com.game.core.hibernate.key.EntityUtils;
import com.game.core.hibernate.orm.BaseEntity;
import com.game.global.Globals;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
@Component
@Service("AsyncProxy")
public class AsyncProxy extends AbsProxy {
	// 操作失败重试次数
	private static final int RETRY_MAX_TIMES = 5;
	// 操作失败重试间隔时间
	private static final int RETRY_INTERVAL_TIME = 1000;

	/** 保存需要被更新的, Key:实体的ID,Value:将要被更新的实体 */
	private Map<String, BaseEntity> updateEntityMap = Maps.newConcurrentMap();
	/** 保存需要被更新的, Key:实体的ID,Value:将要被更新的实体列表. 这里存放玩家一对多的数据存储 */
	private Map<String, Map<Object, BaseEntity>> updateEntityListMap = Maps.newConcurrentMap();

	private List<Class<?>> tableClassList = Lists.newArrayList();

	// 异步处理重入锁
	private ReentrantLock asyncProcessLock = new ReentrantLock();

	public AsyncProxy() {
		tableClassList.addAll(EntityClassManager.getEntityClassList());
	}

	/**
	 * 5分钟执行一次更新存储
	 */
	@Scheduled(initialDelay = 300000, fixedRate = 300000)
	public void doScheduleUpdate() {
		List<BaseEntity> failedEntityList = Lists.newArrayList();

		// 玩家一对一的数据, 或是其他数据
		Iterator<String> iterator = updateEntityMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			BaseEntity entity = updateEntityMap.remove(key);
			if (entity == null) {
				continue;
			}

			if (!getNextProxy().update(entity)) {
				entity.setRetryTimes(entity.getRetryTimes() + 1);
				if (entity.getRetryTimes() < RETRY_MAX_TIMES) {
					failedEntityList.add(entity);
				} else {
					Loggers.dbLogger.error("doScheduleUpdate failed! class = " + entity.getClass().getSimpleName() + ", key = " + key);
				}
			}
		}

		// 玩家一对多映射数据存储
		Iterator<String> iterator2 = updateEntityListMap.keySet().iterator();
		while (iterator2.hasNext()) {
			String key = iterator2.next();
			asyncProcessLock.lock();
			Map<Object, BaseEntity> entityMap = null;
			try {
				entityMap = updateEntityListMap.remove(key);
			} finally {
				asyncProcessLock.unlock();
			}

			if (entityMap == null) {
				continue;
			}

			for (BaseEntity entity : entityMap.values()) {
				if (!getNextProxy().update(entity)) {
					entity.setRetryTimes(entity.getRetryTimes() + 1);
					if (entity.getRetryTimes() < RETRY_MAX_TIMES) {
						failedEntityList.add(entity);
					} else {
						Loggers.dbLogger.error("doScheduleUpdate failed2! class = " + entity.getClass().getSimpleName() + ", key = " + key);
					}
				}
			}
		}

		// 更新失败的数据, 丢回队列中, 下一次再更新
		for (BaseEntity entity : failedEntityList) {
			updateAsync(entity);
		}
	}

	@Override
	public <T extends BaseEntity> boolean insert(T entity) {
		boolean result = getNextProxy().insert(entity);
		if (!result) {
			List<BaseEntity> list = Lists.newArrayList();
			list.add(entity);
			result = retryOperatedList(OperationType.INSERT, list);
		}
		return result;
	}

	@Override
	public <T extends BaseEntity> boolean update(T entity) {
		// 同步更新
		if (!getNextProxy().updateAsync(entity)) {
			// 失败重试
			List<BaseEntity> failedEntityList = Lists.newArrayList();
			failedEntityList.add(entity);
			return retryOperatedList(OperationType.UPDATE, failedEntityList);
		}
		return true;
	}

	@Override
	public <T extends BaseEntity> boolean updateAsync(T entity) {
		// update execute asynchronize
		CacheType annotation = entity.getClass().getAnnotation(CacheType.class);
		int cacheType = annotation == null ? CacheType.COMMON : annotation.type();
		switch (cacheType) {
		case CacheType.ROLE_MULTI_MAPPING: {
			asyncProcessLock.lock();
			try {
				// 玩家1对多映射的数据, 使用uid做key, 
				String key = EntityUtils.getEntityUidKey(entity.getClass(), entity.getId());

				Map<Object, BaseEntity> entityMap = updateEntityListMap.get(key);
				if (entityMap == null) {
					entityMap = updateEntityListMap.putIfAbsent(key, Maps.newConcurrentMap());
					if (entityMap == null) {
						entityMap = updateEntityListMap.get(key);
					}
				}
				entityMap.put(entity.getId(), entity);
				return true;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				Loggers.cacheLogger.error("Error entity key! class = " + entity.getClass().getSimpleName() + ", id = " + entity.getId());
			} finally {
				asyncProcessLock.unlock();
			}
			break;
		}
		case CacheType.COMMON: {
			try {
				String key = EntityUtils.getEntityDbStringKey(entity.getClass(), entity.getId());
				updateEntityMap.put(key, entity);
				return true;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				Loggers.cacheLogger.error("Error entity key! class = " + entity.getClass().getSimpleName() + ", id = " + entity.getId());
			}
			break;
		}
		case CacheType.NO_CACHE:
		default:
			break;
		}
		return false;
	}

	@Override
	public <T extends BaseEntity> boolean delete(T entity) {
		boolean result = getNextProxy().delete(entity);
		if (!result) {
			List<BaseEntity> list = Lists.newArrayList();
			list.add(entity);
			result = retryOperatedList(OperationType.DELETE, list);
		}

		return result;
	}

	@Override
	public void updatePlayerData(long uid) {
		Loggers.dbLogger.info("updatePlayerData. uid = " + uid);
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			for (Class clazz : tableClassList) {
				List<BaseEntity> failedEntityList = Lists.newArrayList();
				CacheType annotation = (CacheType) clazz.getAnnotation(CacheType.class);
//				Loggers.dbLogger.info("clazz: " + clazz.getSimpleName() + ", annotation: " + annotation);
				int cacheType = annotation == null ? CacheType.COMMON : annotation.type();
				switch (cacheType) {
				case CacheType.ROLE_MULTI_MAPPING: {
					// 玩家一对多的数据
					try {
						String key = EntityUtils.getEntityUidKey(clazz, uid);
						asyncProcessLock.lock();
						Map<Object, BaseEntity> entityMap = null;
						try {
							entityMap = updateEntityListMap.remove(key);
						} finally {
							asyncProcessLock.unlock();
						}

						if (entityMap == null || entityMap.isEmpty()) {
							continue;
						}

						for (BaseEntity entity : entityMap.values()) {
							if (!getNextProxy().updateAsync(entity)) {
								Loggers.dbLogger.error("updatePlayerData failed2! class = " + entity.getClass().getSimpleName() + ", key = " + key);
								// save failed
								failedEntityList.add(entity);
							} else {
								Loggers.dbLogger.error("updatePlayerData successfully2! class = " + entity.getClass().getSimpleName() + ", key = " + key);
							}
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						Loggers.cacheLogger.error("Error entity key! class = " + clazz.getSimpleName() + ", id = " + uid);
					}
					break;
				}
				case CacheType.COMMON: {
					// 玩家一对一的数据, 或是其他数据
					try {
						String key = EntityUtils.getEntityDbStringKey(clazz, uid);
						BaseEntity entity = updateEntityMap.remove(key);
						if (entity == null) {
							continue;
						}

						if (!getNextProxy().updateAsync(entity)) {
							Loggers.dbLogger.error("updatePlayerData failed! class = " + entity.getClass().getSimpleName() + ", key = " + uid);
							// save failed
							failedEntityList.add(entity);
						} else {
//							Loggers.dbLogger.info("updatePlayerData successfully! class = " + entity.getClass().getSimpleName() + ", key = " + key);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						Loggers.cacheLogger.error("Error entity key! class = " + clazz.getSimpleName() + ", id = " + uid);
					}
					break;
				}
				case CacheType.NO_CACHE:
				default:
					break;
				}

				if (!failedEntityList.isEmpty()) {
					retryOperatedList(OperationType.UPDATE, failedEntityList);
				}
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void updateAllData() {
		Loggers.dbLogger.info("updateAllData...");
		doScheduleUpdate();
	}

	private boolean retryOperatedList(OperationType operationType, List<BaseEntity> entityList) {
		int retryTimes = 0;
		while (!entityList.isEmpty()) {
			if (retryTimes >= RETRY_MAX_TIMES) {
				for (BaseEntity entity : entityList) {
					Loggers.dbLogger.error("Retry operated entity failed! operationType = " + operationType + ", class = " + entity.getClass().getSimpleName()
							+ ", key = " + entity.getId());
				}
				return false;
			}

			retryTimes++;

			Iterator<BaseEntity> iterator = entityList.iterator();
			entityList = Lists.newArrayList();
			while (iterator.hasNext()) {
				boolean isFailed = false;
				BaseEntity entity = iterator.next();
				switch (operationType) {
				case INSERT:
					if (!getNextProxy().insert(entity)) {
//						// 失败再次加入重试列表
//						entityList.add(entity);
						isFailed = true;
					}
					break;
				case UPDATE:
					if (!getNextProxy().updateAsync(entity)) {
						// 失败再次加入重试列表
//						entityList.add(entity);
						isFailed = true;
					}
					break;
				case DELETE:
					if (!getNextProxy().delete(entity)) {
//						// 失败再次加入重试列表
//						entityList.add(entity);
						isFailed = true;
					}
					break;
				default:
					break;
				}

				if (isFailed) {
					Loggers.dbLogger.error("Retry operated entity failed! operationType = " + operationType + ", class = " + entity.getClass().getSimpleName()
							+ ", key = " + entity.getId());
				}
			}

//			if (!entityList.isEmpty()) {
//				try {
//					Thread.currentThread().wait(RETRY_INTERVAL_TIME);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}

		return true;
	}

	public enum OperationType {
		INSERT,
		UPDATE,
		DELETE,

		;
	}
}
