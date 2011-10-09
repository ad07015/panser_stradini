package lv.stradini.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Department {
	
	private int departmentPk;
	private String nosaukums;
	
	private List<Facility> facultyList = new LinkedList<Facility>();
	
	public Department() {
	}

	public Department(int departmentPk, String nosaukums) {
		super();
		this.departmentPk = departmentPk;
		this.nosaukums = nosaukums;
	}
	
	@Id
	@Column(name="DEPARTMENT_PK")
	@GeneratedValue	
	public int getDepartmentPk() {
		return departmentPk;
	}

	public void setDepartmentPk(int departmentPk) {
		this.departmentPk = departmentPk;
	}

	@ManyToMany
	@JoinTable(name="JOIN_DEPARTMENT_FACILITY",
			joinColumns={@JoinColumn(name="departmentPk")},
			inverseJoinColumns={@JoinColumn(name="facilityPk")})
	public List<Facility> getFacultyList() {
		return facultyList;
	}

	public void setFacultyList(List<Facility> facultyList) {
		this.facultyList = facultyList;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}
}
