package com.lonecpp.thread.learning.one;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seven sins
 * @date 2018年1月27日 下午8:10:51
 * 
 * 1. notify wait必须搭配synchronized使用
 * 2. notify不会释放锁(弊端, 通知后不会释放锁, 做不到适时性)
 * 3. wait会释放锁
 */
public class ThreadWaitNotify {

	private volatile static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) {
		final Object lock = new Object();
		// 
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					/**
					 * notify wait必须搭配synchronized使用
					 */
					synchronized(lock) {
						for(int i = 0; i< 10; i++) {
							list.add(i);
							System.out.println("t1 i = " + i);
							Thread.sleep(1000);
							if(list.size() == 5) {
								System.out.println("发出通知...");
								/**
								 * notify不会释放锁
								 */
								lock.notify();
							}
						}
					}
				}catch(Exception e) {
					
				}
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(lock) {
					while(true) {
						if(list.size() != 5) {
							try {
								/**
								 * wait会释放锁
								 */
								lock.wait();
							}catch(Exception e) {
								
							}
						}
						System.out.println("收到通知: list.size() == 5");
						break;
					}
				}
			}
		}, "t2");
		/**
		 * 必须先启动t2
		 */
		t2.start();
		
		t1.start();
	}
	/**
	 * 执行结果
	 * 	t1 i = 0
		t1 i = 1
		t1 i = 2
		t1 i = 3
		t1 i = 4
		发出通知...
		t1 i = 5
		t1 i = 6
		t1 i = 7
		t1 i = 8
		t1 i = 9
		收到通知: list.size() == 5
	 */
}
