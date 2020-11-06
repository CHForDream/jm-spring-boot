package com.game.common.session;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import com.game.constants.Loggers;
import com.game.constants.UserConstants;
import com.google.protobuf.AbstractMessage.Builder;

import lombok.Data;

@SuppressWarnings("rawtypes")
@Data
public class ChatSessionManager {
	private static final ChatSessionManager instance = new ChatSessionManager();
	private Map<Long, GameSession> sessionMap = new ConcurrentHashMap<Long, GameSession>();

	private LinkedBlockingDeque<Builder> msgs = new LinkedBlockingDeque<Builder>(2000);

	private ChatSessionManager() {
	}

	public static ChatSessionManager getInstance() {
		return instance;
	}

	public void addSession(GameSession session) {
		this.sessionMap.put(session.getUid(), session);
	}

	public LinkedBlockingDeque<Builder> getMsgs() {
		return msgs;
	}

	public void setMsgs(LinkedBlockingDeque<Builder> msgs) {
		this.msgs = msgs;
	}

	public Map<Long, GameSession> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<Long, GameSession> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public GameSession getSession(long uid) {
		return this.sessionMap.get(uid);
	}

	public void noticeAll(Builder gcMsg) {
		// Iterator<GameSession> it = this.sessionMap.values().iterator();
		// while(it.hasNext()){
		// try{
		// GameSession session = it.next();
		// if(session!=null){
		// session.sendMsg(gcMsg);
		// }
		// }catch(Exception ex){
		// ex.printStackTrace();
		// log.error(ex);
		// }
		// }
	}

	public void noticeAllOthers(long fromUid, Builder gcMsg) {
		Iterator<GameSession> it = this.sessionMap.values().iterator();
		while (it.hasNext()) {
			GameSession session = it.next();
			if (session != null) {
				if (session.getUid() == fromUid) {
					continue;
				}
				session.sendMsg(gcMsg);
			}
		}
	}

	public void send(long uid, Builder gcMsg) {
		GameSession session = this.sessionMap.get(uid);
		if (session == null) {
			return;
		}
		session.sendMsg(gcMsg);
	}

	public void off(long uid) {
		this.sessionMap.remove(uid);
	}

	public void addChatAllMsg(Builder gcMsg) {
//		if (msgs.size() > 500) {
//			log.error("chat all room msg size full");
//			return;
//		}
		msgs.offer(gcMsg);
	}

	public void onRun() {
		long now = System.currentTimeMillis();
		Iterator<GameSession> it = this.sessionMap.values().iterator();
		while (it.hasNext()) {
			GameSession session = it.next();
			if (session == null) {
				continue;
			}

			if (now - session.getLastHeartBeatMill() > UserConstants.SESSION_CHAT_TIMEOUT) {
				Loggers.chatLogger.info("Chat session timeout. uid = " + session.getUid());
				it.remove();
				session.close();
			}
		}
	}
}
