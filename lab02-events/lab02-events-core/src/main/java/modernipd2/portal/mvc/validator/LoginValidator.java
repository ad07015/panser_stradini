package modernipd2.portal.mvc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Validator for "Login" form.
 * 
 * This bean is defined in Spring MVC configuration file: 
 * \lab02-events\lab02-events-portal\src\main\webapp\WEB-INF\events-servlet.xml
 */
public class LoginValidator implements Validator{

    
    public LoginValidator() {
        
    }
    
    @SuppressWarnings("rawtypes")
    public boolean supports(Class givenClass){
        return givenClass.equals(Object.class);
    }

    public void validate(Object obj, Errors errors){

    }
}