package com.game.core.websocket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

import com.game.common.msg.IProcessor;
import com.game.constants.Loggers;
import com.game.core.netty.MessageWrapper;
import com.game.global.Globals;
import com.game.utils.BytesUtil;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

public class WebSocketCodec {
	public static MessageWrapper decode(byte[] bytes) {
		byte[] typeArray = { bytes[0], bytes[1] };
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer = ByteBuffer.wrap(typeArray);
		int msgType = buffer.getShort();

		byte[] proto_bytes = new byte[bytes.length - 2];
		System.arraycopy(bytes, 2, proto_bytes, 0, proto_bytes.length);
		IProcessor processor = Globals.getMessageManager().getProcessor(msgType);
//		GeneratedMessage msg = (GeneratedMessage) processor.toMsg(proto_bytes);
		Class<? extends GeneratedMessage> messageClass = Globals.getMessageManager().getProtoClass(msgType);
		if (processor == null || messageClass == null) {
			Loggers.serverLogger.error("WebSocketConn.onMessage(): Erorr msgType: " + msgType);
			return null;
		}
		GeneratedMessage msg = null;
		try {
			Method method = messageClass.getMethod("parseFrom", byte[].class);
			msg = (GeneratedMessage) method.invoke(null, proto_bytes);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return new MessageWrapper(msgType, processor, msg);
	}

	public static ByteBuffer encode(Message msg, short msgType) {
		byte[] req = msg.toByteArray();
		byte[] respback = new byte[req.length + 2 + 2];
		byte[] lengthArry = BytesUtil.shortToByteArray((short) req.length);
		byte[] typeArry = BytesUtil.shortToByteArray(msgType);
		respback[0] = lengthArry[0];
		respback[1] = lengthArry[1];
		respback[2] = typeArry[0];
		respback[3] = typeArry[1];
		System.arraycopy(req, 0, respback, 4, req.length);
		return ByteBuffer.wrap(respback);
	}

	public static ByteBuffer encodeWithoutLengthHeader(Message msg, short msgType) {
		byte[] req = msg.toByteArray();
		byte[] respback = new byte[req.length + 2];
		byte[] typeArry = BytesUtil.shortToByteArray(msgType);
		respback[0] = typeArry[0];
		respback[1] = typeArry[1];
		System.arraycopy(req, 0, respback, 2, req.length);
		return ByteBuffer.wrap(respback);
	}
}
