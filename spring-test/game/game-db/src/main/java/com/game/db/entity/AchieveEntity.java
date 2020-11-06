package com.game.db.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;

/**
 * 文件名：AchieveEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_achieve")
public class AchieveEntity extends BaseEntity<Long> {
	// 成就信息
	private String achieveInfo;
	// totalPoint
	private int totalPoint;
	// totalReward
	private int totalReward;
	// sortPoints
	private String sortPoints;
	// totalStatus
	private String totalStatus;

	private Map<Integer, AchieveInfo> achieveInfoMap = new HashMap<Integer, AchieveInfo>();
	private Map<Integer, Integer> sortPointsMap = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> totalStatusMap = new HashMap<Integer, Integer>();

	public int getStatus(int id) {
		if (!achieveInfoMap.containsKey(id)) {
			return 0;
		}
		return achieveInfoMap.get(id).getStatus();
	}

	public int getProcess(int id) {
		if (!achieveInfoMap.containsKey(id)) {
			return 0;
		}
		return achieveInfoMap.get(id).getProcess();
	}

	public void updateStatus(int id, int status) {
		if (!achieveInfoMap.containsKey(id)) {
			AchieveInfo info = new AchieveInfo();
			info.setStatus(status);
			achieveInfoMap.put(id, info);
		}
		achieveInfoMap.get(id).setStatus(status);
	}

	public void updateProcess(int id, int process) {
		if (!achieveInfoMap.containsKey(id)) {
			AchieveInfo info = new AchieveInfo();
			info.setProcess(process);
			achieveInfoMap.put(id, info);
		}
		achieveInfoMap.get(id).setProcess(process);
	}

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

	@Column(columnDefinition = "TEXT")
	public String getAchieveInfo() {
		if (!achieveInfoMap.isEmpty()) {
			achieveInfo = JsonUtil.toJson(achieveInfoMap);
		}
		return achieveInfo;
	}

	@Column(columnDefinition = " int default 0")
	public int getTotalPoint() {
		return totalPoint;
	}

	@Column(columnDefinition = " int default 0")
	public int getTotalReward() {
		return totalReward;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getSortPoints() {
		if (!sortPointsMap.isEmpty()) {
			sortPoints = JsonUtil.toJson(sortPointsMap);
		}
		return sortPoints;
	}

	@Column(columnDefinition = " varchar(255) default ''")
	public String getTotalStatus() {
		if (!totalStatusMap.isEmpty()) {
			totalStatus = JsonUtil.toJson(totalStatusMap);
		}
		return totalStatus;
	}

	@Transient
	public Map<Integer, AchieveInfo> getAchieveInfoMap() {
		return achieveInfoMap;
	}

	@Transient
	public Map<Integer, Integer> getSortPointsMap() {
		return sortPointsMap;
	}

	@Transient
	public Map<Integer, Integer> getTotalStatusMap() {
		return totalStatusMap;
	}

	@Override
	public void setId(Long id) {
		this.uid = id;
	}

	public void setAchieveInfo(String achieveInfo) {
		if (!StringUtils.isEmpty(achieveInfo)) {
			this.achieveInfoMap = JsonUtil.toMapIntegerKey(achieveInfo, AchieveInfo.class);
		}
		this.achieveInfo = achieveInfo;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}

	public void setTotalReward(int totalReward) {
		this.totalReward = totalReward;
	}

	public void setSortPoints(String sortPoints) {
		if (!StringUtils.isEmpty(sortPoints)) {
			this.sortPointsMap = JsonUtil.toMapIntegerKey(sortPoints, Integer.class);
		}
		this.sortPoints = sortPoints;
	}

	public void setTotalStatus(String totalStatus) {
		if (!StringUtils.isEmpty(totalStatus)) {
			this.totalStatusMap = JsonUtil.toMapIntegerKey(totalStatus, Integer.class);
		}
		this.totalStatus = totalStatus;
	}

	public void setSortPointsMap(Map<Integer, Integer> sortPointsMap) {
		this.sortPointsMap = sortPointsMap;
	}

	public void setTotalStatusMap(Map<Integer, Integer> totalStatusMap) {
		this.totalStatusMap = totalStatusMap;
	}

	public class AchieveInfo {
		private int status;
		private int process;

		public int getStatus() {
			return status;
		}

		public int getProcess() {
			return process;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public void setProcess(int process) {
			this.process = process;
		}
	}
}
