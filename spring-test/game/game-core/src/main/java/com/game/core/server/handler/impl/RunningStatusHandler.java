package com.game.core.server.handler.impl;

import com.game.core.redis.RedisConnFactory;
import com.game.core.server.handler.IStatusHandler;
import com.game.core.server.model.GameModel;
import com.game.core.server.model.ServerInfo;
import com.game.global.Globals;
import com.game.utils.JsonUtil;

import redis.clients.jedis.Jedis;

public class RunningStatusHandler implements IStatusHandler {

	@Override
	public void excute(ServerInfo serverInfo, GameModel gamemodel) {
		if (!Globals.isSreverStarted()) {
			return;
		}

		serverInfo.setHeartbeat((int) (System.currentTimeMillis() / 1000)); // 写入心跳时间 
		// 将数据写入redis
		writeData(serverInfo, gamemodel);
	}

	private void writeData(ServerInfo serverInfo, GameModel gamemodel) {
		Jedis jedis = RedisConnFactory.getServerJedis();
		if (jedis == null) {
			return;
		}
		try {
			jedis.set(gamemodel.getServerKey(), JsonUtil.toJson(serverInfo));
//		jedis.set(gamemodel.getUpdateKey(), String.valueOf(System.currentTimeMillis()));
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
