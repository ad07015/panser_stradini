package lv.stradini.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Department {
	
	private int departmentPk;
	
	private int facilityFk;
	private int vaditajsFk;
	private String nosaukums;
	private String label;
	
	private Facility facility;
	private Doctor vaditajs;
	private List<Cycle> cycleList = new LinkedList<Cycle>();
	
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

	@OneToMany(targetEntity=Cycle.class, mappedBy="department",
			cascade=CascadeType.ALL)
	@org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	public List<Cycle> getCycleList() {
		return cycleList;
	}

	public void setCycleList(List<Cycle> cycleList) {
		this.cycleList = cycleList;
	}

	public void setVaditajs(Doctor vaditajs) {
		this.vaditajs = vaditajs;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}
	
	@Transient
	public String getLabel() {
		if (this.label == null)
			this.label = this.getFacility().getNosaukums() + " - " + this.getNosaukums();
		return this.label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Transient
	public int getFacilityFk() {
		return facilityFk;
	}

	public void setFacilityFk(int facilityFk) {
		this.facilityFk = facilityFk;
	}
	
	@Transient
	public int getVaditajsFk() {
		return vaditajsFk;
	}

	public void setVaditajsFk(int vaditajsFk) {
		this.vaditajsFk = vaditajsFk;
	}
}
