package com.magicbricks.batch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.magicbricks.rest.client.TinyUrlGenerator;
import com.magicbricks.rest.client.constants.Constants.Env;

@Configuration
public class TinyUrlConfig {
	@Autowired
	private Environment environment;

	@Value("${batch.job.id}")
	private Integer batchId;

	@Bean
	public TinyUrlGenerator tinyUrlGenerator() {
		if (environment.getActiveProfiles().length > 0) {
			final String activeProfile = environment.getActiveProfiles()[0];
			if (activeProfile.contains("release") || activeProfile.equalsIgnoreCase("uat")
					|| activeProfile.equalsIgnoreCase("stg") || activeProfile.equalsIgnoreCase("staging")
					|| activeProfile.equalsIgnoreCase("dep") || activeProfile.equalsIgnoreCase("deployment")) {
				return new TinyUrlGenerator(Env.STG, batchId);
			}
			if (activeProfile.contains("prod")) {
				return new TinyUrlGenerator(Env.PROD, batchId);
			}
		}
		return new TinyUrlGenerator(Env.STG, batchId);
	}

}
