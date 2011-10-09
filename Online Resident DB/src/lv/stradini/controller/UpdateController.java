package lv.stradini.controller;

import lv.stradini.constants.Constants;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.util.LoggerUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/resident/updateResident.htm", "/doctor/updateDoctor.htm"}) //TODO: remove update resident mapping
public class UpdateController {

	private static Logger log = Logger.getLogger(LoggerUtils.getClassName(UpdateController.class));
	@Autowired
	private ResidentService residentService;
	
	@RequestMapping(method = RequestMethod.POST, params={"updateResidentID"})
	public ModelAndView showUpdateResidentForm(int updateResidentID) {
		log.info("Location");
		ModelAndView mav = new ModelAndView();
		Resident resident = residentService.findResidentByID(updateResidentID);
		mav.addObject("resident", resident);
		mav.addObject("actionType", Constants.ACTION_TYPE_UPDATE);
		mav.setViewName("add/addResident");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, params={"updateDoctorID"})
	public ModelAndView showUpdateDoctorForm(int updateDoctorID) {
		log.info("Location");
		ModelAndView mav = new ModelAndView();
		Doctor doctor = residentService.findDoctorByID(updateDoctorID);
		mav.addObject("doctor", doctor);
		mav.addObject("actionType", Constants.ACTION_TYPE_UPDATE);
		mav.setViewName("add/addDoctor");
		return mav;
	}
}
