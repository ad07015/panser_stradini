package lv.stradini.controller;

import java.util.List;

import lv.stradini.constants.Constants;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Heart;
import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.validation.AddResidentFormValidator;
import lv.stradini.validation.ResidentFormValidator;
import lv.stradini.validation.UpdateResidentFormValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/*.htm")
public class ViewController {

	private static Logger log = Logger.getLogger(ViewController.class);
	@Autowired
	private ResidentService residentService;

	@RequestMapping(value="residentList.htm")
	public ModelAndView onInitialSearchFormState() {
		log.info("Entering search form in its initial state");

		List<Resident> residentList = residentService.fetchAllResidents();
		log.info("Resident list size = " + residentList.size());

		ModelAndView mav = new ModelAndView();

		mav.addObject("residentList", residentList);
		mav.setViewName("view/residentList");
		return mav;
	}
	
	@RequestMapping(value="doctorList.htm")
	public ModelAndView onDoctorList() {
		log.info("Entering search form in its initial state");

		List<Doctor> doctorList = residentService.fetchAllDoctors();
		log.info("Resident list size = " + doctorList.size());

		ModelAndView mav = new ModelAndView();

		mav.addObject("doctorList", doctorList);
		mav.setViewName("view/doctorList");
		return mav;
	}
	
	@RequestMapping(value="residentDetails.htm")
	public ModelAndView onResidentDetails(
			@RequestParam("residentID") long residentID) throws Exception {
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
			@RequestParam("doctorID") long doctorID) throws Exception {
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
			@RequestParam("deleteResidentID") long residentID) {
		log.info("Deleting resident");
		
		boolean result = residentService.deleteResidentByID(residentID);
		
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
	
	@RequestMapping(value="updateResident.htm", params={"updateResidentID"})
	public ModelAndView onUpdateResident(
			@RequestParam("updateResidentID") long residentID) {
		log.info("Updating resident");
		log.info("Resident ID = " + residentID);
		
		Resident resident = residentService.findResidentByID(residentID);
		log.info("Resident name is " + resident.getVards());
		log.info("Resident PK = " + resident.getID());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", resident);
		mav.addObject("actionType", Constants.ACTION_TYPE_UPDATE);
		mav.setViewName("add/addResident");
		return mav;
	}
	
	@RequestMapping(value="residentDetails.htm", method = RequestMethod.POST, params={"deleteHeart"})
	public ModelAndView onDeleteHeart(long heartID, long residentID) {
		
		boolean result = residentService.deleteHeartByID(heartID);
		
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
		log.info("AddNewResController: in onSubmitNewResidentForm()");
		log.info("actionType is " + actionType);
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
	
	@RequestMapping(value="residentList.htm", method = RequestMethod.POST, params={"action=updateResident"})
	public ModelAndView onSubmitUpdateResidentForm(Resident resident, Errors errors, String actionType, long residentID) {
		log.info("AddNewResController: in onSubmitUpdateResidentForm()");
		ModelAndView mav = new ModelAndView();
		
		resident.setID(residentID);
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
	
	@RequestMapping(value="residentDetails.htm", method = RequestMethod.POST, params={"action=updateHeart"})
	public ModelAndView onSubmitUpdateHeartForm(Heart heart, long heartID, long residentID) {
		log.info("In onSubmitUpdateHeartForm() method");
		heart.setResidentFK(residentID);
		heart.setID(heartID);
		
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
		
		Resident resident = residentService.findResidentByID(heart.getResidentFK());
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", resident);
		mav.addObject("status", status);
		mav.addObject("message", message);
		mav.setViewName("view/residentDetails");
		return mav;
	}
}
