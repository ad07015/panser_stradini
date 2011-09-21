package lv.stradini.domain;

public class EmployeeSkill {
	private long id;
	private String name;
	private String description;
	private int rating;
	private long employeeID;

	public EmployeeSkill(long id, String name, String description, int rating, long employeeID) {
		setID(id);
		setName(name);
		setDescription(description);
		setRating(rating);
		setEmployeeID(employeeID);
	}

	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EmployeeSkill) {
			
			EmployeeSkill skill = (EmployeeSkill) obj;
			if (name.equals(skill.getName())
					&& description.equals(skill.getDescription())
					&& rating == skill.getRating() 
					&& employeeID == skill.getEmployeeID()) {
				
				return true;
			}
		}
		return false;
	}
	
}
