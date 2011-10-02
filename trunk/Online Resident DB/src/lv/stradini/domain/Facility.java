package lv.stradini.domain;


public class Facility {
	
	private long ID;
	private String nosaukums;
	
	public Facility(long iD, String nosaukums) {
		ID = iD;
		this.nosaukums = nosaukums;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}
}
