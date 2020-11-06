package com.game.core.server.handler;

import com.game.core.server.model.GameModel;
import com.game.core.server.model.ServerInfo;

public interface IStatusHandler {
	public void excute(ServerInfo serverInfo, GameModel gamemodel);
}
