package com.game.core.server.handler.impl;

import org.apache.log4j.Logger;

import com.game.core.redis.RedisConnFactory;
import com.game.core.server.EServerStatus;
import com.game.core.server.handler.IStatusHandler;
import com.game.core.server.model.GameModel;
import com.game.core.server.model.ServerInfo;
import com.game.utils.JsonUtil;

import redis.clients.jedis.Jedis;

public class WaitCloseStatusHandler implements IStatusHandler {
	// 日志
	private Logger logger = Logger.getLogger(this.getClass());
	private int num = 0;

	@Override
	public void excute(ServerInfo serverInfo, GameModel gamemodel) {
		if (num * gamemodel.getSleepTime() > 30000) {
			kickAllPlayer();
			serverInfo.setStatus(EServerStatus.SHUT_DOWN.getCode());
			writeData(serverInfo, gamemodel);
		}
		num++;
	}

	public void kickAllPlayer() {
		logger.info("server is waiting for close kick all player");
	}

	private void writeData(ServerInfo serverInfo, GameModel gamemodel) {
		Jedis jedis = RedisConnFactory.getServerJedis();
		if (jedis == null) {
			return;
		}
		try {
			jedis.set(gamemodel.getServerKey(), JsonUtil.toJson(serverInfo));
//			jedis.set(gamemodel.getUpdateKey(), String.valueOf(System.currentTimeMillis()));
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
