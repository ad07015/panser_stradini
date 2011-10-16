package lv.stradini.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class ResidentCycleId implements Serializable {

	private Resident resident;
	private Cycle cycle;
	
	public ResidentCycleId() {
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
}
