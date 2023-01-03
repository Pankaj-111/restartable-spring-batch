package com.magicbricks.batch.items.processor;

import java.util.Random;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.magicbricks.batch.model.Tpownerbatchdata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@StepScope
public class MbItemProcessor implements ItemProcessor<Tpownerbatchdata, Tpownerbatchdata> {

	@Override
	public Tpownerbatchdata process(Tpownerbatchdata item) throws Exception {
		log.info("Processing data :{}, Thread :{}", item, Thread.currentThread());
		Thread.sleep(new Random().nextInt(100));
		return item;
	}

}
