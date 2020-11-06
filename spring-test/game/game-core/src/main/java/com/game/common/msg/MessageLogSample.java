package com.game.common.msg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.game.constants.Loggers;
import com.game.constants.MsgType;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Message;

@SuppressWarnings("rawtypes")
public class MessageLogSample {
	// 消息采样取模基数
	public static final int SAMPLE_COUNT_BASE = 3000;
	// 消息采样计数
	private static int SAMPLE_COUNT = 0;

	/**
	 * 打印通讯消息日志
	 * 
	 * @param msgType
	 * @param uid
	 * @param messageInfo
	 * @param isSend
	 */
	public static void printMessageInfo(int msgType, long uid, Message msg, boolean isSend) {
		if (!isPrintMsg(msgType)) {
			return;
		}

		try {
			StringBuffer buffer = new StringBuffer();
			if (isSend) {
				buffer.append("[Send]");
			} else {
				buffer.append("[Receive]");
			}
			String msgContent = msg.toString().replaceAll("\n", ";").replaceAll("\\s+", "");
//			if (msgContent.length() > 500) {
//				msgContent = msgContent.substring(0, 500) + "...";
//			}
			buffer.append("[" + msgType + "]").append("[" + uid + "]").append("[" + msg.getClass().getSimpleName() + "]").append(msgContent);
			Loggers.msgLogger.info(buffer.toString());
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public static void printMessageInfo(Builder builder) {
		try {
			Method getMsgTypeMethod = builder.getClass().getMethod("getMsgType");
			short msgType = Short.parseShort(getMsgTypeMethod.invoke(builder).toString());
			Message msg = builder.build();
			// TODO uid
			long uid = 0L;
			MessageLogSample.printMessageInfo(msgType, uid, msg, true);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static boolean isPrintMsg(int msgType) {
		switch (msgType) {
		case MsgType.CGHttpBeatMsg: // GCHttpBeatMsg
		case MsgType.CGHeartBeatMsg:
		case MsgType.CGGetRedHatInfoMsg:// GCGetRedHatInfoMsg
		case MsgType.GCCommonResponseMsg:
			SAMPLE_COUNT++;
			if (SAMPLE_COUNT % SAMPLE_COUNT_BASE != 0) {
				return false;
			}
		default:
			return true;
		}
	}
}
