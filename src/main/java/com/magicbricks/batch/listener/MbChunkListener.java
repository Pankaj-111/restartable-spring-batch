package com.magicbricks.batch.listener;

import java.util.List;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MbChunkListener implements ChunkListener {

	@Override
	public void beforeChunk(ChunkContext context) {
		log.info("Chnuk Listner Executing ...");
	}

	@Override
	public void afterChunk(ChunkContext context) {
		int count = context.getStepContext().getStepExecution().getReadCount();
		log.info("Chnuk Listner data read : {}", count);
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		List<Throwable> exception = context.getStepContext().getStepExecution().getFailureExceptions();
		for (Throwable throwable : exception) {
			log.error("Exception in chenk reading : {}", throwable);
		}
	}

}
