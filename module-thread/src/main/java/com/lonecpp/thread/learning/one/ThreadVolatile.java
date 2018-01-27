package com.lonecpp.thread.learning.one;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seven sins
 * @date 2018年1月27日 下午8:10:51
 */
public class ThreadVolatile {

	private volatile static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) {
		/**
		 * 线程中执行很耗时的操作, 操作中修改了某个变量, 其他线程访问时不会读取到修改后的变量
		 * 解决这个问题需要加volatile
		 */
		// List<Integer> list = new ArrayList<>();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i< 10; i++) {
					try {
						list.add(i);
						System.out.println("t1 i = " + i);
						Thread.sleep(1000);
					}catch(Exception e) {
						
					}
				}
			}
		}, "t1");
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					if(list.size() == 5) {
						System.out.println("list.size() == 5");
						break;
					}
				}
			}
		}, "t2");
		t2.start();
	}
}
