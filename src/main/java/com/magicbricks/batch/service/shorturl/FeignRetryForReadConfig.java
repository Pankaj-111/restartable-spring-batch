//package com.magicbricks.batch.service.shorturl;
//
//import java.net.ConnectException;
//
//import org.springframework.context.annotation.Bean;
//
//import feign.Logger;
//import feign.RetryableException;
//import feign.Retryer;
//
//public class FeignRetryForReadConfig {
//	
//	@Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.BASIC;
//    }
//
//	@Bean
//	public Retryer retryer() {
//		return new MyRetry();
//	}
//
//	private static class MyRetry implements Retryer {
//		private final static int retryerMax = 1;
//		private int currentRetryCnt = 0;
//		@Override
//		public void continueOrPropagate(RetryableException e) {
//			if (currentRetryCnt > retryerMax) {
//				throw e;
//			}
//			// Retry when the connection is abnormal
//			if (e.getCause() instanceof ConnectException) {
//				currentRetryCnt++;
//				return;
//			}
//			throw e;
//		}
//
//		@Override
//		public Retryer clone() {
//			return new MyRetry();
//		}
//	}
//}
