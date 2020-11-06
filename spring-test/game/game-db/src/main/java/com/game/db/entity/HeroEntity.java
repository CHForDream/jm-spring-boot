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
 * 文件名：HeroEntity.java
 * <p>
 * 功能：hero
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_hero")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class HeroEntity extends BaseEntity<LongIntPrimaryKey> {
	// heroId
	private int heroId;
	// getTime
	private long getTime;

	@Override
	@Id
	public LongIntPrimaryKey getId() {
		return new LongIntPrimaryKey(this.uid, this.heroId);
	}

	@Override
	public void setId(LongIntPrimaryKey id) {
		this.uid = id.getUid();
		this.heroId = id.getXid();
	}

	@Transient
	public int getHeroId() {
		return heroId;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getGetTime() {
		return getTime;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	public void setGetTime(long getTime) {
		this.getTime = getTime;
	}
}