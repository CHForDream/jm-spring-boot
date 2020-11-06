package com.game.core.server.handler.impl;

import org.apache.log4j.Logger;

import com.game.core.server.handler.IStatusHandler;
import com.game.core.server.model.GameModel;
import com.game.core.server.model.ServerInfo;

public class StopStatusHandler implements IStatusHandler {
	// 日志
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void excute(ServerInfo serverInfo, GameModel gamemodel) {
		logger.info("server status is stopped...");
		System.exit(0);
	}
}
