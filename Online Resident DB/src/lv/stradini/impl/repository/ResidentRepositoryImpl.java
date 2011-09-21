package lv.stradini.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import lv.stradini.dataAccessObject.Doctor;
import lv.stradini.dataAccessObject.Resident;
import lv.stradini.interfaces.repository.ResidentRepository;
import lv.stradini.util.Utils;

import org.apache.log4j.Logger;

public class ResidentRepositoryImpl implements ResidentRepository {

	private static Logger logger = Logger.getLogger(ResidentRepositoryImpl.class);
	private final DataSource dataSource;

	public ResidentRepositoryImpl(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	@Override
	public List<Resident> fetchAllResidents() {
		List<Resident> residentList = new ArrayList<Resident>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String query = "SELECT * FROM resident;";
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				residentList.add(new Resident(rs.getInt("RESIDENT_PK"), rs
						.getString("VARDS"), rs.getString("UZVARDS"), rs
						.getString("PERSONAS_KODS"), rs
						.getString("DARBA_LIGUMS"), rs
						.getString("SPECIALITATE"), rs
						.getString("UNIVERSITATE"), rs
						.getString("STUDIJU_GADS"), rs.getString("ADRESE"), rs
						.getString("TALRUNA_NUMURS"), rs.getString("EPASTS"),
						rs.getString("KOMENTARI")));
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return residentList;
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
	public Resident findResidentByID(long residentID) {
		Resident resident = new Resident();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM resident WHERE RESIDENT_PK = ? ";
		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement(query);
			st.setLong(1, residentID);
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
			}

		} catch (SQLException se) {
			logger.error("SQLException has occured", se);
		} finally {
			closeConnection(rs, st, conn);
		}
		return resident;
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
	public Doctor findDoctorByID(long doctorID) {
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
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			st = conn.prepareStatement("INSERT INTO RESIDENT VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
			logger.info("Da�ko");
			
			st.setString(1, resident.getVards());
			st.setString(2, resident.getUzvards());
			st.setString(3, resident.getPersonasKods());
			st.setString(4, resident.getDarbaLigums());
			st.setString(5, resident.getSpecialitate());
			st.setString(6, resident.getUniversitate());
			st.setString(7, resident.getStudijuGads());
			st.setString(8, resident.getAdrese());
			st.setString(9, resident.getTalrunaNumurs());
			st.setString(10, resident.getEpasts());
			st.setString(11, resident.getKomentari());

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

	@Override
	public boolean deleteResidentByID(long residentID) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			st = conn.prepareStatement("DELETE FROM RESIDENT WHERE RESIDENT_PK = ?;");
			st.setLong(1, residentID);

			if (st.executeUpdate() == 1) {
				conn.commit();
				logger.info("Resident successfully removed");
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

	@Override
	public int findResidentByPersonasKods(String personasKods) {
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
}
