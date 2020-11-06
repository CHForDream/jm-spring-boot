package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

/**
 * 文件名：BanchatEntity.java
 * <p>
 * 功能：聊天禁言
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_banchat")
public class BanchatEntity extends BaseEntity<Long> {
	// startTimeAll
	private long startTimeAll;
	// endTimeAll
	private long endTimeAll;
	// startTimeFriend
	private long startTimeFriend;
	// endTimeAllFriend
	private long endTimeAllFriend;
	// reasonAll
	private String reasonAll = null;
	// reasonFriend
	private String reasonFriend = null;

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

	@Override
	public void setId(Long id) {
		this.uid = id;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getStartTimeAll() {
		return startTimeAll;
	}

	public void setStartTimeAll(long startTimeAll) {
		this.startTimeAll = startTimeAll;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getEndTimeAll() {
		return endTimeAll;
	}

	public void setEndTimeAll(long endTimeAll) {
		this.endTimeAll = endTimeAll;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getStartTimeFriend() {
		return startTimeFriend;
	}

	public void setStartTimeFriend(long startTimeFriend) {
		this.startTimeFriend = startTimeFriend;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getEndTimeAllFriend() {
		return endTimeAllFriend;
	}

	public void setEndTimeAllFriend(long endTimeAllFriend) {
		this.endTimeAllFriend = endTimeAllFriend;
	}

	public String getReasonAll() {
		return reasonAll;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public void setReasonAll(String reasonAll) {
		this.reasonAll = reasonAll;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getReasonFriend() {
		return reasonFriend;
	}

	public void setReasonFriend(String reasonFriend) {
		this.reasonFriend = reasonFriend;
	}

}
