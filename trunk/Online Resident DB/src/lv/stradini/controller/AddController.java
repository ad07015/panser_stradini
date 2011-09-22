package lv.stradini.controller;



import lv.stradini.constants.Constants;
import lv.stradini.dataAccessObject.Resident;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.validation.ResidentFormValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/add/addResident.htm")
public class AddController {

	private static Logger log = Logger.getLogger(AddController.class);
	@Autowired
	private ResidentService residentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showNewForm() {
		log.info("AddNewResController: in show()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", new Resident());
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.setViewName("add/addResident");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, params={"actionType=add"})
	public ModelAndView onSubmitNewForm(Resident resident, Errors errors, String actionType) {
		log.info("AddNewResController: in onSubmitNewForm()");
		log.info("actionType is " + actionType);
		ModelAndView mav = new ModelAndView();
		ResidentFormValidator validator = new ResidentFormValidator(residentService);
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
			message = Constants.MESSAGE_ADD_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_ADD_FAIL;
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
	
	@RequestMapping(method = RequestMethod.POST, params={"updateResidentID"})
	public ModelAndView showUpdateForm(@RequestParam("updateResidentID") long updateResidentID) {
		log.info("AddNewResController: in showUpdate()");
		ModelAndView mav = new ModelAndView();
		Resident resident = residentService.findResidentByID(updateResidentID);
		mav.addObject("resident", resident);
		mav.addObject("actionType", Constants.ACTION_TYPE_UPDATE);
		mav.setViewName("add/addResident");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, params={"actionType=update"})
	public ModelAndView onSubmitUpdateForm(Resident resident, Errors errors, String actionType, long residentID) {
		log.info("AddNewResController: in processUpdateForm()");
		ModelAndView mav = new ModelAndView();
		
		resident.setID(residentID);
		boolean result = residentService.updateResident(resident);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_UPDATE_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_UPDATE_FAIL;
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
}
