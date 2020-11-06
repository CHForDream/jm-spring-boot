package com.game.core.hibernate.key;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class StringStringPrimaryKey implements Serializable {
	private static final long serialVersionUID = 5403182062299165933L;

	/** 主键，角色Id */
	private String uid;
	/** xid */
	private String xid;

	public StringStringPrimaryKey() {
	}

	public StringStringPrimaryKey(String uid, String xid) {
		this.uid = uid;
		this.xid = xid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((xid == null) ? 0 : xid.hashCode());
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
		StringStringPrimaryKey other = (StringStringPrimaryKey) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (xid == null) {
			if (other.xid != null)
				return false;
		} else if (!xid.equals(other.xid))
			return false;
		return true;
	}
}
