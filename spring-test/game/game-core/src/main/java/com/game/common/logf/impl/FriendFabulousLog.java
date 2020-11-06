package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FriendFabulousLog extends AbstractLog {
	private long friendUid; // 好友uid

	public FriendFabulousLog(UserBean userbean, long friendUid) {
		super(LogfType.FRIEND_FABULOUS, userbean);

		this.friendUid = friendUid;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(friendUid).append("|").append("1");
		return sb.toString();
	}
}
