package com.magicbricks.batch.service.shorturl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.magicbricks.rest.client.TinyUrlGenerator;
import com.magicbricks.rest.client.exceptions.MbClientException;
import com.magicbricks.rest.client.model.ShortUrlDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Retryable(maxAttempts = 2, backoff = @Backoff(delay = 3000))
public class ShortUrlGeneratorService implements IShortUrlGeneratorService {

	@Autowired
	private TinyUrlGenerator tinyUrlGenerator;

	@Override
	public String getShortUrl(String fullUrl) throws MbClientException, IOException {
		final ShortUrlDto shortUrlDto = tinyUrlGenerator.getShortUrl(fullUrl);
		log.info("creating short url for :{} " + fullUrl);
		if (shortUrlDto != null) {
			log.info("Full URL : {}, and short url :{}", fullUrl, shortUrlDto.getShortUrl());
			return shortUrlDto.getShortUrl();
		}
		return null;
	}
}
