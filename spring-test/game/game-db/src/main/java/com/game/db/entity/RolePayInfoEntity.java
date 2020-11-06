package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

@Entity
@Table(name = "t_role_payinfo")
public class RolePayInfoEntity extends BaseEntity<Long> {

	private String payInfo = "";// 每个挡位的充值次数
	private int cash;// 充值钻石数量
	private int rewardCash;// 奖励钻石数量

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
	public String getPayInfo() {
		return payInfo;
	}

	@Column(columnDefinition = " int default 0")
	public int getCash() {
		return cash;
	}

	@Column(columnDefinition = " int default 0")
	public int getRewardCash() {
		return rewardCash;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public void setRewardCash(int rewardCash) {
		this.rewardCash = rewardCash;
	}
}
