package lv.stradini.controller.old;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.stradini.domain.old.Person;
import lv.stradini.validation.PersonFormValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@Controller
@RequestMapping("/person/add*.htm")
public class PersonFormController extends SimpleFormController {

	private static Logger log = Logger.getLogger(PersonFormController.class);
	private String[] languages = new String[] { "Java", "Ruby", "Python" };

	public PersonFormController() {
		setCommandName("person");
		setCommandClass(Person.class);
		setFormView("add/newPerson");
		setSuccessView("add/newPersonSuccess");
		setValidator(new PersonFormValidator());
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	}

	@Override
	protected Map referenceData(HttpServletRequest req) throws Exception {
		Map<String, String[]> data = new HashMap<String, String[]>();
		data.put("languages", languages);
		return data;
	}

	// protected void doSubmitAction(Object command) throws Exception {
	// Person person = (Person) command;
	// log.info("Person submittest successfully! Name is "
	// + person.getName().toString());
	// }
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		Person person = (Person) command;
		log.info("Person submittest successfully! Name is " + person.getName().toString());
		ModelAndView mav = new ModelAndView();
		mav.addObject("suggestedBook", "Enterprise Java principles.pdf");
		mav.addObject("person", person);
		mav.setViewName(getSuccessView());
		return mav;
	}
}
