package com.lonecpp.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.lonecpp.core.config.ehcache.CacheConfig;
import com.lonecpp.core.config.ehcache.impl.CacheServiceImpl;

/**
 * ehcache
 * @author seven sins
 * @date 2018年1月6日 下午6:16:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import({ CacheConfig.class, CacheServiceImpl.class })
public @interface Ehcache {

}
