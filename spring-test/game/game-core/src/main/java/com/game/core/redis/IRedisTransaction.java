package com.game.core.redis;

import redis.clients.jedis.Transaction;

public interface IRedisTransaction {

	/**
	 * 需要监测变化的key数组
	 * 
	 * @return
	 */
	String[] getKeyArray();

	/**
	 * 事务失败后是否重试
	 * 
	 * @return
	 */
	boolean isRetry();

	/**
	 * 是否需要取消事务
	 * 
	 * @return
	 */
	boolean isDiscard();

	/**
	 * 添加事务要执行的操作
	 * 
	 * @param transaction
	 */
	void excute(Transaction transaction);

	/**
	 * 获取事务操作的描述（日志打印）
	 * 
	 */
	String getDescription();
}
