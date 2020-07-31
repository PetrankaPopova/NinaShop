package diplomna.web.controllers;

import diplomna.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/order")
    public String userOrder(Model model){
        model.addAttribute("allBoughtProducts"
                , this.userService.getAllBoughtProducts());
        return "order";
    }

}
