package lv.stradini.domain;


public class Department {
	
	private long ID;
	private long facilityFK;
	private long doctorFK;
	private String nosaukums;
	
	public Department() {
		super();
	}

	public Department(long iD, long facilityFK, long doctorFK, String nosaukums) {
		super();
		ID = iD;
		this.facilityFK = facilityFK;
		this.doctorFK = doctorFK;
		this.nosaukums = nosaukums;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getFacilityFK() {
		return facilityFK;
	}

	public void setFacilityFK(long facilityFK) {
		this.facilityFK = facilityFK;
	}

	public long getDoctorFK() {
		return doctorFK;
	}

	public void setDoctorFK(long doctorFK) {
		this.doctorFK = doctorFK;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}
}
