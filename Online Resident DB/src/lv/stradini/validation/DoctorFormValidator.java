package lv.stradini.validation;

import lv.stradini.domain.Doctor;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.util.LoggerUtils;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public abstract class DoctorFormValidator implements Validator {

	protected static Logger log = Logger.getLogger(LoggerUtils.getClassName(DoctorFormValidator.class));
	protected ResidentService residentService;

	public DoctorFormValidator() {
	}

	@Override
	public boolean supports(Class aClass) {
		return Doctor.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vards", "error.emptyField");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uzvards", "error.emptyField");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "akademiskaisGrads", "error.emptyField");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "darbaVieta", "error.emptyField");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adrese", "error.emptyField");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "talrunaNumurs", "error.emptyField");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "epasts", "error.emptyField");
	}

	public void setResidentService(ResidentService residentService) {
		this.residentService = residentService;
	}
}
