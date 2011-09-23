package lv.stradini.validation;

import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AddResidentFormValidator extends ResidentFormValidator {
	
	public AddResidentFormValidator(ResidentService residentService) {
		this.residentService = residentService;
	}
	@Override
	public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		Resident resident = (Resident) obj;
		if (resident.getPersonasKods() == null || resident.getPersonasKods().equals("")) {
			errors.rejectValue("personasKods", "resident.empty");			
		} else if (!resident.getPersonasKods().matches("[0-9]{6}-[0-9]{5}")) {
			errors.rejectValue("personasKods", "resident.personasKods.invalid");
		} else if (residentService.getResidentCountByPersonasKods(resident.getPersonasKods()) > 0) {
			errors.rejectValue("personasKods", "resident.personasKods.exists");
		}
	}
}
