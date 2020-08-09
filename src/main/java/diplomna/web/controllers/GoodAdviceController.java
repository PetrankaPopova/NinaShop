package diplomna.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/advice")
public class GoodAdviceController {

    @GetMapping("")
    public String showAdvice(){
        return "good-advice";
    }

}
