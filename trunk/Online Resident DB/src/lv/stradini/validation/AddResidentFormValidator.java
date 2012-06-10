package lv.stradini.validation;

import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;

import org.springframework.validation.Errors;

public class AddResidentFormValidator extends ResidentFormValidator {
	
	public AddResidentFormValidator(ResidentService residentService) {
		this.residentService = residentService;
	}
	@Override
	public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		Resident resident = (Resident) obj;
		if (resident.getPersonasKods() != null && !resident.getPersonasKods().equals("")) {
			if (!resident.getPersonasKods().matches("[0-9]{6}-[0-9]{5}")) {
				errors.rejectValue("personasKods", "error.personasKods.invalid");
			} else  {
				int personCountWithNewPersonasKods = residentService.getPersonCountByPersonasKods(resident.getPersonasKods());
				if (personCountWithNewPersonasKods > 0) {
					errors.rejectValue("personasKods", "error.personasKods.exists");
				}
			}
		}
	}
}
