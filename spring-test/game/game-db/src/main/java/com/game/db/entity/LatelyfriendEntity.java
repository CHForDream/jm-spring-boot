package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.cache.CacheType;
import com.game.core.hibernate.key.LongLongPrimaryKey;
import com.game.core.hibernate.orm.BaseEntity;

/**
 * 文件名：LatelyfriendEntity.java
 * <p>
 * 功能：latelyfriend
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_latelyfriend")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class LatelyfriendEntity extends BaseEntity<LongLongPrimaryKey> {

	// friendUid
	private long friendUid;
	// avatar
	private String avatar = null;
	// createTime
	private long createTime;
	// name
	private String name = null;
	// sex
	private int sex;
	// friendship
	private int friendship;
	// visitMill
	private long visitMill;
	// visitTimes
	private int visitTimes;
	// offMill
	private long offMill;
	// 地域
	private String arenaStr = null;
	// 地域
	private int arena;
	// 位置
	private String pos = null;
	// loginMill
	private long loginMill;
	// vip
	private int vip;

	@Override
	@Id
	public LongLongPrimaryKey getId() {
		return new LongLongPrimaryKey(this.uid, this.friendUid);
	}

	@Override
	public void setId(LongLongPrimaryKey id) {
		this.uid = id.getUid();
		this.friendUid = id.getXid();
	}

	@Transient
	public long getFriendUid() {
		return friendUid;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getAvatar() {
		return avatar;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getCreateTime() {
		return createTime;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getName() {
		return name;
	}

	@Column(columnDefinition = " int default 0")
	public int getSex() {
		return sex;
	}

	@Column(columnDefinition = " int default 0")
	public int getFriendship() {
		return friendship;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getVisitMill() {
		return visitMill;
	}

	@Column(columnDefinition = " int default 0")
	public int getVisitTimes() {
		return visitTimes;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getOffMill() {
		return offMill;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getArenaStr() {
		return arenaStr;
	}

	@Column(columnDefinition = " int default 0")
	public int getArena() {
		return arena;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getPos() {
		return pos;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getLoginMill() {
		return loginMill;
	}

	@Column(columnDefinition = " int default 0")
	public int getVip() {
		return vip;
	}

	public void setFriendUid(long friendUid) {
		this.friendUid = friendUid;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setFriendship(int friendship) {
		this.friendship = friendship;
	}

	public void setVisitMill(long visitMill) {
		this.visitMill = visitMill;
	}

	public void setVisitTimes(int visitTimes) {
		this.visitTimes = visitTimes;
	}

	public void setOffMill(long offMill) {
		this.offMill = offMill;
	}

	public void setArenaStr(String arenaStr) {
		this.arenaStr = arenaStr;
	}

	public void setArena(int arena) {
		this.arena = arena;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public void setLoginMill(long loginMill) {
		this.loginMill = loginMill;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}
}
