package lv.stradini.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/resident/add*.htm", "/cycle/add*.htm", "/doctor/add*.htm", "/facility/add*.htm", "/department/add*.htm"})
public class AddController {

	private static Logger log = Logger.getLogger(LoggerUtils.getClassName(AddController.class));
	@Autowired
	private ResidentService residentService;
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
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
		log.info("Location");
		
		List<Department> departmentList = residentService.fetchAllDepartments();
		List<Doctor> doctorList = residentService.fetchAllDoctors();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("departmentList", departmentList);
		mav.addObject("doctorList", doctorList);
		mav.addObject("cycle", new Cycle());
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.setViewName("add/addCycle");
		return mav;
	}
	
	@RequestMapping(value="addFacility.htm", method = RequestMethod.GET)
	public ModelAndView showAddFacilityForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("facility", new Facility());
		mav.setViewName("add/addFacility");
		return mav;
	}
	
	@RequestMapping(value="addDepartment.htm", method = RequestMethod.GET)
	public ModelAndView showAddDepartmentForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("facilityList", residentService.fetchAllFacilities());
		mav.addObject("doctorList", residentService.fetchAllDoctors());
		mav.addObject("department", new Department());
		mav.setViewName("add/addDepartment");
		return mav;
	}
}
