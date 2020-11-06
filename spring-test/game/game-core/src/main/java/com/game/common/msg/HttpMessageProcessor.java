package com.game.common.msg;

import com.game.common.session.GameSession;
import com.google.protobuf.GeneratedMessage;

public abstract class HttpMessageProcessor implements IProcessor {

	@Override
	public void process(GameSession session, int msgType, GeneratedMessage msg) {
	}
}
