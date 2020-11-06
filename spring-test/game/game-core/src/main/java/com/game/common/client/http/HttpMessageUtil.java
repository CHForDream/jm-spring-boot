package com.game.common.client.http;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

import com.game.common.msg.MessageManager;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBuilder;
import com.game.utils.BytesUtil;
import com.google.common.collect.Lists;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.GeneratedMessage;

@SuppressWarnings("rawtypes")
public class HttpMessageUtil {
	public static byte[] encode(long uid, int sessionKey, int msgType, Builder builder) {
		byte[] uidArray = BytesUtil.longToByteArray(uid);
		byte[] keyArray = BytesUtil.intToByteArray(sessionKey);
		byte[] req = builder.build().toByteArray();
		byte[] respback = new byte[req.length + 12 + 2 + 2];
		byte[] lengthArry = BytesUtil.shortToByteArray((short) req.length);
		byte[] typeArry = BytesUtil.shortToByteArray((short) msgType);

		System.arraycopy(uidArray, 0, respback, 0, uidArray.length);
		System.arraycopy(keyArray, 0, respback, 8, keyArray.length);
		System.arraycopy(lengthArry, 0, respback, 12, lengthArry.length);
		System.arraycopy(typeArry, 0, respback, 14, typeArry.length);
		System.arraycopy(req, 0, respback, 16, req.length);
		return respback;
	}

	public static List<MsgBuilder> decode(byte[] bytes) {
		List<MsgBuilder> resultList = Lists.newArrayList();

		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		while (buffer.hasRemaining()) {
//			int remaining = buffer.remaining();
//			Loggers.clientLogger.info("decode(): remaining = " + remaining);
			short length = buffer.getShort();
			short msgType = buffer.getShort();
//			Loggers.clientLogger.info("decode(): length = " + length + ", msgType = " + msgType);

			byte[] proto_bytes = new byte[length];
			buffer.get(proto_bytes);

			MsgBuilder msgBuilder = new MsgBuilder();
			msgBuilder.setMsgType(msgType);

			// 根据msgType, 将proto_bytes转为
			Class<? extends GeneratedMessage> messageClass = MessageManager.getInstance().getClientProtoClass(msgType);
			if (messageClass == null) {
				Loggers.clientLogger.error("decode(): Erorr msgType: " + msgType);
				continue;
			}

			try {
				Method method = messageClass.getMethod("parseFrom", byte[].class);
				GeneratedMessage msg = (GeneratedMessage) method.invoke(null, proto_bytes);
				msgBuilder.setBuilder((Builder) msg.toBuilder());
				resultList.add(msgBuilder);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return resultList;
	}
}
