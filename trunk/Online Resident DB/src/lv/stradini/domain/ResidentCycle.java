package lv.stradini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RESIDENT_CYCLE")
public class ResidentCycle {
	
	private int residentCyclePk;
	private Resident resident;
	private Cycle cycle;
	private boolean passed;
	
	@Id
	@GeneratedValue
	@Column(name="RESIDENT_CYCLE_PK")
	public int getResidentCyclePk() {
		return residentCyclePk;
	}

	public void setResidentCyclePk(int residentCyclePk) {
		this.residentCyclePk = residentCyclePk;
	}

	@ManyToOne
	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
	}

	@ManyToOne
	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public ResidentCycle() {
	}

	@Column(name="PASSED")
	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}
}
