package com.game.common.player;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.game.common.session.GameSession;
import com.game.constants.Loggers;
import com.google.protobuf.AbstractMessage.Builder;

@SuppressWarnings({ "rawtypes" })
public class Player {
	private int serverType = 0;
	private long uid = 0;
	private int roomId = 0;
	private long lastHeartBeatMill = 0;

	private GameSession session = null;
	/** 玩家的消息队列 */
	private BlockingQueue<Runnable> msgQueue;

	public Player() {
		msgQueue = new LinkedBlockingQueue<Runnable>();
	}

	public Player(GameSession session) {
		this.session = session;

		msgQueue = new LinkedBlockingQueue<Runnable>();
	}

	public void sendMsg(Builder builder) {
		session.sendMsg(builder);
	}

	public void put(Runnable msg) {
		if (msg == null) {
			return;
		}
		try {
			msgQueue.put(msg);
		} catch (InterruptedException e) {
			Loggers.msgLogger.error("", e);
		}
	}

	public Runnable get() {
		if (isEmpty()) {
			return null;
		}

		try {
			return msgQueue.take();
		} catch (InterruptedException e) {
			Loggers.msgLogger.error("", e);
			return null;
		}
	}

	public boolean isEmpty() {
		return msgQueue.isEmpty();
	}

	public int getServerType() {
		return serverType;
	}

	public void setServerType(int serverType) {
		this.serverType = serverType;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public long getLastHeartBeatMill() {
		return lastHeartBeatMill;
	}

	public void setLastHeartBeatMill(long lastHeartBeatMill) {
		this.lastHeartBeatMill = lastHeartBeatMill;
	}

	public GameSession getSession() {
		return session;
	}

	public void setSession(GameSession session) {
		this.session = session;
	}
}
