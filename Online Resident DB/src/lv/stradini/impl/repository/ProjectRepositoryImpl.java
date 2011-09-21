package lv.stradini.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import lv.stradini.domain.EmployeeProjectDetail;
import lv.stradini.domain.Project;
import lv.stradini.domain.ProjectRole;
import lv.stradini.interfaces.repository.ProjectRepository;

import org.apache.log4j.Logger;

public class ProjectRepositoryImpl implements ProjectRepository {

	//DataSource class encapsulates the driver, database url, username and
	//password information. The DataSource object is automatically created by
	//the Spring framework and passed to the constructor therefore there's no need
	//to instantiate the dataSource variable. A connection can be acquired by
	//accessing the getConnection method of dataSource.
	//
	//Tip: create member variables in this class that will contain the objects
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(ProjectRepositoryImpl.class);
	private DataSource dataSource;


	public ProjectRepositoryImpl() {
	}

	public ProjectRepositoryImpl(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	@Override
	public List<Project> listAllProjects() {
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		log.info("Trying to listAllProjects");

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM project ORDER BY project_id ASC;");

			while (rs.next()) {
				list.add(new Project(rs.getLong("project_id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("client")
				));
			}
		}
		catch (SQLException e) {
			log.error("SQL Exception in listAllProjects: " + e.getMessage(), e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			log.info("Trying to close connection after listAllProjects");
			closeConnection(rs, st, conn);
		}

		return list;
	}

	@Override
	public List<EmployeeProjectDetail> getEmployeeProjectHistory(long employeeID) {

		log.info("Trying to get employee project history for employeeID: " + employeeID);

		List<EmployeeProjectDetail> detailList = new ArrayList<EmployeeProjectDetail>();

		List<Project> projectList = getEmployeeProjects(employeeID);
		for (Project project : projectList) {
			List<ProjectRole> roleList = getEmployeeProjectRoles(employeeID, project.getID());
			detailList.add(new EmployeeProjectDetail(project, roleList));
		}

		return detailList;
	}

	@Override
	public List<ProjectRole> getEmployeeProjectRoles(long employeeID, long projectID) {

		log.info("Trying to getEmployeeProjectRoles for: " + employeeID + " , " + projectID);

		List<ProjectRole> list = new ArrayList<ProjectRole>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String query = "SELECT role.role, project_role.* FROM project_role, role "
				+ "WHERE project_role.role_id = role.role_id AND employee_project_detail_id = " +
				"(SELECT employee_project_detail_id FROM employee_project_detail " +
				"WHERE project_id = " + projectID + " AND employee_id = " + employeeID + ");";

			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				list.add(new ProjectRole(rs.getLong("project_role_id"),
						rs.getString("role"),
						rs.getLong("employee_project_detail_id"),
						rs.getDate("start_date").toString(),
						rs.getDate("end_date").toString()
				));
			}
		}
		catch (SQLException e) {
			log.error("SQL exception in getEmployeeProjectRoles", e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}


		return list;
	}

	@Override
	public List<Project> getEmployeeProjects(long employeeID) {

		log.info("Trying to getEmployeeProjects for employeeID: " + employeeID);

		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT project.* FROM employee_project_detail, project " +
					"WHERE employee_project_detail.project_id = project.project_id AND employee_id = " + employeeID +
			" ORDER BY project_id ASC;");

			while (rs.next()) {
				list.add(new Project(rs.getLong("project_id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("client")
				));
			}
		}
		catch (SQLException e) {
			log.error("SQL Exception in getEmployeeProjects: ",	 e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}

		return list;
	}

	private void closeConnection(ResultSet rs, Statement st, Connection conn) {
		try {
			log.info("Trying to close connections");
			rs.close();
			st.close();
			conn.close();
			log.info("Connections closed");
		} catch (Exception e) {
			log.error("Error during closing connection: " + e.getMessage(), e);
		}
	}

	private void closeConnection(Statement st, Connection conn) {
		try {
			log.info("Trying to close connections");
			st.close();
			conn.close();
			log.info("Connections closed");
		} catch (Exception e) {
			log.error("Error during closing connection: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean insertProject(String name, String description, String client) {

		log.info("Trying to insertProject:" + name);

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("INSERT INTO project VALUES(null, ?, ?, ?);");

			st.setString(1, name);
			st.setString(2, description);
			st.setString(3, client);

			if (st.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {

			log.error("SQL Exception in insertProject: ", e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);

		} finally {

			closeConnection(st, conn);
		}

		return false;
	}

	@Override
	public Project getProjectByID(long projectID) {

		log.info("Trying to getProjectByID: " + projectID);

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("SELECT * FROM project WHERE project_id = " + projectID);

			rs.next();
			Project project = new Project(rs.getLong("project_id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getString("client"));

			return project;
		}
		catch (SQLException e) {

			log.error("SQL Exception in getProjectByID", e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}
	}

	@Override
	public boolean updateProject(Project project) {

		log.info("Trying to updateProject: " + project.getName());

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("UPDATE project SET name = ?, description = ?, " +
			"client = ? WHERE project_id = ?;");

			st.setString(1, project.getName());
			st.setString(2, project.getDescription());
			st.setString(3, project.getClient());
			st.setLong(4, project.getID());

			if (st.executeUpdate() == 1) {
				return true;
			}
		}
		catch (SQLException e) {

			log.error("SQL Exception in updateProject", e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}

		return false;
	}

	@Override
	public boolean deleteProjectByID(long projectID) {

		log.info("Trying to deleteProjectByID: " + projectID);

		Connection conn = null;
		Statement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			st.executeUpdate("DELETE FROM project_role WHERE employee_project_detail_id IN " +
					" (SELECT employee_project_detail_id FROM employee_project_detail, project " +
					" WHERE project.project_id = employee_project_detail.project_id " +
					" AND  project.project_id = " + projectID + ");" );
			st.executeUpdate("DELETE FROM employee_project_detail WHERE project_id = " + projectID + ";");
			int rows = st.executeUpdate("DELETE FROM project WHERE project_id = " + projectID + ";");

			if(rows > 0) {
				return true;
			}

		}
		catch (SQLException e) {

			log.error("SQL Exception in deleteProjectByID", e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}

		return false;
	}

	@Override
	public boolean insertEmployeeProjectDetailList(long projectID, List<Long> employeeIDList) {
		boolean result = false;
		for (Long employeeID : employeeIDList) {
			result = insertEmployeeProjectDetail(projectID, employeeID);
			if (result == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean insertEmployeeProjectDetail(long projectID, long employeeID) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();

			st = conn.prepareStatement("INSERT INTO employee_project_detail VALUES(null, ?, ?);");
			st.setLong(1, projectID);
			st.setLong(2, employeeID);

			return st.executeUpdate() == 1;
		} catch(SQLException e) {
			log.error("SQL Exception in addEmployeeToProject", e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		} finally {
			closeConnection(st, conn);
		}
	}

	@Override
	public boolean deleteEmployeeProjectDetail(long projectID, long employeeID) {
		log.info("Trying to unassign an employee from the project");
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();

			//Removing project roles for this employee-to-project link
			st = conn.prepareStatement("DELETE FROM project_role " +
					"WHERE employee_project_detail_id IN " +
					"(SELECT employee_project_detail_id " +
					"FROM employee_project_detail " +
			"WHERE project_id = ? AND employee_id = ?);");

			st.setLong(1, projectID);
			st.setLong(2, employeeID);
			st.executeUpdate();
			st.close();

			st = conn.prepareStatement("DELETE FROM employee_project_detail " +
			"WHERE project_id=? AND employee_id=?;");
			st.setLong(1, projectID);
			st.setLong(2, employeeID);

			return st.executeUpdate() == 1;
		} catch (SQLException e) {
			log.error("SQL Exception in removeEmployeeFromProject", e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		} finally {
			closeConnection(st, conn);
		}
	}

	@Override
	public boolean deleteEmployeeProjectDetailList(long projectID, List<Long> employeeIDList) {

		log.info("Trying to removeEmployeeFromProject for project: " + projectID);

		Connection conn = null;
		PreparedStatement st = null;

		int employeeCount = employeeIDList.size();
		int count = 0;

		try {
			conn = dataSource.getConnection();

			st = conn.prepareStatement("DELETE FROM project_role " +
					"WHERE employee_project_detail_id IN " +
					"(SELECT employee_project_detail_id " +
					"FROM employee_project_detail " +
			"WHERE project_id =? AND employee_id =?);");

			for (Long employeeId : employeeIDList) {
				st.setLong(1, projectID);
				st.setLong(2, employeeId.longValue());

				st.executeUpdate();
			}


			st = conn.prepareStatement("DELETE FROM employee_project_detail " +
			"WHERE project_id=? AND employee_id=?;");

			for (Long employeeId : employeeIDList) {
				st.setLong(1, projectID);
				st.setLong(2, employeeId.longValue());

				if(st.executeUpdate() > 0) {
					count++;
				}
			}

			if(count == employeeCount) {
				return true;
			}

		} catch (SQLException e) {

			log.error("SQL Exception in removeEmployeeFromProject", e);
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);

		} finally {

			closeConnection(st, conn);
		}

		return false;
	}
}
