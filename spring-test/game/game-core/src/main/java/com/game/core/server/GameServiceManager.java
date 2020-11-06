package com.game.core.server;

import com.game.core.redis.RedisConnFactory;
import com.game.core.server.model.GameModel;
import com.game.core.server.model.ServerInfo;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;

import redis.clients.jedis.Jedis;

public class GameServiceManager extends Thread {
	private GameModel gameModel = new GameModel();
	private String updateTime = "";

	public GameServiceManager() {
		this.setName("GameServiceManager");
		gameModel.init();
	}

	public void run() {
		try {
			while (gameModel.isServerThread()) {
				loopOne();
				sleep(gameModel.getSleepTime());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void loopOne() {
		Jedis jedis = RedisConnFactory.getServerJedis();
		if (jedis == null)
			return;
		try {
			String value = jedis.get(gameModel.getServerKey());
			if (StringUtils.isEmpty(value)) {
				return;
			}

			ServerInfo serverInfo = JsonUtil.toObj(value, ServerInfo.class);
			EServerStatus server = EServerStatus.getServerStatus(serverInfo.getStatus());
			if (server == null)
				return;
			server.getHandler().excute(serverInfo, gameModel);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@SuppressWarnings("unused")
	private void loopOnce() {
		Jedis jedis = RedisConnFactory.getServerJedis();
		try {
			if (jedis != null) {
				String upTime = jedis.get(gameModel.getUpdateKey());
				if (upTime != null && upTime.equals(updateTime)) {

				} else {
					String value = jedis.get(gameModel.getServerKey());
					if (value != null && !value.isEmpty()) {
						ServerInfo status = JsonUtil.toObj(value, ServerInfo.class);
						EServerStatus server = EServerStatus.getServerStatus(status.getStatus());
						server.getHandler().excute(status, gameModel);
					} else {
						ServerInfo status = new ServerInfo();
						status.setStatus(EServerStatus.WAIT_START.getCode());
						value = JsonUtil.toJson(status);
						jedis.set(gameModel.getServerKey(), value);
					}
				}
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
