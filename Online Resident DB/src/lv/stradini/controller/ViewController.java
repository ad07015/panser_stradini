package lv.stradini.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lv.stradini.constants.Constants;
import lv.stradini.domain.Cycle;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Heart;
import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.util.LoggerUtils;
import lv.stradini.validation.AddDoctorFormValidator;
import lv.stradini.validation.AddResidentFormValidator;
import lv.stradini.validation.DoctorFormValidator;
import lv.stradini.validation.HeartFormValidator;
import lv.stradini.validation.ResidentFormValidator;
import lv.stradini.validation.UpdateDoctorFormValidator;
import lv.stradini.validation.UpdateResidentFormValidator;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/*.htm")
public class ViewController {

	private static Logger log = Logger.getLogger(LoggerUtils.getClassName(ViewController.class));
	@Autowired
	private ResidentService residentService;
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

	@RequestMapping(value="residentList.htm")
	public ModelAndView showResidentList() {
		log.info("Entering search form in its initial state");

		List<Resident> residentList = residentService.fetchAllResidents();
		log.info("Resident list size = " + residentList.size());

		ModelAndView mav = new ModelAndView();

		mav.addObject("residentList", residentList);
		mav.setViewName("view/residentList");
		return mav;
	}
	
	@RequestMapping(value="doctorList.htm", method=RequestMethod.GET)
	public ModelAndView showDoctorList() {
		log.info("Entering search form in its initial state");

		List<Doctor> doctorList = residentService.fetchAllDoctors();
		log.info("Resident list size = " + doctorList.size());

		ModelAndView mav = new ModelAndView();

		mav.addObject("doctorList", doctorList);
		mav.setViewName("view/doctorList");
		return mav;
	}
	
	@RequestMapping(value="cycleList.htm", method=RequestMethod.GET)
	public ModelAndView showCycleList() {
		log.info("Showing cycle list");

		List<Cycle> cycleList = residentService.fetchAllCycles();

		ModelAndView mav = new ModelAndView();
		mav.addObject("cycleList", cycleList);
		mav.setViewName("view/cycleList");
		return mav;
	}
	
	@RequestMapping(value="residentDetails.htm")
	public ModelAndView onResidentDetails(
			@RequestParam("residentID") int residentID) throws Exception {
		log.info("Going to resident details page");
		log.info("ResidentID = " + residentID);
		Resident resident = residentService.findResidentByID(residentID);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("resident", resident);
		mav.setViewName("view/residentDetails");
		return mav;
	}
	
	@RequestMapping(value="doctorDetails.htm")
	public ModelAndView onDoctorDetails(
			@RequestParam("doctorID") int doctorID) throws Exception {
		log.info("Going to doctor details page");
		log.info("DoctorID = " + doctorID);
		Doctor doctor = residentService.findDoctorByID(doctorID);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("doctor", doctor);
		mav.setViewName("view/doctorDetails");
		return mav;
	}
	
	@RequestMapping(value="residentList.htm", params={"deleteResidentID"})
	public ModelAndView onDeleteResident(
			@RequestParam("deleteResidentID") int residentID) {
		log.info("Deleting resident");
		
		Resident resident = residentService.findResidentByID(residentID);
		boolean result = residentService.deleteResident(resident);
		
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_RESIDENT_DELETE_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_RESIDENT_DELETE_FAIL;
			status = "fail";
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("message", message);
		mav.addObject("status", status);
		mav.addObject("residentList", residentService.fetchAllResidents());
		mav.setViewName("view/residentList");
		return mav;
	}
	
	@RequestMapping(value="doctorList.htm",  method = RequestMethod.POST, params={"action=deleteDoctor"})
	public ModelAndView onDeleteDoctor(int doctorID) {
		log.info("Location");
		
		Doctor doctor = residentService.findDoctorByID(doctorID);
		boolean result = residentService.deleteDoctor(doctor);
		
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_DOCTOR_DELETE_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_DOCTOR_DELETE_FAIL;
			status = "fail";
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("message", message);
		mav.addObject("status", status);
		mav.addObject("doctorList", residentService.fetchAllDoctors());
		mav.setViewName("view/doctorList");
		return mav;
	}
	
	@RequestMapping(value="updateResident.htm", params={"updateResidentID"})
	public ModelAndView onUpdateResident(
			@RequestParam("updateResidentID") int residentID) {
		log.info("Updating resident");
		log.info("Resident ID = " + residentID);
		
		Resident resident = residentService.findResidentByID(residentID);
		log.info("Resident name is " + resident.getVards());
		log.info("Resident PK = " + resident.getResidentPk());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", resident);
		mav.addObject("actionType", Constants.ACTION_TYPE_UPDATE);
		mav.setViewName("add/addResident");
		return mav;
	}
	
	@RequestMapping(value="residentDetails.htm", method = RequestMethod.POST, params={"deleteHeart"})
	public ModelAndView onDeleteHeart(int heartID, int residentID) {
		
		Heart heart = residentService.findHeartByID(heartID);
//		heart.setResident(residentService.findResidentByID(residentID));
		boolean result = residentService.deleteHeart(heart);
		
		String message;
		String status;
		if(result) {	
			message = Constants.MESSAGE_HEART_DELETE_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_HEART_DELETE_FAIL;
			status = "fail";
		}
		
		
		Resident resident = residentService.findResidentByID(residentID);
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", resident);
		mav.addObject("message", message);
		mav.addObject("status", status);
		mav.setViewName("view/residentDetails");
		return mav;
	}
	
	@RequestMapping(value="residentList.htm", method = RequestMethod.POST, params={"action=addResident"})
	public ModelAndView onSubmitNewResidentForm(Resident resident, Errors errors, String actionType) {
		log.info("Location");
		ModelAndView mav = new ModelAndView();
		ResidentFormValidator validator = new AddResidentFormValidator(residentService);
		validator.validate(resident, errors);
		if (errors.hasErrors()) {
			mav.addObject("actionType", actionType);
			mav.addObject("resident", resident);
			mav.setViewName("add/addResident");
			return mav;
		}
		
		boolean result = residentService.insertResident(resident);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_RESIDENT_ADD_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_RESIDENT_ADD_FAIL;
			status = "fail";
		}
		mav.addObject("status", status);
		mav.addObject("message", message);
		mav.addObject("actionType", actionType);
		mav.addObject("resident", resident);
		mav.addObject("residentList", residentService.fetchAllResidents());
		mav.setViewName("view/residentList");
		return mav;
	}
	
	@RequestMapping(value="doctorList.htm", method=RequestMethod.POST, params={"action=addDoctor"})
	public ModelAndView onSubmitAddDoctorForm(Doctor doctor, Errors errors, String actionType) {
		log.info("Location");
		ModelAndView mav = new ModelAndView();
		
		DoctorFormValidator validator = new AddDoctorFormValidator(residentService);
		validator.validate(doctor, errors);
		log.debug("Error has errors: " + errors.hasErrors());
		if (errors.hasErrors()) {
			mav.addObject("actionType", actionType);
			mav.addObject("doctor", doctor);
			mav.setViewName("add/addDoctor");
			return mav;
		}
		
		boolean result = residentService.insertDoctor(doctor);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_DOCTOR_ADD_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_DOCTOR_ADD_FAIL;
			status = "fail";
		}
		
		mav.addObject("status", status);
		mav.addObject("message", message);
		mav.addObject("actionType", actionType);
		mav.addObject("doctor", doctor);
		mav.addObject("doctorList", residentService.fetchAllDoctors());
		mav.setViewName("view/doctorList");
		return mav;
	}
	
	@RequestMapping(value="residentList.htm", method = RequestMethod.POST, params={"action=updateResident"})
	public ModelAndView onSubmitUpdateResidentForm(Resident resident, Errors errors, String actionType, int residentID) {
		log.info("Location");
		ModelAndView mav = new ModelAndView();
		
		resident.setResidentPk((int) residentID);
		ResidentFormValidator validator = new UpdateResidentFormValidator(residentService);
		validator.validate(resident, errors);
		if (errors.hasErrors()) {
			mav.addObject("actionType", actionType);
			mav.addObject("resident", resident);
			mav.setViewName("add/addResident");
			return mav;
		}
		
		boolean result = residentService.updateResident(resident);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_RESIDENT_UPDATE_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_RESIDENT_UPDATE_FAIL;
			status = "fail";
		}
		mav.addObject("status", status);
		mav.addObject("message", message);
		mav.addObject("actionType", actionType);
		mav.addObject("resident", resident);
		mav.addObject("residentList", residentService.fetchAllResidents());
		mav.setViewName("view/residentList");
		return mav;
	}
	
	@RequestMapping(value="doctorList.htm", method = RequestMethod.POST, params={"action=updateDoctor"})
	public ModelAndView onSubmitUpdateDoctorForm(Doctor doctor, Errors errors, String actionType, int doctorID) {
		log.info("Location");
		ModelAndView mav = new ModelAndView();
		
		doctor.setDoctorPk(doctorID);
		DoctorFormValidator validator = new UpdateDoctorFormValidator(residentService);
		validator.validate(doctor, errors);
		if (errors.hasErrors()) {
			mav.addObject("actionType", actionType);
			mav.addObject("doctor", doctor);
			mav.setViewName("add/addDoctor");
			return mav;
		}
		
		boolean result = residentService.updateDoctor(doctor);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_DOCTOR_UPDATE_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_DOCTOR_UPDATE_FAIL;
			status = "fail";
		}
		
		mav.addObject("status", status);
		mav.addObject("message", message);
		mav.addObject("actionType", actionType);
		mav.addObject("doctor", doctor);
		mav.addObject("doctorList", residentService.fetchAllDoctors());
		mav.setViewName("view/doctorList");
		return mav;
	}

	@RequestMapping(value="residentDetails.htm", method = RequestMethod.POST, params={"action=addHeart"})
	public ModelAndView onSubmitAddHeartForm(Heart heart, Errors errors, int residentID, String actionType) {
		log.info("In onSubmitAddHeartForm() method");
		ModelAndView mav = new ModelAndView();
		
		HeartFormValidator validator = new HeartFormValidator(residentService);
		validator.validate(heart, errors);
		if (errors.hasErrors()) {
			mav.addObject("actionType", actionType);
			mav.addObject("residentFK", residentID);
			mav.addObject("heart", heart);
			mav.setViewName("add/addHeart");
			return mav;
		}
		
		boolean result = residentService.insertHeart(heart, residentID);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_HEART_ADD_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_HEART_ADD_FAIL;
			status = "fail";
		}
		log.info("Status: " + status);
		log.info("Message: " + message);
		
		
		Resident resident = residentService.findResidentByID(residentID);
		mav.addObject("resident", resident);
		mav.addObject("status", status);
		mav.addObject("message", message);
		mav.setViewName("view/residentDetails");
		return mav;
	}
	
	@RequestMapping(value="residentDetails.htm", method = RequestMethod.POST, params={"action=updateHeart"})
	public ModelAndView onSubmitUpdateHeartForm(Heart heart, Errors errors, int heartID, int residentID, String actionType) {
		log.info("In onSubmitUpdateHeartForm() method");
		heart.setID(heartID);
		ModelAndView mav = new ModelAndView();
		
		HeartFormValidator validator = new HeartFormValidator(residentService);
		validator.validate(heart, errors);
		if (errors.hasErrors()) {
			mav.addObject("actionType", actionType);
			mav.addObject("residentFK", residentID);
			mav.addObject("heart", heart);
			mav.setViewName("add/addHeart");
			return mav;
		}
		heart.setResident(residentService.findResidentByID(residentID));
		boolean result = residentService.updateHeart(heart);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_HEART_UPDATE_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_HEART_UPDATE_FAIL;
			status = "fail";
		}
		log.info("Status: " + status);
		log.info("Message: " + message);
		
		Resident resident = residentService.findResidentByID(residentID);
		mav.addObject("resident", resident);
		mav.addObject("status", status);
		mav.addObject("message", message);
		mav.setViewName("view/residentDetails");
		return mav;
		
	}

	@RequestMapping(value="cycleList.htm", method = RequestMethod.POST, params={"action=addCycle"})
	public ModelAndView onSubmitAddCycleForm(Cycle cycle, Errors errors, String actionType) {
		log.info("Location");
		ModelAndView mav = new ModelAndView();
		cycle.setDepartment(residentService.findDepartmentByID(cycle.getDepartmentFk()));
		cycle.setPasniedzejs(residentService.findDoctorByID(cycle.getPasniedzejsFk()));
		
		boolean result = residentService.insertCycle(cycle);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_CYCLE_ADD_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_CYCLE_ADD_FAIL;
			status = "fail";
		}
		log.info("Status: " + status);
		log.info("Message: " + message);
		
		List<Cycle> cycleList = residentService.fetchAllCycles();
		
		mav.addObject("cycleList", cycleList);
		mav.addObject("status", status);
		mav.addObject("message", message);
		mav.setViewName("view/cycleList");
		return mav;
	}
}
