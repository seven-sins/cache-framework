package com.lonecpp.core.config.request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 请求处理线程池(单例)
 * 
 * @author seven sins
 * @date 2018年1月1日 下午5:01:27
 */
public class RequestProcessorThreadPool {

	/**
	 * 线程池 TODO: 设置线程池的大小, 每个线程监控的内存队列的大小做在配置文件中
	 */
	private ExecutorService threadPool = new ThreadPoolExecutor(
			// 核心线程池
			10, 
			// 最大线程池
			30, 
			// 线程池中超过corePoolSize数目的空闲线程最大存活时间
			30, 
			TimeUnit.SECONDS, 
			// 阻塞队列(100容量的阻塞队列)
			new ArrayBlockingQueue<Runnable>(100),
			// 设置线程名称
			new RequestProcessorThreadFactory());

	public RequestProcessorThreadPool() {
		RequestQueue requestQueue = RequestQueue.getInstance();
		int maxThreadPool = 10;
		for (int i = 0; i < maxThreadPool; i++) {
			ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
			requestQueue.addQueue(queue);
			threadPool.submit(new RequestProcessorThread(queue));
		}
	}

	/**
	 * 单例有很多种方式实现，此处采用绝对线程安全的一种方式---静态内部类的方式初始化单例
	 * 
	 * @author seven sins
	 * @date 2018年1月1日 下午5:02:21
	 */
	private static class Singleton {

		private static RequestProcessorThreadPool instance;

		static {
			instance = new RequestProcessorThreadPool();
		}

		public static RequestProcessorThreadPool getInstance() {
			return instance;
		}
	}

	/**
	 * 利用jvm的机制去保证多线程安全 内部类的初始化, 一定只会发生一次, 不管多少个线程并发去初始化
	 * 
	 * @return
	 */
	public static RequestProcessorThreadPool getInstance() {
		return Singleton.getInstance();
	}

	/**
	 * 初始化的便捷方法
	 */
	public static void init() {
		getInstance();
	}
}
