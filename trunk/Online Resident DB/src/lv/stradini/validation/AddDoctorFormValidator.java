package lv.stradini.validation;

import lv.stradini.domain.Doctor;
import lv.stradini.interfaces.service.ResidentService;

import org.springframework.validation.Errors;

public class AddDoctorFormValidator extends DoctorFormValidator {
	
	public AddDoctorFormValidator(ResidentService residentService) {
		this.residentService = residentService;
	}
	@Override
	public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		Doctor doctor = (Doctor) obj;
		if (doctor.getPersonasKods() == null || doctor.getPersonasKods().equals("")) {
			errors.rejectValue("personasKods", "error.emptyField");			
		} else if (!doctor.getPersonasKods().matches("[0-9]{6}-[0-9]{5}")) {
			errors.rejectValue("personasKods", "error.personasKods.invalid");
		} else  {
			int personCountWithNewPersonasKods = residentService.getPersonCountByPersonasKods(doctor.getPersonasKods());
			if (personCountWithNewPersonasKods > 0) {
				errors.rejectValue("personasKods", "error.personasKods.exists");
			}
		}
	}
}
