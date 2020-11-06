package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.cache.CacheType;
import com.game.core.hibernate.key.LongIntPrimaryKey;
import com.game.core.hibernate.orm.BaseEntity;

/**
 * 文件名：GiftsEntity.java
 * <p>
 * 功能：gifts
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_gifts")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class GiftEntity extends BaseEntity<LongIntPrimaryKey> {

	// itemId
	private int itemId;
	// num
	private int num;

	@Override
	@Id
	public LongIntPrimaryKey getId() {
		return new LongIntPrimaryKey(this.uid, this.itemId);
	}

	@Override
	public void setId(LongIntPrimaryKey id) {
		this.uid = id.getUid();
		this.itemId = id.getXid();
	}

	@Transient
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Column(columnDefinition = " int default 0")
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
