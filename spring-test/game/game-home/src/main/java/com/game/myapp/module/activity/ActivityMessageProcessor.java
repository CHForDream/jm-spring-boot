package com.game.myapp.module.activity;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGActivityInfo.CGActivityInfoProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGActivityInfoProto.class,

})
public class ActivityMessageProcessor extends HttpMessageProcessor {
	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGActivityInfo:
			GameGlobals.roleActivityManager.CGActivityInfoProto(msgBack, userBean, (CGActivityInfoProto) msg);
			break;
		}
	}
}
