package com.game.core.db.proxy.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.game.constants.Loggers;
import com.game.core.db.proxy.IDbProxy;
import com.game.core.hibernate.orm.BaseEntity;
import com.game.global.Globals;
import com.google.common.collect.Lists;

@SuppressWarnings("rawtypes")
@Component
@Service("EntityProxy")
public class EntityProxy extends AbsProxy {
	private static IDbProxy ENTITY_PROXY = null;

	private EntityProxy() {
	}

	public static IDbProxy getInstance() {
		if (ENTITY_PROXY != null) {
			return ENTITY_PROXY;
		}

		ENTITY_PROXY = Globals.applicationContext.getBean("EntityProxy", IDbProxy.class);
		IDbProxy cacheProxy = Globals.applicationContext.getBean("CacheProxy", IDbProxy.class);
//		IGameproxy redisProxy = Globals.applicationContext.getBean("RedisProxy", IGameproxy.class);
		IDbProxy asyncProxy = Globals.applicationContext.getBean("AsyncProxy", IDbProxy.class);
		IDbProxy dbProxy = Globals.applicationContext.getBean("MysqlProxy", IDbProxy.class);

		ENTITY_PROXY.setNextProxy(cacheProxy);
		cacheProxy.setNextProxy(asyncProxy);
		asyncProxy.setNextProxy(dbProxy);

		return ENTITY_PROXY;
	}

	@Override
	public <T extends BaseEntity> boolean insert(T entity) {
		if (entity == null) {
			Loggers.dbLogger.warn("Insert entity is null!");
			return false;
		}
		if (entity.isInDb()) {
			Loggers.dbLogger.warn("Insert entity error! isInDb is true. clazz = " + entity.getClass().getSimpleName() + ", id = " + entity.getId());
			return false;
		}
//		Loggers.dbLogger.warn("Insert. clazz = " + entity.getClass().getSimpleName() + ", id = " + entity.getId() + ", hashcode = " + entity.hashCode());
		return getNextProxy().insert(entity);
	}

	@Override
	public <T extends BaseEntity, ID> T get(Class<T> clazz, ID id) {
		if (clazz == null || id == null) {
			Loggers.dbLogger.warn("Get class or id is null! clazz = " + clazz + ", id = " + id);
			return null;
		}

		T entity = getNextProxy().get(clazz, id);
//		if (entity != null) {
//			Loggers.dbLogger.warn("Get. clazz = " + entity.getClass().getSimpleName() + ", id = " + entity.getId() + ", hashcode = " + entity.hashCode());
//		}
		return entity;
	}

	@Override
	public <T extends BaseEntity> boolean update(T entity) {
		if (entity == null) {
			Loggers.dbLogger.warn("Update entity is null!");
			return false;
		}

//		Loggers.dbLogger.warn("Update. clazz = " + entity.getClass().getSimpleName() + ", id = " + entity.getId() + ", hashcode = " + entity.hashCode());
		return getNextProxy().update(entity);
	}

	@Override
	public <T extends BaseEntity> boolean updateAsync(T entity) {
		if (entity == null) {
			Loggers.dbLogger.warn("Async update  entity is null!");
			return false;
		}

//		Loggers.dbLogger.warn("UpdateAsync. clazz = " + entity.getClass().getSimpleName() + ", id = " + entity.getId() + ", hashcode = " + entity.hashCode());
		return getNextProxy().updateAsync(entity);
	}

	@Override
	public <T extends BaseEntity> boolean delete(T entity) {
		if (entity == null) {
			Loggers.dbLogger.warn("Delete entity is null!");
			return false;
		}
		return getNextProxy().delete(entity);
	}

	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> clazz) {
		if (clazz == null) {
			Loggers.dbLogger.warn("findAll class is null!");
			return Lists.newArrayList();
		}
		return getNextProxy().findAll(clazz);
	}

	@Override
	public <T extends BaseEntity> List<T> findAllNotCache(Class<T> clazz) {
		if (clazz == null) {
			Loggers.dbLogger.warn("findAllNotCache class is null!");
			return Lists.newArrayList();
		}
		return getNextProxy().findAllNotCache(clazz);
	}

	@Override
	public <T extends BaseEntity> List<T> findAllByUid(Class<T> clazz, long uid) {
		if (clazz == null) {
			Loggers.dbLogger.warn("findAllByUid class is null!");
			return Lists.newArrayList();
		}
		return getNextProxy().findAllByUid(clazz, uid);
	}

	@Override
	public <T extends BaseEntity> List<T> findAllByUidNotCache(Class<T> clazz, long uid) {
		if (clazz == null) {
			Loggers.dbLogger.warn("findAllByUidNotCache class is null!");
			return Lists.newArrayList();
		}
		return getNextProxy().findAllByUidNotCache(clazz, uid);
	}
}
