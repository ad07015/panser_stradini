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
public class Facility {
	
	private int facilityPk;
	private String nosaukums;
	
	private List<Department> departmentList = new LinkedList<Department>();
	
	public Facility() {
	}
	
	public Facility(int facilityPk, String nosaukums) {
		this.facilityPk = facilityPk;
		this.nosaukums = nosaukums;
	}
	
	@Id
	@Column(name="FACILITY_PK")
	@GeneratedValue()
	public int getFacilityPk() {
		return facilityPk;
	}

	public void setFacilityPk(int facilityPk) {
		this.facilityPk = facilityPk;
	}

	@ManyToMany
	@JoinTable(name="JOIN_DEPARTMENT_FACILITY",
			joinColumns={@JoinColumn(name="facilityPk")},
			inverseJoinColumns={@JoinColumn(name="departmentPk")})
	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}
}
