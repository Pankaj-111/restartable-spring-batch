package com.magicbricks;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableFeignClients
@EnableBatchProcessing
public class BatchTemplateApplication {

	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		final ConfigurableApplicationContext applicationContext = SpringApplication.run(BatchTemplateApplication.class,
				args);

		final Job job = applicationContext.getBean("job", Job.class);
		final TriggerJobService triggerService = applicationContext.getBean(TriggerJobService.class);
		triggerService.runJob(job);
		applicationContext.close();
	}

}
