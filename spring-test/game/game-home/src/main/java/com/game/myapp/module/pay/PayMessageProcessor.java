package com.game.myapp.module.pay;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetMonthCardInfoMsg.CGGetMonthCardInfoProto;
import buffer.CGGetPayInfoMsg.CGGetPayInfoProto;
import buffer.CGGetRoleFirstPayInfoMsg.CGGetRoleFirstPayInfoProto;
import buffer.CGReCeiveFirstPayRewardMsg.CGReCeiveFirstPayRewardProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGGetMonthCardInfoProto.class, CGGetPayInfoProto.class, CGGetRoleFirstPayInfoProto.class, CGReCeiveFirstPayRewardProto.class,

})
public class PayMessageProcessor extends HttpMessageProcessor {
	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGGetMonthCardInfoMsg:
			GameGlobals.monthCardManager.CGGetMonthCardInfo(msgBack, userBean.getUid(), (CGGetMonthCardInfoProto) msg);
			break;
		case MsgType.CGGetPayInfoMsg:
			GameGlobals.payManager.CGGetPayInfo(msgBack, userBean.getUid(), (CGGetPayInfoProto) msg);
			break;
		case MsgType.CGGetRoleFirstPayInfoMsg:
			GameGlobals.payManager.CGGetRoleFirstPayInfo(msgBack, userBean.getUid(), (CGGetRoleFirstPayInfoProto) msg);
			break;
		case MsgType.CGReCeiveFirstPayRewardMsg:
			GameGlobals.payManager.CGReceiveFirstPayReward(msgBack, userBean.getUid(), (CGReCeiveFirstPayRewardProto) msg);
			break;
		}
	}
}
