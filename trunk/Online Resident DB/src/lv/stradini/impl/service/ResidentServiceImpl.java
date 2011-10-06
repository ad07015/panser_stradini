package lv.stradini.impl.service;

import java.util.LinkedList;
import java.util.List;

import lv.stradini.domain.Cycle;
import lv.stradini.domain.Department;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Facility;
import lv.stradini.domain.Heart;
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
	public Resident findResidentByID(int residentID) {
		return repo.findResidentByID(residentID);
	}

	@Override
	public Doctor findDoctorByID(int doctorID) {
		return repo.findDoctorByID(doctorID);
	}

	@Override
	public boolean insertResident(Resident resident) {
		return repo.insertResident(resident);
	}

	@Override
	public boolean deleteResidentByID(int residentID) {
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
	public boolean deleteHeartByID(int heartID) {
		return repo.deleteHeartByID(heartID);
	}

	@Override
	public Heart findHeartByID(int heartID) {
		return repo.findHeartByID(heartID);
	}

	@Override
	public boolean insertHeart(Heart heart, int residentFK) {
		return repo.insertHeart(heart, residentFK);
	}

	@Override
	public boolean updateHeart(Heart heart) {
		return repo.updateHeart(heart);
	}

	@Override
	public boolean insertCycle(Cycle cycle) {
		return repo.insertCycle(cycle);
	}

	@Override
	public LinkedList<Facility> fetchAllFacilities() {
		return repo.fetchAllFacilities();
	}

	@Override
	public LinkedList<Department> fetchAllDepartments() {
		return repo.fetchAllDepartments();
	}

	@Override
	public boolean deleteHeart(Heart heart) {
		return repo.deleteHeart(heart);
	}
}
