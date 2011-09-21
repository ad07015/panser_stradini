package lv.stradini.validation;

import lv.stradini.dataAccessObject.Person;
import lv.stradini.dataAccessObject.Resident;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonFormValidator implements Validator {

	private static Logger log = Logger.getLogger(PersonFormValidator.class);
	@Override
	public boolean supports(Class aClass) {
		return Person.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		log.info("Errors size = " + errors.getAllErrors().size());
		log.info("Validating new Person...");
		ValidationUtils.rejectIfEmpty(errors, "name.first", "label.person.name.first.empty");
		ValidationUtils.rejectIfEmpty(errors, "name.last", "label.person.name.last.empty");
	}
}
