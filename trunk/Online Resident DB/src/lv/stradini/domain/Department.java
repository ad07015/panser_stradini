package lv.stradini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Department {
	
	private int departmentPk;
	private String nosaukums;
	
	private Facility facility;
	private Doctor vaditajs;
	
	public Department() {
	}

	@Id
	@Column(name="DEPARTMENT_PK")
	@GeneratedValue()
	public int getDepartmentPk() {
		return departmentPk;
	}

	public void setDepartmentPk(int departmentPk) {
		this.departmentPk = departmentPk;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}

	@ManyToOne(targetEntity=Facility.class)
	@JoinColumn(name="FACILITY_FK", nullable=false)
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@ManyToOne(targetEntity=Doctor.class)
	@JoinColumn(name="DOCTOR_FK", nullable=false)
	public Doctor getVaditajs() {
		return vaditajs;
	}

	public void setVaditajs(Doctor vaditajs) {
		this.vaditajs = vaditajs;
	}
}
