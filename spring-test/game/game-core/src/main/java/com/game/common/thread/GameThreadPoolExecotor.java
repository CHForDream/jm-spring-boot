/**
 * 
 */
package com.game.common.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.game.constants.Loggers;

/**
 * 线程池
 */
public class GameThreadPoolExecotor extends ThreadPoolExecutor {
	public GameThreadPoolExecotor(int corePoolSize, int maximumPoolSize, String threadNamePrefix) {
		this(corePoolSize, maximumPoolSize, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadNamePrefix);
	}

	private GameThreadPoolExecotor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
			String threadNamePrefix) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new MyThreadFactory(threadNamePrefix), new MyRejectedExecutionHandler());
	}

	@Override
	public void execute(Runnable task) {
		super.execute(task);
	}

	public void shutDown() {
		super.shutdown();
	}

	/**
	 * 拒绝策略 线程池已满打印日志,将任务放到主线程执行
	 *
	 */
	private static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
		@Override
		public void rejectedExecution(Runnable runable, ThreadPoolExecutor executor) {
			doLog(runable, executor);
		}

		private void doLog(Runnable runable, ThreadPoolExecutor executor) {
			if (!executor.isShutdown()) {
				Loggers.serverLogger.info("线程池已关闭，当前线程直接执行任务");
				runable.run();
			}
		}
	}

	/**
	 * 线程工厂
	 * 
	 * @author LPF
	 *
	 */
	private static class MyThreadFactory implements ThreadFactory {
		private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		/**
		 * 
		 */
		public MyThreadFactory(String threadNamePrefix) {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			namePrefix = threadNamePrefix + "-" + POOL_NUMBER.getAndIncrement() + "-";
		}

		@Override
		public Thread newThread(Runnable runable) {
			Thread t = new Thread(group, runable, namePrefix + threadNumber.getAndIncrement(), 0);
			if (t.isDaemon()) {
				t.setDaemon(false);
			}
			if (t.getPriority() != Thread.NORM_PRIORITY) {
				t.setPriority(Thread.NORM_PRIORITY);
			}
			return t;
		}
	}
}
