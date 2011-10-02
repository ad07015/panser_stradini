package lv.stradini.hibernate.chapter1;

import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lv.stradini.domain.Heart;

@Entity
@Table(name="RESIDENT_INFO")
public class Resident {
	private long ID;
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
	@Transient
	private LinkedList<Heart> heartList;
	
	public Resident() {
	}

	public Resident(long iD, String vards, String uzvards,
			String personasKods, String darbaLigums, String specialitate,
			String universitate, String studijuGads, String adrese,
			String talrunaNumurs, String epasts, String komentari) {
		super();
		ID = iD;
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
	public long getID() {
		return ID;
	}


	public void setID(long iD) {
		ID = iD;
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


	public String getPersonasKods() {
		return personasKods;
	}


	public void setPersonasKods(String personasKods) {
		this.personasKods = personasKods;
	}


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

	public LinkedList<Heart> getHeartList() {
		return heartList;
	}

	public void setHeartList(LinkedList<Heart> heartList) {
		this.heartList = heartList;
	}
}
