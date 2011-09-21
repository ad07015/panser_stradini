package lv.stradini.impl.service;

import java.util.ArrayList;
import java.util.List;

import lv.stradini.domain.Project;
import lv.stradini.interfaces.repository.EmployeeRepository;
import lv.stradini.interfaces.repository.ProjectRepository;
import lv.stradini.interfaces.service.ProjectService;

import org.apache.log4j.Logger;

public class ProjectServiceImpl implements ProjectService {

	//Tip: create member variables in this class that will contain the objects
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(ProjectServiceImpl.class);
	private final EmployeeRepository employeeRepo;
	private final ProjectRepository projectRepo;

	public ProjectServiceImpl(EmployeeRepository employeeRepo, ProjectRepository projectRepo) {
		this.employeeRepo = employeeRepo;
		this.projectRepo = projectRepo;
	}

	@Override
	public List<Project> listAllProjects() {
		log.info("Getting all projects from DB");
		List<Project> projectList = projectRepo.listAllProjects();
		return projectList;
	}

	@Override
	public boolean insertProject(String name, String description, String client) {

		if (name.length() == 0 || client.length() == 0) return false;

		return projectRepo.insertProject(name, description, client);
	}

	@Override
	public Project getProjectByID(Long projectID) {

		return projectRepo.getProjectByID(projectID);
	}

	@Override
	public boolean updateProject(Project project) {

		return projectRepo.updateProject(project);
	}

	@Override
	public boolean deleteProject(long projectID) {

		return projectRepo.deleteProjectByID(projectID);
	}

	public boolean assignEmployeeToProject(long projectID, long employeeID) {
		return projectRepo.insertEmployeeProjectDetail(projectID, employeeID);
	}

	@Override
	public boolean assignEmployeeListToProject(long projectID, long[] employeeIDArray) {
		List<Long> employeeIDList = new ArrayList<Long>();

		for (long id : employeeIDArray) {
			employeeIDList.add(id);
		}

		return projectRepo.insertEmployeeProjectDetailList(projectID, employeeIDList);
	}

	@Override
	public boolean unassignEmployeeListFromProject(long projectID, long[] employeeIDArray) {

		List<Long> employeeIDList = new ArrayList<Long>();

		for (long id : employeeIDArray) {
			employeeIDList.add(id);
		}

		return projectRepo.deleteEmployeeProjectDetailList(projectID, employeeIDList);
	}

	@Override
	public boolean unassignEmployeeFromProject(long projectID, long employeeID) {

		List<Long> employeeIDList = new ArrayList<Long>();
		employeeIDList.add(employeeID);

		return projectRepo.deleteEmployeeProjectDetailList(projectID, employeeIDList);
	}
}
