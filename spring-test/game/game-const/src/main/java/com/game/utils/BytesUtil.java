package com.game.utils;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;

import com.google.protobuf.AbstractMessage.Builder;

public class BytesUtil {
	public static byte[] shortToByteArray(short value) {
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.putShort(value);
		byte[] bytes = buffer.array();
		return bytes;
	}

	public static byte[] longToByteArray(long value) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(value);
		byte[] bytes = buffer.array();
		return bytes;
	}

	public static byte[] intToByteArray(int value) {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(value);
		byte[] bytes = buffer.array();
		return bytes;
	}

	@SuppressWarnings("rawtypes")
	public static short getMsgType(Builder builder) throws Exception {
		Method getMoney;
		getMoney = builder.getClass().getMethod("getMsgType");
		Object money2 = getMoney.invoke(builder);
		return Short.parseShort(money2.toString());
	}
}
