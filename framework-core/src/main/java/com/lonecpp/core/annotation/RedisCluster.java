package com.lonecpp.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.lonecpp.core.config.redis.RedisConfig;
import com.lonecpp.core.config.redis.impl.RedisServiceImpl;

/**
 * redis缓存
 * @author seven sins
 * @date 2018年1月1日 下午7:29:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({RedisConfig.class, RedisServiceImpl.class})
public @interface RedisCluster {

}
