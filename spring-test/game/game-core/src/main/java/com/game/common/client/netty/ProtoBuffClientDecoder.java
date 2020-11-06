package com.game.common.client.netty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.game.common.msg.MessageManager;
import com.game.constants.Loggers;
import com.game.core.netty.MessageWrapper;
import com.google.protobuf.GeneratedMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ProtoBuffClientDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext content, ByteBuf buf, List<Object> result) throws Exception {
		int msgType = buf.readShort();
		// 根据msgType, 将proto_bytes转为
		Class<? extends GeneratedMessage> messageClass = MessageManager.getInstance().getClientProtoClass(msgType);
		if (messageClass == null) {
			Loggers.clientLogger.error("decode(): Erorr msgType: " + msgType);
			return;
		}

		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		// Loggers.clientLogger.info("decode. pack.length = " + (bytes.length + 4) + ", msg.length = " + bytes.length);
		try {
			Method method = messageClass.getMethod("parseFrom", byte[].class);
			GeneratedMessage msg = (GeneratedMessage) method.invoke(null, bytes);
			result.add(new MessageWrapper(msgType, null, msg));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
