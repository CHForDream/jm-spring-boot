package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.cache.CacheType;
import com.game.core.hibernate.key.LongLongPrimaryKey;
import com.game.core.hibernate.orm.BaseEntity;

@Entity
@Table(name = "t_username_uid")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class UserNameUidEntity extends BaseEntity<LongLongPrimaryKey> {

	// 名字对应的hashcode, 第一索引
	private long hashcodeId;
	// 玩家uid, 第二索引
	private long uid;
	// 玩家名字
	private String name;

	@Override
	@Id
	public LongLongPrimaryKey getId() {
		return new LongLongPrimaryKey(hashcodeId, uid);
	}

	@Override
	public void setId(LongLongPrimaryKey id) {
		this.hashcodeId = id.getUid();// 第一索引
		this.uid = id.getXid();// 第二索引
	}

	@Transient
	public long getHashcodeId() {
		return hashcodeId;
	}

	public void setHashcodeId(long hashcodeId) {
		this.hashcodeId = hashcodeId;
	}

	@Transient
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
