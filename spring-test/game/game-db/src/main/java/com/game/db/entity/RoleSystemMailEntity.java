package com.game.db.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;
import com.game.utils.JsonUtil;
import com.google.common.collect.Sets;

@Entity
@Table(name = "t_role_mail")
public class RoleSystemMailEntity extends BaseEntity<Long> {

	/** 系统邮件Id列表 */
	private Set<Long> systemMailIdSet = Sets.newHashSet();

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

	@Column(columnDefinition = "TEXT")
	public String getSystemMailIds() {
		return JsonUtil.toJson(systemMailIdSet);
	}

	public void setSystemMailIds(String systemMailIds) {
		List<Long> mailIdList = JsonUtil.toList(systemMailIds, Long.class);
		systemMailIdSet.clear();
		systemMailIdSet.addAll(mailIdList);
	}

	@Transient
	public Set<Long> getSystemMailIdSet() {
		return systemMailIdSet;
	}
}
