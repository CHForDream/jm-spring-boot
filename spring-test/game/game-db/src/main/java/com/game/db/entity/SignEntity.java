package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

/**
 * 文件名：SignEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_sign")
public class SignEntity extends BaseEntity<Long> {

	// 七日签到时间
	private long sevenSignTime = 0;
	// 七日签到天数
	private int sevenSignday = 0;

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
	public void setId(Long uid) {
		this.uid = uid;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getSevenSignTime() {
		return sevenSignTime;
	}

	@Column(columnDefinition = " int default 0")
	public int getSevenSignday() {
		return sevenSignday;
	}

	public void setSevenSignTime(long sevenSignTime) {
		this.sevenSignTime = sevenSignTime;
	}

	public void setSevenSignday(int sevenSignday) {
		this.sevenSignday = sevenSignday;
	}
}
