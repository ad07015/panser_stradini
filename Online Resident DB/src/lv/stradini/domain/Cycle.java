package lv.stradini.domain;

import java.util.Date;
import java.util.LinkedList;

public class Cycle {

	private long ID;
	private Doctor vaditajs;
	private long facilityFK;
	private long departmentFK;
	private Date sakumaDatums;
	private Date beiguDatums;
	private LinkedList<Resident> residentList;
	
	public Cycle() {
	}

	public Cycle(long iD, Doctor vaditajs, long facilityFK, long departmentFK,
			Date sakumaDatums, Date beiguDatums,
			LinkedList<Resident> residentList) {
		super();
		ID = iD;
		this.vaditajs = vaditajs;
		this.facilityFK = facilityFK;
		this.departmentFK = departmentFK;
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

	public long getFacilityFK() {
		return facilityFK;
	}

	public void setFacilityFK(long facilityFK) {
		this.facilityFK = facilityFK;
	}

	public long getDepartmentFK() {
		return departmentFK;
	}

	public void setDepartmentFK(long departmentFK) {
		this.departmentFK = departmentFK;
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