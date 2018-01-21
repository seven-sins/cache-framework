package com.lonecpp.redis;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka监听器(消费者)
 * @author seven sins
 * @date 2018年1月7日 下午8:39:10
 */
@Component
public class Listener {
	
	/**
	 * TODO 版本变更, 此处group改为groupId, 待验证
	 * @param record
	 */
	@KafkaListener(topics = "${kafka.topic}", groupId = "cache-group")
    public void listen(ConsumerRecord<?, ?> record) {
		System.out.println(record.value());
		System.out.println(record);
	}
}
