package com.game.myapp.module.rank;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGRankInfoMsg.CGRankInfoProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGRankInfoProto.class,

})
public class RankMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGRankInfoMsg:
			GameGlobals.rankService.CGRankInfo(msgBack, userBean, (CGRankInfoProto) msg);
			break;
		}
	}
}
