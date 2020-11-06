package com.game.core.netty;

import com.game.utils.BytesUtil;
import com.google.protobuf.AbstractMessage.Builder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@SuppressWarnings("rawtypes")
public class ProtoBuffEncoder extends MessageToByteEncoder<com.google.protobuf.AbstractMessage.Builder> {

	@Override
	protected void encode(ChannelHandlerContext arg0, Builder builder, ByteBuf out) throws Exception {
		int msgType = BytesUtil.getMsgType(builder);
		byte[] req = builder.build().toByteArray();
		ByteBuf directBuf = Unpooled.directBuffer(req.length + 4);
		directBuf.writeShort((short) req.length);
		directBuf.writeShort((short) msgType);
		directBuf.writeBytes(req);

		byte[] bytes = new byte[directBuf.readableBytes()];
		directBuf.readBytes(bytes);
		directBuf.release();
		out.writeBytes(bytes);
	}
}
