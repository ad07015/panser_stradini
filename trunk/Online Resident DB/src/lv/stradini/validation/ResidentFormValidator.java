package lv.stradini.validation;

import lv.stradini.dataAccessObject.Resident;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ResidentFormValidator implements Validator {

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
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "darbaLigums", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "universitate", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studijuGads", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adrese", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "talrunaNumurs", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "epasts", "resident.empty");
	}

}
