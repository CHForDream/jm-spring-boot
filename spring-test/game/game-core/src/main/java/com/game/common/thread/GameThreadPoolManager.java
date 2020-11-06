/**
 * 
 */
package com.game.common.thread;

/**
 * @author pky
 *
 */
public class GameThreadPoolManager {

	/** 战斗线程池 */
	private GameThreadPoolExecotor battleThreadPool;
	/** 聊天线程池 */
	private GameThreadPoolExecotor chatThreadPool;

	private GameThreadPoolManager() {
		int coreSize = Runtime.getRuntime().availableProcessors();
		if (battleThreadPool == null) {
			this.battleThreadPool = new GameThreadPoolExecotor(coreSize + 1, coreSize * 20, "battle");
		}
		if (chatThreadPool == null) {
			this.chatThreadPool = new GameThreadPoolExecotor(coreSize + 1, coreSize * 20, "chat");
		}
	}

	public static GameThreadPoolManager getInstance() {
		return ThreadPoolManagerInner.INSTANCE;
	}

	public final void battleExecute(Runnable task) {
		this.battleThreadPool.execute(task);
	}

	public final void chatExecute(Runnable task) {
		this.chatThreadPool.execute(task);
	}

	public final void execute(Runnable task) {
		this.chatThreadPool.execute(task);
	}

	public void shutDown() {
		if (battleThreadPool != null) {
			battleThreadPool.shutdown();
		}
		if (chatThreadPool != null) {
			chatThreadPool.shutdown();
		}
	}

	private static class ThreadPoolManagerInner {
		private static GameThreadPoolManager INSTANCE = new GameThreadPoolManager();
	}
}
