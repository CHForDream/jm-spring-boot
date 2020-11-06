package com.game.core.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestRedisTransaction implements IRedisTransaction {
	private String key;

	public TestRedisTransaction(String key) {
		this.key = key;
	}

	@Override
	public String[] getKeyArray() {
		return new String[] { key };
	}

	@Override
	public boolean isRetry() {
		return true;
	}

	@Override
	public boolean isDiscard() {
		Jedis jedis = RedisConnFactory.getGameJedis();
		try {
			String value = jedis.get(key);
			if (value == null) {
				// 没有值, 直接取消
				return true;
			}

			// TODO 获取服务器编号
			return !"1".equals(value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public void excute(Transaction transaction) {
		// 写要执行的指令, 不需要执行exec
		transaction.del(key);
	}

	@Override
	public String getDescription() {
		return "Test Transaction";
	}
}
