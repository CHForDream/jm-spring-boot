package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

@Entity
@Table(name = "t_role_guide")
public class RoleGuideEntity extends BaseEntity<Long> {

	private String guide;// 执行过的引导
	private String systemId;// 已经开启的系统
	private String nextGuide;// 触发未执行的引导
	private String battleGuides;// 战斗棋子引导

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

	@Column(columnDefinition = "TEXT")
	public String getGuide() {
		return guide;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getSystemId() {
		return systemId;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getNextGuide() {
		return nextGuide;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getBattleGuides() {
		return battleGuides;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public void setNextGuide(String nextGuide) {
		this.nextGuide = nextGuide;
	}

	public void setBattleGuides(String battleGuides) {
		this.battleGuides = battleGuides;
	}
}
