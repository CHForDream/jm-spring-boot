package com.game.core.server;

import com.game.constants.Loggers;
import com.game.global.Globals;

public class GameShutdownProcessor {
	public static void shutdown() {
		try {
			Globals.stopServer();

//			// 同步服务器信息
//			GameGlobals.serverListManager.syncServerInfo();

			// 存储数据(持久化数据 + 缓存数据)
			Globals.getRoleManager().onShutdown();

//			// 战斗服
//			if (Globals.getAppConfigBean().isBattleServer()) {
//				GameThreadPoolManager.getInstance().shutDown();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Loggers.serverLogger.info("Game server shutdown!");
		}
	}
}
