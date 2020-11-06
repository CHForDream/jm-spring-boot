package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleGuideLog extends AbstractLog {
	private int guideId; // 引导id

	public RoleGuideLog(LogfType logType, UserBean userbean) {
		super(logType, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(guideId);
		return sb.toString();
	}
}
