package lv.stradini.interfaces.service;




public interface ValidatorService {

	/**
	 * Validates parameters for a new Employee object
	 * 
	 * @return a string, which contains an error message; empty string - if validation passed
	 */
	public String validateEmployee(String firstName, String lastName, String middleInitial,
			String level, String workForce, String enterpriseID);

	/**
	 * Validates parameters for a new EmployeeSkill object
	 * 
	 * @return a string, which contains an error message; empty string - if validation passed
	 */
	public String validateEmployeeSkill(String name, String rating);

	/**
	 * Validates parameters for a new ProjectRole object
	 * 
	 * @return a string, which contains an error message; empty string - if validation passed
	 */
	public String validateProjectRole(String startDate, String endDate);

	/**
	 * Validates parameters for a new Project object
	 * 
	 * @return a string, which contains an error message; empty string - if validation passed
	 */
	public String validateProject(String name, String description, String client);
}
