package diplomna.web;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession httpSession){
        return "home";
    }

    @GetMapping("/home")
    public String home(HttpSession httpSession){
        return "home";
    }
}
