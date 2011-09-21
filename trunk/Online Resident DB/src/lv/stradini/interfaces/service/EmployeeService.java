package lv.stradini.interfaces.service;

import java.util.List;

import lv.stradini.domain.Employee;
import lv.stradini.domain.EmployeeDetail;
import lv.stradini.domain.EmployeeSkill;
import lv.stradini.domain.ProjectRole;
import lv.stradini.domain.Role;

public interface EmployeeService {

	/**
	 * Searches for all employees in the database given the project id.
	 * 
	 * @param projectID numeric data representing the ID of the project.
	 * @return the list of employees
	 */
	public List<Employee> findEmployeesByProject(long projectID);
	
	/**
	 * Searches for all employees in the database given the project id.
	 * 
	 * @param projectID numeric data representing the id of the project
	 * @return the list of employees not in the project
	 */
	public List<Employee> findEmployeesNotInProject(long projectID);

	/**
	 * Searches for all employees in the database containing
	 * the first name and/or last name specified.
	 * 
	 * @param firstName String representing the first name of the employee
	 * @param lastName String representing the last name of the employee
	 * @return the list of employees
	 */
	public List<Employee> findEmployeesByName(String firstName, String lastName);

	/**
	 * Searches for an employee in the database given the employee id.
	 * 
	 * @param employeeID numeric data representing the employee id
	 * @return <code>Employee</code> object containing the employee details
	 */
	public Employee findEmployeeByID(long employeeID);

	/**
	 * Searches for an employee skill in the database given the skill id.
	 * @param employeeSkillID
	 * @return <code>EmployeeSkill</code> object containing the employee skill details
	 */
	public EmployeeSkill findEmployeeSkillByID(long employeeSkillID);

	/**
	 * Searches for an employee in the database given the employee id.
	 * 
	 * @param employeeID numeric data representing the employee id.
	 * @return <code>EmployeeDetail</code> object containing the employee
	 * details
	 */
	public EmployeeDetail getEmployeeDetails(long employeeID);

	/**
	 * Inserts new Employee into database, from given parameters, returns true if added, else returns false.
	 * If person with same firstName and lastName exists, do not add, return false.
	 * 
	 * @param firstName - first name if Null, return false
	 * @param lastName - last name if Null, return false
	 * @param middleInitial - may be null
	 * @param level - may be null
	 * @param workForce - may be null
	 * @param enterpriseID - may be null
	 * @return true if Employee added successfully
	 */
	public boolean insertEmployee(String firstName, String lastName,
			String middleInitial, String level, String workForce, String enterpriseID);

	/**
	 * Updates an existing Employee in the repository with the values represented by the Employee object passed
	 * as the parameter.  The Employee parameter must contain an ID that matches an existing ID in the repository
	 * and all fields must be populated and valid.
	 * 
	 * @param employee the Employee containing the details for the update
	 * @return true - if row was updated, false - if the employee was not updated for some reason
	 */
	public boolean updateEmployee(Employee employee);

	/**
	 * Removes an existing Employee from the database given the employee id.
	 * 
	 * @param employeeID the id of the employee
	 * @return true - if the employee was successfully removed, false - if the employee was not removed for some reason
	 */
	public boolean removeEmployeeByID(long employeeID);

	/**
	 * Inserts new Employee into database from given parameters.
	 * 
	 * @param name - skill name
	 * @param description - skill description
	 * @param rating - skill rating
	 * @param employeeID - the ID of the Employee
	 * @return true - if the employee skill was added successfully, else returns false
	 */
	public boolean insertEmployeeSkill(String name, String description, long rating, long employeeID);

	/**
	 * Removes an existing employee skill from the database given the employee skill id.
	 * 
	 * @param employeeSkillID - id of the employee skill
	 * @return true - if the employee skill was successfully removed, else returns false
	 */
	public boolean removeEmployeeSkillByID(long employeeSkillID);

	/**
	 * Updates an existing employee skill in the database from given parameters.
	 * 
	 * @param employeeSkillID - id of the employee skill
	 * @param name - skill name
	 * @param description - skill description
	 * @param rating - numeric skill rating
	 * @return true - if row was updated, else returns false
	 */
	public boolean updateEmployeeSkill(long employeeSkillID, String name, String description, long rating);

	/**
	 * Retrieves all roles from the database
	 * 
	 * @return list of roles from the database
	 */
	public List<Role> listAllRoles();

	/**
	 * Returns an employee project detail id in the database given the project id and employee id.
	 * 
	 * @param projectID numeric data representing the project id
	 * @param employeeID numeric data representing the employee id
	 * @return employee project detail id
	 */
	public long findEmployeeProjectDetailID(long projectID, long employeeID);

	/**
	 * Inserts new employee project role into the database from given parameters.
	 * 
	 * @param employeeID
	 * @param projectID
	 * @param roleID
	 * @param startDate
	 * @param endDate
	 * @return true - if the employee project role was successfully added, else returns false
	 */
	public boolean insertProjectRole(long employeeID, long projectID, long roleID, String startDate, String endDate);

	/**
	 * Removes an existing project role from the database given the project role id.
	 * 
	 * @param projectRoleID the id of the project role
	 * @return true - if the employee role was successfully removed, else returns false
	 */
	public boolean removeProjectRoleByID(long projectRoleID);

	/**
	 * Updates an existing project role in the database from given parameters.
	 * 
	 * @param projectRoleID
	 * @param roleID
	 * @param startDate
	 * @return endDate
	 */
	public boolean updateProjectRole(long projectRoleID, long roleID, String startDate, String endDate);

	/**
	 * Searches for an employee project role in the database given the project role id.
	 * @param projectRoleID
	 * @return <code>ProjectRole</code> object containing the employee project role details
	 */
	public ProjectRole findProjectRoleByID(long projectRoleID);
}
