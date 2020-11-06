package com.game.common.client.http;

import java.util.List;

import com.game.common.client.Client;
import com.game.common.client.msg.ClientMessageHandler;
import com.game.constants.MsgType;
import com.game.core.handler.MsgBuilder;
import com.game.utils.HttpUtil;
import com.google.protobuf.AbstractMessage.Builder;

@SuppressWarnings("rawtypes")
public class HttpClientSession {
	private Client client;
	private long uid = 0;
	private int sessionKey = 999;

	public HttpClientSession(Client client) {
		this.client = client;
	}

	public void sendHttpMessage(String url, int msgType, Builder builder) {
		byte[] messageBytes = HttpMessageUtil.encode(uid, sessionKey, msgType, builder);

		long sendTime = System.currentTimeMillis();
		byte[] httpPostByteArray = HttpUtil.httpPostByteArray(url, messageBytes);

		// 记录消息发送和接收消耗的时间
		if (msgType != MsgType.CGHttpBeatMsg) {
			client.onHttpMessageSent(System.currentTimeMillis() - sendTime);
		}

		List<MsgBuilder> resultList = HttpMessageUtil.decode(httpPostByteArray);
		for (MsgBuilder msgBuilder : resultList) {
			// 处理接收的消息
			ClientMessageHandler.handleMsg(client, msgBuilder);
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(int sessionKey) {
		this.sessionKey = sessionKey;
	}
}
