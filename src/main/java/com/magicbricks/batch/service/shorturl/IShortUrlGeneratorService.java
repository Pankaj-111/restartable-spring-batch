package com.magicbricks.batch.service.shorturl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.magicbricks.rest.client.exceptions.MbClientException;

public interface IShortUrlGeneratorService {
	String getShortUrl(String fullUrl) throws UnsupportedEncodingException, MbClientException, IOException;
}