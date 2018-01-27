package com.lonecpp.thread.learning.one;

/**
 * @author seven sins
 * @date 2018年1月27日 下午8:10:51
 */
public class ThreadSynchronized extends Thread {

	private int count = 5;
	
	/**
	 * synchronized加锁
	 */
	@Override
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		/**
		 * 当多个线程访问Thread1的run方法时, 以排除的方式进行处理
		 * 一个线程想要执行synchronized修饰的方法里的代码
		 * 1. 尝试获得锁
		 * 2. 如果拿到锁, 执行synchronized代码体的内容, 如果拿不到锁, 这个线程会不断
		 *    尝试获得锁, 直到拿到为止, 且是多个线程同时去竞争这把锁(会有锁竞争 的问题)
		 */
		ThreadSynchronized thread = new ThreadSynchronized();
		Thread t1 = new Thread(thread, "t1");
		Thread t2 = new Thread(thread, "t2");
		Thread t3 = new Thread(thread, "t3");
		Thread t4 = new Thread(thread, "t4");
		Thread t5 = new Thread(thread, "t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
