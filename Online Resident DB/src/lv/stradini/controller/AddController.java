package lv.stradini.controller;

import java.util.LinkedList;

import lv.stradini.constants.Constants;
import lv.stradini.domain.Cycle;
import lv.stradini.domain.Department;
import lv.stradini.domain.Doctor;
import lv.stradini.domain.Facility;
import lv.stradini.domain.Heart;
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
@RequestMapping(value={"/resident/add*.htm", "/cycle/add*.htm", "/doctor/add*.htm"})
public class AddController {

	private static Logger log = Logger.getLogger(LoggerUtils.getClassName(AddController.class));
	@Autowired
	private ResidentService residentService;
	
	@RequestMapping(value="addResident.htm", method = RequestMethod.GET)
	public ModelAndView showAddResidentForm() {
		log.info("AddNewResController: in show()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", new Resident());
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.setViewName("add/addResident");
		return mav;
	}
	
	@RequestMapping(value="addHeart.htm", method = RequestMethod.POST)
	public ModelAndView showAddHeartForm(int residentFK) {
		log.info("AddNewResController: in showAddHeartForm()");
			
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("heart", new Heart());
		mav.addObject("residentFK", residentFK);
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.setViewName("add/addHeart");
		return mav;
	}
	
	@RequestMapping(value="addDoctor.htm", method=RequestMethod.GET)
	public ModelAndView showAddDoctorForm() {
		log.info("Location");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("doctor", new Doctor());
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.setViewName("add/addDoctor");
		return mav;
	}
	
	@RequestMapping(value="addCycle.htm", method = RequestMethod.GET)
	public ModelAndView showAddCycleForm() {
		log.info("AddNewResController: in show()");
		
		LinkedList<Facility> facilityList = residentService.fetchAllFacilities();
		LinkedList<Department> departmentList = residentService.fetchAllDepartments();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("facilityList", facilityList);
		mav.addObject("departmentList", departmentList);
		mav.addObject("cycle", new Cycle());
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.setViewName("add/addCycle");
		return mav;
	}
}
