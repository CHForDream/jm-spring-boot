package com.game.common.msg;

import com.game.common.session.GameSession;
import com.game.common.user.UserBean;
import com.game.core.handler.MsgBack;
import com.google.protobuf.GeneratedMessage;

public interface IProcessor {
	/**
	 * Socket链接消息处理
	 * 
	 */
	void process(GameSession session, int msgType, GeneratedMessage msg);

	/**
	 * HTTP消息处理
	 * 
	 */
	void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg);
}
