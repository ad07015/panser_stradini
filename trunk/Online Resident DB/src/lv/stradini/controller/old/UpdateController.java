package lv.stradini.controller.old;

import lv.stradini.domain.Employee;
import lv.stradini.domain.EmployeeDetail;
import lv.stradini.domain.EmployeeSkill;
import lv.stradini.domain.Project;
import lv.stradini.interfaces.service.EmployeeService;
import lv.stradini.interfaces.service.ProjectService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/update/*.htm")
public class UpdateController {

	private static Logger log = Logger.getLogger(OldViewController.class);
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "employee.htm", params = "!updateEmployee")
	public ModelAndView onInitialUpdateFormState(
			@RequestParam("employeeID") long employeeID) {

		log.info("Showing update form for employee with ID: " + employeeID);

		EmployeeDetail employeeDet = employeeService.getEmployeeDetails(employeeID);
		ModelAndView mav = new ModelAndView();
		mav.addObject("employee", employeeDet.getEmployee());
		mav.setViewName("update/updateEmployee");
		return mav;
	}

	@RequestMapping(value = "employee.htm", params = "updateEmployee")
	public ModelAndView onUpdateEmployee(
			@RequestParam("employeeID") Long EmployeeID,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("middleInitial") String middleInitial,
			@RequestParam("level") String level,
			@RequestParam("workForce") String workForce,
			@RequestParam("enterpriseID") String enterpriseID) {

		log.info("Trying to update Employee with name: " + firstName + " and surname: " + lastName);

		Employee emp = new Employee(EmployeeID, firstName, lastName, middleInitial, level, workForce, enterpriseID);

		boolean result = employeeService.updateEmployee(emp);

		String message;
		if (result) {
			message = "<hr>Employee updated successfully!";
		} else {
			message = "<hr>Failed to update employee!";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.addObject("employee", emp);
		mav.setViewName("update/updateEmployee");
		return mav;
	}

	@RequestMapping(value = "employeeSkill.htm", params = "!updateEmployeeSkill")
	public ModelAndView onUpdateEmployeeSkill(
			@RequestParam("employeeSkillID") long employeeSkillID) {

		log.info("Showing update form for employee skill with ID: " + employeeSkillID);

		EmployeeSkill employeeSkill = employeeService.findEmployeeSkillByID(employeeSkillID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("skill", employeeSkill);
		mav.setViewName("update/updateEmployeeSkill");
		return mav;
	}

	@RequestMapping(value = "employeeSkill.htm", params = "updateEmployeeSkill")
	public ModelAndView onSubmitUpdateEmployeeSkill(
			@RequestParam("employeeSkillID") long employeeSkillID,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("rating") int rating) {

		log.info("Trying to update employee skill with name: " + name + "; description: " + description);

		boolean result = employeeService.updateEmployeeSkill(employeeSkillID,
				name, description, rating);
		String message;
		if (result) {
			message = "<hr>Employee skill updated successfully!";
		} else {
			message = "<hr>Failed to update employee skill!";
		}

		EmployeeSkill employeeSkill = employeeService
				.findEmployeeSkillByID(employeeSkillID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("skill", employeeSkill);
		mav.addObject("message", message);
		mav.setViewName("update/updateEmployeeSkill");
		return mav;
	}

	@RequestMapping(value = "project.htm", params = "!updateProject")
	public ModelAndView onUpdateProject(
			@RequestParam("projectID") long projectID) {

		log.info("Showing update form for project with ID: " + projectID);

		Project project = projectService.getProjectByID(projectID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project", project);
		mav.setViewName("update/updateProject");
		return mav;
	}

	@RequestMapping(value = "project.htm", params = "updateProject")
	public ModelAndView onUpdateProjectSubmit(
			@RequestParam("projectID") long projectID,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("client") String client) {

		log.info("Showing update form after update for project with ID: " + projectID);

		Project project = new Project(projectID, name, description, client);

		String message;
		if (projectService.updateProject(project)) {
			message = "<hr>Project updated successfully!";
		} else {
			message = "<hr>Failed to update project!";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.addObject("project", project);
		mav.setViewName("update/updateProject");
		return mav;
	}

	@RequestMapping(value = "projectRole.htm", params = "!updateProjectRole")
	public ModelAndView onUpdateProjectRole(
			@RequestParam("employeeID") long employeeID,
			@RequestParam("projectRoleID") long projectRoleID) {

		log.info("Showing update form for employee project role with ID: " + projectRoleID);

		ModelAndView mav = new ModelAndView();
		mav.addObject("employeeID", employeeID);
		mav.addObject("projectRole", employeeService.findProjectRoleByID(projectRoleID));
		mav.addObject("roleList", employeeService.listAllRoles());
		mav.setViewName("update/updateProjectRole");
		return mav;
	}

	@RequestMapping(value = "projectRole.htm", params = "updateProjectRole")
	public ModelAndView onSubmitUpdateProjectRole(
			@RequestParam("employeeID") long employeeID,
			@RequestParam("projectRoleID") long projectRoleID,
			@RequestParam("roleID") long roleID,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {

		log.info("Trying to update project role with id: " + projectRoleID);

		boolean result = employeeService.updateProjectRole(projectRoleID, roleID, startDate, endDate);
		String message;
		if (result) {
			message = "<hr>Project role updated successfully!";
		} else {
			message = "<hr>Failed to update project role!";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("employeeID", employeeID);
		mav.addObject("message", message);
		mav.addObject("projectRole", employeeService.findProjectRoleByID(projectRoleID));
		mav.addObject("roleList", employeeService.listAllRoles());
		mav.setViewName("update/updateProjectRole");
		return mav;
	}
}
