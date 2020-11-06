package com.game.common.session;

public class SessionUserData {
	private long timeMill = System.currentTimeMillis();
	private long uid = 0;
	private long lastHeartBeatMill = 0;

	public long getLastHeartBeatMill() {
		return lastHeartBeatMill;
	}

	public void setLastHeartBeatMill(long lastHeartBeatMill) {
		this.lastHeartBeatMill = lastHeartBeatMill;
	}

	public long getTimeMill() {
		return timeMill;
	}

	public void setTimeMill(long timeMill) {
		this.timeMill = timeMill;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

}
