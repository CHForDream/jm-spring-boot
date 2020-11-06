package com.game.myapp.module.share;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGShareInfoMsg.CGShareInfoProto;
import buffer.CGShareMsg.CGShareProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGShareInfoProto.class, CGShareProto.class,

//		CGShareGetRewardProto.class,

})
public class ShareMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGShareInfoMsg:
			GameGlobals.shareManager.CGShareInfo(msgBack, userBean);
			break;
		case MsgType.CGShareMsg:
			GameGlobals.shareManager.CGShare(msgBack, userBean, (CGShareProto) msg);
			break;
//			case MsgType.CGShareGetRewardMsg:
//				return GameGlobals.shareManager.CGShareGetReward(userBean, (CGShareGetRewardProto) msg);
		}
	}
}
