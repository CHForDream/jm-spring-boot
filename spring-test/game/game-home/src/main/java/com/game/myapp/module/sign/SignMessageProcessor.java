package com.game.myapp.module.sign;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetSevenSignStatusMsg.CGGetSevenSignStatusProto;
import buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGGetSevenSignStatusProto.class, CGReceiveSevenSignRewardProto.class,

})
public class SignMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGGetSevenSignStatusMsg:
			GameGlobals.signManager.CGGetSevenSignStatusMsg(msgBack, userBean.getUid());
			break;
		case MsgType.CGReceiveSevenSignRewardMsg:
			GameGlobals.signManager.CGReceiveSevenSignRewardMsg(msgBack, userBean.getUid(), (CGReceiveSevenSignRewardProto) msg);
			break;
		}
	}
}
