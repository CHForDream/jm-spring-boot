package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.game.core.hibernate.orm.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "t_role_activity")
public class RoleActivityEntity extends BaseEntity<Long> {

	/** 玩家uid */
	@Id
	private long uid = 0;
	/** 活动版本信息 */
	@Column(columnDefinition = "TEXT")
	private String activityInfo;

	@Override
	public Long getId() {
		return uid;
	}

	@Override
	public void setId(Long uid) {
		this.uid = uid;
	}
}
