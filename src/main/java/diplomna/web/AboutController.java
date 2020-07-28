package diplomna.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/about")
public class AboutController {

    @GetMapping("")
    public String about(Model model){
        return "about";
    }

}
