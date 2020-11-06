package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.game.core.hibernate.orm.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件名：AchieveEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name = "findMaxShowId", query = "select max(id) from ShowIdEntity")
@Entity
@Table(name = "t_show_id")
public class ShowIdEntity extends BaseEntity<Long> {
	@Id
	private long showId;
	@Column
	private long targetUid;

	@Override
	public Long getId() {
		return showId;
	}

	@Override
	public void setId(Long showId) {
		this.showId = showId;
	}
}
