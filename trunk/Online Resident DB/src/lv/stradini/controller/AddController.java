package lv.stradini.controller;



import lv.stradini.constants.Constants;
import lv.stradini.domain.Heart;
import lv.stradini.domain.Resident;
import lv.stradini.interfaces.service.ResidentService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/resident/addResident.htm", "/resident/addHeart.htm", "/resident/updateResident.htm"}) //TODO: remove update resident mapping
public class AddController {

	private static Logger log = Logger.getLogger(AddController.class);
	@Autowired
	private ResidentService residentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showNewResidentForm() {
		log.info("AddNewResController: in show()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", new Resident());
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.setViewName("add/addResident");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, params={"updateResidentID"})
	public ModelAndView showUpdateResidentForm(long updateResidentID) {
		log.info("AddNewResController: in showUpdate()");
		ModelAndView mav = new ModelAndView();
		Resident resident = residentService.findResidentByID(updateResidentID);
		mav.addObject("resident", resident);
		mav.addObject("actionType", Constants.ACTION_TYPE_UPDATE);
		mav.setViewName("add/addResident");
		return mav;
	}
	
	@RequestMapping(value="addHeart.htm", method = RequestMethod.POST)
	public ModelAndView showAddHeartForm(long residentFK) {
		log.info("AddNewResController: in showAddHeartForm()");
		
		
		ModelAndView mav = new ModelAndView();
		
		Heart heart = new Heart();
		mav.addObject("heart", heart);
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.setViewName("add/addHeart");
		return mav;
	}
}
