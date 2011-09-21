package lv.stradini.interfaces.repository;

import java.util.List;

import lv.stradini.dataAccessObject.Doctor;
import lv.stradini.dataAccessObject.Resident;

public interface ResidentRepository {
	
	List<Resident> fetchAllResidents();
	
	List<Doctor> fetchAllDoctors();

	Resident findResidentByID(long residentID);

	Doctor findDoctorByID(long doctorID);

	boolean insertResident(Resident resident);

	boolean deleteResidentByID(long residentID);

	int findResidentByPersonasKods(String personasKods);
}
