package lv.stradini.controller;



import lv.stradini.constants.Constants;
import lv.stradini.domain.Heart;
import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.util.LoggerUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/resident/updateHeart.htm") //TODO: fix to generic mapping
public class HeartController {

	private static Logger log = Logger.getLogger(LoggerUtils.getClassName(HeartController.class));
	@Autowired
	private ResidentService residentService;
	
//	@RequestMapping(value = "addHeart.htm", method = RequestMethod.POST, params={"actionType=addHeart"})
//	public ModelAndView showAddHeart(int residentFK) {
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("heart", new Heart());
//		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
//		mav.addObject("residentFK", residentFK);
//		mav.setViewName("/add/addHeart");
//		return mav;
//	}
	
	@RequestMapping(value="updateHeart.htm", method = RequestMethod.POST)
	public ModelAndView showUpdateHeartForm(int heartID, int residentFK) {
		log.info("In showUpdateHeartForm() method");
		ModelAndView mav = new ModelAndView();
		
		Heart heart = residentService.findHeartByID(heartID);
		
		log.info("Heart tips = " + heart.getTips());
		log.info("Heart komentari = " + heart.getKomentari());
		
		
		mav.addObject("heart", heart);
		mav.addObject("residentFK", residentFK);
		mav.addObject("actionType", Constants.ACTION_TYPE_UPDATE);
		mav.setViewName("add/addHeart");
		return mav;
	}
}
