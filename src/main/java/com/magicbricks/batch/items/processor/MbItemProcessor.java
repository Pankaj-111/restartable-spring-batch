package com.magicbricks.batch.items.processor;

import java.util.Random;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magicbricks.batch.model.Tpownerbatchdata;
import com.magicbricks.batch.service.shorturl.IShortUrlGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@StepScope
public class MbItemProcessor implements ItemProcessor<Tpownerbatchdata, Tpownerbatchdata> {
	@Autowired
	private IShortUrlGeneratorService shortUrlGeneratorService;

	@Override
	public Tpownerbatchdata process(Tpownerbatchdata item) throws Exception {
		log.info("Processing data :{}, Thread :{}", item, Thread.currentThread());
		Thread.sleep(new Random().nextInt(100));
		shortUrlGeneratorService.getShortUrl("http://www.google.com");
		return item;
	}

}
