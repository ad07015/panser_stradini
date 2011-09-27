package lv.stradini.interfaces.service;

import java.util.List;

import lv.stradini.domain.Doctor;
import lv.stradini.domain.Heart;
import lv.stradini.domain.Resident;

public interface ResidentService {
	
	List<Resident> fetchAllResidents();
	
	List<Doctor> fetchAllDoctors();

	Resident findResidentByID(long residentID);

	Doctor findDoctorByID(long doctorID);
	
	boolean insertResident(Resident resident);

	boolean deleteResidentByID(long residentID);

	int getResidentCountByPersonasKods(String personasKods);

	boolean updateResident(Resident resident);

	Resident findResidentByPersonasKods(String personasKods);

	boolean deleteHeartByID(long heartID);

	Heart findHeartByID(long heartID);

	boolean insertHeart(Heart heart);

	boolean updateHeart(Heart heart);
}
