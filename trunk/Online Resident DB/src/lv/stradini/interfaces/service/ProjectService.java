package lv.stradini.interfaces.service;

import java.util.List;

import lv.stradini.domain.Project;

public interface ProjectService {

	/**
	 * Retrieves all projects from the database.
	 * Used to populate the search drop down filters.
	 * 
	 * @return list of projects from the database
	 */
	public List<Project> listAllProjects();

	/**
	 * Uses Repository insert method to add new project
	 * 
	 * @param name - project name
	 * @param description - project description
	 * @param client - client who ordered project
	 * @return return true if added to database
	 */
	public boolean insertProject(String name, String description, String client);

	/**
	 * Calls repository methods to get project with this ID
	 * 
	 * @param projectID - id of the project
	 * @return Project with specified ID
	 */
	public Project getProjectByID(Long projectID);

	/**
	 * Uses Repository method to update project
	 * 
	 * @param project - project with new data
	 * @return true if updated successfully
	 */
	public boolean updateProject(Project project);

	/**
	 * Calls Repository methods to delete project and all project links
	 * 
	 * @param projectID - ID of the project to be deleted
	 * @return true if successful
	 */
	public boolean deleteProject(long projectID);

	/**
	 * Uses repository method to add all employees in list to specified project
	 * 
	 * @param projectID - id of the current project
	 * @param employeeIDList - list with all the employee ID`s
	 * @return - true if ALL employees added, else returns false
	 */
	public boolean assignEmployeeListToProject(long projectID, long []employeeIDList);

	/**
	 * Uses repository method to remove all employees in list from project
	 * 
	 * @param projectID - this project
	 * @param employeeIDList - list of employee id`s to be removed
	 * @return true if succeeded
	 */
	public boolean unassignEmployeeListFromProject(long projectID, long []employeeIDArray);

	/**
	 * Uses repository method to remove one employee from project
	 * 
	 * @param projectID - this project
	 * @param employeeIDList - employees id to be removed
	 * @return true if succeeded
	 */
	public boolean unassignEmployeeFromProject(long projectID, long employeeID);
}
