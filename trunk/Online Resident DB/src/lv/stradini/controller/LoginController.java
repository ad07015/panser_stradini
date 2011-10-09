package lv.stradini.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lv.stradini.util.LoggerUtils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login/*.htm")
public class LoginController {
	private static final Logger log = Logger.getLogger(LoggerUtils.getClassName(LoginController.class));
	
	@RequestMapping(value="login.htm")
	public ModelAndView onRequestLogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		return mav;
	}
	
	@RequestMapping(value="error.htm")
	public ModelAndView onLoginError() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/error");
		return mav;
	}
	
	@RequestMapping(value="logout.htm")
	public ModelAndView onLogOut(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false); 

		if(session!=null) {
			log.info("Invalidating session with id: " + session.getId());
			session.invalidate();
		} 

		session = request.getSession(true); 
			
		ModelAndView mav = new ModelAndView();
		mav.addObject(session);
		mav.setViewName("login/logout");
		return mav;
	}
}
