package lv.stradini.validation;

import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UpdateResidentFormValidator implements Validator {

	private static Logger log = Logger.getLogger(UpdateResidentFormValidator.class);
	private ResidentService residentService;
	
	public UpdateResidentFormValidator(ResidentService residentService) {
		this.residentService = residentService;
	}

	@Override
	public boolean supports(Class aClass) {
		return Resident.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Resident resident = (Resident) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vards", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uzvards", "resident.empty");
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "darbaLigums", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "universitate", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studijuGads", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adrese", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "talrunaNumurs", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "epasts", "resident.empty");
	}

}
