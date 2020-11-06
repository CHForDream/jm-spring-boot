package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AchieveCompleteLog extends AbstractLog {
	private int achieveId; // 成就id

	public AchieveCompleteLog(UserBean userbean) {
		super(LogfType.ACHIEVEMENT_COMPLETE, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(achieveId);
		return sb.toString();
	}
}
