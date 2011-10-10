package lv.stradini.domain;

import java.util.Date;
import java.util.LinkedList;

public class Cycle {

	private long ID;
	private Doctor vaditajs;
//	private Facility facility;
//	private Department department;
	private int facilityFk;
	private int departmentFk;
	private Date sakumaDatums;
	private Date beiguDatums;
	private LinkedList<Resident> residentList;
	
	public Cycle() {
	}

	public Cycle(long iD, Doctor vaditajs, int facilityFk, int departmentFk,
			Date sakumaDatums, Date beiguDatums,
			LinkedList<Resident> residentList) {
		super();
		ID = iD;
		this.vaditajs = vaditajs;
		this.facilityFk = facilityFk;
		this.departmentFk = departmentFk;
		this.sakumaDatums = sakumaDatums;
		this.beiguDatums = beiguDatums;
		this.residentList = residentList;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Doctor getVaditajs() {
		return vaditajs;
	}

	public void setVaditajs(Doctor vaditajs) {
		this.vaditajs = vaditajs;
	}

	public int getFacilityFk() {
		return facilityFk;
	}

	public void setFacilityFk(int facilityFk) {
		this.facilityFk = facilityFk;
	}

	public int getDepartmentFk() {
		return departmentFk;
	}

	public void setDepartmentFk(int departmentFk) {
		this.departmentFk = departmentFk;
	}

	public Date getSakumaDatums() {
		return sakumaDatums;
	}

	public void setSakumaDatums(Date sakumaDatums) {
		this.sakumaDatums = sakumaDatums;
	}

	public Date getBeiguDatums() {
		return beiguDatums;
	}

	public void setBeiguDatums(Date beiguDatums) {
		this.beiguDatums = beiguDatums;
	}

	public LinkedList<Resident> getResidentList() {
		return residentList;
	}

	public void setResidentList(LinkedList<Resident> residentList) {
		this.residentList = residentList;
	}
}