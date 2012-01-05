package lv.lu.events.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import lv.lu.events.interfaces.HTTPClient;

import org.apache.log4j.Logger;

public class HTTPClientImpl implements HTTPClient {
	private Logger log = Logger.getLogger(HTTPClientImpl.class.getSimpleName());

	@Override
	public InputStream download(String url) {
		InputStream is = null;
		try {
			is = new URL(url).openStream();
		} catch (IOException e) {
			log.error("Failed to open input stream by this URL: " + url);
			log.error(e);
		}
		return is;
	}
}
