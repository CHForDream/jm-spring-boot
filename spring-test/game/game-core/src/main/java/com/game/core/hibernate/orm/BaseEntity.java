package com.game.core.hibernate.orm;

import com.game.core.hibernate.IDbEntity;
import com.game.global.Globals;

public abstract class BaseEntity<T> implements IDbEntity<T> {
	protected long uid = 0L;

	protected boolean isInDb = false;

	protected int retryTimes = 0;

	/**
	 * insert操作同步执行
	 * update操作异步执行
	 * 
	 * @return <b>true 成功</b>
	 *         <p>
	 *         <b>false 失败</b>
	 */
	public boolean save() {
		if (isInDb) {
			return Globals.getEntityProxy().updateAsync(this);
		} else {
			return Globals.getEntityProxy().insert(this);
		}
	}

	/**
	 * insert和update操作都是同步执行
	 * 
	 * @return <b>true 成功</b>
	 *         <p>
	 *         <b>false 失败</b>
	 */
	public boolean saveSync() {
		if (isInDb) {
			return Globals.getEntityProxy().update(this);
		} else {
			return Globals.getEntityProxy().insert(this);
		}
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public boolean isInDb() {
		return isInDb;
	}

	public void setInDb(boolean isInDb) {
		this.isInDb = isInDb;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}
}
