package lv.stradini.domain;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestHibernate {

	/**
	 * @param args
	 */
	public static void malin(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.configure(); //reads hibernate.cfg.xml
		
		new SchemaExport(config).create(true, true);
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		createResidentToCycleRelations(session);
//		createDepartmentsAndFacilities(session);

	}
	
	private static void createResidentToCycleRelations(Session session) {
		session.beginTransaction();
		
		Resident res1 = new Resident();
		res1.setVards("John");
		res1.setUzvards("Doe");
		res1.setPersonasKods("075656-34567");
		res1.setAdrese("asdfasdfa");
		res1.setDarbaLigums("darbaLigums");
		res1.setEpasts("epasts");
		res1.setTalrunaNumurs("131231231312");
		res1.setStudijuGads("2011");
		res1.setSpecialitate("specialitate");
		res1.setUniversitate("LU");
		
		Resident res2 = new Resident();
		res2.setVards("Mary");
		res2.setUzvards("Willson");
		res2.setPersonasKods("075656-31111");
		res2.setAdrese("sdfa");
		res2.setDarbaLigums("darbaLigums2");
		res2.setEpasts("epasts2");
		res2.setTalrunaNumurs("1312312313122");
		res2.setStudijuGads("20112");
		res2.setSpecialitate("specialitate2");
		res2.setUniversitate("LU2");
		
		session.save(res1);
		session.save(res2);
		
		Facility fac1 = new Facility();
		fac1.setNosaukums("Stradina slimnica");
		Facility fac2 = new Facility();
		fac2.setNosaukums("1. Rigas slimnica");
		Facility fac3 = new Facility();
		fac3.setNosaukums("Gailezera slimnica");
		
		Doctor doc1 = new Doctor();
		doc1.setVards("Gregory");
		doc1.setUzvards("House");
		doc1.setPersonasKods("030303-12121");
		doc1.setAdrese("Madisson avenue 1-1");
		doc1.setAkademiskaisGrads("1978");
		doc1.setDarbaVieta("Princeton university hospital");
		doc1.setEpasts("g.house@gmail.com");
		doc1.setTalrunaNumurs("23423432");
		doc1.setSpecialitate("Diagnosisj");
		
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
		
		Cycle cycle = new Cycle();
		cycle.setDepartment(dep1);
		cycle.setDepartmentFk(dep1.getDepartmentPk());
		cycle.setSakumaDatums(new Date());
		cycle.setBeiguDatums(new Date());
		cycle.setPasniedzejs(doc1);
		
		Cycle cyc2 = new Cycle();
		cyc2.setDepartment(dep2);
		cyc2.setDepartmentFk(dep2.getDepartmentPk());
		cyc2.setSakumaDatums(new Date());
		cyc2.setBeiguDatums(new Date());
		cyc2.setPasniedzejs(doc1);
		
		doc1.getCycleList().add(cycle);
		doc1.getCycleList().add(cyc2);
		
		session.save(cycle);
		session.save(cyc2);
		session.save(doc1);
		
		ResidentCycle residentCycle = new ResidentCycle();
		residentCycle.setResident(res1);
		residentCycle.setCycle(cycle);
		residentCycle.setPassed(true);
		
		ResidentCycle residentCycle2 = new ResidentCycle();
		residentCycle2.setResident(res2);
		residentCycle2.setCycle(cyc2);
		residentCycle2.setPassed(false);
		
		res1.getResidentCycleList().add(residentCycle);
		cycle.getResidentCycleList().add(residentCycle);
		res2.getResidentCycleList().add(residentCycle2);
		cyc2.getResidentCycleList().add(residentCycle2);
		
		session.save(residentCycle);
		session.save(residentCycle2);
		session.update(res1);
		session.update(res2);
		session.update(cycle);
		session.update(cyc2);
		
		ResidentCycle residentCycle3 = new ResidentCycle();
		residentCycle3.setResident(res2);
		residentCycle3.setCycle(cycle);
		residentCycle3.setPassed(false);
		
		res2.getResidentCycleList().add(residentCycle3);
		cycle.getResidentCycleList().add(residentCycle3);
		
		session.save(residentCycle3);
		session.update(res2);
		session.update(cycle);
		
		session.getTransaction().commit();
		
		System.out.println("Completed!!!");
	}
	
	private static void createDepartmentsAndFacilities(Session session) {
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
		
		
		Cycle cycle = new Cycle();
		cycle.setDepartment(dep1);
		cycle.setDepartmentFk(dep1.getDepartmentPk());
		cycle.setSakumaDatums(new Date());
		cycle.setBeiguDatums(new Date());
		cycle.setPasniedzejs(doc1);
		
		doc1.getCycleList().add(cycle);
		
		session.save(cycle);
		session.save(doc1);
		
		session.getTransaction().commit();
		
//		session.close();
		System.out.println("Completed!!!");
	}
}
