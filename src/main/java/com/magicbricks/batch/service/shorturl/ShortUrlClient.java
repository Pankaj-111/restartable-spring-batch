//package com.magicbricks.batch.service.shorturl;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.magicbricks.batch.model.ShortUrlVO;
//
//
//@FeignClient(value = "shorturl-service", url = "${batch.job.shortUrlDomain}", configuration = FeignRetryForReadConfig.class)
//public interface ShortUrlClient {
//	@GetMapping(path = "/mbtrk/shortUrl")
//	public ShortUrlVO getShortUrl(@RequestParam(value = "urlToshort", required = true) String urlToshort);
//	
//}
