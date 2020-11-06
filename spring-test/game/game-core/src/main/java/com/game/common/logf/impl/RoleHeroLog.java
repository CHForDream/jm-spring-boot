package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleHeroLog extends AbstractLog {
	private int heroId; // 英雄id

	public RoleHeroLog(UserBean userbean) {
		super(LogfType.HERO_INSERT, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(heroId).append("|").append(1);
		return sb.toString();
	}
}
