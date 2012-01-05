package lv.lu.events.interfaces;

import java.io.InputStream;
import java.util.List;

import lv.lu.events.model.User;

/**
 * Interface for XML parsing operations.
 * Helps to extract information from XML files of certain structure.  
 */
public interface XmlParser 
{
	/**
	 * Extracts information about users from XML file represented as InputStream.
	 * 
	 * @param input - XML file as InputStream
	 * @return list of user objects
	 */
	public List<User> parseUsers(InputStream input);

}
