package lv.lu.events.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lv.lu.events.impl.CommonDAOImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Base test for all tests working with persistence. 
 */
public abstract class PersistenceBaseTest extends BaseTest {

	private final boolean CLEAN_UP_DB_ON_SETUP = true;
	private final boolean COMMIT_ON_TEAR_DOWN = false;
	
	// persistent units defined in \lab01-events\src\main\resources\META-INF\persistence.xml
	@SuppressWarnings("unused")
    private static final String PU_HIBERNATE_MYSQL = "hibernate_mysql";
	private static final String PU_HIBERNATE_HSQLDB = "hibernate_hsqldb";
	
	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	protected static CommonDAOImpl commonDAO;

	@BeforeClass
	public static void setUpBeforeClass() {
		// initialize JPA entity manager
		emf = Persistence.createEntityManagerFactory(PU_HIBERNATE_HSQLDB);
		em = emf.createEntityManager();
		commonDAO = new CommonDAOImpl();
		commonDAO.setEntityManager(em);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		// close JPA entity manager
		if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
	}
	
	@Before
	public void setUp() {		
		em.getTransaction().begin();
		
		if (CLEAN_UP_DB_ON_SETUP){
			// delete everything from DB and execute test on a clean schema
			commonDAO.cleanupDB();
		}
	}
	
	@After
	public void tearDown() {
		if (COMMIT_ON_TEAR_DOWN){
			// commit all database changes made during the test
			em.getTransaction().commit();
		}
		else{
			// roll-back all database changes made during the test
			em.getTransaction().rollback();
		}
	}
	
	protected static void flushAndClear(){
		em.flush();		// flush changes to data store
		em.clear();		// clear persistence context
	}
}
