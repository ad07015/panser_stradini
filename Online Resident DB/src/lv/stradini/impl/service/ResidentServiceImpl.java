package lv.stradini.impl.service;

import java.util.List;

import lv.stradini.domain.Doctor;
import lv.stradini.domain.Resident;
import lv.stradini.interfaces.repository.ResidentRepository;
import lv.stradini.interfaces.service.ResidentService;

public class ResidentServiceImpl implements ResidentService {
	
	private final ResidentRepository repo;

	public ResidentServiceImpl(ResidentRepository repo){
		this.repo = repo;
	}
	
	@Override
	public List<Resident> fetchAllResidents() {
		return repo.fetchAllResidents();
	}

	@Override
	public List<Doctor> fetchAllDoctors() {
		return repo.fetchAllDoctors();
	}

	@Override
	public Resident findResidentByID(long residentID) {
		return repo.findResidentByID(residentID);
	}

	@Override
	public Doctor findDoctorByID(long doctorID) {
		return repo.findDoctorByID(doctorID);
	}

	@Override
	public boolean insertResident(Resident resident) {
		return repo.insertResident(resident);
	}

	@Override
	public boolean deleteResidentByID(long residentID) {
		return repo.deleteResidentByID(residentID);
	}

	@Override
	public int getResidentCountByPersonasKods(String personasKods) {
		return repo.getResidentCountByPersonasKods(personasKods);
	}

	@Override
	public boolean updateResident(Resident resident) {
		return repo.updateResident(resident);
	}

	@Override
	public Resident findResidentByPersonasKods(String personasKods) {
		return repo.findResidentByPersonasKods(personasKods);
	}

	@Override
	public boolean deleteHeartByID(long heartID) {
		return repo.deleteHeartByID(heartID);
	}
}
