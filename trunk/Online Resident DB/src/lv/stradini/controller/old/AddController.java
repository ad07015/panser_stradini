package lv.stradini.controller.old;

import java.util.List;

import lv.stradini.domain.Employee;
import lv.stradini.impl.service.ValidatorServiceImpl;
import lv.stradini.interfaces.service.EmployeeService;
import lv.stradini.interfaces.service.ProjectService;
import lv.stradini.interfaces.service.ValidatorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adds/*.htm")
public class AddController {

	private static Logger log = Logger.getLogger(ViewController.class);
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProjectService projectService;
	private final ValidatorService validator = new ValidatorServiceImpl();;

	@RequestMapping(value="employee.htm", params="!addEmployee")
	public ModelAndView onInitialInsertFormState() {

		log.info("Entering InstertEmployee form in its initial state");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("add/addEmployee");
		return mav;
	}

	@RequestMapping(value="employee.htm", params="addEmployee")
	public ModelAndView onSubmitInsertEmployee(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("middleInitial") String middleInitial,
			@RequestParam("level") String level,
			@RequestParam("workForce") String workForce,
			@RequestParam("enterpriseID") String enterpriseID) {

		log.info("Trying to insert Employee with name: " + firstName
				+ " and surname: " + lastName);

		String errorMessage = validator.validateEmployee(firstName, lastName, middleInitial, level, workForce, enterpriseID);

		boolean result = false;
		StringBuilder messageSB = new StringBuilder();
		if (!errorMessage.equals("")) {
			messageSB.append("<hr>Failed to add employee:");
			messageSB.append(errorMessage);
		} else {
			result = employeeService.insertEmployee(firstName,
					lastName, middleInitial, level, workForce, enterpriseID);
			if (result == true) {
				messageSB.append("<hr>Employee added successfully");
			} else {
				messageSB.append("<hr>Failed to add employee");
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", messageSB.toString());
		mav.setViewName("add/addEmployee");
		return mav;
	}

	@RequestMapping(value="employeeSkill.htm", params="!addSkill")
	public ModelAndView onInitialInsertSkillFormState(
			@RequestParam("employeeID") Long employeeID) {

		log.info("Entering InstertEmployeeSkill form in its initial state");

		ModelAndView mav = new ModelAndView();
		mav.addObject("employeeID", employeeID);
		mav.setViewName("add/addEmployeeSkill");
		return mav;
	}

	@RequestMapping(value="employeeSkill.htm", params="addSkill")
	public ModelAndView onSubmitInsertSkill(
			@RequestParam("employeeID") Long employeeID,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("rating") String rating) {

		String errorMessage = validator.validateEmployeeSkill(name, rating);

		boolean result = false;
		StringBuilder messageSB = new StringBuilder();
		if (!errorMessage.equals("")) {
			messageSB.append("<hr>Failed to add employee skill:");
			messageSB.append(errorMessage);
		} else {
			result = employeeService.insertEmployeeSkill(name, description, Long.parseLong(rating), employeeID);
			if (result == true) {
				messageSB.append("<hr>Employee skill added successfully");
			} else {
				messageSB.append("<hr>Failed to add employee skill");
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", messageSB.toString());
		mav.addObject("employeeID", employeeID);
		mav.setViewName("add/addEmployeeSkill");
		return mav;
	}

	@RequestMapping(value="project.htm", params="!addProject")
	public ModelAndView onInsertProjectInitialState() {

		log.info("Entering project insert form in it`s initial state");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("add/addProject");
		return mav;
	}

	@RequestMapping(value="project.htm", params="addProject")
	public ModelAndView onInsertProject(
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("client") String client) {

		log.info("Trying to insert project with name: " + name);

		String errorMessage = validator.validateProject(name, description, client);

		boolean result = false;
		StringBuilder messageSB = new StringBuilder();
		if (!errorMessage.equals("")) {
			messageSB.append("<hr>Failed to add project:");
			messageSB.append(errorMessage);
		} else {
			result = projectService.insertProject(name, description, client);
			if (result == true) {
				messageSB.append("<hr>Project added successfully");
			} else {
				messageSB.append("<hr>Failed to add project");
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("add/addProject");
		mav.addObject("message", messageSB.toString());
		return mav;
	}

	@RequestMapping(value="projectRole.htm", params="!addRole")
	public ModelAndView onInitialInsertRoleFormState(
			@RequestParam("employeeID") Long employeeID,
			@RequestParam("projectID") Long projectID) {

		log.info("Entering InstertEmployeeRole form in its initial state");

		ModelAndView mav = new ModelAndView();
		mav.addObject("employeeID", employeeID);
		mav.addObject("projectID", projectID);
		mav.addObject("roleList", employeeService.listAllRoles());
		mav.setViewName("add/addProjectRole");
		return mav;
	}

	@RequestMapping(value="projectRole.htm", params="addRole")
	public ModelAndView onSubmitInsertRole(
			@RequestParam("employeeID") Long employeeID,
			@RequestParam("projectID") Long projectID,
			@RequestParam("roleID") Long roleID,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {

		String errorMessage = validator.validateProjectRole(startDate, endDate);

		boolean result = false;
		StringBuilder messageSB = new StringBuilder();
		if (!errorMessage.equals("")) {
			messageSB.append("<hr>Failed to add project role:");
			messageSB.append(errorMessage);
		} else {
			result = employeeService.insertProjectRole(employeeID, projectID, roleID, startDate, endDate);
			if (result == true) {
				messageSB.append("<hr>Project role added successfully");
			} else {
				messageSB.append("<hr>Failed to add project role");
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", messageSB.toString());
		mav.addObject("employeeID", employeeID);
		mav.addObject("projectID", projectID);
		mav.addObject("roleList", employeeService.listAllRoles());
		mav.setViewName("add/addProjectRole");
		return mav;
	}

	@RequestMapping(value="employeeToProject.htm", params={"!addToProject","!removeFromProject"})
	public ModelAndView onEmployeeToProject(
			@RequestParam("projectID") long projectID) {

		List<Employee> notInProjectList = employeeService.findEmployeesNotInProject(projectID);
		List<Employee> inProjectList = employeeService.findEmployeesByProject(projectID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("projectID", projectID);
		mav.addObject("notInProjectList", notInProjectList);
		mav.addObject("inProjectList", inProjectList);
		mav.setViewName("add/addEmployeeToProject");
		return mav;
	}

	@RequestMapping(value="employeeToProject.htm", params="addToProject")
	public ModelAndView onEmployeeToProjectAdd(
			@RequestParam("projectID") long projectID,
			@RequestParam("toProject") long [] toProject) {

		boolean result = projectService.assignEmployeeListToProject(projectID, toProject);

		String message;
		if(result) {
			message = "<hr>" + toProject.length + " employees added to project!";
		} else {
			message = "<hr>Failed to add employees to project!";
		}

		List<Employee> notInProjectList = employeeService.findEmployeesNotInProject(projectID);
		List<Employee> inProjectList = employeeService.findEmployeesByProject(projectID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.addObject("projectID", projectID);
		mav.addObject("notInProjectList", notInProjectList);
		mav.addObject("inProjectList", inProjectList);
		mav.setViewName("add/addEmployeeToProject");
		return mav;
	}

	@RequestMapping(value="employeeToProject.htm", params="removeFromProject")
	public ModelAndView onEmployeeToProjectremove(
			@RequestParam("projectID") long projectID,
			@RequestParam("fromProject") long [] fromProject) {

		boolean result = projectService.unassignEmployeeListFromProject(projectID, fromProject);

		String message;
		if(result) {
			message = "<hr>" + fromProject.length + " employees removed from project!";
		} else {
			message = "<hr>Failed to remove employees to project!";
		}

		List<Employee> notInProjectList = employeeService.findEmployeesNotInProject(projectID);
		List<Employee> inProjectList = employeeService.findEmployeesByProject(projectID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.addObject("projectID", projectID);
		mav.addObject("notInProjectList", notInProjectList);
		mav.addObject("inProjectList", inProjectList);
		mav.setViewName("add/addEmployeeToProject");
		return mav;
	}
}
