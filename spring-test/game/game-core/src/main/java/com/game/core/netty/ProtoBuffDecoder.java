package com.game.core.netty;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.common.msg.IProcessor;
import com.game.constants.Loggers;
import com.game.global.Globals;
import com.google.protobuf.GeneratedMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ProtoBuffDecoder extends ByteToMessageDecoder {
	private Logger log = Logger.getLogger(ProtoBuffDecoder.class);

	@Override
	protected void decode(ChannelHandlerContext content, ByteBuf buf, List<Object> result) throws Exception {
//		Loggers.battleLogger.info("decode. pack.length = " + buf.readableBytes());
		int msgType = buf.readShort();
		IProcessor processor = Globals.getMessageManager().getProcessor(msgType);
		if (processor == null) {
			// 异常的消息, 把缓冲池的数据都清掉, 否则会反复的解析msgType, 并且会影响后面消息的解析
			byte[] bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);

			log.error("ProtoBuffDecoder is error,processor is null,msgtype:" + msgType);
			return;
		}
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);

		// 去掉processor的toMsg()依赖, 换成反射调用具体proto消息类的parseFrom方法
//		GeneratedMessage msg = (GeneratedMessage) processor.toMsg(bytes);
		Class<? extends GeneratedMessage> messageClass = Globals.getMessageManager().getProtoClass(msgType);
		if (processor == null || messageClass == null) {
			Loggers.serverLogger.error("ProtoBuffDecoder.decode(): Erorr msgType: " + msgType);
			return;
		}

		try {
			Method method = messageClass.getMethod("parseFrom", byte[].class);
			GeneratedMessage msg = (GeneratedMessage) method.invoke(null, bytes);
			result.add(new MessageWrapper(msgType, processor, msg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
