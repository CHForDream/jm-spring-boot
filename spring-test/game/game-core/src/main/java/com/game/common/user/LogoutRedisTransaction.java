package com.game.common.user;

import com.game.core.redis.IRedisTransaction;
import com.game.core.redis.RedisConnFactory;
import com.game.global.Globals;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class LogoutRedisTransaction implements IRedisTransaction {
	private String key;

	public LogoutRedisTransaction(String key) {
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
			String sidStr = jedis.get(key);
			if (sidStr == null) {
				// 没有值, 直接取消
				return true;
			}

			int sid = Integer.parseInt(sidStr);
			return sid != Globals.getAppConfigBean().getSid();
		} catch (NumberFormatException e) {
			// 数据异常, 不取消, 直接删除
			return false;
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
		return "Delete redis key!";
	}
}
