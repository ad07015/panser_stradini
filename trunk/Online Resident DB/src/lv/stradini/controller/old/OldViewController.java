package lv.stradini.controller.old;

import java.util.List;

import lv.stradini.domain.old.Employee;
import lv.stradini.domain.old.EmployeeDetail;
import lv.stradini.domain.old.Project;
import lv.stradini.interfaces.service.EmployeeService;
import lv.stradini.interfaces.service.ProjectService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/views/*.htm") //FIXME: Incorrect, change to @RequestMapping("/view/*.htm")
public class OldViewController {

	private static Logger log = Logger.getLogger(OldViewController.class);
	@Autowired
	private ProjectService projectService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("employeeDetails.htm")
	public ModelAndView onViewEmployeeDetails(
			@RequestParam("employeeID") long employeeID) throws Exception {

		log.info("Getting details for employee ID " + employeeID);

		EmployeeDetail employee = employeeService.getEmployeeDetails(employeeID);
		ModelAndView mav = new ModelAndView();
		mav.addObject("employee", employee.getEmployee());
		mav.addObject("projectList", employee.getProjectList());
		mav.addObject("skillList", employee.getSkillList());
		mav.setViewName("view/employeeDetails");
		return mav;
	}

	@RequestMapping(value="employees.htm", params={"!findByName","!findByProject","!deleteEmployee","!deleteEmployeeSkill","!deleteProjectRole"})
	public ModelAndView onInitialSearchFormState() {

		List<Project> projectList = projectService.listAllProjects();

		log.info("Entering search form in its initial state");
		ModelAndView mav = new ModelAndView();

		mav.addObject("projectList", projectList);
		mav.setViewName("view/employeeSearchForm");
		return mav;
	}

	@RequestMapping(value="employees.htm", params="findByName")
	public ModelAndView onSubmitSearchByName(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("projectID") long projectID,
			@RequestParam("employeeID")  String employeeID){

		log.info("Searching by first name: " + firstName + " last name: " + lastName);

		List<Employee> employeeList  = employeeService.findEmployeesByName(firstName, lastName);
		List<Project> projectList = projectService.listAllProjects();

		ModelAndView mav = new ModelAndView();
		mav.addObject("projectList", projectList);
		mav.addObject("employeeList", employeeList);
		mav.setViewName("view/employeeSearchForm");
		return mav;
	}

	@RequestMapping(value="employees.htm", params="findByProject")
	public ModelAndView onSubmitSearchByProject(
			@RequestParam("projectID") long projectID,
			@RequestParam("employeeID")  String employeeID) {

		log.info("Finding by project ID: " + projectID);
		List<Project> projectList = projectService.listAllProjects();
		List<Employee> employeeList = employeeService.findEmployeesByProject(projectID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("projectList", projectList);
		mav.addObject("employeeList", employeeList);
		mav.setViewName("view/employeeSearchForm");
		return mav;
	}

	@RequestMapping(value="employees.htm", params="deleteEmployee")
	public ModelAndView onSubmitDelete(
			@RequestParam("employeeID")  String employeeID) {

		log.info("Trying to delete employee with ID: " + employeeID);

		List<Project> projectList = projectService.listAllProjects();

		boolean result = employeeService.removeEmployeeByID(Long.valueOf(employeeID));

		String message;
		if(result) {
			message = "<hr>Employee removed successfully!";
		} else {
			message = "<hr>Failed to remove employee!";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("projectList", projectList);
		mav.addObject("message", message);
		mav.setViewName("view/employeeSearchForm");
		return mav;
	}

	@RequestMapping(value="employeeDetails.htm", params="deleteEmployeeSkill")
	public ModelAndView onSubmitDeleteEmployeeSkill(
			@RequestParam("employeeSkillID")  long employeeSkillID,
			@RequestParam("employeeID")  long employeeID) {

		log.info("Trying to delete employee skill with ID: " + employeeSkillID);

		boolean result = employeeService.removeEmployeeSkillByID(employeeSkillID);

		String message;
		if(result) {
			message = "<hr>Employee skill removed successfully!";
		} else {
			message = "<hr>Failed to remove employee skill!";
		}

		EmployeeDetail employeeDetail = employeeService.getEmployeeDetails(employeeID);
		ModelAndView mav = new ModelAndView();
		mav.addObject("employee", employeeDetail.getEmployee());
		mav.addObject("projectList", employeeDetail.getProjectList());
		mav.addObject("skillList", employeeDetail.getSkillList());
		mav.addObject("message", message);
		mav.setViewName("view/employeeDetails");
		return mav;
	}

	@RequestMapping(value="projects.htm", params={"!projectID","!projectToDelete"})
	public ModelAndView onViewProjectsInitialState() {

		List<Project> projectList = projectService.listAllProjects();

		log.info("Entering project form in its initial state");

		ModelAndView mav = new ModelAndView();
		mav.addObject("projectList", projectList);
		mav.setViewName("view/projectForm");
		return mav;
	}

	//In progress waiting for methods
	@RequestMapping(value="projects.htm", params={"projectID","projectToDelete"})
	public ModelAndView onViewProjectsDelete(
			@RequestParam("projectID") long projectID,
			@RequestParam("projectToDelete") String name) {

		log.info("Entering project form after project delete");

		String message;
		if(projectService.deleteProject(projectID)) {
			message = "Project " + name + "deleted.";
		} else {
			message = "Failed to delete " + name;
		}

		List<Project> projectList = projectService.listAllProjects();

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.addObject("projectList", projectList);
		mav.setViewName("view/projectForm");
		return mav;
	}

	@RequestMapping(value="projectDetails.htm", params="!deleteEmployeeFromProject")
	public ModelAndView onProjectDetail(
			@RequestParam("projectID") long projectID) {

		Project project = projectService.getProjectByID(projectID);

		List<Employee> employeeList = employeeService.findEmployeesByProject(projectID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("employeeList", employeeList);
		mav.addObject("project", project);
		mav.setViewName("view/projectDetails");
		return mav;
	}

	@RequestMapping(value="projectDetails.htm", params="deleteEmployeeFromProject")
	public ModelAndView onProjectDetailDelete(
			@RequestParam("projectID") long projectID,
			@RequestParam("employeeID") long employeeID) {

		boolean result = projectService.unassignEmployeeFromProject(projectID, employeeID);

		String message;
		if(result) {
			message = "<hr>Employee removed from project!";
		} else {
			message = "<hr>Failed to remove employee from project!";
		}

		Project project = projectService.getProjectByID(projectID);

		List<Employee> employeeList = employeeService.findEmployeesByProject(projectID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.addObject("employeeList", employeeList);
		mav.addObject("project", project);
		mav.setViewName("view/projectDetails");
		return mav;
	}

	@RequestMapping(value="employeeDetails.htm", params="deleteProjectRole")
	public ModelAndView onSubmitDeleteProjectRole(
			@RequestParam("projectRoleID")  long projectRoleID,
			@RequestParam("employeeID")  long employeeID) {

		log.info("Trying to delete employee project role with ID: " + projectRoleID);

		boolean result = employeeService.removeProjectRoleByID(projectRoleID);

		String message;
		if(result) {
			message = "<hr>Project role removed successfully!";
		} else {
			message = "<hr>Failed to remove project role!";
		}

		EmployeeDetail employeeDetail = employeeService.getEmployeeDetails(employeeID);
		ModelAndView mav = new ModelAndView();
		mav.addObject("employee", employeeDetail.getEmployee());
		mav.addObject("projectList", employeeDetail.getProjectList());
		mav.addObject("skillList", employeeDetail.getSkillList());
		mav.addObject("message", message);
		mav.setViewName("view/employeeDetails");
		return mav;
	}
}
