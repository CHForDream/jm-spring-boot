package com.game.core.db.proxy.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.game.constants.Loggers;
import com.game.core.cache.CacheType;
import com.game.core.cache.EhcacheUtil;
import com.game.core.hibernate.key.EntityUtils;
import com.game.core.hibernate.orm.BaseEntity;
import com.game.core.lock.LockManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
@Service("CacheProxy")
public class CacheProxy extends AbsProxy {

	@Override
	public <T extends BaseEntity> boolean insert(T entity) {
		BaseEntity cache = getFromCache(entity.getClass(), entity.getId());
		if (cache != null) {
			Loggers.dbLogger.error("Duplicate data insert! class = " + entity.getClass().getSimpleName() + ", dbId = " + entity.getId());
			return false;
		}

		// 存库
		boolean result = getNextProxy().insert(entity);
		if (!result) {
			return result;
		}

		// 加载了uid关联的数据
		syncMultiMappingEntityToCache(entity);

		// 更新缓存
		// 若加载了uid关联的数据, 用当前对象替换缓存的数据
		putIntoCache(entity);

		return result;
	}

	@Override
	public <T extends BaseEntity, ID> T get(Class<T> clazz, ID id) {
		T entity = getFromCache(clazz, id);
		if (entity != null) {
			return entity;
		}

		// 若缓存中没有, 查找数据库, 并缓存
		entity = getNextProxy().get(clazz, id);
		if (entity == null) {
			return null;
		}

		// 加载了uid关联的数据
		syncMultiMappingEntityToCache(entity);

		// 更新缓存
		// 若加载了uid关联的数据, 用当前对象替换缓存的数据
		putIntoCache(entity);

		return entity;
	}

	@Override
	public <T extends BaseEntity> boolean update(T entity) {
		boolean result = getNextProxy().update(entity);
		if (!result) {
			return result;
		}

		// 更新的对象默认就在缓存里
		// 只有缓存中不存在时, 需要更新缓存
		Object cache = getFromCache(entity.getClass(), entity.getId());
		if (cache == null) {
			// 加载了uid关联的数据
			syncMultiMappingEntityToCache(entity);

			putIntoCache(entity);
		}

		return result;
	}

	@Override
	public <T extends BaseEntity> boolean updateAsync(T entity) {
		boolean result = getNextProxy().updateAsync(entity);
		if (!result) {
			return result;
		}

		// 更新的对象默认就在缓存里
		// 只有缓存中不存在时, 需要更新缓存
		Object cache = getFromCache(entity.getClass(), entity.getId());
		if (cache == null) {
			// 加载了uid关联的数据
			syncMultiMappingEntityToCache(entity);

			putIntoCache(entity);
		}

		return result;
	}

	@Override
	public <T extends BaseEntity> boolean delete(T entity) {
		boolean result = getNextProxy().delete(entity);

		// 删除缓存
		deleteFromCache(entity.getClass(), entity.getId());

		return result;
	}

	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> clazz) {
		List<T> list = getNextProxy().findAll(clazz);
		for (T entity : list) {
			putIntoCache(entity);
		}
		return list;
	}

	@Override
	public <T extends BaseEntity> List<T> findAllByUid(Class<T> clazz, long uid) {
		List<T> dbList = getListFromCache(clazz, uid);
		if (dbList != null) {
			return dbList;
		}

		List<T> list = getNextProxy().findAllByUid(clazz, uid);
		for (T entity : list) {
			putIntoCache(entity);
		}
		return list;
	}

	private <T extends BaseEntity> void putIntoCache(T entity) {
		String cacheName = EhcacheUtil.getCacheType(entity.getClass());

		CacheType annotation = entity.getClass().getAnnotation(CacheType.class);
		int cacheType = annotation == null ? CacheType.COMMON : annotation.type();
		switch (cacheType) {
		case CacheType.ROLE_MULTI_MAPPING: {
			// 使用Entity的className和uid生成key, 将对象缓存到ConcurrentMap中
			try {
				String key = EntityUtils.getEntityUidKey(entity.getClass(), entity.getId());
				Map<Object, T> cacheMap = (Map<Object, T>) EhcacheUtil.getInstance().get(cacheName, key);
				if (cacheMap == null) {
					cacheMap = Maps.newConcurrentMap();
					EhcacheUtil.getInstance().put(cacheName, key, cacheMap);
				}
				cacheMap.put(entity.getId(), entity);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				Loggers.cacheLogger.error("Error entity key! class = " + entity.getClass().getSimpleName() + ", id = " + entity.getId());
			}
			break;
		}
		case CacheType.COMMON: {
			// 使用Entity的主键作为key, 直接缓存数据对象
			try {
				String key = EntityUtils.getEntityUidKey(entity.getClass(), entity.getId());
				EhcacheUtil.getInstance().put(cacheName, key, entity);
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
	}

	private <T extends BaseEntity, ID> T getFromCache(Class<T> clazz, ID id) {
		String cacheName = EhcacheUtil.getCacheType(clazz);

		CacheType annotation = clazz.getAnnotation(CacheType.class);
		int cacheType = annotation == null ? CacheType.COMMON : annotation.type();
		switch (cacheType) {
		case CacheType.ROLE_MULTI_MAPPING: {
			// 则使用的Entity的class name和uid生成key, 将对象缓存到List中
			try {
				String key = EntityUtils.getEntityUidKey(clazz, id);
				Map<Serializable, T> cacheMap = (Map<Serializable, T>) EhcacheUtil.getInstance().get(cacheName, key);
				if (cacheMap != null) {
					return cacheMap.get(id);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				Loggers.cacheLogger.error("Error entity key! class = " + clazz.getSimpleName() + ", id = " + id);
			}
			break;
		}
		case CacheType.COMMON: {
			// 则默认使用的Entity的主键作为key, 直接缓存数据对象
			try {
				String key = EntityUtils.getEntityUidKey(clazz, id);
				Object object = EhcacheUtil.getInstance().get(cacheName, key);
				if (object != null) {
					return (T) object;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				Loggers.cacheLogger.error("Error entity key! class = " + clazz.getSimpleName() + ", id = " + id);
			}
			break;
		}
		case CacheType.NO_CACHE:
		default:
			break;
		}
		return null;
	}

	private <T extends BaseEntity> List<T> getListFromCache(Class<T> clazz, long uid) {
		String cacheName = EhcacheUtil.getCacheType(clazz);

		CacheType annotation = clazz.getAnnotation(CacheType.class);
		int cacheType = annotation == null ? CacheType.COMMON : annotation.type();
		switch (cacheType) {
		case CacheType.ROLE_MULTI_MAPPING: {
			// 使用Entity的class name和uid生成key, 将对象缓存到List中
			try {
				String key = EntityUtils.getEntityUidKey(clazz, uid);
				Map<Serializable, T> cacheMap = (Map<Serializable, T>) EhcacheUtil.getInstance().get(cacheName, key);
				if (cacheMap != null) {
					List<T> list = Lists.newArrayList();
					list.addAll(cacheMap.values());
					return list;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				Loggers.cacheLogger.error("Error entity key! class = " + clazz.getSimpleName() + ", id = " + uid);
			}
			break;
		}
		case CacheType.COMMON: {
			// 使用的Entity的主键作为key, 直接缓存数据对象
			try {
				String key = EntityUtils.getEntityUidKey(clazz, uid);
				Object object = EhcacheUtil.getInstance().get(cacheName, key);
				if (object != null) {
					List<T> list = Lists.newArrayList();
					list.add((T) object);
					return list;
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
		return null;
	}

	private <T extends BaseEntity, ID> void deleteFromCache(Class<T> clazz, ID id) {
		String cacheName = EhcacheUtil.getCacheType(clazz);

		CacheType annotation = clazz.getAnnotation(CacheType.class);
		int cacheType = annotation == null ? CacheType.COMMON : annotation.type();
		switch (cacheType) {
		case CacheType.ROLE_MULTI_MAPPING: {
			try {
				String key = EntityUtils.getEntityUidKey(clazz, id);
				Map<Serializable, T> cacheMap = (Map<Serializable, T>) EhcacheUtil.getInstance().get(cacheName, key);
				if (cacheMap != null) {
					cacheMap.remove(id);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				Loggers.cacheLogger.error("Error entity key! class = " + clazz.getSimpleName() + ", id = " + id);
			}
			break;
		}
		case CacheType.COMMON: {
			try {
				String key = EntityUtils.getEntityUidKey(clazz, id);
				EhcacheUtil.getInstance().remove(cacheName, key);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				Loggers.cacheLogger.error("Error entity key! class = " + clazz.getSimpleName() + ", id = " + id);
			}
			break;
		}
		case CacheType.NO_CACHE:
		default:
			break;
		}
	}

	private <T extends BaseEntity> void syncMultiMappingEntityToCache(T entity) {
		CacheType annotation = entity.getClass().getAnnotation(CacheType.class);
		if (annotation == null || annotation.type() != CacheType.ROLE_MULTI_MAPPING) {
			return;
		}

		// 缓存已存在, 不再查询数据
		List<T> dbList = (List<T>) getListFromCache(entity.getClass(), entity.getUid());
		if (dbList != null) {
			return;
		}

		// 当entity是uid集合数据, 查询uid关联的所有数据, 并缓存下来
		ReentrantLock lock = LockManager.getInstance().getLock(entity.getUid());
		lock.lock();
		try {
			dbList = (List<T>) getListFromCache(entity.getClass(), entity.getUid());
			if (dbList == null) {
				findAllByUid(entity.getClass(), entity.getUid());
			}
		} finally {
			lock.unlock();
		}
	}
}
