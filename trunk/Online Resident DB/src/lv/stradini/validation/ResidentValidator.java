package lv.stradini.validation;

import lv.stradini.dataAccessObject.Resident;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ResidentValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		return Resident.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Resident resident = (Resident) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vards", "field.required", "Required field");
	}
}
