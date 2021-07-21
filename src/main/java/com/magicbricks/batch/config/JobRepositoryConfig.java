package com.magicbricks.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.XStreamExecutionContextStringSerializer;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRepositoryConfig extends DefaultBatchConfigurer {
	@Autowired
	private DataSource dataSource;

	@SuppressWarnings("deprecation")
	@Bean
	ExecutionContextSerializer getSerializer() {
		return new XStreamExecutionContextStringSerializer();
	}

	@Override
	protected JobRepository createJobRepository() throws Exception {
		final JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(dataSource);
		factory.setSerializer(getSerializer());
		factory.setTransactionManager(getTransactionManager());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
}
