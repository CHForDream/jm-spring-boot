package com.game.myapp.module.friends;

import com.game.db.entity.FriendreqEntity;

public class FriendReqInfo implements Comparable<FriendReqInfo> {
	private FriendreqEntity db = null;
	private long timeMill = 0;

	public FriendreqEntity getDb() {
		return db;
	}

	public void setDb(FriendreqEntity db) {
		this.db = db;
	}

	public long getTimeMill() {
		return timeMill;
	}

	public void setTimeMill(long timeMill) {
		this.timeMill = timeMill;
	}

	@Override
	public int compareTo(FriendReqInfo o) {
		return (int) (this.timeMill - o.getTimeMill());
	}

}
