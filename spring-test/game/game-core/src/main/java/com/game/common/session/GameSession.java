package com.game.common.session;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.Session;

import org.apache.log4j.Logger;

import com.game.common.msg.MessageLogSample;
import com.game.common.msg.MessageManager;
import com.game.common.player.Player;
import com.game.constants.Loggers;
import com.game.core.websocket.WebSocketCodec;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class GameSession {
	public static final int NETTY_TYPE_BATTLE = 1;
	public static final int NETTY_TYPE_CHAT = 2;
	public static final String SESSION_ATTRIBUTE_KEY = "player";
	public static final AttributeKey<Player> USER_DATA = AttributeKey.valueOf(SESSION_ATTRIBUTE_KEY);

	private static Logger logger = Logger.getLogger(GameSession.class);
	private ChannelHandlerContext connection = null;
	private Session websocketSession = null;
	private Player player = null;

	public GameSession(ChannelHandlerContext context) {
		this.connection = context;

		player = new Player();
		player.setUid(context.hashCode());
		player.setLastHeartBeatMill(System.currentTimeMillis());
		player.setSession(this);

		Attribute<Player> attribute = context.channel().attr(USER_DATA);
		attribute.set(player);
	}

	public GameSession(Session session) {
		this.websocketSession = session;

		player = new Player();
		player.setUid(session.hashCode());
		player.setLastHeartBeatMill(System.currentTimeMillis());
		player.setSession(this);

		session.getUserProperties().put(SESSION_ATTRIBUTE_KEY, player);
	}

	@SuppressWarnings({ "rawtypes" })
	public void sendMsg(Builder builder) {
		if (builder == null) {
			Loggers.msgLogger.warn("Send message is null!");
			return;
		}

		if (connection != null && connection.channel().isActive() && connection.channel().isWritable()) {
			try {
				// 打印通讯信息
				MessageLogSample.printMessageInfo(builder);

				connection.channel().writeAndFlush(builder);
			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("socket error", e);
			}
		}

		if (websocketSession != null && websocketSession.isOpen()) {
			try {
				short msgType = MessageManager.getMsgType(builder);
				Message msg = builder.build();
				ByteBuffer msgBuffer = WebSocketCodec.encode(msg, msgType);
				synchronized (websocketSession) {
					websocketSession.getBasicRemote().sendBinary(msgBuffer);
				}

				// 打印通讯信息
				MessageLogSample.printMessageInfo(msgType, player.getUid(), msg, true);
			} catch (IOException e) {
//				e.printStackTrace();
//				logger.error("websocket error", e);
			}
		}
	}

	public boolean isBattle() {
		return player.getServerType() == GameSession.NETTY_TYPE_BATTLE;
	}

	public ChannelHandlerContext getConnection() {
		return connection;
	}

	public void setConnection(ChannelHandlerContext connection) {
		this.connection = connection;
	}

	public long getUid() {
		return player.getUid();
	}

	public void setUid(long uid) {
		player.setUid(uid);
	}

	public long getLastHeartBeatMill() {
		return getPlayer().getLastHeartBeatMill();
	}

	public void setLastHeartBeatMill(long time) {
		getPlayer().setLastHeartBeatMill(time);
	}

	public void close() {
		if (getConnection() != null) {
			try {
				getConnection().channel().flush();
				getConnection().channel().disconnect();
				getConnection().channel().close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
			try {
				getConnection().disconnect();
				getConnection().close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		if (getWebsocketSession() != null) {
			try {
				getWebsocketSession().close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
	}

	public Session getWebsocketSession() {
		return websocketSession;
	}

	public void setWebsocketSession(Session websocketSession) {
		this.websocketSession = websocketSession;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getRoomId() {
		return player.getRoomId();
	}

	public void setRoomId(int roomId) {
		player.setRoomId(roomId);
	}
}
