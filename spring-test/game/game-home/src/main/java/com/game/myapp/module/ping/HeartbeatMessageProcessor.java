package com.game.myapp.module.ping;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.IProcessor;
import com.game.common.session.GameSession;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGHeartBeatMsg.CGHeartBeatProto;
import buffer.CGHttpBeatMsg.CGHttpBeatProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGHeartBeatProto.class, CGHttpBeatProto.class,

})
public class HeartbeatMessageProcessor implements IProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGHttpBeatMsg:
			GameGlobals.heartBeatManager.CGHttpBeat(msgBack, userBean, (CGHttpBeatProto) msg);
			break;
		}
	}

	@Override
	public void process(GameSession session, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGHeartBeatMsg:
			GameGlobals.heartBeatManager.CGHeartBeatMsg(session, (CGHeartBeatProto) msg);
			return;
		}
	}
}
