package lv.stradini.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.configure(); //reads hibernate.cfg.xml
		
		new SchemaExport(config).create(true, true);

		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Facility fac1 = new Facility();
		fac1.setNosaukums("Stradina slimnica");
		Facility fac2 = new Facility();
		fac2.setNosaukums("1. Rigas slimnica");
		Facility fac3 = new Facility();
		fac3.setNosaukums("Gailezera slimnica");
		
		Doctor doc1 = new Doctor();
		doc1.setVards("Pe4enko");
		doc1.setUzvards("Ivanovno");
		doc1.setPersonasKods("030303-12121");
		doc1.setAdrese("Derevnja pe4enkino");
		doc1.setAkademiskaisGrads("Doctors");
		doc1.setDarbaVieta("Kolhoz n2");
		doc1.setEpasts("a@a.com");
		doc1.setTalrunaNumurs("23423432");
		doc1.setSpecialitate("Pekarj");
		
		session.save(doc1);
		session.save(fac1);
		session.save(fac2);
		session.save(fac3);
		
		Department dep1 = new Department();
		dep1.setNosaukums("Urologijas nodala");
		Department dep2 = new Department();
		dep2.setNosaukums("Asinsrites nodala");
		
		doc1.getDepartmentList().add(dep1);
		doc1.getDepartmentList().add(dep2);
		
		fac1.getDepartmentList().add(dep1);
		fac1.getDepartmentList().add(dep2);
		
		dep1.setVaditajs(doc1);
		dep2.setVaditajs(doc1);
		
		dep1.setFacility(fac1);
		dep2.setFacility(fac1);
		
		session.save(dep1);
		session.save(dep2);
		
		session.getTransaction().commit();
		
//		session.close();
		System.out.println("Completed!!!");
	}
}
