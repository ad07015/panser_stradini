package lv.stradini.validation;

import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;

import org.springframework.validation.Errors;

public class UpdateResidentFormValidator extends ResidentFormValidator {

	public UpdateResidentFormValidator(ResidentService residentService) {
		setResidentService(residentService);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		Resident resident = (Resident) obj;
		if (resident.getPersonasKods() == null || resident.getPersonasKods().equals("")) {
			errors.rejectValue("personasKods", "resident.empty");			
		} else if (!resident.getPersonasKods().matches("[0-9]{6}-[0-9]{5}")) {
			errors.rejectValue("personasKods", "resident.personasKods.invalid");
		} else {
			log.info("Resident ID = " + resident.getID());
			Resident oldResident = residentService.findResidentByID(resident.getID());
			String newPersonasKods = resident.getPersonasKods();
			String oldPersonasKods = oldResident.getPersonasKods();
			int countFoundWithNewPersonasKods = residentService.getResidentCountByPersonasKods(newPersonasKods);
			log.info("Resident personas kods = " + resident.getPersonasKods());
			log.info("Found resident personas kods = " + oldResident.getPersonasKods());
			if (countFoundWithNewPersonasKods > 0 && !newPersonasKods.equals(oldPersonasKods)) {
				errors.rejectValue("personasKods", "resident.personasKods.exists");
			}
		}
	}
}
