package lv.lu.events.interfaces;

import java.io.InputStream;

/**
 * HTTP client module used to download data from the Web.
 * TODO [task]  implement it to send requests to Facebook
 */
public interface HTTPClient {

	/**
	 * Downloads page content from specified URL and returns it as InputStream
	 * 
	 * @param url - page URL to download
	 * @return	input stream
	 */
	public InputStream download(String url);

}
