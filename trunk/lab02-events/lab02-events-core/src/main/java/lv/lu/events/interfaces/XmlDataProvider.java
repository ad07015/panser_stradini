package lv.lu.events.interfaces;

import java.io.InputStream;

/**
 * Interface for loading XML files from some source
 */
public interface XmlDataProvider {

	public InputStream loadUsersXml();
	
	public InputStream loadContentXml();
}
