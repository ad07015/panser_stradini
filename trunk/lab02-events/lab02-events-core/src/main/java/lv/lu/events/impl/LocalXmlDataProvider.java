package lv.lu.events.impl;

import java.io.FileInputStream;
import java.io.InputStream;

import lv.lu.events.interfaces.XmlDataProvider;

/**
 * Loads XML data from local file.
 */
public class LocalXmlDataProvider implements XmlDataProvider {

	// property values are specified in Spring configuration file
	// see /lab01-events/src/main/resources/applicationContext.xml
	private String usersFilePath;		// path to local users.xml file
	private String contentFilePath;		// path to local content.xml file
	
	/**
	 * InputStream has to be closed after usage.
	 */
	public InputStream loadUsersXml() {
		return loadXml(usersFilePath);
	}

	/**
	 * InputStream has to be closed after usage.
	 */
	public InputStream loadContentXml() {
		return loadXml(contentFilePath);
	}
	
	private InputStream loadXml(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			return is;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setUsersFilePath(String usersFilePath) {
		this.usersFilePath = usersFilePath;
	}

	public void setContentFilePath(String contentFilePath) {
		this.contentFilePath = contentFilePath;
	}
}
