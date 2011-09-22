package lv.stradini.interfaces.service;

import java.util.List;

import lv.stradini.domain.Doctor;
import lv.stradini.domain.Resident;

public interface ResidentService {
	
	List<Resident> fetchAllResidents();
	
	List<Doctor> fetchAllDoctors();

	Resident findResidentByID(long residentID);

	Doctor findDoctorByID(long doctorID);
	
	boolean insertResident(Resident resident);

	boolean deleteResidentByID(long residentID);

	int findResidentByPersonasKods(String personasKods);

	boolean updateResident(Resident resident);
}
