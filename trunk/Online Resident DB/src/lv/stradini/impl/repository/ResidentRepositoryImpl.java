package lv.stradini.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import lv.stradini.domain.Cycle;
import lv.stradini.domain.Department;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Facility;
import lv.stradini.domain.Heart;
import lv.stradini.domain.Resident;
import lv.stradini.interfaces.repository.ResidentRepository;
import lv.stradini.util.Utils;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class ResidentRepositoryImpl implements ResidentRepository {

	private static Logger logger = Logger.getLogger(ResidentRepositoryImpl.class);
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
				heartList.add(new Heart(rs.getInt("HEART_PK"),
						rs.getString("TIPS"),
						rs.getString("KOMENTARI")));
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
		List<Doctor> doctorList = new ArrayList<Doctor>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT * FROM doctor;";
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				doctorList.add(new Doctor(rs.getInt("DOCTOR_PK"), rs
						.getString("VARDS"), rs.getString("UZVARDS"), rs
						.getString("PERSONAS_KODS"), rs
						.getString("AKADEMISKAIS_GRADS"), rs
						.getString("DARBA_VIETA"),
						rs.getString("SPECIALITATE"), rs.getString("ADRESE"),
						rs.getString("TALRUNA_NUMURS"), rs.getString("EPASTS"),
						rs.getString("KOMENTARI")));
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return doctorList;
	}

	@Override
	public Resident findResidentByID(int residentID) {
		Session session = sessionFactory.openSession();
		Resident result = (Resident) session.get(Resident.class, residentID);
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
		Doctor doctor = new Doctor();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM DOCTOR WHERE DOCTOR_PK = ? ";
		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement(query);
			st.setLong(1, doctorID);
			rs = st.executeQuery();

			while (rs.next()) {
				doctor = new Doctor(rs.getInt("DOCTOR_PK"),
						rs.getString("VARDS"), rs.getString("UZVARDS"),
						rs.getString("PERSONAS_KODS"),
						rs.getString("AKADEMISKAIS_GRADS"),
						rs.getString("DARBA_VIETA"),
						rs.getString("SPECIALITATE"), rs.getString("ADRESE"),
						rs.getString("TALRUNA_NUMURS"), rs.getString("EPASTS"),
						rs.getString("KOMENTARI"));
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return doctor;
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
				resident.setHeartList(fetchHeartsByResidentID(resident.getResidentPk()));
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
		logger.info("In insertResident(resident)");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Resident res = findResidentByID(residentFK);
			heart.setResident(res);
			res.getHeartList().add(heart);
			session.update(res);
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
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			st = conn.prepareStatement("INSERT INTO CYCLE VALUES(null, ?, ?, ?, ?);");

			long facilityToDepartment = findFacilityToDepartmentByID(cycle.getFacilityFK(), cycle.getDepartmentFK());
			
			st.setLong(1, facilityToDepartment);
			st.setLong(2, cycle.getVaditajs().getID());
			st.setDate(3, new java.sql.Date(cycle.getSakumaDatums().getTime()));
			st.setDate(4, new java.sql.Date(cycle.getBeiguDatums().getTime()));

			if (st.executeUpdate() == 1) {
				conn.commit();
				return true;
			}
			Utils.rollback(conn);
		} catch (SQLException e) {
			Utils.rollback(conn);
			logger.error("", e);
		} finally {
			Utils.setAutoCommit(conn, true);
			closeConnection(st, conn);
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
	public LinkedList<Facility> fetchAllFacilities() {
		LinkedList<Facility> facilityList = new LinkedList<Facility>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT * FROM FACILITY;";
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			Facility facility;
			while (rs.next()) {
				facility = new Facility(rs.getInt("FACILITY_PK"), rs.getString("NOSAUKUMS"));
				facilityList.add(facility);
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return facilityList;
	}

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
				department = new Department(rs.getInt("DEPARTMENT_PK"), rs.getInt("FACILITY_FK"), rs.getInt("DOCTOR_FK"), rs.getString("NOSAUKUMS"));
				departmentList.add(department);
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return departmentList;
	}

	@Override
	public LinkedList<Department> fetchAllDepartments() {
		LinkedList<Department> departmentList = new LinkedList<Department>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT * FROM DEPARTMENT;";
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			Department department;
			while (rs.next()) {
				department = new Department(rs.getInt("DEPARTMENT_PK"), rs.getInt("FACILITY_FK"), rs.getInt("DOCTOR_FK"), rs.getString("NOSAUKUMS"));
				departmentList.add(department);
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
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
}
