package com.game.core.hibernate.key;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class IntIntPrimaryKey implements Serializable {
	private static final long serialVersionUID = -8644759233637082398L;

	/** 主键Id */
	private int uid;
	/** xid */
	private int xid;

	public IntIntPrimaryKey() {
	}

	public IntIntPrimaryKey(int uid, int xid) {
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
		IntIntPrimaryKey other = (IntIntPrimaryKey) obj;
		if (xid != other.xid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
}
