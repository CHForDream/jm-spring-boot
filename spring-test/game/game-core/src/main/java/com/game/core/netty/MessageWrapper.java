package com.game.core.netty;

import com.game.common.msg.IProcessor;
import com.google.protobuf.GeneratedMessage;

public class MessageWrapper {
	private int msgType = 0;
	private IProcessor processor;
	private GeneratedMessage msg;

	public MessageWrapper(int type, IProcessor processor, GeneratedMessage msg) {
		this.msgType = type;
		this.processor = processor;
		this.msg = msg;
	}

	public IProcessor getProcessor() {
		return processor;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setType(int type) {
		this.msgType = type;
	}

	public void setProcessor(IProcessor processor) {
		this.processor = processor;
	}

	public GeneratedMessage getMsg() {
		return msg;
	}

	public void setMsg(GeneratedMessage msg) {
		this.msg = msg;
	}

}
