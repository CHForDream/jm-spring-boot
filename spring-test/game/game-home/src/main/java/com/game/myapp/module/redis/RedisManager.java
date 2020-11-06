package com.game.myapp.module.redis;

import com.game.constants.Loggers;
import com.game.constants.RedisConstants;
import com.game.core.redis.RedisConnFactory;
import com.game.myapp.GameGlobals;

import redis.clients.jedis.Jedis;

public class RedisManager {
	public void init() {
		// 初始化Redis中的自增uid
		long maxUid = GameGlobals.daoService.getRoleDao().findMaxUid();
		long increaseUid = increase(RedisConstants.GAME_KEY_DB_UID);
		Loggers.serverLogger.info("max uid in mysql: " + maxUid);
		Loggers.serverLogger.info("uid in redis: " + increaseUid);
		if (maxUid >= increaseUid) {
			long increaseUid2 = increase(RedisConstants.GAME_KEY_DB_UID, Math.max(maxUid - increaseUid, 1));
			Loggers.serverLogger.info("update uid in redis: " + increaseUid2);
		}

		// 初始化Redis中的自增showid
		long maxShowId = GameGlobals.daoService.getShowIdDao().findMaxShowId();
		long increaseShowId = increase(RedisConstants.GAME_KEY_DB_SHOWID);
		Loggers.serverLogger.info("max showId in mysql: " + maxShowId);
		Loggers.serverLogger.info("showId in redis: " + increaseShowId);
		if (maxShowId >= increaseShowId) {
			long increaseShowId2 = increase(RedisConstants.GAME_KEY_DB_SHOWID, Math.max(maxShowId - increaseShowId, 1));
			Loggers.serverLogger.info("update showId in redis: " + increaseShowId2);
		}
	}

	public long getIncreaseUid() {
		return increase(RedisConstants.GAME_KEY_DB_UID);
	}

	public long getIncreaseShowId() {
		return increase(RedisConstants.GAME_KEY_DB_SHOWID);
	}

	private long increase(String key) {
		Jedis jedis = null;
		try {
			jedis = RedisConnFactory.getGameJedis();
			return jedis.incr(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	private long increase(String key, long increment) {
		Jedis jedis = null;
		try {
			jedis = RedisConnFactory.getGameJedis();
			return jedis.incrBy(key, increment);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
