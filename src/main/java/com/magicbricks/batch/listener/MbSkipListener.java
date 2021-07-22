package com.magicbricks.batch.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MbSkipListener implements SkipListener<Object, Object> {

	@Override
	public void onSkipInRead(Throwable t) {
		log.info("Skiping on read :{}", t);
	}

	@Override
	public void onSkipInWrite(Object item, Throwable t) {
		log.info("Skiping on writer :{}", item);
	}

	@Override
	public void onSkipInProcess(Object item, Throwable t) {
		log.info("Skiping on process :{}", item);
	}
}
