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

	private static int maximumPoolSize = 5;

	private static long keepAliveTime = 1000;

	private static TimeUnit unit = TimeUnit.MILLISECONDS;

	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(1);

	private static ThreadFactory threadFactory = new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r);
		}
	};

	public static void init() {
		executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	public static void init(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
		ThreadPool.corePoolSize = corePoolSize;
		ThreadPool.maximumPoolSize = maximumPoolSize;
		ThreadPool.keepAliveTime = keepAliveTime;
		ThreadPool.unit = unit;
		init();
	}

	public static ThreadPoolExecutor executor = null;

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

	public static TimeUnit getUnit() {
		return unit;
	}

	public static void setUnit(TimeUnit unit) {
		ThreadPool.unit = unit;
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
