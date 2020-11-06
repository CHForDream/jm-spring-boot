package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

@Entity
@Table(name = "t_luck_drawInfo")
public class LuckDrawInfoEntity extends BaseEntity<Long> {

	private long nextFreeLuckDrawTime = 0;// 下次免费抽奖的时间
	private String oneDrawInfo = "";// 单抽信息
	private int luckNum = 0;// 幸运值
	private int oneDrawNum = 0;// 单抽次数

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
	public long getNextFreeLuckDrawTime() {
		return nextFreeLuckDrawTime;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getOneDrawInfo() {
		return oneDrawInfo;
	}

	@Column(columnDefinition = " int default 0")
	public int getLuckNum() {
		return luckNum;
	}

	@Column(columnDefinition = " int default 0")
	public int getOneDrawNum() {
		return oneDrawNum;
	}

	public void setNextFreeLuckDrawTime(long nextFreeLuckDrawTime) {
		this.nextFreeLuckDrawTime = nextFreeLuckDrawTime;
	}

	public void setOneDrawInfo(String oneDrawInfo) {
		this.oneDrawInfo = oneDrawInfo;
	}

	public void setLuckNum(int luckNum) {
		this.luckNum = luckNum;
	}

	public void setOneDrawNum(int oneDrawNum) {
		this.oneDrawNum = oneDrawNum;
	}
}
