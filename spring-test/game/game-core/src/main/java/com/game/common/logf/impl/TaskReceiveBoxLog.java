package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaskReceiveBoxLog extends AbstractLog {
	private int giftId;// 礼包id

	public TaskReceiveBoxLog(UserBean userbean, int giftId) {
		super(LogfType.TASK_RECEIVE_BOX, userbean);

		this.giftId = giftId;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(giftId);
		return sb.toString();
	}
}
