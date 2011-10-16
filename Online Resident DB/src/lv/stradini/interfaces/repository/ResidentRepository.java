package lv.stradini.interfaces.repository;

import java.util.List;

import lv.stradini.domain.Cycle;
import lv.stradini.domain.Department;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Facility;
import lv.stradini.domain.Heart;
import lv.stradini.domain.Resident;
import lv.stradini.domain.ResidentCycle;
import lv.stradini.domain.ResidentCycleId;

public interface ResidentRepository {
	
	List<Resident> fetchAllResidents();
	
	List<Doctor> fetchAllDoctors();

	Resident findResidentByID(int residentID);

	Doctor findDoctorByID(int doctorID);

	boolean insertResident(Resident resident);

	boolean deleteResidentByID(int residentID);

	int getResidentCountByPersonasKods(String personasKods);

	boolean updateResident(Resident resident);

	Resident findResidentByPersonasKods(String personasKods);

	boolean deleteHeartByID(int heartID);

	Heart findHeartByID(int heartID);

	boolean insertHeart(Heart heart, int residentFK);

	boolean updateHeart(Heart heart);

	boolean insertCycle(Cycle cycle);

	List<Facility> fetchAllFacilities();

	List<Department> fetchAllDepartments();

	boolean deleteHeart(Heart heart);

	boolean deleteResident(Resident resident);

	boolean insertDoctor(Doctor doctor);

	boolean updateDoctor(Doctor doctor);

	int getDoctorCountByPersonasKods(String newPersonasKods);

	boolean deleteDoctor(Doctor doctor);

	Department findDepartmentByID(int departmentFk);

	List<Cycle> fetchAllCycles();

	Cycle findCycleByID(int i);

	ResidentCycle findResidentCycleByID(ResidentCycleId resCycId);

	boolean insertResidentCycle(Resident resident, Cycle cycle);
	
	<T> void update(T t);
}
