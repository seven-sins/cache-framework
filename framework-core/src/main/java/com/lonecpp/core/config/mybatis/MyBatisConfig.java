package com.lonecpp.core.config.mybatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.github.pagehelper.PageInterceptor;

/**
 * MyBatis基础配置
 * 
 * @author seven sins
 * @date 2017年5月8日 下午10:55:01
 */
@Configuration
@AutoConfigureAfter(DruidConfiguration.class)
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
	static final Logger LOG = Logger.getLogger(MyBatisConfig.class);
	@Autowired
	DataSource dataSource;

	/**
	 * 构建MyBatis入口类
	 * 
	 * @return
	 */
	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// bean.setTypeAliasesPackage("com.lonecpp.po"); // Mapper.xml中类型为类的全名， 可不配置此属性

		// 分页插件
		PageInterceptor pageHelper = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("reasonable", "true");
		properties.setProperty("authRuntimeDialect", "true");
		pageHelper.setProperties(properties);

		// 添加插件
		bean.setPlugins(new Interceptor[] { pageHelper });

		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			LOG.error("执行setMapperLocations出错: " + e);
			throw new RuntimeException(e);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	/**
	 * 构建事务管理器
	 */
	@Override
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 事务配置
	 * 
	 * @param transactionManager
	 * @return
	 */
	@Bean
	public TransactionInterceptor transactionInterceptor() {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		transactionInterceptor.setTransactionManager(annotationDrivenTransactionManager());
		Properties properties = new Properties();
		properties.setProperty("find*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("get*", "PROPAGATION_REQUIRED, readOnly");
		properties.setProperty("insert*", "PROPAGATION_REQUIRED");
		properties.setProperty("delete*", "PROPAGATION_REQUIRED");
		properties.setProperty("update*", "PROPAGATION_REQUIRED");
		transactionInterceptor.setTransactionAttributes(properties);

		return transactionInterceptor;
	}

	@Bean
	public BeanNameAutoProxyCreator transactionAutoProxy() {
		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
		transactionAutoProxy.setProxyTargetClass(false);
		transactionAutoProxy.setBeanNames(new String[] { "*ServiceImpl" });
		transactionAutoProxy.setInterceptorNames(new String[] { "transactionInterceptor" });

		return transactionAutoProxy;
	}

}
