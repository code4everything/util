/**
 * 
 */
package com.zhazhapan.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pantao
 *
 */
public class ThreadPool {

	private static int corePoolSize = 1;

	private static int maximumPoolSize = 3;

	private static long keepAliveTime = 1000;

	private static TimeUnit timeUnit = TimeUnit.MILLISECONDS;

	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(1);

	public static ThreadPoolExecutor executor = null;

	public ThreadPoolExecutor newExecutor = null;

	/**
	 * 新建线程池
	 * 
	 * @param core
	 *            初始线程大小
	 * @param maximum
	 *            最大线程数
	 * @param keep
	 *            线程存活时长
	 * @param timeUnit
	 *            存活时长单位
	 */
	public ThreadPool(int core, int maximum, int keep, TimeUnit unit) {
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(1);
		ThreadFactory factory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r);
			}
		};
		newExecutor = new ThreadPoolExecutor(core, maximum, keep, unit, queue, factory);
	}

	private static ThreadFactory threadFactory = new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r);
		}
	};

	public static void init() {
		executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue,
				threadFactory);
	}

	public static void init(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit) {
		ThreadPool.corePoolSize = corePoolSize;
		ThreadPool.maximumPoolSize = maximumPoolSize;
		ThreadPool.keepAliveTime = keepAliveTime;
		ThreadPool.timeUnit = timeUnit;
		init();
	}

	public static ThreadPoolExecutor getExecutor() {
		if (Checker.isNull(executor)) {
			init();
		}
		return executor;
	}

	public static void shutdown() {
		executor.shutdown();
	}

	public static void shutdownNow() {
		executor.shutdownNow();
	}

	public static int getCorePoolSize() {
		return corePoolSize;
	}

	public static void setCorePoolSize(int corePoolSize) {
		ThreadPool.corePoolSize = corePoolSize;
	}

	public static int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public static void setMaximumPoolSize(int maximumPoolSize) {
		ThreadPool.maximumPoolSize = maximumPoolSize;
	}

	public static long getKeepAliveTime() {
		return keepAliveTime;
	}

	public static void setKeepAliveTime(int keepAliveTime) {
		ThreadPool.keepAliveTime = keepAliveTime;
	}

	public static TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public static void setTimeUnit(TimeUnit unit) {
		ThreadPool.timeUnit = unit;
	}

	public static BlockingQueue<Runnable> getWorkQueue() {
		return workQueue;
	}

	public static void setWorkQueue(BlockingQueue<Runnable> workQueue) {
		ThreadPool.workQueue = workQueue;
	}

	public static ThreadFactory getThreadFactory() {
		return threadFactory;
	}

	public static void setThreadFactory(ThreadFactory threadFactory) {
		ThreadPool.threadFactory = threadFactory;
	}
}
