package com.game.myapp.util;

import com.game.common.session.GameSession;

import buffer.GCServerBackMsg;

public class MessageUtil {
	public static void sendCommonBack(GameSession session, int msgType, int param1, String param2) {
		GCServerBackMsg.GCServerBackProto.Builder builder = GCServerBackMsg.GCServerBackProto.newBuilder();
		builder.setBackType(msgType);
		builder.setParam1(param1);
		if (param2 != null) {
			builder.setParam2(param2);
		}
		session.sendMsg(builder);
	}
}
