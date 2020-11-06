package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleRegisterLog extends AbstractLog {
	private String ip;
	private String dev;
	private String name;

	public RoleRegisterLog(UserBean userbean) {
		super(LogfType.ROLE_REGISTER, userbean);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(ip).append("|").append(dev).append("|").append(name);
		return sb.toString();
	}
}
