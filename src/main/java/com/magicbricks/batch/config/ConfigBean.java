package com.magicbricks.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "batch.job")
public class ConfigBean {

	private String param;
	private int partitions;
	private int maxworker;
	private int maxPoolSize;
	private int maxQueueCapacity;
	private int maxSkip;
	private int fetchSize;
	private int maxRetry;
	
	private String name; 
	private String id;
	
	private String shortUrlDomain;
	
	private String kafkaBrokers;
	private String mailTopic;
	private String smsTopic;

}
