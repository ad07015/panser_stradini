package lv.stradini.domain;

public class Doctor {
	private long ID;
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

	public Doctor(long iD, String vards, String uzvards,
			String personasKods, String akademiskaisAmats, String darbaVieta,
			String specialitate, String adrese, String talrunaNumurs,
			String epasts, String komentari) {
		super();
		ID = iD;
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

	public String getAkademiskaisGrads() {
		return akademiskaisGrads;
	}

	public void setAkademiskaisGrads(String akademiskaisAmats) {
		this.akademiskaisGrads = akademiskaisAmats;
	}

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
