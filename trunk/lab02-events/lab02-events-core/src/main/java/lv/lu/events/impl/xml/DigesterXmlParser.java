package lv.lu.events.impl.xml;

import java.io.InputStream;
import java.util.List;

import lv.lu.events.interfaces.XmlParser;
import lv.lu.events.model.User;

import org.apache.commons.digester.Digester;

/**
 * This implementation uses Apache Digester (XML -> Java mapping approach).
 * http://commons.apache.org/digester/
 * 
 * http://commons.apache.org/digester/commons-digester-2.0/docs/api/org/apache/commons/digester/package-summary.html#package_description
 * 
 * Validation against XML Schema is not implemented.
 */
public class DigesterXmlParser implements XmlParser {

	public List<User> parseUsers(InputStream input) {
		System.out.println("[DigesterXmlParser] Starting to extract users info from InputStream");
		Digester digester = initDigesterForUsers();
		UsersHolder usersHolder = null;
		try {
			usersHolder = (UsersHolder)digester.parse(input);
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("[DigesterXmlParser] Users info successfully extracted. " +
				"In total [" + usersHolder.getUsers().size() + "] items extracted");
		return usersHolder.getUsers();
	}
	
	private Digester initDigesterForUsers() {
		Digester digester = new Digester();
		digester.setValidating(false);
		
		final String USERS_USER = XmlSchemaConst.Entities.USERS + "/" + XmlSchemaConst.Entities.USER;	// users/user
		
		digester.addObjectCreate(XmlSchemaConst.Entities.USERS, UsersHolder.class );					// users
		digester.addObjectCreate(USERS_USER, User.class );												// users/user
		digester.addBeanPropertySetter(USERS_USER + "/" + XmlSchemaConst.User.USERNAME, "username" );	// users/user/username
		digester.addBeanPropertySetter(USERS_USER + "/" + XmlSchemaConst.User.PASSWORD, "password" );	// users/user/password
		digester.addBeanPropertySetter(USERS_USER + "/" + XmlSchemaConst.User.FULL_NAME, "fullName" );	// users/user/full-name
		digester.addBeanPropertySetter(USERS_USER + "/" + XmlSchemaConst.User.EMAIL, "email" );			// users/user/email
		digester.addBeanPropertySetter(USERS_USER + "/" + XmlSchemaConst.User.FACEBOOK_ID, "facebookId" );			// users/user/facebook-id
		digester.addSetNext(USERS_USER, "addUser" );			// users/user
		
		return digester;
	}	
}
