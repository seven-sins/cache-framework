package com.lonecpp.core.config.request;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 请求异步处理实现类
 * 
 * @author seven sins
 * @date 2018年1月1日 下午7:05:47
 */
@Service
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {
	static final Logger LOG = Logger.getLogger(RequestAsyncProcessServiceImpl.class);
	/**
	 * 如果接收到一个新的请求，发现前面已经有一个写请求和一个读请求时， 当前请求就不需要压力队列<br/>
	 * 因为写请求肯定会更新数据库， 然后读请求也肯定会从数据库中读取最新数据并更新到缓存，当前请求只需要等待一下就可以从缓存中获取到数据
	 */
	@Override
	public void process(Request request) {
		// 做请求的路由, 根据每个请求的id, 跌幅到对应的内存队列中去
		ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getId());
		// 将请求放入到队列中, 完成路由操作
		try {
			queue.put(request);
		} catch (InterruptedException e) {
			LOG.error("=============将请求添加到队列时触发异常 " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 获取路由到的内存队列
	 * 
	 * @param id
	 * @return
	 */
	private ArrayBlockingQueue<Request> getRoutingQueue(Serializable id) {
		RequestQueue requestQueue = RequestQueue.getInstance();
		//
		String key = id != null ? String.valueOf(id) : null;

		int h;
		int hash = key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		// 对hash值取模, 将hash值路由到指定的内存队列中, 比如内存队列大小8
		// 用内存队列的数量对hash值取模之后, 结果一定是在0~7之间
		// 所以任何一个id都会被固定路由到同样的一个内存队列中去
		int index = (requestQueue.size() - 1) & hash;

		return requestQueue.getQueue(index);
	}

}
