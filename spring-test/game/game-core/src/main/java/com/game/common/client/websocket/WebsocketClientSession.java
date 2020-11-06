package com.game.common.client.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.game.common.client.Client;
import com.game.common.client.http.HttpMessageUtil;
import com.game.common.client.msg.ClientMessageHandler;
import com.game.common.msg.MessageManager;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBuilder;
import com.game.core.websocket.WebSocketCodec;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Message;

@SuppressWarnings({ "rawtypes" })
public class WebsocketClientSession {
	private Client client;
	private WebSocketClient session = null;

	public WebsocketClientSession(Client client) {
		this.client = client;
	}

	public void connect(String url) {
		if (session != null) {
			if (session.isOpen()) {
//				Loggers.clientLogger.info("WebSocket is opening");
			} else {
//				Loggers.clientLogger.info("WebSocket reconnect");
				session.reconnect();
			}
			return;
		}

		synchronized (client) {
			if (session != null) {
				if (session.isOpen()) {
//					Loggers.clientLogger.info("WebSocket is opening");
				} else {
//					Loggers.clientLogger.info("WebSocket reconnect");
					session.reconnect();
				}
				return;
			}

			try {
				URI uri = new URI(url);
				this.session = new WebSocketClient(uri) {
					@Override
					public void onOpen(ServerHandshake arg0) {
//						Loggers.clientLogger.info("WebSocket onOpen: clientId = " + client.getId());
						client.onSocketConnect(Client.SOCKET_TYPE_WEBSOCKET_BATLLE);
					}

					@Override
					public void onMessage(ByteBuffer bytes) {
						messageReceived(bytes);
					}

					@Override
					public void onMessage(String arg0) {
//						Loggers.clientLogger.info("WebSocket onMessage:String");
					}

					@Override
					public void onError(Exception arg0) {
//						Loggers.clientLogger.info("WebSocket onError");
						client.onSocketError(Client.SOCKET_TYPE_WEBSOCKET_BATLLE);
					}

					@Override
					public void onClose(int arg0, String arg1, boolean arg2) {
//						Loggers.clientLogger.info("WebSocket onClose");
						client.onSocketClose(Client.SOCKET_TYPE_WEBSOCKET_BATLLE);
					}
				};
				session.connect();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMsg(Builder builder) {
		if (builder == null) {
			Loggers.clientLogger.warn("Send message is null!");
			return;
		}

		if (session == null) {
			Loggers.clientLogger.warn("WebSocket session is null! Please connect webSocket first!");
			return;
		}

		if (session.isOpen()) {
			short msgType = MessageManager.getMsgType(builder);
			Message msg = builder.build();
			ByteBuffer msgBuffer = WebSocketCodec.encodeWithoutLengthHeader(msg, msgType);
			synchronized (session) {
				session.send(msgBuffer);
			}

//			// 打印通讯信息
//			if (ClientMessageLogSample.isPrintMsg(msgType)) {
//				ClientMessageLogSample.printMessageInfo(msgType, client.getUid(), msg, true);
//			}
		} else {
			Loggers.clientLogger.warn("Send message is null!");
		}
	}

	public void messageReceived(ByteBuffer bytes) {
		List<MsgBuilder> resultList = HttpMessageUtil.decode(bytes.array());
		for (MsgBuilder msgBuilder : resultList) {
			ClientMessageHandler.handleMsg(client, msgBuilder);
		}
	}

	public void close() {
		synchronized (client) {
			if (session != null) {
				session.close();
			}
		}
	}

	public boolean isConnect() {
		return session != null && session.isOpen();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public WebSocketClient getSession() {
		return session;
	}

	public void setSession(WebSocketClient session) {
		this.session = session;
	}
}
