package lv.lu.events.xml;

/**
 * XML data as String to be used during tests.
 */
public final class XmlTestData {
	
	// version of users.xml file used in tests
	public static final String USERS_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
		+ "<users>"
			+ "<user>"
				+ "<full-name>John White</full-name>"
				+ "<username>jwhite</username>"
				+ "<password>jwhitepass</password>"
				+ "<email>jwhite@eventsXXX.com</email>"
				+ "<facebook-id>100002774971272</facebook-id>"
			+ "</user>"
			+ "<user>"
				+ "<full-name>David Green</full-name>"
				+ "<username>dgreen</username>"
				+ "<password>dgreenpass</password>"
				+ "<email>dgreen@eventsXXX.com</email>"
				+ "<facebook-id>100002808391341</facebook-id>"
			+ "</user>"
			+ "<user>"
				+ "<full-name>Ann Blue</full-name>"
				+ "<username>ablue</username>"
				+ "<password>abluepass</password>"
				+ "<email>ablue@eventsXXX.com</email>"
				+ "<facebook-id>100002771239557</facebook-id>"
			+ "</user>"
		+ "</users>";	

	private XmlTestData() {
	}
}