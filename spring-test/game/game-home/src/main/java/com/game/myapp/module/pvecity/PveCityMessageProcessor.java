package com.game.myapp.module.pvecity;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGChangeCityMsg.CGChangeCityProto;
import buffer.CGCityAwardMsg.CGCityAwardProto;
import buffer.CGCityInfoMsg.CGCityInfoProto;
import buffer.CGDuplicateCheckMsg.CGDuplicateCheckProto;
import buffer.CGPveCityInfoMsg.CGPveCityInfoProto;
import buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGPveCityInfoProto.class, CGPveCityUnlockProto.class, CGDuplicateCheckProto.class, CGCityInfoProto.class, CGCityAwardProto.class,
		CGChangeCityProto.class,
})
public class PveCityMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGPveCityInfoMsg:
			GameGlobals.pveCityManager.CGPveCityInfo(msgBack, userBean.getUid(), (CGPveCityInfoProto) msg);
			break;
		case MsgType.CGPveCityUnlockMsg:
			GameGlobals.pveCityManager.CGPveCityUnlock(msgBack, userBean.getUid(), (CGPveCityUnlockProto) msg);
			break;
		case MsgType.CGDuplicateCheck:
			GameGlobals.pveCityManager.CGDuplicateCheck(msgBack, userBean.getUid(), (CGDuplicateCheckProto) msg);
			break;
		case MsgType.CGCityInfoMsg:
			GameGlobals.pveCityManager.CGCityInfo(msgBack, userBean.getUid(), (CGCityInfoProto) msg);
			break;
		case MsgType.CGCityAwardMsg:
			GameGlobals.pveCityManager.CGCityAward(msgBack, userBean.getUid(), (CGCityAwardProto) msg);
			break;
		case MsgType.CGChangeCityMsg:
			GameGlobals.pveCityManager.CGChangeCityMsg(msgBack, userBean.getUid(), (CGChangeCityProto) msg);
			break;
//		case MsgType.CGPveCityAttachMsg:
//			GameGlobals.pveCityManager.CGPveCityAttach(msgBack, userBean.getUid(), (CGPveCityAttachProto) msg);
//			break;
//		case MsgType.CGPveCityBuyMsg:
//			GameGlobals.pveCityManager.CGPveCityBuyDec(msgBack, userBean.getUid(), (CGPveCityBuyDecProto) msg);
//			break;
		}
	}
}
