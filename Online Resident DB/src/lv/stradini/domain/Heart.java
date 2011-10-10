package lv.stradini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Heart {
	
	private int ID;
	private String tips;
	private String komentari;
	
//	@Transient
	private Resident resident;
	
	public Heart() {
	}

	public Heart(int iD, String tips, String komentari) {
		super();
		ID = iD;
		this.tips = tips;
		this.komentari = komentari;
	}

	@Id
	@Column(name="HEART_PK")
	@GeneratedValue
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	@ManyToOne(targetEntity=Resident.class)
	@JoinColumn(name="RESIDENT_FK", nullable=false)
	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
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
}
