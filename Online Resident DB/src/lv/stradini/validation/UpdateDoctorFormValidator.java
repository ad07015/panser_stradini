package lv.stradini.validation;

import lv.stradini.domain.Doctor;
import lv.stradini.interfaces.service.ResidentService;

import org.springframework.validation.Errors;

public class UpdateDoctorFormValidator extends DoctorFormValidator {

	public UpdateDoctorFormValidator(ResidentService residentService) {
		setResidentService(residentService);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		Doctor doctor = (Doctor) obj;
		if (doctor.getPersonasKods() == null || doctor.getPersonasKods().equals("")) {
			errors.rejectValue("personasKods", "error.emptyField");			
		} else if (!doctor.getPersonasKods().matches("[0-9]{6}-[0-9]{5}")) {
			errors.rejectValue("personasKods", "error.personasKods.invalid");
		} else {
			log.info("Doctor ID = " + doctor.getDoctorPk());
			Doctor oldDoctor = residentService.findDoctorByID(doctor.getDoctorPk());
			String newPersonasKods = doctor.getPersonasKods();
			String oldPersonasKods = oldDoctor.getPersonasKods();
			int countFoundWithNewPersonasKods = residentService.getPersonCountByPersonasKods(newPersonasKods);
			log.info("Doctor personas kods = " + doctor.getPersonasKods());
			log.info("Found doctor personas kods = " + oldDoctor.getPersonasKods());
			if (countFoundWithNewPersonasKods > 0 && !newPersonasKods.equals(oldPersonasKods)) {
				errors.rejectValue("personasKods", "error.personasKods.exists");
			}
		}
	}
}
