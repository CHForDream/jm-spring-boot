package com.game.myapp.module.achieve;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetAchieveListMsg.CGGetAchieveListProto;
import buffer.CGGetAchieveRewardMsg.CGGetAchieveRewardProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGGetAchieveListProto.class, CGGetAchieveRewardProto.class,

})
public class AchieveMessageProcessor extends HttpMessageProcessor {
	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGGetAchieveListMsg:
			GameGlobals.achieveManager.CGGetAchieveListMsg(msgBack, userBean.getUid(), (CGGetAchieveListProto) msg);
			break;
		case MsgType.CGGetAchieveRewardMsg:
			GameGlobals.achieveManager.CGGetAchieveRewardMsg(msgBack, userBean.getUid(), (CGGetAchieveRewardProto) msg);
			break;
		}
	}
}
