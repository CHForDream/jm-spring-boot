package com.game.core.websocket;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import com.game.common.msg.MessageLogSample;
import com.game.common.player.Player;
import com.game.common.session.GameSession;
import com.game.common.thread.GameThreadPoolManager;
import com.game.common.thread.MessageExecuteRunnable;
import com.game.constants.Loggers;
import com.game.core.netty.MessageWrapper;
import com.game.global.Globals;

@ServerEndpoint(value = "/ws/webchat")
public class ChatWebSockethandler extends BinaryWebSocketHandler {
	private Logger log = Logger.getLogger(this.getClass());

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnMessage
	public void onMessage(byte[] bytes, Session websocket) {
		try {
			MessageWrapper msgWrapper = WebSocketCodec.decode(bytes);

			Player player = null;
			GameSession session = null;
			if (websocket.getUserProperties().containsKey(GameSession.SESSION_ATTRIBUTE_KEY)) {
				player = (Player) websocket.getUserProperties().get(GameSession.SESSION_ATTRIBUTE_KEY);
				player.setLastHeartBeatMill(System.currentTimeMillis());
				session = player.getSession();
			} else {
				session = new GameSession(websocket);
				player = session.getPlayer();
				player.setServerType(GameSession.NETTY_TYPE_CHAT);
			}

			// 打印通讯信息
			MessageLogSample.printMessageInfo(msgWrapper.getMsgType(), player.getUid(), msgWrapper.getMsg(), false);

			GameThreadPoolManager.getInstance()
					.chatExecute(new MessageExecuteRunnable(session, msgWrapper.getProcessor(), msgWrapper.getMsgType(), msgWrapper.getMsg()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		if (session.getUserProperties().containsKey(GameSession.SESSION_ATTRIBUTE_KEY)) {
			Player player = (Player) session.getUserProperties().get(GameSession.SESSION_ATTRIBUTE_KEY);
			log.info("chat client(ws) is closed: uid = " + player.getUid());
			Globals.getChatSessionManager().getSessionMap().remove(player.getUid());
		} else {
			log.info("chat client(ws) is closed: No user data!");
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		if (session.getUserProperties().containsKey(GameSession.SESSION_ATTRIBUTE_KEY)) {
			Player player = (Player) session.getUserProperties().get(GameSession.SESSION_ATTRIBUTE_KEY);
			Loggers.serverLogger.info(error.getMessage() + " uid:" + player.getUid());
		} else {
			Loggers.serverLogger.info(error.getMessage());
		}
	}
}
