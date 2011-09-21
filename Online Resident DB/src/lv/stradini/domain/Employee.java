package lv.stradini.domain;

public class Employee {
	
	private long ID;
	private String firstName;
	private String lastName;
	private String middleInitial;
	private String level;
	private String workForce;
	private String enterpriseID;
	
	public Employee(long id, String firstName, String lastName,
			String middleInitial, String level, String workForce,
			String enterpriseID) {
		setID(id);
		setFirstName(firstName);
		setLastName(lastName);
		setMiddleInitial(middleInitial);
		setLevel(level);
		setWorkForce(workForce);
		setEnterpriseID(enterpriseID);
	}
	
	public long getID() {
		return ID;
	}
	
	public void setID(long id) {
		this.ID = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleInitial() {
		return middleInitial;
	}
	
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getWorkForce() {
		return workForce;
	}
	
	public void setWorkForce(String workForce) {
		this.workForce = workForce;
	}
	
	public String getEnterpriseID() {
		return enterpriseID;
	}
	
	public void setEnterpriseID(String enterpriseID) {
		this.enterpriseID = enterpriseID;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employee) {
			Employee emp = (Employee) obj;
			if (firstName.equals(emp.getFirstName())
					&& lastName.equals(emp.getLastName())
					&& middleInitial.equals(emp.getMiddleInitial())
					&& level.equals(emp.getLevel())
					&& workForce.equals(emp.getWorkForce())
					&& enterpriseID.equals(emp.getEnterpriseID())) {
				return true;
			}
		}
		return false;
	}
}
