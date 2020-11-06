package com.game.myapp.module.chat;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.SocketMessageProcessor;
import com.game.common.session.GameSession;
import com.game.common.user.UserBean;
import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.util.MessageUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGChatGm.CGChatGmProto;
import buffer.CGChatMsg.CGChatProto;
import buffer.CGConnectChatMsg.CGConnectChatProto;

@Component
@ProcessorDefine(protos = {
		// Http
		CGChatGmProto.class,

		// Socket
		CGConnectChatProto.class, CGChatProto.class,

})

public class ChatMessageProcessor extends SocketMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGChatGm:
			GameGlobals.chatManager.CGChatGmProto(msgBack, userBean, (CGChatGmProto) msg);
			break;
		}
	}

	@Override
	public void process(GameSession session, int msgType, GeneratedMessage msg) {
		try {
			switch (msgType) {
			case MsgType.CGConnectChatMsg:
				connectChatMsg(session, (CGConnectChatProto) msg);
				return;
			case MsgType.CGChatMsg:
				GameGlobals.chatManager.doChat(session, (CGChatProto) msg);
				return;
			}
		} catch (Exception e) {
			Loggers.msgLogger.error("Error handle message! msgType = " + msgType);
			e.printStackTrace();
		}
	}

	private void connectChatMsg(GameSession session, CGConnectChatProto msg) {
		long uid = msg.getUid();
		session.setUid(uid);
		Globals.getChatSessionManager().addSession(session);

		MessageUtil.sendCommonBack(session, msg.getMsgType(), 0, null);
	}
}
