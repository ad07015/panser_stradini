package modernipd2.portal.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Validator for "Registration" form.
 * 
 * This bean is defined in Spring MVC configuration file: 
 * \lab02-events\lab02-events-portal\src\main\webapp\WEB-INF\events-servlet.xml
 */
public class RegistrationValidator implements Validator{

    public RegistrationValidator() {
    }    

    @SuppressWarnings("rawtypes")
    public boolean supports(Class givenClass){
        return givenClass.equals(Object.class);
    }

    public void validate(Object obj, Errors errors){
    }
}