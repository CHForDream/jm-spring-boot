package com.game.myapp.module.mail;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGMailDeleteMsg.CGMailDeleteProto;
import buffer.CGMailReadMsg.CGMailReadProto;
import buffer.CGMailgetListMsg.CGMailgetListProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGMailgetListProto.class, CGMailReadProto.class, CGMailDeleteProto.class,

})
public class MailMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGMailgetListMsg:
			GameGlobals.mailManager.CGMailgetListMsg(msgBack, userBean, (CGMailgetListProto) msg);
			break;
		case MsgType.CGMailReadMsg:
			GameGlobals.mailManager.CGMailReadMsg(msgBack, userBean, (CGMailReadProto) msg);
			break;
		case MsgType.CGMailDeleteMsg:
			GameGlobals.mailManager.CGMailDeleteMsg(msgBack, userBean, (CGMailDeleteProto) msg);
			break;
		}
	}
}
