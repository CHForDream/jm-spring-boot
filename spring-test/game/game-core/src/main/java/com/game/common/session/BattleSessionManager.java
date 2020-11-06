package com.game.common.session;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.constants.Loggers;
import com.game.constants.UserConstants;

public class BattleSessionManager {
	private static final BattleSessionManager instance = new BattleSessionManager();
	private Map<Long, GameSession> sessionMap = new ConcurrentHashMap<Long, GameSession>();

	private BattleSessionManager() {
	}

	public static BattleSessionManager getInstance() {
		return instance;
	}

	public void addSession(GameSession session) {
		this.sessionMap.put(session.getUid(), session);
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

	public void onRun() {
		Iterator<GameSession> it = this.sessionMap.values().iterator();
		while (it.hasNext()) {
			GameSession session = it.next();
			if (session != null) {
				if (System.currentTimeMillis() - session.getLastHeartBeatMill() > UserConstants.SESSION_BATTLE_TIMEOUT) {
					Loggers.battleLogger.info("Battle session timeout. uid = " + session.getUid());
					it.remove();
					session.close();
					continue;
				}
			}
		}
	}

	public static void main(String[] args) {

	}
}
