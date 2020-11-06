package com.game.myapp.module.shop;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGShopBuyMsg.CGShopBuyProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGShopBuyProto.class,

})
public class ShopMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGShopBuyMsg:
			GameGlobals.shopManager.CGShopBuyMsg(msgBack, userBean.getUid(), (CGShopBuyProto) msg);
			break;
		}
	}
}
