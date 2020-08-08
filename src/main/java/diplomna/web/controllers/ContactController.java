package diplomna.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String showContact(ModelAndView modelAndView){
        modelAndView.setViewName("contact");
        return "contact";
    }
}
