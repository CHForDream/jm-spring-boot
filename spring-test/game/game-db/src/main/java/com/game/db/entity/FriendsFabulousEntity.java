package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

@Entity
@Table(name = "t_friends_fabulous")
public class FriendsFabulousEntity extends BaseEntity<Long> {

	/** 获赞数量 */
	private int fabulous;
	/** 我给哪些玩家点过赞 */
	private String uids = "";

	/** 点赞次数 */
	private int fabulousNum = 0;

	/** 上次点赞的时间 */
	private long fabulousTime = 0;

	@Transient
	@Override
	public Long getId() {
		return uid;
	}

	@Id
	@Override
	public long getUid() {
		return uid;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getFabulousTime() {
		return fabulousTime;
	}

	public void setFabulousTime(long fabulousTime) {
		this.fabulousTime = fabulousTime;
	}

	@Column(columnDefinition = " int default 0")
	public int getFabulousNum() {
		return fabulousNum;
	}

	public void setFabulousNum(int fabulousNum) {
		this.fabulousNum = fabulousNum;
	}

	@Override
	public void setId(Long id) {
		this.uid = id;
	}

	@Column(columnDefinition = " int default 0")
	public int getFabulous() {
		return fabulous;
	}

	public void setFabulous(int fabulous) {
		this.fabulous = fabulous;
	}

	@Column(columnDefinition = "TEXT")
	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

}
