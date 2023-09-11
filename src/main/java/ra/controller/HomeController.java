package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.dto.request.FormLoginDto;
import ra.model.dto.request.FormRegisterDto;

@Controller
public class HomeController {
    @GetMapping
    public String home(){
        return "index";
    }
    @GetMapping("/form-login")
    public ModelAndView login(){
        return new ModelAndView("form-login","form_login",new FormLoginDto());
    }
    @GetMapping("/form-register")
    public String register(Model model){
        model.addAttribute("form_register",new FormRegisterDto());
        return "form-register";
    }
}
