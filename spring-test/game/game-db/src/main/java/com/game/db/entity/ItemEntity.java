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
 * 文件名：ItemsEntity.java
 * <p>
 * 功能：items
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_items")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class ItemEntity extends BaseEntity<LongIntPrimaryKey> {
	// itemId
	private int itemId;
	// 数量
	private int num;
	// 无限道具截止时间
	private long infiniteTime;
	// 每日免费数量（每日重置）
	private int freeNum;
	// 每日免费重置时间
	private long freeResetTime;

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

	@Column(columnDefinition = "bigint(20) default 0")
	public long getInfiniteTime() {
		return infiniteTime;
	}

	public void setInfiniteTime(long infiniteTime) {
		this.infiniteTime = infiniteTime;
	}

	@Column(columnDefinition = " int default 0")
	public int getFreeNum() {
		return freeNum;
	}

	public void setFreeNum(int freeNum) {
		this.freeNum = freeNum;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getFreeResetTime() {
		return freeResetTime;
	}

	public void setFreeResetTime(long freeResetTime) {
		this.freeResetTime = freeResetTime;
	}

	@Override
	public String toString() {
		return "ItemEntity [itemId=" + itemId + ", num=" + num + ", infiniteTime=" + infiniteTime + ", freeNum=" + freeNum + ", freeResetTime=" + freeResetTime
				+ "]";
	}
}
