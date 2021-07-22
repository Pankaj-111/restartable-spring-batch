package com.magicbricks.batch.service.shorturl;

import java.io.UnsupportedEncodingException;

public interface IShortUrlGeneratorService {

	String getShortUrl(String fullUrl) throws UnsupportedEncodingException;

}