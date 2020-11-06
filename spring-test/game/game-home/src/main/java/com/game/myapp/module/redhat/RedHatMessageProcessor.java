package com.game.myapp.module.redhat;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetRedHatinfoMsg.CGGetRedHatinfoProto;
import buffer.CGRedHatCancelMsg.CGRedHatCancelProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGGetRedHatinfoProto.class, CGRedHatCancelProto.class,

})
public class RedHatMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGGetRedHatInfoMsg:
			GameGlobals.redHatManager.CGGetRedHatInfo(msgBack, userBean, (CGGetRedHatinfoProto) msg);
			break;
		case MsgType.CGRedHatCancelMsg:
			GameGlobals.redHatManager.CGRedHatCancelProto(msgBack, userBean, (CGRedHatCancelProto) msg);
			break;
		}
	}
}
