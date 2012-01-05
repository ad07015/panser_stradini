package lv.lu.events.portal.mvc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lv.lu.events.interfaces.service.UserService;
import lv.lu.events.model.User;

/**
 * Validator for "Registration" form.
 * 
 * This bean is defined in Spring MVC configuration file: 
 * \lab02-events\lab02-events-portal\src\main\webapp\WEB-INF\events-servlet.xml
 */
public class RegistrationValidator implements Validator{

    private UserService userService;
    
    @Autowired
    public RegistrationValidator(UserService userService) {
        this.userService = userService;
    }    

    @SuppressWarnings("rawtypes")
    public boolean supports(Class givenClass){
        return givenClass.equals(User.class);
    }

    public void validate(Object obj, Errors errors){
        User user = (User)obj;
        if (user == null){
            errors.reject("error.nullpointer", "Null data received");
            return;
        }
        if (user.getUsername().trim().equals("")){
            errors.rejectValue("username", "error.empty", "Empty username!");
            return;
        }
        if (user.getPassword().trim().equals("")){
            errors.rejectValue("password", "error.empty", "Empty password!");
            return;
        }
        if (user.getPasswordConfirm().trim().equals("")){
            errors.rejectValue("passwordConfirm", "error.empty", "Empty password confirm!");
            return;
        }
        if (user.getFullName().trim().equals("")){
            errors.rejectValue("fullName", "error.empty", "Empty full name!");
            return;
        }
        if (user.getEmail().trim().equals("")){
            errors.rejectValue("email", "error.empty", "Empty email!");
            return;
        }
                    
        // check if username is free
        User userFromDB = userService.getByUsername(user.getUsername());                
        if (userFromDB != null){            
            errors.rejectValue("username", "error.empty", "User with this username already exists!");
        }       
    }
}