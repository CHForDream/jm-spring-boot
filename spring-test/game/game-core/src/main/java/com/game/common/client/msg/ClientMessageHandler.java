package com.game.common.client.msg;

import com.game.common.client.Client;
import com.game.common.client.IClientProcessor;
import com.game.common.msg.MessageManager;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBuilder;
import com.google.protobuf.GeneratedMessage;

public class ClientMessageHandler {
	public static void handleMsg(Client client, MsgBuilder msgBuilder) {
		IClientProcessor clientProcessor = MessageManager.getInstance().getClientProcessor(msgBuilder.getMsgType());
		Class<? extends GeneratedMessage> clientProtoClass = MessageManager.getInstance().getClientProtoClass(msgBuilder.getMsgType());
		if (clientProcessor == null || clientProtoClass == null) {
			Loggers.clientLogger.error("Erorr msgType: " + msgBuilder.getMsgType());
			return;
		}

		// 处理消息
		clientProcessor.process(client, msgBuilder.getMsgType(), (GeneratedMessage) msgBuilder.getBuilder().build());
	}
}
