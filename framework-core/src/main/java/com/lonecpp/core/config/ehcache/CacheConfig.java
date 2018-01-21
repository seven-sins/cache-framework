package com.lonecpp.core.config.ehcache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * ehCache配置
 * @author seven sins
 * @date 2018年1月6日 下午6:11:57
 */
@Configuration
@EnableCaching
public class CacheConfig {
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFatoryBean() {
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		
		return cacheManagerFactoryBean;
	}
	
	@Bean
	public EhCacheCacheManager ehCacheManager(EhCacheManagerFactoryBean bean) {
		return new EhCacheCacheManager(bean.getObject());
	}
}
