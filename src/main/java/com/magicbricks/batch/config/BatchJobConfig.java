package com.magicbricks.batch.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.magicbricks.batch.exception.MbSkipableException;
import com.magicbricks.batch.listener.MbSkipListener;
import com.magicbricks.batch.mapper.OwnerDataRowMapper;
import com.magicbricks.batch.model.Tpownerbatchdata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class BatchJobConfig {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private ConfigBean configBean;
	
	@Autowired
	private MbSkipListener mbSkipListener;

	@Bean
	public ColumnRangePartitioner partitioner() {
		final ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
		columnRangePartitioner.setColumn("obdrfnum");
		columnRangePartitioner.setDataSource(this.dataSource);
		columnRangePartitioner.setTable("Tpownerbatchdata");
		return columnRangePartitioner;
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<Tpownerbatchdata> pagingItemReader(
			@Value("#{stepExecutionContext['minValue']}") Long minValue,
			@Value("#{stepExecutionContext['maxValue']}") Long maxValue) {
		log.info("reading {} to {}", minValue, maxValue);
		final JdbcPagingItemReader<Tpownerbatchdata> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(1000);
		reader.setRowMapper(new OwnerDataRowMapper());

		final MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		final String selectClause = "o.obdrfnum,o.mobile,o.category, o.propid, o.ptype, o.budget, o.oid, o.city, o.locality, o.email, o.fname,o.createdate,o.bedroom,o.mobile,o.PMT_SOURCE";
		queryProvider.setSelectClause(selectClause);
		queryProvider.setFromClause("from Tpownerbatchdata o,TPUBI u");
		final String whereClause = "where obdrfnum >= " + minValue + " and obdrfnum < " + maxValue
				+ " and o.oid=u.ubirfnum";
		queryProvider.setWhereClause(whereClause);

		final Map<String, Order> sortKeys = new HashMap<>(1);
		sortKeys.put("obdrfnum", Order.ASCENDING);
		queryProvider.setSortKeys(sortKeys);
		reader.setQueryProvider(queryProvider);
		return reader;
	}

	@Bean
	public Step masterStep() throws Exception {
		return stepBuilderFactory.get("masterStep")
				.partitioner(slaveStep().getName(), partitioner())
				.step(slaveStep())
				.gridSize(configBean.getPartitions())
				.taskExecutor(taskExecutor())
				.build();
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		final ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(configBean.getMaxworker());
		pool.setQueueCapacity(configBean.getMaxQueueCapacity());
		pool.setMaxPoolSize(configBean.getMaxPoolSize());
		pool.setThreadNamePrefix("TH-");
		return pool;
	}

	@Bean
	public ItemWriter<Tpownerbatchdata> itemWriter() {
		return new ItemWriter<Tpownerbatchdata>() {
			@Override
			public void write(List<? extends Tpownerbatchdata> items) throws Exception {
				items.forEach(e -> {
					if (e.getId().intValue() == 91127 || e.getId().intValue() == 91129
							|| e.getId().intValue() == 91147) {
						Throwable exc = new RuntimeException("Throwing exc");
						throw new MbSkipableException("Testing skip", exc);
					}
					log.info("Writing Data {}", e.getId());
				});
			}
		};
	}

	@Bean
	public Step slaveStep() {
		return stepBuilderFactory
				.get("slaveStep")
				.<Tpownerbatchdata, Tpownerbatchdata>chunk(1)
				.reader(pagingItemReader(null, null))
				.writer(itemWriter())
				.faultTolerant()
				.skip(MbSkipableException.class)
				.skipLimit(configBean.getMaxSkip())
				.listener(mbSkipListener)
				.build();
	}

	@Bean
	public Job job() throws Exception {
		return jobBuilderFactory
				.get(configBean.getName())
				.start(masterStep())
				.build();
	}
}