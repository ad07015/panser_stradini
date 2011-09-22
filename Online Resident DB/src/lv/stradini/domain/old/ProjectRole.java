package lv.stradini.domain.old;


public class ProjectRole {

	private long ID;
	private String role;
	private long employeeProjectDetailID;
	private String startDate;
	private String endDate;

	public ProjectRole(long iD, String role, long employeeProjectDetailID,
			String startDate, String endDate) {
		super();
		ID = iD;
		this.role = role;
		this.employeeProjectDetailID = employeeProjectDetailID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getID() {
		return ID;
	}

	public void setID(long id) {
		this.ID = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public long getEmployeeProjectDetailID() {
		return employeeProjectDetailID;
	}

	public void setEmployeeProjectDetailID(long employeeProjectDetailID) {
		this.employeeProjectDetailID = employeeProjectDetailID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProjectRole) {

			ProjectRole projectRole = (ProjectRole) obj;
			if (role.equals(projectRole.getRole())
					&& employeeProjectDetailID == projectRole.getEmployeeProjectDetailID() 
					&& startDate.equals(projectRole.getStartDate()) 
					&& endDate.equals(projectRole.getEndDate()) ) {

				return true;
			}
		}
		return false;
	}
}
