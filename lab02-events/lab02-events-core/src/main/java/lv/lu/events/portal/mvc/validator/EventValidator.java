/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.lu.events.portal.mvc.validator;

import lv.lu.events.form.EventForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import lv.lu.events.model.PrivateEvent;
import lv.lu.events.portal.mvc.controller.NewEventController;
import org.springframework.validation.ValidationUtils;

/**
 *
 * @author Dmitry
 */
public class EventValidator implements Validator {

    @SuppressWarnings("rawtypes")
    @Override
    public boolean supports(Class givenClass) {
        return givenClass.equals(EventForm.class);
    }

    /**
     * Name and Access code fields are validated programmatically
     * Start time and End time are validated in the NewEventController.java
     * using initBinder method
     * @see NewEventController
     * @param obj
     * @param errors 
     */
    @Override
    public void validate(Object obj, Errors errors) {
        EventForm eventForm = (EventForm) obj;
        if (eventForm == null) {
            errors.reject("error.nullpointer", "Null data received");
            return;
        }

        // This field is mandatory, cannot be empty or whitespace
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.emptyField");
        
        // This fields is not mandatory, but it should not start or end with
        // whitespaces
        String accessCode = eventForm.getAccessCode();
        if (accessCode != null && accessCode.length() > 0) {
            if (accessCode.length() != accessCode.trim().length()) {
                errors.rejectValue("accessCode", "error.whiteSpaces");
            }
        }
        return;
    }
}
