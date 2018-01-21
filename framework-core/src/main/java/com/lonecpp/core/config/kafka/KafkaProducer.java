package com.lonecpp.core.config.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author seven sins
 * @date 2018年1月7日 下午7:41:28
 */
@Component
public class KafkaProducer {

	@Autowired
    KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${kafka.topic}")
	String topic;

    public void send(String payload) {
    	kafkaTemplate.send(topic, payload);
    }
}
