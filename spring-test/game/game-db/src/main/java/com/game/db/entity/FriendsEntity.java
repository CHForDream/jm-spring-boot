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
 * 文件名：FriendsEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_friends")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class FriendsEntity extends BaseEntity<LongLongPrimaryKey> {

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
	// 段位
	private int level;
	// createRoleMill
	private long createRoleMill;
	// shipGiveTimes
	private int shipGiveTimes;
	// shipReceiveTimes
	private int shipReceiveTimes;
	// daySend
	private int daySend;
	// dayReceive
	private int dayReceive;
	// 0不可收取1表示可收取2表示已收取
	private int shipStatus;

	@Override
	@Id
	public LongLongPrimaryKey getId() {
		return new LongLongPrimaryKey(uid, friendUid);
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

	public void setFriendUid(long friendUid) {
		this.friendUid = friendUid;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition = " int default 0")
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(columnDefinition = " int default 0")
	public int getFriendship() {
		return friendship;
	}

	public void setFriendship(int friendship) {
		this.friendship = friendship;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getVisitMill() {
		return visitMill;
	}

	public void setVisitMill(long visitMill) {
		this.visitMill = visitMill;
	}

	@Column(columnDefinition = " int default 0")
	public int getVisitTimes() {
		return visitTimes;
	}

	public void setVisitTimes(int visitTimes) {
		this.visitTimes = visitTimes;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getOffMill() {
		return offMill;
	}

	public void setOffMill(long offMill) {
		this.offMill = offMill;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getArenaStr() {
		return arenaStr;
	}

	public void setArenaStr(String arenaStr) {
		this.arenaStr = arenaStr;
	}

	@Column(columnDefinition = " int default 0")
	public int getArena() {
		return arena;
	}

	public void setArena(int arena) {
		this.arena = arena;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getLoginMill() {
		return loginMill;
	}

	public void setLoginMill(long loginMill) {
		this.loginMill = loginMill;
	}

	@Column(columnDefinition = " int default 0")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getCreateRoleMill() {
		return createRoleMill;
	}

	public void setCreateRoleMill(long createRoleMill) {
		this.createRoleMill = createRoleMill;
	}

	@Column(columnDefinition = " int default 0")
	public int getShipGiveTimes() {
		return shipGiveTimes;
	}

	public void setShipGiveTimes(int shipGiveTimes) {
		this.shipGiveTimes = shipGiveTimes;
	}

	@Column(columnDefinition = " int default 0")
	public int getShipReceiveTimes() {
		return shipReceiveTimes;
	}

	public void setShipReceiveTimes(int shipReceiveTimes) {
		this.shipReceiveTimes = shipReceiveTimes;
	}

	@Column(columnDefinition = " int default 0")
	public int getDaySend() {
		return daySend;
	}

	public void setDaySend(int daySend) {
		this.daySend = daySend;
	}

	@Column(columnDefinition = " int default 0")
	public int getDayReceive() {
		return dayReceive;
	}

	public void setDayReceive(int dayReceive) {
		this.dayReceive = dayReceive;
	}

	@Column(columnDefinition = " int default 0")
	public int getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(int shipStatus) {
		this.shipStatus = shipStatus;
	}

}
