package com.game.core.server.model;

import com.game.core.redis.RedisConnFactory;
import com.game.global.Globals;

public class GameModel {
	private String serverKey = "";
	private String updateKey = "";
	private int sleepTime = 10000;

	private boolean serverThread = true;

	public void init() {
		serverKey = String.format(RedisConnFactory.SERVER_FOLDER_SERVER_LOGICAL, Globals.getAppConfigBean().getGssid(),
				Globals.getAppConfigBean().getSid());
		updateKey = String.format(RedisConnFactory.SERVER_FOLDER_SERVER_UPDATE, Globals.getAppConfigBean().getGssid());
	}

	public String getServerKey() {
		return serverKey;
	}

	public void setServerKey(String serverKey) {
		this.serverKey = serverKey;
	}

	public String getUpdateKey() {
		return updateKey;
	}

	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public boolean isServerThread() {
		return serverThread;
	}

	public void setServerThread(boolean serverThread) {
		this.serverThread = serverThread;
	}
}
