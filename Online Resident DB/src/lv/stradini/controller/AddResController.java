package lv.stradini.controller;



import lv.stradini.dataAccessObject.Resident;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.validation.ResidentFormValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		setSuccessView("add/addResidentSuccess");
		setValidator(new ResidentFormValidator());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onAddResidentButtonPressed() {
		log.info("Add resident button has been pressed. Forwarding to Add resident form page");

		ModelAndView mav = new ModelAndView();
		
		mav.addObject("resident", new Resident());
		mav.setViewName("add/addResident");
		return mav;
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ModelAndView onAddResidentFormSubmit(Resident resident) {
//			log.info("Submitting new resident information to the database");
//			
//			log.info("Vards: " + resident.getVards());
//			log.info("Uzvards: " + resident.getUzvards());
//			
////			boolean result = residentService.insertResident(vards, uzvards, personasKods, darbaLigums, specialitate, universitate, studijuGads, adrese, talrunaNumurs, epasts, komentari);
////
////			String message;
////			if(result) {
////				message = "Success";
////			} else {
////				message = "Fail";
////			}
//			
//			ModelAndView mav = new ModelAndView();
//			
////			mav.addObject("message", message);
//			mav.setViewName("add/addResident");
//			return mav;
//	}
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		Resident resident = (Resident) command;
		log.info("Person submittest successfully! Name is " + resident.getVards() + " " + resident.getUzvards());
		
		boolean result = residentService.insertResident(resident);
		String message;
		if(result) {
			message = "Success";
		} else {
			message = "Fail";
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", resident);
		mav.addObject("message", message);
		mav.setViewName(getFormView());
		return mav;
	}
}
