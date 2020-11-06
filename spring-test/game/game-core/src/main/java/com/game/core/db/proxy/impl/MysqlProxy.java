package com.game.core.db.proxy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.game.constants.Loggers;
import com.game.core.hibernate.EntityManagerBaseService;
import com.game.core.hibernate.orm.BaseEntity;
import com.google.common.collect.Lists;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Component
@Service("MysqlProxy")
public class MysqlProxy extends AbsProxy {

	@Autowired
	@Qualifier("EntityManagerBaseService")
	private EntityManagerBaseService entityManagerBaseService;

	@Override
	public <T extends BaseEntity> boolean insert(T entity) {
		try {
			entityManagerBaseService.save(entity);
			entity.setInDb(true);
			return true;
		} catch (Exception e) {
			Loggers.dbLogger.error("Insert entity error! entity = " + entity.getClass().getSimpleName() + ", id = " + entity.getId(), e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public <T extends BaseEntity, ID> T get(Class<T> clazz, ID id) {
		try {
			T result = (T) entityManagerBaseService.get(clazz, id);
			if (result != null) {
				result.setInDb(true);
			}
			return result;
		} catch (Exception e) {
			Loggers.dbLogger.error("Get entity error! entity = " + clazz.getSimpleName() + ", id = " + id, e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T extends BaseEntity, ID> T getNotCache(Class<T> clazz, ID id) {
		return get(clazz, id);
	}

	@Override
	public <T extends BaseEntity> boolean update(T entity) {
		try {
			entityManagerBaseService.update(entity);

			entity.setInDb(true);
			// 保存成功, 重置重试次数
			entity.setRetryTimes(0);
			return true;
		} catch (Exception e) {
			Loggers.dbLogger.error("Update entity error! entity = " + entity.getClass().getSimpleName() + ", id = " + entity.getId(), e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public <T extends BaseEntity> boolean updateAsync(T entity) {
		return update(entity);
	}

	@Override
	public <T extends BaseEntity> boolean delete(T entity) {
		try {
			return entityManagerBaseService.delete(entity);
		} catch (Exception e) {
			Loggers.dbLogger.error("Delete entity error! entity = " + entity.getClass().getSimpleName() + ", id = " + entity.getId(), e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> clazz) {
		try {
			List<T> list = entityManagerBaseService.findAll(clazz);
			if (list != null) {
				for (T entity : list) {
					entity.setInDb(true);
				}
			}
			return list;
		} catch (Exception e) {
			Loggers.dbLogger.error("Find all entity error! entity = " + clazz.getSimpleName(), e);
			e.printStackTrace();
		}

		return Lists.newArrayList();
	}

	@Override
	public <T extends BaseEntity> List<T> findAllNotCache(Class<T> clazz) {
		return findAll(clazz);
	}

	@Override
	public <T extends BaseEntity> List<T> findAllByUid(Class<T> clazz, long uid) {
		try {
			return entityManagerBaseService.findByUid(clazz, uid);
		} catch (Exception e) {
			Loggers.dbLogger.error("Find all entity by uid error! entity = " + clazz.getSimpleName() + ", uid = " + uid, e);
			e.printStackTrace();
		}
		return Lists.newArrayList();
	}

	@Override
	public <T extends BaseEntity> List<T> findAllByUidNotCache(Class<T> clazz, long uid) {
		return findAllByUid(clazz, uid);
	}
}
