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
    public ModelAndView login(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes,
                              ModelAndView modelAndView) {
        return modelAndView;
    }

    @PostMapping("/login")
        public ModelAndView loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                BindingResult bindingResult, RedirectAttributes redirectAttributes,
                ModelAndView modelAndView, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            modelAndView.setViewName("redirect:/users/login");

        } else {
            UserServiceModel user = this.userService.findByUsername(userLoginBindingModel.getUsername());


            if (user == null || !user.getPassword().equals(userLoginBindingModel.getPassword())) {
                redirectAttributes.addFlashAttribute("notFound", true);
                redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
                modelAndView.setViewName("redirect:/login");
            } else {

                httpSession.setAttribute("user", user);
                httpSession.setAttribute("id", user.getId());
                httpSession.setAttribute("role", user.getAuthorities());
                modelAndView.setViewName("redirect:/");
            }
        }

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


}
