package com.lonecpp.core.config.request;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 * 
 * @author seven sins
 * @date 2018年1月1日 下午5:20:38
 */
public class RequestProcessorThread implements Callable<Boolean> {

	/**
	 * 自己监控的内存队列
	 */
	private ArrayBlockingQueue<Request> queue;

	public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
		this.queue = queue;
	}

	@Override
	public Boolean call() throws Exception {
		try {
			while (true) {
				// ArrayBlockingQueue
				// Blocking, 如果队列满了, 或者是空的, 都会在执行操作的时候阻塞住
				Request request = queue.take();
				
				/**
				 * 不强制刷新需要去重
				 */
				if(!request.isForceRefresh()) {
					// 先做读请求去重复(type: 1读请求, 2写请求)
					RequestQueue requestQueue = RequestQueue.getInstance();
					Map<Long, Boolean> flagMap = requestQueue.getFlagMap();

					if (request.getType() == 1) {
						// 如果是更新数据库的请求, 将id标识设为true
						flagMap.put(Long.parseLong(String.valueOf(request.getId())), true);
					} else if (request.getType() == 2) {
						// 如果是新缓存的请求, 判断标识不为空且为true, 则表明队列中有一个写操作
						Boolean flag = flagMap.get(request.getId());
						if (flag != null && flag) {
							flagMap.put(Long.parseLong(String.valueOf(request.getId())), false);
						}
						//
						// 如果是缓存刷新的请求, 而且标识不为空, 且标识为false,
						// 则表明队列中已经有一个更新请求和一个刷新缓存请求
						// 不做任何操作
						if (flag != null && !flag) {
							return true;
						}
					}
				}
				
				// 执行这个request操作
				request.update();
				
				// 假如说，执行完了一个读请求之后，假设数据已经刷新到redis中了
				// 但是后面可能redis中的数据会因为内存满了，被自动清理掉
				// 如果说数据从redis中被自动清理掉了以后
				// 然后后面又来一个读请求，此时如果进来，发现标志位是false，就不会去执行这个刷新的操作了
				// 所以在执行完这个读请求之后，实际上这个标志位是停留在false的
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

}
