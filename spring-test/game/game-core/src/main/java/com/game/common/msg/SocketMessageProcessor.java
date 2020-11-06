package com.game.common.msg;

import com.game.common.user.UserBean;
import com.game.core.handler.MsgBack;
import com.google.protobuf.GeneratedMessage;

public abstract class SocketMessageProcessor implements IProcessor {
	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
	}
}
