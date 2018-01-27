package com.lonecpp.thread.learning.one;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author seven sins
 * @date 2018年1月27日 下午8:10:51
 * 
 * CountDownLatch相当于notify wait的升级版, 不依赖synchronized, 并且具有适时性
 */
public class ThreadCountDownLatch {

	private volatile static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) {
		/**
		 * CountDownLatch相当于notify wait的升级版, 不依赖synchronized, 并且具有适时性
		 * 
		 * new CountDownLatch(2); 计数如果改为2, 则必须发出两次通知, CountDownLatch才会被唤醒
		 */
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i = 0; i< 10; i++) {
						list.add(i);
						System.out.println("t1 i = " + i);
						Thread.sleep(1000);
						if(list.size() == 5) {
							System.out.println("发出通知...");
							countDownLatch.countDown();
						}
					}
				}catch(Exception e) {
					
				}
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				if(list.size() != 5) {
					try {
						countDownLatch.await();
					}catch(Exception e) {
						
					}
				}
				System.out.println("收到通知: list.size() == 5");
			}
		}, "t2");

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
		收到通知: list.size() == 5
		t1 i = 6
		t1 i = 7
		t1 i = 8
		t1 i = 9
	 */
}
