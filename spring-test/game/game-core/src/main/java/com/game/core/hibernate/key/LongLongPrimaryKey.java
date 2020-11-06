package com.game.core.hibernate.key;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class LongLongPrimaryKey implements Serializable {
	private static final long serialVersionUID = 322135284055603835L;

	/** 主键，角色Id */
	private long uid;
	/** xid */
	private long xid;

	public LongLongPrimaryKey() {
	}

	public LongLongPrimaryKey(long uid, long xid) {
		this.uid = uid;
		this.xid = xid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (xid ^ (xid >>> 32));
		result = prime * result + (int) (uid ^ (uid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LongLongPrimaryKey other = (LongLongPrimaryKey) obj;
		if (xid != other.xid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
}
