package com.game.common.client;

import com.game.common.client.http.HttpClientSession;
import com.game.common.client.netty.NettyClientSession;
import com.game.common.client.websocket.WebsocketClientSession;

public abstract class Client {
	public static final int SOCKET_TYPE_NETTY_BATTLE = 1;
	public static final int SOCKET_TYPE_NETTY_CHAT = 2;
	public static final int SOCKET_TYPE_WEBSOCKET_BATLLE = 3;

	/** 客户端Id */
	private int id;

	/** 游戏服会话(Http) */
	private HttpClientSession httpSession;
	/** 战斗服会话(Socket) */
	private NettyClientSession socketBattleSession;
	/** 聊天服会话(Socket) */
	private NettyClientSession socketChatSession;

	/** 战斗服会话(WebSocket) */
	private WebsocketClientSession websocketBattleSession;

	public Client(int id) {
		this.id = id;

		this.httpSession = new HttpClientSession(this);
		this.socketBattleSession = new NettyClientSession(this);
		this.socketChatSession = new NettyClientSession(this);
		this.websocketBattleSession = new WebsocketClientSession(this);
	}

	public abstract void onSocketConnect(int type);

	public abstract void onSocketError(int type);

	public abstract void onSocketClose(int type);

	public abstract void onHttpMessageSent(long costTime);

	public void closeBattleConnection() {
		if (socketBattleSession.isConnect()) {
			socketBattleSession.getContext().close();
		}
		if (websocketBattleSession.isConnect()) {
			websocketBattleSession.close();
		}
	}

	public long getUid() {
		return httpSession.getUid();
	}

	public void setUid(long uid) {
		httpSession.setUid(uid);
	}

	public int getSessionKey() {
		return httpSession.getSessionKey();
	}

	public void setSessionKey(int sessionKey) {
		httpSession.setSessionKey(sessionKey);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HttpClientSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpClientSession httpSession) {
		this.httpSession = httpSession;
	}

	public NettyClientSession getSocketBattleSession() {
		return socketBattleSession;
	}

	public void setSocketBattleSession(NettyClientSession socketBattleSession) {
		this.socketBattleSession = socketBattleSession;
	}

	public NettyClientSession getSocketChatSession() {
		return socketChatSession;
	}

	public void setSocketChatSession(NettyClientSession socketChatSession) {
		this.socketChatSession = socketChatSession;
	}

	public WebsocketClientSession getWebsocketBattleSession() {
		return websocketBattleSession;
	}

	public void setWebsocketBattleSession(WebsocketClientSession websocketBattleSession) {
		this.websocketBattleSession = websocketBattleSession;
	}
}
