package com.magicbricks;

import java.util.Calendar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.magicbricks.batch.config.ConfigBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TriggerJobService {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private ConfigBean configBean;

	public void runJob(final Job job) throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		log.info("parameter value :{}", getParameterValue());
		final JobParameters jobParameters = new JobParametersBuilder()
				.addParameter("batch-param-key", new JobParameter(getParameterValue()))
				.toJobParameters();
		this.jobLauncher.run(job, jobParameters);
	}

	public String getParameterValue() {
		if (StringUtils.hasLength(configBean.getParam())) {
			log.info("Executing the batch with the parameter {}", configBean.getParam());
			return configBean.getParam();
		}

		final Calendar cal = Calendar.getInstance();
		final StringBuilder key = new StringBuilder(formatValue(cal.get(Calendar.DATE)));
		key.append(formatValue(cal.get(Calendar.MONTH) + 1));
		key.append(formatValue(cal.get(Calendar.YEAR)));
		log.info("No parameters provided using the parameter {}", key);
		return key.toString();
	}

	private String formatValue(int value) {
		if (value < 10) {
			return "0" + value;
		}
		return value + "";
	}
}
