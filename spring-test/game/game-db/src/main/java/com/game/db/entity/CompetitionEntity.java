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
 * 文件名：CompetitionEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_competition")
@CacheType(type = CacheType.ROLE_MULTI_MAPPING)
public class CompetitionEntity extends BaseEntity<LongLongPrimaryKey> {

	// competitionId
	private long competitionId;
	// competition
	private int competition;
	// startMill
	private long startMill;
	// point
	private int point;
	// status
	private int status;
	// heroInfos
	private String heroInfos = null;
	// endMill
	private long endMill;
	// maxWin
	private int maxWin;
	// step
	private int step;
	// times
	private int times;
	// win
	private int win;
	// conWin
	private int conWin;
	// taskType
	private int taskType;
	// taskParam
	private int taskParam;
	// mvpTimes
	private int mvpTimes;

	@Override
	@Id
	public LongLongPrimaryKey getId() {
		return new LongLongPrimaryKey(uid, competitionId);
	}

	@Override
	public void setId(LongLongPrimaryKey id) {
		this.uid = id.getUid();
		this.competitionId = id.getXid();
	}

	@Transient
	public long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(long competitionId) {
		this.competitionId = competitionId;
	}

	@Column(columnDefinition = " int default 0")
	public int getCompetition() {
		return competition;
	}

	public void setCompetition(int competition) {
		this.competition = competition;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getStartMill() {
		return startMill;
	}

	public void setStartMill(long startMill) {
		this.startMill = startMill;
	}

	@Column(columnDefinition = " int default 0")
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Column(columnDefinition = " int default 0")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(columnDefinition = "TEXT")
	public String getHeroInfos() {
		return heroInfos;
	}

	public void setHeroInfos(String heroInfos) {
		this.heroInfos = heroInfos;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getEndMill() {
		return endMill;
	}

	public void setEndMill(long endMill) {
		this.endMill = endMill;
	}

	@Column(columnDefinition = " int default 0")
	public int getMaxWin() {
		return maxWin;
	}

	public void setMaxWin(int maxWin) {
		this.maxWin = maxWin;
	}

	@Column(columnDefinition = " int default 0")
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Column(columnDefinition = " int default 0")
	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	@Column(columnDefinition = " int default 0")
	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	@Column(columnDefinition = " int default 0")
	public int getConWin() {
		return conWin;
	}

	public void setConWin(int conWin) {
		this.conWin = conWin;
	}

	@Column(columnDefinition = " int default 0")
	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	@Column(columnDefinition = " int default 0")
	public int getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(int taskParam) {
		this.taskParam = taskParam;
	}

	@Column(columnDefinition = " int default 0")
	public int getMvpTimes() {
		return mvpTimes;
	}

	public void setMvpTimes(int mvpTimes) {
		this.mvpTimes = mvpTimes;
	}
}
