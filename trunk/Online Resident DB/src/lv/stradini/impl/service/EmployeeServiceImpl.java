package lv.stradini.impl.service;

import java.util.List;

import lv.stradini.domain.Employee;
import lv.stradini.domain.EmployeeDetail;
import lv.stradini.domain.EmployeeProjectDetail;
import lv.stradini.domain.EmployeeSkill;
import lv.stradini.domain.ProjectRole;
import lv.stradini.domain.Role;
import lv.stradini.interfaces.repository.EmployeeRepository;
import lv.stradini.interfaces.repository.ProjectRepository;
import lv.stradini.interfaces.service.EmployeeService;

import org.apache.log4j.Logger;

public class EmployeeServiceImpl implements EmployeeService {

	private static Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	private final EmployeeRepository employeeRepo;
	private final ProjectRepository projectRepo;

	public EmployeeServiceImpl(EmployeeRepository empDAO, ProjectRepository projectDAO){
		this.employeeRepo = empDAO;
		this.projectRepo = projectDAO;
	}

	@Override
	public List<Employee> findEmployeesByName(String firstName, String lastName) {
		log.info("Trying to get EmployeeList by first and last name");
		List<Employee> employeeList = employeeRepo.findEmployeesByName(firstName, lastName);
		return employeeList;
	}

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {
		List<Employee> employeeList = employeeRepo.findEmployeesByProject(projectID);
		return employeeList;
	}
	
	@Override
	public List<Employee> findEmployeesNotInProject(long projectID) {
		List<Employee> employeeList = employeeRepo.findEmployeesNotInProject(projectID);
		return employeeList;
	}
	
	@Override
	public EmployeeDetail getEmployeeDetails(long employeeID) {
		Employee employee = employeeRepo.findEmployeeByID(employeeID);
		List<EmployeeProjectDetail> detailList = projectRepo.getEmployeeProjectHistory(employeeID);
		List<EmployeeSkill> skillList = employeeRepo.findEmployeeSkills(employeeID);

		EmployeeDetail detail = new EmployeeDetail(employee, detailList, skillList);

		return detail;
	}

	@Override
	public boolean insertEmployee(String firstName, String lastName,
			String middleInitial, String level, String workForce,
			String enterpriseID) {
		if (firstName.length() == 0 || lastName.length() == 0) {
			return false;
		}

		employeeRepo.insertEmployee(firstName, lastName, middleInitial, level, workForce, enterpriseID);
		return true;
	}

	@Override
	public boolean removeEmployeeByID(long employeeID) {
		return employeeRepo.removeEmployeeByID(employeeID);
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		return employeeRepo.updateEmployee(employee);
	}

	@Override
	public boolean insertEmployeeSkill(String name, String description, long rating, long employeeID) {
		return employeeRepo.insertEmployeeSkill(name, description, rating, employeeID);
	}

	@Override
	public boolean removeEmployeeSkillByID(long employeeSkillID) {
		return employeeRepo.removeEmployeeSkillByID(employeeSkillID);
	}

	@Override
	public boolean updateEmployeeSkill(long employeeSkillID, String name, String description, long rating) {
		return employeeRepo.updateEmployeeSkill(employeeSkillID, name, description, rating);
	}

	@Override
	public Employee findEmployeeByID(long employeeID) {
		return employeeRepo.findEmployeeByID(employeeID);
	}

	@Override
	public EmployeeSkill findEmployeeSkillByID(long employeeSkillID) {
		return employeeRepo.findEmployeeSkillByID(employeeSkillID);
	}

	@Override
	public List<Role> listAllRoles() {
		List<Role> roleList = employeeRepo.listAllRoles();
		return roleList;
	}

	@Override
	public long findEmployeeProjectDetailID(long projectID, long employeeID) {
		return employeeRepo.findEmployeeProjectDetailID(projectID, employeeID);
	}

	@Override
	public boolean insertProjectRole(long employeeID, long projectID,
			long roleID, String startDate, String endDate) {
		return employeeRepo.insertProjectRole(employeeID, projectID, roleID, startDate, endDate);
	}

	@Override
	public boolean removeProjectRoleByID(long projectRoleID) {
		return employeeRepo.removeProjectRoleByID(projectRoleID);
	}

	@Override
	public boolean updateProjectRole(long projectRoleID, long roleID, String startDate, String endDate) {
		return employeeRepo.updateProjectRole(projectRoleID, roleID, startDate, endDate);
	}

	@Override
	public ProjectRole findProjectRoleByID(long projectRoleID) {
		return employeeRepo.findProjectRoleByID(projectRoleID);
	}

}
