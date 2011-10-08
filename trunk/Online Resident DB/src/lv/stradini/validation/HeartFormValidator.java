package lv.stradini.validation;

import lv.stradini.domain.Heart;
import lv.stradini.interfaces.service.ResidentService;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class HeartFormValidator implements Validator {

	protected static Logger log = Logger.getLogger(UpdateResidentFormValidator.class);
	protected ResidentService residentService;

	public HeartFormValidator(ResidentService residentService) {
		this.residentService = residentService;
	}

	@Override
	public boolean supports(Class aClass) {
		return Heart.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "komentari", "heart.empty");
		Heart heart = (Heart) obj;
		log.info(heart.getID());
		log.info(heart.getTips());
		log.info(heart.getKomentari());
		if (heart.getTips().equals("Select...")) {
			errors.rejectValue("tips", "heart.default_dropbox");
		}
	}

	public void setResidentService(ResidentService residentService) {
		this.residentService = residentService;
	}
}