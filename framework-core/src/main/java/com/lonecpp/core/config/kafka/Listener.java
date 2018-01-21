package com.lonecpp.core.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * kafka监听器(消费者)
 * @author seven sins
 * @date 2018年1月7日 下午8:34:16
 * 
 * @Component
 */
public class Listener {

	@KafkaListener(topics = "${kafka.topic}", groupId = "cache-group")
    public void listen(ConsumerRecord<?, ?> record) {
		System.out.println(record.value());
		System.out.println(record);
	}
}
