package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatLog extends AbstractLog {
	private int type; // 聊天类型  1世界聊天 2私聊
	private long toUid; // 发送给谁
	private String content; // 聊天内容

	public ChatLog(UserBean userbean) {
		super(LogfType.CHAT_TYPE, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(type).append("|").append(toUid).append("|").append(0).append("|").append(content);
		return sb.toString();
	}
}
