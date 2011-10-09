package lv.stradini.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import lv.stradini.domain.old.Employee;
import lv.stradini.domain.old.EmployeeSkill;
import lv.stradini.domain.old.ProjectRole;
import lv.stradini.domain.old.Role;
import lv.stradini.interfaces.repository.EmployeeRepository;
import lv.stradini.util.LoggerUtils;

import org.apache.log4j.Logger;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	//DataSource class encapsulates the driver, database url, username and
	//password information. The DataSource object is automatically created by
	//the Spring framework and passed to the constructor therefore there's no need
	//to instantiate the dataSource variable. A connection can be acquired by
	//accessing the getConnection method of dataSource.
	//
	//Tip: create member variables in this class that will contain the objects
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(LoggerUtils.getClassName(EmployeeRepositoryImpl.class));
	private final DataSource dataSource;

	public EmployeeRepositoryImpl(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	@Override
	public List<Employee> findEmployeesByName(String firstName, String lastName) {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		if (firstName.equals("") && lastName.equals("")) {
			return employeeList;
		}

		String query;
		if (firstName.equals("")) {
			query = "SELECT * FROM employee WHERE last_name LIKE '%"
				+ lastName + "%' ORDER BY employee_id ASC;";
		} else if (lastName.equals("")) {
			query = "SELECT * FROM employee WHERE first_name LIKE '%"
				+ firstName + "%' ORDER BY employee_id ASC;";
		} else {
			query = "SELECT * FROM employee WHERE first_name LIKE '%"
				+ firstName + "%' OR last_name LIKE '%"
				+ lastName + "%' ORDER BY employee_id ASC;";
		}

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement(query);
			rs = st.executeQuery(query);

			while(rs.next()) {
				employeeList.add(new Employee(rs.getInt("employee_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("middle_initial"),
						rs.getString("level"),
						rs.getString("work_force"),
						rs.getString("enterprise_id")));
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}

		return employeeList;
	}

	@Override
	public Employee findEmployeeByID(long employeeID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("SELECT * FROM employee WHERE employee_id = " + employeeID);

			rs.next();
			Employee employee = new Employee(rs.getInt("employee_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("middle_initial"),
					rs.getString("level"),
					rs.getString("work_force"),
					rs.getString("enterprise_id"));

			return employee;
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}
	}

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("SELECT * FROM employee WHERE employee_id IN ( "
					+ "SELECT employee_id FROM employee_project_detail WHERE project_id = ? "
					+ ") ORDER BY employee_id ASC;");

			st.setString(1, Long.toString(projectID));
			rs = st.executeQuery();

			while(rs.next()) {
				employeeList.add(new Employee(rs.getLong("employee_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("middle_initial"),
						rs.getString("level"),
						rs.getString("work_force"),
						rs.getString("enterprise_id")));
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}

		return employeeList;
	}

	@Override
	public List<Employee> findEmployeesNotInProject(long projectID) {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("SELECT * FROM employee WHERE employee_id NOT IN ( "
					+ "SELECT employee_id FROM employee_project_detail WHERE project_id = ? "
					+ ") ORDER BY employee_id ASC;");

			st.setString(1, Long.toString(projectID));
			rs = st.executeQuery();

			while(rs.next()) {
				employeeList.add(new Employee(rs.getLong("employee_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("middle_initial"),
						rs.getString("level"),
						rs.getString("work_force"),
						rs.getString("enterprise_id")));
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}

		return employeeList;
	}

	public boolean insertEmployee(String firstName, String lastName,
			String middleInitial, String level, String workForce,
			String enterpriseID) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("INSERT INTO employee VALUES(null, ?, ?, ?, ?, ?, ?);");

			st.setString(1, firstName);
			st.setString(2, lastName);
			st.setString(3, middleInitial);
			st.setString(4, level);
			st.setString(5, workForce);
			st.setString(6, enterpriseID);

			if (st.executeUpdate() == 1) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}

		return false;
	}

	@Override
	public List<EmployeeSkill> findEmployeeSkills(long employeeID) {
		List<EmployeeSkill> skillList = new ArrayList<EmployeeSkill>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("SELECT * FROM employee_skill WHERE employee_id = ?;");

			st.setString(1, Long.toString(employeeID));
			rs = st.executeQuery();

			while(rs.next()) {
				skillList.add(new EmployeeSkill(rs.getLong("employee_skill_id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getInt("rating"),
						rs.getLong("employee_id")));
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}

		return skillList;
	}

	@Override
	public boolean removeEmployeeByID(long employeeID) {
		Connection conn = null;
		Statement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			st.executeUpdate("DELETE FROM employee_project_detail WHERE employee_id = " + employeeID + ";");
			st.executeUpdate("DELETE FROM employee_skill WHERE employee_id = " + employeeID + ";");

			if (st.executeUpdate("DELETE FROM employee WHERE employee_id = " + employeeID + ";") > 0) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}

		return false;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("UPDATE employee SET first_name = ?, last_name = ?," +
			" middle_initial = ?, level = ?, work_force = ?, enterprise_id = ? WHERE employee_id = ?;");

			st.setString(1, employee.getFirstName());
			st.setString(2, employee.getLastName());
			st.setString(3, employee.getMiddleInitial());
			st.setString(4, employee.getLevel());
			st.setString(5, employee.getWorkForce());
			st.setString(6, employee.getEnterpriseID());
			st.setLong(7, employee.getID());

			if (st.executeUpdate() == 1) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}

		return false;
	}

	@Override
	public boolean insertEmployeeSkill(String name, String description, long rating, long employeeID) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("INSERT INTO employee_skill VALUES(null, ?, ?, ?, ?);");

			st.setString(1, name);
			st.setString(2, description);
			st.setLong(3, rating);
			st.setLong(4, employeeID);

			if (st.executeUpdate() == 1) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}

		return false;
	}

	@Override
	public boolean removeEmployeeSkillByID(long employeeSkillID) {
		Connection conn = null;
		Statement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();

			if (st.executeUpdate("DELETE FROM employee_skill WHERE employee_skill_id = " + employeeSkillID + ";") == 1) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}
		return false;
	}

	@Override
	public boolean updateEmployeeSkill(long employeeSkillID, String name, String description, long rating) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("UPDATE employee_skill SET name = ?, description = ?," +
			" rating = ? WHERE employee_skill_id = ?;");

			st.setString(1, name);
			st.setString(2, description);
			st.setLong(3, rating);
			st.setLong(4, employeeSkillID);

			if (st.executeUpdate() == 1) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}
		return false;
	}

	private void closeConnection(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) { rs.close(); }
		} catch (Exception e) {
			log.error("Error during closing result set: " + e.getMessage(), e);
		}
		closeConnection(st, conn);
	}

	private void closeConnection(Statement st, Connection conn) {
		try {
			if (st != null) { st.close(); }
			if (st != null) { conn.close(); }
		} catch (Exception e) {
			log.error("Error during closing connection: " + e.getMessage(), e);
		}
	}

	@Override
	public EmployeeSkill findEmployeeSkillByID(long employeeSkillID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("SELECT * FROM employee_skill WHERE employee_skill_id = " + employeeSkillID);

			rs.next();
			EmployeeSkill employeeSkill = new EmployeeSkill(rs.getLong("employee_skill_id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getInt("rating"),
					rs.getLong("employee_id"));

			return employeeSkill;
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}
	}

	@Override
	public List<Role> listAllRoles() {
		List<Role> list = new ArrayList<Role>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM role ORDER BY role_id ASC;");

			while (rs.next()) {
				list.add(new Role(rs.getLong("role_id"), rs.getString("role")));
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}

		return list;
	}

	@Override
	public long findEmployeeProjectDetailID(long projectID, long employeeID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("SELECT employee_project_detail_id FROM employee_project_detail " +
					"WHERE project_id = " + projectID + " AND employee_id = " + employeeID + ";");
			rs.next();

			return rs.getLong("employee_project_detail_id");
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}
	}

	@Override
	public boolean insertProjectRole(long employeeID, long projectID, long roleID, String startDate, String endDate) {

		Connection conn = null;
		PreparedStatement st = null;
		long employeeProjectDetailID = findEmployeeProjectDetailID(projectID, employeeID);

		try {
			conn = dataSource.getConnection();

			if (endDate.equals("")) {
				st = conn.prepareStatement("INSERT INTO project_role VALUES(null, ?, ?, ?, NULL)");
				st.setLong(1, employeeProjectDetailID);
				st.setLong(2, roleID);
				st.setString(3, startDate);
			} else {
				st = conn.prepareStatement("INSERT INTO project_role VALUES(null, ?, ?, ?, ?)");
				st.setLong(1, employeeProjectDetailID);
				st.setLong(2, roleID);
				st.setString(3, startDate);
				st.setString(4, endDate);
			}

			if (st.executeUpdate() == 1) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}

		return false;
	}

	@Override
	public boolean removeProjectRoleByID(long projectRoleID) {
		Connection conn = null;
		Statement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();

			if (st.executeUpdate("DELETE FROM project_role WHERE project_role_id = " + projectRoleID + ";") == 1) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}
		return false;
	}

	@Override
	public boolean updateProjectRole(long projectRoleID, long roleID, String startDate, String endDate) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = dataSource.getConnection();
			st = conn.prepareStatement("UPDATE project_role SET role_id = ?," +
			" start_date = ?, end_date = ?  WHERE project_role_id = ?;");

			st.setLong(1, roleID);
			st.setString(2, startDate);
			st.setString(3, endDate);
			st.setLong(4, projectRoleID);

			if (st.executeUpdate() == 1) {
				return true;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(st, conn);
		}
		return false;
	}

	@Override
	public ProjectRole findProjectRoleByID(long projectRoleID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("SELECT role.role, project_role.* FROM role, project_role " +
					"WHERE role.role_id = project_role.role_id AND project_role_id = " + projectRoleID);

			rs.next();
			ProjectRole projectRole = new ProjectRole(rs.getLong("project_role_id"),
					rs.getString("role"),
					rs.getLong("employee_project_detail_id"),
					rs.getDate("start_date").toString(),
					rs.getDate("end_date").toString());

			return projectRole;
		}
		catch (SQLException e) {
			throw new RuntimeException("Error during database call: " + e.getMessage(), e);
		}
		finally {
			closeConnection(rs, st, conn);
		}
	}
}
