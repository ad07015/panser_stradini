package lv.stradini.validation;

import lv.stradini.domain.Cycle;
import lv.stradini.domain.Heart;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.util.LoggerUtils;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CycleFormValidator implements Validator {

	protected static Logger log = Logger.getLogger(LoggerUtils.getClassName(UpdateResidentFormValidator.class));
	protected ResidentService residentService;

	public CycleFormValidator(ResidentService residentService) {
		this.residentService = residentService;
	}

	@Override
	public boolean supports(Class aClass) {
		return Cycle.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departmentFk", "error.emptyField");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pasniedzejsFk", "error.emptyField");
		Cycle cycle = (Cycle) obj;
		if (cycle.getPasniedzejsFk() == 0) {
			errors.rejectValue("pasniedzejsFk", "error.emptyField");
		}
		if (cycle.getDepartmentFk() == 0) {
			errors.rejectValue("departmentFk", "error.emptyField");
		}
	}

	public void setResidentService(ResidentService residentService) {
		this.residentService = residentService;
	}
}
