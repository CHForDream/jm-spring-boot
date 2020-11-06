package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityCompleteLog extends AbstractLog {
	private int activityType; // 活动类型
	private long activityId; // 活动id
	private int target; // 活动目标

	public ActivityCompleteLog(UserBean userbean) {
		super(LogfType.ACTIVITY_COMPLETE, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(activityType).append("|").append(activityId).append("|").append(target);
		return sb.toString();
	}
}
