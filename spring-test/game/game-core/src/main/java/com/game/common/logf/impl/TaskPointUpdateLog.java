package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaskPointUpdateLog extends AbstractLog {
	private int taskId;// 任务id
	private int point;// 任务id

	public TaskPointUpdateLog(UserBean userbean, int taskId, int point) {
		super(LogfType.TASK_POINT_UPDATE, userbean);

		this.taskId = taskId;
		this.point = point;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(taskId).append("|").append(point);
		return sb.toString();
	}
}
