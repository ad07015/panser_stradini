package lv.stradini.interfaces.service;

import java.util.List;

import lv.stradini.dataAccessObject.Doctor;
import lv.stradini.dataAccessObject.Resident;

public interface ResidentService {
	
	List<Resident> fetchAllResidents();
	
	List<Doctor> fetchAllDoctors();

	Resident findResidentByID(long residentID);

	Doctor findDoctorByID(long doctorID);
	
	boolean insertResident(Resident resident);

	boolean deleteResidentByID(long residentID);
}
