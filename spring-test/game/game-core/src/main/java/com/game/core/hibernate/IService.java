package com.game.core.hibernate;

import java.util.List;

public interface IService {
	/**
	 * 根据<tt>id</tt>获取指定类型的实体
	 *
	 * @param <T>
	 * @param entityClass 实体的类型
	 * @param id          实体的id
	 * @return
	 */
	public <T extends IDbEntity<ID>, ID> T get(Class<T> entityClass, ID id);

	/**
	 * 保存一个实体
	 *
	 * @param entity
	 * @return
	 */
	public abstract <T extends IDbEntity<ID>, ID> ID save(T entity);

	/**
	 * 更新一个实体
	 *
	 * @param entity
	 */
	public abstract <T extends IDbEntity<ID>, ID> boolean update(T entity);

	/**
	 * 删除一个实体
	 *
	 * @param entity
	 */
	public abstract <T extends IDbEntity<ID>, ID> boolean delete(T entity);

	/**
	 * 删除一个实体
	 *
	 * @param entity
	 */
	public abstract <T extends IDbEntity<ID>, ID> boolean deleteById(Class<T> entityClass, ID id);

	/**
	 * 查询表中所有数据
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T extends IDbEntity<ID>, ID> List<T> findAll(Class<T> entityClass);

	/**
	 * 通过uid查询相关联的数据
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param uid
	 * @return
	 */
	public <T extends IDbEntity<ID>, ID> List<T> findByUid(Class<T> entityClass, Long uid);

	/**
	 * 检测服务是否正确初始化
	 */
	public void check();
}
