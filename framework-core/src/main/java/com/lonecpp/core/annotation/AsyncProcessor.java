package com.lonecpp.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.lonecpp.core.config.request.RequestAsyncProcessServiceImpl;

/**
 * 异步处理器
 * @author seven sins
 * @date 2018年1月1日 下午7:30:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({RequestAsyncProcessServiceImpl.class})
public @interface AsyncProcessor {

}
