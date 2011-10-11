package lv.stradini.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import lv.stradini.domain.Cycle;
import lv.stradini.domain.Department;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Facility;
import lv.stradini.domain.Heart;
import lv.stradini.domain.Resident;
import lv.stradini.domain.ResidentCycle;
import lv.stradini.domain.ResidentCycleId;
import lv.stradini.interfaces.repository.ResidentRepository;
import lv.stradini.util.LoggerUtils;
import lv.stradini.util.Utils;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ResidentRepositoryImpl implements ResidentRepository {

	private static Logger logger = Logger.getLogger(LoggerUtils
			.getClassName(ResidentRepositoryImpl.class));
	private final DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;

	public ResidentRepositoryImpl(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	@Override
	public List<Resident> fetchAllResidents() {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(Resident.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Resident> resList = crit.list();
		session.close();
		return resList;
	}

	private LinkedList<Heart> fetchHeartsByResidentID(int residentID) {
		LinkedList<Heart> heartList = new LinkedList<Heart>();

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String query = "SELECT * FROM HEART WHERE RESIDENT_FK = ?;";
		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement(query);
			st.setLong(1, residentID);
			rs = st.executeQuery();

			while (rs.next()) {
				heartList.add(new Heart(rs.getInt("HEART_PK"), rs
						.getString("TIPS"), rs.getString("KOMENTARI")));
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}

		return heartList;
	}

	@Override
	public List<Doctor> fetchAllDoctors() {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(Doctor.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Doctor> doctorList = crit.list();
		session.close();
		return doctorList;
	}

	@Override
	public Resident findResidentByID(int residentID) {
		Session session = sessionFactory.openSession();
		Resident result = (Resident) session.get(Resident.class, residentID);
		Hibernate.initialize(result.getResidentCycleList());
		session.close();
		return result;
	}

	private void closeConnection(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			logger.error("Error during closing result set: " + e.getMessage(),
					e);
		}
		closeConnection(st, conn);
	}

	private void closeConnection(Statement st, Connection conn) {
		try {
			if (st != null) {
				st.close();
			}
			if (st != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("Error during closing connection: " + e.getMessage(),
					e);
		}
	}

	@Override
	public Doctor findDoctorByID(int doctorID) {
		Session session = sessionFactory.openSession();
		Doctor result = (Doctor) session.get(Doctor.class, doctorID);
		//TODO: Make a separate method for lazy collection initialization
		Hibernate.initialize(result.getCycleList());
		Hibernate.initialize(result.getDepartmentList());
		session.close();
		return result;
	}

	@Override
	public boolean insertResident(Resident resident) {
		logger.info("In insertResident(resident)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(resident);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public boolean deleteResidentByID(int residentID) {
		logger.info("In deleteResidentByID(residentID)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Resident resident = findResidentByID(residentID);
			System.out.println("Heart list size = "
					+ resident.getHeartList().size());
			for (Heart heart : resident.getHeartList()) {
				resident.removeHeart(heart);
				session.delete(heart);
			}

			session.delete(resident);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public int getResidentCountByPersonasKods(String personasKods) {
		logger.info("Location");
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int count = 0;
		String query = "SELECT * FROM resident WHERE PERSONAS_KODS = ? ";
		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement(query);
			st.setString(1, personasKods);
			rs = st.executeQuery();

			while (rs.next()) {
				count++;
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return count;
	}
	
	@Override
	public int getDoctorCountByPersonasKods(String newPersonasKods) {
		logger.info("Location");
		int result = -1;
		try {
			Session session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getDoctorCountByPersonasKods").setString("personasKods", newPersonasKods);
			List resultList = query.list();
			result = resultList.size();
			session.close();
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return result;
	}

	@Override
	public boolean updateResident(Resident resident) {
		logger.info("In updateResident(resident)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(resident);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public Resident findResidentByPersonasKods(String personasKods) {
		Resident resident = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM resident WHERE PERSONAS_KODS = ? ";
		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement(query);
			st.setString(1, personasKods);
			rs = st.executeQuery();

			while (rs.next()) {
				resident = new Resident(rs.getInt("RESIDENT_PK"),
						rs.getString("VARDS"), rs.getString("UZVARDS"),
						rs.getString("PERSONAS_KODS"),
						rs.getString("DARBA_LIGUMS"),
						rs.getString("SPECIALITATE"),
						rs.getString("UNIVERSITATE"),
						rs.getString("STUDIJU_GADS"), rs.getString("ADRESE"),
						rs.getString("TALRUNA_NUMURS"), rs.getString("EPASTS"),
						rs.getString("KOMENTARI"));
				resident.setHeartList(fetchHeartsByResidentID(resident
						.getResidentPk()));
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return resident;
	}

	@Override
	public boolean deleteHeartByID(int heartID) {
		logger.info("In deleteResidentByID(residentID)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Heart heart = findHeartByID(heartID);
			session.delete(heart);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public Heart findHeartByID(int heartID) {
		Session session = sessionFactory.openSession();
		Heart heart = (Heart) session.get(Heart.class, heartID);
		session.close();
		return heart;
	}

	@Override
	public boolean insertHeart(Heart heart, int residentFK) {
		Resident res = findResidentByID(residentFK);
		logger.info("In insertResident(resident)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			res.addHeart(heart);
			session.saveOrUpdate(res);
			session.save(heart);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public boolean updateHeart(Heart heart) {
		logger.info("In updateResident(resident)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(heart);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public boolean insertCycle(Cycle cycle) {
		logger.info("Location");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(cycle);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	private long findFacilityToDepartmentByID(long facilityID, long departmentID) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT FAC_TO_DEP_PK FROM FACILITY_TO_DEPARTMENT WHERE FACILITY_FK = ? and DEPARTMENT_FK = ? ";
		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement(query);
			st.setLong(1, facilityID);
			st.setLong(2, departmentID);
			rs = st.executeQuery();

			while (rs.next()) {
				return rs.getLong("FAC_TO_DEP_PK");
			}
		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return 0;
	}

	@Override
	public List<Facility> fetchAllFacilities() {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(Facility.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Facility> facilityList = crit.list();
		session.close();
		return facilityList;
	}
/*
	private LinkedList<Department> findDepartmentsByFacilityID(int facilityID) {
		LinkedList<Department> departmentList = new LinkedList<Department>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT * FROM DEPARTMENT WHERE FACILITY_FK = ?;";
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			Department department;
			while (rs.next()) {
				department = new Department(rs.getInt("DEPARTMENT_PK"),
						rs.getInt("FACILITY_FK"), rs.getInt("DOCTOR_FK"),
						rs.getString("NOSAUKUMS"));
				departmentList.add(department);
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return departmentList;
	}
*/
	@Override
	public List<Department> fetchAllDepartments() {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(Department.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Department> departmentList = crit.list();
		session.close();
		return departmentList;
	}

	@Override
	public boolean deleteHeart(Heart heart) {
		logger.info("In deleteHeart(heart)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(heart);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public boolean deleteResident(Resident resident) {
		logger.info("In deleteResident(resident)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			System.out.println("Heart list size = "
					+ resident.getHeartList().size());
			// This for some reason throws ConcurentModificationException
			// for(Heart heart : resident.getHeartList()) {
			// resident.removeHeart(heart);
			// }
			List<Heart> heartList = resident.getHeartList();
			int size = resident.getHeartList().size();
			for (int i = 0; i < size; i++) {
				resident.removeHeart(heartList.get(0));
			}

			session.delete(resident);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public boolean insertDoctor(Doctor doctor) {
		logger.info("Location");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(doctor);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public boolean updateDoctor(Doctor doctor) {
		logger.info("In updateResident(resident)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(doctor);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public boolean deleteDoctor(Doctor doctor) {
		logger.info("In deleteResident(resident)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(doctor);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}

	@Override
	public Department findDepartmentByID(int departmentFk) {
		Session session = sessionFactory.openSession();
		Department result = (Department) session.get(Department.class, departmentFk);
		session.close();
		return result;
	}

	@Override
	public List<Cycle> fetchAllCycles() {
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(Cycle.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Cycle> cycleList = crit.list();
		session.close();
		return cycleList;
	}

	@Override
	public Cycle findCycleByID(int i) {
		Session session = sessionFactory.openSession();
		Cycle result = (Cycle) session.get(Cycle.class, i);
		Hibernate.initialize(result.getResidentCycleList());
		session.close();
		return result;
	}

	@Override
	public ResidentCycle findResidentCycleByID(ResidentCycleId resCycId) {
		Session session = sessionFactory.openSession();
		ResidentCycle result = (ResidentCycle) session.get(ResidentCycle.class, resCycId);
		session.close();
		return result;
	}

	@Override
	public boolean insertResidentCycle(Resident resident, Cycle cycle) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			ResidentCycle resCyc = new ResidentCycle();
			resCyc.setResident(resident);
			resCyc.setCycle(cycle);
			
			resident.getResidentCycleList().add(resCyc);
			cycle.getResidentCycleList().add(resCyc);
			
			session.save(resCyc);
			session.update(resident);
			session.update(cycle);
			
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException he) {
			logger.error("", he);
		}
		return false;
	}
}
