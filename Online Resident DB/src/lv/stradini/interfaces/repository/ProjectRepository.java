package lv.stradini.interfaces.repository;

import java.util.List;

import lv.stradini.domain.old.EmployeeProjectDetail;
import lv.stradini.domain.old.Project;
import lv.stradini.domain.old.ProjectRole;

public interface ProjectRepository {

	/**
	 * Retrieves all projects from the database
	 * 
	 * @return list of projects from the database
	 */
	public List<Project> listAllProjects();

	/**
	 * Retrieves all projects where an employee is part of.
	 * 
	 * @param employeeID the id of the employee
	 * @return list of projects
	 */
	public List<Project> getEmployeeProjects(long employeeID);

	/**
	 * Retrieves the roles of an employee for a specific project.
	 * 
	 * @param employeeID the id of the employee
	 * @param projectID the id of the project
	 * @return list of roles
	 */
	public List<ProjectRole> getEmployeeProjectRoles(long employeeID, long projectID);

	/**
	 * Retrieves all the employee's projects with the employee's corresponding
	 * roles in a project.
	 * 
	 * @param employeeID the id of the employee
	 * @return list of projects with corresponding roles
	 */
	public List<EmployeeProjectDetail> getEmployeeProjectHistory(long employeeID);

	/**
	 * Inserts new project into database
	 * 
	 * @param name - name of the project
	 * @param description - description of the project
	 * @param client - client, who ordered the project
	 * @return true if added, else false
	 */

	public boolean insertProject(String name, String description, String client);

	/**
	 * Finds project with this ID in database
	 * 
	 * @param projectID - id of the project
	 * @return Project with this ID
	 */

	public Project getProjectByID(long projectID);

	/**
	 * updates project with new data
	 * 
	 * @param project - new project instance
	 * @return true if updated
	 */
	public boolean updateProject(Project project);

	/**
	 * Delete`s project from database,
	 * also deletes all references with this project - "employee_project_detail"
	 * 
	 * @param projectID - id of the project to be deleted
	 * @return - true if deleted
	 */
	public boolean deleteProjectByID(long projectID);

	/**
	 * Assigns all employees in the employeeIDList to a project with the specified projectID
	 * 
	 * @param projectID - id of the project
	 * @param employeeIDList - employees to add to this project
	 * @return true if everyone is added
	 */
	public boolean insertEmployeeProjectDetailList(long projectID, List<Long> employeeIDList);

	/**
	 * Unassigns all employees in the employeeIDList from a project with the specified projectID
	 * 
	 * @param projectID - id of the project
	 * @param employeeIDList - employees to unassign from this project
	 * @return true if everyone is removed
	 */
	public boolean deleteEmployeeProjectDetailList(long projectID, List<Long> employeeIDList);

	/**
	 * Assigns an employee to a project with the specified projectID
	 * 
	 * @param projectID - id of the project
	 * @param employeeID - employee to assign to this project
	 * @return true if the employee has been successfully assigned
	 */
	public boolean insertEmployeeProjectDetail(long projectID, long employeeID);

	/**
	 * Unassigns an employee from a project with the specified projectID
	 * 
	 * @param projectID - id of the project
	 * @param employeeID - employee to add to this project
	 * @return true if the employee is added
	 */
	public boolean deleteEmployeeProjectDetail(long projectID, long employeeID);
}
