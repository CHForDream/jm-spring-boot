package com.game.core.hibernate.key;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class LongStringPrimaryKey implements Serializable {
	private static final long serialVersionUID = 5403182062299165933L;

	/** 主键，角色Id */
	private long uid;
	/** xid */
	private String xid;

	public LongStringPrimaryKey() {
	}

	public LongStringPrimaryKey(long uid, String xid) {
		this.uid = uid;
		this.xid = xid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xid == null) ? 0 : xid.hashCode());
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
		LongStringPrimaryKey other = (LongStringPrimaryKey) obj;
		if (xid == null) {
			if (other.xid != null)
				return false;
		} else if (!xid.equals(other.xid))
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
}
