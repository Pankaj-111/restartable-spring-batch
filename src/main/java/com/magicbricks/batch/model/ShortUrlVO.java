package com.magicbricks.batch.model;

public class ShortUrlVO {

	private String shortUrl;
	
	private String fullUrl;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	@Override
	public String toString() {
		return "ShortUrl [shortUrl=" + shortUrl + ", fullUrl=" + fullUrl + "]";
	}
}
