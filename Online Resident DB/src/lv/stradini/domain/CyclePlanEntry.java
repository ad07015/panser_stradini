package lv.stradini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CYCLE_PLAN_ENTRY")
public class CyclePlanEntry {
	private int cyclePlanEntryPk;
	private Integer kurss;
	private String nosaukums;
	private String komentari;
	private Resident resident;
	
	public CyclePlanEntry() {
	}

	@Id
	@Column(name="CYCLE_PLAN_PK")
	@GeneratedValue
	public int getCyclePlanEntryPk() {
		return cyclePlanEntryPk;
	}

	public void setCyclePlanEntryPk(int cyclePlanEntryPk) {
		this.cyclePlanEntryPk = cyclePlanEntryPk;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}

	@ManyToOne(targetEntity=Resident.class)
	@JoinColumn(name="RESIDENT_FK", nullable=false)
	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
	}

	public Integer getKurss() {
		return kurss;
	}

	public void setKurss(Integer kurss) {
		this.kurss = kurss;
	}

	public String getKomentari() {
		return komentari;
	}

	public void setKomentari(String komentari) {
		this.komentari = komentari;
	}
}
