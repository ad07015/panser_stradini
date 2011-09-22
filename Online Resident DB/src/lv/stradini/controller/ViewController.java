package lv.stradini.controller;

import java.util.List;

import lv.stradini.constants.Constants;
import lv.stradini.dataAccessObject.Doctor;
import lv.stradini.dataAccessObject.Resident;
import lv.stradini.interfaces.service.ResidentService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
			message = Constants.MESSAGE_DELETE_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_DELETE_FAIL;
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
}
