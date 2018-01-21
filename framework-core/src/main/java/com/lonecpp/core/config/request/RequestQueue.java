package com.lonecpp.core.config.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求内存队列
 * @author seven sins
 * @date 2018年1月1日 下午5:27:25
 */
public class RequestQueue {

	/**
	 * 内存队列
	 */
	private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();
	
	/**
	 * 标识位
	 */
	private Map<Long, Boolean> flagMap = new ConcurrentHashMap<>();
	
	/**
	 * 单例有很多种方式实现，此处采用绝对线程安全的一种方式---静态内部类的方式初始化单例
	 * 
	 * @author seven sins
	 * @date 2018年1月1日 下午5:02:21
	 */
	private static class Singleton {

		private static RequestQueue instance;

		static {
			instance = new RequestQueue();
		}

		public static RequestQueue getInstance() {
			return instance;
		}
	}

	/**
	 * 利用jvm的机制去保证多线程安全 内部类的初始化, 一定只会发生一次, 不管多少个线程并发去初始化
	 * 
	 * @return
	 */
	public static RequestQueue getInstance() {
		return Singleton.getInstance();
	}

	/**
	 * 初始化的便捷方法
	 */
	public static void init() {
		getInstance();
	}
	
	/**
	 * 添加一个内存队列
	 * @param queue
	 */
	public void addQueue(ArrayBlockingQueue<Request> queue) {
		this.queues.add(queue);
	}
	
	/**
	 * 获取内存队列的数量
	 * @return
	 */
	public int size() {
		return queues.size();
	}
	
	/**
	 * 获取内存队列
	 * @param index
	 * @return
	 */
	public ArrayBlockingQueue<Request> getQueue(int index) {
		return queues.get(index);
	}

	public Map<Long, Boolean> getFlagMap() {
		return flagMap;
	}
	
}
