package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FriendAddLog extends AbstractLog {
	private long friendUid; // 好友uid

	public FriendAddLog(UserBean userbean, long friendUid) {
		super(LogfType.FRIEND_ADD, userbean);

		this.friendUid = friendUid;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(friendUid).append("|").append("");
		return sb.toString();
	}
}
