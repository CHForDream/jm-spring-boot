package com.game.myapp.module.friends;

import com.game.db.entity.FriendsEntity;

public class FriendInfo implements Comparable<FriendInfo> {
	private FriendsEntity db = null;
	private long timeMill = 0;

	public FriendsEntity getDb() {
		return db;
	}

	public void setDb(FriendsEntity db) {
		this.db = db;
	}

	public long getTimeMill() {
		return timeMill;
	}

	public void setTimeMill(long timeMill) {
		this.timeMill = timeMill;
	}

	@Override
	public int compareTo(FriendInfo o) {
		return (int) (this.timeMill - o.getTimeMill());
	}
}
