package com.game.myapp.module.battle;

import org.springframework.stereotype.Component;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.IProcessor;
import com.game.common.session.GameSession;
import com.game.common.user.UserBean;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBack;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.util.MessageUtil;
import com.google.protobuf.GeneratedMessage;

import buffer.CGBattlePveFailedAddStepMsg.CGBattlePveFailedAddStepProto;
import buffer.CGBattlePveItemList.CGBattlePveItemListProto;
import buffer.CGBattlePveResult.CGBattlePveResultProto;
import buffer.CGBattlePveStart.CGBattlePveStartProto;
import buffer.CGConnectMsg.CGConnectProto;
import buffer.CGPveBuyPowerMsg.CGPveBuyPowerProto;

@Component
@ProcessorDefine(protos = {
		// Http
		CGBattlePveStartProto.class, CGBattlePveResultProto.class, CGBattlePveItemListProto.class, CGBattlePveFailedAddStepProto.class,

		CGPveBuyPowerProto.class,

		// Socket
		CGConnectProto.class,

})

public class BattleMessageProcessor implements IProcessor {

	@Override
	public void logical(MsgBack msgBack, UserBean userBean, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGPveBuyPowerMsg:
			GameGlobals.pveManager.CGPveBuyPower(msgBack, userBean.getUid(), (CGPveBuyPowerProto) msg);
			break;
		case MsgType.CGBattlePveStart:
			GameGlobals.pveManager.CGBattlePveStart(msgBack, userBean, (CGBattlePveStartProto) msg);
			break;
		case MsgType.CGBattlePveResult:
			GameGlobals.pveManager.CGBattlePveResult(msgBack, userBean, (CGBattlePveResultProto) msg);
			break;
		case MsgType.CGBattlePveFailedAddStepMsg:
			GameGlobals.pveManager.CGBattlePveFailedAddStep(msgBack, userBean, (CGBattlePveFailedAddStepProto) msg);
			break;
		case MsgType.CGBattlePveItemList:
			GameGlobals.pveManager.CGBattlePveItemList(msgBack, userBean, (CGBattlePveItemListProto) msg);
			break;
		default:
			break;
		}
	}

	@Override
	public void process(GameSession session, int msgType, GeneratedMessage msg) {
		switch (msgType) {
		case MsgType.CGConnectMsg:
			connect(session, (CGConnectProto) msg);
			return;
		}
	}

	private void connect(GameSession session, CGConnectProto msg) {
		long uid = msg.getUid();
		session.setUid(uid);
		Globals.getGameSessionManager().addSession(session);

		MessageUtil.sendCommonBack(session, msg.getMsgType(), 0, null);
	}
}
