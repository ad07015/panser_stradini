package lv.stradini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Heart {
	
	private long ID;
	private String tips;
	private String komentari;
	
	private Resident resident;
	
	public Heart() {
	}

	public Heart(long iD, String tips, String komentari) {
		super();
		ID = iD;
		this.tips = tips;
		this.komentari = komentari;
	}

	@Id
	@Column(name="HEART_PK")
	@GeneratedValue
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getKomentari() {
		return komentari;
	}

	public void setKomentari(String komentari) {
		this.komentari = komentari;
	}

	@ManyToOne
	@JoinColumn(name="RESIDENT_FK")
	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
	}
}
