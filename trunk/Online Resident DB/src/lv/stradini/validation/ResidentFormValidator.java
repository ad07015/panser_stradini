package lv.stradini.validation;

import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.util.LoggerUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public abstract class ResidentFormValidator implements Validator {

	protected static Logger log = Logger.getLogger(LoggerUtils.getClassName(UpdateResidentFormValidator.class));
	protected ResidentService residentService;

	public ResidentFormValidator() {
	}

	@Override
	public boolean supports(Class aClass) {
		return Resident.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vards", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uzvards", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "darbaLigums", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "universitate", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studijuGads", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adrese", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "talrunaNumurs", "resident.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "epasts", "resident.empty");
	}

	public void setResidentService(ResidentService residentService) {
		this.residentService = residentService;
	}
}
