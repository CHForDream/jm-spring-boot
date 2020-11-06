package com.game.myapp.module.item;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetBagListMsg.CGGetBagListProto;
import buffer.CGSellBagMsg.CGSellBagProto;
import buffer.CGUserItemMsg.CGUserItemProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGGetBagListProto.class, CGSellBagProto.class, CGUserItemProto.class,

})
public class ItemMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGGetBagListMsg:
			GameGlobals.bagManager.CGGetBagListMsg(msgBack, userBean, (CGGetBagListProto) msg);
			break;
		case MsgType.CGSellBagMsg:
			GameGlobals.bagManager.CGSellBagMsg(msgBack, userBean, (CGSellBagProto) msg);
			break;
		case MsgType.CGUserItemMsg:
			GameGlobals.itemManager.CGUserItemMsg(msgBack, userBean, (CGUserItemProto) msg);
			break;
		}
	}
}
