package lv.stradini.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="RESIDENT_CYCLE")
@AssociationOverrides({
	@AssociationOverride(name = "pk.resident", 
		joinColumns = @JoinColumn(name = "RESIDENT_PK")),
	@AssociationOverride(name = "pk.cycle", 
		joinColumns = @JoinColumn(name = "CYCLE_PK")) })
public class ResidentCycle {
	
	private ResidentCycleId pk = new ResidentCycleId();
	private boolean passed;
	
	public ResidentCycle() {
	}

	@EmbeddedId
	@Column(name="RESIDENT_CYCLE_PK")
	public ResidentCycleId getPk() {
		return pk;
	}

	public void setPk(ResidentCycleId pk) {
		this.pk = pk;
	}

	@Column(name="PASSED")
	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	
	@Transient
	public Resident getResident() {
		return this.pk.getResident();
	}
	
	public void setResident(Resident resident) {
		this.pk.setResident(resident);
	}
	
	@Transient
	public Cycle getCycle() {
		return this.pk.getCycle();
	}
	
	public void setCycle(Cycle cycle) {
		this.pk.setCycle(cycle);
	}
}
