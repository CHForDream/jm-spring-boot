package com.game.core.hibernate.key;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class LongIntPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1956981965639502981L;

	/** 主键，角色Id */
	private long uid;
	/** xid */
	private int xid;

	public LongIntPrimaryKey() {
	}

	public LongIntPrimaryKey(long uid, int xid) {
		this.uid = uid;
		this.xid = xid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xid;
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
		LongIntPrimaryKey other = (LongIntPrimaryKey) obj;
		if (xid != other.xid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
}
