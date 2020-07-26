package diplomna.web;


import diplomna.model.bindingmodel.UserLoginBindingModel;
import diplomna.model.bindingmodel.UserRegisterBindingModel;
import diplomna.model.service.UserServiceModel;
import diplomna.service.UserService;
import diplomna.view.UserView;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller

//@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Tools tools;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, Tools tools, ModelMapper modelMapper) {
        this.userService = userService;
        this.tools = tools;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/login")
       public ModelAndView loginConfirm(@ModelAttribute ("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username");
        modelAndView.addObject("password");
        modelAndView.setViewName("login");

        return modelAndView;
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userRegisterBindingModel")
                                          UserRegisterBindingModel userRegisterBindingModel){

        this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";

    }

    @GetMapping("/profile")
    public String profile(Model model){
        UserServiceModel user = this.userService.findByUsername(this.tools.getLoggedUser());
        UserView userView = this.modelMapper.map(user,UserView.class);
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/user/buy/{productId}")
    public String buyProduct(@PathVariable ("productId") String productId){
        this.userService.buyProduct(productId);
        return "/home";
    }

}
