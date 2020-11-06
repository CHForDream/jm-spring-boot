package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BattleDupStartLog extends AbstractLog {
	private int targetId;
	private int heroId;
	private int dupType;

	public BattleDupStartLog(UserBean userbean) {
		super(LogfType.BATTLE_DUP_START_APP, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(targetId).append("|").append(0).append("|").append(heroId).append("|").append(dupType);
		return sb.toString();
	}
}
