package ra.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ra.exception.UserNotFoundException;
import ra.model.dto.request.FormLoginDto;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFound(UserNotFoundException us){
        ModelAndView modelAndView = new ModelAndView("form-login");
        modelAndView.addObject("error_login",us.getMessage());
        modelAndView.addObject("form_login",us.getFormLoginDto());
        return modelAndView;
    }
}
