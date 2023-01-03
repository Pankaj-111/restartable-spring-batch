package com.magicbricks.batch.items.writer;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.magicbricks.batch.exception.MbSkipableException;
import com.magicbricks.batch.model.Tpownerbatchdata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@StepScope
public class MbItemWriter implements ItemWriter<Tpownerbatchdata> {

	@Override
	public void write(List<? extends Tpownerbatchdata> items) throws Exception {
		for (Tpownerbatchdata e : items) {
			log.info("Writing Data item {}, Thread :{}", e.getId(), Thread.currentThread());
//			if (e.getId().intValue() == 21203697 || e.getId().intValue() == 21203699
//					|| e.getId().intValue() == 21203745) {
//				Throwable exc = new RuntimeException("Throwing exc");
//				throw new MbSkipableException("Testing skip", exc);
//			}
//			if (e.getId().intValue() == 21206835 || e.getId().intValue() == 21203723
//					|| e.getId().intValue() == 21203725) {
//				Throwable exc = new RuntimeException("Throwing Ret Exc for :");
//				throw new MbSkipableException("Testing Retry for id " + e.getId(), exc);
//			}
		}
	}

}
