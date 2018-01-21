package com.lonecpp.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.lonecpp.core.config.feign.FeignConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import({ FeignConfiguration.class })
public @interface Feign {

}
