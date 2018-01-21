package com.lonecpp.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.lonecpp.core.config.kafka.KafkaProducerConfig;

/**
 * 消息生产者
 * 
 * @author seven sins
 * @date 2018年1月7日 下午6:42:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import({ com.lonecpp.core.config.kafka.KafkaProducer.class, KafkaProducerConfig.class })
public @interface KafkaProducer {

}
