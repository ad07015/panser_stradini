package lv.stradini.domain;

public class Heart {
	
	private long ID;
	private long residentFK;
	private String tips;
	private String komentari;
	
	public Heart() {
	}

	public Heart(long iD, long residentFK, String tips, String komentari) {
		super();
		ID = iD;
		this.residentFK = residentFK;
		this.tips = tips;
		this.komentari = komentari;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getResidentFK() {
		return residentFK;
	}

	public void setResidentFK(long residentFK) {
		this.residentFK = residentFK;
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
