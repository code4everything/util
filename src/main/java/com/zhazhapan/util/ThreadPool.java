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

	/**
	 * executor
	 */
	public static ThreadPoolExecutor executor = null;

	/**
	 * 新的线程池
	 */
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
	 * @param unit
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

	/**
	 * 初始化线程池
	 */
	public static void init() {
		executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue,
				threadFactory);
	}

	/**
	 * 初始化线程池
	 * 
	 * @param corePoolSize
	 *            初始Size
	 * @param maximumPoolSize
	 *            最大Size
	 * @param keepAliveTime
	 *            存活时长
	 * @param timeUnit
	 *            时间单位
	 */
	public static void init(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit) {
		ThreadPool.corePoolSize = corePoolSize;
		ThreadPool.maximumPoolSize = maximumPoolSize;
		ThreadPool.keepAliveTime = keepAliveTime;
		ThreadPool.timeUnit = timeUnit;
		init();
	}

	/**
	 * 获取获取池
	 * 
	 * @return {@link ThreadPoolExecutor}
	 */
	public static ThreadPoolExecutor getExecutor() {
		if (Checker.isNull(executor)) {
			init();
		}
		return executor;
	}

	/**
	 * 获取coreSize
	 * 
	 * @return {@link Integer}
	 */
	public static int getCorePoolSize() {
		return corePoolSize;
	}

	/**
	 * 设置coreSize
	 * 
	 * @param corePoolSize
	 *            {@link Integer}
	 */
	public static void setCorePoolSize(int corePoolSize) {
		ThreadPool.corePoolSize = corePoolSize;
	}

	/**
	 * 获取maxSize
	 * 
	 * @return {@link Integer}
	 */
	public static int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	/**
	 * 设置maxSize
	 * 
	 * @param maximumPoolSize
	 *            {@link Integer}
	 */
	public static void setMaximumPoolSize(int maximumPoolSize) {
		ThreadPool.maximumPoolSize = maximumPoolSize;
	}

	/**
	 * 获取aliveTime
	 * 
	 * @return {@link Long}
	 */
	public static long getKeepAliveTime() {
		return keepAliveTime;
	}

	/**
	 * 设置aliveTime
	 * 
	 * @param keepAliveTime
	 *            {@link Integer}
	 */
	public static void setKeepAliveTime(int keepAliveTime) {
		ThreadPool.keepAliveTime = keepAliveTime;
	}

	/**
	 * 获取timeUnit
	 * 
	 * @return {@link TimeUnit}
	 */
	public static TimeUnit getTimeUnit() {
		return timeUnit;
	}

	/**
	 * 设置timeUnit
	 * 
	 * @param unit
	 *            {@link TimeUnit}
	 */
	public static void setTimeUnit(TimeUnit unit) {
		ThreadPool.timeUnit = unit;
	}

	/**
	 * 获取workQueue
	 * 
	 * @return {@link BlockingQueue}
	 */
	public static BlockingQueue<Runnable> getWorkQueue() {
		return workQueue;
	}

	/**
	 * 设置workQueue
	 * 
	 * @param workQueue
	 *            {@link BlockingQueue}
	 */
	public static void setWorkQueue(BlockingQueue<Runnable> workQueue) {
		ThreadPool.workQueue = workQueue;
	}

	/**
	 * 获取threadFactory
	 * 
	 * @return {@link ThreadFactory}
	 */
	public static ThreadFactory getThreadFactory() {
		return threadFactory;
	}

	/**
	 * 设置threadFactory
	 * 
	 * @param threadFactory
	 *            {@link ThreadFactory}
	 */
	public static void setThreadFactory(ThreadFactory threadFactory) {
		ThreadPool.threadFactory = threadFactory;
	}
}
