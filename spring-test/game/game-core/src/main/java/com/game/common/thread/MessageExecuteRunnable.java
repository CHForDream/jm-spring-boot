package com.game.common.thread;

import com.game.common.msg.IProcessor;
import com.game.common.session.GameSession;
import com.google.protobuf.GeneratedMessage;

public class MessageExecuteRunnable implements Runnable {
	private GameSession session = null;
	private IProcessor processor = null;
	private GeneratedMessage msg = null;
	private int msgType = 0;

	public MessageExecuteRunnable(GameSession session, IProcessor processor, int msgType, GeneratedMessage msg) {
		this.session = session;
		this.processor = processor;
		this.msgType = msgType;
		this.msg = msg;
	}

	public GameSession getSession() {
		return session;
	}

	public void setSession(GameSession session) {
		this.session = session;
	}

	public IProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(IProcessor processor) {
		this.processor = processor;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public GeneratedMessage getMsg() {
		return msg;
	}

	public void setMsg(GeneratedMessage msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		try {
			processor.process(session, msgType, msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
