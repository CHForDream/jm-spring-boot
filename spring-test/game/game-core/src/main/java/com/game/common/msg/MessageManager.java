package com.game.common.msg;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.annotation.ProcessorDefine;
import com.game.common.client.IClientProcessor;
import com.game.constants.MsgType;
import com.google.common.collect.Maps;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.GeneratedMessage;

@SuppressWarnings("rawtypes")
public class MessageManager {
	private static final MessageManager instance = new MessageManager();
	private static Logger logger = Logger.getLogger(MessageManager.class);

	public Map<Integer, IProcessor> processorMap = Maps.newHashMap();
	public Map<Integer, Class<? extends GeneratedMessage>> protoClassMap = Maps.newHashMap();

	public Map<Integer, IClientProcessor> clientProcessorMap = Maps.newHashMap();
	public Map<Integer, Class<? extends GeneratedMessage>> clientProtoClassMap = Maps.newHashMap();

	private MessageManager() {
	}

	public static MessageManager getInstance() {
		return instance;
	}

	public static short getMsgType(Builder builder) {
		try {
			Method getMsgTypeMethod = builder.getClass().getMethod("getMsgType");
			Object msgType = getMsgTypeMethod.invoke(builder);
			return Short.parseShort(msgType.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return -1;
	}

	public static boolean isSessionMessage(int msgType) {
		switch (msgType) {
		case MsgType.CGConnectMsg:// 链接战斗服
			return true;
		default:
			return false;
		}
	}

	public void registMsg(Map<String, Object> objectMap) {
		for (Object beanClassName : objectMap.values()) {
			ProcessorDefine myClassAnnotation = beanClassName.getClass().getAnnotation(ProcessorDefine.class);
			if (myClassAnnotation.canUse()) {
				try {
					Class<? extends GeneratedMessage>[] protos = myClassAnnotation.protos();
					Object instance = beanClassName.getClass().newInstance();
					if (instance instanceof IProcessor) {
						IProcessor processor = (IProcessor) instance;
						for (Class<? extends GeneratedMessage> clazz : protos) {
							Method builder = clazz.getMethod("newBuilder");
							Object temp = builder.invoke(null);
							int msgType = getMsgType((Builder) temp);
							processorMap.put(msgType, processor);
							protoClassMap.put(msgType, clazz);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					logger.error(ex);
					System.exit(0);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void registClientMsg(List<Class<?>> classList) {
		for (Class clazz : classList) {
			ProcessorDefine myClassAnnotation = (ProcessorDefine) clazz.getAnnotation(ProcessorDefine.class);
			if (myClassAnnotation.canUse()) {
				try {
					Class<? extends GeneratedMessage>[] protos = myClassAnnotation.protos();
					Object instance = clazz.newInstance();
					if (instance instanceof IClientProcessor) {
						IClientProcessor processor = (IClientProcessor) instance;
						for (Class<? extends GeneratedMessage> msgClass : protos) {
							Method builder = msgClass.getMethod("newBuilder");
							Object temp = builder.invoke(null);
							int msgType = getMsgType((Builder) temp);
							clientProcessorMap.put(msgType, processor);
							clientProtoClassMap.put(msgType, msgClass);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					logger.error(ex);
					System.exit(0);
				}
			}
		}
	}

	public IProcessor getProcessor(int msgType) {
		return processorMap.get(msgType);
	}

	public Class<? extends GeneratedMessage> getProtoClass(int msgType) {
		return protoClassMap.get(msgType);
	}

	public IClientProcessor getClientProcessor(int msgType) {
		return clientProcessorMap.get(msgType);
	}

	public Class<? extends GeneratedMessage> getClientProtoClass(int msgType) {
		return clientProtoClassMap.get(msgType);
	}
}
