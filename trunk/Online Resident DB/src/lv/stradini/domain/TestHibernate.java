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
		config.addAnnotatedClass(Heart.class);
		config.addAnnotatedClass(Resident.class);
		config.configure(); //reads hibernate.cfg.xml
		
		new SchemaExport(config).create(true, true);
		
//		SessionFactory factory = config.buildSessionFactory();
//		Session session = factory.getCurrentSession();
//		
//		session.beginTransaction();
//
//		Resident john = new Resident();
//		john.setVards("John");
//		john.setUzvards("Doe");
//		john.setPersonasKods("070707-12345");
//		john.setDarbaLigums("dl");
//		john.setSpecialitate("Speciality");
//		john.setUniversitate("university");
//		john.setStudijuGads("studiju gads");
//		john.setAdrese("adrese");
//		john.setTalrunaNumurs("23455435");
//		john.setEpasts("a@a.com");
//		john.setKomentari("Komentari");
//		
//		Resident sam = new Resident();
//		sam.setVards("Sam");
//		sam.setUzvards("Fan");
//		sam.setPersonasKods("070707-12345");
//		sam.setDarbaLigums("dl");
//		sam.setSpecialitate("Speciality");
//		sam.setUniversitate("university");
//		sam.setStudijuGads("studiju gads");
//		sam.setAdrese("adrese");
//		sam.setTalrunaNumurs("23455435");
//		sam.setEpasts("a@a.com");
//		sam.setKomentari("Komentari");
//		
//		Heart heart = new Heart();
//		heart.setKomentari("Painted on walls");
//		heart.setTips("green");
//		
//		Heart heart2 = new Heart();
//		heart2.setKomentari("Painted on walls");
//		heart2.setTips("black");
//		
//		Heart heart3 = new Heart();
//		heart3.setKomentari("Painted on walls");
//		heart3.setTips("black");
//
//		Heart heart4 = new Heart();
//		heart4.setKomentari("Painted on walls");
//		heart4.setTips("black");
//		
//		john.getHeartList().add(heart);
//		john.getHeartList().add(heart2);
//		sam.getHeartList().add(heart3);
//		sam.getHeartList().add(heart4);
//		
//		heart.setResident(john);
//		heart2.setResident(john);
//		heart3.setResident(sam);
//		heart4.setResident(sam);
//		
//		session.save(sam);
//		session.save(john);
//		session.save(heart);
//		session.save(heart2);
//		session.save(heart3);
//		session.save(heart4);
//		
//		session.getTransaction().commit();
//		
//		session = factory.openSession();
//		Criteria crit = session.createCriteria(Resident.class);
////		crit.add(Restrictions.eq("residentPk", 1));
//		List<Resident> resList = crit.list();
//		System.out.println("Res list size = " + resList.size());
//		for (Resident res : resList) {
//			System.out.println("----------------");
//			System.out.println(res.getResidentPk());
//			System.out.println(res.getAdrese());
//			System.out.println(res.getVards());
//			System.out.println(res.getUzvards());
//			System.out.println("Size = " + res.getHeartList().size());
//		}
//		
//		session.close();
//		
		
//		Resident johnFromPersistance = (Resident) session.get(Resident.class, 1L);
//		System.out.println(johnFromPersistance.getAdrese());
//		System.out.println(johnFromPersistance.getVards());
//		System.out.println(johnFromPersistance.getUzvards());
//		System.out.println("Size = " + johnFromPersistance.getHeartList().size());
	}
}
