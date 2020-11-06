package com.game.myapp.module.luckdraw;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetLuckDrawInfoMsg.CGGetLuckDrawInfoProto;
import buffer.CGLuckDrawMsg.CGLuckDrawMsgProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGGetLuckDrawInfoProto.class, CGLuckDrawMsgProto.class,

})
public class LuckDrawMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGGetLuckDrawInfoMsg:
			GameGlobals.luckDrawManager.CGGetLuckDrawInfo(msgBack, userBean.getUid(), (CGGetLuckDrawInfoProto) msg);
			break;
		case MsgType.CGLuckDrawMsg:
			GameGlobals.luckDrawManager.CGLuckDraw(msgBack, userBean.getUid(), (CGLuckDrawMsgProto) msg);
			break;
		}
	}
}
