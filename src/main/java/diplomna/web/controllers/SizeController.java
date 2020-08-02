package diplomna.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/size")
public class SizeController {

    @GetMapping("")
    public String showSize(){
        return "size";
    }
}
