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
 * 英雄评论
 * 
 * @author 刘朋飞
 *
 */
@Entity
@Table(name = "t_hero_comment")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class HeroCommentEntity extends BaseEntity<LongIntPrimaryKey> {

	/** 英雄标识 */
	private int heroCode;
	/** 评论 */
	private String comment = "";
	/** 点赞数量 */
	private int fabulousNum;
	/** 创建时间 */
	private long createTime;
	/** 点赞玩家 */
	private String fabulousUids;

	@Override
	@Id
	public LongIntPrimaryKey getId() {
		return new LongIntPrimaryKey(this.uid, this.heroCode);
	}

	@Override
	public void setId(LongIntPrimaryKey id) {
		this.uid = id.getUid();
		this.heroCode = id.getXid();
	}

	public void setFabulousUids(String fabulousUids) {
		this.fabulousUids = fabulousUids;
	}

	public void setHeroCode(int heroCode) {
		this.heroCode = heroCode;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setFabulousNum(int fabulousNum) {
		this.fabulousNum = fabulousNum;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@Transient
	public int getHeroCode() {
		return heroCode;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getComment() {
		return comment;
	}

	@Column(columnDefinition = " int default 0")
	public int getFabulousNum() {
		return fabulousNum;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getCreateTime() {
		return createTime;
	}

	@Column(columnDefinition = "MediumText")
	public String getFabulousUids() {
		return fabulousUids;
	}

	@Override
	public String toString() {
		return "HeroCommentEntity [heroCode=" + heroCode + ", comment=" + comment + ", fabulousNum=" + fabulousNum + ", createTime=" + createTime + "]";
	}
}
