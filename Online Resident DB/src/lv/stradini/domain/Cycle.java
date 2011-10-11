package lv.stradini.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Cycle {

	private int cyclePk;
	private int pasniedzejsFk;
	private int departmentFk;
	private Date sakumaDatums;
	private Date beiguDatums;
	
	private Doctor pasniedzejs;
	private Department department;
	private List<ResidentCycle> residentCycleList = new LinkedList<ResidentCycle>();
	
	public Cycle() {
	}

	@Id
	@Column(name="CYCLE_PK")
	@GeneratedValue
	public int getCyclePk() {
		return cyclePk;
	}

	public void setCyclePk(int cyclePk) {
		this.cyclePk = cyclePk;
	}

	@ManyToOne(targetEntity=Doctor.class)
	@JoinColumn(name="DOCTOR_FK", nullable=false)
	public Doctor getPasniedzejs() {
		return pasniedzejs;
	}

	public void setPasniedzejs(Doctor pasniedzejs) {
		this.pasniedzejs = pasniedzejs;
	}
	
	@ManyToOne(targetEntity=Department.class)
	@JoinColumn(name="DEPARTMENT_FK", nullable=false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToMany(targetEntity=ResidentCycle.class, mappedBy="pk.cycle",
			cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ResidentCycle> getResidentCycleList() {
		return residentCycleList;
	}

	public void setResidentCycleList(List<ResidentCycle> residentCycleList) {
		this.residentCycleList = residentCycleList;
	}

	@Transient
	public int getDepartmentFk() {
		return departmentFk;
	}

	public void setDepartmentFk(int departmentFk) {
		this.departmentFk = departmentFk;
	}

	@Transient
	public int getPasniedzejsFk() {
		return pasniedzejsFk;
	}

	public void setPasniedzejsFk(int pasniedzejsFk) {
		this.pasniedzejsFk = pasniedzejsFk;
	}

	public Date getSakumaDatums() {
		return sakumaDatums;
	}

	public void setSakumaDatums(Date sakumaDatums) {
		this.sakumaDatums = sakumaDatums;
	}

	public Date getBeiguDatums() {
		return beiguDatums;
	}

	public void setBeiguDatums(Date beiguDatums) {
		this.beiguDatums = beiguDatums;
	}
	
	@Transient
	public List<Resident> getResidentList() {
		List<Resident> residentList = new LinkedList<Resident>();
		for (ResidentCycle resCyc : this.residentCycleList) {
			residentList.add(resCyc.getResident());
		}
		return residentList;
	}
}