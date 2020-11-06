package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleSignLog extends AbstractLog {
	private int day; // 第几天
	private int itemType;
	private int itemId;
	private int itemNum;

	public RoleSignLog(LogfType logType, UserBean userbean) {
		super(logType, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(day).append("|").append(itemType).append("|").append(itemId).append("|").append(itemNum);
		return sb.toString();
	}
}
