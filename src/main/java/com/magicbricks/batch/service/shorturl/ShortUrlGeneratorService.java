package com.magicbricks.batch.service.shorturl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.magicbricks.batch.config.ConfigBean;
import com.magicbricks.batch.model.ShortUrlVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Retryable(maxAttempts = 2, backoff = @Backoff(delay = 3000))
public class ShortUrlGeneratorService implements IShortUrlGeneratorService {

	@Autowired
	private ConfigBean configBean;

	@Autowired
	private ShortUrlClient client;

	@Override
	public String getShortUrl(String fullUrl) throws UnsupportedEncodingException {
		final ShortUrlVO shortUrlVo = client.getShortUrl(fullUrl);
		log.info("creating short url for :{} " + fullUrl);
		if (shortUrlVo != null) {
			final String shortUrl = shortUrlVo.getShortUrl().replace("http://mbtrk.co", configBean.getDomain());
			log.info("Full URL : {}, and short url :{}", fullUrl, shortUrl);
			return shortUrl;
		}
		return null;
	}
}
