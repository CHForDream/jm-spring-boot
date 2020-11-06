package com.game.core.handler;

import com.google.protobuf.AbstractMessage.Builder;

@SuppressWarnings("rawtypes")
public class MsgBuilder {
	private int msgType;
	private Builder builder;

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

}
