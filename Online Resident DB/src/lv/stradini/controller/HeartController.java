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
@RequestMapping("/resident/*Heart.htm") //TODO: fix to generic mapping
public class HeartController {

	private static Logger log = Logger.getLogger(HeartController.class);
	@Autowired
	private ResidentService residentService;
	private String[] typeList = new String[] {"green", "black"};
	
	@RequestMapping(value="updateHeart.htm", method = RequestMethod.POST)
	public ModelAndView showUpdateHeartForm(long heartID) {
		log.info("In showUpdateHeartForm() method");
		ModelAndView mav = new ModelAndView();
		
		Heart heart = residentService.findHeartByID(heartID);
		
		log.info("Heart tips = " + heart.getTips());
		log.info("Heart komentari = " + heart.getKomentari());
		
		
		mav.addObject("heart", heart);
		mav.addObject("typeList", typeList);
		mav.addObject("actionType", Constants.ACTION_TYPE_UPDATE);
		mav.setViewName("add/addHeart");
		return mav;
	}
	
	@RequestMapping(value = "addHeart.htm", method = RequestMethod.POST, params={"actionType=addHeart"})
	public ModelAndView showAddHeart(long residentFK) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("heart", new Heart());
		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
		mav.addObject("residentFK", residentFK);
		mav.setViewName("/add/addHeart");
		return mav;
	}
	
	@RequestMapping(value = "addHeart.htm", method = RequestMethod.POST)
	public ModelAndView onSubmitNewHeartForm(Heart heart, long residentFK) {
		heart.setResidentFK(residentFK);
		
		System.out.println(heart.getID());
		System.out.println(heart.getResidentFK());
		System.out.println(heart.getTips());
		System.out.println(heart.getKomentari());
		
		/*
		Resident resident = residentService.findResidentByID(heart.getResidentFK());
		boolean result = residentService.insertHeart(heart);
		String message;
		String status;
		if(result) {
			message = Constants.MESSAGE_RESIDENT_ADD_SUCCESS;
			status = "success";
		} else {
			message = Constants.MESSAGE_RESIDENT_ADD_FAIL;
			status = "fail";
		}
		*/
		ModelAndView mav = new ModelAndView();
		mav.addObject("resident", new Resident());
/*		mav.addObject("resident", resident);
		mav.addObject(status);
		mav.addObject(message);*/
		mav.setViewName("view/residentDetails");
		return mav;
	}
}
