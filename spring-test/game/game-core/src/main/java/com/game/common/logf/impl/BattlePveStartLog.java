package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BattlePveStartLog extends AbstractLog {
	private int targetId;
	private int heroId;

	public BattlePveStartLog(UserBean userbean) {
		super(LogfType.BATTLE_PVE_START_APP, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(targetId).append("|").append(0).append("|").append(heroId);
		return sb.toString();
	}
}
