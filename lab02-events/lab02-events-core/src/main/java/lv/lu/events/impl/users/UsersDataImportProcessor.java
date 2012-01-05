package lv.lu.events.impl.users;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.persistence.Query;

import lv.lu.events.interfaces.CommonDAO;


import lv.lu.events.interfaces.DataImportProcessor;
import lv.lu.events.interfaces.XmlDataProvider;
import lv.lu.events.interfaces.XmlParser;
import lv.lu.events.constants.JPQConst;
import lv.lu.events.model.User;

/**
 * Default main processor implementation.
 */
public class UsersDataImportProcessor implements DataImportProcessor {

	// loads XML files from configured sources
	private XmlDataProvider dataProvider;	
	
	// parses XML files and converts data to Java objects
	private XmlParser xmlParser;
	
	// database operations
	private CommonDAO commonDAO;
	
	// flag to delete everything from database before start
	private boolean cleanDB;
	
	/**
	 * Main method, which does all the processing
	 */
	public void importData() {
		log("Started...");
		
		if (cleanDB){
			commonDAO.cleanupDB();
		}
		
		/**
		 * Load users data from XML and save new records in database
		 */
		InputStream usersXml = dataProvider.loadUsersXml();		// load XML with users data
		if (usersXml != null){

			// parse XML file and extract user objects from it
			List<User> users = xmlParser.parseUsers(usersXml);
			
			// close XML file input stream
			try {
				usersXml.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// process users retrieved from XML file
			if (users != null && !users.isEmpty()){
				for (User user: users){
					
					// check if user with such username already exists in database
					if (!isUserPresentInDatabase(user.getUsername())){
						// user does not exist, create new record
						commonDAO.save(user);
						log("User [" + user.getUsername() + "] saved to database");
					}
					else{
						// user already exists, use retrieved object
						log("User [" + user.getUsername() + "] already exists in database");
					}
				}	
			}
			else{
				log("No users found");
			}
		}
		else{
			log("Users XML file was not loaded");
		}
		log("Finished!");
	}
	
	/**
	 * Checks if user with provided username already exists in database
	 * @return true is user already exists, false otherwise
	 */
	@SuppressWarnings("unchecked")
    private boolean isUserPresentInDatabase(String username){
		Query query = commonDAO.getEntityManager().createNamedQuery(JPQConst.UserJpq.QUERY_GET_ID_BY_USERNAME);
		query.setParameter("username", username);
		List<Long> ids = (List<Long>)query.getResultList();
		return !ids.isEmpty();
	}
		
	private void log(String message){
		System.out.println("[" + this.getClass().getSimpleName() + "] " + message);
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	public void setXmlParser(XmlParser xmlParser) {
		this.xmlParser = xmlParser;
	}

	public void setDataProvider(XmlDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	public void setCleanDB(boolean cleanDB) {
		this.cleanDB = cleanDB;
	}
}
