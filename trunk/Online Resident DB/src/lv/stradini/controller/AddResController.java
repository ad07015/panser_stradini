package lv.stradini.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.stradini.constants.Constants;
import lv.stradini.dataAccessObject.Resident;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.validation.ResidentFormValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@Controller
@RequestMapping("/add/addResident.htm")
public class AddResController extends SimpleFormController {

	private static Logger log = Logger.getLogger(AddResController.class);
	@Autowired
	private ResidentService residentService;
	
	public AddResController() {
		setCommandName("resident");
		setCommandClass(Resident.class);
		setFormView("add/addResident");
		setSuccessView("view/residentList");
	}

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ResidentFormValidator validator = new ResidentFormValidator(residentService);
		validator.validate(command, errors);
		return super.processFormSubmission(request, response, command, errors);
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		Resident resident = (Resident) command;
		log.info("Person submittest successfully! Name is " + resident.getVards() + " " + resident.getUzvards());

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
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", resident);
		mav.addObject("status", status);
		mav.addObject("residentList", residentService.fetchAllResidents());
		mav.addObject("message", message);
		mav.setViewName(getSuccessView());
		return mav;
	}
}
