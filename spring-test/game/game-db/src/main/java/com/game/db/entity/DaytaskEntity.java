package com.game.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
 * 文件名：DaytaskEntity.java
 * <p>
 * 功能：1
 * <p>
 * 作者：Builded by AppTools.
 */
@Entity
@Table(name = "t_daytask")
public class DaytaskEntity extends BaseEntity<Long> {

	/** 下次刷新的时间戳 (s) */
	private Date resfreshTime;
	/** 任务进度状态信息 */
	private String taskInfo;
	/** 当天的活跃度 */
	private int point;
	/** 已经领取的活跃度奖励id */
	private String activeId;

	private Map<Integer, TaskInfo> taskInfoMap = new HashMap<Integer, DaytaskEntity.TaskInfo>();
	private List<Integer> activeIdList = new ArrayList<Integer>();

	public int getStatus(int id) {
		if (!taskInfoMap.containsKey(id)) {
			return 0;
		}
		return taskInfoMap.get(id).getStatus();
	}

	public int getProcess(int id) {
		if (!taskInfoMap.containsKey(id)) {
			return 0;
		}
		return taskInfoMap.get(id).getProcess();
	}

	public void updateStatus(int id, int status) {
		if (!taskInfoMap.containsKey(id)) {
			TaskInfo info = new TaskInfo();
			info.setStatus(status);
			taskInfoMap.put(id, info);
		}
		taskInfoMap.get(id).setStatus(status);
	}

	public void updateProcess(int id, int process) {
		if (!taskInfoMap.containsKey(id)) {
			TaskInfo info = new TaskInfo();
			info.setProcess(process);
			taskInfoMap.put(id, info);
		}
		taskInfoMap.get(id).setProcess(process);
	}

	public boolean addAciveId(int actId) {
		if (this.activeIdList.contains(actId)) {
			return false;
		}
		this.activeIdList.add(actId);
		return true;
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

	@Column(columnDefinition = "timestamp NULL DEFAULT NULL")
	public Date getResfreshTime() {
		return resfreshTime;
	}

	@Column(columnDefinition = "TEXT")
	public String getTaskInfo() {
		if (!taskInfoMap.isEmpty()) {
			taskInfo = JsonUtil.toJson(taskInfoMap);
		}
		return taskInfo;
	}

	@Column(columnDefinition = " int default 0")
	public int getPoint() {
		return point;
	}

	@Column(columnDefinition = " varchar(255) default '' ")
	public String getActiveId() {
		if (activeIdList != null) {
			activeId = JsonUtil.toJson(activeIdList);
		}
		return activeId;
	}

	@Transient
	public Map<Integer, TaskInfo> getTaskInfoMap() {
		return taskInfoMap;
	}

	@Transient
	public List<Integer> getActiveIdList() {
		return activeIdList;
	}

	@Override
	public void setId(Long id) {
		this.uid = id;
	}

	public void setResfreshTime(Date resfreshTime) {
		this.resfreshTime = resfreshTime;
	}

	public void setTaskInfo(String taskInfo) {
		if (!StringUtils.isEmpty(taskInfo)) {
			this.taskInfoMap = JsonUtil.toMapIntegerKey(taskInfo, TaskInfo.class);
		}
		this.taskInfo = taskInfo;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public void setActiveId(String activeId) {
		if (!StringUtils.isEmpty(activeId)) {
			this.activeIdList = JsonUtil.toList(activeId, Integer.class);
		}
		this.activeId = activeId;
	}

	public class TaskInfo {
		private int process;
		private int status;

		public int getProcess() {
			return process;
		}

		public int getStatus() {
			return status;
		}

		public void setProcess(int process) {
			this.process = process;
		}

		public void setStatus(int status) {
			this.status = status;
		}
	}
}
