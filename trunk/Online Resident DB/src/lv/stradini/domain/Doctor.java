package lv.stradini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(
	name = "getDoctorCountByPersonasKods",
	query = "from Doctor d where d.personasKods = :personasKods"
	)
})

@Entity
public class Doctor {
	private int doctorPk;
	private String vards;
	private String uzvards;
	private String personasKods;
	private String akademiskaisGrads;
	private String darbaVieta;
	private String specialitate;
	private String adrese;
	private String talrunaNumurs;
	private String epasts;
	private String komentari;
	
	public Doctor() {
	}

	public Doctor(int doctorPk, String vards, String uzvards,
			String personasKods, String akademiskaisAmats, String darbaVieta,
			String specialitate, String adrese, String talrunaNumurs,
			String epasts, String komentari) {
		super();
		this.doctorPk = doctorPk;
		this.vards = vards;
		this.uzvards = uzvards;
		this.personasKods = personasKods;
		this.akademiskaisGrads = akademiskaisAmats;
		this.darbaVieta = darbaVieta;
		this.specialitate = specialitate;
		this.adrese = adrese;
		this.talrunaNumurs = talrunaNumurs;
		this.epasts = epasts;
		this.komentari = komentari;
	}
	
	@Id
	@Column(name="DOCTOR_PK")
	@GeneratedValue
	public int getDoctorPk() {
		return doctorPk;
	}

	public void setDoctorPk(int doctorPk) {
		this.doctorPk = doctorPk;
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

	@Column(name="AKADEMISKAIS_GRADS")
	public String getAkademiskaisGrads() {
		return akademiskaisGrads;
	}

	public void setAkademiskaisGrads(String akademiskaisAmats) {
		this.akademiskaisGrads = akademiskaisAmats;
	}

	@Column(name="DARBA_VIETA")
	public String getDarbaVieta() {
		return darbaVieta;
	}

	public void setDarbaVieta(String darbaVieta) {
		this.darbaVieta = darbaVieta;
	}

	public String getSpecialitate() {
		return specialitate;
	}

	public void setSpecialitate(String specialitate) {
		this.specialitate = specialitate;
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
}
