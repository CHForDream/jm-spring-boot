package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AchieveUpgradeLog extends AbstractLog {
	private int achieveLeve; // 成就id

	public AchieveUpgradeLog(UserBean userbean) {
		super(LogfType.ACHIEVEMENT_UPGRADE, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(achieveLeve);
		return sb.toString();
	}
}
