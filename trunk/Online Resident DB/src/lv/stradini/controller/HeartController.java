package lv.stradini.controller;



import lv.stradini.constants.Constants;
import lv.stradini.domain.Heart;
import lv.stradini.interfaces.service.ResidentService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/resident/updateHeart.htm") //TODO: fix to generic mapping
public class HeartController {

	private static Logger log = Logger.getLogger(HeartController.class);
	@Autowired
	private ResidentService residentService;
	private String[] typeList = new String[] {"Select...", "green", "black"};
	
	@RequestMapping(value="updateHeart.htm", method = RequestMethod.POST)
	public ModelAndView showUpdateHeart(long heartID) {
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
}
