package com.game.robot.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.game.robot.config.Config;

public class StrategryExcutor {
	private static final int THREAD_POOL_MAX_SIZE = 8;
	private static volatile StrategryExcutor instance;
	private static Object LOCKER = new Object();

	private final ScheduledExecutorService scheduledExecutor;
	private final ExecutorService fixedThreadPool;

	private StrategryExcutor(int poolSize) {
		scheduledExecutor = Executors.newScheduledThreadPool(4);
		fixedThreadPool = Executors.newFixedThreadPool(poolSize);
	}

	public static StrategryExcutor getInstance() {
		if (instance == null) {
			synchronized (LOCKER) {
				if (instance == null) {
					int poolSize = Config.ROBOT_COUNT < THREAD_POOL_MAX_SIZE ? Config.ROBOT_COUNT : THREAD_POOL_MAX_SIZE;
					instance = new StrategryExcutor(poolSize);
				}
			}
		}

		return instance;
	}

	public void execute(Runnable runnable) {
		fixedThreadPool.execute(runnable);
	}

	public void scheduleOnce(Runnable runnable, long delay) {
		scheduledExecutor.schedule(runnable, delay, TimeUnit.MILLISECONDS);
	}

	public void scheduleOnce(StrategyRunnable runnable, long delay) {
		ScheduledFuture<?> future = scheduledExecutor.schedule(runnable, delay, TimeUnit.MILLISECONDS);
		runnable.setFuture(future);
	}

	public void scheduleWithFixedDelay(StrategyRunnable runnable, long delay, long interval) {
		ScheduledFuture<?> future = scheduledExecutor.scheduleWithFixedDelay(runnable, delay, interval, TimeUnit.MILLISECONDS);
		runnable.setFuture(future);
	}
}
