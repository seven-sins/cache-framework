package com.lonecpp.thread.learning.two;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author seven sins
 * @date 2018年1月28日 上午11:55:54
 * 
 * 阻塞队列实现
 */
public class QueueBlockDemo {
	
	/**
	 * 申明集合
	 */
	private final LinkedList<Object> list = new LinkedList<>();
	/**
	 * 申明计数器
	 */
	private AtomicInteger count = new AtomicInteger(0);
	/**
	 * 制定上限和下限
	 */
	private final int minSize = 0;
	
	private final int maxSize;
	
	public QueueBlockDemo(int size) {
		this.maxSize = size;
	}
	/**
	 * 对象锁
	 */
	private final Object lock = new Object();
	
	public void put(Object object) {
		synchronized(lock) {
			while(count.get() == this.maxSize) {
				try {
					// 集合长度达到最大长度, 阻塞
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(object);
			// 计数器递增
			count.incrementAndGet();
			System.out.println("新加入元素: " + object);
			// System.out.println("集合长度: " + count.get());
			// 唤醒其他线程
			lock.notify();
		}
	}
	
	public Object take() {
		Object ret = null;
		synchronized(lock) {
			while(count.get() == this.minSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 从集合移除第一个元素
			ret = list.removeFirst();
			// 计数器递减
			count.decrementAndGet();
			// 唤醒其他线程
			lock.notify();
		}
		return ret;
	}
	
	public int getSize() {
		return this.count.get();
	}
	
	public static void main(String[] args) {
		final QueueBlockDemo queue = new QueueBlockDemo(5);
		queue.put("a");
		queue.put("b");
		queue.put("c");
		queue.put("d");
		queue.put("e");
		
		System.out.println("当前容器长度为: " + queue.getSize());
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				queue.put("f");
				queue.put("g");
			}
			
		}, "t1");
		t1.start();
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				Object o1 = queue.take();
				System.out.println("===取得元素为: " + o1);
				Object o2 = queue.take();
				System.out.println("===取得元素为: " + o2);
			}
			
		}, "t2");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.start();
	}
}
