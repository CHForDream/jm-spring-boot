package com.game.myapp.module.friends;

import com.game.db.entity.LatelyfriendEntity;

public class LatelyInfo implements Comparable<LatelyInfo> {
	private LatelyfriendEntity db = null;
	private long timeMill = 0;

	public LatelyfriendEntity getDb() {
		return db;
	}

	public void setDb(LatelyfriendEntity db) {
		this.db = db;
	}

	public long getTimeMill() {
		return timeMill;
	}

	public void setTimeMill(long timeMill) {
		this.timeMill = timeMill;
	}

	@Override
	public int compareTo(LatelyInfo o) {
		return (int) (this.timeMill - o.getTimeMill());
	}
}
