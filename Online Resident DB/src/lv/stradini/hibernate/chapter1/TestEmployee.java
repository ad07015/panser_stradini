package lv.stradini.hibernate.chapter1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestEmployee {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(Employee.class);
		config.addAnnotatedClass(Resident.class);
		config.configure(); //reads hibernate.cfg.xml
		
		new SchemaExport(config).create(true, true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
//		Resident john = new Resident(0, "John", "Doe", "070707-12345", "dl", "sp", "un", "stG", "adrese", "23423553", "a@a.com", "komentari");
		
		Employee emp = new Employee();
		emp.setEmpName("Alex");
		
		Employee johnE = new Employee();
		johnE.setEmpName("John");
		
		session.save(emp);
		session.save(johnE);
//		session.save(john);
		
		session.getTransaction().commit();
	}
}
