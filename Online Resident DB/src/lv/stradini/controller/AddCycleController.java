package lv.stradini.controller;



import lv.stradini.interfaces.service.ResidentService;
import lv.stradini.util.LoggerUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/cycle/blablabla.htm"})
public class AddCycleController {

	private static Logger log = Logger.getLogger(LoggerUtils.getClassName(AddCycleController.class));
	@Autowired
	private ResidentService residentService;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView showAddCycleForm() {
//		log.info("AddNewResController: in show()");
//		
//		LinkedList<Facility> facilityList = residentService.fetchAllFacilities();
////		LinkedList<Department> departmentList = residentService.fetchAllDepartments();
//		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("facilityList", facilityList);
////		mav.addObject("departmentList", departmentList);
//		mav.addObject("cycle", new Cycle());
//		mav.addObject("actionType", Constants.ACTION_TYPE_NEW);
//		mav.setViewName("add/addCycle");
//		return mav;
//	}
}
