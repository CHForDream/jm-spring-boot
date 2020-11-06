package com.game.myapp.module.guide;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGUpdateRoleGuideMsg.CGUpdateRoleGuideProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGUpdateRoleGuideProto.class,

})
public class GuideMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGUpdateRoleGuideMsg:
			GameGlobals.guideManager.CGUpdateRoleGuide(msgBack, userBean.getUid(), (CGUpdateRoleGuideProto) msg);
			break;
		}
	}
}
