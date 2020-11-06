package com.game.core.server.handler.impl;

import org.apache.log4j.Logger;

import com.game.core.server.handler.IStatusHandler;
import com.game.core.server.model.GameModel;
import com.game.core.server.model.ServerInfo;

public class ShutDownStatusHandler implements IStatusHandler {
	// 日志
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void excute(ServerInfo serverInfo, GameModel gamemodel) {
		System.exit(0);
		logger.info("serve status is -1");
	}
}
