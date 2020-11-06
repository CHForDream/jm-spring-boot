package com.game.core.handler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.protobuf.AbstractMessage.Builder;

@SuppressWarnings("rawtypes")
public class MsgBack {
	private Logger log = Logger.getLogger(this.getClass());
	private List<MsgBuilder> builderList = new ArrayList<MsgBuilder>();

	public List<MsgBuilder> getBuilder() {
		return builderList;
	}

	public int getTotalSize() {
		int size = 0;
		for (MsgBuilder temp : this.builderList) {
			size += (temp.getBuilder().build().toByteArray().length + 4);
		}
		return size;
	}

	public void addBuilder(Builder builder) {
		try {
			Method getMethod = builder.getClass().getMethod("getMsgType");
			Object msgType = getMethod.invoke(builder);
			MsgBuilder msgBuilder = new MsgBuilder();
			msgBuilder.setMsgType(Short.parseShort(msgType.toString()));
			msgBuilder.setBuilder(builder);
			builderList.add(msgBuilder);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
		}
	}

}
