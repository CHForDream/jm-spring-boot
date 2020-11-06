package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

@Entity
@Table(name = "t_account")
public class AccountEntity extends BaseEntity<String> {

	private String pfId = "";

	@Transient
	@Override
	public String getId() {
		return pfId;
	}

	@Override
	public void setId(String id) {
		this.pfId = id;
	}

	@Id
	public String getPfId() {
		return pfId;
	}

	public void setPfId(String id) {
		this.pfId = id;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
}
