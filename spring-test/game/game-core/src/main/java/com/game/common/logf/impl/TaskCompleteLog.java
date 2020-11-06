package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaskCompleteLog extends AbstractLog {
	private int taskType;// 任务类型
	private int taskId;// 任务id

	public TaskCompleteLog(UserBean userbean, int taskType, int taskId) {
		super(LogfType.TASK_COMPLETE, userbean);

		this.taskType = taskType;
		this.taskId = taskId;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(taskType).append("|").append(taskId);
		return sb.toString();
	}
}
