package com.game.myapp.module.daytask;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.HttpMessageProcessor;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.myapp.GameGlobals;
import com.google.protobuf.GeneratedMessage;

import buffer.CGGetDayTaskListMsg.CGGetDayTaskListProto;
import buffer.CGGetDayTaskRewardMsg.CGGetDayTaskRewardProto;

@Component
@ProcessorDefine(protos = {
		// HTTP
		CGGetDayTaskListProto.class, CGGetDayTaskRewardProto.class,

})
public class DayTaskMessageProcessor extends HttpMessageProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGGetDayTaskListMsg:
			GameGlobals.taskManager.CGGetDayTaskList(msgBack, userBean.getUid(), (CGGetDayTaskListProto) msg);
			break;
		case MsgType.CGGetDayTaskRewardMsg:
			GameGlobals.taskManager.CGGetDayTaskReward(msgBack, userBean.getUid(), (CGGetDayTaskRewardProto) msg);
			break;
		}
	}
}
