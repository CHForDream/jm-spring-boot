package com.game.core.db.proxy;

import java.util.List;

import com.game.core.hibernate.orm.BaseEntity;

@SuppressWarnings("rawtypes")
public interface IDbProxy {
	<T extends BaseEntity> boolean insert(T entity);

	<T extends BaseEntity, ID> T get(Class<T> clazz, ID id);

	<T extends BaseEntity, ID> T getNotCache(Class<T> clazz, ID id);

	<T extends BaseEntity> boolean update(T entity);

	<T extends BaseEntity> boolean updateAsync(T entity);

	<T extends BaseEntity> boolean delete(T entity);

	<T extends BaseEntity> List<T> findAll(Class<T> clazz);

	<T extends BaseEntity> List<T> findAllNotCache(Class<T> clazz);

	/**
	 * 根据用户id此人所以数据
	 * 
	 * 
	 * @param <T>   数据库操作entity 的类
	 * @param uid   用户id
	 * @param clazz 数据库操作entity 的类
	 * @return 一个entity
	 */
	<T extends BaseEntity> List<T> findAllByUid(Class<T> clazz, long uid);

	<T extends BaseEntity> List<T> findAllByUidNotCache(Class<T> clazz, long uid);

//	<T extends BaseMemBean> List<T> findAllByParam(Class<T> clazz, final String[] paramNames, final Object[] values);
//
//	<T extends BaseMemBean> List<T> findAllByParam(Class<T> clazz, final String[] paramNames, final Object[] values, final int maxResult, final int start);

	void updatePlayerData(long uid);

	void updateAllData();

	void setNextProxy(IDbProxy proxy);

	IDbProxy getNextProxy();
}
