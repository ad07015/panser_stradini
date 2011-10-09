package lv.stradini.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Resident {
	private int residentPk;
	private String vards;
	private String uzvards;
	private String personasKods;
	private String darbaLigums;
	private String specialitate;
	private String universitate;
	private String studijuGads;
	private String adrese;
	private String talrunaNumurs;
	private String epasts;
	private String komentari;
	private List<Heart> heartList = new LinkedList<Heart>();
	
	public Resident() {
	}

	public Resident(int residentPk, String vards, String uzvards,
			String personasKods, String darbaLigums, String specialitate,
			String universitate, String studijuGads, String adrese,
			String talrunaNumurs, String epasts, String komentari) {
		super();
		this.residentPk = residentPk;
		this.vards = vards;
		this.uzvards = uzvards;
		this.personasKods = personasKods;
		this.darbaLigums = darbaLigums;
		this.specialitate = specialitate;
		this.universitate = universitate;
		this.studijuGads = studijuGads;
		this.adrese = adrese;
		this.talrunaNumurs = talrunaNumurs;
		this.epasts = epasts;
		this.komentari = komentari;
	}

	@Id
	@Column(name="RESIDENT_PK")
	@GeneratedValue
	public int getResidentPk() {
		return residentPk;
	}

	public void setResidentPk(int residentPk) {
		this.residentPk = residentPk;
	}

	@OneToMany(targetEntity=Heart.class, mappedBy="resident",
			cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	public List<Heart> getHeartList() {
		return heartList;
	}

	public void setHeartList(List<Heart> heartList) {
		this.heartList = heartList;
	}

	public String getVards() {
		return vards;
	}


	public void setVards(String vards) {
		this.vards = vards;
	}


	public String getUzvards() {
		return uzvards;
	}


	public void setUzvards(String uzvards) {
		this.uzvards = uzvards;
	}

	@Column(name="PERSONAS_KODS")
	public String getPersonasKods() {
		return personasKods;
	}


	public void setPersonasKods(String personasKods) {
		this.personasKods = personasKods;
	}

	@Column(name="DARBA_LIGUMS")
	public String getDarbaLigums() {
		return darbaLigums;
	}

	public void setDarbaLigums(String darbaLigums) {
		this.darbaLigums = darbaLigums;
	}


	public String getSpecialitate() {
		return specialitate;
	}


	public void setSpecialitate(String specialitate) {
		this.specialitate = specialitate;
	}


	public String getUniversitate() {
		return universitate;
	}


	public void setUniversitate(String universitate) {
		this.universitate = universitate;
	}

	@Column(name="STUDIJU_GADS")
	public String getStudijuGads() {
		return studijuGads;
	}


	public void setStudijuGads(String studijuGads) {
		this.studijuGads = studijuGads;
	}


	public String getAdrese() {
		return adrese;
	}


	public void setAdrese(String adrese) {
		this.adrese = adrese;
	}

	@Column(name="TALRUNA_NUMURS")
	public String getTalrunaNumurs() {
		return talrunaNumurs;
	}


	public void setTalrunaNumurs(String talrunaNumurs) {
		this.talrunaNumurs = talrunaNumurs;
	}


	public String getEpasts() {
		return epasts;
	}


	public void setEpasts(String epasts) {
		this.epasts = epasts;
	}


	public String getKomentari() {
		return komentari;
	}


	public void setKomentari(String komentari) {
		this.komentari = komentari;
	}
	
	public void removeHeart(Heart heart) {
//		if (heartList.contains(heart)) {
//			heart.setResident(null);
			this.heartList.remove(heart);
//		}
	}
	
	public void addHeart(Heart heart) {
		heart.setResident(this);
		this.heartList.add(heart);
	}
}
