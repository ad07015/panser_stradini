package lv.stradini.interfaces.service;

import java.util.List;

import lv.stradini.domain.Cycle;
import lv.stradini.domain.CyclePlanEntry;
import lv.stradini.domain.Department;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Facility;
import lv.stradini.domain.Heart;
import lv.stradini.domain.Resident;
import lv.stradini.domain.ResidentCycle;
import lv.stradini.domain.ResidentCycleId;

public interface ResidentService {
	
	List<Resident> fetchAllResidents();
	
	List<Doctor> fetchAllDoctors();

	Resident findResidentByID(int residentID);

	Doctor findDoctorByID(int doctorID);
	
	boolean insertResident(Resident resident);

	boolean deleteResidentByID(int residentID);
	
	boolean deleteResident(Resident resident);

	boolean updateResident(Resident resident);

	Resident findResidentByPersonasKods(String personasKods);
	
	boolean deleteHeart(Heart heart);

	boolean deleteHeartByID(int heartID);

	Heart findHeartByID(int heartID);

	boolean insertHeart(Heart heart, int residentFK);

	boolean updateHeart(Heart heart);

	boolean insertCycle(Cycle cycle);

	List<Facility> fetchAllFacilities();

	List<Department> fetchAllDepartments();

	boolean insertDoctor(Doctor doctor);

	boolean updateDoctor(Doctor doctor);
	
	int getPersonCountByPersonasKods(String personasKods);

	boolean deleteDoctor(Doctor doctor);

	Department findDepartmentByID(int departmentFk);

	List<Cycle> fetchAllCycles();

	Cycle findCycleByID(int i);

	ResidentCycle findResidentCycleByID(ResidentCycleId resCycId);

	boolean insertResidentCycle(Resident resident, Cycle cycle);

	<T> void update(T t);

	<T> void delete(T t);

	CyclePlanEntry findCyclePlanEntryByID(int cpeID);

	boolean insertCyclePlanEntry(CyclePlanEntry cyclePlanEntry);

	boolean insertFacility(Facility facility);

	Facility findFacilityByID(int facilityID);

	boolean save(Object object);
}
