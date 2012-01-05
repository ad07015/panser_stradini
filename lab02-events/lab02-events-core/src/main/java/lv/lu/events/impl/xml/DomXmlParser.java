package lv.lu.events.impl.xml;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import lv.lu.events.interfaces.XmlParser;
import lv.lu.events.model.User;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This implementation uses JAXP API in combination with DOM API.
 */
public class DomXmlParser implements XmlParser {
	
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema"; 
	
	// flag to validate XML file against XSD schema
	// if XML file does not correspond to schema, processing will fail
	private boolean validateXml;
	
	public void setValidateXml(boolean validateXml) {
		this.validateXml = validateXml;
	}
	
	public List<User> parseUsers(InputStream input) {
		System.out.println("[DomXmlParser] Starting to extract users from XML file...");
		Document doc = null;
		try {
			doc = getDocumentBuilder().parse(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return extractUsers(doc);
	}

	/**
	 * Initialization of DocumentBuilder to be used to obtain DOM Document.
	 */
	private DocumentBuilder getDocumentBuilder(){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			// specifies if XML document will be validated against XML Schema (XSD file)
			// if document will not correspond to schema definition, then there will be an error
			factory.setValidating(validateXml);
			try {
			  factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			} 
			catch (IllegalArgumentException e) {
			  throw new RuntimeException(e);
			} 
			DocumentBuilder db = factory.newDocumentBuilder();
			// define error handler class to report XML processing errors
			db.setErrorHandler(new XmlErrorHandler());
			return db;
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}		
	}
	
	/**
	 * Parse DOM document and extract users information from it.
	 * 
	 * @param doc
	 * @return list of users
	 */
	private List<User> extractUsers(Document doc)
	{
		List<User> users = new LinkedList<User>();

		// go through the Document and extract users info
		Element root = doc.getDocumentElement();
		for (Node userNode = root.getFirstChild(); userNode != null; userNode = userNode.getNextSibling())
		{	
			if (!XmlSchemaConst.Entities.USER.equals(userNode.getNodeName())){
//				System.out.println("Skipping node: " + userNode);
				continue;
			}
			
			User user = new User();
			NodeList userNodes = userNode.getChildNodes();
			for (int i=0; i<userNodes.getLength(); i++){
				Node node = userNodes.item(i);
				if (XmlSchemaConst.User.USERNAME.equals(node.getNodeName())){
					user.setUsername(node.getTextContent());	
					continue;
				}
				if (XmlSchemaConst.User.PASSWORD.equals(node.getNodeName())){
					user.setPassword(node.getTextContent());
					continue;
				}
				if (XmlSchemaConst.User.FULL_NAME.equals(node.getNodeName())){
					user.setFullName(node.getTextContent());
					continue;
				}
				if (XmlSchemaConst.User.EMAIL.equals(node.getNodeName())){
					user.setEmail(node.getTextContent());
					continue;
				}
				if (XmlSchemaConst.User.FACEBOOK_ID.equals(node.getNodeName())){
					user.setFacebookId(node.getTextContent());
					continue;
				}
			}
			users.add(user);
		}
		return users;
	}	
}